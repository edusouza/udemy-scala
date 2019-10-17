package lectures.part2oop

object OOBasics extends App {

//  val person = new Person("Eduardo", 37)
//
//  println(person.x)
//  person.greet("Daniel")
//  person.greet()


  val writer = new Writer("Eduardo", "Souza", 1983)


//  println(writer.fullname())

  val novel = new Novel("O Sorriso do Lagarto", 2013, writer)

  println(novel.authorAge)
  println(novel.isWrittenBy(writer))

  val newEdition = novel.copy(2019)

  println(newEdition.authorAge())
  println(newEdition.isWrittenBy(writer))

  val counter = new Counter(5)

  println(counter.inc(4).current )// 9
  println(counter.dec(3).current) // 2
  println(counter.inc().current) // 6
  println(counter.dec().current) // 4
}


//Constructor
class Person(name: String, val age: Int = 0) {

  val x = 2 // x is a field

  println(1+3)

  // method
  def greet(name: String) : Unit = println(s"${this.name} says: Hi $name")

  //overloading
  def greet(): Unit = println(s"Hi, I am $name")

  // multiple constructor
  def this(name: String) = this(name, 0)
  def this() = this("Eduardo")



}

/*
  Novel and a Writer

  Writer: first name, surname, year
    - method fullname

  Novel: name, year of release, author
    - method authorAge
    - isWrittenBy(author)
    - copy(new year of release) = new instance of Novel
 */

/*
  Counter Class
    - receives an int value
    - method current count
    - method to increment/decrement => new Counter
    - overload inc/dec to receive a amount
 */

class Writer(firstName: String, surname: String, val year: Int) {

  def fullname(): String = firstName + " " + surname
}

class Novel(name: String, yearRelease: Int, author: Writer) {

  def authorAge(): Int = yearRelease - author.year

  def isWrittenBy(author: Writer) = author == this.author

  def copy(year: Int): Novel = new Novel(name, year, author)
}

class Counter(seed: Int = 0) {

  def current = seed

  def inc(): Counter = inc(1)

  def dec(): Counter = dec(1)

  def inc(amount: Int): Counter = new Counter(seed + amount)

  def dec(amount: Int): Counter = new Counter(seed - amount)
}