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
        groupDomain.setStudyName("���ڽ��� �Բ��ϴ� ���͵�׷�");
        groupDomain.setStudyDesc("�ʺ����� ������� ����̵��� �����ϴ� ���͵�׷�. ���Ժ� 50����.");
        assertNotNull(groupDomain.getId());
        assertThat(groupDomain.getStudyName(),is("���ڽ��� �Բ��ϴ� ���͵�׷�"));
    }





}
