//package PythonGlossary;

import javax.lang.model.type.ArrayType;
import java.lang.reflect.Array;
import java.util.*;
import java.io.*;

public class vocabDrill{
    public static void main(String []args){
        Scanner keyb = new Scanner(System.in);
        String filename = "pythonGlossary.csv";
        Map<String,String>vocabMap = readCsvFileIntoMap(filename);
        //System.out.println(vocabMap);
        //askAQuesiton(vocabMap);
        showFourQuestions(vocabMap);
        questionsFromChapter(filename);
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


    
    static void showFourQuestions(Map<String,String>vocabMap){
        int count = 0;
        for(int j = 0; j<4; j++){
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
        String validAnswers = "ABCD";
        System.out.print("Enter your answer: ");    
        Scanner input = new Scanner(System.in);
        String userInput = input.next().toUpperCase();    
        while(!validAnswers.contains(userInput)){
            System.out.println("Invalid input. Please select 'A','B','C', or 'D'\n");
            System.out.print("Enter your answer: ");
            userInput = input.next().toUpperCase();
        }
        //Check to see if the user got the question right
        switch(userInput){
                case "A":
                    if(options.get(0).equals(correctWord)){
                        System.out.println("Correct");
                        count = count + 1;
                    }else{
                        System.out.println("Incorrect. The right answer was: "+correctWord);
                    }
                    break;
                case "B":
                    if(options.get(1).equals(correctWord)){
                        System.out.println("Correct");
                        count = count + 1;
                    }else{
                        System.out.println("Incorrect. The right answer was: "+correctWord);
                        
                    }
                    break;
                case "C":
                    if(options.get(2).equals(correctWord)){
                        System.out.println("Correct");
                        count = count + 1;
                    }else{
                        System.out.println("Incorrect. The right answer was: "+correctWord);
                    }
                    break;
                case "D":
                    if(options.get(3).equals(correctWord)){
                        System.out.println("Correct");
                        count = count + 1;
                    }else{
                        System.out.println("Incorrect. The right answer was: "+correctWord);
                    }
                    break;
            }
                   
        }
        System.out.printf("You got %d question(s) out of 4 questions correct.\n", count);
    }

    static void questionsFromChapter(String filename){
        
            int count = 0;
        
            //ArrayList<Integer> numsFromChap = new ArrayList<>();
            //numsFromChap.addAll(Arrays.asList(1, 2, 3,4,5,6,7,8,9,10,11,12,13,14,15));
            //System.out.println(numsFromChap);

            
            //ask which chapter the user wants to study
            System.out.println("Which chapter would you like to study?(1-15)");
            System.out.print("Select your chapter: ");
            Scanner input = new Scanner(System.in);
            int chapterNumber = input.nextInt()-1;
   
            
   
            //present 4 questions from the selected chapter
            //divide the questions into lists of terms and definitions by chapter
            ArrayList<ArrayList<String>> allChapterTerms = new ArrayList<>();
            ArrayList<ArrayList<String>> allChapterDefinitions = new ArrayList<>();
            for (int j = 0; j < 15; j++) {
                ArrayList<String> chapterTerms = new ArrayList<>();
                ArrayList<String> chapterDefinitions = new ArrayList<>();
                allChapterTerms.add(chapterTerms);
                allChapterDefinitions.add(chapterDefinitions);
            }
            try{
                Scanner reader = new Scanner(new File(filename));
                String headers = reader.nextLine();
                while(reader.hasNext()){
                    String line = reader.nextLine();
                    String[] parts = line.split(",");
                    String chapterNum = parts[0];
                    String termKey = parts[1];
                    String definitionValue = parts[2];
                    int index = Integer.parseInt(chapterNum) - 1 ;
                    allChapterTerms.get(index).add(termKey);
                    allChapterDefinitions.get(index).add(definitionValue);
                }
                reader.close();
            }catch(Exception e){
                e.printStackTrace();
                System.err.println("Something went wrong");
                System.exit(0);
            }
            for (int i = 0; i < 4; i++) {

                //making a list of the options to show the user
                ArrayList<String> options = new ArrayList<>();
                //choosing a random word as the "correct" word and 3 other "incorrect" words
                int indexForRandomDefinition = new Random().nextInt(allChapterTerms.get(chapterNumber).size());
                String correctWord = allChapterTerms.get(chapterNumber).get(indexForRandomDefinition);
                String correctDefinition = allChapterDefinitions.get(chapterNumber).get(indexForRandomDefinition);
                System.out.println("Which of the following means: "+correctDefinition);
                allChapterDefinitions.get(chapterNumber).remove(indexForRandomDefinition);
                options.add(correctWord);
                //removing the correct and incorrect words from the list after choosing them, so they cannot be chosen again
                allChapterTerms.get(chapterNumber).remove(indexForRandomDefinition);
                for (int j = 0; j < 3; j++) {
                    int indexForIncorrectWord = new Random().nextInt(allChapterTerms.get(chapterNumber).size());
                    //System.out.println("This is the index: " + indexForIncorrectWord);
                    String falseWord = allChapterTerms.get(chapterNumber).get(indexForIncorrectWord);
                    options.add(falseWord);
                    allChapterTerms.get(chapterNumber).remove(indexForIncorrectWord);
                    allChapterDefinitions.get(chapterNumber).remove(indexForIncorrectWord);

                }
                //randomizing the output of the options and printing
                Collections.shuffle(options);
                char letter = 65;//This is to print A, B, C, and D in font of the options
                for(int j = 0; j<4;j++){
                    System.out.println(letter+") "+options.get(j));
                    letter++;
                }
                //asking user for an answer
                String validAnswers = "ABCD";
                System.out.print("Enter your number: ");
                String userInput = input.next().toUpperCase();
                while(!validAnswers.contains(userInput)){
                    System.out.println("Invalid input. Please select 'A','B','C', or 'D'\n");
                    System.out.print("Enter your answer: ");
                    userInput = input.next().toUpperCase();
                }
                //Check to see if the user got the question right
                switch(userInput){
                    case "A":
                        if(options.get(0).equals(correctWord)){
                            System.out.println("Correct");
                            count = count + 1;
                        }else{
                            System.out.println("Incorrect. The right answer was: "+correctWord);
                        }
                        break;
                    case "B":
                        if(options.get(1).equals(correctWord)){
                            System.out.println("Correct");
                            count = count + 1;
                        }else{
                            System.out.println("Incorrect. The right answer was: "+correctWord);

                        }
                        break;
                    case "C":
                        if(options.get(2).equals(correctWord)){
                            System.out.println("Correct");
                            count = count + 1;
                        }else{
                            System.out.println("Incorrect. The right answer was: "+correctWord);
                        }
                        break;
                    case "D":
                        if(options.get(3).equals(correctWord)){
                            System.out.println("Correct");
                            count = count + 1;
                        }else{
                            System.out.println("Incorrect. The right answer was: "+correctWord);
                        }
                        break;
                }

            }
            System.out.printf("You got %d question(s) out of 4 questions correct.\n", count);
           
            
    }      
        
}// class 









































































































// import java.util.*;
// import java.io.*;

// public class VocabDrill{
//     public static void main(String []args){
//         Scanner keyb = new Scanner(System.in);
//         String filename = "pythonGlossary.csv";
//         Map<String,String>vocabMap = readCsvFileIntoMap(filename);
//         //System.out.println(vocabMap);
//         presentOneQuestion(vocabMap);
        
//     }// main
//     static Map<String,String>readCsvFileIntoMap(String filename){
//         Map<String,String> map = new HashMap<>();
//             try{
//                 Scanner keyb = new Scanner(new File(filename));
//                 String headers = keyb.nextLine();
//                 while(keyb.hasNext()){
//                     String line = keyb.nextLine();
//                     String[] parts = line.split(",");
//                     String chapterNum = parts[0];
//                     String termKey = parts[1];
//                     String definitionValue = parts[2];
//                     map.put(termKey,definitionValue);
//             }
//                     keyb.close();
//             }catch(Exception e){
//                 e.printStackTrace();
//                 System.err.println("Something went wrong");
//                 System.exit(0);
//         }

//         return map;
//     }
    
//     static void presentOneQuestion(Map<String,String>vocabMap){
//         //System.out.println("This is the map\n"+ vocabMap);
//         Object[] randomKey = vocabMap.keySet().toArray();
// 		Object termToUse = randomKey[new Random().nextInt(randomKey.length)];
//         //System.out.println("Your word is: " + termToUse);
        
        
//     }
    
// }// class