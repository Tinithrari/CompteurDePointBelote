package groupe_ipi_belote.compteurBelote;

import junit.framework.TestCase;
import groupe_ipi_belote.compteurBelote.Score_core.*;
import groupe_ipi_belote.compteurBelote.Components_core.*;
/**
 * Created by Maxence on 19/02/2015.
 */
public class TestScore extends TestCase {


    protected void TestBeloteEtRebelote(){
        BeloteEtRebelote br;
        try {
            br = new BeloteEtRebelote(null,null);
        } catch(Exception e){
            System.err.print(e.getCause() + " - " + e.getMessage());
        }

        Joueur j= new Joueur("test");
        Joueur j2= new Joueur("test");
        Equipe e= new Equipe("test", j,j2);
        Color c =   Color.TREFLE;

        br = new BeloteEtRebelote( e,  c);
        int test=20;
        assertNotNull(br);
        assertEquals(test,br.annonce());
    }

    protected void Carre(){
        Carre c;
    }

    protected void CarreADR(){
        CarreADR c = null;

        int test=100;
        assertEquals(c.annonce(),test);
    }


    protected void CarreN(){
        CarreN cn = null;

        int test=150;
        assertEquals(cn.annonce(),test);
    }

    protected void CarreV(){
        CarreV cv = null;

        int test=200;
        assertEquals(cv.annonce(),test);
    }

    protected void Cent(){
       Cent c = null;

        int test=100;
        assertEquals(c.annonce(),test);
    }

    protected void Cinquante(){
        Cinquante c = null;

        int test=50;
        assertEquals(c.annonce(),test);
    }

    protected void StratAnnonce(){

    }

    protected void StratSequence(){

    }

    protected void Tierce(){
        Tierce t = null;

        int test=20;
        assertEquals(t.annonce(),test);
    }

}
