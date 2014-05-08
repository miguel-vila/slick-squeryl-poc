package co.s4n.slick.poc.persistence

import slick.driver.PostgresDriver.simple._
import slick.driver.PostgresDriver.backend.{ Session , DatabaseDef }
import scala.slick.lifted.TableQuery
import co.s4n.slick.poc.persistence.Tables.Coffees

class CoffeesDAO(database: DatabaseDef, coffees: TableQuery[Coffees]) extends DAO(database) {
  
  val queryByName = for {
    name <- Parameters[String]
    u <- coffees if u.name is name
  } yield u.sales
  
}