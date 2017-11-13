package com.cellos.data.support

/**
  * @author ${user.name}
  */

case class CheckMkCheck(name: String,
                   checkValue: Double,
                   warn: Double,
                   crit: Double,
                   min: Double,
                   max: Double,
                   output: String = ""
                  ) {

  def isValidName(): Boolean = {
    var isValid = false
    val hasSpace = " ".r

    isValid = !hasSpace.findFirstIn(name).isEmpty
    if (!isValid) {
      throw new IllegalArgumentException("""name can not have space.""")
    }
    isValid
  }

  def isValidValue(): Boolean = {

    var isValid = false

    // Check if violate any rules, if not change isValid to true
    if (min > max) {
      throw new IllegalArgumentException("""min need to be less than or equals to max""")
    }
    if ((warn > crit) || (warn < min)) {
      throw new IllegalArgumentException("""warn need to be smaller than crit and greater than min""")
    }
    if ((crit < warn) || (crit > max)) {
      throw new IllegalArgumentException("""crit need to be greater than warn and smaller than max""")
    }
    if (checkValue < min || checkValue > max) {
      throw new IllegalArgumentException("""checkValue need to between min and max""")
    }
    isValid = true

    isValid
  }

  isValidName()
  isValidValue()

}

class CheckMkReturn (checks:Vector[CheckMkCheck]) {
  /*
  Output contains one or more lines with four space seperated columns:
    Status
    Item Name
    Performance data
    Check output
   */
}


