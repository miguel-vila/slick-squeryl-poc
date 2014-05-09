package co.s4n.slick.poc.persistence.slick

import scala.slick.driver.PostgresDriver.simple._
import slick.driver.PostgresDriver.backend.{ Session , DatabaseDef }
import java.util.Properties

abstract class DAO() {
  import co.s4n.slick.poc.constants.persistence._
  
  val props = new Properties
    props.setProperty("autoCommit", "false")
  protected val defaultDB = Database.forURL(url, driver = driver, user=user, prop=props, password=password)
  
}

object DAO {
  import co.s4n.slick.poc.persistence.slick.Tables._
  
  val coffeesDao = new CoffeesDAO(coffees)
  val suppliersDao = new SuppliersDAO(suppliers)
  
}