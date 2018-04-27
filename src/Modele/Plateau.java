package Modele;

import java.util.Arrays;
import java.util.LinkedList;

import Vue.Vue;

public class Plateau {
	 int [][] tab;
	    int compteurCoups,
	        hauteur,
	        largeur;

	    public Plateau() {
	        this(10,10, 1);
	    }

	    public Plateau(int hauteur, int largeur) {
	        this(hauteur, largeur, 1);
	    }

	    public Plateau(int hauteur, int largeur, int compteurCoups) {
	    	this.observers = new LinkedList<Vue>();
	        this.largeur = largeur;
	        this.hauteur = hauteur;
	        this.compteurCoups = compteurCoups;
	        this.tab = initTab(largeur, hauteur);
	    }

	    private int[][] initTab(int largeur, int hauteur) {
	        int[][] tab = new int[hauteur][largeur];
	        for (int[] e : tab) {
	            Arrays.fill(e, 0);
	        }
	        return tab;
	    }
	    /**
	     * estMangeable : Si une case est mangeable.
	     *  @param coord : coordonnées de la case.
	     *  @return : booléen vrai si la case est mangeable, faux sinon.
	     */
	    public boolean estMangeable(Couple coord) {
	        return estSurPlateau(coord) && !estMangee(coord);
	    }

	    /**
	     * estSurPlateau : Si une case est sur le plateau.
	     * @param coord : coordonée de la case.
	     * @return : booléen vrai si la case est dans le plateau, faux sinon.
	     */
	    public boolean estSurPlateau(Couple coord) {
	        return 0 <= coord.i && coord.i < hauteur
	                && 0 <= coord.j && coord.j < largeur;
	    }

	    /** FIXME : possible redondance avec estMangeable
	     * estMangee : Si une case a déjà été mangée ou non
	     * @param coord : coordonnée de la case
	     * @return : vrai si la case a été mangée faux sinon
	     */
	    public boolean estMangee(Couple coord) {
	        return tab[coord.i][coord.j] != 0;
	    }

	    /**
	     * manger : mange les cases en fonction de la position donnée.
	     * @param coord : coordonnées de la case.
	     */
	    public void manger(Couple coord) {
	        if(estMangeable(coord)) {
	            // mange toutes les cases en dessous et à droite de la position donnée
	            for(int i = coord.i; i < hauteur; i++) {
	                for (int j = coord.j; j < largeur; j++) {
	                    if (!estMangee(new Couple(i,j))) {
	                        tab[i][j] = compteurCoups;
	                    }
	                }
	            }
	            compteurCoups++;
	        }
	    }

	    // DEBUG FONCTION
	    public void setCase(Couple coord, int value) {
	        this.tab[coord.i][coord.j] = value;
	    }
	    
	    public int hauteur() {
	    	return hauteur;
	    }
	    
	    public int largeur() {
	    	return largeur;
	    }

	    @Override
	    public String toString() {
	        String res = "[\n";
	        for (int[] e : tab) {
	            res += Arrays.toString(e) + "\n";
	        }
	        return res +"," + largeur +","+ hauteur + "]";
	    }
}
