package groupe_ipi_belote.compteurBelote.Score_core;

/**
 * Created by Axel on 27/01/2015.
 */

import groupe_ipi_belote.compteurBelote.Components_core.Equipe;
import groupe_ipi_belote.compteurBelote.Exceptions_core.AnnounceException;
import groupe_ipi_belote.compteurBelote.Exceptions_core.CustomExceptionTemplate;

public abstract class StratAnnonce {
    private Equipe benef;
    public abstract int annonce();

    /**
     *
     * @param beneficiaire Equipe qui bénéficie de la donne
     */
    public StratAnnonce(Equipe beneficiaire)throws CustomExceptionTemplate{
        try {
            if (beneficiaire == null) {
                throw new AnnounceException(0xAAA1);
            } else {
                benef = beneficiaire;
            }
        } catch(AnnounceException ae){
            throw ae;
        } catch(Exception e){
            throw new AnnounceException(0xFFFF, e);
        }
    }

}
