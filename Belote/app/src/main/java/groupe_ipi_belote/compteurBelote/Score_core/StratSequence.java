package groupe_ipi_belote.compteurBelote.Score_core;

/**
 * Created by Axel on 27/01/2015.
 */

import groupe_ipi_belote.compteurBelote.Components_core.Cards;
import groupe_ipi_belote.compteurBelote.Components_core.Equipe;
import groupe_ipi_belote.compteurBelote.Exceptions_core.AnnounceException;

public abstract class StratSequence extends StratAnnonce{
    private Cards[] card;

    /**
     * @param benef Equipe bénéficiaire
     * @param compo Les cartes qui composent l'annonce
     */
    public StratSequence(Equipe benef, Cards[] compo){
        super(benef);
        try {
            if (compo == null || compo.length == 0) {
                throw new AnnounceException(0xAAA0);
            } else {
                card = compo;
            }
        } catch(AnnounceException ae){

        } catch (Exception e){

        }
    }

    /**
     *
     * @return La valeur de l'annonce
     */
    public abstract int annonce();

}
