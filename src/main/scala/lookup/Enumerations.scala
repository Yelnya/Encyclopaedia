package lookup

/**
  * from: https://www.scala-exercises.org/std_lib/enumerations
  */

object Enumerations {

  def main(args: Array[String]): Unit = {

    /**
      * To create an enumeration, create an object that extends the abstract class Enumeration, and set a val
      * variable to the method Value. This is a trick to give values to each val.
      * Value assigns a numerical value to fields:
      */

    object Planets extends Enumeration {
      val Mercury = Value
      val Venus = Value
      val Earth = Value
      val Mars = Value
      val Jupiter = Value
      val Saturn = Value
      val Uranus = Value
      val Neptune = Value
      val Pluto = Value
    }

    println(Planets.Mercury.id)
    // RESULT: 0
    println(Planets.Venus.id)
    // RESULT: 1
    println(Planets.Mercury.toString)
    // RESULT: Mercury
    println(Planets.Venus.toString)
    // RESULT: Venus
    println(Planets.Earth == Planets.Earth)
    // RESULT: true
    println(Planets.Neptune == Planets.Jupiter)
    // RESULT: false

    /**
      * You can create an enumeration with your own index and your own Strings, in this exercise we will
      * start with an index of one and use Greek names instead of Roman:
      */

    object GreekPlanets extends Enumeration {

      val Mercury = Value(1, "Hermes")
      val Venus = Value(2, "Aphrodite")
      //Fun Fact: Tellus is Roman for (Mother) Earth
      val Earth = Value(3, "Gaia")
      val Mars = Value(4, "Ares")
      val Jupiter = Value(5, "Zeus")
      val Saturn = Value(6, "Cronus")
      val Uranus = Value(7, "Ouranus")
      val Neptune = Value(8, "Poseidon")
      val Pluto = Value(9, "Hades")
    }

    println(GreekPlanets.Mercury.id)
    println(GreekPlanets.Venus.id)

    println(GreekPlanets.Mercury.toString)
    println(GreekPlanets.Venus.toString)

    println(GreekPlanets.Earth == GreekPlanets.Earth)
    println(GreekPlanets.Neptune == GreekPlanets.Jupiter)


    /**
      * Enumerations can be declared in one line if you are merely setting variables to Value:
      */

    object Planets2 extends Enumeration {
      val Mercury, Venus, Earth, Mars, Jupiter, Saturn, Uranus, Neptune, Pluto = Value
    }

    println(Planets2.Mercury.id)
    println(Planets2.Venus.id)

    println(Planets2.Mercury.toString)
    println(Planets2.Venus.toString)

    println(Planets2.Earth == Planets2.Earth)
    println(Planets2.Neptune == Planets2.Jupiter)

    /**
      * Enumerations can be declared with a string value only:
      */

    object GreekPlanets2 extends Enumeration {

      val Mercury = Value("Hermes")
      val Venus = Value("Aphrodite")
      val Earth = Value("Gaia")
      val Mars = Value("Ares")
      val Jupiter = Value("Zeus")
      val Saturn = Value("Cronus")
      val Uranus = Value("Ouranus")
      val Neptune = Value("Poseidon")
      val Pluto = Value("Hades")
    }

    println(GreekPlanets2.Mercury.id)
    println(GreekPlanets2.Venus.id)
    println(GreekPlanets2.Mercury.toString)
    println(GreekPlanets2.Venus.toString)

    println(GreekPlanets2.Earth == GreekPlanets2.Earth)
    println(GreekPlanets2.Neptune == GreekPlanets2.Jupiter)

    object Planets3 extends Enumeration {

      val G = 6.67300E-11

      class PlanetValue(val i: Int, val name: String, val mass: Double, val radius: Double)
        extends Val(i: Int, name: String) {

        def surfaceGravity = G * mass / (radius * radius)

        def surfaceWeight(otherMass: Double) = otherMass * surfaceGravity

        def compare(that: PlanetValue) = this.i - that.i
      }

      val Mercury = new PlanetValue(0, "Mercury", 3.303e+23, 2.4397e6)
      val Venus = new PlanetValue(1, "Venus", 4.869e+24, 6.0518e6)
      val Earth = new PlanetValue(2, "Earth", 5.976e+24, 6.37814e6)
      val Mars = new PlanetValue(3, "Mars", 6.421e+23, 3.3972e6)
      val Jupiter = new PlanetValue(4, "Jupiter", 1.9e+27, 7.1492e7)
      val Saturn = new PlanetValue(5, "Saturn", 5.688e+26, 6.0268e7)
      val Uranus = new PlanetValue(6, "Uranus", 8.686e+25, 2.5559e7)
      val Neptune = new PlanetValue(7, "Neptune", 1.024e+26, 2.4746e7)
      val Pluto = new PlanetValue(8, "Pluto", 1.27e+22, 1.137e6)

    }

    println(Planets3.Earth.mass)
    println(Planets3.Earth.radius)

  }
}
