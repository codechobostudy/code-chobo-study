package io.codechobostudy.study.controller

import io.codechobostudy.study.domain.StudyGroup
import io.codechobostudy.study.service.StudyGroupService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation._

@RestController
@RequestMapping(Array("/api/study/group"))
class StudyGroupController {

  @Autowired
  private var studyGroupService: StudyGroupService = _

  @RequestMapping(value = Array("/"),  method = Array(RequestMethod.POST))
  def groupCreate(@RequestBody studyGroup: StudyGroup): StudyGroup = {
    studyGroupService.createGroup(studyGroup)
  }

  @RequestMapping(value = Array("/{id}"), method= Array(RequestMethod.GET))
  def groupShow(@PathVariable id: java.lang.Long): StudyGroup= {
    studyGroupService.showGroup(id)
  }

  @RequestMapping(value = Array("/{id}"), method = Array(RequestMethod.PUT))
  def groupUpdate(@PathVariable id: java.lang.Long,
             @RequestBody studyGroup: StudyGroup): StudyGroup = {
    studyGroup.id = id
    studyGroupService.updateGroup(studyGroup)
  }

  @RequestMapping(value = Array("/{id}"), method = Array(RequestMethod.DELETE))
  def groupDelete(@PathVariable id: java.lang.Long) = {

    val studyGroup = new StudyGroup
    studyGroup.id = id
    studyGroupService.deleteGroup(studyGroup)
  }
}
