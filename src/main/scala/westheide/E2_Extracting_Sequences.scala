package westheide

/**
  * from: http://danielwestheide.com/blog/2012/11/28/the-neophytes-guide-to-scala-part-2-extracting-sequences.html
  */
class E2_Extracting_Sequences {

  def main(args: Array[String]): Unit = {

  // You can use a pattern that only matches a list of exactly two elemens, or a list of exactly three elements:

    val xs = 3 :: 6 :: 12 :: Nil
    xs match {
      case List(a, b) => a * b
      case List(a, b, c) => a + b + c
      case _ => 0
    }

  }

}
