package lectures.part3fp

import java.util.Random

object Options extends App {

  val myFirstOption: Option[Int] = Some(4)
  val noOption: Option[Int] = None

  println(myFirstOption)

  // WORK with unsafe APIs
  def unsafeMethod(): String = null
//  val result = Some(unsafeMethod()) //WRONG
  val result = Option(unsafeMethod())
  println(result)

  // chained methods
  def backupMethod(): String = "A valid value"
  val chainedResult = Option(unsafeMethod()).orElse(Option(backupMethod()))

  println(chainedResult)

  // Design unsafe APIs
  def betterUnsafeMethod(): Option[String] = None
  def betterbackupMethod(): Option[String] = Some("A valid value")

  val betterChainedResult = betterUnsafeMethod() orElse betterbackupMethod()
  println(betterChainedResult)

  println(myFirstOption.isEmpty)
  println(myFirstOption.get) // UNSAFE - DO NOT USE THIS

  println(myFirstOption.map(_ * 2))
  println(myFirstOption.filter(x => x > 10))
  println(myFirstOption.flatMap(x => Option(x * 10)))

  // for-comprehensions

  /*
      Exercises
   */

  val config: Map[String, String] = Map(
    "host" -> "176.45.36.1",
    "port" -> "80"
  )

  class Connection {
    def connect = "Connected"
  }

  object Connection {
    val random = new Random(System.nanoTime())

    def apply(host: String, port: String): Option[Connection] =
      if (random.nextBoolean()) Some(new Connection)
      else None
  }

  // try to establish a connection, if so - print the connect method
  val host = config.get("host")
  val port = config.get("port")
  /*
    if (h != null)
      if (p != null)
        return Connection(h,p)
    return null
   */
  val connection = host.flatMap(h => port.flatMap(p => Connection(h, p)))
  /*
    if (c != null)
      return c.connect
    return null
   */
  val connnectionStatus = connection.map(c => c.connect)
  /*
    if (connectionstatus == null) println(None) else println(Some(connnectionStatus.get))
   */
  println(connnectionStatus)
  /*
    if (connectionStatus != null)
      println(connectionStatus)
   */
  connnectionStatus.foreach(println)

  // chained calls
  config.get("host")
    .flatMap(host => config.get("port")
      .flatMap(port => Connection(host, port))
    .map(connection => connection.connect))
    .foreach(println)

  val forConnectionStatus = for {
    host <- config.get("host")
    port <- config.get("port")
    connection <- Connection(host, port)
  } yield connection.connect
  forConnectionStatus.foreach(println)

}
