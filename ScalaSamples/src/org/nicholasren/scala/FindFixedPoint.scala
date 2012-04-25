package org.nicholasren.scala

object FindFixedPoint {

  val tolerance = 0.0001

  def isCloseEnough(x: Double, y: Double) = Math.abs((x - y) / x) < tolerance

  def fixedPoint(f: Double => Double)(firstGuess: Double) = {
    def iterate(guess: Double): Double = {
      val next = f(guess)
      println(next)
      if (isCloseEnough(guess, next)) next
      else iterate(next)

    }
    iterate(firstGuess)
  }

  def sqrt(x: Double) = fixedPoint(y => (y + x/y) / 2)(1.0)

  def main(args: Array[String]) {
    println("sqrt(2):" + sqrt(2.0));
  }
}