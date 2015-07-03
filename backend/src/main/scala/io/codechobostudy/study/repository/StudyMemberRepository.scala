package io.codechobostudy.study.repository

import java.lang.Long
import java.util.List

import io.codechobostudy.study.domain.{StudyMemberDomain, StudyGroupDomain}
import org.springframework.data.jpa.repository.JpaRepository

trait StudyMemberRepository extends JpaRepository[StudyMemberDomain, Long]{
}
