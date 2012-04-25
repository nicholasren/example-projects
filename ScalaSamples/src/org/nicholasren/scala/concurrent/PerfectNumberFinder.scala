package org.nicholasren.scala.concurrent

object PerfectNumberFinder {

  def sumOfFactors(number: Int) = { (0 /: (1 to number)) { (sum, i) => if (number % i == 0) sum + i else sum } }

  def sumOfFactors2(number: Int) = { (1 to number).foldLeft(0) { (sum, i) => if (number % i == 0) sum + i else sum } }

  
  def sum(from : Int, to : Int) = {(from to to).foldLeft(0){(sum, i) => sum + i}}
  
  def sum2(from : Int, to : Int) = {(from to to).foldRight(0){(sum, i) => sum + i}}
  
  
  def isPerfect(candidate: Int) = 2 * candidate == sumOfFactors2(candidate)

  def main(args: Array[String]) {
    println(" 33550336 is perfect ? " + isPerfect(33550336));
    println("Sum of numbers between 0 to 5 = " + sum2(0, 5))
  }

}