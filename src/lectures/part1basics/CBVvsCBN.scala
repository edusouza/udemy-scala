package lectures.part1basics

object CBVvsCBN extends App{

  def callByValue(n: Long): Unit = {
    println("by value: " + n)
    println("by value: " + n)
  }

  def callByName(n: => Long): Unit = {
    println("by name: " + n)
    println("by name: " + n)
  }

  callByValue(System.nanoTime())
  callByName(System.nanoTime())

}
