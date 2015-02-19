package groupe_ipi_belote.compteurBelote.Data_Access_Layer;

/**
 * Created by Axel on 18/02/2015.
 */
public class Database_Donnes_Facade extends Database_Facade {

    public Database_Donnes_Facade(Database_mediator dbm){
        super("Donne");
        setUpMediator(dbm);
    }



}
