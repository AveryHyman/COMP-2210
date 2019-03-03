import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * ArraySet.java.
 *
 * Provides an implementation of the Set interface using an
 * array as the underlying data structure. Values in the array
 * are kept in ascending natural order and, where possible,
 * methods take advantage of this. Many of the methods with parameters
 * of type ArraySet are specifically designed to take advantage
 * of the ordered array implementation.
 *
 * @author Avery Hyman (amh0120@auburn.edu)
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 2017-10-05
 *
 */
public class ArraySet<T extends Comparable<? super T>> implements Set<T> {

   ////////////////////////////////////////////
   // DO NOT CHANGE THE FOLLOWING TWO FIELDS //
   ////////////////////////////////////////////
   T[] elements;
   int size;

   ////////////////////////////////////
   // DO NOT CHANGE THIS CONSTRUCTOR //
   ////////////////////////////////////
   /**
    * Instantiates an empty set.
    */
   @SuppressWarnings("unchecked")
   public ArraySet() {
      elements = (T[]) new Comparable[1];
      size = 0;
   }

   ///////////////////////////////////
   // DO NOT CHANGE THE SIZE METHOD //
   ///////////////////////////////////
   /**
    * Returns the current size of this collection.
    *
    * @return  the number of elements in this collection.
    */
   @Override
   public int size() {
      return size;
   }

   //////////////////////////////////////
   // DO NOT CHANGE THE ISEMPTY METHOD //
   //////////////////////////////////////
   /**
    * Tests to see if this collection is empty.
    *
    * @return  true if this collection contains no elements,
    *               false otherwise.
    */
   @Override
   public boolean isEmpty() {
      return size == 0;
   }

   ///////////////////////////////////////
   // DO NOT CHANGE THE TOSTRING METHOD //
   ///////////////////////////////////////
   /**
    * Return a string representation of this ArraySet.
    *
    * @return a string representation of this ArraySet
    */
   @Override
   public String toString() {
      if (isEmpty()) {
         return "[]";
      }
      StringBuilder result = new StringBuilder();
      result.append("[");
      for (T element : this) {
         result.append(element + ", ");
      }
      result.delete(result.length() - 2, result.length());
      result.append("]");
      return result.toString();
   }

   /**
    * Ensures the collection contains the specified element. Elements are
    * maintained in ascending natural order at all times. Neither duplicate nor
    * null values are allowed.
    *
    * @param  element  The element whose presence is to be ensured.
    * @return true if collection is changed, false otherwise.
    */
   @Override
   public boolean add(T element) {
      
      if (size == elements.length) {
         resizer();
      }
      
      if (element == null) {
         return false;
      }
      
      if (locate(element) != -1) {
         return false;
      }
      
      int i = 0;
      while (i <= size() || size == 0) {
         if (elements[i] == null || element.compareTo(elements[i]) < 0) {
            move(i);
            
            elements[i] = element;
            
            size++;
            
            return true;
         }
         
         i++;
      }
            
      return false;
   }

   /**
    * Ensures the collection does not contain the specified element.
    * If the specified element is present, this method removes it
    * from the collection. Elements are maintained in ascending natural
    * order at all times.
    *
    * @param   element  The element to be removed.
    * @return  true if collection is changed, false otherwise.
    */
   @Override
   public boolean remove(T element) {
      if (isEmpty()) {
         return false;
      }
      
      if (locate(element) == -1) {
         return false;
      }
      
      int search = locate(element);
      
      while (search <= size() && !((search + 1) > size())) {
         elements[search] = elements[search + 1];
         search++;
      }
      
      elements[size - 1] = null;
      size--;
      
      if (size() < elements.length * .25) {
         T[] temp = (T[]) new Comparable[(elements.length / 2)];
         System.arraycopy(elements, 0, temp, 0, (elements.length / 2));
         elements = temp;
      }
      
      return true;
   }

   /**
    * Searches for specified element in this collection.
    *
    * @param   element  The element whose presence in this collection
    *                   is to be tested.
    * @return  true if this collection contains the specified element,
    *               false otherwise.
    */
   @Override
   public boolean contains(T element) {
      if (isEmpty()) {
         return false;
      }
      
      if (locate(element) != -1) {
         return true;
      }
      
      return false;
   }

   /**
    * Tests for equality between this set and the parameter set.
    * Returns true if this set contains exactly the same elements
    * as the parameter set, regardless of order.
    *
    * @return  true if this set contains exactly the same elements
    *               as the parameter set, false otherwise
    */
   @Override
   public boolean equals(Set<T> s) {
      if (size() != s.size()) {
         return false;
      }
      
      int counter = 0;
      Iterator<T> search = s.iterator();
      while (search.hasNext()) {
         T temp = search.next();
         if (contains(temp)) {
            counter++;
         }
      }
      if (counter == size()) {
         return true;
      }
      
      return true;
   }

   /**
    * Tests for equality between this set and the parameter set.
    * Returns true if this set contains exactly the same elements
    * as the parameter set, regardless of order.
    *
    * @return  true if this set contains exactly the same elements
    *               as the parameter set, false otherwise
    */
   public boolean equals(ArraySet<T> s) {
      if (size() != s.size()) {
         return false;
      }
      
      int counter = 0;
      Iterator<T> search = s.iterator();
      while (search.hasNext()) {
         T temp = search.next();
         if (contains(temp)) {
            counter++;
         }
      }
      if (counter == size()) {
         return true;
      }
      
      return true;
   }

   /**
    * Returns a set that is the union of this set and the parameter set.
    *
    * @return  a set that contains all the elements of this set and
    *            the parameter set
    */
   @Override
   public Set<T> union(Set<T> s) {
      ArraySet<T> union = new ArraySet<T>();
      
      for (int i = 0; i < size(); i++) {
         union.add(elements[i]);
      }
      
      Iterator<T> search = s.iterator();
      
      while (search.hasNext()) {
         union.add(search.next());
      }
      
      return union;
   }

   /**
    * Returns a set that is the union of this set and the parameter set.
    *
    * @return  a set that contains all the elements of this set and
    *            the parameter set
    */
   public Set<T> union(ArraySet<T> s) {
      ArraySet<T> union = new ArraySet<T>();
      
      for (int i = 0; i < size(); i++) {
         union.add(elements[i]);
      }
      
      Iterator<T> search = s.iterator();
      
      while (search.hasNext()) {
         union.add(search.next());
      }
      
      return union;
   }


   /**
    * Returns a set that is the intersection of this set
    * and the parameter set.
    *
    * @return  a set that contains elements that are in both
    *            this set and the parameter set
    */
   @Override
   public Set<T> intersection(Set<T> s) {
      ArraySet<T> intersect = new ArraySet<T>();
      
      Iterator<T> search = s.iterator();
      
      while (search.hasNext()) {
         T temp = search.next();
         if (contains(temp)) {
            intersect.add(temp);
         }
      }
      
      return intersect;
   }

   /**
    * Returns a set that is the intersection of this set and
    * the parameter set.
    *
    * @return  a set that contains elements that are in both
    *            this set and the parameter set
    */
   public Set<T> intersection(ArraySet<T> s) {
      ArraySet<T> intersect = new ArraySet<T>();
      
      Iterator<T> search = s.iterator();
      
      while (search.hasNext()) {
         T temp = search.next();
         if (contains(temp)) {
            intersect.add(temp);
         }
      }
      
      return intersect;
   }

   /**
    * Returns a set that is the complement of this set and
    * the parameter set.
    *
    * @return  a set that contains elements that are in this
    *            set but not the parameter set
    */
   @Override
   public Set<T> complement(Set<T> s) {
      ArraySet<T> complemenT = new ArraySet<T>();
      
      for (int i = 0; i < size(); i++) {
         T temp = elements[i];
         if (!s.contains(temp)) {
            complemenT.add(temp);
         }
      }
      
      return complemenT;
   }

   /**
    * Returns a set that is the complement of this set and
    * the parameter set.
    *
    * @return  a set that contains elements that are in this
    *            set but not the parameter set
    */
   public Set<T> complement(ArraySet<T> s) {
      ArraySet<T> complemenT = new ArraySet<T>();
      
      for (int i = 0; i < size(); i++) {
         T temp = elements[i];
         if (!s.contains(temp)) {
            complemenT.add(temp);
         }
      }
      
      return complemenT;
   }


   /**
    * Returns an iterator over the elements in this ArraySet.
    * No specific order can be assumed.
    *
    * @return  an iterator over the elements in this ArraySet
    */
   @Override
   public Iterator<T> iterator() {
   
      // ALMOST ALL THE TESTS DEPEND ON THIS METHOD WORKING CORRECTLY.
      // MAKE SURE YOU GET THIS ONE WORKING FIRST.
      // HINT: JUST USE THE SAME CODE/STRATEGY AS THE ARRAYBAG CLASS
      // FROM LECTURE. THE ONLY DIFFERENCE IS THAT YOU'LL NEED THE
      // ARRAYITERATOR CLASS TO BE NESTED, NOT TOP-LEVEL.
      return new ArrayIterator(elements, size);
   }
   
   private class ArrayIterator<T> implements Iterator<T> {
      private T[] items;
      private int counter;
      private int current;
      
      public ArrayIterator(T[] elements, int size) {
         items = elements;
         counter = size;
         current = 0;
      }
      
      @Override public boolean hasNext() {
         return ( current < counter);
      }
      
      @Override public T next() {
         if (!hasNext()) {
            throw new NoSuchElementException();
         }
         T end = items[current];
         current ++;
         return end;
      }
      
      @Override public void remove() {
         throw new UnsupportedOperationException();
      }
   }

   /**
    * Returns an iterator over the elements in this ArraySet.
    * The elements are returned in descending order.
    *
    * @return  an iterator over the elements in this ArraySet
    */
   public Iterator<T> descendingIterator() {
      Iterator<T> bIter = 
         new Iterator<T>() {
            private int count = size();
         
            @Override
            public boolean hasNext() {
               return count > 0 && elements[count] != null;
            }
         
            @Override
            public T next() {
               return elements[count - 1];
            }
         
            @Override
            public void remove() {
               throw new UnsupportedOperationException();
            }
         };
      
      return bIter;
   }

   /**
    * Returns an iterator over the members of the power set
    * of this ArraySet. No specific order can be assumed.
    *
    * @return  an iterator over members of the power set
    */
   public Iterator<Set<T>> powerSetIterator() {
      return null;
   }
   
   
   /**
   * Private method make sure all spaces are filled.
   */
   private boolean isFull() {
      return size == elements.length;
   }

   /**
   * Finds value and returns location.
   */
   private int locate(T element) {
      int min = 0;
      int mid = 0;
      int max = size - 1;
      while (min <= max) {
         mid = (max + min) / 2;
         int compareElementsResult = elements[mid].compareTo(element);
         if (compareElementsResult > 0) {
            max = mid - 1;
         }
         if (compareElementsResult < 0) {
            min = mid + 1;
         }
         if (compareElementsResult == 0) {
            return mid;
         }
      }
      return -1;
      
      /**
      for (int i = 0; i < size; i++) {
         if (elements[i].equals(element)) {
            return i;
         }
      }
      return -1;*/
   }
   
   //Moves elements
   private void move(int number) {
      int i = size();
      while (i > number) {
         elements[i] = elements[i - 1];
         i--;
      }
   }
   
   /**
   *Resizes the Array.
   */
   private void resizer() {
      T[] newArray = (T[]) (new Comparable[elements.length * 2]);
      for (int i = 0; i < elements.length; i++) {
         newArray[i] = elements[i];
      }
      elements = newArray;
   }
}
