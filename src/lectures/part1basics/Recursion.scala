package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {

  def factorial(n: Int): Int = {
    if (n <= 1) 1
    else n * factorial(n - 1)
  }

  def concatenate(n: Int, aString: String): String = {
    def concatHelper(t: Int, concat: String, anotherString: String): String = {
      if (t <= 1) concat + anotherString
      else concatHelper(t - 1, concat + anotherString, anotherString)
    }

    concatHelper(n, "", aString)
  }

  def isPrime(n: Int): Boolean = {
    @tailrec
    def isPrimeHelper(t: Int, isStillPrime: Boolean): Boolean = {
      if (!isStillPrime) false
      else if (t <= 1) true
      else isPrimeHelper(t-1, n % t != 0 && isStillPrime)
    }

    isPrimeHelper(n/2, true)
  }

  println(concatenate(3, "hello"))
  println(isPrime(17 * 37))

}