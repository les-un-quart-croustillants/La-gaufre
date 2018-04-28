package Vue.GameObject;

public class AnimationGraphique extends GameObject{
	private long tempsDebut;
	private long duree;
	private float avancement;//avancement de l'animation (de 0 à 1)
		
	public AnimationGraphique(long duree){
		super(0,0);
		tempsDebut = System.currentTimeMillis();
		this.duree = duree;
		avancement = 0;
	}
	
	@Override
	public void update(){
		avancement = (System.currentTimeMillis()-tempsDebut)/duree;
		if(avancement>=1) {
			this.detruire();
		}
	}
	
}
