import java.util.Scanner;

public class GCWeek1Capstone {

	public static void main(String[] args) {
		
		/*Build Specs:
		 * Able to translate sentences to pig latin
		 * asks user if they want to continue & does with correct input
		 * doesn't accept blank input (I think)
		 * Capitalizes beginning of sentence
		 * if word is all caps or all lower, keeps it that way
		 * if word has a capital letter but is not all caps (proper nouns, e.g.) moves the capital to the 
		 * 			beginning of the word
		 * doesn't accept input that has numbers or non-punctuation symbols
		 * punctuation and contractions are 'allowed', but don't get modified in any special way
		 * */

		Scanner scnr = new Scanner(System.in);
		String userString;
		String userCont = "y";
		String translatedString = "";
		String spaceChar = " ";

		System.out.println("Welcome to the Pig Latin Translator!");

		do {
			System.out.println("Enter a line to be translated:");
			userString = scnr.nextLine();
//			userString = userString.toLowerCase(); taken out for extended challenge
			
			if (userString.length() > 0 && !hasSymbolsOrNumbers(userString)) {
				
				for (int i = 0; i < userString.length(); i++) {
					if (userString.charAt(i) == ' ' ) {
						String tempWord = "";
						
						if (capsCheck(userString.substring(0, i)) && !allCapsCheck(userString.substring(0, i))) {
							tempWord = convertWordCaps(convertWord(userString.substring(0, i)));
							translatedString += tempWord + " ";
							
						} else {
							
							tempWord = convertWord(userString.substring(0, i));
							translatedString += tempWord + " ";
						}
						userString = userString.substring(i + 1);
						i = 0;

					} else if (!userString.contains(spaceChar)) {
						
						String tempWord = "";
						if (capsCheck(userString.substring(0, i)) && !allCapsCheck(userString.substring(0, i))) {
							
							tempWord = convertWordCaps(convertWord(userString.substring(0, i)));
							translatedString += tempWord;
							
						} else {
							
							tempWord = convertWord(userString);
							translatedString += tempWord;
						}
						i = userString.length();
					}
				}
				
			} else if (hasSymbolsOrNumbers(userString)) {
				System.out.println("No symbols or numbers, please.");
				translatedString = "";
				
			} else {
				System.out.println("I told you to enter a line, bozo");
				translatedString = "";
			}
			if (translatedString.length() > 0) {
				System.out.println(convertWordCaps(translatedString)); /* start sentence with capital letter */
				translatedString = "";
			}

			System.out.println("Translate another line? (y/n):");
			userCont = scnr.nextLine();
			
		} while (userCont.startsWith("y"));
		
		System.out.println("Goodbye!");

		scnr.close();

	}

	public static String convertWord(String userWord) {
		String wordOutput = userWord;
		for (int i = 0; i < wordOutput.length(); i++) {
//			String wordOutput = userWord;
			if (isVowel(userWord.charAt(i)) && i == 0) {
				wordOutput = userWord + "way";
				if (capsCheck(wordOutput)) {
					wordOutput = convertWordCaps(wordOutput);
				}
//				return wordOutput;
				break;

			} else if (isVowel(userWord.charAt(i)) && i != 0) {
				wordOutput = userWord.substring(i) + userWord.substring(0, i) + "ay";
//				return wordOutput;
				break;
			}
		}
		return wordOutput;
	}

	public static boolean isVowel(char letter) {

		if ((letter == 'a') || (letter == 'e') || (letter == 'i') || (letter == 'o') || (letter == 'u')
				|| (letter == 'A') || (letter == 'E') || (letter == 'I') || (letter == 'O') || (letter == 'U')) {
			return true;
		} else {
			return false;
		}

	}

	public static boolean hasNumber(char letter) { /* integrated this code into another thing */
		if (letter >= 48 && letter <= 57) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean hasPunc(char letter) { /* didn't end up deciding to use this */
		if (letter == '.' || letter == ',' || letter == ':' || letter == ';' || letter == '?' || letter == '!') {
			return true;
		} else {
			return false;
		}
	}

	public static boolean hasSymbolsOrNumbers(String userWord) { /* checks if string contains any symbols or numbers */
		int charCount = 0;
		for (int i = 0; i < userWord.length(); i++) {
			char letter = userWord.charAt(i);
			if ((letter >= 48 && letter <= 57) || (letter >= 35 && letter <= 38) || (letter >= 40 && letter <= 43)
					|| letter == 47 || (letter >= 60 && letter <= 62) || letter == 64 || (letter >= 91 && letter <= 96)
					|| (letter >= 123 && letter <= 126)) {
				charCount += 1;
			}
		}
		if (charCount > 0) {
			return true;
		} else {
			return false;
		}
	}

	public static String convertWordCaps(String userWord) { /*
															 * ensures a word starts with a capital letter, for
															 * beginning of sentence and proper nouns
															 */
		String firstLetter;
		userWord = userWord.toLowerCase();
		firstLetter = userWord.substring(0, 1);
		firstLetter = firstLetter.toUpperCase();
		userWord = firstLetter + userWord.substring(1);
		return userWord;

	}

	public static boolean capsCheck(String userWord) { /* checks if word has any capital letters - verified to work */
		for (int i = 0; i < userWord.length(); i++) {
			if (userWord.charAt(i) >= 65 && userWord.charAt(i) <= 90) {
				return true;
			}
		}
		return false;
	}

	public static boolean allCapsCheck(String userWord) { /* checks if word is all capital letters - verified to work */
		int capsCount = 0;
		for (int i = 0; i < userWord.length(); i++) {
			if (userWord.charAt(i) >= 65 && userWord.charAt(i) <= 90) {
				capsCount += 1;
			}
		}
		if (capsCount == userWord.length()) {
			return true;
		} else {
			return false;
		}
	}

}
