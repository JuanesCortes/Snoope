package aplicacion;

import graficos.Sprite;

public class Lupa extends Sorpresa {

	private static final long serialVersionUID = 1L;

	public Lupa(int x, int y) throws SnoopeExcepcion {
		if (x < 200 || x > 800 || y < 150 || y > 600)
			throw new SnoopeExcepcion(SnoopeExcepcion.ITEM_POS);
		this.x = x;
		this.y = y;
		this.dItem = 400;
		this.isVivo = true;
		this.sprite = Sprite.CSORPRESA;
	}

}
