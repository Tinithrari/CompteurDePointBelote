package groupe_ipi_belote.compteurBelote.Additional_features;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import groupe_ipi_belote.compteurBelote.Exceptions_core.CustomExceptionTemplate;

// Class used to read and write special kind of log files. These files will be understandable if a small amount
// of errors occured, yet it becomes useless to read them if there are a very large quantity of problems
// (More than twenty / twenty-five)
public class Logs {
    private BufferedWriter buffer;
    private FileWriter fw;

    private File file;
    private String currentTime;

    public static final String __PATH = System.getProperty("user.dir") + "/Logs/";
    public static final String CURRENT_DAY = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());

    /*
        Une cible en particulier sera choisie, si le filenumber est inferieur a 0, on va prendre
        le dernier fichier log ecrit.

     */

    /**
     *
     * @param fileNumber Indique le numéro du log voulu, il faut s'assurer que ce log existe bel et bien.
     */
    public static void readLog(int fileNumber){
        File fileExistence = new File(__PATH);

        if(!fileExistence.exists() || !fileExistence.isDirectory() ) {
            return;
        }

        if(fileNumber < 0){
            Logs tmpLog = new Logs();
            fileNumber = tmpLog.detectLastFile();
            tmpLog = null;

            System.gc();
        }

        File tmp = null;

        File[] listOfFiles = fileExistence.listFiles();

        final ArrayList<File> _VALABLE = new ArrayList<>();

        for(File f : listOfFiles){
            if(f.getName().length() > 7 && Integer.parseInt(f.getName().substring(3,4)) == (fileNumber) && f.getName().substring(0,3).toLowerCase().equals("log") && f.getName().endsWith(".txt")) {
                tmp = f;
                break;
            }
        }

        if(tmp ==  null) return;

        String logReader;
        try {
            BufferedReader br = new BufferedReader(new FileReader(tmp));
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                line = br.readLine();
            }

            logReader = sb.toString();
        }
        catch(Exception e){
            return;
        }

        String[][][][] broken = breakCode(logReader);
        for(String[][][] arrLo : broken){
            for(String[][] arrLt : arrLo){
                for(String[] arrLth: arrLt){
                    for(String str : arrLth) {
                        System.out.println("LOG_READER : " + str);
                    }
                }
            }
        }

        broken = null;
        System.gc();

    }

    /**
     *
     * @param str La ligne d'un fichier
     * @return    Le "code" cassé, si il est écrit selon la norme de ce log
     */
    private static String[][][][] breakCode(String str){
        String[] firstLevelTempArray = str.split("#@END@#");
        String[][] secondLevelTempArray = new String[firstLevelTempArray.length][];

        for(int i = 0; i < secondLevelTempArray.length; ++i){
            secondLevelTempArray[i] = firstLevelTempArray[i].split("#");
        }

        String[][][] thirdLevelTempArray = new String[secondLevelTempArray.length][][];
        String[][][][] fourthLevelTempArray = new String[secondLevelTempArray.length][][][];

        for(int i = 0; i < thirdLevelTempArray.length; ++i){
            thirdLevelTempArray[i] = new String[secondLevelTempArray[i].length][];
            fourthLevelTempArray[i] = new String[secondLevelTempArray[i].length][][];

            for( int j = 0; j < secondLevelTempArray[i].length; ++j){
                thirdLevelTempArray[i][j] = secondLevelTempArray[i][j].split("@");
            }

            for( int j = 0; j < thirdLevelTempArray[i].length; ++j){
                fourthLevelTempArray[i][j] = new String[thirdLevelTempArray[i][j].length][];

                for ( int k = 0; k < thirdLevelTempArray[i][j].length; ++k){

                    fourthLevelTempArray[i][j][k] = thirdLevelTempArray[i][j][k].split(":FROM:");

                }
            }

        }

        return fourthLevelTempArray;
    }

    /**
     *
     */
    public Logs(){
        detectFile();
    }

    /**
     *
     * @param cet Erreur "custom" pour le moment. Par la suite, il sera implémenté une nouvelle fonction pour
     *            tout type d'erreur, que ce soit les Exceptions comme les autres.
     */
    public void writeIntoFile(CustomExceptionTemplate cet){

        try{
            currentTime = new SimpleDateFormat("HHmmss").format(Calendar.getInstance().getTime());

            fw.append("[" + currentTime + "]@Er" + cet.getErrCode() + ":@Me_" + cet.getMessage() + ":FROM:" + cet.getCause() + " [TRACE] ");

            for (StackTraceElement s : cet.getStackTrace()){
                fw.append("@Cl_" + s.getClassName() + "@Fl_" + s.getFileName() + "@Mt_" + s.getMethodName() + "@Ln_" + s.getLineNumber() + "@@");
            }

            fw.flush();

        } catch(Exception e){
            closeWriter();
        }

    }

    /**
     *
     * @param e Ecrit une exception dans le dernier fichier log ouvert
     */
    public void writeIntoFile(Exception e){
        try{
            currentTime = new SimpleDateFormat("HHmmss").format(Calendar.getInstance().getTime());

            fw.append("[" + currentTime + "]@Me_" + e.getMessage() + ":FROM:" + e.getCause() + " [TRACE] ");

            for (StackTraceElement s : e.getStackTrace()){
                fw.append("@Cl_" + s.getClassName() + "@Fl_" + s.getFileName() + "@Mt_" + s.getMethodName() + "@Ln_" + s.getLineNumber() + "@@");
            }

            fw.flush();

        } catch(Exception ex){
            closeWriter();
        }

    }

    /**
     *
     * @return Le numéro du nouveau log, basé sur les anciens numéros (1,2...)
     *         Si aucun log n'existe, le log '0' sera crée.
     */
    private int detectLastFile(){
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

        fileExistence = null;
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

        return index;
    }

    /**
     * Crée le dernier fichier de log, l'ouvre et reste ouvert tant que le
     * programme n'est pas arrêté, ou tant que le partie n'est pas finie.
     */
    private void detectFile(){
        int index = detectLastFile();
        file = new File(__PATH + "/log" + (index) + ".txt");

        try {
            file.createNewFile();
            buffer = new BufferedWriter((fw = new FileWriter(file,true)));

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.gc();
    }


    /**
     * S'assure que le fichier est bel et bien modifiable
     */
    public void openFile(){
        file.setWritable(true);
    }

    /**
     *
     */
    public void closeWriter(){
        try{
            buffer.close();
            closeFile();
        } catch(IOException ioe){

        }
    }

    /**
     * On s'assure que le fichier est en lecture seule, pour éviter toute
     * modification des logs ainsi que des erreurs.
     */
    public void closeFile(){
        file.setReadOnly();
    }
}