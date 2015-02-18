package groupe_ipi_belote.compteurBelote.Score_core;

/**
 * Created by Axel on 27/01/2015.
 */

import groupe_ipi_belote.compteurBelote.Components_core.Cards;
import groupe_ipi_belote.compteurBelote.Components_core.Equipe;

public class Cinquante extends StratSequence {
    public Cinquante(Equipe eq, Cards[] cards){
        super(eq, cards);
    }
    public int annonce(){
        return 50;
    }
}
