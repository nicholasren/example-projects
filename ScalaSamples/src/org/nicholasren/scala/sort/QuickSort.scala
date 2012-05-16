//declaritive version
def quicksort[T](less: (T, T) => Boolean)(xs: List[T]):List[T]= xs match
{
  case List() => List()
  case y::ys => 
    quicksort(less)(ys.filter( a => less(a, y)))::: List(y) ::: quicksort(less)(ys.filter( b => !less(b, y)))
}

//instructive version
def quicksort2[T](less: (T, T) => Boolean, xs: Array[T], low:Int, high:Int):Unit={
  var l = low
  var h = high
  val pivot_idx = l + (h - l) / 2
  if (l >= h)
    return
  val pivot = xs(pivot_idx)

    while(l < h && less(xs(l), pivot))
    l += 1
  while(h > l && !less(xs(h), pivot))
    h -= 1

  if (l < h) {
    var tmp = xs(l) 
      xs(l) = xs(h)
    xs(h) = tmp
  }

  if( l > h){
    val tmp = l
    h = l
    l = tmp
  }

  quicksort2(less, xs, low, l-1)
  quicksort2(less, xs, l+1, high)
}


val ys = Array(7, 3, 4, 29, 11, 98)
val xs = ys.toList 
val less = (x:Int, y:Int) => { x < y}

val s = System.nanoTime
val sorted1 = quicksort[Int](less)(xs)
println("functional time cost: " + (System.nanoTime - s) / 1000)

val e = System.nanoTime
quicksort2[Int](less, ys, 0, xs.length - 1)
println("instructive time cost: " + (System.nanoTime - e) / 1000)
