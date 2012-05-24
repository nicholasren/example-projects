class Integer
  def prime?
    return true if self <= 2
    return false if self & 1 == 0
    (3..(Math.sqrt(self).to_i)).step(2).none? { |i| self%i == 0 }
  end
end

def firt_n_prime n
  primes = []
  i = 2
  while primes.length < n do
    primes << i if i.prime?
    i += 1
  end
  primes
end

puts firt_n_prime(20).join(', ')
