package lookup.infix_prefix_postfix_operators

/**
  * from: https://www.scala-exercises.org/std_lib/infix_prefix_and_postfix_operators
  */
object InfixPrefixPostfixOperators {

  def main(args: Array[String]): Unit = {

    /**
      * Any method which takes a single parameter can be used as an infix operator: a.m(b) can be written a m b
      */

    val g: Int = 3
    println(g + 4) // + is an infix operator
    // RESULT: 7
    println(g.+ (4))
    // RESULT: 7

    /**
      * Infix Operators do NOT work if an object has a method that takes two parameters
      */

    val g1: String = "Check out the big brains on Brad!"
    println(g1 indexOf 'o')
    // RESULT: 6
    // g1 indexOf 'o', 4 should be (6) //indexOf(Char, Int) cannot be used as an infix operator
    println(g1.indexOf('o', 7))
    // RESULT: 25

    /**
      * Any method which does not require a parameter can be used as a postfix operator: a.m can be written a m.
      * For instance a.##(b) can be written a ## b and a.! can be written a!

      **Postfix operators** have lower precedence than **infix operators**, so: - foo bar baz means
      * foo.bar(baz). - foo bar baz bam means (foo.bar(baz)).bam - foo bar baz bam bim means (foo.bar(baz)).bam(bim).
      */

    val g2: Int = 31
    println(g2 toHexString)
    // RESULT: 1f

    /**
      * Prefix operators work if an object has a method name that starts with unary_
      */

    val g3 : Int = 31
    println(-g3)
    // RESULT: -31

    /**
      * Here we create our own prefix operator for our own class. The only identifiers that can be used as
      * prefix operators are +, -, !, and ~
      */

    class Stereo {
      def unary_+ = "on"
      def unary_- = "off"
    }

    val stereo = new Stereo
    println(+stereo)
    // RESULT: on
    println(-stereo)
    // RESULT: off

  }
}
