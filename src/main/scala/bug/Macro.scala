package bug

import scala.quoted._

object Macro {

  inline def funMac(tree: Any): Unit = ${ funMacImpl('tree) }
  def funMacImpl(tree: Expr[Any])(using qctx: QuoteContext): Expr[Unit] = {
    import qctx.tasty.{given _, _}

    tree.unseal.underlyingArgument.seal match {
        case vv @ '{ FunObject.fun(($arg: String) => $out) } =>
          println("======= MATCHED ======")
          println(vv.show)
          //println(arg.show) // Causes and error: Not found: arg
          println(out.show)

        case _ => 
          println("====== NOT MATCHED ======")
    }

    '{ () }
  }
}

object FunObject {
  def fun(t: String => String) = t
}
