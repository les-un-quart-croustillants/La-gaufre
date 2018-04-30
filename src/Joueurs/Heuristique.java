package Joueurs;

import Modele.Plateau;

public class Heuristique {
    public static int nbzeroligne(int[][] tab, int i, int M) {
    	int res = 0;
    	for(int j = 0; j < M; j++) {
    		if(tab[i][j] == 0) {
    			res++;
    		}
    	}
    	return res;
    }
    public static int nbzerocolonne(int[][] tab, int j, int N) {
    	int res = 0;
    	for(int i = 0; i < N; i++) {
    		if(tab[i][j] == 0) {
    			res++;
    		}
    	}
    	return res;
    }
	/**
     * calcule_heuristique : modifie par effet de bord l'heuristique d'un noeud donné
     *  @param N : le noeud duquel il faut calculer l'heuristique
     */
	public static void calcule_heuristique(Noeud N) {
		//on recupere le tableau du pere
		Plateau Ppere = TabConverter.ToTab(N.pere().valeur());
		int[][] tabpere = Ppere.getTab();
		
		//on recupere le tableau du noeud courant
		Plateau Pcurrent = TabConverter.ToTab(N.valeur());
		int[][] tabcurrent = Pcurrent.getTab();
		
		//on recupere les dimensions
		int hauteur = Pcurrent.hauteur();
		int largeur = Ppere.largeur();
		
		//traitement 
		
		/*
		 * evite ce cas :
		 * 0 0 0 		0 1 1
		 * 0 0 0	=>  0 1 1
		 * 0 0 0		0 1 1 
		 * qui est perdant
		 */
		
		if((tabpere[0][1] == 0 && tabcurrent[0][1] > 0 ) || (tabpere[0][1] == 0 && tabcurrent[0][1] > 0)) {
			N.setHeuristic(0);
			return;
		}
		/*
		 * favorise ce cas :
		 * 0 1 1 		0 1 1
		 * 0 1 1	=>  2 1 1
		 * 0 1 1		2 1 1 
		 * qui est gagnant
		 * 
		 * et, favorise ce cas :
		 * 0 0 0 		0 2 2
		 * 1 1 1	=>  1 1 1
		 * 1 1 1		1 1 1 
		 * qui est gagnant
		 */
		
		if((tabpere[0][1] > 0 && tabpere[1][0]  == 0 && tabcurrent[1][0] > 0) || (tabpere[1][0] > 0 && tabpere[0][1]  == 0 && tabcurrent[0][1] > 0)) {
			N.setHeuristic(1000);
			return;
		}
		
		// ..
		if(nbzeroligne(tabcurrent,0,largeur) != nbzerocolonne(tabcurrent,0,hauteur) && tabcurrent[1][1] > 0) {
			N.setHeuristic(1000);
			return;
		}
		// ..
		if(nbzeroligne(tabcurrent,0,largeur) == nbzerocolonne(tabcurrent,0,hauteur) && tabcurrent[1][1] > 0) {
			N.setHeuristic(0);
			return;
		}
		// ..
		if(nbzeroligne(tabpere,0,largeur) == nbzerocolonne(tabpere,0,hauteur) && tabpere[1][1] == 0 && tabcurrent[1][1] > 0) {
			N.setHeuristic(1000);
			return;
		}
		//obvious
		if(tabcurrent[0][0] > 0) {
			N.setHeuristic(0);
			return;
			
		}	
		// faut accepter la defaite
		if(tabpere[1][0] > 0 && tabpere[0][1] > 0) {
			N.setHeuristic(1000);
			return;
		}
		N.setHeuristic(N.pere().heuristic());
	}
}
