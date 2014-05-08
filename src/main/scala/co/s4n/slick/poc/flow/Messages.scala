package co.s4n.slick.poc.flow

object Messages {

  case class CoffeeQueryByNameRequest(name: String)
  case class SuppliersUpdateStreetByNameRequest(name: String, street: String)
  case class UpdateCompleted(startTime: Long)
  case class TimedMessage[T](message: T, startTime: Long)
  
}