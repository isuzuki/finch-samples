package io.github.isuzuki

import com.twitter.finagle.{Http, Service}
import com.twitter.finagle.http.{Request, Response}
import com.twitter.util.Await
import io.finch._
// Note: this import is required to build.
import io.finch.circe._
import io.finch.syntax._
import io.finch.Endpoint

object SampleApp extends App {
  Await.ready(Http.serve(":8080", Router.service))
}

object Router {
  val helloGet: Endpoint[String] = get("hello") {
    Ok("hello get finch.")
  }

  val helloPost: Endpoint[String] = post("hello" :: jsonBody[String]) { s: String =>
    Ok(s"hello post finch: $s.")
  }

  val service: Service[Request, Response] =
    (helloGet :+: helloPost)
      .handle {
      case e: Exception => {
        e.printStackTrace()
        BadRequest(e)
      }
    }.toService
}
