package groupe_ipi_belote.compteurBelote.Exceptions_core;

/**
 * Created by Axel on 02/02/2015.
 */
public class AnnounceException extends Exception {
    private String message;
    private int errCode;

    public AnnounceException(int code) throws Exception {
        errCode = code;
        switch (code) {
            case 0xAAA0:
                message = "Il est impossible que le nombre de cartes soit nulle";
                break;

            case 0xAAA1:
                message = "Il est impossible d'avoir une equipe nulle !";
                break;
        }
    }
}
