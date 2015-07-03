package io.codechobostudy.study.domain

import javax.persistence._

import com.fasterxml.jackson.annotation.{JsonBackReference, JsonIgnore}

import scala.beans.BeanProperty


@Entity
@Table(name="STUDY_MEMBER")
class StudyMemberDomain extends StudyBaseEntity{

  @BeanProperty
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "members")
  @JsonBackReference
  var studyGroup: StudyGroupDomain = _

  @BeanProperty
  var memberId: String =_

}
