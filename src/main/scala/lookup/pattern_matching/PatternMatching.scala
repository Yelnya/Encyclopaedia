package lookup.pattern_matching

/**
  * from: https://www.scala-exercises.org/std_lib/pattern_matching
  */
object PatternMatching {

  def main(args: Array[String]): Unit = {

    /**
      * Scala has a built-in general pattern matching mechanism. It allows to match on any sort of data with a
      * first-match policy. Here is a small example which shows how to match against an integer value
      * The block with the case statements defines a function which maps integers to strings. The match keyword
      * provides a convenient way of applying a function (like the pattern matching function above) to an object.
      *
      * Scala's pattern matching statement is most useful for matching on algebraic types expressed via case classes. Scala
      * also allows the definition of patterns independently of case classes, using unapply methods in extractor objects.
      */
    def matchTest(x: Int): String = x match {
      case 1 => "one"
      case 2 => "two"
      case _ => "many"
    }
    println(matchTest(3))
    // RESULT: many

    /**
      * Pattern matching returns something
      */
    val stuff = "blue"

    val myStuff = stuff match {
      case "red" ⇒
        println("RED"); 1
      case "blue" ⇒
        println("BLUE"); 2
      case "green" ⇒
        println("GREEN"); 3
      case _ ⇒ println(stuff); 0 //case _ will trigger if all other cases fail.
    }

    println(myStuff)
    // RESULT: BLUE; 2

    /**
      * Pattern matching can return complex somethings
      */

    val stuff2 = "blue"

    val myStuff2 = stuff match {
      case "red" ⇒ (255, 0, 0)
      case "green" ⇒ (0, 255, 0)
      case "blue" ⇒ (0, 0, 255)
      case _ ⇒ println(stuff2); 0
    }

    println(myStuff2)
    // RESULT: (0,0,255)

    /**
      * Pattern matching can match complex expressions
      */

    def goldilocks(expr: Any) = expr match {
      case ("porridge", "Papa") ⇒ "Papa eating porridge"
      case ("porridge", "Mama") ⇒ "Mama eating porridge"
      case ("porridge", "Baby") ⇒ "Baby eating porridge"
      case _ ⇒ "what?"
    }

    println(goldilocks("porridge", "Mama"))
    // RESULT: Mama eating porridge

    /**
      * Pattern matching can wildcard parts of expressions
      */

    def goldilocks2(expr: Any) = expr match {
      case ("porridge", _) ⇒ "eating"
      case ("chair", "Mama") ⇒ "sitting"
      case ("bed", "Baby") ⇒ "sleeping"
      case _ ⇒ "what?"
    }

    println(goldilocks2(("porridge", "Papa")))
    // RESULT: eating
    println(goldilocks2(("chair", "Mama")))
    // RESULT:  sitting

    /**
      * Pattern matching can substitute parts of expressions
      */

    def goldilocks3(expr: Any) = expr match {
      case ("porridge", bear) ⇒ bear + " said someone's been eating my porridge"
      case ("chair", bear) ⇒ bear + " said someone's been sitting in my chair"
      case ("bed", bear) ⇒ bear + " said someone's been sleeping in my bed"
      case _ ⇒ "what?"
    }

    println(goldilocks3(("porridge", "Papa")))
    // RESULT: Papa said someone's been eating my porridge
    println(goldilocks3(("chair", "Mama")))
    // RESULT: Mama said someone's been sitting in my chair

    /**
      * A backquote can be used to refer to a stable variable in scope to create a case statement. This prevents
      * what is called "Variable Shadowing"
      */

    val foodItem = "porridge"

    def goldilocks4(expr: Any) = expr match {
      case (`foodItem`, _) ⇒ "eating"
      case ("chair", "Mama") ⇒ "sitting"
      case ("bed", "Baby") ⇒ "sleeping"
      case _ ⇒ "what?"
    }

    println(goldilocks4(("porridge", "Papa")))
    // RESULT: eating
    println(goldilocks4(("chair", "Mama")))
    // RESULT: sitting
    println(goldilocks4(("porridge", "Cousin")))
    // RESULT: eating
    println(goldilocks4(("beer", "Cousin")))
    // RESULT: what?

    /**
      * A backquote can be used to refer to a method parameter as a stable variable to create a case statement
      */

    def patternEquals(i: Int, j: Int) = j match {
      case `i` ⇒ true
      case _ ⇒ false
    }
    println(patternEquals(3, 3))
    // RESULT: true
    println(patternEquals(7, 9))
    // RESULT: false
    println(patternEquals(9, 9))
    // RESULT: true

    /**
      * To pattern match against a List, the list can be broken out into parts, in this case the head x and the
      * tail xs. Since the case doesn't terminate in Nil, xs is interpreted as the rest of the list
      */

    val secondElement = List(1, 2, 3) match {
      case x :: xs ⇒ xs.head
      case _ ⇒ 0
    }

    println(secondElement)
    // RESULT: 2

    /**
      * To obtain the second element you can expand on the pattern. Where x is the first element,
      * y is the second element, and xs is the res
      */

    val secondElement2 = List(1, 2, 3) match {
      case x :: y :: xs ⇒ y
      case _ ⇒ 0
    }

    println(secondElement2)
    // RESULT: 2

    /**
      * Same koan as above, but we are pattern matching a list with only one item!
      */

    val secondElement3 = List(1) match {
      case x :: y :: xs ⇒ y
      case _ ⇒ 0
    }

    println(secondElement3)
    // RESULT: 0

    /**
      * To pattern match against List, you can also establish a pattern match if you know the exact
      * number of elements in a List
      */

    val r = List(1, 2, 3) match {
      case x :: y :: Nil ⇒ y
      case _ ⇒ 0
    }

    // TODO: don't understand this result!
    println(r)
    // RESULT: 0

  }
}
