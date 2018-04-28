package Joueurs;

import java.util.LinkedList;
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
	
	private Couple reconstruireCoup(Plateau origin, Plateau nouveau) {
		Couple c;
		for(int i=0;i < origin.largeur();i++) {
			for(int j=0;i < origin.hauteur();j++) {
				c = new Couple(i,j);
				if(nouveau.estMangee(c) && !origin.estMangee(c)) {
					return c;
				}
			}
		}
		return new Couple(0,0);
	}
	
	private boolean minimaxA(Noeud n) {
		if (n.estFeuille()) {
			// la configuration ne permet pas de jouer,
			// le joueur B gagne
			n.setTag(false);
			return false;
		} else {
			// Le joueur A doit jouer
			boolean tag = false;
			// On parcours l'ensemble des coups jouables par A
			for(Noeud fils : n.fils()) {
				tag = tag || minimaxB(fils);
			}
			n.setTag(tag);
			return tag;
		}
	}
	
	private boolean minimaxB(Noeud n) {
		if (n.estFeuille()) {
			// la configuration ne permet pas de jouer
			// le joueur A gagne
			n.setTag(true);
			return true;
		} else {
			// Le joueur B doit jouer
			boolean tag = true;
			// On parcours l'ensemble des coups jouables par B
			for(Noeud fils : n.fils()) {
				tag = tag && minimaxB(fils);
			}
			n.setTag(tag);
			return tag;
		}
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
		ArbreConfiguration a = new ArbreConfiguration(); // construction de l'arbre des configurations
		if(minimaxA(a.racine())) {
			LinkedList<Noeud> cp = a.racine().filsTaggue();
			return reconstruireCoup(PlateauConverter(cp.get(r.nextInt(cp.size())).valeur())); //remplacer Converter !
		} else {
			return false;
		}
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
