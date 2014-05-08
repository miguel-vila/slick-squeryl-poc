package co.s4n.slick.poc.persistence.slick

import slick.driver.PostgresDriver.simple._
import slick.driver.PostgresDriver.backend.{ Session , DatabaseDef }
import scala.slick.lifted.TableQuery
import co.s4n.slick.poc.persistence.slick.Tables.Suppliers

class SuppliersDAO(database: DatabaseDef, suppliers: TableQuery[Suppliers]) extends DAO(database) {

  def updateStreetByName(name: String, street: String) = database.withSession {
    implicit session =>
	    val q = for( supplier <- suppliers if supplier.name === name) yield supplier.street
	    q.update(street)
  }
  
}