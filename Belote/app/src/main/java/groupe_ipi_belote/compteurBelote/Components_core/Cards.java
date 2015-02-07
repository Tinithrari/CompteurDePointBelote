package groupe_ipi_belote.compteurBelote.Components_core;

import java.util.Random;

import groupe_ipi_belote.compteurBelote.Exceptions_core.CardCstException;
/**
 * Created by Axel on 27/01/2015.
 */
public class Cards {
    private Color color;
    private Value value;

    /**
     *
     * @param c Couleur de la carte
     * @param v Valeur / Face de la carte
     */
    public Cards(Color c, Value v) {
        try {
            if (c != null || v != null) {
                color = c;
                value = v;
            } else {
                throw new CardCstException();
            }
        } catch( CardCstException cce){
            // Sera utilise a des fins de communications
        } catch( Exception e){

        }
    }

    public Cards(Cards carte) {
        this(carte.getFace(), carte.getValue());
    }

    public Value getValue(){ return value; }
    public Color getFace (){ return color; }

    @Override
    public String toString(){
        return color.toString() + "_" + value.toString();
    }

}
