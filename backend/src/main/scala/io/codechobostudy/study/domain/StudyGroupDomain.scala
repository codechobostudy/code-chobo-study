package io.codechobostudy.study.domain

import java.util
import javax.persistence._

import com.fasterxml.jackson.annotation.{JsonManagedReference, JsonIgnore}
import io.codechobostudy.study.domain.StudyMemberDomain

import scala.beans.BeanProperty


@Entity
@Table(name="STUDY_GROUP")
class StudyGroupDomain extends StudyBaseEntity{

  @BeanProperty
  @Column(name="study_name", nullable = false, length = 20)
  var studyName: String = _

  @BeanProperty
  @Column(name = "study_desc", nullable = false, length = 1000)
  var studyDesc: String = _

  @BeanProperty
  @Column(name = "leader_id", nullable = false, length = 20)
  var leader: String =_

  @BeanProperty
  /* @OneToMany( targetEntity = classOf[StudyGroupMemberDomain]  ,mappedBy = "studyGroup",cascade = Array(CascadeType.ALL), fetch = FetchType.LAZY )*/
  @OneToMany(mappedBy = "studyGroup",cascade = Array(CascadeType.ALL))
  @JsonManagedReference
  var members : util.List[StudyMemberDomain] =  new util.ArrayList[StudyMemberDomain]();

  def addMember(studyGroupMemberDomain: StudyMemberDomain) ={
    this.members.add(studyGroupMemberDomain)
  }

  private def getMembersString() :String ={
    if(members == null) return "NO"
    var result=""
    for(i <- 0 to members.size()){
      result += members.get(i).getMemberId
    }
    result
  }
}
