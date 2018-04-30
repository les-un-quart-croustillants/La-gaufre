package Vue;

import Joueurs.Joueur;
import Joueurs.JoueurIA;
import Joueurs.JoueurPhysique;
import Modele.Couple;
import Modele.Plateau;
import Vue.GameObject.CibleurGraphique;
import javafx.scene.paint.Color;

public class Moteur {
	public enum FSA_state {
		DEBUT_TOUR(0), CHOISIR_COUP(1), CASE_CIBLE(2), FIN_COUP(3), FIN_TOUR(4);

		public int value;

		FSA_state(int value) {
			this.value = value;
		}
	}

	public Plateau plateau;
	public PanePrincipal panePrincipal;
	
	private FSA_state etat_courant;
	private int i_joueur_courant;
	private long timeA;
	private float vitesseDelais = 1f;
	private int delayDebutIA=(int) (250/vitesseDelais);
	private int delayEntreTour=(int) (250/vitesseDelais);
	private int delayCiblage=(int) (500/vitesseDelais);
	private Joueur[] joueurs;
	private Color[] couleursJoueurs;
	private CibleurGraphique cibleur;
	private Couple coupIA;

	public Moteur(PanePrincipal pp) {
		this.panePrincipal = pp;
		etat_courant = FSA_state.DEBUT_TOUR;
		joueurs = new Joueur[2];
		joueurs[0] = new JoueurPhysique();
		//joueurs[0] = new JoueurIA(panePrincipal.plateau, Joueur.Difficulte.FACILE);
		joueurs[1] = new JoueurIA(panePrincipal.plateau, Joueur.Difficulte.FACILE);
		//joueurs[1] = new JoueurPhysique();
		couleursJoueurs = new Color[2];
		couleursJoueurs[0] = new Color(100f/255f,160f/255f,50f/255f,1);
		couleursJoueurs[1] = new Color(230f/255f,60f/255f,60f/255f,1);
		cibleur = new CibleurGraphique(panePrincipal.gameView.plateauGraphique(), 0, 0);
		panePrincipal.gameView.gameObjects.add(cibleur);
		panePrincipal.enteteView.label.setText(nom_joueur(i_joueur_courant));
		panePrincipal.enteteView.label.setTextFill(couleur_courante());
	}
	
	public void update() {
		switch (etat_courant) {
		case DEBUT_TOUR:
			if(!joueurs[i_joueur_courant].estIA() || timeA+delayDebutIA<System.currentTimeMillis()) {
				etat_courant = FSA_state.CHOISIR_COUP;
				cibleur.setCouleur(couleur_courante());
			}
			if(!joueurs[i_joueur_courant].estIA()) {
				panePrincipal.gameView.cibleurGraphique().setCouleur(couleur_courante());
			}
			break;
		case CHOISIR_COUP:
			if (joueurs[i_joueur_courant].estIA()
					&& panePrincipal.plateau.estMangeable(panePrincipal.plateau.getCasePoison())) {
						coupIA = joueurs[i_joueur_courant].prochainCoup(panePrincipal.plateau);
						cibleur.setCaseCible(coupIA);
						etat_courant = FSA_state.CASE_CIBLE;
						timeA = System.currentTimeMillis();
			}
			break;
		case CASE_CIBLE:
			if(timeA+delayCiblage<System.currentTimeMillis()) {
				cibleur.setCaseCible(new Couple(-1,-1));
				panePrincipal.plateau.manger(coupIA);
				etat_courant = FSA_state.FIN_COUP;
			}
			break;
			
		case FIN_COUP:
			timeA = System.currentTimeMillis();
			etat_courant = FSA_state.FIN_TOUR;
			break;
		case FIN_TOUR:
			if(timeA+delayEntreTour<System.currentTimeMillis()) {
				etat_courant = FSA_state.DEBUT_TOUR;
				timeA = System.currentTimeMillis();
				joueur_suivant();
			}
			break;
		}
	}
	
	public void setEtatCourant(FSA_state s) {
		etat_courant = s;
	}

	public Joueur joueur_courant() {
		return joueurs[i_joueur_courant];
	}

	public FSA_state etat_courant() {
		return etat_courant;
	}

	public Color couleur_courante() {
		return couleursJoueurs[i_joueur_courant];
	}
	
	private String nom_joueur(int n) {
		if (joueurs[0].estIA() && joueurs[1].estIA()) {
			return "Ordinateur " + (n + 1);
		} else if (!joueurs[0].estIA() && !joueurs[1].estIA()) {
			return "Joueur " + (n + 1);
		} else {
			if (joueurs[n].estIA())
				return "Ordinateur";
			else
				return "Joueur";
		}
	}
	
	private void joueur_suivant() {
		i_joueur_courant = 1-i_joueur_courant;
		panePrincipal.enteteView.label.setText(nom_joueur(i_joueur_courant));
		panePrincipal.enteteView.label.setTextFill(couleur_courante());
	}
	
	public void undo() {
		if(!joueurs[i_joueur_courant].estIA()) {
			if(joueurs[1-i_joueur_courant].estIA()) {
				panePrincipal.plateau.undo();
				panePrincipal.plateau.undo();
			}
			else {
				panePrincipal.plateau.undo();
				joueur_suivant();
			}
		}
	}
	
	public void redo() {
		if(!joueurs[i_joueur_courant].estIA()) {
			if(joueurs[1-i_joueur_courant].estIA()) {
				panePrincipal.plateau.redo();
				panePrincipal.plateau.redo();
			}
			else {
				panePrincipal.plateau.redo();
				joueur_suivant();
			}
		}
	}
	
	public void setVitesseDelais(float v) {
		vitesseDelais = v;
	}

}