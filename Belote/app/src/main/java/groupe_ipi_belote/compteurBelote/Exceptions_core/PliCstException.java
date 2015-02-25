package groupe_ipi_belote.compteurBelote.Exceptions_core;

/**
 * Created by Axel on 08/02/2015.
 */
public class PliCstException extends CustomExceptionTemplate{

    public PliCstException(int errCode) throws Exception{
        this(errCode, null);
    }

    public PliCstException(int errCode, Exception e) {
        super(errCode, e);

        switch(errCode) {
            case 0xAB00:
                message = "L'équipe gagnante ne peut pas être nulle.";
                break;
            case 0xAB01:
                message = "La carte utilisée pour le pli ne peut pas être nulle.";
                break;
        }
    }

}
