package org.upgrade.psr.steps

import io.gatling.core.Predef._
import io.gatling.core.structure.ChainBuilder
import io.gatling.http.Predef._
import org.upgrade.psr.helpers.Json.toJsonString
import org.upgrade.psr.helpers.UserHelper.createUserDto

object User {

  val create: ChainBuilder =
    exec{ session =>
      val createUser = toJsonString(
        createUserDto(
          session("firstName").as[String],
          session("lastName").as[String],
          session("email").as[String],
        )
      )
      session.set("create_user", createUser)
    }
      .exec(doCreate("id"))

  def doCreate(outputKey: String): ChainBuilder =
    exec(
      http("CreateUser")
        .post("/user/v1/create")
        .body(StringBody("${create_user}"))
        .check(jsonPath("$.id").exists.saveAs(outputKey))
        .check(status.is(201))
    )

  val getUser: ChainBuilder =
    exec(
      http("Get User by Id")
        .get("/user/v1/find/${id}")
        .check(status is 200)
    )

}
