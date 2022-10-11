package aplicacion;

import graficos.Sprite;

public class Veneno extends Alimento {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor de la clase Veneno
	 * 
	 * @param x posicion x donde se encuentra el veneno
	 * @param y posicion y donde se encuentra el veneno
	 * @throws SnoopeExcepcion
	 */
	public Veneno(int x, int y) throws SnoopeExcepcion {
		if (x < 200 || x > 800 || y < 150 || y > 600)
			throw new SnoopeExcepcion(SnoopeExcepcion.ITEM_POS);
		this.x = x;
		this.y = y;
		this.dItem = 400;
		this.isVivo = true;
		this.sprite = Sprite.VENENO;
	}

}
