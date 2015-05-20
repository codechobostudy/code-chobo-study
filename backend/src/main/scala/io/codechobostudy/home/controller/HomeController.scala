package io.codechobostudy.home.controller

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.{RequestMapping, RestController}

@RestController
class HomeController {

  @PreAuthorize("hasRole('ROLE_USER')")
  @RequestMapping(Array("/index"))
  def home (model: Model) = {
    model.addAttribute("message", "Hello World")
  }
}
