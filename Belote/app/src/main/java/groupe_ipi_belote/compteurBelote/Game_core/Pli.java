package groupe_ipi_belote.compteurBelote.Game_core;
import groupe_ipi_belote.compteurBelote.Components_core.Equipe;
import groupe_ipi_belote.compteurBelote.Components_core.Cards;


/**
 * Created by Axel on 27/01/2015.
 */
public class Pli {
    private Equipe gagnant;
    private Cards  wCard;

    /**
     *
     * @param win  Equipe qui remporte le pli
     * @param card La carte dans le pli
     */
    public Pli(Equipe win, Cards card){
        if(win == null || card == null) {
            // throw new PliCstException();
        } else {
            gagnant = win;
            wCard = card;
        }
    }

    public Cards getCartes(){ return wCard; }
    public Equipe getGagnant(){ return gagnant; }
}
