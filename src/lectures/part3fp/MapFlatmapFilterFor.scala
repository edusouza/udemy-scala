package lectures.part3fp

object MapFlatmapFilterFor extends App {

  val list = List(1,2,3)
  println(list)
  println(list.head)
  println(list.tail)

  // Map
  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))

  // Filter
  println(list.filter(_ % 2 == 0))

  // Flatmap
  val toPair = (x: Int) => List(x, x+1)
  println(list.flatMap(toPair))

  // exercises
  // Print all combinations between tow lists
  val numbers = List(1,2,3,4)
  val chars = List('a', 'b', 'c', 'd')
  val colors = List("black", "white")

  // iterations
  val combinations = numbers.flatMap(n => chars.flatMap(c => colors.map(color =>  "" + c + n + "-" + color)))
  println(combinations)

  numbers.foreach(println)

  // for-comprehensions
  val forCombinations = for {
    n <- numbers if n % 2 == 0
    c <- chars
    color <- colors
  } yield "" + c + n + "-" + color
  println(forCombinations)

  for {
    n <- numbers
  } println(n)

  // syntax overload
  numbers.map { x =>
    x * 2
  }

  /*
    1. MyList supports for comprehensions - YES, it DOES
    2. A small collection of at most ONE element - Maybe[+T]
      - map, flatMap, filter
   */

}
