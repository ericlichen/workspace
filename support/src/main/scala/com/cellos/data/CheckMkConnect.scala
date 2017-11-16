package com.cellos.data.support

/**
  * @author ${user.name}
  */

import util.Properties


case class CheckMkCheck(name: String,
                   varName: String,
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

    isValid = (hasSpace.findFirstIn(name).isEmpty && hasSpace.findFirstIn(varName).isEmpty)

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

  def status(): String = {
    // The check result in Nagios convention: 0 for OK, 1 for WARNING, 2 for CRITICAL and 3 for UNKNOWN
    checkValue match {
      case checkValue if (checkValue < warn) => "0"
      case checkValue if (checkValue >= warn && checkValue < crit) => "1"
      case checkValue if (checkValue >= crit) => "2"
      case _ => "3"
    }

  }

  isValidName()
  isValidValue()

}

class CheckMkPluginOutput (checks:Vector[CheckMkCheck]) {
  /*
  Output contains one or more lines with four space seperated columns:
    Status
    Item Name
    Performance data
    Check output
   */

  def checkOutput(check:CheckMkCheck):String = {
    (check.status + ' ' 
      + check.name + ' ' 
      + check.varName + '=' 
      + check.checkValue + ';' 
      + check.warn + ';' 
      + check.crit + ';' 
      + check.min + ';' 
      + check.max + ' '
      + check.output)
  }

  def checkCount():Int = {
    checks.length
  }

  def pluginOutput():String = {
    
    var output = ""

    for (check <- checks) {
      output = output + (checkOutput(check)) + Properties.lineSeparator
    }

    output
  }
  
}