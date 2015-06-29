package io.codechobostudy.study.domain

import javax.persistence._

import scala.beans.BeanProperty


@Entity
class StudyGroupMemberDomain extends StudyBaseEntity{

  @BeanProperty
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "members")
  var studyGroup: StudyGroupDomain = _

  @BeanProperty
  var memberId: String =_

}
