package westheide

/**
  * from: http://danielwestheide.com/blog/2012/12/12/the-neophytes-guide-to-scala-part-4-pattern-matching-anonymous-
  * functions.html
  */
object E4_Pattern_Matching_Anonymous_Functions {

  def main(args: Array[String]): Unit = {

    val songTitles = List("The White Hare", "Childe the Hunter", "Take no Rogues")
    songTitles.map(t => t.toLowerCase)
    // or
    songTitles.map(_.toLowerCase)

    // Our initial solution makes use of the filter and map methods, passing anonymous functions to them using our
    // familiar syntax

    val wordFrequencies = ("habitual", 6) :: ("and", 56) :: ("consuetudinary", 2) ::
      ("additionally", 27) :: ("homely", 5) :: ("society", 13) :: Nil

    def wordsWithoutOutliers(wordFrequencies: Seq[(String, Int)]): Seq[String] =
      wordFrequencies.filter(wf => wf._2 > 3 && wf._2 < 25).map(_._1)

    println(wordsWithoutOutliers(wordFrequencies))
    // RESULT: List(habitual, homely, society)

    def wordsWithoutOutliers2(wordFrequencies: Seq[(String, Int)]): Seq[String] =
      wordFrequencies.filter { case (_, f) => f > 3 && f < 25 } map { case (w, _) => w }

    println(wordsWithoutOutliers2(wordFrequencies))
    // RESULT: List(habitual, homely, society)

    val predicate: ((String, Int)) => Boolean = { case (_, f) => f > 3 && f < 25 }
    val transformFn: ((String, Int)) => String = { case (w, _) => w }

    println(predicate("habitual", 6))
    // RESULT: true
    println(transformFn("habitual", 6))
    // RESULT: habitual

    /**
      * Partial functions
      */

    val pf: PartialFunction[(String, Int), String] = {
      case (word, freq) if freq > 3 && freq < 25 => word
    }

    println(pf("habitual", 6))
    // RESULT: habitual

    val pf2 = new PartialFunction[(String, Int), String] {
      def apply(wordFrequency: (String, Int)) = wordFrequency match {
        case (word, freq) if freq > 3 && freq < 25 => word
      }
      def isDefinedAt(wordFrequency: (String, Int)) = wordFrequency match {
        case (word, freq) if freq > 3 && freq < 25 => true
        case _ => false
      }
    }
    println(pf2.apply("habitual", 6))
    // RESULT: habitual
    println(pf2.isDefinedAt("habitual", 6))
    // RESULT: true

    def wordsWithoutOutliers3(wordFrequencies: Seq[(String, Int)]): Seq[String] =
      wordFrequencies.collect { case (word, freq) if freq > 3 && freq < 25 => word }

    println(wordsWithoutOutliers3(wordFrequencies))
    // RESULT: List(habitual, homely, society)



  }
}
