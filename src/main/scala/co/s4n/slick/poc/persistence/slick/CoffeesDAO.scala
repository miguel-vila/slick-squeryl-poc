package co.s4n.slick.poc.persistence.slick

import slick.driver.PostgresDriver.simple._
import slick.driver.PostgresDriver.backend.{ Session , DatabaseDef }
import scala.slick.lifted.TableQuery
import co.s4n.slick.poc.persistence.slick.Tables.Coffees

class CoffeesDAO(coffees: TableQuery[Coffees]) extends DAO() {
  
  val querySalesByName = for {
    name <- Parameters[String]
    c <- coffees if c.name is name
  } yield c.sales
  
}