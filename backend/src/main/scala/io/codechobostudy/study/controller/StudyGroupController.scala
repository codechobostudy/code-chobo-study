package io.codechobostudy.study.controller

import com.fasterxml.jackson.databind.ObjectMapper
import io.codechobostudy.study.domain.StudyGroup
import io.codechobostudy.study.service.StudyGroupService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation._

@RestController
@RequestMapping(Array("/api/study/group"))
class StudyGroupController {

  @Autowired
  private var objectMapper: ObjectMapper = _

  @Autowired
  private var studyGroupService: StudyGroupService = _

  @RequestMapping(value = Array("/"),  method = Array(RequestMethod.POST))
  def groupCreate(@RequestBody studyGroup: StudyGroup): String = {
    objectMapper.writeValueAsString(studyGroupService.createGroup(studyGroup))
  }

  @RequestMapping(value = Array("/{id}"), method= Array(RequestMethod.GET))
  def groupShow(@PathVariable id: java.lang.Long): String = {
    objectMapper.writeValueAsString(studyGroupService.showGroup(id))
  }

  @RequestMapping(value = Array("/{id}"), method = Array(RequestMethod.PUT))
  def groupUpdate(@PathVariable id: java.lang.Long,
             @RequestBody studyGroup: StudyGroup): String = {
    studyGroup.id = id
    objectMapper.writeValueAsString(studyGroupService.updateGroup(studyGroup))
  }

  @RequestMapping(value = Array("/{id}"), method = Array(RequestMethod.DELETE))
  def groupDelete(@PathVariable id: java.lang.Long) = {

    val studyGroup = new StudyGroup
    studyGroup.id = id
    studyGroupService.deleteGroup(studyGroup)
  }
}
