package org.nicholasren.scala.concurrent

object FindPerfectNumberOverRange {

  def countPerfectNumbersInRange(start: Int, end: Int, isPerfectFinder: Int => Boolean) {
    val startTime = System.nanoTime;

    val numberOfPerfectNumbers = (0 /: (start to end)) { (count, candidate) => if (isPerfectFinder(candidate)) count + 1 else count }
    val endTime = System.nanoTime
    println("Found " + numberOfPerfectNumbers + " perfect number in given range, took " + (endTime - startTime) / 1000000000.0 + "secs")   
  }
  
      def main(args: Array[String]) {
    	var startNumber = 33550300
    var endNumber = 33550400
    
    countPerfectNumbersInRange(startNumber, endNumber, PerfectNumberFinder.isPerfect)
    	
    countPerfectNumbersInRange(startNumber, endNumber, FasterPerfectNumberFinder.isPerfectConcurrent)
}
}