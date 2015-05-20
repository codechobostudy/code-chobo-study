package io.codechobostudy.user.controller;

import io.codechobostudy.user.domain.User;
import io.codechobostudy.user.exception.BadRequestException;
import io.codechobostudy.user.exception.ConflictException;
import io.codechobostudy.user.exception.InternalServerErrorException;
import io.codechobostudy.user.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;

@Controller
public class SignupController {

    private final ProviderSignInUtils providerSignInUtils = new ProviderSignInUtils();

    @Autowired
    private UserService userService;

    @Autowired
    private SignInAdapter signInAdapter;

    @RequestMapping(value="/signup", method= RequestMethod.GET)
    public String signupForm(NativeWebRequest request) {
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);
        if (connection != null) {
            User user = userService.findByProvider(connection.getKey());
            if (user != null) {
                return getRedirectUrlAfterSignIn(Integer.toString(user.getIdx()), connection, request);
            }
            return "signup";
        } else {
            return null;
        }
    }

    @RequestMapping(value="/signup", method=RequestMethod.POST)
    public String signup(@Valid User userForm, BindingResult formBinding, NativeWebRequest request) {
        if (formBinding.hasErrors()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST.toString());
        }

        if (userService.findByNickname(userForm.getNickname()) != null) {
            throw new ConflictException(HttpStatus.CONFLICT.toString());
        }
        else {
            Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);
            if (connection != null && connection.getKey() != null) {
                User user = userService.createUser(userForm, connection.getKey());
                userService.save(user);
                return getRedirectUrlAfterSignIn(Integer.toString(user.getIdx()), connection, request);
            }

            throw new InternalServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        }

    }

    private String getRedirectUrlAfterSignIn(String userIdx, Connection<?> connection, NativeWebRequest request) {
        String redirectUrl = signInAdapter.signIn(userIdx, connection, request);
        return StringUtils.isEmpty(redirectUrl) ? "redirect:/" : "redirect:" + redirectUrl;
    }

}
