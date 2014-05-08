package co.s4n.slick.poc.flow

import akka.actor.{ Actor , Props }
import co.s4n.slick.poc.flow.Messages._
import co.s4n.slick.poc.persistence.DAO
import scala.concurrent.Future

class WriterActor extends Actor {

  val supplierDAO = DAO.suppliersDao
  import context.dispatcher
  
  def receive = {
    case TimedMessage(SuppliersUpdateStreetByNameRequest(name: String, street: String), startTime)  =>
//      println("writer actor recived update")
     val originalSender = sender
     Future { 
       supplierDAO.updateStreetByName(name, street)
       originalSender ! UpdateCompleted(startTime)
     }
  }
}

object WriterActor {
  def props(): Props = Props[ WriterActor ]
}