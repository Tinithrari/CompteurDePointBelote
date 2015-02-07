package groupe_ipi_belote.compteurBelote.Components_core;

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
    public Equipe( String nom, Joueur[] players){
        this(nom, players[0], players[1]);
    }

    /**
     *
     * @param nom  Nom de l'équipe
     * @param nom1 Nom du joueur 1
     * @param nom2 Nom du joueur 2
     */
    public Equipe( String nom, String nom1, String nom2){
        this(nom, new Joueur(nom1), new Joueur(nom2));
    }

    /**
     * @param team On va utiliser les valeurs d'une équipe précédemment faite.
     */
    public Equipe( Equipe team ){
        this(team.getNomEquipe(),team.getPlayers()[0], team.getPlayers()[1]);
    }

    /**
     *
     * @param nom Nom de l'équipe
     * @param J1  Joueur 1
     * @param J2  Joueur 2
     */
    public Equipe( String nom, Joueur J1, Joueur J2){
        membres = new Joueur[2];

        if(nom.equals(" ") || nom == null || nom.equals("")){
            // If the name is invalid, it will throw a new NameException
            // that will show an invalid name for the team

            // throw new NameException();
        } else {
            this.name = nom;
        }

        if(J1 == null || J2 == null){
            // As for the previous check, we need a non-null value
            // else it will throw a custom exception

            membres[0] = membres[1] = null;

            // throw new PlayerException("Valeur non nulle requise."):
        } else {
            membres[0] = J1;
            membres[1] = J1;
        }

    }

    /**
     *
     * @param index Ou numéro joueur, utilisé dans le référencement du tableau
     * @param name  Nouveau nom du joueur
     */
    public void modifyPlayer(int index, String name){
        if(index < 0 || index > membres.length){
            return;
        }

        modifyPlayer(index, new Joueur(name));
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
    public int compareTo(Equipe e){
	//implementation à faire 
		

        return 0;
    }
}
