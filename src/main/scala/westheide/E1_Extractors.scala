package westheide

/**
  * from: http://danielwestheide.com/blog/2012/11/21/the-neophytes-guide-to-scala-part-1-extractors.html
  */

object E1_Extractors {

  def main(args: Array[String]): Unit = {

    /**
      * EXTRACTORS
      */

    // In its most widely applied form, an extractor has the opposite role of a constructor: While the latter
    // creates an object from a given list of parameters, an extractor extracts the parameters from which an
    // object passed to it was created.
    // Case classes are special because Scala automatically creates a companion object for them: a singleton
    // object that contains not only an apply method for creating new instances of the case class, but also an
    // unapply method – the method that needs to be implemented by an object in order for it to be an extractor.

    case class User(firstName: String, lastName: String, score: Int)
    def advance(xs: List[User]) = xs match {
      case User(_, _, score1) :: User(_, _, score2) :: _ => score1 - score2
      case _ => 0
    }


    // We want to implement extractors for the FreeUser and PremiumUser classes in respective companion objects,
    // just as Scala would have done were these case classes.
    trait User1 {
      def name: String
    }
    class FreeUser(val name: String) extends User1
    class PremiumUser(val name: String) extends User1

    object FreeUser {
      def unapply(user: FreeUser): Option[String] = Some(user.name)
    }
    object PremiumUser {
      def unapply(user: PremiumUser): Option[String] = Some(user.name)
    }

    println(FreeUser.unapply(new FreeUser("Daniel")))
    // RESULT: Some(Daniel)

    // If the result of calling unapply is Some[T], this means that the pattern matches, and the extracted value
    // is bound to the variable declared in the pattern. If it is None, this means that the pattern doesn’t
    // match and the next case statement is tested.

    val user: User1 = new PremiumUser("Daniel")
    user match {
      case FreeUser(name) => "Hello" + name
      case PremiumUser(name) => "Welcome back, dear " + name
    }

    // The two extractors never return None! The example shows that this makes more sense than it might seem at first.
    // If you have an object that could be of some type or another, you can check its type and destructure it at the
    // same time. In the example, the FreeUser pattern will not match bevause it expects an object of a different type
    // than we pass it. Since it wants an object of type FreeUser, not one of type PremiumUser, this extractor is never
    // even called.

    /**
      * EXTRACTING SEVERAL VALUES
      */

    trait User2 {
      def name: String
      def score: Int
    }

    class FreeUser2(val name: String, val score: Int, val upgradeProbability: Double) extends User2
    class PremiumUser2(val name: String, val score: Int) extends User2

    object FreeUser2 {
      def unapply(user: FreeUser2): Option[(String, Int, Double)] = Some((user.name, user.score, user.upgradeProbability))
    }

    object PremiumUser2 {
      def unapply(user: PremiumUser2): Option[(String, Int)] = Some((user.name, user.score))
    }

    // Pattern Matching for extraction

    val user2: User2 = new FreeUser2("Daniel", 3000, 0.7d)
    user2 match {
      case FreeUser2(name, _, p) =>
        if (p > 0.75) println(name + ", what can we do for you today?") else println("Hello " + name)
      case PremiumUser2(name, _) => println("Welcome back, dear " + name)
    }


    // Infix operation patterns - #:: is for streams

    val xs = 58 #:: 43 #:: 93 #:: Stream.empty
    xs match {
      case first #:: second #:: _ => println(first - second)
      case _ => println(-1)
    }

  }

}
