import java.util.Scanner;

public class TextAnalyzer {

    static String text = "";

    static void analyzeText() {
        if (text.isEmpty()) {
            System.out.println("\nNo text available. Please enter text first.");
            return;
        }

        String[] words = text.trim().split("\\s+");

        int characters = text.length();
        int charactersWithoutSpaces = text.replace(" ", "").length();
        int wordCount = words.length;
        int sentenceCount = 0;
        int vowels = 0;
        int consonants = 0;
        int digits = 0;
        int spaces = 0;

        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);

            if (ch == ' ') {
                spaces++;
            }

            if (Character.isDigit(ch)) {
                digits++;
            }

            if (Character.isLetter(ch)) {
                char lower = Character.toLowerCase(ch);

                if (lower == 'a' || lower == 'e' || lower == 'i'
                        || lower == 'o' || lower == 'u') {
                    vowels++;
                } else {
                    consonants++;
                }
            }

            if (ch == '.' || ch == '!' || ch == '?') {
                sentenceCount++;
            }
        }

        String longestWord = "";
        String shortestWord = "";

        for (String word : words) {
            word = word.replaceAll("[^a-zA-Z]", "");

            if (word.isEmpty()) {
                continue;
            }

            if (longestWord.isEmpty() || word.length() > longestWord.length()) {
                longestWord = word;
            }

            if (shortestWord.isEmpty() || word.length() < shortestWord.length()) {
                shortestWord = word;
            }
        }

        String mostFrequentWord = "";
        int highestFrequency = 0;

        for (String word1 : words) {
            word1 = word1.toLowerCase().replaceAll("[^a-zA-Z]", "");

            if (word1.isEmpty()) {
                continue;
            }

            int count = 0;

            for (String word2 : words) {
                word2 = word2.toLowerCase().replaceAll("[^a-zA-Z]", "");

                if (word1.equals(word2)) {
                    count++;
                }
            }

            if (count > highestFrequency) {
                highestFrequency = count;
                mostFrequentWord = word1;
            }
        }

        System.out.println("\n===== Text Analysis =====");
        System.out.println("Characters             : " + characters);
        System.out.println("Characters (no spaces) : " + charactersWithoutSpaces);
        System.out.println("Words                  : " + wordCount);
        System.out.println("Sentences              : " + sentenceCount);
        System.out.println("Vowels                 : " + vowels);
        System.out.println("Consonants             : " + consonants);
        System.out.println("Digits                 : " + digits);
        System.out.println("Spaces                 : " + spaces);
        System.out.println("Longest Word           : " + longestWord);
        System.out.println("Shortest Word          : " + shortestWord);
        System.out.println("Most Frequent Word     : " + mostFrequentWord);
        System.out.println("Frequency              : " + highestFrequency);
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== Mini Text Analyzer =====");
            System.out.println("1. Enter Text");
            System.out.println("2. Analyze Text");
            System.out.println("3. Count Words");
            System.out.println("4. Count Vowels");
            System.out.println("5. Find Longest Word");
            System.out.println("6. Find Most Frequent Word");
            System.out.println("7. Exit");

            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    System.out.print("\nEnter your text: ");
                    text = scanner.nextLine();
                    System.out.println("Text saved successfully.");
                    break;

                case 2:
                    analyzeText();
                    break;

                case 3:
                    if (text.isEmpty()) {
                        System.out.println("\nPlease enter text first.");
                    } else {
                        String[] words = text.trim().split("\\s+");
                        System.out.println("\nTotal Words: " + words.length);
                    }
                    break;

                case 4:
                    if (text.isEmpty()) {
                        System.out.println("\nPlease enter text first.");
                    } else {
                        int vowelCount = 0;

                        for (int i = 0; i < text.length(); i++) {
                            char ch = Character.toLowerCase(text.charAt(i));

                            if (ch == 'a' || ch == 'e' || ch == 'i'
                                    || ch == 'o' || ch == 'u') {
                                vowelCount++;
                            }
                        }

                        System.out.println("\nTotal Vowels: " + vowelCount);
                    }
                    break;

                case 5:
                    if (text.isEmpty()) {
                        System.out.println("\nPlease enter text first.");
                    } else {
                        String[] words = text.trim().split("\\s+");
                        String longest = "";

                        for (String word : words) {
                            word = word.replaceAll("[^a-zA-Z]", "");

                            if (word.length() > longest.length()) {
                                longest = word;
                            }
                        }

                        System.out.println("\nLongest Word: " + longest);
                    }
                    break;

                case 6:
                    if (text.isEmpty()) {
                        System.out.println("\nPlease enter text first.");
                    } else {
                        String[] words = text.trim().split("\\s+");
                        String mostFrequent = "";
                        int highestCount = 0;

                        for (String word1 : words) {

                            word1 = word1.toLowerCase()
                                    .replaceAll("[^a-zA-Z]", "");

                            if (word1.isEmpty()) {
                                continue;
                            }

                            int count = 0;

                            for (String word2 : words) {

                                word2 = word2.toLowerCase()
                                        .replaceAll("[^a-zA-Z]", "");

                                if (word1.equals(word2)) {
                                    count++;
                                }
                            }

                            if (count > highestCount) {
                                highestCount = count;
                                mostFrequent = word1;
                            }
                        }

                        System.out.println("\nMost Frequent Word: " + mostFrequent);
                        System.out.println("Frequency: " + highestCount);
                    }
                    break;

                case 7:
                    System.out.println("\nProgram closed.");
                    scanner.close();
                    return;

                default:
                    System.out.println("\nInvalid choice. Try again.");
            }
        }
    }
}