package Vue;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import Modele.Plateau;

public class PanePrincipal extends GridPane {

	Cadre gameView;
	Cadre enteteView;
	Cadre infoView;

	private class TestView extends Cadre {// exemple
		String s;

		TestView(int w, int h, String s) {
			super(w, h);
			this.s = s;
		}

		void Draw() {
			gc.clearRect(0, 0, this.getWidth(), this.getHeight());
			gc.setStroke(Color.RED);
			gc.strokeRect(0, 0, this.getWidth(), this.getHeight());
			gc.fillText(s, 10, 20);
		}
	}

	public PanePrincipal(){
		super();
		
		gameView = new VuePlateau(800,600, new Plateau());
		enteteView= new TestView(200,50,"coucou");
		infoView = new TestView(100,50,"On en a gros!");

		GridPane.setConstraints(enteteView,0,0);
		GridPane.setConstraints(infoView,0,1);
		GridPane.setConstraints(gameView,0,2);
		this.getChildren().addAll(enteteView,infoView,gameView);
		
		RowConstraints row1 = new RowConstraints();
	    row1.setPercentHeight(10);
	    RowConstraints row2 = new RowConstraints();
	    row2.setPercentHeight(10);
	    RowConstraints row3 = new RowConstraints();
	    row3.setPercentHeight(80);
		this.getRowConstraints().addAll(row1,row2,row3);
		
		
		ColumnConstraints column1 = new ColumnConstraints();
	    column1.setPercentWidth(100);
		this.getColumnConstraints().addAll(column1);
		
		/*this.setCenter(gameView);
		this.setBottom(bottomView);
		this.setRight(rightView);*/
		
	}

	void Draw() {
		
		gameView.Draw();
		enteteView.Draw();
		infoView.Draw();
		// rightView.Draw();
	}

}
