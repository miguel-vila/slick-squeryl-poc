package co.s4n.slick.poc.persistence.squeryl

import org.squeryl._
import java.sql.DriverManager

class SuppliersDAO extends DAO {
  import org.squeryl.PrimitiveTypeMode._
  
  val suppliers = Tables.suppliers
  
  def updateStreetByName(name: String, street: String) = {
    transaction { 
      update(suppliers) {
        s => where(s.name === name) set(s.street := street)
      }
    }
  }
  
}