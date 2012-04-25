package org.nicholasren.scala.concurrent
import scala.actors.Actor._

object FasterPerfectNumberFinder {
  def sumOfFactorsInRange(lower: Int, upper: Int, number: Int) =
    {
      (0 /: (lower to upper)) { (sum, i) => if (number % i == 0) sum + i else sum }
    }

  def isPerfectConcurrent(candidate: Int) = {
    val RANGE = 1000000
    val numberOfPartitions = (candidate.toDouble / RANGE).ceil.toInt

    var caller = self

    for (i <- 0 until candidate) {
      var lower = i * RANGE + 1
      var upper = candidate min (i + 1) * RANGE

      actor {
        caller ! sumOfFactorsInRange(lower, upper, candidate)
      }

    }

    val sum = (0 /: (0 until numberOfPartitions)) { (partialSum, i) =>
      receive {
        case sumInRange: Int => partialSum + sumInRange
      }
    }

    2 * candidate == sum
  }

  def main(args: Array[String]) {

    println("Start");
    val start = System.nanoTime
    println(" 6 is perfect ? " + isPerfectConcurrent(6));
    val end = System.nanoTime
    println("End, took " + (end - start)/1000000000.0) + "secs";
  }
}