package groupe_ipi_belote.compteurBelote.Exceptions_core;

/**
 * Created by Axel on 02/02/2015.
 */
public class CardException extends CustomExceptionTemplate{

    public CardException(int code, Exception e) {
        super(code, e);
        message = "Une des valeur spécifiée est invalide.";
    }

    public CardException(int code) throws Exception{
        this(code,null);
    }
}

