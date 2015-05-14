package io.codechobostudy.user.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import io.codechobostudy.user.domain.UserDomain;
import io.codechobostudy.user.repository.UserRepository;
import io.codechobostudy.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private ObjectMapper objectMapper;

  @Autowired
  private UserService userService;

    @Autowired
    UserRepository userRepository;


  @RequestMapping(value = ( "/create"),  method = RequestMethod.GET)
  public String createUser(UserDomain userDomain){
        UserDomain userDomain1 = new UserDomain();
        userDomain1.setUserId("scarfunk");
        userDomain1.setEmail("scarfunk@naver.com");

      userRepository.save(userDomain1);

      System.out.println(userRepository.findAll());
      return "hello";
  }


}
