package exercises

abstract class MyList[+A] {

  /*
    head = first element of the list
    tail - remainder of the list
    isEmpty - is this list empty
    add(int) => new list with this element added
    toString => a string representation of the list
   */

  def head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]
  def printElements: String
  override def toString: String = "[" + printElements + "]"

  def map[B](transformer: A => B): MyList[B]
  def flatMap[B](transformer: A => MyList[B]): MyList[B]
  def filter(predicate: A => Boolean): MyList[A]

  def ++[B >: A] (list: MyList[B]):  MyList[B]

  // hofs
  def foreach(f: A => Unit): Unit
  def sort(compare: (A, A) => Int): MyList[A]
  def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C]
  def fold[B](start: B)(operator: (B, A) => B): B
}

case object Empty extends MyList[Nothing] {
  def head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)
  override def printElements: String = " "

  def map[B](transformer: Nothing => B): MyList[B] = Empty
  def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty
  def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty

  def ++[B >: Nothing](list: MyList[B]): MyList[B] = list

  //hofs
  def foreach(f: Nothing => Unit): Unit = ()

  def sort(compare: (Nothing, Nothing) => Int): MyList[Nothing] = Empty

  def zipWith[B, C](list: MyList[B], zip: (Nothing, B) => C): MyList[C] = {
    if (!list.isEmpty) throw new RuntimeException("Lists do not have same length")
    else Empty
  }

  def fold[B](start: B)(operator: (B, Nothing) => B): B = start
}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  def head: A = h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyList[B] = new Cons(element, this)

  override def printElements: String = {
    if (t.isEmpty) "" + head
    else head + " " + tail.printElements
  }

  def map[B](transformer: A => B): MyList[B] =
    new Cons(transformer(h), t.map(transformer))

  def flatMap[B](transformer: A => MyList[B]): MyList[B] =
    transformer(h) ++ t.flatMap(transformer)

  def filter(predicate: A => Boolean): MyList[A] =
    if (predicate(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)

  def ++[B >: A] (list: MyList[B]):  MyList[B] = new Cons(h, t ++ list)

  def foreach(f: A => Unit): Unit = {
    f(h)
    t.foreach(f)
  }

  def sort(compare: (A, A) => Int): MyList[A] = {

    def insert(x: A, sorteList: MyList[A]): MyList[A] = {
      if (sorteList.isEmpty) new Cons(x, Empty)
      else if (compare(x, sorteList.head) <= 0) new Cons(x, sorteList)
      else new Cons(sorteList.head, insert(x, sorteList.tail))
    }

    val sortedTail = t.sort(compare)
    insert(h, sortedTail)

  }

  def zipWith[B, C](list: MyList[B], zip: (A, B) => C): MyList[C] = {
    if (list.isEmpty) throw new RuntimeException("Lists do not have the same length")
    else new Cons(zip(h, list.head), t.zipWith(list.tail, zip))
  }

  def fold[B](start: B)(operator: (B, A) => B): B =
    t.fold(operator(start, h))(operator)
}

object ListTest extends App {
  val list: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val cloneList = new Cons(1, new Cons(2, new Cons(3, Empty)))
  val anotherList: MyList[Int] = new Cons(4, new Cons(5, Empty))
  val listOfStrings: MyList[String] = new Cons("Hello", new Cons("Scala", Empty))

//  println(list.tail.head)
//  println(list.add(4).head)
//  println(list.isEmpty)
  println(list.toString)

  println(list.map(_ * 2).toString)

  println(list.filter(_ % 2 == 0).toString)

  println(list ++ anotherList)

  println(list.flatMap((elem: Int) => new Cons(elem, new Cons(elem + 1, Empty))).toString)

  println(list == cloneList)

  list.foreach(println)

  println(list.sort((x, y) => y - x))

  println(anotherList.zipWith[String, String](listOfStrings, _ + "-" + _))

  println(list.fold(0)(_ + _))

  println(listOfStrings.fold("")(_+_))

}