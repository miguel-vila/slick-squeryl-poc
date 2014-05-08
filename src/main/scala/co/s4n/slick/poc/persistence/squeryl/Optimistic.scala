package co.s4n.slick.poc.persistence.squeryl

import org.squeryl.KeyedEntity
import org.squeryl.annotations.Column

trait Optimistic {
  self: KeyedEntity[_] =>
    
    @Column("version")
    protected val occVersionNumber = 0

}