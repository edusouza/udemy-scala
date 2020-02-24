package lectures.part3fp

import java.util.Random

import scala.util.{Failure, Success, Try}

object HandlingFailure extends App {

  val aSuccess = Success(3)
  val aFailure = Failure(new RuntimeException("SUPER FAILURE"))

  println(aSuccess)
  println(aFailure)

  def unsafeMethod(): String = throw new RuntimeException("NO STRING FOR YOU BUSTER")

  // Try objects via apply method
  val potentialFailure = Try(unsafeMethod())
  println(potentialFailure)

  //syntax sugar
  val anotherPotentialFailure = Try {
    // code that might throw
  }

  println(potentialFailure.isSuccess)

  def backupMethod(): String = "A valid result"
  val fallbackTry = Try(unsafeMethod()).orElse(Try(backupMethod()))
  println(fallbackTry)

  // If you design the API
  def betterUnsafeMethod(): Try[String] = Failure(new RuntimeException)
  def betterBackupMethod(): Try[String] = Success("A valid result")
  val betterFallbackTry = betterUnsafeMethod() orElse betterBackupMethod()
  println(betterFallbackTry)

  // if your code might return nulls, use Option. If your code might throw Exceptions, use Try

  println(aSuccess.map(_ * 2))
  println(aSuccess.flatMap(x => Success(x * 10)))
  println(aSuccess.filter(_ > 10))

  // for comprehensions

  /*
    Exercises
   */
  val hostname = "localhost"
  val port = "8080"

  def renderhtml(page: String) = println(page)

  class Connection {
    def get(url: String): String = {
      val random = new Random(System.nanoTime())
      if (random.nextBoolean()) "<html>...</html>"
      else throw new RuntimeException("Connection interrupted")
    }
  }

  object HttpService {
    def getConnection(host: String, port: String): Connection = {
      val random = new Random(System.nanoTime())
      if (random.nextBoolean()) new Connection
      else throw new RuntimeException("Someone else took the port")
    }
  }

//  val safeHostname = Try(hostname)
//  val safePort = Try(port)
//  val connection = safeHostname.flatMap(h => safePort.flatMap(p => Try(HttpService.getConnection(h,p))))
//  val page = connection.flatMap(c => Try(c.get("string")))
//  page.foreach(renderhtml)
//
//  Try(hostname)
//    .flatMap(h => Try(port)
//      .flatMap(p => Try(HttpService.getConnection(h, p))))
//    .flatMap(c => Try(c.get("string")))
//      .foreach(renderhtml)

  for {
    h <- Try(hostname)
    p <- Try(port)
    conn <- Try(HttpService.getConnection(h,p))
    html <- Try(conn.get("/host"))
  } renderhtml(html)

}
