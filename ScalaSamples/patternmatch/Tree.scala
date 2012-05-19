package org.nicholasren.scala.patternmatch

abstract class Tree

/**
 * Tree which will evaluate the summary of its sub tree
 */
case class Sum(l: Tree, r: Tree) extends Tree

/**
 * 
 *Tree which will multiply its sub tree 
 *
 */
case class Multiply(l: Tree, r:Tree) extends Tree

/**
 * Tree with a variable
 */
case class Var(n: String) extends Tree

/**
 * Tree with a constant
 *
 */
case class Const(v: Int) extends Tree

