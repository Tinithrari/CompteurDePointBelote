package groupe_ipi_belote.compteurBelote.Data_Access_Layer;


import android.app.DownloadManager;

import groupe_ipi_belote.compteurBelote.Exceptions_core.DatabaseConnectionError;
import java.sql.*;
import java.util.ArrayList;


/**
 * Created by Axel on 18/02/2015.
 */
public class Database_mediator{
    private static Database_connector DBC;
    private Statement stt;
    private final ArrayList<Database_query> DB_Queries = new ArrayList<>();

    private Database_query lastQuery;

    public Database_mediator(String dbName){
        try {
            DBC = new Database_connector(dbName);
            Database_query.setDBM(this);
        } catch(DatabaseConnectionError dbce){

        }
    }

    private void addQueries(String[][] data){
        for(String[] globalQuery : data){
            DB_Queries.add(new Database_query(globalQuery));
        }
    }

    private void executeQueries(){
        for(Database_query dbq : DB_Queries){
            dbq.executeQuery(stt);
        }

        try {
            DBC.getConnection().commit();
        } catch(SQLException sqle){

        }
    }

    // Format : 0 - Table_name, 1 - type of operation, 2 - data , 3 - constraints, 4 - tables concerned
    // Constraints include group by, order by etc.;
    public void updateMethod(String[][] data){
        try {

            stt = DBC.getConnection().createStatement();
            addQueries(data);
            executeQueries();
            stt.close();

        } catch(Exception e){

        }
    }
}
