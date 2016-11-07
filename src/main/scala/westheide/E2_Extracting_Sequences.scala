package westheide

/**
  * from: http://danielwestheide.com/blog/2012/11/28/the-neophytes-guide-to-scala-part-2-extracting-sequences.html
  */
object E2_Extracting_Sequences {

  def main(args: Array[String]): Unit = {

    // You can use a pattern that only matches a list of exactly two elemens, or a list of exactly three elements:

    val xs = 3 :: 6 :: 12 :: Nil

    def listMatch(xs: List[Int]): Unit = xs match {
      case List(a, b) => println(a * b)
      case List(a, b, c) => println(a + b + c)
      case _ => println(0)
    }
    listMatch(xs)
    // RESULT: 21

    // What’s more, if you want to match lists the exact length of which you don’t care about, you can use a
    // wildcard operator, _*:

    val xs2 = 3 :: 6 :: 12 :: 24 :: Nil

    def listMatch2(xs: List[Int]): Unit = xs match {
      case List(a, b, _*) => println(a * b)
      case _ => println(0)
    }

    listMatch2(xs2)
    // RESULT: 18

    // Extracting given names
    // Here is a very simple extractor implementation by means of the unapplySeq method that will allow us to do that:

    object GivenNames {
      def unapplySeq(name: String): Option[Seq[String]] = {
        val names = name.trim.split(" ")
        if (names.forall(_.isEmpty)) None else Some(names)
      }
    }

    def greetWithFirstName(name: String) = name match {
      case GivenNames(firstName, _*) => println("Good morning, " + firstName + "!")
      case _ => println("Welcome! Please make sure to fill in your name!")
    }

    greetWithFirstName("Daniel")
    // RESULT: Good morning, Daniel!
    greetWithFirstName("Catherina Johanna")
    // RESULT: Good morning, Catherina!
    greetWithFirstName("Matthew John Michael")
    // RESULT: Good morning, Matthew!

    // Combining fixed and variable parameter extraction

    object Names {
      def unapplySeq(name: String): Option[(String, String, Array[String])] = {
        val names = name.trim.split(" ")
        if (names.size < 2) None
        else Some((names.last, names.head, names.drop(1).dropRight(1)))
      }
    }

    def greet(fullName: String) = fullName match {
      case Names(lastName, firstName, _*) => println("Good morning, " + firstName + " " + lastName + "!")
      case _ => println("Welcome! Please make sure to fill in your name!")
    }
    greet("John Doe")
    // RESULT: Good morning, John Doe!
    greet("Catherina Johanna Peterson")
    // RESULT: Good morning, Catherina Peterson!

    def greet2(fullName: String) = fullName match {
      case Names(lastName, firstName, _*) => println("Good morning, " + firstName + " " + lastName + "!")
      case _ => println("Welcome! Please make sure to fill in your name!")
    }

    greet2("John Doe")
    // RESULT: Good morning, John Doe!
    greet2("Catherina Johanna Peterson")
    // RESULT: Good morning, Catherina Peterson!

  }
}
