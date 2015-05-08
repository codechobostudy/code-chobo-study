package io.codechobostudy.controller

import com.fasterxml.jackson.databind.ObjectMapper
import io.codechobostudy.domain.SampleDomain
import io.codechobostudy.service.SampleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{RequestMethod, RequestBody, RequestMapping, RestController}

@RestController
@RequestMapping(Array("/api/sample"))
class SampleController {

  @Autowired
  private var objectMapper: ObjectMapper = _

  @Autowired
  private var categoryService: SampleService = _

  @RequestMapping(value = Array( "/create"),  method = Array(RequestMethod.POST))
  def create(@RequestBody category: SampleDomain): String = {
    objectMapper.writeValueAsString(categoryService.create(category))
  }

  @RequestMapping(value = Array("/update"), method = Array(RequestMethod.PUT))
  def update(@RequestBody category: SampleDomain): String = {
    val updateCategory = categoryService.update(category)
    objectMapper.writeValueAsString(updateCategory)
  }

  @RequestMapping(Array("/"))
  def getAllCategory: String = {
    objectMapper.writeValueAsString(categoryService.getAllCategory)
  }
}
