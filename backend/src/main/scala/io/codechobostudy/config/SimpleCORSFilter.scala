package io.codechobostudy.config

import javax.servlet.http.HttpServletResponse
import javax.servlet._

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Component
@Profile(Array("dev"))
class SimpleCORSFilter extends Filter {

  def doFilter(req: ServletRequest, res: ServletResponse, chain: FilterChain) {
    val response = res.asInstanceOf[HttpServletResponse]
    response.setHeader("Access-Control-Allow-Origin", "*")
    response.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, DELETE")
    response.setHeader("Access-Control-Max-Age", "3600")
    response.setHeader("Access-Control-Allow-Headers", "origin, x-requested-with, content-type, accept")
    chain.doFilter(req, res)
  }

  def init(filterConfig: FilterConfig) {
  }

  def destroy() {
  }
}
