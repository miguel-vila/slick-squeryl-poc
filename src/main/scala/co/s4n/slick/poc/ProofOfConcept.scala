package co.s4n.slick.poc

import scala.concurrent.ExecutionContext
import akka.actor.Actor
import co.s4n.slick.poc.flow.Messages._
import co.s4n.slick.poc.flow.WriterActor
import akka.actor.Props
import akka.actor.ActorSystem

object ProofOfConcept extends App{

  import ExecutionContext.Implicits.global
  
  implicit lazy val system = ActorSystem( "slick-poc" )
  implicit lazy val ex: ExecutionContext = system.dispatcher
  
  val rootActor = system.actorOf( RootActor.props() , "writerActor")
  
  println("ProofOfConcept started!!!!!!!!!!!!!!!!!!!!!!!!!!!!")
  
  val maxReq = 1000
  for(i <- 1 to maxReq)
    rootActor ! SuppliersUpdateStreetByNameRequest("Superior Coffee", "carrera "+i)
  
}

class RootActor extends Actor {
  
  val writerActor = context.actorOf( WriterActor.props() , "writerActor")
  
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
      if(processedMessages >= ProofOfConcept.maxReq)
        context.stop(self)
  }
  
  override def postStop(): Unit = {
    println("maxDuration = "+maxDuration)
  }
  
}

object RootActor {
  def props(): Props = Props[RootActor]
}