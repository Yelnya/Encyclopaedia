package lookup.literal_boolean

/**
  * from: https://www.scala-exercises.org/std_lib/literal_booleans
  */
object LiteralBoolean {

  def main(args: Array[String]): Unit = {

    /**
      * Boolean literals are either true or false, using the true or false keyword
      */

    val a = true
    println(a)
    // RESULT: true
    val b = false
    println(b)
    // RESULT: false
    val c = 1 > 2
    println(c)
    // RESULT: false
    val d = 1 < 2
    println(d)
    // RESULT: true
    val e = a == c
    println(e)
    // RESULT: false
    val f = b == d
    println(f)
    // RESULT: false

  }
}
