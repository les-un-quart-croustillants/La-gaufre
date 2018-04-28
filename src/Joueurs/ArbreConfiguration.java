package Joueurs;

public class ArbreConfiguration {
	private int valeur;
	private ArbreConfiguration fg, fd;

	// Constructeurs

	/**
	 * Constructeur pour une feuille
	 *
	 * @param  v  	Valeur entiere
	 */
	public ArbreConfiguration(int v) {
		this.valeur = v;
	}

	/**
	 * Constructeur pour un arbre avec pour etiquette de racine v, pour sous arbre gauche g
	 * et pour sous arbre droit d
	 * 
	 * @param v		Valeur de la racine
	 * @param g		Sous arbre gauche
	 * @param d		Sous arbre droit
	 */
	public ArbreConfiguration(int v, ArbreConfiguration g, ArbreConfiguration d) {
		this.valeur = v;
		this.fg = g;
		this.fd = d;
	}
	
	/**
	 * Getteur pour l'etiquette de la racine de l'arbre
	 * 
	 * @return valeur de l'etiquette de la racine
	 */
	public int valeur() {
		return this.valeur;
	}

	@Override
	public String toString() {
		return toString("\t");
	}

	/**
	 * construction de l'affichage recurcif d'un arbre dans la sortie standard 
	 * 
	 * @param s type de separateur
	 * @return chaine de characteres contenant l'affichage cree
	 */
	public String toString(String s) {
		if (fg != null) {
			if (fd != null)
				return (s + valeur + "\n" + fg.toString(s + "\t") + fd.toString(s + "\t"));
			else
				return (s + valeur + "\n" + fg.toString(s + "\t") + "\n");
		} else if (fd != null)
			return (s + valeur + "\n\n" + fd.toString(s + "\t"));
		else
			return (s + valeur + "\n");
	}
}
