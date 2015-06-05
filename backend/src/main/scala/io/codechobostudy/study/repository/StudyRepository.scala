package io.codechobostudy.study.repository

import java.lang.Long
import java.util.List

import io.codechobostudy.study.domain.StudyGroupDomain
import org.springframework.data.jpa.repository.JpaRepository

trait StudyRepository extends JpaRepository[StudyGroupDomain, Long]{

  def findByStudyName(studyName: String) : List[StudyGroupDomain]
}
