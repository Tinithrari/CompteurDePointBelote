package groupe_ipi_belote.compteurBelote.Score_core;

/**
 * Created by Axel on 27/01/2015.
 */
import groupe_ipi_belote.compteurBelote.Components_core.*;

public class Cent extends StratSequence {
    public Cent(Equipe eq, Cards[] cards){
        super(eq, cards);
    }
    public int annonce(){
        return 100;
    }
}
