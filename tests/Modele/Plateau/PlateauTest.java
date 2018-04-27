package Modele.Plateau;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PlateauTest {
    Plateau sujet;
    @BeforeEach
    void setUp() {
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
    void estMangeable() {
        assertTrue(sujet.estMangeable(new Couple(0,0)));
        assertFalse(sujet.estMangeable(new Couple(1,1)));
        assertFalse(sujet.estMangeable(new Couple(2,1)));
        assertFalse(sujet.estMangeable(new Couple(2,3)));
    }

}