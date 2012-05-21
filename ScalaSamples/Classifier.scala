def factorsOf (n:Int) = {
  (1 until n).toList.filter{ n % _ == 0}
}


def sumOfFactors(n:Int) = {
  n == factorsOf(n).foldLeft(0) {(_:Int) + (_:Int)}
}

def isPerfect(n:Int):Boolean = {
  sumOfFactors(n) == n
}


println(isPerfect(6))
println(isPerfect(7))

