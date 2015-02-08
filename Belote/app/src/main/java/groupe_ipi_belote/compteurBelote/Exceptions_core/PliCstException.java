package groupe_ipi_belote.compteurBelote.Exceptions_core;

/**
 * Created by Axel on 08/02/2015.
 */
public class PliCstException extends Exception{
    private String message;

    public PliCstException(int errCode) throws Exception{
        super();

        switch(errCode) {
            case 0xAB00:
                message = "L'équipe gagnante ne peut pas être nulle.";
                break;
            case 0xAB01:
                message = "La carte utilisée pour le pli ne peut pas être nulle.";
                break;
        }
    }

    public String getMessage(){
        return message;
    }
}
