package lookup.Sets

// from: https://www.scala-exercises.org/std_lib/sets
object Sets {

  def main(args: Array[String]): Unit = {

    /*
    Sets are Iterables that contain no duplicate elements. The operations on sets are summarized in the following
    table for general sets and in the table after that for mutable sets. They fall into the following categories:

    * **Tests** contains, apply, subsetOf. The contains method asks whether a set contains a given element. The apply
    * method for a set is the same as contains, so set(elem) is the same as set contains elem. That means sets can also
    * be used as test functions that return true for the elements they contain. * **Additions** + and ++, which add one or
    * more elements to a set, yielding a new set. * **Removals** -, --, which remove one or more elements from a set,
    * yielding a new set. * **Set operations** for union, intersection, and set difference. Each of these operations
    * exists in two forms: alphabetic and symbolic. The alphabetic versions are intersect, union, and diff, whereas
    * the symbolic versions are &, |, and &~. In fact, the ++ that Set inherits from Traversable can be seen as yet
    * another alias of union or |, except that ++ takes a Traversable argument whereas union and | take sets.

    Sets can be created easily
    */

    val mySet = Set("Michigan", "Ohio", "Wisconsin", "Iowa")

    println(mySet)
    // RESULT: Set(Michigan, Ohio, Wisconsin, Iowa)
    println(mySet.size)
    // RESULT: 4

    /*
    Sets contain distinct values
    */

    val mySet2 = Set("Michigan", "Ohio", "Wisconsin", "Michigan")
    println(mySet2)
    // RESULT: Set(Michigan, Ohio, Wisconsin)
    println(mySet2.size)
    // RESULT: 3

    /*
    Sets can be added to easily
    */

    val aNewSet2 = mySet2 + "Illinois"
    println(aNewSet2)
    // RESULT: Set(Michigan, Ohio, Wisconsin, Illinois)

    /*
    Sets may be of mixed type
    */

    val mySet3 = Set("Michigan", "Ohio", 12)
    println(mySet3)
    // RESULT: Set(Michigan, Ohio, 12)

    /*
    Sets can be checked for member existence
    */

    println(aNewSet2)
    // RESULT: Set(Michigan, Ohio, Wisconsin, Illinois)
    println(aNewSet2.contains("Illinois"))
    // RESULT: true

    /*
    Set elements can be removed easily
    */

    val mySet4 = Set("Michigan", "Ohio", "Wisconsin", "Iowa")
    val aNewSet4 = mySet4 - "Michigan"
    println(mySet4)
    // RESULT: Set(Michigan, Ohio, Wisconsin, Iowa)
    println(aNewSet4)
    // RESULT: Set(Ohio, Wisconsin, Iowa)

    /*
    Set elements can be removed in multiple
    */

    val mySet5 = Set("Michigan", "Ohio", "Wisconsin", "Iowa")
    val aNewSet5 = mySet5 -- List("Michigan", "Ohio")
    println(mySet5)
    // RESULT: Set(Michigan, Ohio, Wisconsin, Iowa)
    println(aNewSet5)
    // RESULT: Set(Wisconsin, Iowa)

    /*
    Set elements can be removed with a tuple
    */

    val mySet6 = Set("Michigan", "Ohio", "Wisconsin", "Iowa")
    val aNewSet6 = mySet6 - ("Michigan", "Ohio") // Notice: single '-' operator for tuples
    val aNewSet7 = mySet6 - "Minnesota"   // Attempted removal of nonexistent elements from a set is handled gracefully

    println(mySet6)
    // RESULT: Set(Michigan, Ohio, Wisconsin, Iowa)
    println(aNewSet6)
    // RESULT: Set(Wisconsin, Iowa)

    /*
    Two sets can be intersected easily
    */

    val mySet8 = Set("Michigan", "Ohio", "Wisconsin", "Iowa")
    val mySet9 = Set("Wisconsin", "Michigan", "Minnesota")

    val aNewSet10 = mySet8 intersect mySet9

    println (aNewSet10)
    // RESULT: Set(Michigan, Wisconsin)

    /*
    Two sets can be joined as their union easily
    */

    val mySet10 = Set("Michigan", "Ohio", "Wisconsin", "Iowa")
    val mySet11 = Set("Wisconsin", "Michigan", "Minnesota")

    val aNewSet12 = mySet10 union mySet11 // NOTE: You can also use the "|" operator

    println(aNewSet12)
    // RESULT: Set(Minnesota, Wisconsin, Ohio, Iowa, Michigan)

    /*
    A set is either a subset of another set or it isn't
    */
    val mySet13 = Set("Michigan", "Ohio", "Wisconsin", "Iowa")
    val mySet14 = Set("Wisconsin", "Michigan", "Minnesota")
    val mySet15 = Set("Wisconsin", "Michigan")

    println(mySet15 subsetOf(mySet13))
    // RESULT: true
    println(mySet14 subsetOf(mySet13))
    // RESULT: false

    /*
    The difference between two sets can be obtained easily
    */

    println(mySet13 diff mySet15)
    // RESULT: Set(Ohio, Iowa)

    /*
    Set equivalency is independent of order
    */

    val mySet16 = Set("Michigan", "Ohio", "Wisconsin", "Iowa")
    val mySet17 = Set("Wisconsin", "Michigan", "Ohio", "Iowa")

    println(mySet16.eq(mySet17))
    // RESULT: false
  }
}
