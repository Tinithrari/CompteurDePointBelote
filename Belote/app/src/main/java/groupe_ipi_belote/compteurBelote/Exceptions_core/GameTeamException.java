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
        }
    }

}
