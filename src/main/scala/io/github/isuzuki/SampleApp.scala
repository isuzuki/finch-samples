package io.github.isuzuki

import com.twitter.finagle.{Http, Service}
import com.twitter.finagle.http.{Request, Response}
import com.twitter.util.Await
import io.finch._
import io.finch.circe._
import io.finch.syntax._
import io.finch.Endpoint

object SampleApp extends App {
  Await.ready(Http.serve(":8080", Router.service))
}

object Router {
  val hello: Endpoint[String] = get("hello") {
    Ok("hello finch.")
  }

  val service: Service[Request, Response] = hello.handle {
    case e: Exception => {
      e.printStackTrace()
      BadRequest(e)
    }
  }.toService
}
