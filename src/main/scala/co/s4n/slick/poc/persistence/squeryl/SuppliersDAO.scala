package co.s4n.slick.poc.persistence.squeryl

import org.squeryl._
import java.sql.DriverManager
import org.squeryl.adapters.PostgreSqlAdapter

class SuppliersDAO {
  import org.squeryl.PrimitiveTypeMode._
  val url = "jdbc:postgresql:slick-poc-db"
  val driver = "org.postgresql.Driver"
  val user = "slick-poc-user"
  val password="absolute4n"
    
  Class.forName(driver)
  
  SessionFactory.concreteFactory = Some { () =>
    Session.create(DriverManager.getConnection(url, user, password), new PostgreSqlAdapter())
  }
  
  val suppliers = Tables.suppliers
  
  def updateStreetByName(name: String, street: String) = {
    transaction { 
      update(suppliers) {
        s => 
          if(street == "carrera 1"){
        	  println("ME DORMIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII <<ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ")
            Thread.sleep(10000)
          }
          val ret = where(s.name === name) set(s.street := street)
          println("transaction for " +street +"-")
          ret
      }
    }
  }
  
}