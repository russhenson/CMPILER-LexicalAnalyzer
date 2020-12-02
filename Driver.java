import java.io.*;
import java.util.*;


public class Driver {
    public static void main(String[] args) throws IOException {
        FileReader inputfile = new FileReader("D:/SCHOOL SHIT/4TH YR/TERM 1/CMPILER/Lexical Analyzer/inputfile.txt");
        FileWriter outputfile = new FileWriter("D:/SCHOOL SHIT/4TH YR/TERM 1/CMPILER/Lexical Analyzer/outputfile.txt");
        BufferedReader reader = new BufferedReader (inputfile);
        BufferedWriter writer = new BufferedWriter(outputfile);

        
        
        ArrayList<String> tokens = new ArrayList<>(); 
        LexicalAnalyzer lAnalyzer = new LexicalAnalyzer();
        
        String input = reader.readLine();
        while (input != null){ // reads and process per line
            
            input = input.concat(" ");

            tokens = lAnalyzer.process(input);

            for(int i = 0; i < tokens.size(); i++ ){
                
                writer.write(lAnalyzer.tokenIdentifier(tokens.get(i)) + " ");
            }
            writer.write("\n");
            input = reader.readLine();
        }
        

        reader.close();
        writer.close();

        System.out.println("Success");

    }

    

}