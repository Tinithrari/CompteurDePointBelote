package groupe_ipi_belote.compteurBelote.Exceptions_core;

/**
 * Created by Axel on 02/02/2015.
 */
public class CardException extends Exception{
    private String message;

    public CardException(String msg) throws Exception{
        message = msg;
    }
}

