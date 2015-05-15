package io.codechobostudy.user.controller;

import io.codechobostudy.user.domain.User;
import io.codechobostudy.user.exception.BadRequestException;
import io.codechobostudy.user.exception.ConflictException;
import io.codechobostudy.user.exception.InternalServerErrorException;
import io.codechobostudy.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionKey;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;

@Controller
public class SignupController {

    private final ProviderSignInUtils providerSignInUtils = new ProviderSignInUtils();

    @Autowired
    private UserService userService;

    @RequestMapping(value="/signup", method= RequestMethod.GET)
    public ConnectionKey signupForm(WebRequest request) {
        Connection<?> connection = providerSignInUtils.getConnectionFromSession(request);
        if (connection != null) {
            if (userService.findByProvider(connection.getKey()) != null) {
                // TODO redirect destination
            }
            return connection.getKey();
        } else {
            return null;
        }
    }

    @RequestMapping(value="/signup", method=RequestMethod.POST)
    public String signup(@Valid User userForm, BindingResult formBinding) {
        if (formBinding.hasErrors()) {
            throw new BadRequestException(HttpStatus.BAD_REQUEST.toString());
        }

        if (userService.findByNickname(userForm.getNickname()) != null) {
            throw new ConflictException(HttpStatus.CONFLICT.toString());
        }
        else {
            User user = userService.createUser(userForm);
            if (user != null) {
                userService.save(user);
                // TODO signin
                // TODO redirect signup complete page
            }

            throw new InternalServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        }

    }

}
