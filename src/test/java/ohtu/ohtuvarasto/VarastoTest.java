package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void otetaanLiikaaPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(4);

        double saatuMaara = varasto.otaVarastosta(6);
        
        //pitäisi palauttaa kaikki, eli 4
        assertEquals(4, saatuMaara, vertailuTarkkuus);
    }    
    
    @Test
    public void otetaanLiikaaVarastoTyhjenee() {
        varasto.lisaaVarastoon(4);

        varasto.otaVarastosta(6);
        
        //pitäisi palauttaa kaikki, eli 4
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }    
    
    @Test
    public void otetaanNegatiivinenPalauttaaNollan() {

        double saatuMaara = varasto.otaVarastosta(-6);
        assertEquals(0, saatuMaara, vertailuTarkkuus);
    }    
        
    @Test
    public void lisaysLisaaLiikaaSaldoa() {
        varasto.lisaaVarastoon(8);
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kuin ensimmäisen lisäyksen jälkeen, eli 8
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysLisaaNegatiivinen() {
        varasto.lisaaVarastoon(4);
        varasto.lisaaVarastoon(-1);

        // saldon pitäisi olla sama kuin ennen lisäystä, eli 4
        assertEquals(4, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void liianPieniVarastoKonstruktoriSaldo() {
        Varasto testivarasto = new Varasto(0);

        // saldon pitäisi olla sama kuin ennen lisäystä, eli 4
        assertEquals(0, testivarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void liianPieniVarastoKonstruktoriTilavuus() {
        Varasto testivarasto = new Varasto(0);

        // saldon pitäisi olla sama kuin ennen lisäystä, eli 4
        assertEquals(0, testivarasto.getTilavuus(), vertailuTarkkuus);
    }    
    
    @Test
    public void varastoToString() {
        varasto.lisaaVarastoon(5);

        // saldo 5.0 tilavuus 10, joten mahtuu 5.0
        assertEquals("saldo = 5.0, vielä tilaa 5.0", varasto.toString());
    }
    
    @Test
    public void tuplKonstruktoriHyväSyöte() {
        Varasto testivarasto = new Varasto(5, 3);

        // tilaa pitäisi olla 5-3=2
        assertEquals(2, testivarasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void tuplKonstruktoriLiianPieni() {
        Varasto testivarasto = new Varasto(0, 3);

        // tilaa pitäisi olla 0
        assertEquals(0, testivarasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void tuplKonstruktoriLiianPieniSaldo() {
        Varasto testivarasto = new Varasto(5, -3);

        // saldon pitäisi olla 0
        assertEquals(0, testivarasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void tuplKonstruktoriLiianIsoSaldo() {
        Varasto testivarasto = new Varasto(5, 7);

        // saldon pitäisi olla tilavuus, eli 5
        assertEquals(4, testivarasto.getSaldo(), vertailuTarkkuus);
    }
    
    

}