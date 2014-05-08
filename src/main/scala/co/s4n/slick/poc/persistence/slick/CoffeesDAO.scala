package co.s4n.slick.poc.persistence.slick

import slick.driver.PostgresDriver.simple._
import slick.driver.PostgresDriver.backend.{ Session , DatabaseDef }
import scala.slick.lifted.TableQuery
import co.s4n.slick.poc.persistence.slick.Tables.Coffees

class CoffeesDAO(database: DatabaseDef, coffees: TableQuery[Coffees]) extends DAO(database) {
  
  val querySalesByName = for {
    name <- Parameters[String]
    c <- coffees if c.name is name
  } yield c.sales
  
}