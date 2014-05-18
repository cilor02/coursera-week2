package funsets

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * This class is a test suite for the methods in object FunSets. To run
 * the test suite, you can either:
 *  - run the "test" command in the SBT console
 *  - right-click the file in eclipse and chose "Run As" - "JUnit Test"
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {


  /**
   * Link to the scaladoc - very clear and detailed tutorial of FunSuite
   *
   * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
   *
   * Operators
   *  - test
   *  - ignore
   *  - pending
   */

  /**
   * Tests are written using the "test" operator and the "assert" method.
   */
  test("string take") {
    val message = "hello, world"
    assert(message.take(5) == "hello")
  }

  /**
   * For ScalaTest tests, there exists a special equality operator "===" that
   * can be used inside "assert". If the assertion fails, the two values will
   * be printed in the error message. Otherwise, when using "==", the test
   * error message will only say "assertion failed", without showing the values.
   *
   * Try it out! Change the values so that the assertion fails, and look at the
   * error message.
   */
  test("adding ints") {
    assert(1 + 2 === 3)
  }

  
  import FunSets._

  test("contains is implemented") {
    assert(contains(x => true, 100))
  }
  
  /**
   * When writing tests, one would often like to re-use certain values for multiple
   * tests. For instance, we would like to create an Int-set and have multiple test
   * about it.
   * 
   * Instead of copy-pasting the code for creating the set into every test, we can
   * store it in the test class using a val:
   * 
   *   val s1 = singletonSet(1)
   * 
   * However, what happens if the method "singletonSet" has a bug and crashes? Then
   * the test methods are not even executed, because creating an instance of the
   * test class fails!
   * 
   * Therefore, we put the shared values into a separate trait (traits are like
   * abstract classes), and create an instance inside each test method.
   * 
   */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val multiplesOf2 = (x:Int) => x%2 == 0          
    val multiplesOf3 = (x:Int) => x%3 == 0          
    val multiplesOf5 = (x:Int) => x%5 == 0
    val multiplesOf10 = (x:Int) => x%10 == 0          
    val negativeNum  = (x:Int) => x < 0             
    val lessThan10   = (x:Int) => x < 10            
    val moreThan10   = (x:Int) => x < 10            
    val factorOf20   = (x:Int) => x match {case 0 =>false case _ =>20%x  == 0 }                                                 
    val factorOf100  = (x:Int) => x match {case 0 =>false case _ =>100%x == 0 }                                                 
    val factorOf144  = (x:Int) => x match {case 0 =>false case _ =>144%x == 0 }
  }

  /**
   * This test is currently disabled (by using "ignore") because the method
   * "singletonSet" is not yet implemented and the test would fail.
   * 
   * Once you finish your implementation of "singletonSet", exchange the
   * function "ignore" by "test".
   */
  test("singletonSet(1) contains 1") {
    
    /**
     * We create a new instance of the "TestSets" trait, this gives us access
     * to the values "s1" to "s3". 
     */
    new TestSets {
      /**
       * The string argument of "assert" is a message that is printed in case
       * the test fails. This helps identifying which assertion failed.
       */
      assert(contains(s1, 1), "Singleton")
    }
  }

  test("union contains all elements") {
    new TestSets {
      val s = union(s1, s2)
      assert(contains(s, 1), "Union 1")
      assert(contains(s, 2), "Union 2")
      assert(!contains(s, 3), "Union 3")
    }
  }
  
    test("diff between multiples of 5 and multiples of 2 ") {
    new TestSets {
      assert(!diff(multiplesOf5,multiplesOf2)(27),"does not contain 27") 
      assert(diff(multiplesOf5,multiplesOf2)(35),"contains 35")
      assert(!diff(multiplesOf5,multiplesOf2)(30),"does not contain 30") 
    }    
  }
    
    test("intersect of multiples of 5 and multiples of 2 ") {
    new TestSets {
      assert(!intersect(multiplesOf5,multiplesOf2)(25),"does not contain 25") 
      assert(intersect(multiplesOf5,multiplesOf2)(30),"contains 30") 
    }
}
    
  test("union of multiples of 5 and multiples of 2 ") {
    new TestSets {
      assert(!union(multiplesOf5,multiplesOf2)(29),"does not contain 29") 
      assert(union(multiplesOf5,multiplesOf2)(25),"contains 25") 
    }
}
    
    
  test("map  multiples of 5 mapped => x + 2 ") {
    new TestSets {
      assert(map(multiplesOf5, (x)=> x+2)(22),"contains 22")
      assert(map(multiplesOf5, (x)=> x+2)(27),"contains 27")
      assert(!map(multiplesOf5, (x)=> x+2)(20),"does not contain 20")
      assert(!map(multiplesOf5, (x)=> x+2)(35),"does not contain 35")
    }
} 
  
  
      
  test("misc mapping tests") {
    new TestSets {
      assert(map(multiplesOf5, (x)=> x*x)(225),"contains 225")
      assert(!map(multiplesOf5, (x)=> x*x)(35),"does not contain 35")
      assert(map(multiplesOf5, (x)=> (x+1)*x)(30),"contains 30")
      assert(!map(singletonSet(7), (x)=> x*x)(35),"does not contain 35")
      assert(map(singletonSet(7), (x)=> x*x)(49),"contain 49")
    }
}
  
  test("various contains test") {
    new TestSets {
      assert(!contains( multiplesOf5,21), "doesn't contain 21")
      assert(contains( multiplesOf5,200), "contains 200")
      assert(!contains( multiplesOf3,25), "doesn't contain 25")
      assert(contains( multiplesOf3,6393), "contains 6963")
    }
}  

  test("filter tests") {
    new TestSets {
 assert(!filter(multiplesOf5,(x)=> x < 100)(100), "doesn't contain 100")
 assert(filter(multiplesOf5,(x)=> x < 100)(90), "contains 90")
 assert(filter(multiplesOf5,(x)=> x < 100)(-100), "contains -100")
 assert(filter(multiplesOf5,(x)=> x % 2 == 0)(90), "contains 90")
 assert(!filter(multiplesOf5,(x)=> x % 2 == 0)(95), "does not contain 95")
  }
 }
  

  test("exists tests") {
    new TestSets {
 assert(!exists(factorOf20,(x)=> x == 109), "109 does not exist in this set")
 assert(exists(factorOf144,(x)=> x % 4 == 0), "a factor of 4 exists")
 assert(!exists(factorOf100,(x)=> x % 3 == 0), "a factor of 3 doesn't exist")
  }
 }  
  
 
  test("forall tests") {
    new TestSets {
 assert(forall(multiplesOf10,(x)=> x %5 == 0), "all multiples of 10 divisible by 5")
 assert(!forall(multiplesOf5,(x)=> x %10 == 0), "not all multiples of 5 divisible by 10")
  }
 }  
}