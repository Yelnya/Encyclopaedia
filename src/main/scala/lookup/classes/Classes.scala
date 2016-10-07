package lookup.classes


//from: https://www.scala-exercises.org/std_lib/classes
object Classes {

  def main(args: Array[String]): Unit = {

    /*
    Classes in Scala are static templates that can be instantiated into many objects at runtime. Here is a class
    definition which defines a class Point
    The class defines two variables x and y and one method: toString.

    Classes in Scala are parameterized with constructor arguments. The code above defines two constructor arguments,
    x and y; they are both visible in the whole body of the class. In our example they are used to implement toString
    */

    class Point (x: Int, y: Int) {
      override def toString: String = "(" + x + ", " + y + ")"
    }

    /*
    Classes are instantiated with the new primitive, as the following example will show.
    The program defines an executable application Classes in form of a top-level singleton object with a main method.
    The main method creates a new Point and stores it in value pt.
     */

    val pt = new Point(1, 2)
    println(pt.toString)

    /*
    Exercise:
    */
    class ClassWithValParameter (val name: String)
    val aClass = new ClassWithValParameter("Gandalf")
    println (aClass.name)
  }
}
