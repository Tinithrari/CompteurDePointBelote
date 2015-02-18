package groupe_ipi_belote.compteurBelote.Score_core;

/**
 * Created by Axel on 27/01/2015.
 */

import groupe_ipi_belote.compteurBelote.Components_core.Equipe;
import groupe_ipi_belote.compteurBelote.Exceptions_core.AnnounceException;

public abstract class StratAnnonce {
    private Equipe benef;
    public abstract int annonce();

    /**
     *
     * @param beneficiaire Equipe qui bénéficie de la donne
     */
    public StratAnnonce(Equipe beneficiaire){
        try {
            if (beneficiaire == null) {
                throw new AnnounceException(0xAAA1);
            } else {
                benef = beneficiaire;
            }
        } catch(AnnounceException ae){

        } catch(Exception e){

        }
    }

}
