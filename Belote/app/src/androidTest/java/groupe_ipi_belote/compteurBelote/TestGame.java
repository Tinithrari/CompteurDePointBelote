package groupe_ipi_belote.compteurBelote;

import junit.framework.TestCase;

import groupe_ipi_belote.compteurBelote.Components_core.Cards;
import groupe_ipi_belote.compteurBelote.Components_core.Color;
import groupe_ipi_belote.compteurBelote.Components_core.Equipe;
import groupe_ipi_belote.compteurBelote.Components_core.Joueur;
import groupe_ipi_belote.compteurBelote.Components_core.Value;
import groupe_ipi_belote.compteurBelote.Exceptions_core.CustomExceptionTemplate;
import groupe_ipi_belote.compteurBelote.Game_core.BeloteEtRebelote;
import groupe_ipi_belote.compteurBelote.Game_core.Donne;
import groupe_ipi_belote.compteurBelote.Game_core.Main;
import groupe_ipi_belote.compteurBelote.Game_core.Partie;
import groupe_ipi_belote.compteurBelote.Game_core.Pli;

/**
 * Created by Maxence on 16/02/2015.
 */
public class TestGame extends TestCase {

    public TestGame(){
        super();

    }

    @Override
    protected void runTest() throws Throwable {
        super.runTest();
    }


    protected void TestBeloteEtRebelote(){
        BeloteEtRebelote br;
        try {
            br = new BeloteEtRebelote(null,null);
        } catch(Exception e){
            System.err.print(e.getCause() + " - " + e.getMessage());
        }

        Joueur j= null;
        try {
            j = new Joueur("test");
            Joueur j2= new Joueur("test");
            Equipe e= new Equipe("test", j,j2);
            Color c =   Color.TREFLE;

            br = new BeloteEtRebelote( e,  c);

            assertNotNull(br);
        } catch (CustomExceptionTemplate customExceptionTemplate) {
            customExceptionTemplate.printStackTrace();
        }


    }

    protected void TestDonne(){
        Donne d;
        try {
            d = new Donne(null,null);
        } catch(Exception e){
            System.err.print(e.getCause() + " - " + e.getMessage());
        }

        Joueur j= null;
        try {
            j = new Joueur("test");
            Joueur j2= new Joueur("test");
            Equipe e= new Equipe("test", j,j2);
            Color c =   Color.TREFLE;

            d = new Donne( e, c);

            assertNotNull(d);
            assertNotNull(d.getNomEquipe());
            assertNotNull(d.getAtout());
            assertNotNull(d.getEquipe());
            assertNotNull(d.getMains());
            assertEquals(d.calculerScore(),0);
            assertEquals(d.totalScore(),0);

        } catch (CustomExceptionTemplate customExceptionTemplate) {
            customExceptionTemplate.printStackTrace();
        }

    }

    protected void TestMain(){
        Main m;
        try {
            m = new Main(null);
        } catch(Exception e){
            System.err.print(e.getCause() + " - " + e.getMessage());
        }

        Joueur j= null;
        try {
            j = new Joueur("test");
            m = new Main(j);
            Cards cd = new Cards(Color.CARREAU, Value.VALET);
            assertNotNull(m);
            assertNotNull(m.getOwner());
            m.ajouterCarte(cd);
            assertEquals(m.getOwner().getMain().getCards().size(),1);
            assertEquals(m.getOwner().getMain().getOneCard(cd),cd);

        } catch (CustomExceptionTemplate customExceptionTemplate) {
            customExceptionTemplate.printStackTrace();
        }






    }

    protected void TestPartie(){
        Partie p;
        try {
            p = new Partie(null,null,null);
        } catch(Exception e){
            System.err.print(e.getCause() + " - " + e.getMessage());
        }

        Joueur j= null;
        try {
            j = new Joueur("test");
            Joueur j2= new Joueur("test");
            Joueur j3= new Joueur("test3");
            Joueur j4= new Joueur("test4");
            Equipe e= new Equipe("test1", j,j2);
            Equipe e2= new Equipe("test2", j3,j4);

            p = new Partie( "test", e , e2);

            assertNotNull(p);
            assertNotNull(p.getEquipe(0));
            assertNotNull(p.getEquipe(1));
            assertNotNull(p.getEquipes());
            assertNotNull(p.getScore(0));
            assertNotNull(p.getScore(1));
            assertNotNull(p.getScores());
        } catch (CustomExceptionTemplate customExceptionTemplate) {
            customExceptionTemplate.printStackTrace();
        }

    }

    protected void TestPli(){
        Pli p;
        try {
            p = new Pli(null,null);
        } catch(Exception e){
            System.err.print(e.getCause() + " - " + e.getMessage());
        }

        Joueur j= null;
        try {
            j = new Joueur("test");
            Joueur j2= new Joueur("test");
            Equipe e= new Equipe("test", j,j2);
            Color c2 =   Color.TREFLE;
            Value v =   Value.ROI;
            Cards c=new Cards(c2,v);

            p = new Pli( e, c);

            assertNotNull(p);
            assertNotNull(p.getCartes());
            assertNotNull(p.getGagnant());
        } catch (CustomExceptionTemplate customExceptionTemplate) {
            customExceptionTemplate.printStackTrace();
        }

    }

}
