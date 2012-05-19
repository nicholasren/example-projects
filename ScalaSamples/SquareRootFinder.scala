object FindFixedPoint {
  val tolerance = 0.0001

  def isCloseEnough(x: Double, y: Double) = Math.abs((x - y) / x) < tolerance

  def fixedPoint(f: Double => Double)(firstGuess: Double) = {
    def iterate(guess: Double): Double = {
      val next = f(guess)
      if (isCloseEnough(guess, next)) next
      else iterate(next)

    }
    iterate(firstGuess)
  }

  def sqrt(x: Double) = fixedPoint(y => (y + x/y) / 2)(1.0)
}

//lazy evluation
object LazySquareRootFinder{
  def next (n:Float, x:Float):Float={
    (x + n/x)/2
  }

  def repeat(f:(Float => Float), a:Float):Stream[Float]={
    Stream.cons(a, repeat(f, f(a)))
  }

  def within(eps:Float, xs:Stream[Float]):Float={
    if (math.abs(xs.tail.head - xs.head) < eps)
      xs.head
    else
      within(eps, xs.tail)
  }

  def sqrt(n:Float, eps:Float = 0.00000001F):Float={
    within(eps, repeat(next(n, _:Float), 1))
  }
}

println("sqrt(9)= "+ FindFixedPoint.sqrt(9))
println("lazy sqrt(9)= " + LazySquareRootFinder.sqrt(9))
