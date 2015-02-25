package groupe_ipi_belote.compteurBelote.Score_core;

import groupe_ipi_belote.compteurBelote.Components_core.Color;
import groupe_ipi_belote.compteurBelote.Components_core.Equipe;
import groupe_ipi_belote.compteurBelote.Exceptions_core.BERException;
import groupe_ipi_belote.compteurBelote.Exceptions_core.CustomExceptionTemplate;

/**
 * Created by Axel on 27/01/2015.
 */

public class BeloteEtRebelote extends StratAnnonce{
    private Color  atout;
    private Equipe team;

    /**
     *
     * @param eqp L'équipe qui annonce
     * @param clr L'atout
     */
    public BeloteEtRebelote(Equipe eqp, Color clr) throws CustomExceptionTemplate{
        super(eqp);

        try {
            if (clr == null || clr == Color.UNDEFINED || eqp == null) {
                throw new BERException(clr == null ? 0xCC00 : 0xCC01);
            } else {
                atout = clr;
                team = eqp;
            }
        } catch(BERException br){
            throw br;
        } catch(Exception e){
            throw new BERException(0xFFFF, e);
        }

    }


    @Override
    public int annonce() {
        return 20;
    }
}
