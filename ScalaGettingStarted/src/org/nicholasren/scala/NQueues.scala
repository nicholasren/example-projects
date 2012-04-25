package org.nicholasren.scala

object NQueues {
	
	def main(args: Array[String]) {
       val result = queens(8);
       result map(println);
       println(result.length);
    }
	
  def queens(n: Int): List[List[Int]] = {
    def placeQueens(k: Int): List[List[Int]] =
      if (k == 0) List(List())
      else for {
        queens <- placeQueens(k - 1)
        column <- List.range(1, n + 1)
        if isSafe(column, queens, 1)
      } yield column :: queens
    placeQueens(n)
  }

  def isSafe(col: Int, queens: List[Int], delta: Int): Boolean =
    {
       var idx = 0;
       while(idx < queens.size)
       {
    	   if(queens(idx) == col)
    	     {
    	  	   return false;
    	     }
    	   idx += 1;
       }
       
       return true;
    }

}