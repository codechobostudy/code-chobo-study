package io.codechobostudy.study.domain

import javax.persistence.Entity

import scala.beans.BeanProperty


@Entity
class StudyDomain extends StudyEntity{

  @BeanProperty
  var studyName: String = _

  @BeanProperty
  var studyDesc: String = _

  override def toString: String = "studyName : "+studyName+", studyDesc : "+studyDesc
}
