package io.codechobostudy.study.service

import java.util

import io.codechobostudy.study.domain.{StudyMemberDomain, StudyGroupDomain}
import io.codechobostudy.study.repository.{StudyMemberRepository, StudyRepository}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class StudyService {

  @Autowired
  private var studyRepository: StudyRepository = _
  @Autowired
  private var studyMemberRepository: StudyMemberRepository = _

  def create(study: StudyGroupDomain): StudyGroupDomain = {
    studyRepository.save(study)
  }

  def getGroup(id : java.lang.Long) : StudyGroupDomain = {
    studyRepository.findOne(id)
  }
  def getGroup(studyName : String) : util.List[StudyGroupDomain] = {
    studyRepository.findByStudyName(studyName)
  }

  def update(study: StudyGroupDomain): StudyGroupDomain = {
    val updateCategory = studyRepository.findOne(study id)
    updateCategory.setStudyName(study.studyName)
    updateCategory.setStudyDesc(study.studyDesc)

    studyRepository.save(updateCategory)
  }

  def getAllStudyGroup: java.util.List[StudyGroupDomain] = {
    studyRepository.findAll()
  }

  def join(member: StudyMemberDomain, id : java.lang.Long)={
    var group = studyRepository.findOne(id)
    try{
      group.addMember(member)
      println(member.getId+"ID!!")
      member.setStudyGroup(group)
      println(studyRepository.findOne(id).toString+"group!!")
      studyMemberRepository.save(member)
    }catch{
      case e : Exception => e.printStackTrace()
    }
  }

}
