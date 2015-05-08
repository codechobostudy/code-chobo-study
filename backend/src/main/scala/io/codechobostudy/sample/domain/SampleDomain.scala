package io.codechobostudy.sample.domain

import javax.persistence.Entity
import scala.beans.BeanProperty


@Entity
class SampleDomain extends BaseEntity{

  @BeanProperty
  var categoryName: String = _

  @BeanProperty
  var categoryDesc: String = _
}
