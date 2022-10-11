package aplicacion;

import java.util.ArrayList;

import graficos.Sprite;

public class Cola implements Elemento {

	private String direccion;
	private int x;
	private int y;
	private boolean viva;
	private int vel;
	public String color;
	private ArrayList<String> ultDir;
	private ArrayList<int[]> ultPos;

	public Sprite colaSprite;

	/**
	 * Constructor de la clase Cola
	 * 
	 * @param x      posicion x de la cola
	 * @param y      posicion y de la cola
	 * @param sprite Sprite inicial de la cola
	 * @param color  color de la cola(Serpiente)
	 * @param dir    direccion inicial a la que va la cola
	 */
	public Cola(final int x, final int y, Sprite sprite, String color, String dir) {
		this.x = x;
		this.y = y;
		this.colaSprite = sprite;
		this.color = color;
		this.vel = 2;
		ultDir = new ArrayList<String>();
		ultPos = new ArrayList<int[]>();
		viva = true;
		this.direccion = dir;
	}

	/**
	 * actualiza la direccion a donde va la cola
	 * 
	 * @param mov la nueva direccion de la cola
	 */
	public void actualizar(String mov) {
		this.direccion = mov;
	}

	/**
	 * Muestra en el tablero de juego la cola
	 * 
	 * @param tablero tablero donde se va a mostrar la cola
	 */
	public void mostrar(Tablero tablero) {
		tablero.mostrarCola(x, y, this);
	}

	/**
	 * Mueve la cola dependiendo de la direccion actual y si esta viva
	 */
	public void mover() {
		if (viva) {
			if (direccion.equals("r")) {
				x += vel;
			} else if (direccion.equals("l")) {
				x -= vel;
			} else if (direccion.equals("u")) {
				y -= vel;
			} else if (direccion.equals("d")) {
				y += vel;
			}

			if (ultDir.size() == 15) {
				ultDir.add(direccion);
				int pX = x;
				int pY = y;
				int[] pos = { pX, pY };
				ultPos.add(pos);
				ultDir.remove(0);
				ultPos.remove(0);
			} else {
				ultDir.add(direccion);
				int pX = x;
				int pY = y;
				int[] pos = { pX, pY };
				ultPos.add(pos);
			}
		}
	}

	/**
	 * Indica si la cola esta viva
	 */
	public boolean isVIvo() {
		return false;
	}

	public Sprite getSprite() {
		return colaSprite;
	}

	public int getPosX() {
		return x;
	}

	public int getPosY() {
		return y;
	}

	public String getUltDir() {
		return ultDir.get(0);
	}

	public int getUltPosX() {
		return ultPos.get(0)[0];
	}

	public int getUltPosY() {
		return ultPos.get(0)[1];
	}

	public String getDireccion() {
		return direccion;
	}

}
