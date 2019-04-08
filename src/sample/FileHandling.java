package sample;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.*;

public class FileHandling {

    /**
     * Used as a utility class for gathering information such as Creation time of the file, aswell as the current
     * Date, used for both logging the data, aswell as recording new data.
     * @param path
     * @return
     */
    public static String getCreationTime(String path){
        String creationTime = "";
        try {
            Path file = Paths.get(path);
            BasicFileAttributes attributes = Files.readAttributes(file, BasicFileAttributes.class);
            attributes.creationTime();
            return new SimpleDateFormat("dd/MM-yy").format(attributes.creationTime().toMillis());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return creationTime;
    }

    public static String getCurrentDate(String format){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        return simpleDateFormat.format(cal.getTime());
    }

    /**
     * Returnerar de 3 senaste .txt filerna för att användas
     * @return
     */
    public static List<File> getNewestFiles(){
        File folder = new File("C:\\Users\\Martin\\Documents\\JavaProjekt\\GoalAndScheduling\\Loggboksinlägg");
        List<File> filesToReturn = Arrays.asList(Objects.requireNonNull(folder.listFiles()));
        Collections.reverse(filesToReturn);
        return filesToReturn.subList(0,3);

    }

    public static void main(String[] args) {
        for (File newestFile : getNewestFiles()) {
            System.out.println(newestFile.getName());
        }
    }
}


