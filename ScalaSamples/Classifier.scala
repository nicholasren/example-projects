def factorsOf (n:Int) = {
  (1 to n).toList.filter{ n % _ == 0}
}


def sumOfFactors(n:Int) = {
  factorsOf(n).foldLeft(0) {(_:Int) + (_:Int)}
}

def isPerfect(n:Int):Boolean = {
  (sumOfFactors(n) - n) == n
}

def isAbundant(n:Int):Boolean = {
  (sumOfFactors(n) - n) > n
}

def isDeficient(n:Int):Boolean = {
  (sumOfFactors(n) - n) < n
}

println("6 is perfect number? " + isPerfect(6))
println("7 is perfect number? " + isPerfect(7))

