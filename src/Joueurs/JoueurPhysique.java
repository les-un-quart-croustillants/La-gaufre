package Joueurs;

import Modele.Couple;
import Modele.Plateau;

public class JoueurPhysique extends Joueur{
	
	JoueurPhysique(Plateau p){
		super(p);
	}
	
	@Override
	boolean jouerCoup(Couple pos) {
        if (plateau.estMangeable(pos)) { // Test coup valide
            plateau.manger(pos);
            return true;
        } else {
            return false;
        }
	}

}
