trait Simulator {
  def poke(variable : String, value : Int): Unit

  def peek(variable : String): Int

  def step(): Unit

  def makeMap(): Unit
}
