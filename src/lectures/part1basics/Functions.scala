
object Functions extends App {

  def greeting(name: String, age: Int): Unit = {
    println(s"Hi, my name is $name and I am $age years old.")
  }

  def factorial(n: Int): Int = {
    if (n==1) 1
    else n * factorial(n-1)
  }

  def fibonacci(n: Int): Int = {
    if (n == 1 || n == 2) 1
    else fibonacci(n-1) + fibonacci(n-2)
  }

  def isPrime(n: Int): Boolean = {

    def isPrimeUntil (t: Int): Boolean = {
      if (t <= 1) true
      else n % t != 0 && isPrimeUntil(t-1)
    }

    isPrimeUntil(n / 2);
  }

  greeting("Eduardo", 36)

  println(factorial(5))

  println(fibonacci(8))

  println(isPrime(17 * 35))
}

