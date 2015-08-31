package com.gensler.scalavro.types.primitive

import com.gensler.scalavro.types.AvroPrimitiveType
import com.gensler.scalavro.JsonSchemaProtocol._
import spray.json._

import scala.collection.immutable.ListMap

/**
  * Represents a mapping from scala.BigDecimal to logical Avro type decimal.
  */
trait AvroBigDecimal extends AvroPrimitiveType[BigDecimal] {

  val typeName = "bytes"

  override def schema(): spray.json.JsValue = new JsObject(ListMap(
    "type" -> typeName.toJson,
    "logicalType" -> JsString("decimal"),
    "precision" -> JsNumber(20)
  ))
}

object AvroBigDecimal extends AvroBigDecimal
