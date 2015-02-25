package groupe_ipi_belote.compteurBelote.Data_Access_Layer;

import android.app.DownloadManager;

import groupe_ipi_belote.compteurBelote.Exceptions_core.DatabaseConnectionError;
import groupe_ipi_belote.compteurBelote.Exceptions_core.QueryExceptionError;

import java.sql.*;

/**
 * Created by Axel on 18/02/2015.
 */
public class Database_query {
    private String Query = null;
    private int identifier = -0xFFFF;
    private static int id = 0;

    private final static double basic_lifetime = 30.0;
    private double current_lifetime;

    private String[] data;

    private boolean is_triggered = false;
    protected static Database_mediator DBM;

    protected static boolean setDBM(Database_mediator dbm) throws QueryExceptionError {
        try {
            if(dbm == null || !dbm.isValid()) {
                throw new QueryExceptionError(0xEE0A);
            }

            DBM = dbm;
            return true;
        } catch( QueryExceptionError qee){
            throw qee;
        } catch(Exception e){
            throw new QueryExceptionError(0xEEFF, e);
        }

    }

    public Database_query(String[] data) throws QueryExceptionError{
        try {
            if(DBM == null) {
                throw new QueryExceptionError(0xEE0A);
            }
            this.data = data;
            identifier = id++;
            setQuery();

        } catch(QueryExceptionError qee){
            throw qee;
        } catch(Exception e){
            throw new QueryExceptionError(0xEEFF,e);
        }

    }

    public void executeQuery(Statement state)throws QueryExceptionError{

        if(Query != null) {
            try {
                state.executeUpdate(Query);
                is_triggered = true;
            } catch(SQLException sqle){
                throw new QueryExceptionError(0xEE0B, sqle);
            } catch(Exception e){
                throw new QueryExceptionError(0xEEFF, e);
            }
        }
    }

    // Format : 0 - Table_name, 1 - type of operation, 2 - data , 3 - constraints, 4 - tables concerned, 5 - group by, 6 - having, 7 - order by, 8 - Kind of order by
    // Constraints include group by, order by etc.;
    // Query set up by default.
    private void setQuery() throws QueryExceptionError{
        try {
            if (data == null) throw new QueryExceptionError(0xEE0C);
            Query = "";

            // Basic methods, these will be modified later.
            // For the moment, we only consider that the largest part of the data are given by the mediator
            switch (data[1].toUpperCase()) {
                case "SELECT":
                    Query += data[1].toUpperCase() + " " + data[2] + " FROM " + data[0] + " " + data[4] + " WHERE " + data[3];

                    if (data[5] != null && !data[5].equals("")) {
                        Query += " GROUP BY " + data[5] + " " + (data[6] != null && !data[6].equals("") ? " HAVING " + data[6] : "");
                    }

                    if (data[7] != null) {
                        Query += " ORDER BY " + data[7] + " " + (data[8] != null && data[8].toUpperCase().equals("T") ? " ASC " : " DESC ");
                    }

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

            Query += ";";
        } catch(QueryExceptionError qee){
            throw qee;
        } catch(Exception e){
            throw new QueryExceptionError(0xEEFF, e);
        }
    }


    public String getQuery(){
        return Query;
    }

    public boolean is_trig() {
        return is_triggered;
    }
}
