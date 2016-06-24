package com.gensler.scalavro.types

trait AvroLocalDate extends AvroPrimitiveType[java.time.LocalDate] {
  val typeName = "long"
}

object AvroLocalDate extends AvroLocalDate