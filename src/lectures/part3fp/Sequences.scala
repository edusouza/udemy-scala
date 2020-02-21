package lectures.part3fp

import scala.util.Random

object Sequences extends App {

  // Sequences
  val aSequence = Seq(1,2,3,4)
  println(aSequence)
  println(aSequence.reverse)
  println(aSequence(2))
  println(aSequence ++ Seq(5,6,7))
  println(aSequence.sorted)

  // ranges
  val aRange: Seq[Int] = 1 until 10
  aRange.foreach(println)

  // Lists
  val aList = List(1,2,3)
  val prepended = 42 :: aList
  println(prepended)

  val apples5 = List.fill(5)("apples")
  println(apples5)
  println(aList.mkString("-|-"))

  // arrays
  val numbers = Array(1,2,3,4)
  val threeElements = Array.ofDim[Int](3)
  threeElements.foreach(println)

  // mutation
  numbers(2) = 0 // syntax sugar for numbers.update(2,0)
  println(numbers.mkString(" "))

  // arrays and seq
  val numbersSeq: Seq[Int] = numbers
  println(numbersSeq)

  // vector
  val vector: Vector[Int] = Vector(1,2,3,4)
  println(vector)

  // vector vs lists
  val maxRuns = 1000
  val maxCapacity = 1000000

  def getWriteTime(collection: Seq[Int]): Double = {

    val r = new Random
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity), r.nextInt())
      System.nanoTime() - currentTime
    }

    times.sum * 1.0 / maxRuns
  }

  val numberList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  println(getWriteTime(numberList))
  println(getWriteTime(numbersVector))
}
