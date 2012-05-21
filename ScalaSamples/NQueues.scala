def queues(n: Int):List[List[Int]] = {
  def placeQueues(k: Int):List[List[Int]] = {
    if (k == 0)
      return List(List())
    else
      for {
        queues <- placeQueues(k - 1)
        column <- List.range(1, n + 1)
        if isSafe(column, queues)
      } yield column :: queues
  }
  placeQueues(n)
}


def isSafe(column:Int, queues:List[Int]):Boolean = {
  queues.filter{col => col == column}.isEmpty
}

val result = queues(2);
result map(println);
