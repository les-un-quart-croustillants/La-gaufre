package Joueurs;

import java.util.LinkedList;

public class Noeud {
	private int valeur;
	private int tag;
	private Noeud pere;
	private LinkedList<Noeud> fils;
	
	public Noeud(int v) {
		this.valeur = v;
		fils = new LinkedList<Noeud>();
	}
	
	/**
	 * 
	 * @return valeur du noeud
	 */
	public int valeur() {
		return this.valeur;
	}
	
	/**
	 * 
	 * @return tag du noeud
 	 */
	public int tag() {
		return this.tag;
	}
	
	/**
	 * 
	 * @return pere du noeud
	 */
	public Noeud pere() {
		return this.pere;
	}
	
	/**
	 * 
	 * @return liste des fils du noeud
	 */
	public LinkedList<Noeud> fils(){
		return this.fils;
	}
	
	/**
	 * 
	 * @param i indice du fils a recupere
	 * @return fils i du noeud
	 */
	public Noeud fils(int i) {
		return this.fils.get(i);
	}
	
	/**
	 * 
	 * @param v nouvelle valeur pour le noeud
	 */
	public void setValeur(int v) {
		this.valeur = v;
	}
	
	/**
	 * 
	 * @param t nouveau tag pour le noeud
 	 */
	public void setTag(int t) {
		this.tag = t; 
	}
	
	/**
	 * 
	 * @param p nouveau pere pour le noeud
	 */
	public void setPere(Noeud p) {
		this.pere = p;
	}
	
	/**
	 * Remplace la liste des fils
	 * 
	 * @param l nouvelle liste des noeud
	 */
	public void setFils(LinkedList<Noeud> l) {
		this.fils = l;
	}
	
	/**
	 * 
	 * @param n Noeud a ajouter a la liste des fils
	 */
	public void addFils(Noeud n) {
		this.fils.add(n);
	}
	
	/**
	 * 
	 * @param i indice du noeud a supprimer de la liste des fils
	 */
	public void removeFils(int i) {
		this.fils.remove(i);
	}
	
}