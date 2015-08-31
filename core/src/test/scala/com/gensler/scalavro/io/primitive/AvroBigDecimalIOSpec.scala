package com.gensler.scalavro.io.primitive.test

import scala.util.{ Try, Success, Failure }
import scala.reflect.runtime.universe._

import org.scalatest.FlatSpec
import org.scalatest.Matchers

import com.gensler.scalavro.types._
import com.gensler.scalavro.types.primitive._
import com.gensler.scalavro.io.primitive._
import com.gensler.scalavro.error._

import com.gensler.scalavro.io.AvroTypeIO

import java.io.{ ByteArrayInputStream, ByteArrayOutputStream }

class AvroBigDecimalIOSpec extends FlatSpec with Matchers {

  val io = AvroBigDecimalIO

  "AvroBigDecimalIO" should "be the AvroTypeIO for AvroBigDecimal" in {
    val avroTypeIO: AvroTypeIO[_] = AvroBigDecimal.io
    avroTypeIO should be (io)
  }

  it should "read and write BigDecimals" in {
    val out = new ByteArrayOutputStream

    io.write(BigDecimal(5), out)
    io.write(BigDecimal(100000000000000000000000000000.01), out)

    val bytes = out.toByteArray
    val in = new ByteArrayInputStream(bytes)

    io read in should equal (Success(BigDecimal(5)))
    io read in should equal (Success(BigDecimal(100000000000000000000000000000.01)))
  }

  it should "read and write BigDecimals as JSON" in {
    val json = io writeJson BigDecimal(5)
    io readJson json should equal (Success(BigDecimal(5)))
  }
}