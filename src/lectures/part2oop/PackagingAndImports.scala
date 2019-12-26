package lectures.part2oop

import java.util.Date
import java.sql.{Date => SqlDate}

import playground.{PrinceCharming, Cinderela => Princess}

object PackagingAndImports extends App{

  // package members are acessible by their simple name
  val writer = new Writer("Daniel", "rockTheJVM", 2018)

  val princess = new Princess // payground.Cinderela = fully qualified name

  // package object
  sayHello
  println(SPEED_OF_LIGHT)

  // imports
  val prince = new PrinceCharming

  // 1. use FQN names
  val date = new Date
  val sqlDate = new java.sql.Date(2019,5,4)

  // 2. use aliasing
  val sqlDate2 = new SqlDate(2019,5,4)
}
