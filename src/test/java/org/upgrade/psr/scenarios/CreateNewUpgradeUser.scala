package org.upgrade.psr.scenarios

import io.gatling.core.Predef._
import io.gatling.http.Predef.{flushCookieJar, flushHttpCache, flushSessionCookies}
import org.upgrade.psr.feeders.Feeder
import org.upgrade.psr.steps.User

import scala.concurrent.duration._

object CreateNewUpgradeUser {

  val createUpgradeUser =
    scenario("Create Upgrade User")
      .forever() {
        exec(flushSessionCookies).exec(flushCookieJar).exec(flushHttpCache)
        exec(
          feed(Feeder.dynamicEmailFeed)
            .feed(Feeder.dynamicFirstNameFeed)
            .feed(Feeder.dynamicLastNameFeed)
              .exec(
                User.create
              )
      )
        .pause(1 seconds)
    }

  val createUpgradeUserDataLoad =
    scenario("Data Creation")
      .repeat(50) {
        exec(flushSessionCookies).exec(flushCookieJar).exec(flushHttpCache)
        exec(
          feed(Feeder.dynamicEmailFeed)
            .feed(Feeder.dynamicFirstNameFeed)
            .feed(Feeder.dynamicLastNameFeed)
            .exec(
              User.create
            )
        )
      }

}
