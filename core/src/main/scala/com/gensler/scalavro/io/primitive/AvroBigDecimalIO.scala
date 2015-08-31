package com.gensler.scalavro.io.primitive

import com.gensler.scalavro.types.primitive.AvroBigDecimal
import com.gensler.scalavro.error.AvroDeserializationException

import org.apache.avro.io.{ BinaryEncoder, BinaryDecoder }

import spray.json._

import scala.util.Try

object AvroBigDecimalIO extends AvroBigDecimalIO

trait AvroBigDecimalIO extends AvroPrimitiveTypeIO[BigDecimal] {

  override val avroType = AvroBigDecimal

  ////////////////////////////////////////////////////////////////////////////
  // BINARY ENCODING
  ////////////////////////////////////////////////////////////////////////////

  override protected[scalavro] def write(
    value: BigDecimal,
    encoder: BinaryEncoder): Unit = AvroBytesIO.write(value.toBigInt().toByteArray, encoder)

  protected[scalavro] def read(decoder: BinaryDecoder) =
    BigDecimal(BigInt(AvroBytesIO.read(decoder).toArray), 0)

  ////////////////////////////////////////////////////////////////////////////
  // JSON ENCODING
  ////////////////////////////////////////////////////////////////////////////

  def writePrimitiveJson(value: BigDecimal) = JsNumber(value)

  def readJson(json: JsValue) = Try {
    json match {
      case JsNumber(value) => value
      case _               => throw new AvroDeserializationException[BigDecimal]
    }
  }

}