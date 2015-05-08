package io.codechobostudy.sample.controller;

import io.codechobostudy.sample.domain.SampleDomain;
import io.codechobostudy.sample.repository.SampleRepository;
import io.codechobostudy.sample.fixture.SampleBuilder;
import io.codechobostudy.Application;

import org.apache.http.HttpStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.jayway.restassured.RestAssured;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class SampleControllerTest {

    @Autowired
    private SampleRepository sampleRepository;

    private static final String apiPrefixUrl = "/api/sample";
    private static final String contentType = "application/json";

    @Value("${local.server.port}")
    private int port;

    private SampleDomain paramSampleDomain;

    @Before
    public void setup(){
        RestAssured.port = port;

        paramSampleDomain = SampleBuilder.anCategory()
                .withCategoryName("Test Category")
                .withCategoryDesc("Test Category Desc")
                .build();
    }

    @Test
    public void test_sample_create(){

        given()
            .contentType(contentType)
            .body(paramSampleDomain)
        .when()
            .post(apiPrefixUrl + "/create")
        .then()
            .statusCode(HttpStatus.SC_OK)
            .body(containsString(paramSampleDomain.getCategoryName()));
    }

    @Test
    public void test_sample_update(){

        SampleDomain sampleDomain = sampleRepository.save(paramSampleDomain);
        sampleDomain.setCategoryName("Update Category");

        given()
            .contentType(contentType)
            .body(sampleDomain)
        .when()
            .put(apiPrefixUrl + "/update")
        .then()
            .statusCode(HttpStatus.SC_OK)
            .body(containsString("Update Category"));
    }

    @Test
    public void test_sample_get_all(){

        sampleRepository.save(paramSampleDomain);

        given()
        .when()
            .get(apiPrefixUrl+"/")
        .then()
            .statusCode(HttpStatus.SC_OK);
    }
}
