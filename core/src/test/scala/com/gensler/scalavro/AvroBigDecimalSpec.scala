package com.gensler.scalavro.test

import scala.collection.mutable
import scala.util.{ Try, Success, Failure }
import scala.reflect.runtime.universe._

import com.gensler.scalavro.types._
import com.gensler.scalavro.types.primitive._

class AvroBigDecimalSpec extends AvroSpec {

  val as = AvroBigDecimal

  "AvroBigDecimal" should "be a subclass of AvroType[AvroBigDecimal]" in {
    as.isInstanceOf[AvroType[AvroBigDecimal]] should be (true)
    typeOf[as.scalaType] =:= typeOf[BigDecimal] should be (true)
  }

  it should "be a primitive AvroType" in {
    as.isPrimitive should be (true)
  }

}