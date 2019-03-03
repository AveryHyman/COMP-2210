import java.util.Arrays;

/**
* Defines a library of selection methods
* on arrays of ints.
*
* @author   Avery Hyman (amh0120@auburn.edu)
* @version  2017-08-26
*
*/
public final class Selector {

   /**
    * Can't instantiate this class.
    *
    * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
    *
    */
   private Selector() { }


   /**
    * Selects the minimum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    */
   public static int min(int[] a) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      else {
         int minNumber = a[0];
         for (int counter = 1; counter < a.length; counter++) {
            if (minNumber > a[counter]) {
               minNumber = a[counter];
            }
         }
         return minNumber;
      }
   }


   /**
    * Selects the maximum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    */
   public static int max(int[] a) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      else {
         int maxNumber = a[0];
         for (int counter = 1; counter < a.length; counter++) {
            if (maxNumber < a[counter]) {
               maxNumber = a[counter];
            }
         }
         return maxNumber;
      }
   }


   /**
    * Selects the kth minimum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth minimum value. Note that there is no kth
    * minimum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    */
   public static int kmin(int[] a, int k) {
      if (a == null || a.length == 0 || k == 0 || k > a.length + 1 || k < 1) {
         throw new IllegalArgumentException();
      }
      else {
         int[] aTest = a.clone();
         for (int i = 0; i < aTest.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < aTest.length; j++) {
               if (aTest[j] < aTest[min]) {
                  min = j;
               }
            }
            int temp = aTest[i];
            aTest[i] = aTest[min];
            aTest[min] = temp;
         }
         int location = (k - 1);
         int value = aTest[location];
         int counter;
         for (counter = 0; counter > aTest.length; counter++) {
            if (counter == location) {
               value = aTest[counter];
            }
         }
         counter = 1;
         while (value == aTest[(location + counter)] && (location + counter) > aTest.length) {
            value = aTest[(location + counter)];
         }
         return value;
      }
   }

   
   /**
    * Selects the kth maximum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth maximum value. Note that there is no kth
    * maximum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    */
   public static int kmax(int[] a, int k) {
      if (a == null || a.length == 0 || k == 0 || k > a.length + 1 || k < 1) {
         throw new IllegalArgumentException();
      }
      else {
         int[] aTest = a.clone();
         for (int i = 0; i < aTest.length - 1; i++) {
            int max = i;
            for (int j = i + 1; j < aTest.length; j++) {
               if (aTest[j] > aTest[max]) {
                  max = j;
               }
            }
            int temp = aTest[i];
            aTest[i] = aTest[max];
            aTest[max] = temp;
         }
         int location = (k - 1);
         int value = aTest[location];
         int counter;
         for (counter = aTest.length; counter >= -1; counter--) {
            if (counter == location) {
               value = aTest[counter];
            }
         }
         counter = 1;
         while (value == aTest[(location + counter)] && (location + counter) > aTest.length) {
            value = aTest[(location + counter)];
         }
         return value;
      }
   }


   /**
    * Returns an array containing all the values in a in the
    * range [low..high]; that is, all the values that are greater
    * than or equal to low and less than or equal to high,
    * including duplicate values. The length of the returned array
    * is the same as the number of values in the range [low..high].
    * If there are no qualifying values, this method returns a
    * zero-length array. Note that low and high do not have
    * to be actual values in a. This method throws an
    * IllegalArgumentException if a is null or has zero length.
    * The array a is not changed by this method.
    */
   public static int[] range(int[] a, int low, int high) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      else {
         int length = 0;
         int placement = 0;
         for (int counter = 0; counter < a.length; counter++) {
            if (a[counter] >= low && a[counter] <= high) {
               length++;
            }
         }
         int[] finalArray = new int[length];
         for (int counter = 0; counter < a.length; counter++) {
            if (a[counter] >= low && a[counter] <= high) {
               finalArray[placement] = a[counter];
               placement++;
            }
         }
         return finalArray;
      }
   }


   /**
    * Returns the smallest value in a that is greater than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    */
   public static int ceiling(int[] a, int key) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      else { 
         int maxValue = Integer.MAX_VALUE;
         int returnedValue = -1;
         for (int counter = 0; counter < a.length; counter++) {
            if (a[counter] >= key && maxValue > (a[counter] - key)) {
               maxValue = a[counter] - key;
               returnedValue = a[counter];
            }
         }
         if (returnedValue == -1) {
            throw new IllegalArgumentException();
         }
         else {
            return returnedValue;
         }
      }
      
   }


   /**
    * Returns the largest value in a that is less than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    */
   public static int floor(int[] a, int key) {
      if (a == null || a.length == 0) {
         throw new IllegalArgumentException();
      }
      else { 
         int minValue = Integer.MIN_VALUE;
         int returnedValue = -1;
         for (int counter = 0; counter < a.length; counter++) {
            if (a[counter] <= key && minValue < (a[counter] + key)) {
               minValue = a[counter] + key;
               returnedValue = a[counter];
            }
         }
         if (returnedValue == -1) {
            throw new IllegalArgumentException();
         }
         else {
            return returnedValue;
         }
      }
   }

}
