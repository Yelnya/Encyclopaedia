package westheide

/**
  * from: http://danielwestheide.com/blog/2012/12/26/the-neophytes-guide-to-scala-part-6-error-handling-with-try.html
  */

object E6_Error_Handling_With_Try {

  def main(args: Array[String]): Unit = {

    /**
      * Throwing and catching exceptions
      */

    case class Customer(age: Int)
    class Cigarettes
    case class UnderAgeException(message: String) extends Exception(message)
    def buyCigarettes(customer: Customer): Cigarettes =
      if (customer.age < 16)
        throw UnderAgeException(s"Customer must be older than 16 but was ${customer.age}")
      else new Cigarettes

    val youngCustomer = Customer(15)
    try {
      buyCigarettes(youngCustomer)
      println("Yo, here are your cancer sticks! Happy smokin'!")
    } catch {
      case UnderAgeException(msg) => println(msg)
    }
    // RESULT: Customer must be older than 16 but was 15

    /**
      * The semantics of Try
      */

    import scala.util.Try
    import java.net.URL
    def parseURL(url: String): Try[URL] = Try(new URL(url))

    println(parseURL("http://danielwestheide.com"))
    // RESULT: Success(http://danielwestheide.com)
    println(parseURL("garbage"))
    // RESULT: Failure(java.net.MalformedURLException: no protocol: garbage)


    /**
      * Working with Try values
      */

    //    val url = parseURL(Console.readLine("URL: ")) getOrElse new URL("http://duckduckgo.com")
    //    println(url)
    // RESULT: URL:


    /**
      * Chaining operations
      */

    println(parseURL("http://danielwestheide.com").map(_.getProtocol))
    // results in Success("http")
    println(parseURL("garbage").map(_.getProtocol))
    // results in Failure(java.net.MalformedURLException: no protocol: garbage)

    import java.io.InputStream
    def inputStreamForURL(url: String): Try[InputStream] = parseURL(url).flatMap { u =>
      Try(u.openConnection()).flatMap(conn => Try(conn.getInputStream))
    }

    println(inputStreamForURL("http://danielwestheide.com"))
    // RESULT: Success(sun.net.www.protocol.http.HttpURLConnection$HttpInputStream@13c78c0b)


    /**
      * Filter and foreach
      */

    def parseHttpURL(url: String) = parseURL(url).filter(_.getProtocol == "http")
    println(parseHttpURL("http://apache.openmirror.de")) // results in a Success[URL]
    // RESULT: Success(http://apache.openmirror.de)
    println(parseHttpURL("ftp://mirror.netcologne.de/apache.org")) // results in a Failure[URL]
    // RESULT: Failure(java.util.NoSuchElementException: Predicate does not hold for ftp://mirror.netcologne.de/apache.org)
    parseHttpURL("http://danielwestheide.com").foreach(println)
    // RESULT: http://danielwestheide.com


    /**
      * For comprehensions
      */

    import scala.io.Source
    def getURLContent(url: String): Try[Iterator[String]] =
      for {
        url <- parseURL(url)
        connection <- Try(url.openConnection())
        is <- Try(connection.getInputStream)
        source = Source.fromInputStream(is)
      } yield source.getLines()

    println(getURLContent("http://www.google.at"))
    // RESULT: Success(non-empty iterator)

    /**
      * Pattern Matching
      */

    import scala.util.Success
    import scala.util.Failure

    println(
      getURLContent("http://danielwestheide.com/foobar") match {
        case Success(lines) => lines.foreach(println)
        case Failure(ex) => println(s"Problem rendering URL content: ${ex.getMessage}")
      }
    )
    // RESULT: Problem rendering URL content: http://danielwestheide.com/foobar()

    /**
      * Recovering from a Failure
      */

    import java.net.MalformedURLException
    import java.io.FileNotFoundException
    val content = getURLContent("garbage") recover {
      case e: FileNotFoundException => Iterator("Requested page does not exist")
      case e: MalformedURLException => Iterator("Please make sure to enter a valid URL")
      case _ => Iterator("An unexpected error has occurred. We are so sorry!")
    }
    println(content)
    // RESULT: Success(non-empty iterator)

  }
}
