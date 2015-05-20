package io.codechobostudy.study.repository

import java.lang.Long
import java.util.List

import io.codechobostudy.study.domain.StudyDomain
import org.springframework.data.jpa.repository.JpaRepository

trait StudyRepository extends JpaRepository[StudyDomain, Long]{

  def findByStudyName(study: String) : List[StudyDomain]

}
