
import scala.language.implicitConversions

import puddle.errs.expected.SimpleErr
import puddle.exports.core._

object Main {

  def add(i1: Int, i2: Int): Int =
    i1 + i2
  
  def div(i1: Int, i2: Int): ??[Int] =
    if (i2 == 0)
      SimpleErr("Div/0", s"Cannot divide $i1 by 0")
    else
      i1 / i2
  
  def div2(i1: Int, i2: Int): ??[Int] =
    ???!
  
  def x10(i: Int): Int =
    i * 10
  
  def main(args: Array[String]): Unit = {
    
    println(add <* 10 <* 5)
    println
    println(div <* 10 <* 5)
    println(div <<* 10 <<* 5)
    println
    println(div <* 10 <* 0)
    println(div <<* 10 <<* 0)
    
  }
  
}
