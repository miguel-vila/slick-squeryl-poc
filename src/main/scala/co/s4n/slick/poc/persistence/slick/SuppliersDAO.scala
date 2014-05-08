package co.s4n.slick.poc.persistence.slick

import slick.driver.PostgresDriver.simple._
import slick.driver.PostgresDriver.backend.{ Session , DatabaseDef }
import scala.slick.lifted.TableQuery
import co.s4n.slick.poc.persistence.slick.Tables.Suppliers

class SuppliersDAO(database: DatabaseDef, suppliers: TableQuery[Suppliers]) extends DAO(database) {

  def updateStreetByName(name: String, street: String) = database.withTransaction {
    implicit session =>
      if(street == "carrera 1"){
        	  println("ME DORMIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII <<ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ")
            Thread.sleep(60000)
          }
	    val q = for( supplier <- suppliers if supplier.name === name) yield supplier.street
	    q.update(street)
  }
  
}