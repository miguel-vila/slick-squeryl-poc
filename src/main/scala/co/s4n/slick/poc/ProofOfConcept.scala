package co.s4n.slick.poc

import scala.concurrent.ExecutionContext
import akka.actor.Actor
import co.s4n.slick.poc.flow.Messages._
import co.s4n.slick.poc.flow.SlickWriterActor
import akka.actor.Props
import akka.actor.ActorSystem
import akka.actor.ActorRef
import co.s4n.slick.poc.flow.SquerylWriterActor
import org.squeryl.StaleUpdateException


object ProofOfConcept extends App{

  import ExecutionContext.Implicits.global
  
  val maxReq = 1000

  /**
  ProofInParallel.enParalelo(maxReq)
         println("maxDuration = "+ProofInParallel.maxDuration)
   **/
  implicit lazy val system = ActorSystem( "slick-poc" )
  implicit lazy val ex: ExecutionContext = system.dispatcher

  
  
  val slickWriterActor = system.actorOf( SlickWriterActor.props() , "slickWriterActor")
  val squerylWriterActor = system.actorOf( SquerylWriterActor.props() , "squerylWriterActor")
  
  val writerActor = squerylWriterActor
  
  val rootActor = system.actorOf( RootActor.props(writerActor) , "rootActor")
  
  println("ProofOfConcept started!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
  
  
  for(i <- 1 to maxReq)
    rootActor ! SuppliersUpdateStreetByNameRequest("Superior Coffee", "carrera "+i)
  
   
  
}

object ProofInParallel {
import co.s4n.slick.poc.persistence.slick.DAO
  var staleExceptions = 0
  //val supplierDao = new co.s4n.slick.poc.persistence.squeryl.SuppliersDAO()
  val supplierDao = DAO.suppliersDao
  var maxDuration = Long.MinValue
    def enParalelo(maxReq: Int) = {
      (1 to maxReq).par.map {
	    i => 
	      try {
	        //println("UPDATING!!!!:"+i+"-")
    	   val initTime = System.currentTimeMillis()
	        supplierDao.updateStreetByName("Superior Coffee", "carrera "+i)
	        val duration = System.currentTimeMillis() - initTime
	        maxDuration = maxDuration max duration
       } catch {
         case staleExc : StaleUpdateException =>
           staleExceptions = staleExceptions + 1
           println("RETRIEEEEED!!!!! :"+staleExceptions)
         case exc: Exception =>
           println("uncatched error !!!!:" +exc)
         case t : Throwable =>
           println("uncatched Throwable !!!!:" +t)
       }
	  }
  }
}

class RootActor(writerActor: ActorRef) extends Actor {
  
  var maxDuration = Long.MinValue
  
  var processedMessages = 0
  
  def receive = {
    case updateSupplier: SuppliersUpdateStreetByNameRequest => 
      val currentTime = System.currentTimeMillis()
      println("received message = "+updateSupplier.toString)
      writerActor ! TimedMessage(updateSupplier, currentTime)
    case UpdateCompleted(initTime) =>
      processedMessages += 1
      val currentTime = System.currentTimeMillis()
      val duration = currentTime - initTime
      println("duration = "+duration)
      maxDuration = maxDuration max duration
      if(processedMessages >= ProofOfConcept.maxReq) {
        context.stop(writerActor)      
        context.stop(self)
      }
  }
  
  override def postStop(): Unit = {
    println("maxDuration = "+maxDuration)
  }
  
}

object RootActor {
  def props(writerActor: ActorRef): Props = Props(classOf[RootActor], writerActor)
}