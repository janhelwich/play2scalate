package controllers.useragent

import play.api.mvc.{AnyContent, Request}

case class UserAgentOf(request: Request[AnyContent]) {
  val userAgent = request.headers.get("user-agent")
  val accept = request.headers.get("accept")
  val info: UAgentInfo = new UAgentInfo(userAgent.get, accept.get)

  def isMobile = {
    info.detectSmartphone() || info.detectTierTablet()
  }

  def isiOsSafari = (info.isIphone || info.detectIpad()) && !info.detectChrome()

}
