package Vue;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InterfaceGraphique extends Application {
	PanePrincipal gp;
	
	public enum Appli_state {
		MENU(0), NEW_GAME(1), LOAD(2);
		
		public int value;
		
		Appli_state(int value) {
			this.value = value;
		}
	}
	
	final static float dt = 0.0166f; //temps entre 2 frames en s (60 fps)
	Appli_state etat = Appli_state.MENU;
	
	public static void creer(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		gp = new PanePrincipal();
		PaneMenu m = new PaneMenu(1000, 800);
        Scene menu = new Scene(m, 1000, 800);
        primaryStage.setScene(menu);  
		primaryStage.show();
		primaryStage.setMinHeight(400);
		primaryStage.setMinWidth(400);
		
		new AnimationTimer() {
			@Override
			public void handle(long currentNanoTime) {
				switch(etat) {
				case MENU:
					switch(m.Choix()) {
					case NEW_GAME:
						gp = new PanePrincipal(m.height, m.width);
						gp.moteur.remplacerJoueur(m.joueur1, m.joueur2);
						Scene partie = new Scene(gp, m.getWidth(), m.getHeight());
						primaryStage.setScene(partie);
						etat = Appli_state.NEW_GAME;
						return;
					case QUIT:
						// On ferme l'appli
						primaryStage.close();
						break;
					case LOAD:
						etat = Appli_state.LOAD;
						break;
					case NOTHING:
						// On ne fait rien
						break;
					}
					break;
				case NEW_GAME:
					// On joue la partie comme avant
					gp.Update();
					gp.Draw();
					break;
				case LOAD:
					// Code du chargement
					break;
				}
				
			}
		}.start();
	}
}
