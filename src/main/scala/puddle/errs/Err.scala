package puddle.errs

import puddle.errs.expected.Err

sealed trait _Err(val message: String) {
  
  override def toString: String =
    s"${this.getClass.getSimpleName} -> $message"
    
  
}

sealed trait SimpleErrToString {
  
  def name: String
  def _message: String
  
  override def toString: String =
    s"$name -> ${_message}"
  
}

object expected {
  
  abstract class Err(message: String) extends _Err(message)
  
  case class BasicErr(_message: String) extends Err(_message)
  
  case class SimpleErr(name: String, _message: String) extends Err(s"$name : ${_message}") with SimpleErrToString
  
  case class EnsureErr(value: Any) extends Err(s"Failed to pass ensure predicate : $value")
  
  case class HandledErr(ex: Throwable) extends Err(ex.getMessage)
  
}
object unexpected {

  abstract class Err(message: String) extends _Err(message)

  case class BasicErr(_message: String) extends Err(_message)

  case class SimpleErr(name: String, _message: String) extends Err(s"$name -> ${_message}") with SimpleErrToString
  
  case class UnhandledErr(ex: Throwable) extends Err(ex.getMessage)
  
  // TODO : Case class or case object?
  //      : I have a feeling case object will not probably handle stack trace
  case class UnimplementedErr() extends Err("This method/function is yet to be implmeneted")
  
  def ???! : _Err = UnimplementedErr()
  
}
