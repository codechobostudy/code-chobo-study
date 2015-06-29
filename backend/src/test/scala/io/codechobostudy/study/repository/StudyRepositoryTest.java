package io.codechobostudy.study.repository;

import io.codechobostudy.Application;
import io.codechobostudy.sample.domain.SampleDomain;
import io.codechobostudy.sample.fixture.SampleBuilder;
import io.codechobostudy.sample.repository.SampleRepository;
import io.codechobostudy.study.domain.StudyGroupDomain;
import io.codechobostudy.user.domain.UserDomain;
import org.apache.catalina.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@Transactional
public class StudyRepositoryTest {

    @Autowired
    private StudyRepository studyRepository;

    UserDomain studyLeader;
    UserDomain studyMember;
    StudyGroupDomain groupDomain1;
    StudyGroupDomain groupDomain2;

    @Before
    public void setup(){
        studyLeader = new UserDomain();
        studyLeader.setEmail("leader@naver.com");
        studyLeader.setUserId("im_the_leader");

        studyMember = new UserDomain();
        studyMember.setEmail("leader@naver.com");
        studyMember.setUserId("scott");

        groupDomain1 = new StudyGroupDomain();
        groupDomain1.setStudyName("리더님과 함께하는 초코스");
        groupDomain1.setStudyDesc("싸게모십니다 월 50의 코스");

        groupDomain2 = new StudyGroupDomain();
        groupDomain2.setStudyName("자바스터디");
        groupDomain2.setStudyDesc("자바 기초부터 웹작성까지!");

    }

    @Test
    @Rollback
    public void test_study_group_create(){
        //when
        StudyGroupDomain studyGroupDomain = studyRepository.save(groupDomain1);
//        studyGroupDomain.setLeader(studyLeader);

        List<UserDomain> memberList = new ArrayList<UserDomain>();
        memberList.add(studyMember);
//        studyGroupDomain.setMembers(memberList);

        //then
        assertNotNull(studyGroupDomain.getId());
        assertNotNull(studyGroupDomain.getCreatedAt());
        assertThat(studyGroupDomain.getStudyName(), is(studyGroupDomain.getStudyName()));
//        assertThat(studyGroupDomain.getMembers().size() , is(1));
    }

}
