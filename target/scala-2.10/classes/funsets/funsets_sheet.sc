package funsets

object funsets_sheet {
  println("Welcome to the Scala worksheet")
    type Set = Int => Boolean

  /**
   * Indicates whether a set contains a given element.
   */
  def contains(s: Set, elem: Int): Boolean = s(elem)

  /**
   * Returns the set of the one given element.
   */
  def singletonSet(elem: Int): Set = {def singleItemSet(x:Int):Boolean = x==elem;singleItemSet}
 
   /**
   * Returns the union of the two given sets,
   * the sets of all elements that are in either `s` or `t`.
   */
  def union(s: Set, t: Set): Set = {def f(x:Int): Boolean = s(x) || t(x);f}

  /**
   * Returns the intersection of the two given sets,
   * the set of all elements that are both in `s` and `t`.
   */
  def intersect(s: Set, t: Set): Set = {def f(x:Int): Boolean = s(x) && t(x);f}

  /**
   * Returns the difference of the two given sets,
   * the set of all elements of `s` that are not in `t`.
   */
  def diff(s: Set, t: Set): Set = {def f(x:Int): Boolean = s(x) && !t(x);f}
   /*
   * The bounds for `forall` and `exists` are +/- 1000.
   */
  val bound = 1000

  /**
   * Returns whether all bounded integers within `s` satisfy `p`.
   */
  def forall(s: Set, p: Int => Boolean): Boolean = {
    def iter(a: Int): Boolean = {
      if (s(a) && !p(a)) false
      else if (a < -bound) true
      else iter(a-1)
    }
    iter(bound)
  }
  
    /**
   * Returns whether there exists a bounded integer within `s`
   * that satisfies `p`.
   */
  def exists(s: Set, p: Int => Boolean): Boolean = !forall(s, (x:Int) => !p(x))

  /**
   * Returns a set transformed by applying `f` to each element of `s`.
   */
  def map(s: Set, f: Int => Int): Set =
  {
   (z:Int ) => exists(s, (x:Int)=>f(x) == z)
  }
  
  def filter(s: Set, p: Int => Boolean): Set = (x:Int) => s(x) && p(x)
  
  val multiplesOf2 = (x:Int) => x%2 == 0
  val multiplesOf3 = (x:Int) => x%3 == 0
  val multiplesOf5 = (x:Int) => x%5 == 0
  val negativeNum  = (x:Int) => x < 0
  val lessThan10   = (x:Int) => x < 10
  val moreThan10   = (x:Int) => x < 10
  val factorOf20   = (x:Int) => x match {case 0 =>false case _ =>20%x  == 0 }
  val factorOf100  = (x:Int) => x match {case 0 =>false case _ =>100%x == 0 }
  val factorOf144  = (x:Int) => x match {case 0 =>false case _ =>144%x == 0 }
 
    
  contains(singletonSet(4),2)
  contains(singletonSet(4),2*2)
  
  contains (intersect(multiplesOf2,multiplesOf5),10)
  contains (intersect(multiplesOf2,multiplesOf5),15)
  contains (intersect(multiplesOf3,multiplesOf5),15)
  contains (union(multiplesOf2,multiplesOf5),7)
  contains (union(multiplesOf2,multiplesOf5),4)
  contains (union(multiplesOf2,multiplesOf3),9)
  contains (intersect(multiplesOf2,multiplesOf3),9)
  
  exists(union(multiplesOf2,multiplesOf5),(x)=> x==41)
  exists(union(multiplesOf2,multiplesOf5),factorOf20)
  exists(union(multiplesOf2,multiplesOf5),factorOf144)
  exists(intersect(multiplesOf2,multiplesOf5),factorOf20)
  exists(intersect(multiplesOf2,multiplesOf5),factorOf144)
  exists(intersect(multiplesOf2,multiplesOf3),factorOf144)
                                                  
  contains( map(union(multiplesOf3,multiplesOf5),(x:Int) => x+1),40)
  contains( map(intersect(multiplesOf3,multiplesOf5),(x:Int) => x+101),116)
  contains( map(intersect(multiplesOf3,multiplesOf5),(x:Int) => x*7),420)
  contains( map(union(multiplesOf3,multiplesOf5),(x:Int) => x+1),46)
  contains( map(intersect(multiplesOf3,multiplesOf5),(x:Int) => x+101),117)
  contains( map(intersect(multiplesOf3,multiplesOf5),(x:Int) => x*7),421)
 
 contains( diff(multiplesOf5,multiplesOf2),20)
 contains( diff(multiplesOf5,multiplesOf2),24)
 contains( diff(multiplesOf5,multiplesOf2),25)
 contains( diff(multiplesOf5,multiplesOf2),-25)
 
 diff(multiplesOf5,multiplesOf2)(30)
 map(multiplesOf5,(x)=> x+1)(21)
 map(multiplesOf5,(x)=> x*10)(15)
 map(multiplesOf5,(x)=> x*10)(150)
 
 filter(multiplesOf5,(x)=> x < 100)(100)
 filter(multiplesOf5,(x)=> x < 100)(90)
 filter(multiplesOf5,(x)=> x < 100)(-100)
 b
}