name := "slibpg-bug"

organization := "io.simao"

scalaVersion := "2.11.7"

scalacOptions ++= Seq("-Xmax-classfile-name", "140")

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.5",
  "org.postgresql" % "postgresql" % "9.4-1203-jdbc42",
  "com.zaxxer" % "HikariCP" % "2.3.12",
  "com.typesafe" % "config" % "1.3.0",
  "com.typesafe.slick" %% "slick" % "3.0.3",
  "com.github.tminglei" %% "slick-pg" % "0.9.2",
  "org.json4s" %% "json4s-jackson" % "3.2.10",
//  "com.typesafe.slick" %% "slick" % "3.1.0",
//  "com.github.tminglei" %% "slick-pg" % "0.10.0",
//  "com.github.tminglei" %% "slick-pg_joda-time" % "0.10.0",
  "org.scalatest" %% "scalatest" % "2.2.5" % "test",
  "joda-time" % "joda-time" % "2.7",
  "org.joda" % "joda-convert" % "1.7",
  "com.vividsolutions" % "jts" % "1.13"
)

