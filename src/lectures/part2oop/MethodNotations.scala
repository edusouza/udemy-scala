package lectures.part2oop

object MethodNotations extends App{

  class Person(val name: String, favoriteMovie: String) {
    def like(movie: String): Boolean = movie == favoriteMovie
  }

  val mary = new Person("Mary", "Inception")

//  println(mary.like("Inception"))
  println(mary like "Inception")
}
