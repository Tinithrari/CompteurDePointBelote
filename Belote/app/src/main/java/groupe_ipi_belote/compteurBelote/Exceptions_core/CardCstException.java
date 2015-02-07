package groupe_ipi_belote.compteurBelote.Exceptions_core;

import android.text.style.TtsSpan;

/**
 * Created by Axel on 01/02/2015.
 */
public class CardCstException extends CardException{
    private String message;

    public CardCstException() throws Exception{
        super("Il est impossible de donner une valeur nulle aux cartes !");
    }

}
