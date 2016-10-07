package lookup.asserts


// from https://www.scala-exercises.org/std_lib/asserts

object Asserts {

  def main(args: Array[String]): Unit = {

    /*
    ScalaTest makes three assertions available by default in any style trait. You can use:

    assert for general assertions;
    assertResult to differentiate expected from actual values;
    intercept to ensure a bit of code throws an expected exception.

    In any Scala program, you can write assertions by invoking assert and passing in a Boolean expression:
    */
    val left = 1
    val right = 2
    assert(left == right)
    // RESULT: Assertion Error

    //===================================================================

    /*
    ScalaTest provides a domain specific language (DSL) for expressing assertions in tests using the word should.
    ScalaTest matchers provides five different ways to check equality, each designed to address a different need. They are:
    */

//    result should equal (3) // can customize equality
//    result should === (3)   // can customize quality and enforce type constraints
//    result should be (3)    // cannot customize equality, so fastest to compile
//    result shouldEqual 3    // can customize equality, no parentheses required
//    result shouldBe 3       // cannot customize equality, so fastest to compile, no parentheses required

    //===================================================================

    /*
    true and false values can be compared with should matchers
     */

//    true should be (true)

    /*
    Booleans in asserts can test equality.
     */
//    val v1 = 4
//    v1 shouldEqual 4

    assert(2 == 1 + 1)



  }

}
