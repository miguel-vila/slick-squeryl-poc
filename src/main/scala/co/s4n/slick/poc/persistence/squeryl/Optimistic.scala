package co.s4n.slick.poc.persistence.squeryl

import org.squeryl.KeyedEntity
import org.squeryl.annotations.Column
import org.squeryl.Optimistic

trait SuppliersOptimistic extends Optimistic {
  self: KeyedEntity[_] =>
    
    @Column("version")
    override val occVersionNumber = 0

}