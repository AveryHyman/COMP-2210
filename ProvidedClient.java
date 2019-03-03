/**
*  A  client for ProvidedClass.
*
*  @author Avery Hyman (amh0120@auburn.edu)
*  @version 2017-09-25
*/
public class ProvidedClient {
//   to convert from nanoseconds to   seconds
   private static final double SECONDS =  1_000_000_000d;

/** Drives execution. */
   public static void main(String[] args) {
      int numRuns =  15;
      int n =  2;
      double value1R = 0;
      double value2R = 0;
      double valueR = 0;
      ProvidedClass providedClass = new ProvidedClass(903713261);
      for (int i  =  0;   i <  numRuns; i++) {
         long startTime =  System.nanoTime();
         providedClass.methodToTime(n);
         long endTime = System.nanoTime();
         double elapsedTime = (endTime -  startTime) / SECONDS;
         if (value1R == 0) {
         
            System.out.println("Problem size =  " +  n  +  "  "+  "Elapsed time = "  +  elapsedTime + " --");
            value1R = elapsedTime;
         }
         else {
            value2R = elapsedTime;
            valueR = value2R / value1R;
            System.out.println("Problem size =  " +  n  +  "  "+  "Elapsed time = "  +  elapsedTime + " R value = " + valueR);
            value1R = elapsedTime;
         }
         n  =  n *  2;
      }
   }
}