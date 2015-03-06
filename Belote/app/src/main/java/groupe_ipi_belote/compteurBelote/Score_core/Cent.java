package groupe_ipi_belote.compteurBelote.Score_core;

/**
 * Created by Axel on 27/01/2015.
 */

import groupe_ipi_belote.compteurBelote.Components_core.Cards;
import groupe_ipi_belote.compteurBelote.Components_core.Equipe;
import groupe_ipi_belote.compteurBelote.Exceptions_core.CustomExceptionTemplate;

public class Cent extends StratSequence {
    public Cent(Equipe eq, Cards[] cards) throws CustomExceptionTemplate {
        super(eq, cards);
    }
    public int annonce(){
        return 100;
    }
}
