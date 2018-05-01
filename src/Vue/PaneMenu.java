package Vue;

import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.event.*;
import Joueurs.*;

public class PaneMenu extends VBox {
	private MenuAction choix = MenuAction.NOTHING;
	private VBox choix_general, choix_mode, choix_difficulte;
	private PanePrincipal gp;
	private PaneMenu menu = this; // Pour les handlers
	public Joueur joueur1, joueur2;
	
	public enum MenuAction {
		NOTHING(0), NEW_GAME(1), LOAD(2), QUIT(3);
		
		public int value;

		MenuAction(int value) {
			this.value = value;
		}
	}
	
	PaneMenu(PanePrincipal gp) {
		super();
		this.gp = gp;
		creer_groups();
		this.setAlignment(Pos.CENTER);
		this.getStylesheets().add("menu.css");
		creer_bouton_nouvelle_partie();
		creer_bouton_charger();
		creer_bouton_quitter();
		creer_mode();
		this.setMinSize(gp.getWidth(), gp.getHeight());
	}
	
	
	private Button creer_bouton_nouvelle_partie() {
		Button tmp = new Button("Nouvelle Partie");
		tmp.setFont(Donnees.FONT_TEXT);
		choix_general.getChildren().add(tmp);
		tmp.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				//choix = MenuAction.NEW_GAME;
				menu.getChildren().remove(choix_general);
				menu.getChildren().add(choix_mode);
			}
		});
		return tmp; 
	}
	
	private void creer_groups() {
		choix_general = new VBox();
		choix_mode = new VBox();
		choix_difficulte = new VBox();
		choix_general.setAlignment(Pos.CENTER);
		choix_mode.setAlignment(Pos.CENTER);
		choix_difficulte.setAlignment(Pos.CENTER);
		this.getChildren().add(choix_general);
	}
	
	private void creer_bouton_charger() {
		Button tmp = new Button("Charger");
		tmp.setFont(Donnees.FONT_TEXT);
		choix_general.getChildren().add(tmp);
	}
	
	private void creer_bouton_quitter() {
		Button tmp = new Button("Quitter");
		tmp.setFont(Donnees.FONT_TEXT);
		choix_general.getChildren().add(tmp);
		tmp.setCancelButton(true);
		tmp.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				choix = MenuAction.QUIT;
			}
		});
	}
	
	private void creer_mode() {
		HBox boxj1 = new HBox();
		HBox boxj2 = new HBox();
		
		boxj1.setAlignment(Pos.CENTER);
		boxj2.setAlignment(Pos.CENTER);
		
		Label lblj1 = new Label("Joueur 1 : ");
		Label lblj2 = new Label("Joueur 2 : ");
		
		Button j1 = new Button("Humain");
		Button j2 = new Button("Humain");
		
		Button difficultej1 = new Button("Facile");
		Button difficultej2 = new Button("Facile");
		
		Button accept = new Button("Jouer !");
		Button back = new Button("Retour");
		
		accept.setFont(Donnees.FONT_TEXT);
		back.setFont(Donnees.FONT_TEXT);
		
		difficultej1.setFont(Donnees.FONT_TEXT);
		difficultej2.setFont(Donnees.FONT_TEXT);
		
		j1.setFont(Donnees.FONT_TEXT);
		j2.setFont(Donnees.FONT_TEXT);

		lblj1.setFont(Donnees.FONT_TEXT);
		lblj2.setFont(Donnees.FONT_TEXT);

		boxj1.getChildren().add(lblj1);
		boxj1.getChildren().add(j1);
		
		boxj2.getChildren().add(lblj2);
		boxj2.getChildren().add(j2);
		
		// Configure buttons
		j1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if(j1.getText() == "Humain") {
					j1.setText("IA");
					boxj1.getChildren().add(difficultej1);
				} else if(j1.getText() == "IA") {
					j1.setText("Humain");
					boxj1.getChildren().remove(difficultej1);
				}
			}
		});
		
		j2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if(j2.getText() == "Humain") {
					j2.setText("IA");
					boxj2.getChildren().add(difficultej2);
				} else if(j2.getText() == "IA") {
					j2.setText("Humain");
					boxj2.getChildren().remove(difficultej2);
				}
			}
		});
		
		back.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				menu.getChildren().remove(choix_mode);
				menu.getChildren().add(choix_general);
			}
		});
		
		accept.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				// On alloue le joueur1
				if(j1.getText() == "IA") {
					if(difficultej1.getText() == "Facile")
						joueur1 = new JoueurIA(gp.plateau, Joueur.Difficulte.FACILE);
					else if(difficultej1.getText() == "Moyen")
						joueur1 = new JoueurIA(gp.plateau, Joueur.Difficulte.MOYEN);
					else if(difficultej1.getText() == "Difficile")
						joueur1 = new JoueurIA(gp.plateau, Joueur.Difficulte.DIFFICILE);
				} else {
					joueur1 = new JoueurPhysique();
				}
				// On alloue le joueur2
				if(j2.getText() == "IA") {
					if(difficultej2.getText() == "Facile")
						joueur2 = new JoueurIA(gp.plateau, Joueur.Difficulte.FACILE);
					else if(difficultej2.getText() == "Moyen")
						joueur2 = new JoueurIA(gp.plateau, Joueur.Difficulte.MOYEN);
					else if(difficultej2.getText() == "Difficile")
						joueur2 = new JoueurIA(gp.plateau, Joueur.Difficulte.DIFFICILE);
				} else {
					joueur2 = new JoueurPhysique();
				}
				choix = MenuAction.NEW_GAME;
			}
		});
		
		difficultej1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if(difficultej1.getText() == "Facile") {
					difficultej1.setText("Moyen");
				} else if(difficultej1.getText() == "Moyen") {
					difficultej1.setText("Difficile");
				} else if(difficultej1.getText() == "Difficile") {
					difficultej1.setText("Facile");
				}
			}
		});
		
		difficultej2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if(difficultej2.getText() == "Facile") {
					difficultej2.setText("Moyen");
				} else if(difficultej2.getText() == "Moyen") {
					difficultej2.setText("Difficile");
				} else if(difficultej2.getText() == "Difficile") {
					difficultej2.setText("Facile");
				}
			}
		});
		
		choix_mode.getChildren().add(boxj1);
		choix_mode.getChildren().add(boxj2);
		choix_mode.getChildren().add(accept);
		choix_mode.getChildren().add(back);
	}
	
	public MenuAction Choix() {
		return choix;
	}
}