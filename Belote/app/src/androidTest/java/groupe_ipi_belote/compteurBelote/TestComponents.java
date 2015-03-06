package groupe_ipi_belote.compteurBelote;

import junit.framework.TestCase;
import groupe_ipi_belote.compteurBelote.Components_core.*;
import groupe_ipi_belote.compteurBelote.Exceptions_core.CardCstException;
import groupe_ipi_belote.compteurBelote.Exceptions_core.CustomExceptionTemplate;
import groupe_ipi_belote.compteurBelote.Exceptions_core.GameTeamException;

/**
 * Created by Axel on 02/.02/2015.
 */
public class TestComponents extends TestCase {
    public TestComponents(){
        super();

    }

    @Override
    protected void runTest() throws Throwable {
        super.runTest();
    }

    protected void testCards(){
        Cards cd;
        try {
            cd = new Cards(null, null);
        } catch(Exception e){
            System.err.print(e.getCause() + " - " + e.getMessage());
        }

        try {
            cd = new Cards(Color.CARREAU, Value.VALET);
            assertNotNull(cd.getFace());
            assertNotNull(cd.getValue());
        } catch (CardCstException e) {
            e.printStackTrace();
        }


    }

    //complete by Delory Maxence

    protected void testColor(){

    }

    protected void testEquipe(){
        Equipe eq;
        Joueur[] team = new Joueur[2];
        try {
            eq = new Equipe(null,null);
        } catch(Exception e){
            System.err.print(e.getCause() + " - " + e.getMessage());
        }

        try {
            eq = new Equipe("test",team);
            Equipe eq2 = eq;
            assertNotNull(eq.getNomEquipe());
            assertEquals(eq.compareTo(eq2),true);
            eq =new Equipe("test2","Nom1","Nom2");
            assertNotSame(eq,eq2);
            assertEquals(eq.compareTo(eq2),false);
            assertNotNull(eq.getNomEquipe());
            eq =new Equipe("test2",team[0].getNom(),team[1].getNom());
            assertNotNull(eq.getNomEquipe());

            eq =new Equipe("test2",team[0],team[1]);
            assertNotNull(eq.getNomEquipe());

            eq = new Equipe(eq2);
            assertNotNull(eq.getNomEquipe());
        } catch (GameTeamException e) {
            e.printStackTrace();
        } catch (CustomExceptionTemplate customExceptionTemplate) {
            customExceptionTemplate.printStackTrace();
        }

    }

    protected void testJoueur() {
        Joueur j;
        try {
            j = new Joueur(null);
        } catch(Exception e){
            System.err.print(e.getCause() + " - " + e.getMessage());
        }

        try {
            j = new Joueur("test");
            Joueur j2= j ;
            assertEquals(j.equals(j2),true);
            j2= new Joueur("test2");
            assertEquals(j.equals(j2),false);
            assertNotNull(j.getNom());
            assertNotNull(j.getMain());
        } catch (CustomExceptionTemplate customExceptionTemplate) {
            customExceptionTemplate.printStackTrace();
        }


    }

    protected void testPaquet(){
        Paquet p= null;
        try {
            p = new Paquet();
            assertNotNull(p);
        } catch (CardCstException e) {
            e.printStackTrace();
        }


    }

    protected void testValue(){

    }
}
