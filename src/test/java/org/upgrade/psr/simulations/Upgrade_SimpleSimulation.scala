package org.upgrade.psr.simulations

import io.gatling.core.Predef._
import org.upgrade.psr.helpers.Constants
import org.upgrade.psr.scenarios.{CreateNewUpgradeUser, GetUpgradeUserById}

import scala.concurrent.duration._

class Upgrade_SimpleSimulation extends Simulation{

  setUp(
    CreateNewUpgradeUser.createUpgradeUserDataLoad.inject(rampUsers(10) during (10 seconds))
        .andThen(
          CreateNewUpgradeUser.createUpgradeUser.inject(rampUsers(1) during (10 seconds)),
          GetUpgradeUserById.getUpgradeUserById.inject(rampUsers(2) during (10 seconds))
        )

  )
    .protocols(Constants.httpProtocol)
    .assertions(global.responseTime.percentile(95).lt(3000))
    .assertions(global.successfulRequests.percent.gt(95))
    .maxDuration(1 minutes)
}
