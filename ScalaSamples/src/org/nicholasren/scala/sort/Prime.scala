object Primes {
  def primes = {
    sieve(Stream.from(2))
  }

  def sieve(is: Stream[Int]): Stream[Int] = {
    val h = is.head
    Stream.cons(h, sieve(is filter (_ % h > 0)))
  }

  def main(args: Array[String]) {
    println(primes take 10 toList)
  }
}

