package io.codechobostudy.study.service;

import io.codechobostudy.Application;
import io.codechobostudy.study.domain.StudyGroup;
import io.codechobostudy.study.fixture.GroupFixtureBuilder;
import io.codechobostudy.study.repository.StudyGroupRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class StudyGroupServiceTest {

    @Autowired
    private StudyGroupService studyGroupService;

    @Autowired
    private StudyGroupRepository studyGroupRepository;

    GroupFixtureBuilder groupFixtureBuilder;

    @Before
    public void setUp(){

        groupFixtureBuilder = GroupFixtureBuilder.anGroup()
            .withGroupName("테스트그룹")
            .withGroupCreateUserId("create-userId1")
            .withGroupLeaderUserId("leader-userId2")
            .withGroupGoals("Java의 정석 완독")
            .withGroupDesc("java의 정석 완동하는 스터디");
    }

    @Test
    @Rollback
    public void test_group_create(){

        //given
        StudyGroup studyGroup = groupFixtureBuilder.build();

        //when
        StudyGroup resultStudyGroup = studyGroupService.createGroup(studyGroup);

        //then
        assertNotNull(resultStudyGroup.getId());
        assertThat(resultStudyGroup.getGroupName(), is(studyGroup.getGroupName()));
    }

    @Test
    @Rollback
    public void test_group_update(){

        //given
        StudyGroup studyGroup = groupFixtureBuilder.build();
        StudyGroup studyGroup1 = groupFixtureBuilder
                .withGroupName("테스트그룹2")
                .build();
        studyGroup = studyGroupRepository.save(studyGroup);

        //when
        studyGroup1.setId(studyGroup.getId());
        StudyGroup resultGroup = studyGroupService.updateGroup(studyGroup1);

        //then
        assertThat(resultGroup.getGroupName(), is(studyGroup1.getGroupName()));
    }

    @Test
    public void test_group_delete(){

        //given
        StudyGroup studyGroup = groupFixtureBuilder.build();
        studyGroup = studyGroupRepository.save(studyGroup);

        //when
        studyGroupService.deleteGroup(studyGroup);

        //then
        StudyGroup result = studyGroupRepository.findOne(studyGroup.getId());
        assertThat(result.getGroupStatus(), is("D"));
    }

    @Test
    @Rollback
    public void test_group_only_one_show(){

        //given
        StudyGroup studyGroup = groupFixtureBuilder.build();
        studyGroup = studyGroupRepository.save(studyGroup);

        //when
        StudyGroup result = studyGroupService.showGroup(studyGroup.getId());

        //then
        assertThat(result.getId(), is(studyGroup.getId()));
        assertThat(result.getGroupName(), is(studyGroup.getGroupName()));
    }

    @Test
    @Rollback
    public void test_group_all_show(){

        //given
        StudyGroup studyGroup1 = groupFixtureBuilder.build();
        studyGroup1 = studyGroupRepository.save(studyGroup1);

        StudyGroup studyGroup2 = groupFixtureBuilder.withGroupName("테스트2").build();
        studyGroup2 = studyGroupRepository.save(studyGroup2);

        //when
        List<StudyGroup> result = studyGroupService.showGroupsAll();

        //then
        assertThat(result.size(), is(2));
    }

}
