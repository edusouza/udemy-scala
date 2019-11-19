package exercises

abstract class MyList {

  /*
    head = first element of the list
    tail - remainder of the list
    isEmpty - is this list empty
    add(int) => new list with this element added
    toString => a string representation of the list
   */

  def head: Int
  def tail: MyList
  def isEmpty: Boolean
  def add(n: Int): MyList
  def printElements: String
  override def toString: String = "[" + printElements + "]"
}

object Empty extends MyList {
  def head: Int = throw new NoSuchElementException
  def tail: MyList = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add(n: Int): MyList = new Cons(n, Empty)
  override def printElements: String = " "
}

class Cons(h: Int, t: MyList) extends MyList {
  def head: Int = h
  def tail: MyList = t
  def isEmpty: Boolean = false
  def add(n: Int): MyList = new Cons(n, this)

  override def printElements: String = {
    if (t.isEmpty) "" + head
    else head + " " + tail.printElements
  }
}

object ListTest extends App {
  val list = new Cons(1, new Cons(2, new Cons(3, Empty)))

  println(list.tail.head)
  println(list.add(4).head)
  println(list.isEmpty)
  println(list.toString)
}