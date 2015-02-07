package groupe_ipi_belote.compteurBelote.Game_core;

/**
 * Created by Axel on 27/01/2015.
 */


import groupe_ipi_belote.compteurBelote.Components_core.Equipe;
import groupe_ipi_belote.compteurBelote.Components_core.Color;
import groupe_ipi_belote.compteurBelote.Components_core.Joueur;
import groupe_ipi_belote.compteurBelote.Components_core.Cards;
import groupe_ipi_belote.compteurBelote.Components_core.Value;
import groupe_ipi_belote.compteurBelote.Score_core.*;



import java.util.PriorityQueue;
import java.util.ArrayList;

public class Donne {
    private Equipe contractant;
    private Color  couleur;
    private final ArrayList<StratAnnonce> annonces = new ArrayList<StratAnnonce>();

    /**
     *
     * @param ctt Equipe contractante
     * @param clr Couleur désignée
     */
    public Donne(Equipe ctt, Color clr){
        if(ctt == null || clr == null) {
            // throw new DonneCstrException();
        } else {
            contractant = ctt;
            couleur     = clr;
        }
    }

    /**
     *
     * @return
     */
    public int[] calculerScore(){
        int[] tmp = new int[annonces.size()];

        for (int i = 0; i < tmp.length; ++i){
            tmp[i] = annonces.get(i).annonce();
        }

        return tmp;
    }

    public int totalScore(){
        int temp = 0;

        for(int i : calculerScore()){
            temp += i;
        }

        return temp;
    }
    /**
     *
     */

    private void common_check(boolean found, Color typeOfCard, Value valueOfCard, final int direction) {
        if (!found) {

            ArrayList<Cards> followingCards = new ArrayList<>();

            int aofc = 0;

            switch (direction) {
                case 0:
                    valueOfCard = null;
                    break;

                case 1:
                    typeOfCard = null;
                    break;
            }

            Label_players:
            for (Joueur j : contractant.getPlayers()) {
                found = false;

                Main m = j.getMain();
                ArrayList<Cards> alc = m.getCards();

                Label_cards:
                for (int i = 0; i < alc.size() - 1; ++i) {
                    if(direction == 1)
                        typeOfCard = alc.get(i).getFace();

                    for (int k = i + 1; k < alc.size(); ++k) {
                        if (direction == 0) {

                            if (alc.get(i).getValue().getRang() != alc.get(k).getValue().getRang() - 1 && alc.get(i).getFace() == alc.get(k).getFace()) {
                                break Label_cards;

                            } else {
                                aofc++;
                                followingCards.add(alc.get(i));
                                found = true;
                            }

                        } else {
                            if (alc.get(i).getValue() != alc.get(k).getValue()) {
                                break Label_cards;
                            } else {
                                valueOfCard = alc.get(i).getValue();
                                found = true;
                            }

                        }
                    }

                    if (found) {
                        break Label_players;
                    } else {
                        if (direction == 0) {
                            valueOfCard = null;
                            followingCards.clear();
                        } else {
                            typeOfCard = null;
                        }
                    }
                }

            }


            if (found && typeOfCard != null) {
                if (direction == 0 && valueOfCard != null) {

                    switch (valueOfCard) {
                        case VALET:
                            annonces.add(new CarreV(contractant));
                            break;

                        case NEUF:
                            annonces.add(new CarreN(contractant));
                            break;

                        case AS: case DIX: case DAME: case ROI:
                            annonces.add(new CarreADR(contractant));
                            break;

                        default:
                            annonces.add(new Carre(contractant) { @Override public int annonce() { return 0; } });
                    }

                    if (direction == 1 && typeOfCard != null) {
                        switch (aofc) {
                            case 3:
                                annonces.add(new Tierce(contractant, followingCards.toArray(new Cards[followingCards.size()])));
                                break;

                            case 4:
                                annonces.add(new Cinquante(contractant, followingCards.toArray(new Cards[followingCards.size()])));
                                break;

                            case 5:
                                annonces.add(new Cent(contractant, followingCards.toArray(new Cards[followingCards.size()])));
                                break;
                        }
                    }

                }

            }
        }
    }

    private void check_colors(boolean found, Color typeOfCard){
        if(!found) {
            // Amount of Following Cards
            int aofc = 0;
            ArrayList<Cards> followingCards = new ArrayList<>();

            Label_players:
            for (Joueur j : contractant.getPlayers()) {
                found = false;

                Main m = j.getMain();
                ArrayList<Cards> alc = m.getCards();

                Label_cards:
                for (int i = 0; i < alc.size() - 1; ++i) {
                    typeOfCard = alc.get(i).getFace();

                    for (int k = i + 1; k < alc.size(); ++k) {
                        if (alc.get(i).getValue().getRang() != alc.get(k).getValue().getRang()-1
                                && alc.get(i).getFace() == alc.get(k).getFace() ){
                            break Label_cards;
                        } else {
                            aofc++;
                            followingCards.add(alc.get(i));
                            found = true;
                        }
                    }
                }

                if (found) { break Label_players; }
                else {
                    typeOfCard = null;
                    followingCards.clear();
                }
            }

            if (found && typeOfCard != null) {

                switch (aofc) {
                    case 3:
                        annonces.add(new Tierce(contractant, followingCards.toArray(new Cards[followingCards.size()]    ) ) );
                        break;

                    case 4:
                        annonces.add(new Cinquante(contractant, followingCards.toArray(new Cards[followingCards.size()] ) ) );
                        break;

                    case 5:
                        annonces.add(new Cent(contractant , followingCards.toArray(new Cards[followingCards.size()]     ) ) );
                        break;

                }

            }
        }
    }

    private void check_values(boolean found, Value typeOfCard){
        if(!found) {
            Label_players:
            for (Joueur j : contractant.getPlayers()) {
                found = false;

                Main m = j.getMain();
                ArrayList<Cards> alc = m.getCards();

                Label_cards:
                for (int i = 0; i < alc.size() - 1; ++i) {
                    for (int k = i + 1; k < alc.size(); ++k) {

                        if (alc.get(i).getValue() != alc.get(k).getValue()) {
                            break Label_cards;
                        } else {
                            typeOfCard = alc.get(i).getValue();
                            found = true;
                        }

                    }
                }

                if (found) { break Label_players; } else { typeOfCard = null; }
            }

            if (found && typeOfCard != null) {

                switch (typeOfCard) {
                    case VALET: annonces.add(new CarreV(contractant)); break;

                    case NEUF:  annonces.add(new CarreN(contractant)); break;

                    case AS: case DIX: case DAME: case ROI:
                        annonces.add(new CarreADR(contractant));
                        break;

                    default:
                        annonces.add(new Carre(contractant) {
                            @Override
                            public int annonce() {
                                return 0;
                            }
                        });

                }

            }
        }
    }

    public void annonce(){
        if(contractant != null){

            boolean found = false;



            /*
                 Deux choix possibles :
                 Vérification automatique, c'est-à-dire que le programme va vérifier les types d'annonces.
                 Vérification manuelle, le joueur va entrer manuelle son annonce, qui sera ensuite vérifiée.

                 Dans le cas de l'automatique, on ne tiendra pas compte, dans un premier temps, des possibiliés
                 multiples.
            */


            Value typeOfCard = null;
            Color colorOfCard = null;

            common_check(found,colorOfCard, typeOfCard, 0);
            common_check(found,colorOfCard, typeOfCard, 0);

        }
    }

    /**
     *
     */
    public void ajouterAnnonce(){
        annonces.add(new StratAnnonce(contractant) {
            @Override
            public int annonce() {
                return 0;
            }
        });
    }

    /**
     *
     * @return
     */
    public Color getAtout(){
        return couleur;
    }

    /**S
     *
     * @return
     */
    public Main[] getMains(){
        Main[] tmp = new Main[2];

        for(Joueur j : contractant.getPlayers()){

            for ( int i = 0; i < tmp.length; i++){
                if( tmp[i] != null ) continue;
                tmp[i] = j.getMain();
            }
        }

        return tmp;
    }
}
