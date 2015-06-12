package io.codechobostudy.study.controller

import javax.servlet.http.HttpServletRequest

import com.fasterxml.jackson.databind.ObjectMapper
import io.codechobostudy.Application
import io.codechobostudy.sample.service.SampleService
import io.codechobostudy.study.domain.StudyGroupDomain
import io.codechobostudy.study.service.StudyService
import io.codechobostudy.user.domain.UserDomain
import io.codechobostudy.user.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation._


/**
 * Created by use on 2015-05-15.
 */
@Controller
class StudyController {

  @Autowired
  private var studyService: StudyService = _

  @Autowired
  private var userService: UserService = _

  @RequestMapping(value = Array("/study/main"))
  def studyMain(request: HttpServletRequest, model:Model): String = {
    getUserId(request)
    model.addAttribute("studyList", studyService.getAllStudyGroup)
    "/study/main"
  }


  @RequestMapping(value = Array("/study/create") ,method = Array(RequestMethod.POST))
  def create(request: HttpServletRequest, study: StudyGroupDomain, model: Model): String= {
    study.setLeader(getUserId(request))
    println("들어는 오는가" + study+getUserId(request))
    studyService.create(study)
    studyMain(request,model)
  }

  @RequestMapping(value = Array("/study/list"))
  @ResponseBody
  def list(study: StudyGroupDomain, model: Model) : java.util.List[StudyGroupDomain] = {
    studyService.getAllStudyGroup
  }

  @RequestMapping(value = Array("/study/join"))
  def studyJoin(study: StudyGroupDomain, model: Model): String = {

    val user = userService.getAllUser() get 0
//    val group = new StudyGroupJoinDomain
//    group.setId(study id)
//    group.setAuth("R")
//    group.setUserId(user.getUserId)
//    studyService.createJoin(group)

    model.addAttribute("studyList", studyService.getAllStudyGroup)
    model.addAttribute("het", "hey")
    "/study/main"
  }

  @RequestMapping(value = Array("/study/find"))
  def studyFind(study: StudyGroupDomain, model: Model): String = {
    model.addAttribute("studyList", studyService.findGroup(study studyName))
    "/study/main"
  }

  private def getUserId(request : HttpServletRequest): String ={
    var userId = request.getSession().getAttribute("userId")
    if(userId == null){
      userId = "TEST_USER"
      request.getSession().setAttribute("userId",userId)
    }
    userId.toString()
  }

}
