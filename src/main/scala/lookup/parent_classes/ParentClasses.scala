package lookup.parent_classes

/**
  * from: https://www.scala-exercises.org/std_lib/parent_classes
  */
object ParentClasses {

  def main(args: Array[String]): Unit = {

    /**
      * In contrast to Java, all values in Scala are objects (including numerical values and functions).
      * Since Scala is class-based, all values are instances of a class.
      *
      * Class hierarchy is linear, a class can only extend from one parent class
      */

    class Soldier(val firstName: String, val lastName: String) {}
    class Pilot(override val firstName: String, override val lastName: String, val squadron: Long) extends Soldier(firstName, lastName)

    val pilot = new Pilot("John", "Yossarian", 256)
    println(pilot.firstName)
    // RESULT: John
    println(pilot.lastName)
    // RESULT: Yossarian

    /**
      * A class that extends from another is polymorphic:
      */

    class Soldier1(val firstName: String, val lastName: String) {}
    class Pilot1(override val firstName: String, override val lastName: String,
                val squadron: Long) extends Soldier1(firstName, lastName)

    val pilot1 = new Pilot1("John", "Yossarian", 256)
    val soldier1: Soldier1 = pilot1

    println(soldier1.firstName)
    // RESULT: John
    println(soldier1.lastName)
    // RESULT: Yossarian

    /**
      * An abstract class, as in Java, cannot be instantiated and only inherited:
      */

    abstract class Soldier2(val firstName: String, val lastName: String){}

    //if you uncomment this line, it will fail compilation
    //val soldier2 = new Soldier2

    /**
      * A class can be placed inside an abstract class just like in java
      */

    abstract class Soldier3(val firstName: String, val lastName: String){
      class Catch(val number: Long) {
        // nothing to do here. Just observe that it compiles
      }
    }

    class Pilot3(override val firstName: String, override val lastName: String, val squadron: Long) extends Soldier3(firstName, lastName)

    val pilot3 = new Pilot3("John", "Yossarian", 256)
    val catchNo = new pilot3.Catch(22)  // using the pilot instance's path, create an catch object for it.
    println(catchNo.number)
    // RESULT: 22

  }
}
