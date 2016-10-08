package lookup.formatting

/**
  * from: https://www.scala-exercises.org/std_lib/formatting
  */
object Formatting {

  def main(args: Array[String]): Unit = {

    /**
      * String can be placed in format
      */
    val s = "Hello World"
    println("Application %s".format(s))
    // RESULT: Application Hello World

    /**
      * Character Literals can be a single character
      */
    val a = 'a'
    val b = 'B'
    println("%c".format(a))
    // RESULT: a
    println("%c".format(b))
    // RESULT: B

    /**
      * Character Literals can be an escape sequence, including octal or hexidecimal
      */
    val c = '\u0061' //unicode for a
    val d = '\141' //octal for a
    val e = '\"'
    val f = '\\'

    println("%c".format(c))
    // RESULT: a
    println("%c".format(d))
    // RESULT: a
    println("%c".format(e))
    // RESULT: "
    println("%c".format(f))
    // RESULT: \

    /**
      * Formatting can also include numbers
      */
    val j = 190
    val k = "vodka"
    println("%d bottles of %s on the wall".format(j - 100, k))
    // RESULT: 90 bottles of vodka on the wall

  }
}
