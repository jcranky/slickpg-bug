package io.simao.slickpgbug

import com.github.tminglei.slickpg._
import org.json4s.JValue
import slick.driver.PostgresDriver

trait CustomPostgresDriver extends PostgresDriver
with PgDateSupportJoda
with PgRangeSupport
with PgJson4sSupport
with PgPostGISSupport {
  def pgjson = "jsonb"

  type DOCType = JValue

  override val api = MyAPI
  override val jsonMethods = org.json4s.jackson.JsonMethods

  object MyAPI extends API
  with DateTimeImplicits
  with JsonImplicits
  with PostGISImplicits
  with RangeImplicits
}

object CustomPostgresDriver extends CustomPostgresDriver

