package io.codechobostudy.study.controller;

import com.jayway.restassured.RestAssured;
import io.codechobostudy.Application;
import io.codechobostudy.study.domain.StudyGroup;
import io.codechobostudy.study.fixture.GroupFixtureBuilder;
import io.codechobostudy.study.repository.StudyGroupRepository;
import org.apache.http.HttpStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class StudyGroupControllerTest {

    private static final String apiPrefixUrl = "/api/study/group";
    private static final String contentType = "application/json; charset=UTF-8";

    @Value("${local.server.port}")
    private int port;

    private GroupFixtureBuilder groupFixtureBuilder;
    private StudyGroup studyGroup;

    @Autowired
    private StudyGroupRepository studyGroupRepository;

    @Before
    public void setUp(){
        RestAssured.port = port;

        groupFixtureBuilder = GroupFixtureBuilder.anGroup()
                .withGroupName("테스트그룹")
                .withGroupCreateUserId("create-userId1")
                .withGroupLeaderUserId("leader-userId2")
                .withGroupGoals("Java의 정석 완독")
                .withGroupDesc("java의 정석 완동하는 스터디");

        studyGroup = groupFixtureBuilder.build();
        studyGroup = studyGroupRepository.save(studyGroup);
    }

    @After
    public void cleanUp(){
        studyGroupRepository.deleteAll();
    }

    @Test
    public void test_study_group_create(){

        StudyGroup paramStudyGroup = groupFixtureBuilder.build();

        given()
            .contentType("application/x-www-form-urlencoded; charset=utf-8")
            .param("groupName", paramStudyGroup.getGroupName())
        .when()
            .post(apiPrefixUrl+"/")
        .then()
            .statusCode(HttpStatus.SC_OK)
            .body(containsString(paramStudyGroup.getGroupName()));
    }

    @Test
    public void test_study_group_only_one_show(){

        given()
            .contentType(contentType)
        .when()
            .get(apiPrefixUrl + "/" + studyGroup.getId())
        .then()
            .statusCode(HttpStatus.SC_OK)
            .body(containsString(studyGroup.getGroupName()));
    }

    @Test
    public void test_study_group_update(){

        StudyGroup paramGroup = groupFixtureBuilder
                .withGroupName("수정그룹")
                .build();

        given()
            .contentType(contentType)
            .body(paramGroup)
        .when()
            .put(apiPrefixUrl + "/" + studyGroup.getId())
        .then()
            .statusCode(HttpStatus.SC_OK)
            .body(containsString(paramGroup.getGroupName()));
    }

    @Test
    public void test_study_group_delete(){

        given()
            .contentType(contentType)
        .when()
            .delete(apiPrefixUrl + "/" + studyGroup.getId())
        .then()
            .statusCode(HttpStatus.SC_OK);
    }
}