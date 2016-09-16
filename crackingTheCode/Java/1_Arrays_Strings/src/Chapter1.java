import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.HashSet;
import java.util.ArrayList;


public class Chapter1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Running Chapter 1 Arrays and String examples");
		//1.1
		System.out.print("1.1 no Data Structure all unique, ");
		System.out.println(isUniqueNoDataStructure("abcdefghi") );
		System.out.print("1.1 no Data Structure not unique, ");
		System.out.println(isUniqueNoDataStructure("abcddefghi") );
		
		System.out.print("1.1 Data Structure all unique, ");
		System.out.println(isUniqueHashSet("abcdefghi") );
		System.out.print("1.1 Data Structure not unique, ");
		System.out.println(isUniqueHashSet("abcddefghi") );
		
		//1.2
		System.out.print("1.2 strings are permuation (expect true), ");
		System.out.println(checkPermutation("String once", "once String") );
		System.out.print("1.2 strings are permuation (expect false), ");
		System.out.println(checkPermutation("String twice", "once String") );
		
		//1.3
		System.out.print("1.3 urlify (expect Mr%20John%20Smith), ");
		System.out.println(urlify("Mr John Smith    ", 13) );
		
		//1.4
		System.out.print("1.4 palindrome Permutation (expect true), ");
		System.out.println(palindromePermutation("tact coa"));
		
		System.out.print("1.4 palindrome Permutation (expect true X4), ");
		System.out.println(isPalindrome("tact coa"));
		System.out.println(isPalindrome("dood"));
		System.out.println(isPalindrome("bib"));
		System.out.println(isPalindrome("civic"));
		
		System.out.print("1.4 palindrome Permutation (expect false X4), ");
		System.out.println(isPalindrome("tact coaz"));
		System.out.println(isPalindrome("doadz"));//adding just one letter to a 4 letter palindrome will still result in a palindrome
		System.out.println(isPalindrome("bibz"));
		System.out.println(isPalindrome("civicz"));
		
		System.out.print("1.5 OneAway (expect true X3, 1 false), ");
		System.out.println(isOneAway("pale", "ple"));
		System.out.println(isOneAway("pales", "pale"));
		System.out.println(isOneAway("pale", "bale"));
		System.out.println(isOneAway("pale", "bake"));
		
		System.out.print("1.6 string compresion (expect a2b1c5a3,abcdefghijklmno), ");
		System.out.println(compress("aabcccccaaa"));
		System.out.println(compress("abcdefghijklmno"));
		
		System.out.println("1.7 matrix rotation ");
		int[][] testMatrix = new int[][]{
			{1,2},
			{3,4}
		};
		
		int[][] expectedMatrix = new int[][]{
			{3,1},
			{4,2}
		};
		
		int[][] rotatedMatrix =rotateMatrix(testMatrix);
		//1,3
		//2,4
		
		System.out.println(rotatedMatrix.equals(expectedMatrix));
	}
	
	//1.1 no hash - array . sort could be considered cheating :) 
	private static Boolean isUniqueNoDataStructure(String testValue){
		Boolean testResult = true;		
		
		char[] charArray = testValue.toCharArray();
		Arrays.sort(charArray);
		for(int i = 1; i < charArray.length; i++){
			if(charArray[i-1] == charArray[i])
				return false;//could have also set the value of testResult and used break 
		}
		
		return testResult;
	}
	
	//1.1 Hashset is an implementation of a hash map with just the value vs key value pair
	private static Boolean isUniqueHashSet(String testValue){
		Boolean testResult = true;		
		
		char[] charArray = testValue.toCharArray();
		Set<Character> testSet = new HashSet<Character>(); 
		
		for(int i = 1; i < charArray.length; i++){
			if(! testSet.add(charArray[i]))
				return false;//could have also set the value of testResult and used break 
		}
		
		return testResult;
	}
	
	//1.2 - this solution doesn't take into account whitespace. 
	//If one of the strings had extra whitespace then it would miss that difference.
	//If that has to be considered a str length as used in the book would solve this problem
	private static Boolean checkPermutation(String firstStr, String secondStr){
		char[] charArray1 = firstStr.toCharArray();
		char[] charArray2 = secondStr.toCharArray();		
				
		Arrays.sort(charArray1);//interestingly this sort method is O(n log(n))
		Arrays.sort(charArray2);
		
		return Arrays.equals(charArray1, charArray2);
	}
	
	//1.3 urlify 
	private static String urlify(String value, int length){
		String[] valueArray = value.split(" ");//this removes the trailing spaces 
		
		//in this approach length is irrelevant other than to validate result
		return String.join("%20",valueArray);
	}
	
	//1.4 palindrome Permutation tact coa
	//assumption that the only special character in the method param is a space
	// also assume that the length is odd, for even would need a different approach 
	//Successful permutations are "taco cat" "atco cta" - notice o is center
	private static Boolean palindromePermutation(String words){		
		ArrayList<Integer> spaceIndexes = new ArrayList<Integer>();
		char[] charArray = words.toCharArray();
		
		
		for(int i=0; i < charArray.length; i++){
			//find space
			if(charArray[i] == ' ')
				spaceIndexes.add(i);
			else{
				String subString = "";
				if(i+1 != words.length()){
					String beginSubString = words.substring(0, i);
					String endSubString = words.substring(i+1, words.length());
					subString= beginSubString + endSubString;
				}else{
					subString = words.substring(0, i);
				}
				
				if(isPalindrome(charArray[i], subString))
					return true;
			}
						
		}
		return false;
	}
	
	//continuation of 1.4 there is an error in this that should be fix there are 4 and 6 letter palindromes
	private static Boolean isPalindrome(char centerChar, String remainingLetters){		
		//System.out.print("centerChar ");
		//System.out.println(centerChar);
		
		//System.out.print("remainingLetters ");
		//System.out.println(remainingLetters);
		
		String noSpaces = remainingLetters.replace(" ", "");
		char[] charArray = noSpaces.toCharArray();
		Arrays.sort(charArray);
		if((noSpaces.length() % 2) == 0){
			for(int i=0; i < charArray.length;i++){
				if(charArray[i]!= charArray[i+1])
					return false;
				i++;
			}
			return true;
		}			
		else{
			return false;	
		}
		
	}
	
	//1.4 take 2
	// assumption only special character is space
	// I'm re trying this after reading through the solutions I partially fell prey to thinking about permutations
	private static Boolean isPalindrome(String phrase){		
		String phraseNoSpaces = phrase.replace(" ", "");
		
		char[] charArray = phraseNoSpaces.toCharArray();
		Arrays.sort(charArray);
		double diffCount = 0;//cover even cases
		for(int i=1; i < charArray.length;i++){
			if(charArray[i]!= charArray[i-1])
				diffCount++;
		}
		
		//look at diff count in relation to length ...
		double halfLength = charArray.length / 2.0;
				
		if(diffCount < halfLength)
			return true;
		else
			return false;
		
	}
	
	//1.5 
	//assume that word one is not always the longest one
	//this also needs to be refactored into 2 or 3 methods
	//"pale", "ple"
	private static Boolean isOneAway(String wordOne, String wordTwo){
		if( java.lang.Math.abs(wordOne.length()-wordTwo.length()) > 1)
			return false;
		//could check for equality before looping through 
		char[] charArray1 = wordOne.toCharArray();
		char[] charArray2 = wordTwo.toCharArray();
		
		//determine which array is longer and loop through it
		if(charArray1.length > charArray2.length){
			//use array 2 to loop over to prevent array out of bounds exceptions
			for(int i =0; i < charArray2.length; i++ ){
				if(charArray2[i] != charArray1[i]){
					//test insert (Create a string by inserting char1[i] into word 2 at position i //then check equality
					StringBuilder sbWord2 = new StringBuilder(wordTwo);
					sbWord2.insert(i, charArray1[i]);
					if(sbWord2.toString().equals(wordOne))
							return true;
					//if charArray1[i] is removed from word 1 at position i would they be equal
					StringBuilder sbWord1 = new StringBuilder(wordOne);
					sbWord1.deleteCharAt(i);
					if(sbWord1.toString().equals(wordTwo))
						return true;
					else
						return false;//this is akin to a default case difference found but did not fix the problem					
				}
			}
			//there is only one character left in array 1 problem could be solved by adding 1
			return true;
		}else{
			//word 2 is longer or equal in length			
			for(int i =0; i < charArray1.length; i++ ){
				if(charArray1[i] != charArray2[i]){
					//test insert (Create a string by inserting char1[i] into word 1 at position i //then check equality
					StringBuilder sbWord1 = new StringBuilder(wordOne);					
					sbWord1.insert(i, charArray1[i]);
					if(sbWord1.toString().equals(wordTwo))
							return true;
					//if charArray1[i] is removed from word 2 at position i would they be equal
					StringBuilder sbWord2 = new StringBuilder(wordTwo);
					
					sbWord2.deleteCharAt(i);
					if(sbWord2.toString().equals(wordOne))
						return true;
					
					//equal would require substitution / replace check when difference found - this could be its own method
					StringBuilder sbWord1b = new StringBuilder(wordOne);
					sbWord1b.setCharAt(i, charArray2[i]);
					if(sbWord1b.toString().equals(wordTwo))
						return true;
					else
						return false;//this is akin to a default case difference found 3 rules but did not fix the problem					
				}
			}
			return true;
		}		
	}
	
	//1.6 compress string
	//I: aabcccccaaa
	//O: a2b1c5a3
	private static String compress(String longString){
		//todo trap for empty or null string
		
		char[] charArray = longString.toCharArray();
		
		StringBuilder sbResult = new StringBuilder();
		int currentCharCount = 1;
		char previousChar = charArray[0];
		for(int i=1; i< charArray.length; i++){
			if(charArray[i] != previousChar){
				sbResult.append(previousChar);
				sbResult.append(currentCharCount);
				previousChar = charArray[i];
				currentCharCount = 1;
			}else{
				currentCharCount++;
			}
			
		}
		
		//capture last char
		sbResult.append(previousChar);
		sbResult.append(currentCharCount);
		
		if(longString.length() < sbResult.toString().length())
			return longString;
		else
			return sbResult.toString();
	}
	
	//1.7
	private static int[][] rotateMatrix(int[][] matrix){
		if(matrix.length < 1)
			return matrix;
		
		int xLength = matrix[0].length;
		int yLength = matrix.length;
		
		int[][] newMatrix = new int[xLength][yLength];
		
		
		
		for(int x =0; x < matrix.length; ++x){
			
			if(x == matrix.length - 1){
				//last row
				for(int y =0; y < matrix[x].length; ++y){
					int targetYCell = y - 1;			
					int targetXCell = x;
					
					//column out of bounds
					if((y-1) < 0 ){
						targetYCell = 0;
						targetXCell = x-1;
					}
					
					//row out of bounds
					if((x-1) < 0 ){
						targetXCell = 0;
					}
					
					newMatrix[targetXCell][targetYCell] = matrix[x][y];
				}
				
			}else
				for(int y =0; y < matrix[x].length; ++y){
					int targetYCell = y + 1;			
					int targetXCell = x;
					
					//column out of bounds
					if((y+1) == xLength ){
						targetYCell = y;
						targetXCell = x+1;
					}
					
					//row out of bounds
					if((x+1) == yLength ){
						targetXCell = 0;
					}
					
					newMatrix[targetXCell][targetYCell] = matrix[x][y];
				}
		}
		
		
		return newMatrix;
	}
}


/*code worth reviewing

Character[] charArray1 = wordOne.chars().mapToObj(c -> (char)c).toArray(Character[]::new); 		
Character[] charArray2 = wordTwo.chars().mapToObj(c -> (char)c).toArray(Character[]::new);		
		
Set<Character> set1 = new HashSet<Character>(Arrays.asList(charArray1));
Set<Character> set2 = new HashSet<Character>(Arrays.asList(charArray2));

Set<Character> set1cp = new HashSet<Character>(set1);
Set<Character> set2cp = new HashSet<Character>(set2);

set1.removeAll(set2);
set2cp.removeAll(set1cp);

*/

/* Matrix rotation notes
//example input
		//x1,y1
		//x2,y2
		
		//target output
		//x2,x1
		//y2,y1
		
		// 0,0 -> 0,1
		
		//this actually does a rotate and mirror -- accidental success 
		/*
		//4,2
		//3,1
		for(int x =0; x < matrix.length; ++x){
			int targetYCell = yLength - 1 - x;
			for(int y =0; y < matrix[x].length; ++y){
				int targetXCell = xLength - 1 - y;
				newMatrix[targetXCell][targetYCell] = matrix[x][y];
			}
		}
		
		//mirror 
		//4,3
		//2,1
		for(int x =0; x < matrix.length; ++x){
			int targetXCell = yLength - 1 - x;
			for(int y =0; y < matrix[x].length; ++y){
				int targetYCell = xLength - 1 - y;
				newMatrix[targetXCell][targetYCell] = matrix[x][y];
			}
		}
*/