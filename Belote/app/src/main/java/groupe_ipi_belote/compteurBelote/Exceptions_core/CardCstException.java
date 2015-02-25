package groupe_ipi_belote.compteurBelote.Exceptions_core;

/**
 * Created by Axel on 01/02/2015.
 */
public class CardCstException extends CustomExceptionTemplate{

    public CardCstException(int value, Exception e){
        super(value);
        message = ("Il est impossible de donner une valeur nulle aux cartes !");
    }

    public CardCstException() {
        this(0xAD01);
    }

    public CardCstException(int value){
        this(value, null);
    }





}
