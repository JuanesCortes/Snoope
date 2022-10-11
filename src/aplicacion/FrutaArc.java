package aplicacion;

import graficos.Sprite;

public class FrutaArc extends Alimento {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor de la clase FrutaArc
	 * 
	 * @param x posicion x donde se encuentra la fruta Arcoiris
	 * @param y posicion y donde se encuentra la fruta Arcoiris
	 * @throws SnoopeExcepcion
	 */
	public FrutaArc(int x, int y) throws SnoopeExcepcion {
		if (x < 200 || x > 800 || y < 150 || y > 600)
			throw new SnoopeExcepcion(SnoopeExcepcion.ITEM_POS);
		this.x = x;
		this.y = y;
		this.dItem = 200;
		this.isVivo = true;
		this.sprite = Sprite.FRUTAARC;
	}

}
