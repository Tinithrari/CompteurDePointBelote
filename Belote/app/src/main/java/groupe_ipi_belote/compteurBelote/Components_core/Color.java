package groupe_ipi_belote.compteurBelote.Components_core;
/**
 * Created by Axel on 27/01/2015.
 * Les couleurs et faces représente la même chose.s
 */
public enum Color {
    COEUR(0),
    CARREAU(1),
    TREFLE(2),
    PIQUE(3);

    int rank;

    /**
     *
     * @param i 'Rang' de la couleur
     */
    Color(int i){
        rank = i;
    }

    @Override
    public String toString(){
        return name().toLowerCase();
    }
}
