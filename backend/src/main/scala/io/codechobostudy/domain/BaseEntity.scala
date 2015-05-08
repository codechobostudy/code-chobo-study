package io.codechobostudy.domain

import java.util.Date
import javax.persistence._

import com.fasterxml.jackson.annotation.JsonFormat

import scala.beans.BeanProperty

@MappedSuperclass
class BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @BeanProperty
  var id: Long = _

  @Column(name = "created_at")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @BeanProperty
  var createdAt: Date = _

  @Column(name = "updated_at")
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @BeanProperty
  var updatedAt: Date = _

  @PrePersist
  def created = {
    this.updatedAt = new Date()
    this.createdAt = this.updatedAt
  }

  @PreUpdate
  def updated = {
    this.updatedAt = new Date()
  }
}
