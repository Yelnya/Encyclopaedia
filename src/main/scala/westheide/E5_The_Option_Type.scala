package westheide

/**
  * from: http://danielwestheide.com/blog/2012/12/19/the-neophytes-guide-to-scala-part-5-the-option-type.html
  */

object E5_The_Option_Type {

  def main(args: Array[String]): Unit = {

    /**
      * Creating an option
      */

    val greeting: Option[String] = Some("Hello world")
    println(greeting)
    // RESULT: Some(Hello world)
    val greeting2: Option[String] = None
    println(greeting2)
    // RESULT: None

    val absentGreeting: Option[String] = Option(null) // absentGreeting will be None
    println(absentGreeting)
    // RESULT: None
    val presentGreeting: Option[String] = Option("Hello!") // presentGreeting will be Some("Hello!")
    println(presentGreeting)
    // RESULT: Some(Hello!)

    /**
      * Working with optional values
      */

    case class User(
                     id: Int,
                     firstName: String,
                     lastName: String,
                     age: Int,
                     gender: Option[String])

    object UserRepository {
      private val users = Map(1 -> User(1, "John", "Doe", 32, Some("male")),
        2 -> User(2, "Johanna", "Doe", 30, None))

      def findById(id: Int): Option[User] = users.get(id)

      def findAll = users.values
    }

    val user1 = UserRepository.findById(1)
    if (user1.isDefined) {
      println(user1.get.firstName)
    } // RESULT: John

    /**
      * Providing a default value
      */

    val user = User(2, "Johanna", "Doe", 30, None)
    println("Gender: " + user.gender.getOrElse("not specified"))
    // RESULT: Gender: not specified

    /**
      * Pattern Matching
      */

    user.gender match {
      case Some(gender) => println("Gender: " + gender)
      case None => println("Gender: not specified")
    }
    // RESULT: Gender: not specified

    val gender = user.gender match {
      case Some(gender) => gender
      case None => "not specified"
    }
    println("Gender: " + gender)
    // RESULT: Gender: not specified

    /**
      * Performing a side-effect if a value is present
      */

    UserRepository.findById(2).foreach(user => println(user.firstName))
    // RESULT: Johanna

    /**
      * Mapping an option
      */

    val age = UserRepository.findById(1).map(_.age)
    println(age)
    // RESULT: Some(32)

    /**
      * flatMap and options
      */

    val gender2 = UserRepository.findById(1).map(_.gender)
    println(gender2)
    // RESULT: Some(Some(male))

    val gender3 = UserRepository.findById(1).flatMap(_.gender)
    println(gender3)
    // RESULT: Some(male)
    val gender4 = UserRepository.findById(2).flatMap(_.gender)
    println(gender4)
    // RESULT: None
    val gender5 = UserRepository.findById(3).flatMap(_.gender)
    println(gender5)
    // RESULT: None

    val names: List[List[String]] =
      List(List("John", "Johanna", "Daniel"), List(), List("Doe", "Westheide"))
    println(names.map(_.map(_.toUpperCase)))
    // RESULT: List(List(JOHN, JOHANNA, DANIEL), List(), List(DOE, WESTHEIDE))
    println(names.flatMap(_.map(_.toUpperCase)))
    // RESULT: List(JOHN, JOHANNA, DANIEL, DOE, WESTHEIDE)

    val names2: List[Option[String]] = List(Some("Johanna"), None, Some("Daniel"))
    println(names2.map(_.map(_.toUpperCase)))
    // RESULT: List(Some(JOHANNA), None, Some(DANIEL))
    println(names2.flatMap(xs => xs.map(_.toUpperCase)))
    // RESULT: List(JOHANNA, DANIEL)


    /**
      * Filtering an option
      */

    println(UserRepository.findById(1).filter(_.age > 30)) // Some(User(1,John,Doe,32,Some(male))), because age is > 30
    println(UserRepository.findById(2).filter(_.age > 30)) // None, because age is <= 30
    println(UserRepository.findById(3).filter(_.age > 30)) // None, because user is already None


    /**
      * For comprehensions
      */

    println(
      for {
        user <- UserRepository.findById(1)
        gender <- user.gender
      } yield gender
    ) // results in Some("male")

    println(
      for {
        user <- UserRepository.findAll
        gender <- user.gender
      } yield gender
    ) // RESULT: List(male)


    /**
      * Usage in the left side of a generator
      */

    println(
      for {
        User(_, _, _, _, Some(gender)) <- UserRepository.findAll
      } yield gender
    ) // RESULT: List(male)


    /**
      * Chaining options
      */

    case class Resource(content: String)
    val resourceFromConfigDir: Option[Resource] = None
    val resourceFromClasspath: Option[Resource] = Some(Resource("I was found on the classpath"))
    val resource = resourceFromConfigDir orElse resourceFromClasspath
    println(resource)
    // RESULT: Some(Resource(I was found on the classpath))

  }
}
