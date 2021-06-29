package org.upgrade.psr.scenarios

import io.gatling.core.Predef._
import io.gatling.http.Predef.{flushCookieJar, flushHttpCache, flushSessionCookies}
import org.upgrade.psr.feeders.Feeder
import org.upgrade.psr.steps.User

object GetUpgradeUserById {

  val getUpgradeUserById =
    scenario("Get Upgrade User By Id")
    .feed(Feeder.dynamicUserFeed)
      .forever() {
        exec(flushSessionCookies).exec(flushCookieJar).exec(flushHttpCache)
        .exec(
          User.getUser
        )
      }

}
