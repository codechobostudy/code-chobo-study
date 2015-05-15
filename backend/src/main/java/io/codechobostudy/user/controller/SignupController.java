package io.codechobostudy.user.controller;

import io.codechobostudy.user.domain.User;
import io.codechobostudy.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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
            // TODO throw exception badrequest (http status 400)
            return null;
        }

        if (userService.findByNickname(userForm.getNickname()) != null) {
            // TODO throw exception conflict (http status 409)
            return null;
        }
        else {
            User user = userService.createUser(userForm);
            if (user != null) {
                userService.save(user);
                // TODO signin
                // TODO redirect signup complete page
            }

            // TODO throw internal error (http status 500)
            return null;
        }

    }

}
