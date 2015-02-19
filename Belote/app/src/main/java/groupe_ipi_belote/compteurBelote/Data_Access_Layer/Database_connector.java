package groupe_ipi_belote.compteurBelote.Data_Access_Layer;

import groupe_ipi_belote.compteurBelote.Exceptions_core.DatabaseConnectionError;
import java.sql.*;

import groupe_ipi_belote.compteurBelote.Exceptions_core.DatabaseConnectionError;

/**
 * Created by Axel on 18/02/2015.
 */
public class Database_connector {
    private static Connection DB_Connector = null;
    public static final int DB_Timeout = 1500;

    public Database_connector(String db) throws DatabaseConnectionError{
        connectToDatabase(db);
    }

    private void connectToDatabase(String db) throws DatabaseConnectionError {
        try {
            Class.forName("groupe_ipi_belote.compteurBelote.Data_Access_Layer.Database_connector");
            try {

                DB_Connector = DriverManager.getConnection(db);
                DB_Connector.setAutoCommit(false);
                DB_Connector.isValid(DB_Timeout);

            } catch(SQLException e){
                throw new DatabaseConnectionError(0xEE00);
            }

        } catch (ClassNotFoundException e) {

        }catch(DatabaseConnectionError dbce){
            throw dbce;
        }
    }

    public static void disconnect() throws DatabaseConnectionError{
        try {
            try {
                DB_Connector.close();
            } catch (SQLException e) {
                throw new DatabaseConnectionError(0xEE01);
            }
        } catch(DatabaseConnectionError dbce){

        } catch(Exception e){

        }
    }

    public Connection getConnection(){ return DB_Connector; }

}
