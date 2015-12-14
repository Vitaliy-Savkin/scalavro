package com.gensler.scalavro.test

import com.gensler.scalavro.types.AvroType
import com.jayway.jsonpath.JsonPath

class AvroSealedTraitEnumSpec extends AvroSpec {

  it should "generate self-contained schema for record" in {
    val colorType = AvroType[Color]
    println(colorType.schema)
    println(colorType.selfContainedSchema().toString())
    val json = JsonPath.parse(colorType.selfContainedSchema().toString())
    json.read[String]("$.type") should be ("enum")
    json.read[String]("$.name") should be ("Color")
  }

  it should "generate self-contained schema for record with enum field" in {
    val carType = AvroType[Car]
    val json = JsonPath.parse(carType.selfContainedSchema().toString())
    json.read[String]("$.name") should be ("com.gensler.scalavro.test.Car")
  }
}
