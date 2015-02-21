package groupe_ipi_belote.compteurBelote.Exceptions_core;

/**
 * Created by Axel on 02/02/2015.
 */

// A completer
public class MainException extends CardException{

    public MainException () throws Exception{
        super(0xAE00);
    }
    public MainException(Exception e)throws Exception{
        super(0xAE00, e);
    }
}
