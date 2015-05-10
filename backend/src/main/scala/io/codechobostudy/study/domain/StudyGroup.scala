package io.codechobostudy.study.domain

import javax.persistence.Entity

import scala.beans.BeanProperty

@Entity
class StudyGroup extends BaseEntity{

  @BeanProperty
  var groupName: String = _ //스터디그룹명

  @BeanProperty
  var groupCreateUserId: String = _ //개설자

  @BeanProperty
  var groupLeaderUserId: String = _ //스터디리더

  @BeanProperty
  var groupGoals: String = _  //스터디그룹목표

  @BeanProperty
  var groupDesc: String = _ //스터디그룹설명

  @BeanProperty
  var groupStatus: String = "N" //스터디그룹상태값 ( N: 정상 / D: 삭제 )
}
