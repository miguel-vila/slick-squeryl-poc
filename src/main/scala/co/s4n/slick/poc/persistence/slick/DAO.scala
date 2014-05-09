package co.s4n.slick.poc.persistence.slick

import scala.slick.driver.PostgresDriver.simple._
import slick.driver.PostgresDriver.backend.{ Session , DatabaseDef }

abstract class DAO(protected val database: DatabaseDef)

object DAO {
  import co.s4n.slick.poc.constants.persistence._
  import co.s4n.slick.poc.persistence.slick.Tables._
  private val defaultDB = Database.forURL(url, driver = driver, user=user, password=password)
  
  val coffeesDao = new CoffeesDAO(defaultDB, coffees)
  val suppliersDao = new SuppliersDAO(defaultDB, suppliers)
  
}