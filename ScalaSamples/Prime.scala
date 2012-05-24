def sieve(is: Stream[Int]): Stream[Int] = {
  val h = is.head
  Stream.cons(h, sieve(is filter {_ % h > 0}))
}

val primes = sieve(Stream.from(2))
(primes take 20).foreach { x => print(x + " ") }
