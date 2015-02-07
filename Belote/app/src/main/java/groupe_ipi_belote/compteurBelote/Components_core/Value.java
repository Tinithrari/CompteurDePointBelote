package groupe_ipi_belote.compteurBelote.Components_core;
/**
 * Created by Axel on 27/01/2015.
 */
public enum Value {
    SEPT(0,0),
    HUIT(0,1),
    NEUF(0,2),
    DIX(10,3),
    VALET(2,4),
    DAME(3,5),
    ROI(4,6),
    AS(11,7);

    private int value;
    private int rank;

    /**
     *
     * @param i Valeur de la carte
     * @param r Rang de la carte
     */
    Value(int i, int r) {
        value = i;
        rank  = r;
    }

    public int getValue() { return value; }
    public int getRang(){ return rank; }

    @Override
    public String toString(){
        return name().toLowerCase();
    }
}
