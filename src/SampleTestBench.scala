/**
 * This is a sample testbench that someone would write
 * to test a hardware design generated by Java Essent.
 */

object SampleTestBench {
  def main(args : Array[String]): Unit = {
    val sim = Compiler.compile(args(0))
    sim.poke("io_a", 7)
    sim.poke("io_b", 10)
    sim.step()
    println(sim.peek("io_z"))
    sim.step()
    println(sim.peek("io_z"))
    sim.step()
    println(sim.peek("io_z"))
  }
}
