package lectures.part3fp

object TuplesAndMaps extends App{

  // tuples - finite ordered "lists"
  val aTuple = (2, "Hello, Scala") // new Tuple2(2, "Hello, Scala") = (2, "Hello, Scala")

  println(aTuple._1) // 2
  println(aTuple.copy(_2 = "Goodbye, Java"))
  println(aTuple.swap) // ("Hello, Scala", 2)

  // maps - keys -> values
  val aMap: Map[String, Int] = Map()

  val phonebook = Map(("Jim", 555), "Daniel" -> 789)

  println(phonebook)

}
