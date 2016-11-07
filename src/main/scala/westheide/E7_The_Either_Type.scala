package westheide

/**
  * from: http://danielwestheide.com/blog/2013/01/02/the-neophytes-guide-to-scala-part-7-the-either-type.html
  */

object E7_The_Either_Type {

  def main(args: Array[String]): Unit = {

    /**
      * Either has exactly two sub types, Left and Right. If an Either[A, B] object contains an instance of
      * A, then the Either is a Left. Otherwise it contains an instance of B and is a Right.
      */

    /**
      * Creating an Either
      */

    import scala.io.Source
    import java.net.URL
    def getContent(url: URL): Either[String, Source] =
      if (url.getHost.contains("google"))
        Left("Requested URL is blocked for the good of the people!")
      else
        Right(Source.fromURL(url))

    println(getContent(new URL("http://danielwestheide.com")))
    // RESULT: Right(non-empty iterator)

    /**
      * Working with Either values
      */

    println(
      getContent(new URL("http://google.com")) match {
        case Left(msg) => println(msg)
        case Right(source) => source.getLines.foreach(println)
      }
    )
    // RESULT: Requested URL is blocked for the good of the people!


    /**
      * Mapping of Projections
      */

    val content: Either[String, Iterator[String]] =
    getContent(new URL("http://danielwestheide.com")).right.map(_.getLines())
    // content is a Right containing the lines from the Source returned by getContent
    val moreContent: Either[String, Iterator[String]] =
    getContent(new URL("http://google.com")).right.map(_.getLines)
    // moreContent is a Left, as already returned by getContent

    println(content)
    // RESULT: Right(non-empty iterator)
    println(moreContent)
    // RESULT: Left(Requested URL is blocked for the good of the people!)

    val contentLeft: Either[Iterator[String], Source] =
      getContent(new URL("http://danielwestheide.com")).left.map(Iterator(_))
    // content is the Right containing a Source, as already returned by getContent
    val moreContentLeft: Either[Iterator[String], Source] =
    getContent(new URL("http://google.com")).left.map(Iterator(_))
    // moreContent is a Left containing the msg returned by getContent in an Iterator

    println(contentLeft)
    // RESULT: Right(non-empty iterator)
    println(moreContentLeft)
    // RESULT: Left(non-empty iterator)

    /**
      * Flat mapping
      */

    val part5 = new URL("http://t.co/UR1aalX4")
    val part6 = new URL("http://t.co/6wlKwTmu")
    val content2 = getContent(part5).right.map(a =>
      getContent(part6).right.map(b =>
        (a.getLines().size + b.getLines().size) / 2))

    println(content2)
    // RESULT: Right(Right(539))

    val content3 = getContent(part5).right.flatMap(a =>
      getContent(part6).right.map(b =>
        (a.getLines().size + b.getLines().size) / 2))

    println(content3)
    // RESULT: Right(539)


    /**
      * For comprehensions
      */

    val url1 = new URL("http://www.google.at")
    val url2 = new URL("http://www.wikipedia.com")

    def averageLineCount(url1: URL, url2: URL): Either[String, Int] =
      for {
        source1 <- getContent(url1).right
        source2 <- getContent(url2).right
      } yield (source1.getLines().size + source2.getLines().size) / 2

    println(averageLineCount(url1, url2))
    // RESULT: Left(Requested URL is blocked for the good of the people!)

    def averageLineCount2(url1: URL, url2: URL): Either[String, Int] =
      for {
        source1 <- getContent(url1).right
        source2 <- getContent(url2).right
        lines1 <- Right(source1.getLines().size).right
        lines2 <- Right(source2.getLines().size).right
      } yield (lines1 + lines2) / 2

    println(averageLineCount2(url1, url2))
    // RESULT: Left(Requested URL is blocked for the good of the people!)


    /**
      * Folding
      */

    val content4: Iterator[String] =
    getContent(new URL("http://danielwestheide.com")).fold(Iterator(_), _.getLines())
    val moreContent4: Iterator[String] =
      getContent(new URL("http://google.com")).fold(Iterator(_), _.getLines())

    println(content4)
    // RESULT: non-empty iterator
    println(moreContent4)
    // RESULT: non-empty iterator


    /**
      * Error handling
      */

    case class Customer(age: Int)
    class Cigarettes
    case class UnderAgeFailure(age: Int, required: Int)
    def buyCigarettes(customer: Customer): Either[UnderAgeFailure, Cigarettes] =
      if (customer.age < 16) Left(UnderAgeFailure(customer.age, 16))
      else Right(new Cigarettes)

    println(buyCigarettes(Customer(32)))
    // RESULT: Right(westheide.E7_The_Either_Type$Cigarettes$1@23ceabc1)
    println(buyCigarettes(Customer(14)))
    // RESULT: Left(UnderAgeFailure(14,16))

    /**
      * Processing collections
      */

    type Citizen = String
    case class BlackListedResource(url: URL, visitors: Set[Citizen])

    val blacklist = List(
      BlackListedResource(new URL("https://google.com"), Set("John Doe", "Johanna Doe")),
      BlackListedResource(new URL("http://yahoo.com"), Set.empty),
      BlackListedResource(new URL("https://maps.google.com"), Set("John Doe")),
      BlackListedResource(new URL("http://plus.google.com"), Set.empty)
    )

    val checkedBlacklist: List[Either[URL, Set[Citizen]]] =
      blacklist.map(resource =>
        if (resource.visitors.isEmpty) Left(resource.url)
        else Right(resource.visitors))

    println(checkedBlacklist)
    // RESULT: List(Right(Set(John Doe, Johanna Doe)), Left(http://yahoo.com), Right(Set(John Doe)), Left(http://plus.google.com))

    val suspiciousResources = checkedBlacklist.flatMap(_.left.toOption)
    println(suspiciousResources)
    // RESULT: List(http://yahoo.com, http://plus.google.com)
    val problemCitizens = checkedBlacklist.flatMap(_.right.toOption).flatten.toSet
    println(problemCitizens)
    // RESULT: Set(John Doe, Johanna Doe)

  }
}
