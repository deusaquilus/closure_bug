package bug

import Macro._

@main def testOtherstuff() = {
  val v = Option("foo")
  funMac(FunObject.fun(s => s.toUpperCase))
}