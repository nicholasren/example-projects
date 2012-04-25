package org.nicholasren.scala

/**
 * Generic
 * @author administrator
 * @param T 类型参数
 */
class Reference[T] {

  /**
   * T的默认值是 '_',表示各种类型的默认值  
   */
  private var contents: T = _
  
  def set(value: T) { contents = value }
  
  def get: T = contents

}