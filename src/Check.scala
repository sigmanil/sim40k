import scala.util.Random

class Die(val sides: Int = 6) {
  val numgen = new Random()
  val roll = if (sides == 0) { 0 } else { numgen.nextInt(sides)+1 }
}

class Check(val targetNumber: Int) {
  val die = new Die()
  def success: Boolean = die.roll != 1 && die.roll >= targetNumber
}

