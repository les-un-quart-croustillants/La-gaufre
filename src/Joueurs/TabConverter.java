package Joueurs;

import Modele.Plateau;
import java.util.LinkedList;
import java.lang.Integer;

public class TabConverter {
	    /**
	     * afficher_tab : affiche le tableau
	     *  @param P : la plateau contenant le tableau a afficher
	     *  
	     */
		public static void afficher_tab(Plateau P) {
			int [][] tab = P.getTab();
			int N = P.hauteur();
			int M = P.largeur();
			for(int p = 0; p < N; p ++) {
				for(int po = 0; po < M; po ++) {
					System.out.print(tab[p][po] + " , ");
				}
				System.out.println(" ");
			}
		}
	    /**
	     * afficher_tab : ajoute 1 ou 0 à la representation binaire de l'int donne
	     *  @param res : l'int à modifier
	     *  @param val :  0 ou 1
	     *  @return l'entier modifié
	     */
		public static int addbin(int res, int val) {
			if(val == 1) {
				res = res << 1;
				res++;
				//System.out.println("add 1");
			}else {
				res = res << 1;
				//System.out.println("add 0");
			}
			return res;
		}
		
	    /**
	     * ToInt : traduit un configuration de tableau en Int via le vecteur de bit de partie mangee ( O(N+M) )
	     *  @param P : le plateau contenant le tableau a ToInter
	     *  @return : L'integer correspondant au vecteur de bit du tableau
	     */
		
		public static int ToInt(Plateau P) {
			int[][] tableau = P.getTab();
			int M = P.largeur();
			int N = P.hauteur();
			
			boolean pass = true;
			int curr = 0;
			int res = 0;
			if(tableau[N-1][M-1] == 0) return 0; // cas trivial
			for(int i = 0; i < M && pass; i++) { // traitement de la premiere ligne
				if(tableau[N-1][i] == 0) { 
					res = addbin(res, 0);
				}
				else { // sauvegarde de la position du premier 1 de la ligne
					curr = i;
					pass = false;
				}
			}
			
			int i = N-1; // calcul de "l'escalier"
			boolean doubles = true;
			boolean pasde1 = true;
			while( i >= 0 ) {
				doubles = true;
				pasde1 = true;
				for(int j = curr; j < M && doubles ; j++) { // a partir du premier 1 de la ligne precedente
					if(j != M-1 && tableau[i][j] == 0 && doubles) {
						res = addbin(res, 0);
					}else if(doubles && tableau[i][j] > 0) { // un 1 : ligne suivante into add 1
						doubles = false;
						pasde1 = false;
						res = addbin(res, 1);
						curr = j;
						i --;
					}
				}
				if(pasde1) { // aucun 1 dans la ligne : break
					i = -1;
				}
			}
			
			doubles = false;
			for(int k = N-1; k >= 0; k--) { // calcul de la paroie droite + cas batard
				if(k != N-1 && (tableau[k][M-1] == 0 && tableau[k+1][M-1] != 0)) {
					res=addbin(res, 0);
					doubles = true;
				}
				if(doubles)
					res=addbin(res,1);
			}
			
			for(int k = 0; k < M; k++) { // on ajoute les 0 manquants sur la ligne 0
				if(tableau[0][k] != 0) {
					res = addbin(res,0);
				}
			}
			return res;
	    }
		
		
	    /**
	     *  Honnetement, je sais pas pourquoi la partie en haut a gauche du tableau est initialisée à 0, mais ca marche mais     
	     *  ToTab : Traduit un int en une configuration du tableau ( O(n²) )
	     *  @param base : l'int à traduire
	     *  @param M :  largeur M 
	     *  @param N :  hauteur N
	     *  @return : un plateau contenant le tableau créé, et sa taille
	     */
		
		public static Plateau ToTab(int base,int M,int N) {
			
			int [][] res = new int[N][M];
			LinkedList<Integer> binary = new LinkedList<>();
			int curr;
			int temp = 0;
			boolean pass = true ; // represente le faire de ne rencontrer qu'un un par ligne
			
			binary = intToBinary(base); // representation binaire de l'int
			int nbajout = N+M-binary.size();
			
			for(int i = 0; i< nbajout ;i++) { // on ajoute les bits non significatifs du INT qui nous interessent
				binary.addFirst(0);
			}
			int i = N-1; // on remplit depuis la derniere ligne 
			temp = 0;
			//System.out.println(binary);
			while( i >= 0 ){ // on recalcule le tableau a partir du vecteur
				pass = true;
				for(int j = temp ; j < M && pass; j++) { 
					if((curr = binary.pollFirst()) == 0 && j == M-1) {// on recupere le bit à traite0
						return new Plateau(N,M,res);
					}
					if(curr == 1 && pass) { // la ligne entiere est à remplir de 1, on passe a la suivante
						//System.out.println(i+" ; "+j);
						pass = false;
						temp = j;
						while(j < M ) { // remplissage
							res[i][j] = 1;
							j++;
						}
						i--; // ligne suivante
					}
					else { // on ajoute des 0 au cas par cas
						res[i][j] = 0;

					}
				}
				if(pass) // au cas il n'y ait aucun 1 dans la ligne
					i--;
			}
			
			return new Plateau(N,M,res);
		}
	    /**
	     * IntToBinary : traduit un int en une linkedlist
	     *  @param base : l'int à traduire
	     *  @return : une linked list contenant le binaire de l'int donne
	     */
		public static LinkedList<Integer> intToBinary(int base)
		{
		    LinkedList<Integer> res = new LinkedList<>();
		    while (base > 0)
		    {
		        res.addFirst((base % 2 ) == 0 ? 0 : 1);
		        base = base / 2;
		    }
		    return res;
		}

}
