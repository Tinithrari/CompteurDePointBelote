package groupe_ipi_belote.compteurBelote.Game_core;
import groupe_ipi_belote.compteurBelote.Components_core.Color;
import groupe_ipi_belote.compteurBelote.Components_core.Equipe;
import groupe_ipi_belote.compteurBelote.Components_core.Cards;
import groupe_ipi_belote.compteurBelote.Components_core.Value;
import groupe_ipi_belote.compteurBelote.Exceptions_core.PliCstException;


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
        try {
            if (win == null || card == null || card.getValue() == Value.UNDEFINED || card.getFace() == Color.UNDEFINED) {
                throw new PliCstException(win == null ? 0xAB00 : 0xAB01);
            } else {
                gagnant = win;
                wCard = card;
            }
        } catch(PliCstException pce){

        } catch (Exception e){

        }
    }

    public Cards getCartes(){ return wCard; }
    public Equipe getGagnant(){ return gagnant; }
}
