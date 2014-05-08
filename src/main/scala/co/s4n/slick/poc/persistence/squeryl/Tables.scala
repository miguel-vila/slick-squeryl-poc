package co.s4n.slick.poc.persistence.squeryl

import org.squeryl.PrimitiveTypeMode._
import org.squeryl.Schema
import org.squeryl.KeyedEntity
import org.squeryl.annotations.Column

case class Supplier(
    @Column("SUP_ID")
    id: Long,
    @Column("SUP_NAME")
    name: String,
    @Column("STREET")
    street: String,
    @Column("CITY")
    city: String,
    @Column("STATE")
    state: String,
    @Column("ZIP")
    zip: String
    ) extends KeyedEntity[Long] with Optimistic

object Tables extends Schema {

  val suppliers = table[Supplier]("public.SUPPLIERS")
  
}