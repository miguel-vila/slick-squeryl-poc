package co.s4n.slick.poc.flow

import akka.actor.{ Actor , Props }
import co.s4n.slick.poc.flow.Messages._
import co.s4n.slick.poc.persistence.DAO
import scala.concurrent.Future

class WriterActor extends Actor {

  val supplierDAO = DAO.suppliersDao
  import context.dispatcher
  
  def receive = {
    case SuppliersUpdateStreetByNameRequest(name: String, street: String)  =>
     val originalSender = sender
     Future { 
       supplierDAO.updateStreetByName(name, street)
       originalSender ! UpdateCompleted
     }
  }
}

object WriterActor {
  def props(): Props = Props[ WriterActor ]
}