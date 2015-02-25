package groupe_ipi_belote.compteurBelote.Components_core;

import groupe_ipi_belote.compteurBelote.Exceptions_core.CustomExceptionTemplate;
import groupe_ipi_belote.compteurBelote.Exceptions_core.GameTeamException;
import groupe_ipi_belote.compteurBelote.Game_core.Main;

/**
 * Created by Axel on 27/01/2015.
 */
public class Joueur {
    private String name;
    final private Main hand;

    /**
     * @param nom : Le nom du joueur
     */
    public Joueur(String nom) throws CustomExceptionTemplate {
        try {
            if (nom == null || nom.equals("")) {
                throw new GameTeamException(0xAA04);
            } else {
                this.name = nom;
            }
        } catch(CustomExceptionTemplate cet){
            throw cet;
        } catch (Exception e){
            throw new GameTeamException(0xFFFF, e);
        }

        hand = new Main(this);
    }

    public String getNom(){ return this.name; }
    public Main getMain(){ return hand; }

    // Les mains ne sont pas utilisees dans le equals
    public boolean equals(Joueur j){
        return j.name.equals(name);
    }
}
