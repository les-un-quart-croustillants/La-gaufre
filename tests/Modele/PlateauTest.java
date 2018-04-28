package Modele;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlateauTest {

    Plateau sujet;
    @Before
    public void setUp() {
        sujet = new Plateau(3, 4, 4);
        sujet.setCase(new Couple(1,1), 3);
        sujet.setCase(new Couple(2,1), 3);
        sujet.setCase(new Couple(1,2), 2);
        sujet.setCase(new Couple(2,2), 2);
        sujet.setCase(new Couple(1,3), 2);
        sujet.setCase(new Couple(2,3), 1);
        /*
         * 0 1 2 3
         * 0 0 0 0 | 0
         * 0 3 2 2 | 1
         * 0 3 2 1 | 2
         *
         */
        System.out.println(sujet);
    }

    @Test
    public void estMangeable() {
        assertTrue(sujet.estMangeable(new Couple(0,0)));
        assertFalse(sujet.estMangeable(new Couple(1,1)));
        assertFalse(sujet.estMangeable(new Couple(2,1)));
        assertFalse(sujet.estMangeable(new Couple(2,3)));
    }

    @Test
    public void estSurPlateau() {
        System.out.println(sujet.tab[2][3]);
        assertTrue(sujet.estSurPlateau(new Couple(0, 0)));
        assertTrue(sujet.estSurPlateau(new Couple(2, 3)));
        assertTrue(sujet.estSurPlateau(new Couple(2, 1)));
        assertFalse(sujet.estSurPlateau(new Couple(4, 3)));
    }
    @Test
    public void manger() {
        Plateau p = sujet;

        Couple coord = new Couple(0,1);
        p.manger(coord);
        verifManger(p);
        assertEquals(5, p.getCompteurCoups());
        p.manger(coord);
        verifManger(p);
        assertEquals(5, p.getCompteurCoups());
    }

    private void verifManger(Plateau p) {
        assertEquals(4, p.getTab()[0][1]);
        assertEquals(4, p.getTab()[0][2]);
        assertEquals(4, p.getTab()[0][3]);
        for (int i = 0; i < p.hauteur(); i++) {
            for (int j = 1; j < p.largeur(); j++) {
                assertTrue(p.getTab()[i][j] != 0);
                if(sujet.getTab()[i][j] == 0)
                    assertEquals(4, p.getTab()[i][j]);
            }
        }
    }

    @Test
    public void toBinary() {
        Plateau p = new Plateau(3,3);
        assertEquals(0b000111, p.toBinary());
        assertEquals(0b011001, sujet.toBinary());
    }
}
