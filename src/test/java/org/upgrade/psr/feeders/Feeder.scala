package org.upgrade.psr.feeders

import io.gatling.jdbc.Predef.jdbcFeeder
import io.gatling.core.Predef._
import org.upgrade.psr.helpers.TestProperties

import scala.util.Random

object Feeder {
  val dbUrl = TestProperties.getAsString("spring.datasource.url")
  val dbUsername = TestProperties.getAsString("spring.datasource.username")
  val dbPassword = TestProperties.getAsString("spring.datasource.password")

  val usersQuery = "SELECT id FROM upgrade_user"

  val dynamicUserFeed = jdbcFeeder(dbUrl,dbUsername,dbPassword,usersQuery).circular.queue
  val dynamicEmailFeed = Iterator.continually(Map("email" -> (Random.alphanumeric.take(20).mkString + "@foo.com")))
  val dynamicFirstNameFeed = Iterator.continually(Map("firstName" -> (Random.alphanumeric.take(20).mkString)))
  val dynamicLastNameFeed = Iterator.continually(Map("lastName" -> (Random.alphanumeric.take(20).mkString)))

}
