package groupe_ipi_belote.compteurBelote.Components_core;

import groupe_ipi_belote.compteurBelote.Exceptions_core.GameTeamException;

/**
 * Created by Axel on 27/01/2015.
 */


public class Equipe implements Comparable<Equipe>{

    private Joueur[] membres;
    private String name;

    /**
     *
     * @param nom       Nom de l'équipe
     * @param players   Joueurs de l'équipe
     */
    public Equipe( String nom, Joueur[] players) throws GameTeamException {
        this(nom, players[0], players[1]);
    }

    /**
     *
     * @param nom  Nom de l'équipe
     * @param nom1 Nom du joueur 1
     * @param nom2 Nom du joueur 2
     */
    public Equipe( String nom, String nom1, String nom2) throws GameTeamException {
        this(nom, new Joueur(nom1), new Joueur(nom2));
    }

    /**
     * @param team On va utiliser les valeurs d'une équipe précédemment faite.
     */
    public Equipe( Equipe team ) throws GameTeamException {
        this(team.getNomEquipe(),team.getPlayers()[0], team.getPlayers()[1]);
    }

    /**
     *
     * @param nom Nom de l'équipe
     * @param J1  Joueur 1
     * @param J2  Joueur 2
     */
    public Equipe( String nom, Joueur J1, Joueur J2) throws GameTeamException {
        membres = new Joueur[2];

        try {
            if (nom.equals(" ") || nom == null || nom.equals("")) {
                // If the name is invalid, it will throw a new NameException
                // that will show an invalid name for the team

                throw new GameTeamException(0xAA06);
            } else {
                this.name = nom;
            }

            if (J1 == null || J2 == null) {
                // As for the previous check, we need a non-null value
                // else it will throw a custom exception

                membres[0] = membres[1] = null;
                throw new GameTeamException(0xAA03);

            } else {
                membres[0] = J1;
                membres[1] = J2;
            }

        } catch( GameTeamException gte){
            throw gte;
        } catch(Exception e){
            throw new GameTeamException(0xFFFF, e);
        }
    }

    /**
     *
     * @param index Ou numéro joueur, utilisé dans le référencement du tableau
     * @param name  Nouveau nom du joueur
     */
    public void modifyPlayer(int index, String name) throws GameTeamException {
        try {
            if (index < 0 || index > membres.length) {
                throw new GameTeamException(0xAA0F); // message = Invalid Data
            }
            modifyPlayer(index, new Joueur(name));
        } catch(GameTeamException gte){
            throw gte;

        } catch(Exception e){
            throw new GameTeamException(0xFFFF,e);
        }
    }

    /**
     *
     * @param index Ou numéro joueur, utilisé dans le référencement du tableau
     * @param j     Joueur qui va être intégré dans l'équipe
     */
    public void modifyPlayer(int index, Joueur j){
        if(index > membres.length || index < 0){
            return;
        }

        membres[index] = j;
    }

    public Joueur[] getPlayers() { return this.membres;}
    public String getNomEquipe() { return this.name; }


    // All Override methods and Interface implementations

    @Override
    // Will be implemented later

    /**
     * Unused method
     */
    public int compareTo(Equipe e){
	//implementation à faire
        return 0;
    }

    // On considere que l'inverse est strictement impossible : le joueur 1 ne peut pas etre le joueur 2

    /**
     *
     * @param e La deuième équipe
     * @return  Retourne si oui, ou non, l'équipe courante et l'équipe sélectionnée sont les deux mêmes équipes
     */
    public boolean equals(Equipe e){
        return this.name.equals(e.name) && membres[0].equals(e.membres[0]) && membres[1].equals(e.membres[1]);
    }
}
