package Joueurs;

import java.util.Random;
import Modele.Couple;
import Modele.Plateau;

/**
 * 
 * @author Louka Soret
 *
 */
public class JoueurIA extends Joueur {
	Random r;
	Difficulte difficulte;
	
	/**
	 * Constructeur d'une IA pour une difficulte donnee
	 * 
	 * @param p plateau de jeu
	 * @param d difficulte de l'IA
	 */
	JoueurIA (Plateau p, Difficulte d){
		super();
		r = new Random();
		this.difficulte = d;
	}
	
	/**
	 * Joue un coup sur le plateau avec les regles d'IA faciles
	 * l'IA facile est un choix aleatoire du coup a jouer
	 * 
	 * @return True si le coup a bien ete joue, False sinon
	 */
	boolean jouerCoupFacile(Plateau plateau) {
		int i, j;
        
        i = r.nextInt(plateau.hauteur());
        j = r.nextInt(plateau.largeur());
        while (!plateau.estMangeable(new Couple(i,j))) {
            i = r.nextInt(plateau.hauteur());
            j = r.nextInt(plateau.largeur());
        }
        plateau.manger(new Couple(i,j));
        return true;

	}
	
	/**
	 * Joue un coup sur le plateau avec les regles d'IA moyennes
	 * 
	 * @return True si le coup a bien ete joue, False sinon
	 */
	boolean jouerCoupMoyen(Plateau plateau) {
		return false;
	}
	
	/**
	 * Joue un coup sur le plateau avec les regles d'IA difficiles
	 * 
	 * @return True si le coup a bien ete joue, False sinon
	 */
	boolean jouerCoupDifficile(Plateau plateau) {
		return false;
	}
	
	@Override
	int delai() {
		switch(this.difficulte) {
			case FACILE: 
			case MOYEN:
				return 1000;
			case DIFFICILE:
				return 0;
			default:
				return 0;
		}
	}
	
	@Override
	boolean prochainCoup(Plateau p) {
		switch(this.difficulte) {
			case FACILE:
				return jouerCoupFacile(p);
			case MOYEN:
				return jouerCoupMoyen(p);
			case DIFFICILE:
				return jouerCoupDifficile(p);
			default:
				return false;
		}
	}
	
}
