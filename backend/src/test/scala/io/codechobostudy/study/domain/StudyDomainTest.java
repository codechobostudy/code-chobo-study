package io.codechobostudy.study.domain;

import org.junit.Test;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


/**
 * Created by use on 2015-05-29.
 */
public class StudyDomainTest {


    @Test
    public void studyGroupDomainTest(){
        StudyGroupDomain groupDomain = new StudyGroupDomain();
        groupDomain.setStudyName("초코스와 함께하는 스터디그룹");
        groupDomain.setStudyDesc("초보부터 고수까지 모든이들이 참여하는 스터디그룹. 가입비 50만원.");
        assertNotNull(groupDomain.getId());
        assertThat(groupDomain.getStudyName(),is("초코스와 함께하는 스터디그룹"));
    }





}
