package Vue.GameObject;

import Vue.Donnees;
import javafx.scene.canvas.GraphicsContext;

public class PoisonGaphique extends GameObject{

	PlateauGraphique pg;
	long delay = 300;
	float poisonEchelle = 0.5f;
	
	float alpha;
	
	private long time;

	
	public PoisonGaphique(PlateauGraphique pg) {
		this.pg = pg;
		time = System.currentTimeMillis();
	}

	@Override
	public void draw(GraphicsContext gc) {
		gc.setGlobalAlpha(alpha);
		gc.drawImage(Donnees.IMG_POISON, position.x + pg.tailleCase()*poisonEchelle/2,  position.y + pg.tailleCase()*poisonEchelle/2, pg.tailleCase()*poisonEchelle, pg.tailleCase()*poisonEchelle);
		gc.setGlobalAlpha(1);
	}
		
	@Override
	public void update() {
		position.x = pg.position().x;
		position.y = pg.position().y;
		alpha = (float) ((1+Math.cos((System.currentTimeMillis()-time)*5/1000.0))/2);
	}
	
}
