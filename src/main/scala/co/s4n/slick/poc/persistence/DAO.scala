package co.s4n.slick.poc.persistence

import scala.slick.driver.PostgresDriver.simple._
import slick.driver.PostgresDriver.backend.{ Session , DatabaseDef }

abstract class DAO(protected val database: DatabaseDef) {

}

object DAO {
  import co.s4n.slick.poc.persistence.Tables._
  private val defaultDB = Database.forURL("jdbc:postgresql:slick-poc-db", driver = "org.postgresql.Driver", user="slick-poc-user", password="absolute4n")
  
  val coffeesDao = new CoffeesDAO(defaultDB, coffees)
  val suppliersDao = new SuppliersDAO(defaultDB, suppliers)
  
}