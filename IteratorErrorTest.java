import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Provides JUnit test cases for the IteratorError class.
 *
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 2017-09-05
 */
public class IteratorErrorTest {

   @Test
   public void testSearchNotFound() {
      Integer[] a = {2, 4, 6, 8, 10};
      List<Integer> list = Arrays.<Integer>asList(a);
      int expected = -1;
      int actual = IteratorError.<Integer>search(list, 5);
      assertEquals(expected, actual);
   }

   @Test
   public void testSearchFoundAtFront() {
      Integer[] a = {2, 4, 6, 8, 10};
      List<Integer> list = Arrays.<Integer>asList(a);
      int expected = 0;
      int actual = IteratorError.<Integer>search(list, 2);
      assertEquals(expected, actual);
   }

   @Test
   public void testSearchFoundAtRear() {
      Integer[] a = {2, 4, 6, 8, 10};
      List<Integer> list = Arrays.<Integer>asList(a);
      int expected = 4;
      int actual = IteratorError.<Integer>search(list, 10);
      assertEquals(expected, actual);
   }

   @Test
   public void testSearchFoundInMiddle1() {
      Integer[] a = {2, 4, 6, 8, 10};
      List<Integer> list = Arrays.<Integer>asList(a);
      int expected = 1;
      int actual = IteratorError.<Integer>search(list, 4);
      assertEquals(expected, actual);
   }

   @Test
   public void testSearchFoundInMiddle2() {
      Integer[] a = {2, 4, 6, 8, 10};
      List<Integer> list = Arrays.<Integer>asList(a);
      int expected = 2;
      int actual = IteratorError.<Integer>search(list, 6);
      assertEquals(expected, actual);
   }

   @Test
   public void testSearchFoundInMiddle3() {
      Integer[] a = {2, 4, 6, 8, 10};
      List<Integer> list = Arrays.<Integer>asList(a);
      int expected = 3;
      int actual = IteratorError.<Integer>search(list, 8);
      assertEquals(expected, actual);
   }


}