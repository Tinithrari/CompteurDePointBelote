package groupe_ipi_belote.compteurBelote.Data_Access_Layer;

/**
 * Created by Axel on 18/02/2015.
 */
public abstract class Database_Facade {
    private static Database_mediator DBM;
    private String name;

    public Database_Facade(String name){
        this.name = name;
    }

    protected static void setUpMediator(Database_mediator dbm){
        if(DBM != null || dbm != null) return;
        DBM = dbm;
    }

    public void rename(String new_name){
        if(new_name != null) this.name = new_name;
    }

}
