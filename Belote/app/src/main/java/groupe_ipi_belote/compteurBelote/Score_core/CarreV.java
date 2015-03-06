package groupe_ipi_belote.compteurBelote.Score_core;

import groupe_ipi_belote.compteurBelote.Components_core.Equipe;
import groupe_ipi_belote.compteurBelote.Exceptions_core.CustomExceptionTemplate;

/**
 * Created by Axel on 27/01/2015.
 */
public class CarreV extends Carre{
    public int annonce(){
        return 200;
    }
    public CarreV(Equipe beneficiaire) throws CustomExceptionTemplate { super(beneficiaire); }
}
