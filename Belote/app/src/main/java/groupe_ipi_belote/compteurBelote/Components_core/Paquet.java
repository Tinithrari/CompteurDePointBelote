package groupe_ipi_belote.compteurBelote.Components_core;

import java.util.Collections;
import java.util.LinkedList;

import groupe_ipi_belote.compteurBelote.Exceptions_core.CardCstException;

/**
 * Created by Axel on 01/02/2015.
 * @deprecated
 * Unused class, avoid to use it if possible. Only use it in case of debug
 * or tests.
 */
public class Paquet {
    private static final LinkedList<Cards> Deck = new LinkedList<>();

    /**
     *
     */
    public Paquet() throws CardCstException {
        for( Color c : Color.values()){
            for ( Value v : Value.values()){
                if(c != Color.UNDEFINED && v != Value.UNDEFINED)
                    Deck.add(new Cards(c,v));
            }
        }

        Collections.shuffle(Deck);
    }
}
