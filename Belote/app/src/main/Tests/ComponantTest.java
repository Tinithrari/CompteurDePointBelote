import junit.framework.Assert;
import android.test.AndroidTestCase;

// Lancement via Android JUnit Test
// adb shell am instrument -w nom.tests/android.test.InstrumentationTestRunner


import junit.framework.TestCase;
import groupe_ipi_belote.compteurBelote.Components_core.*;

/**
 * Created by Axel on 02/.02/2015.
 */
public class ComponantTest extends TestCase {
    public ComponantTest(){
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

        cd = new Cards(Color.CARREAU, Value.VALET);

        assertNotNull(cd.getFace());
        assertNotNull(cd.getValue());
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

        eq = new Equipe("test",team);
        Equipe eq2 = eq;
        assertNotNull(eq.getNomEquipe());

        eq =new Equipe("test2","Nom1","Nom2");
        assertNotSame(eq,eq2);
        assertNotNull(eq.getNomEquipe());
        eq =new Equipe("test2",team[0].getNom(),team[1].getNom());
        assertNotNull(eq.getNomEquipe());

        eq =new Equipe("test2",team[0],team[1]);
        assertNotNull(eq.getNomEquipe());

        eq = new Equipe(eq2);
        assertNotNull(eq.getNomEquipe());
    }

    protected void testJoueur(){
        Joueur j;
        try {
            j = new Joueur(null);
        } catch(Exception e){
            System.err.print(e.getCause() + " - " + e.getMessage());
        }

        j = new Joueur("test");

        assertNotNull(j.getNom());
        assertNotNull(j.getMain());
    }

    protected void testPaquet(){
        Paquet p= new Paquet();
        assertNotNull(p);

    }

    protected void testValue(){

    }
}
