package org.upgrade.psr.helpers

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

object Constants {
  val headers: Map[String, String] = Map(
    "Content-Type" -> "application/json",
  )

  val httpProtocol: HttpProtocolBuilder = http
    .baseUrl(TestProperties.getAsString("host.url"))
    .acceptHeader("application/json; */*")
    .headers(headers)

}
