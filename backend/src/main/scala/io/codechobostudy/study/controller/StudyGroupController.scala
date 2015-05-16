package io.codechobostudy.study.controller

import io.codechobostudy.study.domain.StudyGroup
import io.codechobostudy.study.service.StudyGroupService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation._

@Controller
class StudyGroupController {

  private val STUDY_GROUP_VIEW_ROOT  = "/study/group/"

  @Autowired
  private var studyGroupService: StudyGroupService = _

  @RequestMapping(value = Array("/view/studyGroupCreate"))
  def groupViewCreate(): String = {
    STUDY_GROUP_VIEW_ROOT + "create"
  }


  @RequestMapping(value = Array("/api/study/group/"),  method = Array(RequestMethod.POST))
  def groupCreate(studyGroup: StudyGroup, model: Model): String = {
    model.addAttribute("studyGroup", studyGroupService.createGroup(studyGroup))
    STUDY_GROUP_VIEW_ROOT + "show"
  }

  @RequestMapping(value = Array("/api/study/group/{id}"), method= Array(RequestMethod.GET))
  @ResponseBody
  def groupShow(@PathVariable id: java.lang.Long): StudyGroup= {
    studyGroupService.showGroup(id)
  }

  @RequestMapping(value = Array("/api/study/group/{id}"), method = Array(RequestMethod.PUT))
  @ResponseBody
  def groupUpdate(@PathVariable id: java.lang.Long,
             @RequestBody studyGroup: StudyGroup): StudyGroup = {
    studyGroup.id = id
    studyGroupService.updateGroup(studyGroup)
  }

  @RequestMapping(value = Array("/api/study/group/{id}"), method = Array(RequestMethod.DELETE))
  @ResponseBody
  def groupDelete(@PathVariable id: java.lang.Long) = {

    val studyGroup = new StudyGroup
    studyGroup.id = id
    studyGroupService.deleteGroup(studyGroup)
  }
}
