package io.codechobostudy.study.service

import io.codechobostudy.study.domain.StudyDomain
import io.codechobostudy.study.repository.StudyRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class StudyService {

  @Autowired
  private var studyRepository: StudyRepository = _

  def create(study: StudyDomain): StudyDomain = {
    studyRepository.save(study)
  }

  def update(study: StudyDomain): StudyDomain = {
    val updateCategory = studyRepository.findOne(study.getId)
    updateCategory.setStudyName(study.studyName)
    updateCategory.setStudyDesc(study.studyDesc)

    studyRepository.save(updateCategory)
  }

  def getAllCategory: java.util.List[StudyDomain] = {
    studyRepository.findAll()
  }
}
