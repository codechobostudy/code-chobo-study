package io.codechobostudy.study.service

import javax.transaction.Transactional

import io.codechobostudy.study.domain.StudyGroup
import io.codechobostudy.study.repository.StudyGroupRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@Transactional
class StudyGroupService {

  @Autowired
  private var studyGroupRepository: StudyGroupRepository = _

  def createGroup(group: StudyGroup): StudyGroup = {
    studyGroupRepository.save(group)
  }

  /**
   * 그룹정보변경
   *
   * 그룹정보변경시에는 모든데이터가 같이 넘어오기 때문에 별도의 조건처리는 없이 진행
   * @param group
   * @return
   */
  def updateGroup(group: StudyGroup): StudyGroup = {
    val updateGroup = studyGroupRepository.findOne(group.id)

    updateGroup.groupLeaderUserId = group.groupLeaderUserId
    updateGroup.groupName = group.groupName
    updateGroup.groupDesc = group.groupDesc
    updateGroup.groupGoals = group.groupGoals

    studyGroupRepository.save(updateGroup)
  }

  /**
   * 그룹삭제
   *
   * 실제로는 삭제하지 않고 상태값만 'D' 로 변경한다.
   * @param group
   */
  def deleteGroup(group: StudyGroup) = {
    val deleteGroup =studyGroupRepository.findOne(group.id)
    deleteGroup.groupStatus = "D"
  }
}
