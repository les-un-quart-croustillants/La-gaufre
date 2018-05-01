package Joueurs;

import java.util.LinkedList;

import Modele.Plateau;

public class Main {
	
	public static void main(String[] args) {
		boolean TEST_TOTAB_TOINT = false;
		boolean TEST_LESFILS = true;
		
		if(TEST_TOTAB_TOINT) { // tu donnes un tableau en entrée, le tableau est traduit en INT, puis retraduit depuis l'int et enfin affiché
			
			int [][] tableau = {{0,0},
								{0,0}};
			int N = 2;
			int M = 2;
			int res = TabConverter.ToInt(new Plateau(N,M,tableau));
			System.out.println("res = "+res);

			Plateau P = TabConverter.ToTab(res);
			TabConverter.afficher_tab(P);
			
		}else if(TEST_LESFILS) { 
			// tu donnes au tableau le tableau à tester, il affiche les fils contenu par le noeud contenant la valeur INT representée par ce tableau

			int [][] tableau = {{0,0},
								{0,1}};
			
			int N = 2;
			int M = 2;
			
			int test = TabConverter.ToInt(new Plateau(N,M,tableau));
			LinkedList<Noeud> lesfils = new LinkedList<Noeud>();	
			Plateau resres = TabConverter.ToTab(test);
			
			System.out.println("---------------------------------------");
			System.out.println("---------------------------------------");
			System.out.println("Le tableau initial, calculé par ToTab : ");
			TabConverter.afficher_tab(resres);	
			System.out.println("---------------------------------------");
			System.out.println("---------------------------------------");
			
			Noeud ntest = new Noeud(test);
			TabConverter.FilsNoeud(ntest);
			lesfils = ntest.fils();
			for(int i = 0; i < lesfils.size();i++) {
				// ENLEVER COMMENTAIRES SI ON VEUX VOIR A DEUX PAS (hyper long, faire petits exemples)
				// decommenter les deux bloc suivants
				/*			
				LinkedList<Noeud> lespetitsfils = new LinkedList<Noeud>();
				Noeud current;
				current = lesfils.get(i);
				TabConverter.FilsNoeud(current);
				lespetitsfils = current.fils();
				*/
				
				System.out.println("tableau du fils numero " + i);
				TabConverter.afficher_tab(TabConverter.ToTab(lesfils.get(i).valeur()));
				
				/*
				for(int j = 0; j < lespetitsfils.size();j++) {
					System.out.println("Les tableaux des petits fils de  " + i);
					TabConverter.afficher_tab(TabConverter.ToTab(lespetitsfils.get(j).valeur()));
					
				}*/
			}
		}
		
	}
}
