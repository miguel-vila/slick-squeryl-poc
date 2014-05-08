package co.s4n.slick.poc.flow

import akka.actor.Actor
import akka.actor.Props
import co.s4n.slick.poc.flow.Messages._
import scala.concurrent.Future
import co.s4n.slick.poc.persistence.squeryl.SuppliersDAO
import org.squeryl.StaleUpdateException

class SquerylWriterActor extends Actor {

  val supplierDAO = new SuppliersDAO
  import context.dispatcher
  
  var staleExceptions = 0
  
  def receive = {
    case TimedMessage(SuppliersUpdateStreetByNameRequest(name, street), startTime)  =>
      println("SquerylWriterActor received!!! : "+street)
     val originalSender = sender
     Future { 
       try {
    	   supplierDAO.updateStreetByName(name, street)
    	   originalSender ! UpdateCompleted(startTime)         
    	   println("retried :"+staleExceptions)   
       } catch {
         case staleExc : StaleUpdateException =>
           staleExceptions = staleExceptions + 1
           println("retried :"+staleExceptions)
           self ! TimedMessage(SuppliersUpdateStreetByNameRequest(name, street), startTime)
         case exc: Exception =>
           println("uncatched error !!!!:" +exc)
       }
     }
  }
  
  override def postStop(): Unit = {
    println("staleExceptions = "+staleExceptions)
  }
}

object SquerylWriterActor {
  def props(): Props = Props[ SquerylWriterActor ]
}