package groupe_ipi_belote.compteurBelote.Data_Access_Layer;


import android.app.DownloadManager;
import android.provider.ContactsContract;

import groupe_ipi_belote.compteurBelote.Exceptions_core.DatabaseConnectionError;
import java.sql.*;
import java.util.ArrayList;


/**
 * Created by Axel on 18/02/2015.
 */
public class Database_mediator{
    private final static int MAX_QUERY_SIZE = 30;

    private static Database_connector DBC;

    private Statement stt;
    // Either 30 queries maximum, or 3 minutes of lifetime / queries
    private final ArrayList<Database_query> DB_Queries = new ArrayList<>();

    // If an error occured, DCE will block I/O operations to the databases and
    // will avoid the possibility to set the Query's owner to the current mediator
    private DatabaseConnectionError DCE = null;

    public void setNewDBC(Database_connector dbc) throws DatabaseConnectionError{
        try{
            DBC.disconnect();
            DBC = dbc;
            DCE = null;
        } catch(DatabaseConnectionError sql){
            DCE = sql;
            throw sql;
        } catch(Exception e){
            DatabaseConnectionError dceT = DCE = new DatabaseConnectionError(0xEEFF, e);
            throw dceT;
        }
    }

    public Database_mediator(String dbName) throws DatabaseConnectionError{
        try {
            DBC = new Database_connector(dbName);
            Database_query.setDBM(this);
            DCE = null;
        } catch(DatabaseConnectionError dbce){
            DCE = dbce;
            throw dbce;
        } catch(Exception e){
            DatabaseConnectionError dceT = DCE = new DatabaseConnectionError(0xEEFF, e);
            throw dceT;
        }
    }

    private void addQueries(String[][] data) throws DatabaseConnectionError{
        try {
            if (isValid()) {
                if (DB_Queries.size() < MAX_QUERY_SIZE) {
                    for (String[] globalQuery : data) {
                        DB_Queries.add(new Database_query(globalQuery));
                    }
                } else {
                    DB_Queries.subList(0, data.length - 1).clear();
                    addQueries(data);
                }
            }
        }catch(DatabaseConnectionError dce) {
            DCE = dce;
            throw dce;
        }
        catch(Exception e){
            throw new DatabaseConnectionError(0xEEFF,e);
        }
    }

    /**
     * Execute toutes les requêtes non active de la liste
     * des requêtes enregistrées.
     */
    private void executeQueries()throws DatabaseConnectionError{
        try {
            if (isValid()) {
                for (Database_query dbq : DB_Queries) {
                    if (!dbq.is_trig()) {
                        dbq.executeQuery(stt);
                    }
                }

                DBC.getConnection().commit();

            }
        }
        catch(DatabaseConnectionError dce){
            throw dce;
        }
        catch(Exception e){
            throw new DatabaseConnectionError(0xEEFF,e);
        }
    }

    // Format : 0 - Table_name, 1 - type of operation, 2 - data , 3 - constraints, 4 - tables concerned
    // Constraints include group by, order by etc.;
    public void updateMethod(String[][] data)throws DatabaseConnectionError{
        if(isValid()) {
            try {

                stt = DBC.getConnection().createStatement();
                addQueries(data);
                executeQueries();
                stt.close();

                
            } catch (DatabaseConnectionError dce) {
                throw dce;
            } catch (Exception e) {
                throw new DatabaseConnectionError(0xEEFF, e);
            }
        }
    }

    public boolean isValid(){
        return DCE != null;
    }
}
