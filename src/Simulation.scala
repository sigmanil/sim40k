

object Simulation extends App {
  
  val pulseLaser =    Weapon(3, 8, -3, 0, 3)
  val starCannon =    Weapon(3, 6, -3, 3, 0)
  val aml =           Weapon(3, 8, -2, 6, 0)
  val focussedPrism = Weapon(3, 9, -4, 3, 0)
  val doomWeaver =    Weapon(3, 7, 0, 0, 2, -4)
  
  val landRaider =    Armor(8, 2, 7, 7)
  
  val attempts = 1000000
  val starcannonFalconVsLandRaider = (0 until attempts).map { _ =>
    val attack1 = new Attack(pulseLaser, landRaider)
    val attack2 = new Attack(pulseLaser, landRaider)
    val attack3 = new Attack(starCannon, landRaider)
    val attack4 = new Attack(starCannon, landRaider)

    if (!attack1.attackSuccess) {
      attack1.rerollAttack
    } else if (!attack2.attackSuccess) {
      attack2.rerollAttack
    } else if (!attack3.attackSuccess) {
      attack3.rerollAttack
    } else if (!attack4.attackSuccess) {
      attack4.rerollAttack
    }
    
    if (attack1.attackSuccess && !attack1.woundSuccess) {
      attack1.rerollWound
    } else if (attack2.attackSuccess && !attack2.woundSuccess) {
      attack2.rerollWound
    } else if (attack3.attackSuccess && !attack3.woundSuccess) {
      attack3.rerollWound
    } else if (attack4.attackSuccess && !attack4.woundSuccess) {
      attack4.rerollWound
    }
   
    attack1.damage + attack2.damage + attack3.damage + attack4.damage
  }.sum.toDouble / attempts.toDouble

  val amlFalconVsLandraider = (0 until attempts).map { _ =>
    val attack1 = new Attack(pulseLaser, landRaider)
    val attack2 = new Attack(pulseLaser, landRaider)
    val attack3 = new Attack(aml, landRaider)

    if (!attack1.attackSuccess) {
      attack1.rerollAttack
    } else if (!attack2.attackSuccess) {
      attack2.rerollAttack
    } else if (!attack3.attackSuccess) {
      attack3.rerollAttack
    }
    
    if (attack1.attackSuccess && !attack1.woundSuccess) {
      attack1.rerollWound
    } else if (attack2.attackSuccess && !attack2.woundSuccess) {
      attack2.rerollWound
    } else if (attack3.attackSuccess && !attack3.woundSuccess) {
      attack3.rerollWound
    }
   
    attack1.damage + attack2.damage + attack3.damage
  }.sum.toDouble / attempts.toDouble
  
  val focussedFirePrismVsLandraider = (0 until attempts).map { _ =>
    val twoDeeThree = (new Die(3)).roll + (new Die(3)).roll 
        
    (0 until twoDeeThree).map { _ =>
      val attacked = new Attack(focussedPrism, landRaider)
      if (!attacked.attackSuccess) {
        attacked.rerollAttack
      }
      if (!attacked.woundSuccess) {
        attacked.rerollWound
      }
      attacked.damage
    }.sum
  }.sum.toDouble / attempts.toDouble
  
  val nightSpinnerVsLandRaider = (0 until attempts).map { _ =>
    val d6 = (new Die(6)).roll + (new Die(6)).roll 
        
    (0 until d6).map { _ =>
      val attacked = new Attack(doomWeaver, landRaider)
      
      attacked.damage
    }.sum
  }.sum.toDouble / attempts.toDouble
   
  System.err.println("Falcon with star cannon and rerolls " + starcannonFalconVsLandRaider)
  System.err.println("Falcon with AML and rerolls " + amlFalconVsLandraider)
  System.err.println("Focussed prism with rerolls " + focussedFirePrismVsLandraider)
  System.err.println("Nightspinner " + nightSpinnerVsLandRaider)
}
