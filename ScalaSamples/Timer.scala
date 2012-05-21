object Timer {

  /**
  * 这个方法接受一个名字为callback，入参为空，返回值为Unit的函数<br>
  * 然后每隔一秒钟执行一次callback<br>
  */
  def oncePerSecond(callback: () => Unit) 
    {
    while (true) { callback(); Thread sleep 1000 }
  }


  def timeFlies() {
    println("time flies like an arrow...")
  }


}

def doSomething(){
  println("do something...")
}

Timer.oncePerSecond(doSomething)
