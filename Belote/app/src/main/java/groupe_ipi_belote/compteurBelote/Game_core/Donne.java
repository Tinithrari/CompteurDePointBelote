package groupe_ipi_belote.compteurBelote.Game_core;

/**
 * Created by Axel on 27/01/2015.
 */


import groupe_ipi_belote.compteurBelote.Components_core.Equipe;
import groupe_ipi_belote.compteurBelote.Components_core.Color;
import groupe_ipi_belote.compteurBelote.Components_core.Joueur;
import groupe_ipi_belote.compteurBelote.Components_core.Cards;
import groupe_ipi_belote.compteurBelote.Components_core.Value;
import groupe_ipi_belote.compteurBelote.Exceptions_core.CardException;
import groupe_ipi_belote.compteurBelote.Exceptions_core.CustomExceptionTemplate;
import groupe_ipi_belote.compteurBelote.Exceptions_core.DonneException;
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
        try{
            if(ctt == null || clr == Color.UNDEFINED || clr == null) {
                throw new DonneException( ctt == null ? 0xAC00 : 0xAC01);
            } else {
                contractant = ctt;
                couleur     = clr;
            }
        } catch(CustomExceptionTemplate cet){

        } catch(Exception e){

        }

    }

    /**
     *
     * @return
     */
    public int[] calculerScore(){
        if(annonces != null || annonces.size() == 0){
            int[] tmp = new int[1];
            tmp[0] = 0;
            return tmp;
        }

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
     * @param found         Si la valeur a déjà été trouvé ou non
     * @param typeOfCard    Couleur de la carte impliquée
     * @param valueOfCard   Valeur de la carte impliquée
     * @param direction     Le type de recherche qui va être utilisé (0/1)
     */
    private void common_check(boolean found, Color typeOfCard, Value valueOfCard, final int direction) {
        if (!found) {

            ArrayList<Cards> followingCards = new ArrayList<>();

            int aofc = 0; // aofc : Amount Of Following Cards

            switch (direction) {
                case 0:
                    valueOfCard = null; // On recherche selon la valeur (suite / carre etc.)
                    break;

                case 1:
                    typeOfCard = null; // On recherche selon la couleur (Tierce / Cinquante etc.)
                    break;
            }

            Label_players:
            for (Joueur j : contractant.getPlayers()) {
                found = false;

                // On recupere les cartes de chaque mains
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

            // Si la derniere valeur de found est "true", on a trouve un ou plusieurs elements.
            if (found) {
                if (direction == 0 && valueOfCard != null) {

                    switch (valueOfCard) {
                        case VALET:
                            annonces.add(new CarreV(contractant));
                            break;

                        case NEUF:
                            annonces.add(new CarreN(contractant));
                            break;

                        case AS:
                        case DIX:
                        case DAME:
                        case ROI:
                            annonces.add(new CarreADR(contractant));
                            break;

                        // Annonce nulle : Sept, Huit etc.
                        default:
                            annonces.add(new Carre(contractant) {
                                @Override
                                public int annonce() {
                                    return 0;
                                }
                            });
                    }
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

    /**
     * Deprecated Method
     * @param found         Si la valeur a déjà été trouvé ou non
     * @param typeOfCard    Couleur de la carte impliquée
     */
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

    /**
     * Deprecated Method
     * @param found         Si la valeur a déjà été trouvé ou non
     * @param typeOfCard    Face de la carte impliquée
     */
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
            common_check(found,colorOfCard, typeOfCard, 1);

        }
    }

    /**
     *  On ajoute une annonce vide
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

    /**
     *
     * @return
     */
    public Main[] getMains(){
        Main[] tmp = new Main[contractant.getPlayers().length];

        for(Joueur j : contractant.getPlayers()){

            for ( int i = 0; i < tmp.length; i++){
                if( tmp[i] != null ) continue;
                tmp[i] = j.getMain();
            }
        }

        return tmp;
    }

    public Equipe getEquipe(){
        return contractant;
    }

    public String getNomEquipe(){
        return contractant.getNomEquipe();
    }
}
