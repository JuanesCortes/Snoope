package aplicacion;

import graficos.Sprite;

public class Dulce extends Alimento {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor de la clase Dulce
	 * 
	 * @param x posicion x donde se encuentra el dulce
	 * @param y posicion y donde se encuentra el dulce
	 * @throws SnoopeExcepcion
	 */
	public Dulce(int x, int y) throws SnoopeExcepcion {
		if (x < 200 || x > 800 || y < 150 || y > 600)
			throw new SnoopeExcepcion(SnoopeExcepcion.ITEM_POS);
		this.x = x;
		this.y = y;
		this.dItem = 400;
		this.isVivo = true;
		this.sprite = Sprite.DUlCE;
	}

}
