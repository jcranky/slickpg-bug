package io.simao.slickpgbug

import io.simao.slickpgbug.CustomPostgresDriver.api._
import org.json4s.JValue
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Millis, Seconds, Span}
import org.scalatest.{FunSuiteLike, ShouldMatchers}
import slick.lifted.Tag

import scala.concurrent.{ExecutionContext, Future}

class CustomPostgresDriverTest extends FunSuiteLike
with ScalaFutures
with ShouldMatchers
{
  implicit val ec = ExecutionContext.global

  implicit override val patienceConfig =
    PatienceConfig(timeout = Span(3, Seconds), interval = Span(20, Millis))

  case class TestBean(id: Int, details: JValue)

  class TestBeans(tag: Tag) extends Table[TestBean](tag, "test_beans") {
    def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def details = column[JValue]("details")

    def * = (id, details) <> (TestBean.tupled, TestBean.unapply)
  }

  val testBeans = TableQuery[TestBeans]

  def dbSetup(): Future[Unit] = {
    db.run(DBIO.seq(testBeans.schema.drop.asTry, testBeans.schema.create))
  }

  lazy val db: Database = Database.forURL(url = "jdbc:postgresql://localhost/", user = "admin", "admin", driver = "org.postgresql.ds.PGSimpleDataSource")

  test("+>> operator fails") {
    val f = for {
      _ ← dbSetup()
      size ← db.run(testBeans.filter(_.details.+>>("lol") === "lol").result.map(_.size))
    } yield size

    whenReady(f) { size ⇒
      size shouldBe 0
    }
  }
}
