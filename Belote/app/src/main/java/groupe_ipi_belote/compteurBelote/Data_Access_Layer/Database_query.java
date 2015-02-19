package groupe_ipi_belote.compteurBelote.Data_Access_Layer;

import groupe_ipi_belote.compteurBelote.Exceptions_core.DatabaseConnectionError;
import java.sql.*;

/**
 * Created by Axel on 18/02/2015.
 */
public class Database_query {
    private String Query = null;
    private int identifier = -0xFFFF;
    private static int id = 0;

    private String[] data;

    protected static Database_mediator DBM;

    protected static boolean setDBM(Database_mediator dbm){
        if(dbm == null) return false;
        DBM = dbm;

        return true;
    }

    public Database_query(String[] data){
        if(DBM != null) {
            this.data = data;
            identifier = id++;
            setQuery();
        }
    }

    public void executeQuery(Statement state){
        if(Query != null) {
            try {
                state.executeUpdate(Query);
            } catch(SQLException sqle){

            }
        }
    }

    // Format : 0 - Table_name, 1 - type of operation, 2 - data , 3 - constraints, 4 - tables concerned
    // Constraints include group by, order by etc.;
    private void setQuery(){
        if(data == null) return;
        Query = "";

        // Basic methods, these will be modified later.
        // For the moment, we only consider that the largest part of the data are given by the mediator
        switch(data[1].toUpperCase()){
            case "SELECT":
                Query += data[1].toUpperCase() + " " + data[2] + " FROM " + data[0] + " " + data[4] + " WHERE " + data[3];
                break;

            case "UPDATE":
                Query += data[1].toUpperCase() + " " + data[0] + " set " + data[2] + " " + data[4] + " WHERE " + data[3];
                break;

            case "DELETE":
                data[2] = data[3] = null;
                Query += data[1].toUpperCase() + " FROM " + data[0] + " WHERE " + data[3];
                break;

            case "INSERT":
                data[3] = data[4] = null;
                Query += data[1].toUpperCase() + " INTO " + data[0] + " (" + data[2] + ")";
                break;
        }
    }

    public String getQuery(){
        return Query;
    }

}
