package io.codechobostudy.home.controller

import org.springframework.ui.Model
import org.springframework.web.bind.annotation.{RequestMapping, RestController}

@RestController
class HomeController {

  @RequestMapping(Array("/index"))
  def home (model: Model) = {
    model.addAttribute("message", "Hello World")
  }
}
