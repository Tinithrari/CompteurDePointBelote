package groupe_ipi_belote.compteurBelote.Additional_features;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import groupe_ipi_belote.compteurBelote.Exceptions_core.CustomExceptionTemplate;

public class Logs {
    private BufferedWriter buffer;
    private FileWriter fw;

    private File file;
    private String currentTime;

    public static final String __PATH = System.getProperty("user.dir") + "/Logs/";
    public static final String CURRENT_DAY = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());


    public Logs(){
        detectFile();
    }

    public void writeIntoFile(CustomExceptionTemplate cet){

        try{
            currentTime = new SimpleDateFormat("HHmmss").format(Calendar.getInstance().getTime());

            fw.append("[" + currentTime + "]@Er" + cet.getErrCode() + ":@Me" + cet.getMessage() + ":FROM:" + cet.getCause() + " [TRACE] ");

            for (StackTraceElement s : cet.getStackTrace()){
                fw.append("@Cl" + s.getClassName() + "@Fl" + s.getFileName() + "@Mt" + s.getMethodName() + "@Ln" + s.getLineNumber() + "@@");
            }

            fw.flush();

        } catch(Exception e){
            closeWriter();
        }

    }

    public void writeIntoFile(Exception e){
        try{
            currentTime = new SimpleDateFormat("HHmmss").format(Calendar.getInstance().getTime());

            fw.append("[" + currentTime + "]@Me" + e.getMessage() + ":FROM:" + e.getCause() + " [TRACE] ");

            for (StackTraceElement s : e.getStackTrace()){
                fw.append("@Cl" + s.getClassName() + "@Fl" + s.getFileName() + "@Mt" + s.getMethodName() + "@Ln" + s.getLineNumber() + "@@");
            }

            fw.flush();

        } catch(Exception ex){
            closeWriter();
        }

    }

    private void detectFile(){
        File fileExistence = new File(__PATH);

        if(!fileExistence.exists() || !fileExistence.isDirectory() ){
            fileExistence.mkdirs();
        }

        File fileDetected;
        File[] listOfFiles = fileExistence.listFiles();

        final ArrayList<File> _VALABLE = new ArrayList<>();

        for(File f : listOfFiles){
            if(f.getName().length() > 7 && f.getName().substring(0,3).toLowerCase().equals("log") && f.getName().endsWith(".txt")) {
                _VALABLE.add(f);
            }
        }

        listOfFiles = null;
        int index;

        if(_VALABLE.size() > 0) {
            fileDetected = _VALABLE.get(_VALABLE.size() - 1);
            _VALABLE.clear();

            index = Integer.parseInt(fileDetected.getName().substring(3, fileDetected.getName().length() - 4)) + 1;
            fileDetected = null;
        } else {
            index = 0;
        }

        file = new File(__PATH + "/log" + (index) + ".txt");

        try {
            file.createNewFile();
            // fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true),"utf-8"));
            buffer = new BufferedWriter((fw = new FileWriter(file,true)));

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.gc();
    }


    public void openFile(){
        file.setWritable(true);
    }

    public void closeWriter(){
        try{
            buffer.close();
        } catch(IOException ioe){

        }
    }

    public void closeFile(){
        file.setReadOnly();
    }
}