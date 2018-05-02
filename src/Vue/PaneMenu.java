package Vue;

import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.event.*;
import javafx.scene.image.*;

import Joueurs.*;

public class PaneMenu extends VBox {
	private MenuAction choix = MenuAction.NOTHING;
	private VBox choix_general, choix_mode, regles;
	private Button chgStyle;
	private PaneMenu menu = this; // Pour les handlers
	public Joueur joueur1, joueur2;
	public int width = 8, height = 5;
	
	public enum MenuAction {
		NOTHING(0), NEW_GAME(1), QUIT(2);
		
		public int value;
    
		MenuAction(int value) {
			this.value = value;
		}
	}
	
	public PaneMenu(double width, double height) {
		super();
		creer_groups();
		this.setAlignment(Pos.CENTER);
		this.getStylesheets().add("lightmode.css");
		creer_bouton_nouvelle_partie();
		creer_bouton_regles();
		creer_bouton_quitter();
		creer_mode();
		creer_regles();
		creer_bouton_style();
		this.setMinSize(width, height);
	}
	
	public PaneMenu(double width, double height, String css) {
		super();
		creer_groups();
		this.setAlignment(Pos.CENTER);
		this.getStylesheets().add(css);
		creer_bouton_nouvelle_partie();
		creer_bouton_regles();
		creer_bouton_quitter();
		creer_mode();
		creer_regles();
		creer_bouton_style();
		this.setMinSize(width, height);
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
				menu.getChildren().remove(chgStyle);
				menu.getChildren().add(choix_mode);
				menu.getChildren().add(chgStyle);
			}
		});
		return tmp; 
	}
	
	private void creer_groups() {
		choix_general = new VBox();
		choix_mode = new VBox();
		regles = new VBox();
		choix_general.setAlignment(Pos.CENTER);
		choix_mode.setAlignment(Pos.CENTER);
		regles.setAlignment(Pos.CENTER);
		this.getChildren().add(choix_general);
	}
	
	private void creer_bouton_regles() {
		Button tmp = new Button("Règles");
		tmp.setFont(Donnees.FONT_TEXT);
		choix_general.getChildren().add(tmp);
		tmp.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				menu.getChildren().remove(choix_general);
				menu.getChildren().remove(chgStyle);
				menu.getChildren().add(regles);
			}
		});
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
	
	private void creer_regles() {
		Label title = new Label("Règles");
		Label line1 = new Label("Deux joueurs mangent tour");
		Label line2 = new Label("à tour un morceau d'une gaufre");
		Label line3 = new Label("dont le carreau haut-gauche");
		Label line4 = new Label("est empoisonné.");
		Label line5 = new Label("Le joueur qui mange ce carreau");
		Label line6 = new Label("perd la partie.");
		Button back = new Button("Retour");
		
		title.setFont(Donnees.FONT_PLAY);
		line1.setFont(Donnees.FONT_TEXT);
		line2.setFont(Donnees.FONT_TEXT);
		line3.setFont(Donnees.FONT_TEXT);
		line4.setFont(Donnees.FONT_TEXT);
		line5.setFont(Donnees.FONT_TEXT);
		line6.setFont(Donnees.FONT_TEXT);
		back.setFont(Donnees.FONT_TEXT);
		
		regles.getChildren().addAll(title, line1, line2, line3, line4, line5, line6, new Label(" "), new Label(" "), back);
		
		back.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				menu.getChildren().remove(regles);
				menu.getChildren().add(choix_general);
				menu.getChildren().add(chgStyle);
			}
		});
	}
	
	private void creer_mode() {
		HBox boxj1 = new HBox();
		HBox boxj2 = new HBox();
		HBox boxwidth = new HBox();
		VBox boxheight = new VBox();
		
		boxj1.setAlignment(Pos.CENTER);
		boxj2.setAlignment(Pos.CENTER);
		
		Label lblj1 = new Label("Joueur 1 : ");
		Label lblj2 = new Label("Joueur 2 : ");
		
		Label lblwidth = new Label("Largeur");
		Label lblheight = new Label("Hauteur");
		
		Label lblwidthVal = new Label(Integer.toString(width));
		Label lblheightVal = new Label(Integer.toString(height));
		
		Button j1 = new Button("Humain");
		Button j2 = new Button("Humain");
		
		Button difficultej1 = new Button("Facile");
		Button difficultej2 = new Button("Facile");
		
		Button accept = new Button("Jouer !");
		Button back = new Button("Retour");
		
		Button pluswidth = new Button("+");
		Button minuswidth = new Button("-");
		
		Button plusheight = new Button("+");
		Button minusheight = new Button("-");
		
		lblwidth.setFont(Donnees.FONT_TEXT);
		lblheight.setFont(Donnees.FONT_TEXT);
		
		lblwidthVal.setFont(Donnees.FONT_TEXT);
		lblheightVal.setFont(Donnees.FONT_TEXT);
		
		pluswidth.setFont(Donnees.FONT_TEXT);
		minuswidth.setFont(Donnees.FONT_TEXT);
		
		plusheight.setFont(Donnees.FONT_TEXT);
		minusheight.setFont(Donnees.FONT_TEXT);
		
		accept.setFont(Donnees.FONT_PLAY);
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
		
		boxheight.setAlignment(Pos.CENTER);
		boxheight.getChildren().add(plusheight);
		boxheight.getChildren().add(lblheightVal);
		boxheight.getChildren().add(minusheight);
		
		boxwidth.setAlignment(Pos.CENTER);
		boxwidth.getChildren().add(lblwidth);
		boxwidth.getChildren().add(minuswidth);
		boxwidth.getChildren().add(lblwidthVal);
		boxwidth.getChildren().add(pluswidth);
		boxwidth.getChildren().add(new Label("\t\t\t"));
		boxwidth.getChildren().add(lblheight);
		boxwidth.getChildren().add(boxheight);
		
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
				menu.getChildren().remove(chgStyle);
				menu.getChildren().add(choix_general);
				menu.getChildren().add(chgStyle);
			}
		});
		
		accept.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				// On alloue le joueur1
				if(j1.getText() == "IA") {
					if(difficultej1.getText() == "Facile")
						joueur1 = new JoueurIA(Joueur.Difficulte.FACILE);
					else if(difficultej1.getText() == "Moyen")
						joueur1 = new JoueurIA(Joueur.Difficulte.MOYEN);
					else if(difficultej1.getText() == "Difficile")
						joueur1 = new JoueurIA(Joueur.Difficulte.DIFFICILE);
				} else {
					joueur1 = new JoueurPhysique();
				}
				// On alloue le joueur2
				if(j2.getText() == "IA") {
					if(difficultej2.getText() == "Facile")
						joueur2 = new JoueurIA(Joueur.Difficulte.FACILE);
					else if(difficultej2.getText() == "Moyen")
						joueur2 = new JoueurIA(Joueur.Difficulte.MOYEN);
					else if(difficultej2.getText() == "Difficile")
						joueur2 = new JoueurIA(Joueur.Difficulte.DIFFICILE);
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
		  
		pluswidth.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if(width < 14) {
					width += 1;
					lblwidthVal.setText(Integer.toString(width));
				}
			}
		});
		
		plusheight.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if(height < 14) {
					height += 1;
					lblheightVal.setText(Integer.toString(height));
				}
			}
		});
		
		minuswidth.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if(width > 2) {
					width -= 1;
					lblwidthVal.setText(Integer.toString(width));
				}
			}
		});
		
		minusheight.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if(height > 2) {
					height -= 1;
					lblheightVal.setText(Integer.toString(height));
				}
			}
		});
		
		choix_mode.getChildren().add(boxj1);
		choix_mode.getChildren().add(boxj2);
		choix_mode.getChildren().add(boxwidth);
		choix_mode.getChildren().add(accept);
		choix_mode.getChildren().add(back);
	}
	
	private void creer_bouton_style() {
		chgStyle = new Button();
		ImageView nightImage = new ImageView(new Image("night.png"));
		ImageView lightImage = new ImageView(new Image("light.png"));
		if(this.getStylesheets().get(0) == "lightmode.css") {
			chgStyle.setGraphic(nightImage);	
		} else {
			chgStyle.setGraphic(lightImage); 
		}
		this.getChildren().add(chgStyle); 
		
		chgStyle.setOnAction(new EventHandler<ActionEvent>() {
			@Override  
			public void handle(ActionEvent e) {
				if(chgStyle.getGraphic() == nightImage) {
					chgStyle.setGraphic(lightImage);
					menu.getStylesheets().remove("lightmode.css");
					menu.getStylesheets().add("nightmode.css");
				} else if(chgStyle.getGraphic() == lightImage) {
					chgStyle.setGraphic(nightImage);
					menu.getStylesheets().remove("nightmode.css");
					menu.getStylesheets().add("lightmode.css");
				}
			}
		});
	}
	
	public String get_css() {
		return this.getStylesheets().get(0);
	}
	
	public MenuAction Choix() {
		return choix;
	}
}