package io.codechobostudy.study.controller

import javax.servlet.http.HttpServletRequest

import io.codechobostudy.sample.service.SampleService
import io.codechobostudy.study.domain.StudyDomain
import io.codechobostudy.study.service.StudyService
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

  @RequestMapping(value = Array("/study/main"))
  def main (study : StudyDomain, model: Model) :String = {
    if(study != null)
      studyService.create(study)

    model.addAttribute("studyList",studyService.getAllCategory )
    model.addAttribute("het","hey" )
    "/study/main"
  }

}
