package aplicacion;

import java.io.Serializable;

import graficos.Sprite;

public abstract class Item implements Elemento, Serializable {

	private static final long serialVersionUID = 1L;
	protected int x;
	protected int y;
	protected boolean isVivo;
	protected int dItem;
	protected int tItem = 0;
	public Sprite sprite;

	/**
	 * Constructor
	 */
	public Item() {

	}

	/**
	 * Actualiza el estado del Item
	 */
	public void actualizar() {
		if (dItem == tItem) {
			isVivo = false;
		} else {
			tItem += 1;
		}
	}

	/**
	 * Muestra en el tablero el item dependiendo de su posicion
	 * 
	 * @param tablero tablero en el que se va a mostrar el sprite item
	 */
	public void mostrar(Tablero tablero) {
		if (isVivo) {
			tablero.mostrarItem(x, y, this);
		}

	}

	/**
	 * Cambia el estado del item a muerto
	 */
	public void morir() {
		isVivo = false;
	}

	public int getDuracionItem() {
		return dItem;
	}

	public boolean isVIvo() {
		return isVivo;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public int getPosX() {
		return x;
	}

	public int getPosY() {
		return y;
	}

}
