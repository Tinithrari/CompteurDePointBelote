package groupe_ipi_belote.compteurBelote.Game_core;
import java.util.ArrayList;

import groupe_ipi_belote.compteurBelote.Components_core.Cards;
import groupe_ipi_belote.compteurBelote.Components_core.Equipe;
import groupe_ipi_belote.compteurBelote.Components_core.Color;
import groupe_ipi_belote.compteurBelote.Components_core.Joueur;
import groupe_ipi_belote.compteurBelote.Exceptions_core.BERException;

/**
 * Created by Axel on 27/01/2015.
 */

public class BeloteEtRebelote {
    private Color  atout;
    private Equipe team;

    public BeloteEtRebelote(Equipe eqp, Color clr) {
        try {
            if (clr == null || eqp == null) {
                throw new BERException(clr == null ? 0xCC00 : 0xCC01);
            } else {
                atout = clr;
                team = eqp;
            }
        } catch(BERException br){

        } catch(Exception e){

        }

    }

}
