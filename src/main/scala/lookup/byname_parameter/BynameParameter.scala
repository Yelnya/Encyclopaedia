package lookup.byname_parameter

/**
  * from: https://www.scala-exercises.org/std_lib/byname_parameter
  */

object BynameParameter {

  def main(args: Array[String]): Unit = {

    /**
      * () => Int is a Function type that takes a Unit type. Unit is known as void to a Java programmer. The
      * function returns an Int. You can place this as a method parameter so that you can you use it as a
      * block, but still it doesn't look quite right
      */

    def calc(x: () => Int): Either[Throwable, Int] = {
      try {
        Right(x())  //An explicit call the x function
      } catch {
        case b: Throwable => Left(b)
      }
    }

    val y = calc { () =>  // Having explicitly declaring that Unit is a parameter with ()
      14 + 15
    }
    println(y)
    // RESULT: Right(29)

    /**
      * A by-name parameter does the same thing as the previous koan but there is no need to explicitly
      * handle Unit or (). This is used extensively in scala to create blocks
      */

    def calc2(x: => Int): Either[Throwable, Int] = {
      // x is a call by name parameter
      try {
        Right(x)
      } catch {
        case b: Throwable => Left(b)
      }
    }

    val y2 = calc2 {
      // This looks like a natural block
      println("Here we go!")  // Some superfluous call
      val z2 = List(1, 2, 3, 4) // Another superfluous call
      49 + 20
    }
    println(y2)
    // RESULT: Right(69)

    /**
      * By name parameters can also be used with an *Object* and apply to make interesting block-like calls
      */

    object PigLatinizer {
      def apply(x: => String) = x.tail + x.head + "ay"
    }

    val result3 = PigLatinizer {
      val x = "pret"
      val z = "zel"
      x ++ z  //concatenate the strings   => pretzel to PigLatinizer
    }

    println(result3)
    // RESULT: retzelpay

  }
}
