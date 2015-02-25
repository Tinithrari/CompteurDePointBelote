package groupe_ipi_belote.compteurBelote.Game_core;
import java.util.ArrayList;

import groupe_ipi_belote.compteurBelote.Components_core.Cards;
import groupe_ipi_belote.compteurBelote.Components_core.Equipe;
import groupe_ipi_belote.compteurBelote.Components_core.Color;
import groupe_ipi_belote.compteurBelote.Components_core.Joueur;

/**
 * Created by Axel on 27/01/2015.
 */

public class BeloteEtRebelote {
    private Color  atout;
    private Equipe team;

    public BeloteEtRebelote(Equipe eqp, Color clr){
        if(clr == null || eqp == null){ /*throw new BERException(); */}
        else {
            atout = clr;
            team = eqp;
        }
    }

}
