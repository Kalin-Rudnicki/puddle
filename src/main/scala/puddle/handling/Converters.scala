package puddle.handling

import scala.language.implicitConversions

import puddle.errs._Err

implicit def convertErrToError(error: _Err): Error =
  Error(error)

implicit def convertV[V](v: V): ??[V] =
  Value(v)
