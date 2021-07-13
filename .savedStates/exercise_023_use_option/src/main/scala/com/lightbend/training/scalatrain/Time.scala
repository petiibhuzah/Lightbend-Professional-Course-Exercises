package com.lightbend.training.scalatrain

import play.api.libs.json._
import scala.util.Try

object Time {

  def fromMinutes(minutes: Int): Time = {
    Time(minutes / 60, minutes % 60)
  }

  def fromMinutes(js: JsValue): Option[Time] = {
    for {
      hours <- Try((js \ "hours").as[Int])
      minutes <- Try((js \ "minutes").as[Int]).recover{case _: NumberFormatException => 0}
    } yield Time(hours,minutes)
  }.toOption
}

case class Time(hours: Int = 0, minutes: Int = 0)  extends Ordered[Time]{
  require(hours >= 0 && hours < 24,"Invalid Hours, must be >= 0 and < 24")
  require(minutes >= 0 && minutes < 60,"Invalid Minutes, must be >= 0 and < 60")

  val asMinutes: Int = hours * 60 + minutes

  def minus(that: Time): Int = asMinutes - that.asMinutes

  def -(that: Time): Int = minus(that)

  override lazy val toString: String = f"$hours%02d:$minutes%02d"

  override def compare(that: Time): Int = asMinutes - that.asMinutes

  def toJson: JsValue = JsObject(
    Map("hours" -> JsNumber(hours), "minutes" -> JsNumber(minutes))
  )
}
