package co.s4n.slick.poc.persistence.squeryl

import org.squeryl.adapters.PostgreSqlAdapter
import org.squeryl.{ SessionFactory , Session }
import java.sql.DriverManager
import com.mchange.v2.c3p0.ComboPooledDataSource


abstract class DAO {
  import co.s4n.slick.poc.constants.persistence._
  
  Class.forName(driver)
  
  def createSession() {
  	val cpds = new ComboPooledDataSource 
	cpds.setDriverClass(driver) 
	cpds.setJdbcUrl(url) 
	cpds.setUser(user) 
	cpds.setPassword(password)
	cpds.setInitialPoolSize(3)
	cpds.setMaxPoolSize(200)
	org.squeryl.SessionFactory.concreteFactory =
	 Some(() => Session.create( 
	   cpds.getConnection, new PostgreSqlAdapter))
  }

}