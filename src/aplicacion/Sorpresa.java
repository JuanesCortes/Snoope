package aplicacion;

import java.util.Random;

public abstract class Sorpresa extends Item {

	private static final long serialVersionUID = 1L;

	public Sorpresa() {

	}

	/**
	 * Genera una sorpresa aleatoria
	 * 
	 * @return sor es la sorpresa generada aleatoriamente
	 * @throws SnoopeExcepcion
	 */
	public static Item generarSorpresaAleatoria() throws SnoopeExcepcion {
		Random r = new Random();
		int val = r.nextInt(20);
		Item sor;
		if (val <= 5 && val > 0) {
			Random val1 = new Random();
			int v1 = val1.nextInt(600) + 200;
			int v2 = val1.nextInt(450) + 150;
			sor = new Division(v1, v2);
		} else if (val > 5 && val <= 10) {
			Random val1 = new Random();
			int v1 = val1.nextInt(600) + 200;
			int v2 = val1.nextInt(450) + 150;
			sor = new Lupa(v1, v2);

		} else if (val > 10 && val <= 15) {
			Random val1 = new Random();
			int v1 = val1.nextInt(600) + 200;
			int v2 = val1.nextInt(450) + 150;
			sor = new FlechaMasVelocidad(v1, v2);

		} else if (val > 15 && val <= 20) {
			Random val1 = new Random();
			int v1 = val1.nextInt(600) + 200;
			int v2 = val1.nextInt(450) + 150;
			sor = new FlechaMenosVelocidad(v1, v2);

		} else {
			Random val1 = new Random();
			int v1 = val1.nextInt(600) + 200;
			int v2 = val1.nextInt(450) + 150;
			sor = new Division(v1, v2);
		}
		return sor;
	}
}
