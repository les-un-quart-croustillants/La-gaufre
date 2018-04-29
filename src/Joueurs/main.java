package Joueurs;

import Modele.Plateau;

public class main {
		public static void main(String[] args) {
			int test = 355;
			Plateau resres = TabConverter.ToTab(test);
			TabConverter.afficher_tab(resres);
			
			Noeud ntest = new Noeud(test);
			TabConverter.FilsNoeud(ntest);
		}
}
