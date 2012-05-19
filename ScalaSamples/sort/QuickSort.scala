/**
 * Created by IntelliJ IDEA.
 * User: nicholasren
 * Date: 11-7-16
 * Time: 下午2:44
 */

object QuickSort
{
   def quicksort[T](less: (T, T) => Boolean)(xs: List[T]):List[T]= xs match
   {
     case List() => List()
     case y::ys => quicksort(less)(ys.filter( a => less(a, y))) ::: List(y) ::: quicksort(less)(ys.filter( b => !less(b, y)))
   }

  def main(args : Array[String])
  {
    val xs = List(4, 5, 8, 1, 10);
    val sorted = quicksort((x: Int, y: Int) => x < y)(xs);
    print (sorted)
  }
}