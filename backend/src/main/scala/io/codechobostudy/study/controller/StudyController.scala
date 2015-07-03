package io.codechobostudy.study.controller

import javax.servlet.http.HttpServletRequest

import com.fasterxml.jackson.databind.ObjectMapper
import io.codechobostudy.Application
import io.codechobostudy.sample.service.SampleService
import io.codechobostudy.study.domain.{StudyMemberDomain, StudyGroupDomain}
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

  @RequestMapping(value = Array("/study/"))
  def studyMain(request: HttpServletRequest, model:Model): String = {
    getUserId(request)
    model.addAttribute("studyList", studyService.getAllStudyGroup)
    "/study/list"
  }
  @RequestMapping(value = Array("/study/createPage"))
  def createPage(request: HttpServletRequest, model:Model): String = {
    "/study/create"
  }

  @RequestMapping(value = Array("/study/view/{id}"))
  def view(request: HttpServletRequest, model:Model, @PathVariable id : java.lang.Long): String = {
    model.addAttribute("data", studyService.getGroup(id) )
    "/study/view"
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

  @RequestMapping(value = Array("/study/join/{id}"))
  def studyJoin(request: HttpServletRequest, model: Model, @PathVariable id : java.lang.Long): String = {
    var member = new StudyMemberDomain()
    println(member.getId+"아이디아아아아")
    member.setMemberId( getUserId(request))
    studyService.join(member,id)

    studyMain(request,model)
  }

  @RequestMapping(value = Array("/study/find"))
  def studyFind(study: StudyGroupDomain, model: Model): String = {
    model.addAttribute("studyList", studyService.getGroup(study studyName))
    "/study/main"
  }


  @RequestMapping(value = Array("/study/changeId"))
  def changeId(request: HttpServletRequest, model:Model):String = {
    request.getSession().setAttribute("userId",request.getParameter("userId"))
    studyMain(request,model)
  }

  private def getUserId(request : HttpServletRequest): String ={
    var userId = request.getSession().getAttribute("userId")
    if(userId == null){
      userId = "TEST_USER"
    }

    request.getSession().setAttribute("userId",userId)
    userId.toString()
  }

}
