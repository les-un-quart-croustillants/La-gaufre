package Modele;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlateauTest {

    private Plateau sujet;
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
    public void constructorPlateau() {
        Plateau p = new Plateau(sujet.hauteur(), sujet.largeur(), sujet.getCompteurCoups(), sujet.getTab());

        for (int i = 0; i < sujet.hauteur() - 1; i++) {
            for (int j = 0; j < sujet.hauteur() - 1; j++) {
                assertEquals(sujet.getTab()[i][j], p.getTab()[i][j]);
            }
        }
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
        assertTrue(p.manger(coord));
        verifManger(p);
        assertEquals(5, p.getCompteurCoups());
        assertFalse(p.manger(coord));
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

    @Test
    public void undo() {
        Plateau p = sujet.clone();
        p.manger(new Couple(0,1));
        assertNotEquals(p, sujet);
        assertTrue(p.undo());
        assertEquals(p, sujet);
        assertTrue(p.history.contains(new Couple(0,1)));
        p = new Plateau(3,3);
        Plateau tmp = p.clone();
        assertFalse(p.undo());
        assertEquals(p, tmp);
        assertTrue(p.history.isEmpty());
    }

    @Test
    public void redo() {
        sujet.undo();
        sujet.undo();
        assertFalse(sujet.history.isEmpty());
        assertEquals(new Couple(1,1), sujet.history.getFirst());
        assertEquals(new Couple(1,2), sujet.history.getLast());
        assertEquals(0, sujet.getTab()[1][1]);
        assertEquals(0, sujet.getTab()[1][2]);
        assertTrue(sujet.redo());
        assertEquals(0, sujet.getTab()[1][1]);
        assertEquals(2, sujet.getTab()[1][2]);
        assertEquals(new Couple(1,1), sujet.history.getFirst());
        assertTrue(sujet.redo());
        assertEquals(3, sujet.getTab()[1][1]);
        assertTrue(sujet.history.isEmpty());
        assertFalse(sujet.redo());
    }
    @Test
    public void equals() {
        Plateau p = new Plateau(3,3);
        assertNotEquals(p, sujet);
        p = new Plateau(3,4, 4);
        assertNotEquals(p, sujet);
        p = sujet.clone();
        assertEquals(p, sujet);
    }
}
