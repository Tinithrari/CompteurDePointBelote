package groupe_ipi_belote.compteurBelote.Data_Access_Layer;

import groupe_ipi_belote.compteurBelote.Exceptions_core.DatabaseConnectionError;
import java.sql.*;

/**
 * Created by Axel on 18/02/2015.
 */
public class Database_Player_Facade extends Database_Facade {

    public Database_Player_Facade(Database_mediator dbm){
        super("Joueur");
        setUpMediator(dbm);
    }



}
