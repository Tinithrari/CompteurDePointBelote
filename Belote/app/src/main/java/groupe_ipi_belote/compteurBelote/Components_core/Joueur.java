package groupe_ipi_belote.compteurBelote.Components_core;

import groupe_ipi_belote.compteurBelote.Game_core.Main;

/**
 * Created by Axel on 27/01/2015.
 */
public class Joueur {
    private String name;
    private Main hand;

    /**
     * @param nom : Le nom du joueur
     */
    public Joueur(String nom){
        this.name = nom;
        hand = new Main(this);
    }

    public String getNom(){ return this.name; }
    public Main getMain(){ return hand; }

}
