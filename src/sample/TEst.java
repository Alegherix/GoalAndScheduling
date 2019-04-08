package sample;

import java.io.File;
import java.io.IOException;

public class TEst {
    public static void main(String[] args) {
        File file = new File("Hallå.txt");
        if(!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            System.out.println("Created file");
        }

    }
}
