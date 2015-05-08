package io.codechobostudy.sample.repository;

import io.codechobostudy.Application;
import io.codechobostudy.sample.domain.SampleDomain;
import io.codechobostudy.sample.fixture.SampleBuilder;
import io.codechobostudy.sample.repository.SampleRepository;
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
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@Transactional
public class SampleRepositoryTest {

    @Autowired
    private SampleRepository sampleRepository;

    SampleDomain sampleDomain1;
    SampleDomain sampleDomain2;

    @Before
    public void setup(){

        sampleDomain1 = SampleBuilder
                .anCategory()
                .withCategoryName("Test")
                .withCategoryDesc("Test sample")
                .build();

        sampleDomain2 = SampleBuilder
                .anCategory()
                .withCategoryName("Test2")
                .withCategoryDesc("Test sample")
                .build();
    }

    @Test
    @Rollback
    public void test_sample_save(){

        //when
        SampleDomain resultSampleDomain = sampleRepository.save(sampleDomain1);

        //then
        assertNotNull(resultSampleDomain.getId());
        assertNotNull(resultSampleDomain.getCreatedAt());
        assertThat(resultSampleDomain.getCategoryName(), is(sampleDomain1.getCategoryName()));
    }

    @Test
    @Rollback
    public void test_sample_update(){

        //given
        SampleDomain sampleDomain = sampleRepository.save(sampleDomain1);
        Long categoryId = sampleDomain.getId();

        //when
        sampleDomain.setCategoryName("UpdateName");
        sampleRepository.save(sampleDomain1);

        //then
        assertThat(sampleDomain.getId(), is(categoryId));
        assertThat(sampleDomain.getCategoryName(), is("UpdateName"));
    }

    @Test
    @Rollback
    public void test_find_by_name_one(){

        //given
        sampleRepository.save(sampleDomain1);

        //when
        List<SampleDomain> categories = sampleRepository.findByCategoryName(sampleDomain1.getCategoryName());

        //then
        assertThat(categories.size(), is(1));
    }
}
