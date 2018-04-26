import java.util.List;

public abstract class Modele {
	List<Vue> observers;

	void notifief() {
		for(Vue o : observers) {
			o.maj(this);
		}
	}

	void ajouter_observateur(Vue o) {
		observers.add(o);
	}
}
