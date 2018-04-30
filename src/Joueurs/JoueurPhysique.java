package Joueurs;

import Modele.Couple;
import Modele.Plateau;

public class JoueurPhysique extends Joueur{
	
	public JoueurPhysique(){
		super();
	}
	
	@Override
	boolean jouerCoup(Plateau p, Couple pos) {
        if (p.estMangeable(pos)) { // Test coup valide
            p.manger(pos);
            return true;
        } else {
            return false;
        }
	}
	
	@Override
	public boolean estIA() {
		return false;
	}
}
