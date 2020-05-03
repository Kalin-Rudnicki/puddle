package puddle.handling

import scala.language.implicitConversions

import puddle.errs._
import puddle.errs.expected._

// TODO : Use (=> V) wherever possible
sealed trait ??[+V] {

  // =====| Map |=====

  // Abstract

  def map[V2](f: V => V2): ??[V2]

  def flatMap[V2](f: V => ??[V2]): ??[V2]

  def handleMap[V2](f: V => V2): ??[V2]

  def ensure(predicate: V => Boolean)(error: _Err): ??[V]

  def ensure(predicate: V => Boolean)(implicit converter: V => _Err): ??[V]


  // =====| Converters |=====

  def toBoolean: Boolean

  def toOption: Option[V]

  def toEither: Either[_Err, V]


  // =====| Access |=====

  def getOrElse[V2 >: V](dflt: V2): V2

  def unsafeGet: V

}

final case class Value[V](value: V) extends ??[V] {

  // =====| Map |=====

  override def map[V2](f: V => V2): ??[V2] =
    Value[V2](f(value))

  override def flatMap[V2](f: V => ??[V2]): ??[V2] =
    f(value)

  override def handleMap[V2](f: V => V2): ??[V2] =
    ??? // TODO : Implement handler

  override def ensure(predicate: V => Boolean)(error: _Err): ??[V] =
    if (predicate(value))
      this
    else
      error

  override def ensure(predicate: V => Boolean)(implicit converter: V => _Err): ??[V] =
    ensure(predicate)(converter(value))


  // =====| Converters |=====

  override def toBoolean: Boolean =
    true

  override def toOption: Option[V] =
    Some[V](value)

  override def toEither: Either[Nothing, V] =
    Right[Nothing, V](value)


  // =====| Access |=====

  override def getOrElse[V2 >: V](dflt: V2): V2 =
    value

  override def unsafeGet: V =
    value

}

final case class Error(error: _Err) extends ??[Nothing] {

  // =====| Map |=====

  override def map[V2](f: Nothing => V2): ??[V2] =
    this

  override def flatMap[V2](f: Nothing => ??[V2]): ??[V2] =
    this

  override def handleMap[V2](f: Nothing => V2): ??[V2] =
    this
  
  override def ensure(predicate: Nothing => Boolean)(error: _Err): ??[Nothing] =
    this

  override def ensure(predicate: Nothing => Boolean)(implicit converter: Nothing => _Err): ??[Nothing] =
    this
  

  // =====| Converters |=====

  override def toBoolean: Boolean =
    false

  override def toOption: Option[Nothing] =
    None

  override def toEither: Either[_Err, Nothing] =
    Left[_Err, Nothing](error)


  // =====| Access |=====

  override def getOrElse[V2 >: Nothing](dflt: V2): V2 =
    dflt

  override def unsafeGet: Nothing =
    throw new NoSuchElementException

  
  // =====| Other |=====
  override def toString: String =
    s"Error: $error"
  
}
