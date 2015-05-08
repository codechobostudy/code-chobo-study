package io.codechobostudy.sample.service;

import io.codechobostudy.Application;
import io.codechobostudy.sample.domain.SampleDomain;
import io.codechobostudy.sample.repository.SampleRepository;
import io.codechobostudy.sample.fixture.SampleBuilder;
import io.codechobostudy.sample.service.SampleService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@Transactional
public class SampleServiceTest {

    @Autowired
    private SampleService sampleService;

    @Autowired
    private SampleRepository sampleRepository;

    private SampleDomain sampleDomain1;

    @Before
    public void setup(){

        sampleDomain1 = SampleBuilder
                .anCategory()
                .withCategoryName("Test")
                .withCategoryDesc("Test Category")
                .build();
    }

    @Test
    @Rollback
    public void test_sample_save(){

        //when
        SampleDomain resultSampleDomain = sampleService.create(sampleDomain1);

        //then
        assertNotNull(resultSampleDomain.getId());
        assertNotNull(resultSampleDomain.getCreatedAt());
        assertThat(resultSampleDomain.getCategoryName(), is(sampleDomain1.getCategoryName()));
    }

    @Test
    @Rollback
    public void test_sample_update(){

        //given
        SampleDomain saveSampleDomain = sampleRepository.save(sampleDomain1);
        saveSampleDomain.setCategoryName("Update Category");

        //when
        SampleDomain resultSampleDomain = sampleService.update(saveSampleDomain);

        //then
        assertThat(resultSampleDomain.getCategoryName(), is(saveSampleDomain.getCategoryName()));
    }

    @Test
    public void test_get_all_sample(){

        //given
        sampleRepository.save(sampleDomain1);

        //when
        List<SampleDomain> categories = sampleService.getAllCategory();

        //then
        assertThat(categories.size(), is(1));
    }
}
