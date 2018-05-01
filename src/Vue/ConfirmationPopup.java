package Vue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class ConfirmationPopup extends StackPane {
	
	VBox vbox;
	HBox hbox;
	String str="Retourner au menu?";
	EventHandler<ActionEvent> ouiAction;
	EventHandler<ActionEvent> nonAction; 

	public ConfirmationPopup(EventHandler<ActionEvent> oui, EventHandler<ActionEvent> non, String css) {
		vbox = new VBox();
		vbox.getStyleClass().add("popup");
		vbox.getChildren().add(creer_label(str));

		vbox.setAlignment(Pos.CENTER);
		//vbox.setBackground(new Background(new BackgroundFill(Donnees.COULEUR_BG, new CornerRadii(30),Insets.EMPTY)));
		vbox.maxWidthProperty().bind(this.widthProperty().multiply(0.5));
		vbox.maxHeightProperty().bind(this.heightProperty().multiply(0.5));
		vbox.setAlignment(Pos.CENTER);
		this.setBackground(new Background(new BackgroundFill(new Color(0,0,0,0.5), CornerRadii.EMPTY,Insets.EMPTY)));
		this.getChildren().add(vbox);
		this.getStylesheets().add(css);
	
		ouiAction = oui;
		nonAction = non;
		hbox = creer_hbox();
		vbox.getChildren().add(hbox);
	}
	
	private Label creer_label(String str) {
		Label l = new Label(str);
		l.setFont(Donnees.FONT_TEXT);
		l.maxHeight(100);
		l.setAlignment(Pos.CENTER);
		return l;
	}
	
	private HBox creer_hbox() {
		HBox h = new HBox();
		h.setAlignment(Pos.CENTER);
		h.getChildren().add(creer_bouton_oui());
		h.getChildren().add(creer_bouton_non());
		return h;
	}
	
	private Button creer_bouton_oui() {
		Button tmp = new Button("Oui");
		tmp.setFont(Donnees.FONT_TEXT);
		this.getChildren().add(tmp);
		tmp.setOnAction(ouiAction);
		return tmp; 
	}
	
	private Button creer_bouton_non() {
		Button tmp = new Button("Non");
		tmp.setFont(Donnees.FONT_TEXT);
		this.getChildren().add(tmp);
		tmp.setOnAction(nonAction);
		return tmp; 
	}
	
	public void setOuiAction(EventHandler<ActionEvent> oui) {
		Button b = (Button) hbox.getChildren().get(0);
		ouiAction = oui;
		b.setOnAction(oui);
	}
	public void setNonAction(EventHandler<ActionEvent> non) {
		Button b = (Button) hbox.getChildren().get(1);
		nonAction = non;
		b.setOnAction(non);
	}
	
}
