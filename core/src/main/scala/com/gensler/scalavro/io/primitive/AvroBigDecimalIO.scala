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
    encoder: BinaryEncoder): Unit = AvroDoubleIO.write(value.toDouble, encoder)

  protected[scalavro] def read(decoder: BinaryDecoder) = AvroDoubleIO.read(decoder)

  ////////////////////////////////////////////////////////////////////////////
  // JSON ENCODING
  ////////////////////////////////////////////////////////////////////////////

  def writePrimitiveJson(value: BigDecimal) = AvroDoubleIO.writePrimitiveJson(value.toDouble)

  def readJson(json: JsValue) = AvroDoubleIO.readJson(json).map(BigDecimal.apply)

}