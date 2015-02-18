package groupe_ipi_belote.compteurBelote.Game_core;

import java.util.ArrayList;

import groupe_ipi_belote.compteurBelote.Components_core.Equipe;
import groupe_ipi_belote.compteurBelote.Exceptions_core.DonneException;
import groupe_ipi_belote.compteurBelote.Exceptions_core.GameTeamException;

/**
 * Created by Axel on 27/01/2015.
 */

public class Partie {
    private String name;
    private final int[] score_equipes = new int[2];             // On ne peut pas modifier le tableau déjà présent.

    private final Equipe[] equipes = new Equipe[2];
    private final ArrayList<Donne> donnes = new ArrayList<Donne>(); // On s'assure que la donne ne peut pas être modifiée en cours

    /**
     *
     * @param nom Nom de la partie
     * @param e1  Equipe 1
     * @param e2  Equipe 2
     */
    public Partie(String nom, Equipe e1, Equipe e2){
        try {
            if (nom == null || nom.equals(" ") || nom.equals("")) {
                 throw new GameTeamException(0xAA02);
            } else if (e1 == null || e2 == null) {
                throw new GameTeamException(0xAA00);
            } else if (e1.getPlayers() == e2.getPlayers() || e1.getNomEquipe() == e2.getNomEquipe()) {
                throw new GameTeamException(0x0AA01);
            } else {
                this.name = nom;
                this.equipes[0] = e1;
                this.equipes[1] = e2;
            }

        } catch(GameTeamException gte){
            // Communication en amont
        } catch(Exception e){

        } finally{
            this.score_equipes[0] = this.score_equipes[1] = 0;
        }
    }

    /**
     * Ajouter une donne à la partie via le GUI
     *
     */
    public void ajouterDonne(Donne d){
        if(d != null) donnes.add(d);
    }

    /**
     *
     * @param equipe Ajout de points à l'équipe désignée
     */
    public void ajouterPoint(Equipe equipe) {
        int pos = -1;

        try{
            if (donnes.isEmpty()) {
                throw new DonneException(0xACFF);
            }

            if (equipe == null) {
                throw new GameTeamException(0xAA04);
            }


            if(equipe.equals(equipes[0])) {
                pos = 0;
            } else if(equipe.equals(equipes[1])) {
                pos = 1;
            } else {
                throw new GameTeamException(0xAA05);
            }

        } catch(DonneException de){

        } catch(GameTeamException gte){

        } catch(Exception e){

        }

        if(pos == -1) return; // Pour eviter que l'interpreteur ne s'emballe a rien.
        
        boolean teamFound = false;
        int index = donnes.size()-1;

        search_team_label :
        while(!teamFound && index >= 0){
             if(donnes.get(index).getEquipe().equals(equipes[pos])){

                score_equipes[pos] += donnes.get(index).totalScore();
                teamFound = true;

                break search_team_label;
             }
            index--;
        }



    }


    // Getters and setters

    /**
     *
     * @return Le nom de la partie.
     */
    public String getNom() { return name; }

    /**
     *
     * @param index Indice de l'équipe concernée (0 / 1).
     * @return      L'équipe sélectionnée.
     */
    public Equipe getEquipe(int index){ return (index >= 0 && index < equipes.length) ? equipes[index] : null;}
    public Equipe[] getEquipes(){ return equipes; }

    /**
     *
     * @return Les deux scores des deux équipes.
     */
    public int[] getScores(){ return score_equipes; }

    /**
     *
     * @param index Indice de l'équipe concernée (0 / 1).
     * @return      Le score de l'équipe sélectionnée.
     */
    public int getScore(int index) { return index >= 0 && index < score_equipes.length ? score_equipes[index] : -1; }



}
