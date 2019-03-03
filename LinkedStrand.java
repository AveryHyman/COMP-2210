/**
* LinkedStrand.java
* Provides a linked chunk list implementation of the DnaStrand interface.
* 
* @author   Avery Hyman (you@auburn.edu)
* @author   Dean Hendrix (dh@auburn.edu)
* @version  2017-10-17
*
*/
public class LinkedStrand implements DnaStrand {

   /**
    * Container for storing DNA information. A given DNA strand is
    * represented by a chain of nodes.
    *
    * D O   N O T   M A K E   A N Y   C H A N G E S   T O
    *
    * T H E   N O D E   C L A S S 
    *
    */
   class Node {
      String dnaInfo;
      Node next;
   
      public Node() {
         dnaInfo = "";
         next = null;
      }
   
      public Node(String s, Node n) {
         dnaInfo = s;
         next = n;
      }
   }

   /** An empty strand. */
   public static final LinkedStrand EMPTY_STRAND = new LinkedStrand();

   /** The first and last node in this LinkedStrand. */
   // D O   N O T   C H A N G E   T H E S E   F I E L D S 
   Node front;
   Node rear;


   /** The number of nucleotides in this strand. */
   long size;

   /**
    * Create a strand representing an empty DNA strand, length of zero.
    *
    * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
    */
   public LinkedStrand() {
      front = null;
      rear = null;
      size = 0;
   }


   /**
    * Create a strand representing s. No error checking is done to see if s represents
    * valid genomic/DNA data.
    *
    * @param s is the source of cgat data for this strand
    */
   public LinkedStrand(String s) {
      front = rear = new Node(s, null);
      size = s.length();
   }


   /**
    * Appends the parameter to this strand changing this strand to represent
    * the addition of new characters/base-pairs, e.g., changing this strand's
    * size.
    * 
    * If possible implementations should take advantage of optimizations
    * possible if the parameter is of the same type as the strand to which data
    * is appended.
    * 
    * @param dna is the strand being appended
    * @return this strand after the data has been added
    */
   @Override
   public DnaStrand append(DnaStrand dna) {
      if (dna.toString().length() == 0 && front == null) {
         front = null;
         rear = null;
         size = 0;
         return this;
      }
      
      if (dna == null || dna.toString().length() == 0) {
         return this;
      }
      
      if (dna instanceof LinkedStrand) {
         LinkedStrand temp = (LinkedStrand) dna;
         if (front == null || front.dnaInfo.length() == 0) {
            front = rear = temp.front;
         }
         
         else {
            rear.next = temp.front;
         }
         
         rear = temp.rear;
         size += temp.size();
         return this;
      }
      
      else {
         return append(dna.toString());
      }
   }


   /**
    * Similar to append with a DnaStrand parameter in
    * functionality, but fewer optimizations are possible. Typically this
    * method will be called by the append method with an DNAStrand
    * parameter if the DnaStrand passed to that other append method isn't the same 
    * class as the strand being appended to.
    * 
    * @param dna is the string appended to this strand
    * @return this strand after the data has been added
    */
   @Override
   public DnaStrand append(String dna) {
      if (dna == null || dna.length() == 0) {
         return this;
      }
      
      if (front == null || front.dnaInfo.length() == 0) {
         front = rear = new Node(dna, null);
      }
      
      else {
         rear.next = new Node(dna, null);
         rear = rear.next;
      }
      
      size += dna.length();
      return this;
   }


   /**
    * Cut this strand at every occurrence of enzyme, replacing
    * every occurrence of enzyme with splice.
    *
    * @param enzyme is the pattern/strand searched for and replaced
    * @param splice is the pattern/strand replacing each occurrence of enzyme
    * @return the new strand leaving the original strand unchanged.
    */
   @Override
   public DnaStrand cutAndSplice(String enzyme, String splice) {
      if (front == null || front != rear) {
         throw new IllegalStateException();
      }
      
      DnaStrand temp = new LinkedStrand();
      
      String tempS = this.toString();
      
      int place = tempS.indexOf(enzyme);
      
      if (place == -1) {
         return EMPTY_STRAND;
      }
      
      while (place >= 0) {
         temp.append(tempS.substring(0, place));
         temp.append(splice);
         tempS = tempS.substring(place + enzyme.length());
         place = tempS.indexOf(enzyme);
      }
      
      temp.append(tempS);
      
      return temp;
      
   }


   /**
    * Simulate a restriction enzyme cut by finding the first occurrence of
    * enzyme in this strand and replacing this strand with what comes before
    * the enzyme while returning a new strand representing what comes after the
    * enzyme. If the enzyme isn't found, this strand is unaffected and an empty
    * strand with size equal to zero is returned.
    * 
    * @param enzyme is the string being searched for
    * @return the part of the strand that comes after the enzyme
    */
   @Override
   public DnaStrand cutWith(String enzyme) {
      if (front == null || front != rear) {
         throw new IllegalStateException();
      }
      
      
      
      String tempS = this.toString();
      
      int place = tempS.indexOf(enzyme);
      
      if (place == -1) {
         return EMPTY_STRAND;
      }
      
      String remaining = tempS.substring(place + enzyme.length(), tempS.length());
      
      
      LinkedStrand temp = new LinkedStrand(remaining);
      
      if (temp.front.dnaInfo.length() == 0) {
         temp.front = temp.rear = null;
         temp.size = 0;
      }
      initializeFrom(tempS.substring(0, place));
      
      return temp;
   }


   /**
    * Initialize by copying DNA data from the string into this strand,
    * replacing any data that was stored. The parameter should contain only
    * valid DNA characters, no error checking is done by the this method.
    * 
    * @param source is the string used to initialize this strand
    */
   @Override
   public void initializeFrom(String source) {
      if (source.length() == 0 || source == null) {
         front = null;
         rear = null;
         size = 0;
      }
      
      else {
         front = rear = new Node(source, null);
         size = source.length();
      }
   }


   /**
    * Returns the number of elements/base-pairs/nucleotides in this strand.
    * @return the number of base-pairs in this strand
    */
   @Override
   public long size() {
      return size;
   }


   /**
    * Returns a string representation of this LinkedStrand.
    */
   @Override
   public String toString() {
      
      Node tempN = this.front;
      String tempSt = "";
      
      while (tempN != null) {
         tempSt += tempN.dnaInfo;
         tempN = tempN.next;
      }
      return tempSt;
   }


}

