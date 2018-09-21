import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
Author: Prince Agbebiyi
For Keyur Patel - Emergitel, Canada.
 */
public class Main {
    
    public static void main(String[] args) {
        
        // question 1
        System.out.println(isPalindrome("not a palindrome"));
        System.out.println(isPalindrome("A Santa dog lived as a devil God at NASA."));
       
        // question 2
        System.out.println(Arrays.asList(sortIt(new String[]{"001", "Alpha", "Gamma", "Peter", "432", "Beta", "Palindrome", "123.4", "Pit"})));
    }
    
    /*
    1. Write a function that returns a boolean indicating whether or not a string is a palindrome.
     */
    static boolean isPalindrome(final String input) {
        //Safe check
        if (input == null || input.isEmpty()) {
            return false;
        }
      
        /*
        we can improve performance by assessing half the input by doing less iteration.
        If e.g ABBA would still be a palidrone as AB(first 2 characters)= AB(last 2 characters)
         */
        String normalizedInput = input.replaceAll("[^a-zA-Z0-9]","").toLowerCase();
        int stringLength = normalizedInput.length();
        int halfStringLength = stringLength / 2;
       
        for (int x = 0; x < halfStringLength; x++) {
            if (normalizedInput.charAt(x) != normalizedInput.charAt(stringLength - x - 1)) {
                return false;
            }
        }
        return true;
    }
    
    
    /*
    2.2. Write a function that takes array of strings as an argument and returns a new array that contains the same items as the original array, but in the following
        order: first, all strings that start with a digit (0-9), sorted in descendent
        order by their numeric value, then all strings that start with anything except
        the capital letter "P" in alphabetical order, and then all strings that start
        with the "P" in the order of the string length.
        
        // should print ['432', '123.4', '001', 'Alpha', 'Beta', 'Gamma', 'Pit', 'Peter', 'Palindrome']
        //              [432, 123.4, 001, Alpha, Beta, Gamma, Pit, Peter, Palindrome]
    */
    
    
    static String[] sortIt(String[] input){
    
        /*
        I will attempt to use divide and conquer by splitting data set based on types to reduce complexity.
         */
        List<String> numerals = new ArrayList<>();
        List<String> pwords = new ArrayList<>();
        List<String> words = new ArrayList<>();
        
        /*
        1. We iterate the input once and break large data into subsets for easier manipulation
        2. Convert array to stream to take advantage of Java processor Stream feature
         */
        Arrays.stream(input).forEach(s -> {
            if (s.matches("[0-9.]+")) { //all numbers
                numerals.add(s);
            } else {
                if(s.startsWith("P")){
                    pwords.add(s);
                }else {
                    words.add(s);
                }
            }
        });
        
        //now we have 3 lists with unique data type, now we can sort based on requirement
        numerals.sort(Comparator.comparing(Double::parseDouble).reversed());
        pwords.sort(Comparator.comparing(String::length));
        words.sort(Comparator.comparing(s->s));
        //Join the array into one and return
        List<String> result = new ArrayList<>();
        result.addAll(numerals);
        result.addAll(words);
        result.addAll(pwords);
        return result.toArray(new String[input.length]);
    }

}
