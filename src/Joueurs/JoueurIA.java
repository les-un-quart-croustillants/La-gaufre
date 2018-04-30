package Joueurs;

import java.util.LinkedList;
import java.util.HashMap;
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
	public JoueurIA (Plateau p, Difficulte d){
		super();
		r = new Random();
		this.difficulte = d;
	}
	
	/**
	 * Trouve le coup pour passer d'un Plateau à un autre
	 * retourne (0,0) si le couple n'as pas ete trouve
	 * 
	 * @param origin Plateau avant le coup joue
	 * @param nouveau Plateau après le coup joué
	 * @return un Couple c la position a cliquer pour passer de origin a nouveau
	 */
	private Couple reconstruireCoup(Plateau origin, Plateau nouveau) {
		Couple c;
		for(int i=0;i < origin.largeur();i++) {
			for(int j=0;j < origin.hauteur();j++) {
				c = new Couple(j,i);
				if(nouveau.estMangee(c) && !origin.estMangee(c)) {
					return c;
				}
			}
		}
		return new Couple(0,0);
	}
	
	private int evaluerProfondeur(Plateau p) {
		return 1000;
	}
	
	private int evaluerA(int p) {
		return 1000;
	}
	
	private int evaluerB(int p) {
		return 0;
	}
	
	/**
	 * Calcule si la configuration a la racine de l'arbre des configurations
	 * est gagnante pour le joueur A en sachant que c'est au joeur A de jouer
	 * 
	 * @param n racine de l'arbre des configurations
	 * @return true si la configuration est gagnante pour le joueur A false sinon
	 */
	private int minimaxA(Noeud n, HashMap<Integer,Integer> r, int profondeur) {
		TabConverter.FilsNoeud (n);	//calcul des fils
		int heuristique;
		if (n.estFeuille() || profondeur == 0) {
			// la configuration ne permet pas de jouer,
			// le joueur B gagne
			heuristique = evaluerA(n.valeur()); 
			r.put(n.valeur(), heuristique);
			n.setHeuristic(heuristique);
			return heuristique;
		} else {
			// Le joueur A doit jouer
			heuristique = 0;
			// On parcours l'ensemble des coups jouables par A
			for(Noeud fils : n.fils()) {
				int curr = minimaxB(fils, r, profondeur-1);
				if(! r.containsKey(fils.valeur())) { // Si fils n'as pas encore ete calcule, le faire et mettre a jour r
					r.put(fils.valeur(), curr);
				}
				heuristique = Math.max(heuristique,r.get(fils.valeur()));
			}
			r.put(n.valeur(), heuristique);
			n.setHeuristic(heuristique);
			return heuristique;
		}
	}
	
	/**
	 * Calcule si la configuration a la racine de l'arbre des configurations
	 * est gagnante pour le joueur A en sachant que c'est au joeur B de jouer
	 * 
	 * @param n racine de l'arbre des configurations
	 * @return true si la configuration est gagnante pour le joueur A false sinon
	 */
	private int minimaxB(Noeud n,HashMap<Integer,Integer> r, int profondeur) {
		TabConverter.FilsNoeud (n);	//calcul des fils
		int heuristique;
		if (n.estFeuille() || profondeur == 0) {
			// la configuration ne permet pas de jouer
			// le joueur A gagne
			heuristique = evaluerB(n.valeur());
			r.put(n.valeur(), heuristique);
			n.setHeuristic(heuristique);
			return heuristique;
		} else {
			// Le joueur B doit jouer
			heuristique = 1000; // + infini
			// On parcours l'ensemble des coups jouables par B
			for(Noeud fils : n.fils()) {
				int curr = minimaxA(fils, r, profondeur-1);
				if(! r.containsKey(fils.valeur())) { // Si fils n'as pas encore ete calcule, le faire et mettre a jour r
					r.put(fils.valeur(), curr);
				}
				heuristique = Math.min(heuristique,r.get(fils.valeur()));
			}
			r.put(n.valeur(), heuristique);
			n.setHeuristic(heuristique);
			return heuristique;
		}
	}
	
	/**
	 * Joue un coup sur le plateau avec les regles d'IA faciles
	 * l'IA facile est un choix aleatoire du coup a jouer
	 * 
	 * @return True si le coup a bien ete joue, False sinon
	 */
	Couple jouerCoupFacile(Plateau plateau) {
		int i, j;
        
        i = r.nextInt(plateau.hauteur());
        j = r.nextInt(plateau.largeur());
        while (!plateau.estMangeable(new Couple(i,j))) {
            i = r.nextInt(plateau.hauteur());
            j = r.nextInt(plateau.largeur());
        }
        //plateau.manger(new Couple(i,j));
        return new Couple(i,j);

	}
	
	/**
	 * Joue un coup sur le plateau avec les regles d'IA moyennes
	 * 
	 * @return True si le coup a bien ete joue, False sinon
	 */
	Couple jouerCoupMoyen(Plateau plateau) {
		return new Couple(-1,-1);
	}
	
	/**
	 * Joue un coup sur le plateau avec les regles d'IA difficiles
	 * 
	 * @return True si le coup a bien ete joue, False sinon
	 */
	Couple jouerCoupDifficile(Plateau plateau) {
		ArbreConfiguration a = new ArbreConfiguration(TabConverter.ToInt(plateau)); // construction de l'arbre des configurations
		HashMap<Integer,Integer> memo = new HashMap<Integer,Integer>();
		int profondeur = evaluerProfondeur(plateau);
		if(minimaxA(a.racine(),memo,profondeur) > 0) {
			LinkedList<Noeud> cp = a.racine().filsTaggue(); //recuperations des solutions
			int rand = r.nextInt(cp.size()); //choix d'une solution admissible aleatoire
			Plateau nouveau = TabConverter.ToTab(cp.get(rand).valeur()); //traduction de la solution en Plateau
			Couple res = reconstruireCoup(plateau , nouveau); //traduction de la solution en Couple
			//plateau.manger(res); //Appliquer solution
			return res;
		} else {
			return jouerCoupFacile(plateau);
		}
	}
	
	@Override
	public int delai() {
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
	public Couple prochainCoup(Plateau p) {
		switch(this.difficulte) {
			case FACILE:
				return jouerCoupFacile(p);
			case MOYEN:
				return jouerCoupMoyen(p);
			case DIFFICILE:
				return jouerCoupDifficile(p);
			default:
				return new Couple(-1,-1);
		}
	}
	
}
