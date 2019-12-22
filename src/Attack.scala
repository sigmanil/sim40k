
class Attack(val weapon: Weapon, val armor: Armor) {
  private var attackDie = new Check(weapon.bs)
  private def attackRoll = attackDie.success
  
  val armourTn = if (weapon.s >= armor.t * 2) {
    2
  } else if (weapon.s > armor.t) {
    3
  } else if (weapon.s == armor.t) {
    4
  } else if (weapon.s*2 <= armor.t) {
    6
  } else if (weapon.s < armor.t) {
    5
  } else {
    throw new RuntimeException("Høh")
  }
  
  private var woundRoll = new Check(armourTn).success  
  
  def ap = if (attackDie.die.roll == 6) {
    Math.min(weapon.ap, weapon.rendAp)
  } else {
    weapon.ap
  }
  
  private def saveRoll = new Check(Math.min(armor.invul, armor.save - ap)).success
  private def damageRoll = (new Die(weapon.damageDieSize)).roll + weapon.damageMod
  private def damageAfterFnp = (0 until damageRoll).filter(_ => !new Check(armor.fnp).success).size
  
  def attackSuccess = attackRoll
  def woundSuccess = woundRoll
  
  def success = attackRoll && woundRoll && !saveRoll
  def damage = if (success) {
    damageAfterFnp
  } else {
    0
  }
  
  def rerollAttack {
    attackDie = new Check(weapon.bs)
  }
  
  def rerollWound {
    woundRoll = new Check(weapon.bs).success
  }
}
