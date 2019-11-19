package playground

object ScalaPlayground extends App {

  println("hello Scala")

  // SCALA  DOES NOT HAVE CLASS-LEVEL FUNCIONALITY ("static")
  object Person { //type + its only instance

    // static / class level funcionality
    val N_EYES = 2
    def canFly(): Boolean = false

    def apply(mother: Person, father: Person): Person = new Person("Bobbie")
  }

  class Person(name: String) {
    // instance level functionality
  }
  println(Person.N_EYES)
  println(Person.canFly())

  val mary = new Person("Mary")
  val john = new Person("John")
  println(mary == john)

  val bobbie = Person(mary, john)
  // scala object = singleton instance
}
