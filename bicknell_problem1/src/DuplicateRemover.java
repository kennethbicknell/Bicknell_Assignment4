import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Formatter;
import java.util.HashSet;
import java.util.Scanner;

public class DuplicateRemover {
    private HashSet<String> uniqueWords;

    public DuplicateRemover(){
        uniqueWords = new HashSet<String>();
    }

    public void remove(String dataFile){
        File data = new File(dataFile);
        try(Scanner input = new Scanner(data)){
            
            while(input.hasNext()){
                String nextWord = input.next();
                nextWord = nextWord.replaceAll("\\p{Punct}", "");
                uniqueWords.add(nextWord.toLowerCase());
            }

        }catch (FileNotFoundException notfound){
            
            System.err.printf("Could not find the file \"%s\" in the current directory\n", dataFile);
        
        }catch (SecurityException security){
            
            System.err.printf("You dont have permission to access \"%s\".\n", dataFile);
        
        }catch (IOException e){
            
            System.err.printf("There was an unknown error while trying to open the file for read\n");
        
        }
        
    }

    public void write(String dataFile){
        remove(dataFile);
        
        File data = new File("output1.txt");

        try(Formatter output = new Formatter(data)){

            for (String word : uniqueWords){
                output.format("%s ", word);
            }

        }catch (FileNotFoundException e){

            System.err.printf("There was an error when opening \"output1\".txt for write.");

        }catch (SecurityException e){

            System.err.printf("You dont have permission to access \"output1.txt\".\n");

        }catch (IOException e){

            System.err.printf("There was an unknown error while trying to open the file for write\n");

        }

    }
}