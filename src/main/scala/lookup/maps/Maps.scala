package lookup.maps

// from: https://www.scala-exercises.org/std_lib/maps
object Maps {

  def main(args: Array[String]): Unit = {

    /*
    A Map is an Iterable consisting of pairs of keys and values (also named mappings or associations). Scala's Predef
    class offers an implicit conversion that lets you write key -> value as an alternate syntax for the pair (key,
    value). For instance Map("x" -> 24, "y" -> 25, "z" -> 26) means exactly the same as Map(("x", 24), ("y", 25),
    ("z", 26)), but reads better.

    The fundamental operations on maps are similar to those on sets. They are summarized in the following table and fall
    into the following categories:

    - Lookup operations apply, get, getOrElse, contains, and isDefinedAt. These turn maps into partial functions from keys
    to values. The fundamental lookup method for a map is: def get(key): Option[Value]. The operation "m get key" tests
    whether the map contains an association for the given key. If so, it returns the associated value in a Some. If no
    key is defined in the map, get returns None. Maps also define an apply method that returns the value associated with
    a given key directly, without wrapping it in an Option. If the key is not defined in the map, an exception is raised.
    - Additions and updates +, ++, updated, which let you add new bindings to a map or change existing bindings. - Removals
    -, --, which remove bindings from a map. - Subcollection producers keys, keySet, keysIterator, values, valuesIterator,
    which return a map's keys and values separately in various forms. - Transformations filterKeys and mapValues, which
    produce a new map by filtering and transforming bindings of an existing map.

    Maps can be created easily:
    */

    val myMap = Map("MI" -> "Michigan", "OH" -> "Ohio", "WI" -> "Wisconsin", "IA" -> "Iowa")
    println(myMap.size)
    // RESULT: 4

    /*
    Maps contain distinct pairings
    */

    val myMap2 = Map("MI" -> "Michigan", "OH" -> "Ohio", "WI" -> "Wisconsin", "MI" -> "Michigan")
    println(myMap2.size)
    // RESULT: 3

    /*
    Maps can be added to easily
    */

    val aNewMap2 = myMap2 + ("IL" -> "Illinois")
    println(aNewMap2.contains("IL"))
    // RESULT: true
    println(aNewMap2)
    // RESULT: Map(MI -> Michigan, OH -> Ohio, WI -> Wisconsin, IL -> Illinois)

    /*
    Map values can be iterated
    */

    println(myMap2.head)
    // RESULT: (MI,Michigan)
    println(myMap2.tail)
    // RESULT: Map(OH -> Ohio, WI -> Wisconsin)

    /*
    Maps insertion with duplicate key updates previous entry with subsequent value
    */

    val myMap3 = Map("MI" -> "Michigan", "OH" -> "Ohio", "WI" -> "Wisconsin", "MI" -> "Meechigan")
    println(myMap3("MI"))
    // RESULT: Meechigan

    /*
    Map keys may be of mixed type
    */

    val myMap4 = Map("Ann Arbor" -> "MI", 49931 -> "MI")
    println(myMap4("Ann Arbor"))
    // RESULT: MI
    println(myMap4(49931))
    // RESULT: MI

    /*
    Maps may be accessed
    */

    val myMap5 = Map("MI" -> "Michigan", "OH" -> "Ohio", "WI" -> "Wisconsin", "IA" -> "Iowa")

    println(myMap5("OH"))
    // RESULT: Ohio

    /*
    If a map key is requested using myMap(missingKey) which does not exist a NoSuchElementException will be thrown.
    Default values may be provided using either getOrElse or withDefaultValue for the entire map
    */

    println(myMap5.getOrElse("TX", "missing data"))
    // RESULT: missing data
    println(myMap5.getOrElse("OH", "missing data"))
    // RESULT: Ohio

    /*
    Map elements can be removed easily
    */

    val aNewMap5 = myMap5 - "MI"
    println(aNewMap5)
    // RESULT: Map(OH -> Ohio, WI -> Wisconsin, IA -> Iowa)
    println(aNewMap5.contains("MI"))
    // RESULT: false

    /*
    Map elements can be removed in multiple
    */

    val myMap6 = Map("MI" -> "Michigan", "OH" -> "Ohio", "WI" -> "Wisconsin", "IA" -> "Iowa")

    val aNewMap6 = myMap6 -- List("MI", "OH")
    println(aNewMap6)
    // RESULT: Map(WI -> Wisconsin, IA -> Iowa)

    /*
    Map elements can be removed with a tuple
    */

    val myMap7 = Map("MI" -> "Michigan", "OH" -> "Ohio", "WI" -> "Wisconsin", "IA" -> "Iowa")

    val aNewMap7 = myMap7 - ("MI", "OH")
    println(aNewMap7)
    // RESULT: Map(WI -> Wisconsin, IA -> Iowa)

    /*
    Attempted removal of nonexistent elements from a map is handled gracefully
    */

    val aNewMap8 = myMap7 - ("MN")
    println(aNewMap8)
    // RESULT: Map(MI -> Michigan, OH -> Ohio, WI -> Wisconsin, IA -> Iowa)

    /*
    Map equivalency is independent of order
    */

    val myMap9 = Map("MI" -> "Michigan", "OH" -> "Ohio", "WI" -> "Wisconsin", "IA" -> "Iowa")
    val myMap10 = Map("WI" -> "Wisconsin", "MI" -> "Michigan", "IA" -> "Iowa", "OH" -> "Ohio")

    println(myMap9)
    // RESULT: Map(MI -> Michigan, OH -> Ohio, WI -> Wisconsin, IA -> Iowa)
    println(myMap10)
    // RESULT: Map(WI -> Wisconsin, MI -> Michigan, IA -> Iowa, OH -> Ohio)
    println(myMap9.eq(myMap10))
    // RESULT: false
    println(myMap9 == myMap10)
    // RESULT: true
  }
}
