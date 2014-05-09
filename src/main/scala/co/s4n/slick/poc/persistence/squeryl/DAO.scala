package co.s4n.slick.poc.persistence.squeryl

import org.squeryl.adapters.PostgreSqlAdapter
import org.squeryl.{ SessionFactory , Session }
import java.sql.DriverManager


abstract class DAO {
  import co.s4n.slick.poc.constants.persistence._
  
  Class.forName(driver)
  
  SessionFactory.concreteFactory = Some { () =>
    Session.create(DriverManager.getConnection(url, user, password), new PostgreSqlAdapter())
  }

}