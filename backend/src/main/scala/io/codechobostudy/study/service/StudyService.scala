package io.codechobostudy.study.service

import java.util

import io.codechobostudy.study.domain.StudyGroupDomain
import io.codechobostudy.study.repository.StudyRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class StudyService {

  @Autowired
  private var studyRepository: StudyRepository = _

  def create(study: StudyGroupDomain): StudyGroupDomain = {
    studyRepository.save(study)
  }

  def findGroup(studyName : String) : util.List[StudyGroupDomain] = {
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


}
