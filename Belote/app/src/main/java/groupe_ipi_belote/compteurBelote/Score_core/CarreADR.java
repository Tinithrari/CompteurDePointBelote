package groupe_ipi_belote.compteurBelote.Score_core;

import groupe_ipi_belote.compteurBelote.Components_core.Equipe;

/**
 * Created by Axel on 27/01/2015.
 */
public class CarreADR extends Carre{
    public int annonce(){
        return 100;
    }

    public CarreADR( Equipe beneficiaire){ super(beneficiaire); }
}
