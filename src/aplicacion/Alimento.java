package aplicacion;

import java.util.Random;

public abstract class Alimento extends Item {

	private static final long serialVersionUID = 1L;

	public Alimento() {

	}

	/**
	 * Genrea un alimento aleatorio
	 * 
	 * @return alim es el alimento aleatorio generado
	 * @throws SnoopeExcepcion
	 */
	public static Item generarAlimentoAleatorio() throws SnoopeExcepcion {
		Random r = new Random();
		int val = r.nextInt(20);
		Item alim;
		if (val <= 5 && val > 0) {
			Random val1 = new Random();
			int v1 = val1.nextInt(600) + 200;
			int v2 = val1.nextInt(450) + 150;
			int c = val1.nextInt(20);
			String col;
			if (c <= 5 && c > 0) {
				col = "Morado";
			} else if (c > 5 && c <= 10) {
				col = "Cafe";
			} else if (c > 10 && c <= 15) {
				col = "Negra";
			} else if (c > 15 && c <= 20) {
				col = "Verde";
			} else {
				col = "Morado";
			}
			alim = new Fruta(v1, v2, col);
		} else if (val > 5 && val <= 10) {
			Random val1 = new Random();
			int v1 = val1.nextInt(600) + 200;
			int v2 = val1.nextInt(450) + 150;
			alim = new FrutaArc(v1, v2);

		} else if (val > 10 && val <= 15) {
			Random val1 = new Random();
			int v1 = val1.nextInt(600) + 200;
			int v2 = val1.nextInt(450) + 150;
			alim = new Veneno(v1, v2);

		} else if (val > 15 && val <= 20) {
			Random val1 = new Random();
			int v1 = val1.nextInt(600) + 200;
			int v2 = val1.nextInt(450) + 150;
			alim = new Dulce(v1, v2);

		} else {
			Random val1 = new Random();
			int v1 = val1.nextInt(600) + 200;
			int v2 = val1.nextInt(450) + 150;
			alim = new FrutaArc(v1, v2);
		}
		return alim;
	}
}
