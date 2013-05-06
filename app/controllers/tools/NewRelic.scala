package com.newrelic.api.agent

import java.lang.String
import java.util

class NewRelic

object NewRelic {
  def recordMetric(name:java.lang.String , value:java.lang.Float) { }

  def recordResponseTimeMetric(name:java.lang.String , millis:java.lang.Long) { }

  def incrementCounter(name:java.lang.String ) { }

  def incrementCounter(name:java.lang.String , count:java.lang.Integer) { }

  def noticeError(throwable:java.lang.Throwable, params:java.util.Map[java.lang.String,java.lang.String] ) { }

  def noticeError(throwable:java.lang.Throwable ) { }

  def noticeError(message:java.lang.String , params:java.util.Map[java.lang.String,java.lang.String]) { }

  def noticeError(message:java.lang.String ) { }

  def addCustomParameter(key:java.lang.String , value: java.lang.Number) { }

  def addCustomParameter(key:java.lang.String , value:java.lang.String ) { }

  def setTransactionName(category:java.lang.String , name:java.lang.String ) { }

  def ignoreTransaction() { }

  def ignoreApdex() { }

  def setRequestAndResponse( request:com.newrelic.api.agent.Request,  response:com.newrelic.api.agent.Response) { }

  def getBrowserTimingHeader():java.lang.String = { "" }

  def getBrowserTimingFooter():java.lang.String = { "" }

  def setUserName(name:java.lang.String) { }

  def setAccountName(name:java.lang.String) { }

  def setProductName(name:java.lang.String) { }

}

trait Request {
  def getRequestURI():String
  def getHeader(name:String):String
  def getRemoteUser():String
  def getParameterNames():util.Enumeration[String]
  def getParameterValues(name:String):Array[String]
  def getAttribute(name:String ):Object
}

trait Response {
  def getStatus():java.lang.Integer
  def getStatusMessage():String
  def setHeader(name:String,value:String )
}