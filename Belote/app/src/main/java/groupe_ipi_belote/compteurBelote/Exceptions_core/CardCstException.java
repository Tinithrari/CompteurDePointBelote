package groupe_ipi_belote.compteurBelote.Exceptions_core;

/**
 * Created by Axel on 01/02/2015.
 */
public class CardCstException extends CustomExceptionTemplate{

    public CardCstException() {
        super(0xAD01);
        message = ("Il est impossible de donner une valeur nulle aux cartes !");
    }

}
