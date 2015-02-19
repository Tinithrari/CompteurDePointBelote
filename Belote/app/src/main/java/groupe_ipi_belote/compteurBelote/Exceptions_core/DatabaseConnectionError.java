package groupe_ipi_belote.compteurBelote.Exceptions_core;

import java.sql.SQLException;

/**
 * Created by Axel on 18/02/2015.
 */
public class DatabaseConnectionError extends SQLException {
    private int errCode;
    private String message;

    public DatabaseConnectionError(int errCode) {
        this.errCode = errCode;

        switch(errCode){
            case 0xEE00 :
                message = "Connexion impossible avec la base.";
                break;
            case 0xEE01 :
                message = "Un problème est survenue lors de la déconnexion.";
                break;
            case 0xEE02 :
                message = "Les requêtes n'ont pas pu être exécuté";
                break;
            case 0xEEFF :
                message = "Une erreur inconnue s'est produite durant l'exécution de la requête.";
                break;
        }

    }

    public String getMessage(){
        return errCode + "_" + message;
    }
}
