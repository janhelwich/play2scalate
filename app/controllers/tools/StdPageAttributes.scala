package controllers.tools

import play.api.mvc.{RequestHeader, AnyContent, Request}

object StdPageAttributes {
  implicit def session(implicit request: RequestHeader) = request.session

  def apply()(implicit request:Request[AnyContent]): Map[String, Object] = {
    Map(
    )
  }
}
