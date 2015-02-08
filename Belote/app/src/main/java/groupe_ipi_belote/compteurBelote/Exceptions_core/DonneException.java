package groupe_ipi_belote.compteurBelote.Exceptions_core;

/**
 * Created by Axel on 02/02/2015.
 */
public class DonneException extends CardException {
    private String message;

    public DonneException(int errCode) throws Exception{
        super(errCode == 0xAC00 ? "L'équipe spécifiée ne peut être nulle." : "La couleur spécifiée ne peut être nulle.");

        switch(errCode){
            case 0xAC00:
                message = "L'équipe spécifiée ne peut être nulle.";
                break;
            case 0xAC01:
                message = "La couleur spécifiée ne peut être nulle.";
                break;
            case 0xACFF :
                message = "La donne est vide.";
                break;
        }
    }

    public String getMessage(){
        return message;
    }
}