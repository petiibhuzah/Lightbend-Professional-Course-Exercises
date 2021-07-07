package com.lightbend.training.scalatrain
object Time {

  def fromMinutes(minutes: Int): Time = {
    Time(minutes / 60, minutes % 60)
  }
}

case class Time(hours: Int = 0, minutes: Int = 0) {
  require(hours >= 0 && hours < 24,"Invalid Hours, must be >= 0 and < 24")
  require(minutes >= 0 && minutes < 60,"Invalid Minutes, must be >= 0 and < 60")

  val asMinutes: Int = hours * 60 + minutes

  def minus(that: Time): Int = asMinutes - that.asMinutes

  def -(that: Time): Int = minus(that)

  override lazy val toString: String = s"$hours%02d:$minutes%02d"
}
