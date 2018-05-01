package Vue;

import Joueurs.Joueur;
import Joueurs.JoueurPhysique;
import Modele.Couple;
import Modele.Plateau;
import Vue.GameObject.CibleurGraphique;
import Vue.InterfaceGraphique.Appli_state;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class Moteur {
	public enum FSA_state {
		DEBUT_TOUR(0), CHOISIR_COUP(1), CASE_CIBLE(2), FIN_COUP(3), FIN_TOUR(4), FIN_PARTIE(5), CHOIX_FIN(6);

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
	private int delayDebutIA = (int) (250 / vitesseDelais);
	private int delayEntreTour = (int) (250 / vitesseDelais);
	private int delayCiblage = (int) (500 / vitesseDelais);
	private Joueur[] joueurs;
	private Color[] couleursJoueurs;
	private CibleurGraphique cibleur;
	private Couple coupIA;

	public Moteur(PanePrincipal pp) {
		this.panePrincipal = pp;
		etat_courant = FSA_state.FIN_TOUR;
		i_joueur_courant = 1;
		joueurs = new Joueur[2];
		joueurs[0] = new JoueurPhysique();
		joueurs[1] = new JoueurPhysique();
		couleursJoueurs = new Color[2];
		couleursJoueurs[0] = new Color(100f / 255f, 160f / 255f, 50f / 255f, 1);
		couleursJoueurs[1] = new Color(230f / 255f, 60f / 255f, 60f / 255f, 1);
		cibleur = new CibleurGraphique(panePrincipal.gameView.plateauGraphique(), 0, 0);
		panePrincipal.gameView.gameObjects.add(cibleur);
		panePrincipal.enteteView.label.setText(nom_joueur(i_joueur_courant));
		panePrincipal.enteteView.label.setTextFill(couleur_courante());
	}

	public void remplacerJoueur(Joueur j1, Joueur j2) {
		joueurs[0] = j1;
		joueurs[1] = j2;
	}

	public void update() {
		switch (etat_courant) {
		case DEBUT_TOUR:
			if (!joueurs[i_joueur_courant].estIA() || timeA + delayDebutIA < System.currentTimeMillis()) {
				etat_courant = FSA_state.CHOISIR_COUP;
				cibleur.setCouleur(couleur_courante());
			}
			if (!joueurs[i_joueur_courant].estIA()) {
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
			if (timeA + delayCiblage < System.currentTimeMillis()) {
				cibleur.setCaseCible(new Couple(-1, -1));
				panePrincipal.plateau.manger(coupIA);
				etat_courant = FSA_state.FIN_COUP;
			}
			break;

		case FIN_COUP:
			timeA = System.currentTimeMillis();
			etat_courant = FSA_state.FIN_TOUR;
			break;
		case FIN_TOUR:
			if (timeA + delayEntreTour < System.currentTimeMillis()) {
				if (!panePrincipal.plateau.estMangeable(panePrincipal.plateau.getCasePoison()))
					etat_courant = FSA_state.FIN_PARTIE;
				else
					etat_courant = FSA_state.DEBUT_TOUR;
				timeA = System.currentTimeMillis();
				joueur_suivant();
			}
			break;
		case FIN_PARTIE:
			ConfirmationPopup c = new ConfirmationPopup(nom_joueur_courant()+" gagne!","Rejouer","Menu",null, null, InterfaceGraphique.m.get_css());
			EventHandler<ActionEvent> oui = new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					InterfaceGraphique.etat=Appli_state.MENU;
				}
			};
			EventHandler<ActionEvent> non = new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					InterfaceGraphique.etat=Appli_state.MENU;
					InterfaceGraphique.m = new PaneMenu(panePrincipal.getWidth(),panePrincipal.getHeight(), InterfaceGraphique.m.get_css());
					InterfaceGraphique.primaryStage.setScene(new Scene(InterfaceGraphique.m));
				}
			};
			c.setOuiAction(oui);
			c.setNonAction(non);
			panePrincipal.getChildren().add(c);
			etat_courant=FSA_state.CHOIX_FIN;
			break;
		case CHOIX_FIN:
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

	public String nom_joueur_courant() {
		return nom_joueur(i_joueur_courant);
	}

	private void joueur_suivant() {
		i_joueur_courant = 1 - i_joueur_courant;
		panePrincipal.enteteView.label.setText(nom_joueur(i_joueur_courant));
		panePrincipal.enteteView.label.setTextFill(couleur_courante());
	}

	public void undo() {
		if (!joueurs[i_joueur_courant].estIA()) {
			if (joueurs[1 - i_joueur_courant].estIA()) {
				panePrincipal.plateau.undo();
				panePrincipal.plateau.undo();
			} else {
				if (panePrincipal.plateau.undo())
					joueur_suivant();
			}
			etat_courant = FSA_state.CHOISIR_COUP;
		}
	}

	public void redo() {
		if (!joueurs[i_joueur_courant].estIA()) {
			if (joueurs[1 - i_joueur_courant].estIA()) {
				panePrincipal.plateau.redo();
				panePrincipal.plateau.redo();
			} else {
				if (panePrincipal.plateau.redo())
					joueur_suivant();
			}
			etat_courant = FSA_state.CHOISIR_COUP;
		}
	}

	public void setVitesseDelais(float v) {
		vitesseDelais = v;
	}

}
