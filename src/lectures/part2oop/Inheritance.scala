package lectures.part2oop

object Inheritance extends App {

  sealed class Animal {
    val creatureType = "wild"

    def eat() = println("nomnom")
  }

  class Cat extends Animal

  val cat = new Cat()
  cat.eat

  class Dog(override val creatureType: String) extends Animal {
    override def eat: Unit = {
      super.eat
      println("crunch, crunch")
    }
  }

  val dog = new Dog("K9")
  dog.eat
  println(dog.creatureType)

  // type substitution (broad: polymorphism
  val unkwnonAnimal: Animal = new Dog("K9")
  unkwnonAnimal.eat

  // super

  // preventing overriding
  // 1 - use final on member
  //  final def eat = println("nomnom")
  // 2 - use final on the entire class
  // final class Animal
  // 3 - seal the class. extend the classes in THIS file, prevent extension in other files.
  // sealed class Animal
}
