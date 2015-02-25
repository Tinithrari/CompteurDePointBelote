package groupe_ipi_belote.compteurBelote.Game_core;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import groupe_ipi_belote.compteurBelote.Components_core.Cards;
import groupe_ipi_belote.compteurBelote.Components_core.Color;
import groupe_ipi_belote.compteurBelote.Components_core.Joueur;
import groupe_ipi_belote.compteurBelote.Components_core.Value;
import groupe_ipi_belote.compteurBelote.Exceptions_core.CustomExceptionTemplate;
import groupe_ipi_belote.compteurBelote.Exceptions_core.GameTeamException;

/**
 * Created by Axel on 30/01/2015.
 */
public class Main {
    private Joueur  owner;
    private final ArrayList<Cards> card = new ArrayList<>(4);


    /**
     *
     * @param o Propriétaire de la main.
     */
    public Main(Joueur o) throws CustomExceptionTemplate {
        try {
            if (o == null) {
                throw new GameTeamException(0xAA04);
            } else {
                owner = o;
            }
        } catch(CustomExceptionTemplate cet){
            throw cet;
        } catch(Exception e){
            throw new GameTeamException(0xFFFF, e);
        }
    }

    /**
     *
     * @param carte Ajoute la carte concernée à la main du joueur
     */
    public void ajouterCarte(Cards carte) throws CustomExceptionTemplate{
        try {
            if (card.size() != 4) {
                card.add(new Cards(carte));
            }

            // Tri 'adaptatif'
            Collections.sort(card, new Comparator<Cards>() {
                public int compare(Cards c1, Cards c2) {
                    return c1.compareTo(c2);
                }
            });
        } catch(Exception e){
            throw new GameTeamException(0xFFFF, e);
        }
    }

    /**
     * @deprecated
     * @param c La couleur de la carte
     * @param v La valeur de la carte
     */
    public void ajouterCarte(Color c, Value v) throws CustomExceptionTemplate {
        if(c != null || v != null)
            ajouterCarte(new Cards(c,v));
    }
    /**
     *
     * @return La main du joueur
     */
    public ArrayList<Cards> getCards(){  return card; }

    /**
     *
     * @param index L'indice de la carte voulue
     * @return      La carte sélectionnée
     */
    public Cards getOneCard(int index) { return card.get(index%card.size()); }

    /**
     *
     * @param cd La carte voulue
     * @return   La carte sélectionnée, si elle existe
     */
    public Cards getOneCard(Cards cd)  { return card.contains(cd) ? cd : null;}

    /**
     *
     * @return Le joueur qui détient cette main
     */
    public Joueur getOwner() { return owner; }
}
