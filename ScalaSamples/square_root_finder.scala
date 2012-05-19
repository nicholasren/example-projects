//lazy evluation
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



def sqrt(n:Float, eps:Float):Float={
  within(eps, repeat(next(n, _:Float), 1))
}

println(sqrt(9, 0.00000001F))
