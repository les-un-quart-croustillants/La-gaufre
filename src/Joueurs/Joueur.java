package Joueurs;

import Modele.Couple;
import Modele.Plateau;

/**
 * 
 * @author Louka Soret
 *
 */
public abstract class Joueur {
	
	/**
	 * 
	 * @author Louka Soret
	 *
	 */
	public enum Difficulte {
		FACILE,
		MOYEN,
		DIFFICILE;
		
		/**
		 * 
		 * @param d
		 * @return d traduis en chaine de charactere
		 */
		static public String toString (Difficulte d) {
			switch(d) {
				case FACILE:
					return "facile";
				case MOYEN:
					return "moyen";
				case DIFFICILE:
					return "difficile";
				default:
					return "unknown";
			}
		}
		
		/**
		 * 
		 * @param s
		 * @return la difficulte corespondante a s
		 */
		static public Difficulte fromString (String s) {
			switch(s) {
				case "facile":
					return Difficulte.FACILE;
				case "moyen":
					return Difficulte.MOYEN;
				case "difficile":
					return Difficulte.DIFFICILE;
				default:
					return Difficulte.FACILE;
			}
		}
	}
	/**
	 * Constructeur pour la classe Joueur
	 * 
	 * @param p un plateau de jeu
	 * @author Louka Soret
	 */
	Joueur() {
	 }

	/**
	 * Méthode appelée lorsque le tour d'un joueur vient
	 * 
	 * @return un temps à attendre en millisecondes ou 0 si aucune temporisation requise
	 * @author Louka Soret
	 */
	public int delai() {
		return 0;
	}

	/**
	 * Méthode appelée une fois le temps écoulé, calcule et joue un coup 
	 * selon la difficulte actuelle
	 * 
	 * @return True si le traitement a reussi, False sinon
	 * @author Louka Soret
	 */
	public Couple prochainCoup(Plateau p) {
		return new Couple(-2,-2);
	}
	
	/**
	 * Méthode appelée lors d'un clic sur le plateau
	 * 
	 * @param i absisse
	 * @param j ordonnée
	 * @return True si le coup a bien été joué, False si le coup n'était pas jouable
	 * @author Louka Soret
	 */
	boolean jouerCoup(Plateau p, Couple pos) {
		return false;
	}
	
	/**
	 * 
	 * @return true si le joueur est une IA false sinon
	 * @author Louka Soret
	 */
	public boolean estIA() {
		return true;
	}
}
