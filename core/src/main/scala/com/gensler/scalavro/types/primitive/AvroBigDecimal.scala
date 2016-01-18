package com.gensler.scalavro.types.primitive

import com.gensler.scalavro.types.AvroPrimitiveType

/**
  * Represents a mapping from scala.BigDecimal to logical Avro type decimal.
  */
trait AvroBigDecimal extends AvroPrimitiveType[BigDecimal] {
  val typeName = "double"
}

object AvroBigDecimal extends AvroBigDecimal
