package groupe_ipi_belote.compteurBelote.Exceptions_core;

/**
 * Created by Axel on 01/02/2015.
 */
public class GameTeamException extends Exception{
    private String message;
    private int errCode;

    /**
     *
     * @param value Identifiaction de l'exception provoquée.
     * @throws Exception
     */
    public GameTeamException(int value) throws Exception{
        super();
        errCode = value;

        switch(value){
            case 0xAA00 :
                message = "Les équipes ne peuvent pas être vide !";
                break;
            case 0xAA01 :
                message = "Les équipes doivent être différentes !";
                break;
            case 0xAA02 :
                message = "Les champs ne peuvent pas être vide !";
                break;
            case 0xAA03:
                message = "Les valeurs non nulles ne peuvent pas être utilisées.";
                break;
            case 0xAA04:
                break;
            case 0xAA05:
                break;
            case 0xAA06:
                message = "Le nom d'une des deux équipes est invalide.";
                break;
        }
    }

    public String getMessage(){
        return message;
    }
}
