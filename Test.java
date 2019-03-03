import java.util.Arrays;
public class Test {

   public static void main(String[] args) {
      int count = 0;
      for (int i = 1; i <= 1000000; i++)
         for (int j = i; j > 500; j--)
            for (int k = 1; k < 10500; k = k + 2) {
               count++;
               System.out.println(count);
            }
      System.out.println(count);
   }
}
