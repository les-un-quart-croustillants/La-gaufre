package Vue;

import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.event.*;

public class PaneMenu extends VBox {
	private MenuAction choix = MenuAction.NOTHING;
	
	public enum MenuAction {
		NOTHING(0), NEW_GAME(1), LOAD(2), QUIT(3);
		
		public int value;

		MenuAction(int value) {
			this.value = value;
		}
	}
	
	PaneMenu() {
		super();
		this.setAlignment(Pos.CENTER);
		this.getStylesheets().add("menu.css");
		creer_bouton_nouvelle_partie();
		creer_bouton_charger();
		creer_bouton_quitter();
	}
	
	PaneMenu(double width, double height) {
		
		super();
		this.setAlignment(Pos.CENTER);
		this.getStylesheets().add("menu.css");
		creer_bouton_nouvelle_partie();
		creer_bouton_charger();
		creer_bouton_quitter();
		this.setMinSize(width, height);
	}
	
	private Button creer_bouton_nouvelle_partie() {
		Button tmp = new Button("Nouvelle Partie");
		tmp.setFont(Donnees.FONT_TEXT);
		this.getChildren().add(tmp);
		tmp.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				choix = MenuAction.NEW_GAME;
			}
		});
		return tmp; 
	}
	
	
	private Button creer_bouton_charger() {
		Button tmp = new Button("Charger");
		tmp.setFont(Donnees.FONT_TEXT);
		this.getChildren().add(tmp);
		return tmp;
	}
	
	private Button creer_bouton_quitter() {
		Button tmp = new Button("Quitter");
		tmp.setFont(Donnees.FONT_TEXT);
		this.getChildren().add(tmp);
		tmp.setCancelButton(true);
		tmp.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				choix = MenuAction.QUIT;
			}
		});
		return tmp;
	}
	
	public MenuAction Choix() {
		return choix;
	}
}