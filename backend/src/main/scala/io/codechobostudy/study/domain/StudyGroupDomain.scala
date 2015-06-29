package io.codechobostudy.study.domain

import java.util
import javax.persistence._

import scala.beans.BeanProperty


@Entity
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
  @OneToMany( targetEntity = classOf[StudyGroupMemberDomain]  ,mappedBy = "studyGroup",cascade = Array(CascadeType.ALL), fetch = FetchType.LAZY )
  var members : java.util.List[StudyGroupMemberDomain] = _

  def addMember(studyGroupMemberDomain: StudyGroupMemberDomain) ={
    this.members.add(studyGroupMemberDomain)
  }

  override def toString: String = "studyName : "+studyName+", studyDesc : "+studyDesc + "member : " +members

  private def getMembersString() :String ={
    if(members == null) return "NO"
    var result=""
    for(i <- 0 to members.size()){
      result += members.get(i).getMemberId
    }
    result
  }
}
