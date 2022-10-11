package aplicacion;

import graficos.Sprite;

public class Fruta extends Alimento {

	private static final long serialVersionUID = 1L;
	private String color;

	/**
	 * Constructor de la clase Fruta
	 * 
	 * @param x     posicion x en la que se encuentra la fruta
	 * @param y     posicion x en la que se encuentra la fruta
	 * @param color color de la fruta
	 * @throws SnoopeExcepcion
	 */
	public Fruta(int x, int y, String color) throws SnoopeExcepcion {
		if (x < 200 || x > 800 || y < 150 || y > 600)
			throw new SnoopeExcepcion(SnoopeExcepcion.ITEM_POS);
		this.x = x;
		this.y = y;
		this.color = color;
		this.dItem = 400;
		this.isVivo = true;
		if (color.equals("Morado")) {
			this.sprite = Sprite.FRUTAM;
		} else if (color.equals("Negra")) {
			this.sprite = Sprite.FRUTAN;
		} else if (color.equals("Cafe")) {
			this.sprite = Sprite.FRUTAC;
		} else if (color.equals("Verde")) {
			this.sprite = Sprite.FRUTAV;
		}
	}

	public String getColor() {
		return color;
	}

}
