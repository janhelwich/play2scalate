package controllers.tools

import play.api.mvc.{Result, PlainResult}

trait HttpTools {

  def unCached(res:PlainResult):Result = {
    res.withHeaders("Cache-Control" -> "no-cache, no-store, must-revalidate", "Pragma" -> "no-cache", "Expires" -> "0")
  }
}
