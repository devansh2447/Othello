import java.io.*;
//Logs game to disk, includes methods to get the log file path (both initial log and backup log), to process a file, to write to a file, and to finalise a log by putting it into an easier to read format.

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;
public class Logger
{
    public static String processFile (String filename)
    throws FileNotFoundException, IOException {
        String forReturn = "";
        FileReader fileReader = new FileReader (filename);
        BufferedReader in = new BufferedReader (fileReader);
        while (true) {
            String s = in.readLine();
            if (s == null) break;
            forReturn = forReturn + s;
        }
        return forReturn;
    }

    public static void write(String path, String content) throws IOException, FileNotFoundException {
        content = processFile(path) + content;   
        FileWriter fw = new FileWriter(path);    
        fw.write(content);
        fw.close();
    }

    public static void createFile(String path) throws IOException {
        File toCreate = new File(path);
        toCreate.createNewFile();
    }

    public static void replace(String interpret) throws IOException {
        String write = "";
        for(int iter = 0; iter < interpret.length(); iter++){
            if(interpret.charAt(iter) == '!'){
                interpret = interpret.substring(0, iter) + "\n" + interpret.substring(iter + 1, interpret.length());
            }
        }
        write = interpret;
        FileWriter fw = new FileWriter(getPath());    
        fw.write(write);
        fw.close();
    }
    
    public static void replaceBackup(String interpret) throws IOException {
        String write = "";
        for(int iter = 0; iter < interpret.length(); iter++){
            if(interpret.charAt(iter) == '!'){
                interpret = interpret.substring(0, iter) + "\n" + interpret.substring(iter + 1, interpret.length());
            }
        }
        write = interpret;
        FileWriter fw = new FileWriter(getFinalPath());    
        fw.write(write);
        fw.close();
    }

    public static String getPath(){
        String os = System.getProperty("os.name");
        String user = System.getProperty("user.name");
        if(os.contains("Win")){
            return "C:\\Users\\" + user + "\\Desktop\\othellog.txt";
        }
        else if(os.contains("Mac")){
            return "/Users/" + user + "/Desktop/othellog.txt" ;
        }
        else{
            return "/home/" + user + "/Desktop/othellog.txt" ;
        }
    }

    public static String getLogPath(){
        String os = System.getProperty("os.name");
        String user = System.getProperty("user.name");
        if(os.contains("Win")){
            return "C:\\Users\\" + user + "\\Desktop\\othellogs\\";
        }
        else if(os.contains("Mac")){
            return "/Users/" + user + "/Desktop/othellogs/" ;
        }
        else{
            return "/home/" + user + "/Desktop/othellogs/" ;
        }
    }

    public static String getFinalPath(){
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");  
        LocalDateTime now = LocalDateTime.now();
        String name = "othellog_" + format.format(now) + ".txt";
        return getLogPath() + name;
    }

    public static void createDirectory(){
        new File(getLogPath()).mkdirs();
    }
}
