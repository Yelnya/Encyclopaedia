package lookup.objects

// from: https://www.scala-exercises.org/std_lib/objects
object Objects {

  def main(args: Array[String]): Unit = {

    /*
    An object is a singleton. One object, that's it. This object is a replacement of static in Java, and is called upon much in the same way.
    */

    object Greeting {
      def english = "Hi"
      def espanol = "Hola"
      def deutsch = "Hallo"
      def magyar = "Szia"
    }

    println(Greeting.english)
    // RESULT: "Hi"
    println(Greeting.espanol)
    // RESULT: "Hola"
    println(Greeting.deutsch)
    // RESULT: "Hallo"
    println(Greeting.magyar)
    // RESULT: "Szia"

    /*
    Here is a proof that an object is a singleton, and not a static method in a class.
    */

    val x = Greeting
    val y = x
    var trueOrFalse : Boolean = (x == y)
    println(trueOrFalse)
    // RESULT: true

    val z = Greeting
    trueOrFalse = (x == z)
    println(trueOrFalse)
    // RESULT: true

    /*
    An object that has the same name as a class is called a companion object of the class, and it is often used to
    contain factory methods for the class that it complements.
    */

    class Movie(val name: String, val year: Short)
    object Movie {
      def academyAwardBestMoviesForYear(x: Short) = {
        //This is a match statement, more powerful than a Java switch statement!
        x match {
          case 1930 => Some(new Movie("All Quiet On the Western Front", 1930))
          case 1931 => Some(new Movie("Cimarron", 1931))
          case 1932 => Some(new Movie("Grand Hotel", 1932))
          case _ => None
        }
      }
    }
    println(Movie.academyAwardBestMoviesForYear(1932).get.name)
    // RESULT: Grand Hotel

    /*
    A companion object can also see private values and variables of the corresponding classes' instantiated objects
    */

    class Person(val name: String, private val superheroName: String) //The superhero name is private!

    object Person {
      def showMeInnerSecret(x: Person) = x.superheroName
    }

    val clark = new Person("Clark Kent", "Superman")
    val peter = new Person("Peter Parker", "Spiderman")
    val bruce = new Person("Bruce Wayne", "Batman")
    val diana = new Person("Diana Prince", "Wonder Woman")

    println(Person.showMeInnerSecret(clark))
    // RESULT: Superman
    println(Person.showMeInnerSecret(peter))
    // RESULT: Spiderman
    println(Person.showMeInnerSecret(bruce))
    // RESULT: Batman
    println(Person.showMeInnerSecret(diana))
    // RESULT: Wonder Woman
  }
}
