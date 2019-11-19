package lectures.part2oop

object MethodNotations extends App{

  class Person(val name: String, favoriteMovie: String, val age: Int = 0) {
    def like(movie: String): Boolean = movie == favoriteMovie
    def +(person: Person): String = s"${this.name} is hanging out with ${person.name}"
    def +(nickname: String): Person = new Person(s"${this.name} ($nickname)", this.favoriteMovie)

    def unary_! : String = s"${this.name}, what the heck?!"
    def unary_+ : Person = new Person(this.name, this.favoriteMovie, this.age + 1)
    def isAlive: Boolean = true
    def apply(): String = s"Hi, my name is $name and I like $favoriteMovie"

    def learns(skill: String) = s"${this.name} learns $skill"
    def learnsScala = this learns "Scala"
    def apply(n: Int): String = s"${this.name} watched ${this.favoriteMovie} $n times"
  }

  val mary = new Person("Mary", "Inception")

//  println(mary.like("Inception"))
  println(mary like "Inception")

  //operators
  val tom = new Person("Tom", "Fight club")
  println(mary + tom)
  println(mary.+(tom))

  println(1 + 2)
  println(1.+(2))

  // ALL OPERATORS ARE METHODS

  // prefix notation
  val x = -1 // equivalent with 1.unary_-
  val y = 1.unary_-

  println(!mary)
  println(mary.unary_!)

  // Postfix notation
  println(mary.isAlive)
  println(mary isAlive)

  // apply
  println(mary.apply())
  println(mary()) // equivalent

  // Exercises
  println((mary + "the rockstar").apply())

  println((+mary).age)


  println(mary learnsScala)

  println(mary.apply(2))
  println(mary(2))
}
