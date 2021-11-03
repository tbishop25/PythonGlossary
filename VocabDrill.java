package PythonGlossary;

import javax.lang.model.type.ArrayType;
import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

public class VocabDrill{
    public static void main(String []args){
        Scanner keyb = new Scanner(System.in);
        String filename = "pythonGlossary.csv";
        Map<String,String>vocabMap = readCsvFileIntoMap(filename);
        askAQuesiton(vocabMap);
        
    }// main
    static Map<String,String>readCsvFileIntoMap(String filename){
        Map<String,String> map = new HashMap<>();
            try{
                Scanner keyb = new Scanner(new File(filename));
                String headers = keyb.nextLine();
                while(keyb.hasNext()){
                    String line = keyb.nextLine();
                    String[] parts = line.split(",");
                    String chapterNum = parts[0];
                    String termKey = parts[1];
                    String definitionValue = parts[2];
                    map.put(termKey,definitionValue);
            }
                    keyb.close();
            }catch(Exception e){
                e.printStackTrace();
                System.err.println("Something went wrong");
                System.exit(0);
        }

        return map;
    }

    static void askAQuesiton(Map<String,String>vocabMap){
        ArrayList<String> words = new ArrayList<>();
        ArrayList<String> definitions = new ArrayList<>();
        //initializing lists from the map to make random selection easier
        for(String x : vocabMap.keySet()){
            words.add(x);
        }
        for(String x : vocabMap.values()){
            definitions.add(x);
        }
        //making a list of the options to show the user
        ArrayList<String> options = new ArrayList<>();
        //choosing a random word as the "correct" word and 3 other "incorrect" words
        int indexForRandomDefinition = new Random().nextInt(definitions.size());
        String correctWord = words.get(indexForRandomDefinition);
        options.add(correctWord);
        //removing the correct and incorrect words from the list after choosing them, so they cannot be chosen again
        words.remove(indexForRandomDefinition);
        for (int i = 0; i < 3; i++) {
            int indexForIncorrectWord = new Random().nextInt(definitions.size());
            String falseWord = words.get(indexForIncorrectWord);
            options.add(falseWord);
            words.remove(indexForIncorrectWord);
        }

        System.out.println("Which of the following means: "+definitions.get(indexForRandomDefinition));
        //randomizing the output of the options and printing
        Collections.shuffle(options);
        char letter = 65;//This is to print A, B, C, and D in font of the options
        for(int i = 0; i<4;i++){
            System.out.println(letter+") "+options.get(i));
            letter++;
        }
        //asking user for an answer
        Scanner input = new Scanner(System.in);
        String userInput = input.next().toUpperCase();
        if(!Character.isLetter(userInput.charAt(0))&&(!userInput.equals("A"))){
            System.out.println("Invalid input. Please select 'A','B','C', or 'D'");
            userInput = input.next().toUpperCase();
        }
        //Check to see if the user got the question right
        switch(userInput){
                case "A":
                    if(options.get(0).equals(correctWord)){
                        System.out.println("correct");
                    }else{
                        System.out.println("incorrect. The right answer was: "+correctWord);
                    }
                    break;
                case "B":
                    if(options.get(1).equals(correctWord)){
                        System.out.println("correct");
                    }else{
                        System.out.println("incorrect. The right answer was: "+correctWord);
                    }
                    break;
                case "C":
                    if(options.get(2).equals(correctWord)){
                        System.out.println("correct");
                    }else{
                        System.out.println("incorrect. The right answer was: "+correctWord);
                    }
                    break;
                case "D":
                    if(options.get(3).equals(correctWord)){
                        System.out.println("correct");
                    }else{
                        System.out.println("incorrect. The right answer was: "+correctWord);
                    }
                    break;
            }

    }
    
}// class