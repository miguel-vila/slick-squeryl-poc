package co.s4n.slick.poc.persistence.slick

import slick.driver.PostgresDriver.simple._
import slick.driver.PostgresDriver.backend.{ Session , DatabaseDef }
import scala.slick.lifted.TableQuery
import co.s4n.slick.poc.persistence.slick.Tables.Suppliers

class SuppliersDAO(suppliers: TableQuery[Suppliers]) extends DAO() {

  def updateStreetByName(name: String, street: String) = defaultDB.withTransaction {
    implicit session =>
	    val q = for( supplier <- suppliers if supplier.name === name) yield supplier.street
	    q.update(street)
	    if(street == "carrera 1")
	    {
	    	println("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ")
	    	Thread.sleep(60000)
	    	println("DESPERTAAAAAAAAAAANDO")
	    }
  }
  
}