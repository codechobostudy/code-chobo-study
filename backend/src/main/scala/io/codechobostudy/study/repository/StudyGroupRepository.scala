package io.codechobostudy.study.repository

import io.codechobostudy.study.domain.StudyGroup
import org.springframework.data.jpa.repository.JpaRepository

trait StudyGroupRepository extends JpaRepository[StudyGroup, java.lang.Long]{

}
