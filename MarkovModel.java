import java.io.File;
import java.util.HashMap;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/**
 * MarkovModel.java Creates an order K Markov model of the supplied source
 * text. The value of K determines the size of the "kgrams" used to generate
 * the model. A kgram is a sequence of k consecutive characters in the source
 * text.
 *
 * @author     Avery Hyman (amh0120@auburn.edu)
 * @author     Dean Hendrix (dh@auburn.edu)
 * @version    2017-12-08
 *
 */
public class MarkovModel {

   // Map of <kgram, chars following> pairs that stores the Markov model.
   private HashMap<String, String> model;
   
   //String of resulting text
   private String resultKgram;
   
   //Value of k
   private int kPrivate;

   // add other fields as you need them ...


   /**
    * Reads the contents of the file sourceText into a string, then calls
    * buildModel to construct the order K model.
    *
    * DO NOT CHANGE THIS CONSTRUCTOR.
    *
    */
   public MarkovModel(int K, File sourceText) {
      model = new HashMap<>();
      try {
         String text = new Scanner(sourceText).useDelimiter("\\Z").next();
         buildModel(K, text);
      }
      catch (IOException e) {
         System.out.println("Error loading source text: " + e);
      }
   }


   /**
    * Calls buildModel to construct the order K model of the string sourceText.
    *
    * DO NOT CHANGE THIS CONSTRUCTOR.
    *
    */
   public MarkovModel(int K, String sourceText) {
      model = new HashMap<>();
      buildModel(K, sourceText);
   }


   /**
    * Builds an order K Markov model of the string sourceText.
    */
   private void buildModel(int K, String sourceText) {
      sourceText = sourceText.replaceAll("/n", " ");
      sourceText = sourceText.replaceAll("/r", "");
      int i = 0;
      resultKgram = sourceText.substring(i, i + K);
      kPrivate = K;
      
      while (i + K < sourceText.length() + 1) {
         String current = sourceText.substring(i, i + K);
         String value;
         
         try {
            value = sourceText.substring(i + K, i + K + 1);
         }
         
         catch (StringIndexOutOfBoundsException e) {
            value = null;
         }
         
         if (model.putIfAbsent(current, value) != null) {
            String newValue = model.get(current);
            newValue += value;
            model.put(current, newValue);
         }
         i++;
      }
      
   }


   /** 
   * Returns the first kgram found in the source text. 
   */
   public String getFirstKgram() {
      if (resultKgram.length() > 0) {
         return resultKgram.substring(0, kPrivate);
      }
      return null;
   }


   /** 
    * Returns a kgram chosen at random from the source text. 
    */
   public String getRandomKgram() {
      Random r1 = new Random();
      
      Object[] kRandom = getAllKgrams().toArray();
      int place = r1.nextInt(model.size());
      String kString = kRandom[place].toString();
      return kString;
   }


   /**
    * Returns the set of kgrams in the source text.
    *
    * DO NOT CHANGE THIS METHOD.
    *
    */
   public Set<String> getAllKgrams() {
      return model.keySet();
   }


   /**
    * Returns a single character that follows the given kgram in the source
    * text. This method selects the character according to the probability
    * distribution of all characters that follow the given kgram in the source
    * text.
    */
   public char getNextChar(String kgram) {
      if (resultKgram.length() < 0) {
         return '\u0000';
      }
      
      String following = model.get(kgram);
      Random r1 = new Random();
      int place = r1.nextInt(following.length());
      char character = following.charAt(place);
      return character;
   }


   /**
    * Returns a string representation of the model.
    * This is not part of the provided shell for the assignment.
    *
    * DO NOT CHANGE THIS METHOD.
    *
    */
   @Override
    public String toString() {
      return model.toString();
   }

}
