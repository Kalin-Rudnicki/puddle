package puddle.handling

import scala.language.implicitConversions

import puddle.errs._
import puddle.errs.expected.HandledErr

def handleAll[V](v: => V): ??[V] =
try {
  v
} catch {
  case ex: Throwable =>
  HandledErr(ex)
}
