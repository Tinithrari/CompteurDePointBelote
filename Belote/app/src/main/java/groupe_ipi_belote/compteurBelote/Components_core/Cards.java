package groupe_ipi_belote.compteurBelote.Components_core;

import groupe_ipi_belote.compteurBelote.Exceptions_core.CardCstException;
/**
 * Created by Axel on 27/01/2015.
 */
public class Cards implements Comparable {
    final private Color color;
    final private Value value;

    /**
     *
     * @param c Couleur de la carte
     * @param v Valeur / Face de la carte
     */
    public Cards(Color c, Value v) throws CardCstException {
        if(c == null) color = Color.UNDEFINED; else color = c;
        if(v == null) value = Value.UNDEFINED; else value = v;

        try {
            if ((c != null && v != null) || (c != Color.UNDEFINED && v != Value.UNDEFINED)) {
                // Rien a faire
            } else {
                throw new CardCstException();
            }
        } catch( CardCstException cce){
            throw cce;
        } catch( Exception e){
            throw new CardCstException(0xFFFF,e);
        }
    }

    /**
     *
     * @param carte Constructeur de copie.
     */
    public Cards(Cards carte) throws CardCstException {
        this(carte.getFace(), carte.getValue());
    }

    /**
     *
     * @return La valeur de la carte courante
     */
    public Value getValue(){ return value; }

    /**
     *
     * @return La couleur de la carte courante
     */
    public Color getFace (){ return color; }

    @Override
    public String toString(){
        return color.toString() + "_" + value.toString();
    }

    private int compareTo(Cards cd) {
        return (this.getValue().getRang() < cd.getValue().getRang()) ? -10 : (this.getValue().getRang() == cd.getValue().getRang()) ? 0 : 10;
    }

    @Override
    public boolean equals(Object object) {
        return false;
    }

    @Override
    public int compareTo(Object another) {
        return compareTo(another);
    }
}
