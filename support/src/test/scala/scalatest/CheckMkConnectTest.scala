import org.scalatest.FlatSpec
import com.cellos.data.support._

class CheckMkConnectSpec extends FlatSpec {
 
 "A CheckMkCheck" should "initialized with value" in {
    val check = CheckMkCheck(name="totalBytes", varName="speed", checkValue=2, warn=4, crit=6, min=1, max=10)
    assert(check.checkValue === 2)
  }

 it should "throw IllegalArgumentException if check name has space" in {
    assertThrows[IllegalArgumentException] {
      val check = CheckMkCheck(name="total Bytes", varName="speed", checkValue=2, warn=4, crit=6, min=1, max=10)
    }
  }
 it should "throw IllegalArgumentException if var name has space" in {
    assertThrows[IllegalArgumentException] {
      val check = CheckMkCheck(name="total Bytes", varName="sp ed", checkValue=2, warn=4, crit=6, min=1, max=10)
    }
  }
 it should "throw IllegalArgumentException if min greater than max value " in {
    assertThrows[IllegalArgumentException] {
      val check = CheckMkCheck(name="totalBytes", varName="speed", checkValue=2, warn=4, crit=6, min=20, max=10)
    }
    assertThrows[IllegalArgumentException] {
      val check = CheckMkCheck(name="totalBytes", varName="speed", checkValue=2, warn=4, crit=6, min=10, max=10)
    }
  }
 it should "throw IllegalArgumentException if warn greater than crit or warn less than min" in {
    assertThrows[IllegalArgumentException] {
      val check = CheckMkCheck(name="totalBytes", varName="speed", checkValue=15, warn=7, crit=6, min=20, max=10)
    }
    assertThrows[IllegalArgumentException] {
      val check = CheckMkCheck(name="totalBytes", varName="speed", checkValue=10, warn=2, crit=16, min=10, max=10)
    }
  }
 it should "throw IllegalArgumentException if crit less than warn or crit great than max" in {
    assertThrows[IllegalArgumentException] {
      val check = CheckMkCheck(name="totalBytes", varName="speed", checkValue=2, warn=6, crit=6, min=0, max=5)
    }
    assertThrows[IllegalArgumentException] {
      val check = CheckMkCheck(name="totalBytes", varName="speed", checkValue=1, warn=3, crit=2, min=0, max=5)
    }
  }
 it should "throw IllegalArgumentException if check value is not between min and max" in {
    assertThrows[IllegalArgumentException] {
      val check = CheckMkCheck(name="totalBytes", varName="speed", checkValue=20, warn=3, crit=6, min=0, max=10)
    }
    val check1 = CheckMkCheck(name="totalBytes", varName="speed", checkValue=0, warn=3, crit=6, min=0, max=10)
    assert(check1.isValidName() == true)
    assert(check1.isValidValue() == true)
     
    val check2 = CheckMkCheck(name="totalBytes", varName="speed", checkValue=10, warn=3, crit=6, min=0, max=10)
    assert(check2.isValidName() == true)
    assert(check2.isValidValue() == true)
 }
 "A CheckMkPluginOutput" should "initialize with a CheckMkCheck vector" in {
    val check1 = CheckMkCheck(name="totalBytes", varName="Bytes", checkValue=90, warn=100, crit=200, min=0, max=250, output="output1")
    val check2 = CheckMkCheck(name="totalUsers", varName="Count", checkValue=5, warn=10, crit=20, min=0, max=25,output="output2")
    val checks = Vector(check1, check2)
    val pluginoutput = new CheckMkPluginOutput(checks)
    print(pluginoutput.pluginOutput()) 
    //assert(output.pluginOutput().stripLineEnd == """0 totalBytes Bytes=90.0;100.0;200.0;0.0;250.0 output2""")

 }
}
