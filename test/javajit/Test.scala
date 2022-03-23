package javajit

import org.scalatest.freespec.AnyFreeSpec

class Test extends AnyFreeSpec {

  val src =
    """import javajit.Simulator;
      |
      |public class GCD implements Simulator {
      |  @Override public void poke(String var, int val) {
      |  }
      |
      |  @Override public int peek(String var) {
      |    return -1;
      |  }
      |
      |   @Override public void step() {
      |   }
      |}
      |
      |""".stripMargin

  "compiler helper should return the current class path" in {
    val classPath = CompilerHelpers.getClassPath
    println("The class path is:")
    println(classPath.mkString("\n"))
  }

  "compile and load java code" in {
    val sim = Compiler.compileCode(src, "GCD")
    assert(sim.peek("123") == -1)
  }

}
