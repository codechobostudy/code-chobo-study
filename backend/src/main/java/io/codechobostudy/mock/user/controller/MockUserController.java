package io.codechobostudy.mock.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.codechobostudy.mock.user.domain.MockUser;
import io.codechobostudy.mock.user.service.MockUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
@RequestMapping(value="/user")
public class MockUserController {
    @Autowired
    private MockUserService mockUserService;

    @RequestMapping(value = "/insertInitData")
    public @ResponseBody List<MockUser> insertInitData() throws JsonProcessingException {
        List<MockUser> userList = mockUserService.insertInitData();
        return userList;
    }

    @RequestMapping(value = "/deleteAllData")
    @ResponseBody
    public String deleteAllData() {
        mockUserService.deleteAllData();
        return "success";
    }
}
