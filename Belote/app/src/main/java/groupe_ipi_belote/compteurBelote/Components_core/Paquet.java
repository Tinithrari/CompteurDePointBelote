package groupe_ipi_belote.compteurBelote.Components_core;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
/**
 * Created by Axel on 01/02/2015.
 */
public class Paquet {
    private static final LinkedList<Cards> Deck = new LinkedList<>();

    /**
     *
     */
    public Paquet(){
        for( Color c : Color.values()){
            for ( Value v : Value.values()){
                Deck.add(new Cards(c,v));
            }
        }

        Collections.shuffle(Deck);
    }
}
