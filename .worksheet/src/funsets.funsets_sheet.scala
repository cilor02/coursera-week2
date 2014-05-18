package funsets

object funsets_sheet {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(83); 
  println("Welcome to the Scala worksheet")
    type Set = Int => Boolean;$skip(151); 

  /**
   * Indicates whether a set contains a given element.
   */
  def contains(s: Set, elem: Int): Boolean = s(elem);System.out.println("""contains: (s: Int => Boolean, elem: Int)Boolean""");$skip(156); 

  /**
   * Returns the set of the one given element.
   */
  def singletonSet(elem: Int): Set = {def singleItemSet(x:Int):Boolean = x==elem;singleItemSet};System.out.println("""singletonSet: (elem: Int)Int => Boolean""");$skip(198); 
 
   /**
   * Returns the union of the two given sets,
   * the sets of all elements that are in either `s` or `t`.
   */
  def union(s: Set, t: Set): Set = {def f(x:Int): Boolean = s(x) || t(x);f};System.out.println("""union: (s: Int => Boolean, t: Int => Boolean)Int => Boolean""");$skip(205); 

  /**
   * Returns the intersection of the two given sets,
   * the set of all elements that are both in `s` and `t`.
   */
  def intersect(s: Set, t: Set): Set = {def f(x:Int): Boolean = s(x) && t(x);f};System.out.println("""intersect: (s: Int => Boolean, t: Int => Boolean)Int => Boolean""");$skip(197); 

  /**
   * Returns the difference of the two given sets,
   * the set of all elements of `s` that are not in `t`.
   */
  def diff(s: Set, t: Set): Set = {def f(x:Int): Boolean = s(x) && !t(x);f};System.out.println("""diff: (s: Int => Boolean, t: Int => Boolean)Int => Boolean""");$skip(87); 
   /*
   * The bounds for `forall` and `exists` are +/- 1000.
   */
  val bound = 1000;System.out.println("""bound  : Int = """ + $show(bound ));$skip(276); 

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
  };System.out.println("""forall: (s: Int => Boolean, p: Int => Boolean)Boolean""");$skip(185); 
  
    /**
   * Returns whether there exists a bounded integer within `s`
   * that satisfies `p`.
   */
  def exists(s: Set, p: Int => Boolean): Boolean = !forall(s, (x:Int) => !p(x));System.out.println("""exists: (s: Int => Boolean, p: Int => Boolean)Boolean""");$skip(177); 

  /**
   * Returns a set transformed by applying `f` to each element of `s`.
   */
  def map(s: Set, f: Int => Int): Set =
  {
   (z:Int ) => exists(s, (x:Int)=>f(x) == z)
  };System.out.println("""map: (s: Int => Boolean, f: Int => Int)Int => Boolean""");$skip(74); 
  
  def filter(s: Set, p: Int => Boolean): Set = (x:Int) => s(x) && p(x);System.out.println("""filter: (s: Int => Boolean, p: Int => Boolean)Int => Boolean""");$skip(44); 
  
  val multiplesOf2 = (x:Int) => x%2 == 0;System.out.println("""multiplesOf2  : Int => Boolean = """ + $show(multiplesOf2 ));$skip(41); 
  val multiplesOf3 = (x:Int) => x%3 == 0;System.out.println("""multiplesOf3  : Int => Boolean = """ + $show(multiplesOf3 ));$skip(41); 
  val multiplesOf5 = (x:Int) => x%5 == 0;System.out.println("""multiplesOf5  : Int => Boolean = """ + $show(multiplesOf5 ));$skip(38); 
  val negativeNum  = (x:Int) => x < 0;System.out.println("""negativeNum  : Int => Boolean = """ + $show(negativeNum ));$skip(39); 
  val lessThan10   = (x:Int) => x < 10;System.out.println("""lessThan10  : Int => Boolean = """ + $show(lessThan10 ));$skip(39); 
  val moreThan10   = (x:Int) => x < 10;System.out.println("""moreThan10  : Int => Boolean = """ + $show(moreThan10 ));$skip(78); 
  val factorOf20   = (x:Int) => x match {case 0 =>false case _ =>20%x  == 0 };System.out.println("""factorOf20  : Int => Boolean = """ + $show(factorOf20 ));$skip(78); 
  val factorOf100  = (x:Int) => x match {case 0 =>false case _ =>100%x == 0 };System.out.println("""factorOf100  : Int => Boolean = """ + $show(factorOf100 ));$skip(78); 
  val factorOf144  = (x:Int) => x match {case 0 =>false case _ =>144%x == 0 };System.out.println("""factorOf144  : Int => Boolean = """ + $show(factorOf144 ));$skip(37); val res$0 = 
 
    
  contains(singletonSet(4),2);System.out.println("""res0: Boolean = """ + $show(res$0));$skip(32); val res$1 = 
  contains(singletonSet(4),2*2);System.out.println("""res1: Boolean = """ + $show(res$1));$skip(56); val res$2 = 
  
  contains (intersect(multiplesOf2,multiplesOf5),10);System.out.println("""res2: Boolean = """ + $show(res$2));$skip(53); val res$3 = 
  contains (intersect(multiplesOf2,multiplesOf5),15);System.out.println("""res3: Boolean = """ + $show(res$3));$skip(53); val res$4 = 
  contains (intersect(multiplesOf3,multiplesOf5),15);System.out.println("""res4: Boolean = """ + $show(res$4));$skip(48); val res$5 = 
  contains (union(multiplesOf2,multiplesOf5),7);System.out.println("""res5: Boolean = """ + $show(res$5));$skip(48); val res$6 = 
  contains (union(multiplesOf2,multiplesOf5),4);System.out.println("""res6: Boolean = """ + $show(res$6));$skip(48); val res$7 = 
  contains (union(multiplesOf2,multiplesOf3),9);System.out.println("""res7: Boolean = """ + $show(res$7));$skip(52); val res$8 = 
  contains (intersect(multiplesOf2,multiplesOf3),9);System.out.println("""res8: Boolean = """ + $show(res$8));$skip(58); val res$9 = 
  
  exists(union(multiplesOf2,multiplesOf5),(x)=> x==41);System.out.println("""res9: Boolean = """ + $show(res$9));$skip(54); val res$10 = 
  exists(union(multiplesOf2,multiplesOf5),factorOf20);System.out.println("""res10: Boolean = """ + $show(res$10));$skip(55); val res$11 = 
  exists(union(multiplesOf2,multiplesOf5),factorOf144);System.out.println("""res11: Boolean = """ + $show(res$11));$skip(58); val res$12 = 
  exists(intersect(multiplesOf2,multiplesOf5),factorOf20);System.out.println("""res12: Boolean = """ + $show(res$12));$skip(59); val res$13 = 
  exists(intersect(multiplesOf2,multiplesOf5),factorOf144);System.out.println("""res13: Boolean = """ + $show(res$13));$skip(59); val res$14 = 
  exists(intersect(multiplesOf2,multiplesOf3),factorOf144);System.out.println("""res14: Boolean = """ + $show(res$14));$skip(121); val res$15 = 
                                                  
  contains( map(union(multiplesOf3,multiplesOf5),(x:Int) => x+1),40);System.out.println("""res15: Boolean = """ + $show(res$15));$skip(76); val res$16 = 
  contains( map(intersect(multiplesOf3,multiplesOf5),(x:Int) => x+101),116);System.out.println("""res16: Boolean = """ + $show(res$16));$skip(74); val res$17 = 
  contains( map(intersect(multiplesOf3,multiplesOf5),(x:Int) => x*7),420);System.out.println("""res17: Boolean = """ + $show(res$17));$skip(69); val res$18 = 
  contains( map(union(multiplesOf3,multiplesOf5),(x:Int) => x+1),46);System.out.println("""res18: Boolean = """ + $show(res$18));$skip(76); val res$19 = 
  contains( map(intersect(multiplesOf3,multiplesOf5),(x:Int) => x+101),117);System.out.println("""res19: Boolean = """ + $show(res$19));$skip(74); val res$20 = 
  contains( map(intersect(multiplesOf3,multiplesOf5),(x:Int) => x*7),421);System.out.println("""res20: Boolean = """ + $show(res$20));$skip(49); val res$21 = 
 
 contains( diff(multiplesOf5,multiplesOf2),20);System.out.println("""res21: Boolean = """ + $show(res$21));$skip(47); val res$22 = 
 contains( diff(multiplesOf5,multiplesOf2),24);System.out.println("""res22: Boolean = """ + $show(res$22));$skip(47); val res$23 = 
 contains( diff(multiplesOf5,multiplesOf2),25);System.out.println("""res23: Boolean = """ + $show(res$23));$skip(48); val res$24 = 
 contains( diff(multiplesOf5,multiplesOf2),-25);System.out.println("""res24: Boolean = """ + $show(res$24));$skip(39); val res$25 = 
 
 diff(multiplesOf5,multiplesOf2)(30);System.out.println("""res25: Boolean = """ + $show(res$25));$skip(33); val res$26 = 
 map(multiplesOf5,(x)=> x+1)(21);System.out.println("""res26: Boolean = """ + $show(res$26));$skip(34); val res$27 = 
 map(multiplesOf5,(x)=> x*10)(15);System.out.println("""res27: Boolean = """ + $show(res$27));$skip(35); val res$28 = 
 map(multiplesOf5,(x)=> x*10)(150);System.out.println("""res28: Boolean = """ + $show(res$28));$skip(43); val res$29 = 
 
 filter(multiplesOf5,(x)=> x < 100)(100);System.out.println("""res29: Boolean = """ + $show(res$29));$skip(40); val res$30 = 
 filter(multiplesOf5,(x)=> x < 100)(90);System.out.println("""res30: Boolean = """ + $show(res$30));$skip(42); val res$31 = 
 filter(multiplesOf5,(x)=> x < 100)(-100);System.out.println("""res31: Boolean = """ + $show(res$31));$skip(3); val res$32 = 
 b;System.out.println("""res32: <error> = """ + $show(res$32))}
}
