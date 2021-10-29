import java.util.*;
import java.io.*;

public class VocabDrill{
    public static void main(String []args){
        Scanner keyb = new Scanner(System.in);
        String filename = "pythonGlossary.csv";
        Map<String,String>vocabMap = readCsvFileIntoMap(filename);
        //System.out.println(vocabMap);
        
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
    
}// class