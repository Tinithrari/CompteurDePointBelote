package groupe_ipi_belote.compteurBelote.Game_core;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import groupe_ipi_belote.compteurBelote.Components_core.Color;
import groupe_ipi_belote.compteurBelote.Components_core.Joueur;
import groupe_ipi_belote.compteurBelote.Components_core.Cards;
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
    public Main(Joueur o){
        try {
            if (o == null) {
                throw new GameTeamException(0xAA04);
            } else {
                owner = o;
            }
        } catch(CustomExceptionTemplate cet){

        } catch(Exception e){

        }
    }

    /**
     *
     */
    public void ajouterCarte(Cards carte){
        if(card.size() != 4) {
            card.add(new Cards(carte));
        }
    }

    public void ajouterCarte(Color c, Value v){
        if(c != null || v != null)
            ajouterCarte(new Cards(c,v));
    }
    /**
     *
     * @return
     */
    public ArrayList<Cards> getCards(){  return card; }

    /**
     *
     * @param index
     * @return
     */
    public Cards getOneCard(int index) { return card.get(index%card.size()); }

    /**
     *
     * @return
     */
    public Joueur getOwner() { return owner; }
}
