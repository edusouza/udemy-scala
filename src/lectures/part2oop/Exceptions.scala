package lectures.part2oop

object Exceptions extends App {

  val x: String = null
//  println(x.length)

  // 1. throwing exceptions
//  val aWeirdValue: String = throw new NullPointerException

  //throwable classes extends the Throwable class
  // Exception and Error are the major Throwable subtypes

  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("No int for you!")
    else 42

  try {
    // code that might throw
    getInt(true)
  } catch {
    case e: RuntimeException => println("caught a Runtime exception")
  } finally {
    // code that will get executed NO MATTER WHAT
    println("finally")
  }

  val potentialfail = try {
    // code that might throw
    getInt(true)
  } catch {
    case e: RuntimeException => 43
  } finally {
    // code that will get executed NO MATTER WHAT
    println("finally")
  }

  println(potentialfail)

  class MyException extends Exception

  val exception = new MyException

//  throw exception

  // OOM
//  val array = Array.ofDim(Int.MaxValue)

  // SO
//  def infinite: Int = 1 + infinite
//  val noLimit = infinite

  class OverflowException extends RuntimeException
  class UnderflowException extends RuntimeException
  class MathCalculationException extends RuntimeException("Divide By Zero")

  object PocketCalculator {
    def add(x: Int, y: Int) = {
      val result = x + y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result > 0) throw new UnderflowException
      else result
    }

    def subtract(x: Int, y: Int) = {
      val result = x - y
      if (x > 0 && y < 0 && result < 0) throw new OverflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def multiply(x: Int, y: Int) = {
      val result = x * y
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result < 0) throw new OverflowException
      else if (x > 0 && y < 0 && result > 0) throw new UnderflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }

    def divide(x: Int, y: Int) = {
      if (y == 0) throw new MathCalculationException
      else x / y
    }
  }

//  println(PocketCalculator.add(Int.MaxValue, 10))
//  println(PocketCalculator.add(Int.MinValue,-10))
//  println(PocketCalculator.subtract(10, Int.MinValue))
//  println(PocketCalculator.subtract(Int.MinValue, 10))
//  println(PocketCalculator.multiply(10, Int.MaxValue))
//  println(PocketCalculator.multiply(-10, Int.MinValue))
//  println(PocketCalculator.multiply(10, Int.MinValue))
  println(PocketCalculator.multiply(-10, Int.MaxValue))
//  println(PocketCalculator.divide(2,0))
}
