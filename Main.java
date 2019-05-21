/**
 * StudentID: 20119571
 * Name: Trong Phuong Nguyen
 * Subject: CS2ALG
 */

import java.io.*;
import java.util.ArrayList;

public class Main {
    private static String fileInput1;
    private static String fileInput2;
    private static String fileOutput1;
    private static String fileOutput2;
    private static String regexTemp;

    private static ArrayList<String> regexWords = new ArrayList<>();
    private static LexiconSystem ls = new LexiconSystem();

    public static void main(String[] args) throws IOException {
        fileInput1 = args[0];
        fileOutput1 = args[1];
        fileInput2 = args[2];
        fileOutput2 = args[3];
        run();
    }

    public static void run() throws IOException{
        PrintWriter printWriter = new PrintWriter(fileOutput1, "UTF-8");
        PrintWriter printWriter2 = new PrintWriter(fileOutput2,"UTF-8");
        filterFile(fileInput1);
        ls.triggerFinding();
        ls.displayElements(printWriter);
        readSecondTestFile(fileInput2);
        for(int i = 0; i < regexWords.size(); i++){
            printWriter2.println(regexWords.get(i));
            regexTemp = convertStarToRegex(convertMarkToRegex(regexWords.get(i)));
            ls.matchRegexWord(regexTemp, printWriter2);
            printWriter2.println("\n");
        }
        printWriter.close();
        printWriter2.close();
    }

    public static String convertMarkToRegex(String patternInput){
        return patternInput.replaceAll("[?]",".");
    }

    public static String convertStarToRegex(String patternInput){
        return patternInput.replaceAll("[*]","[a-z]{0,}");
    }

    public static void readSecondTestFile(String fileName){
        String line = null;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {

                //checking empty line in sample text files
                if (line.length()>0){
                    regexWords.add(line);
                }
            }

            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Read file and filter content
     * @param fileName sample text file
     */
    public static void filterFile(String fileName){
        String line = null;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                line = removeCharacters(line);
                line = toLowerCase(line);
                line = removeSpaces(line);

                //checking empty line in sample text files
                if (line.length() > 0){
                    wordFilter(line);
                }
            }

            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String removeCharacters(String inputSource){
        return inputSource.replaceAll("[^a-zA-Z]"," ");
    }

    public static String toLowerCase(String inputSource){
        return inputSource.toLowerCase();
    }

    public static String removeSpaces(String inputSource){
        return inputSource.trim().replaceAll(" +"," ");
    }

    public static void wordFilter(String inputSource){
        String[] wordBreak = inputSource.split(" ");
        for (int i = 0; i < wordBreak.length; i++){
            if(ls.contains(wordBreak[i]) == null){
                ls.insert(wordBreak[i]);
            } else {
                Lexicon temp = ls.contains(wordBreak[i]);
                temp.setCount(temp.getCount() + 1);
            }
        }
    }

    public static boolean differBy1Character(String word1, String word2){
        int differCount = 0;
        if(word1.length() == word2.length()){
            for(int i = 0; i < word1.length(); i++) {
                if(word1.charAt(i) != word2.charAt(i)){
                    if(differCount == 1){
                        return false;
                    }
                    differCount++;
                }
            }
        }
        if(differCount == 1){
            return true;
        }
        return false;
    }
}
