package lookup.literal_strings

/**
  * from: https://www.scala-exercises.org/std_lib/literal_strings
  */
object LiteralStrings {

  def main(args: Array[String]): Unit = {

    /**
      * Character Literals are quoted with single quotes
      */
    val a = 'a'
    val b = 'B'

    println(a.toString)
    // RESULT: a
    println(b.toString)
    // RESULT: B

    /**
      * Character Literals can use hexadecimal Unicode
      */

    val c = '\u0061'

    println(c.toString)
    // RESULT: a

    /**
      * Character Literals can use octal as well
      */

    val d = '\141' //octal for a
    println(d.toString)
    // RESULT: a

    val e = '\"'
    val f = '\\'

    println(e.toString)
    // RESULT: \"
    println(f.toString)
    // RESULT: \\

    /**
      * One-Line String Literals are surrounded by quotation marks.
      */

    val a3 = "To be or not to be"
    println(a3)
    // RESULT: To be or not to be

  }
}
