package sample;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class FileHandling {

    public static final String PATH_TO_LOGS = "C:\\Users\\Martin\\Documents\\JavaProjekt\\GoalAndScheduling\\Loggboksinlägg";

    /**
     * Used as a utility class for gathering information such as Creation time of the file, aswell as the current
     * Date, used for both logging the data, aswell as recording new data.
     * @param path
     * @return
     */
    public static String getCreationTime(Path path){
        String creationTime = "";
        try {
            BasicFileAttributes attributes = Files.readAttributes(path, BasicFileAttributes.class);
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
     * Returnerar Pathen till de 3 senaste generarade .txt filerna
     * @return
     */
    public static List<Path> getPathForNewestFiles(){
        File folder = new File(PATH_TO_LOGS);
        List<File> filesToReturn = Arrays.asList(Objects.requireNonNull(folder.listFiles()));
        Collections.reverse(filesToReturn);
        return filesToReturn.stream().map(File::toPath).collect(Collectors.toList()).subList(0,3);
    }


    public static String readFirstLine(Path path){
        return readTextFile(path).get(0);
    }

    public static String readTasksCompleted(Path path){
        return readTextFile(path).get(1);
    }

    public static String readTextFromFile(Path path){
        StringBuilder sb = new StringBuilder();
        List<String> strings =  readTextFile(path).stream().skip(2).collect(Collectors.toList());
        strings.forEach(s -> sb.append(s).append("\n"));
        return sb.toString();
    }

    private static List<String> readTextFile(Path path){
        File file = path.toFile();
        List<String> stringsToParse = null;
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
            return bf.lines().collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringsToParse;
    }

}


