package aplicacion;

import graficos.Sprite;

public final class Tablero {
	private int ancho;
	private int largo;

	public final int[] pixeles;

	private int difX;
	private int difY;

	/**
	 * Constructor de la clase Tablero
	 * 
	 * @param ancho ancho del tablero en pixeles
	 * @param largo largo del tablero en pixeles
	 */
	public Tablero(final int ancho, final int largo) {
		this.ancho = ancho;
		this.largo = largo;
		pixeles = new int[ancho * largo];
	}

	/**
	 * Crea un tablero predeterminado sin objetos dentro
	 */
	public void crear() {
		for (int i = 0; i < 32; i++) {
			for (int j = 0; j < 24; j++) {
				if ((j == 2 && i >= 4 && i <= 27) || (j == 21 && i >= 4 && i <= 27) || (i == 3 && j >= 2 && j <= 21)
						|| (i == 28 && j >= 2 && j <= 21)) {
					mostrarSprite(i * 32, j * 32, Sprite.MURO);
				} else if (i < 3 || j < 2 || j > 21 || i > 28) {
					mostrarSprite(i * 32, j * 32, Sprite.HIERBA1);
				} else {
					if (i % 2 == 0 && j % 2 != 0) {
						mostrarSprite(i * 32, j * 32, Sprite.HIERBA2);
					} else {
						mostrarSprite(i * 32, j * 32, Sprite.HIERBA3);
					}

				}

			}

		}
	}

	/**
	 * Muestra en el tablero el sprite de la serpiente que tiene atualmente
	 * 
	 * @param movX posicion x de la serpiente en la que esta actualmente
	 * @param movY posicion y de la serpiente en la que esta actualmente
	 * @param serp Objeto que representa la serpiente
	 */
	public void mostrarSerpiente(int movX, int movY, Serpiente serp) {
		movX -= difX;
		movY -= difY;

		for (int y = 0; y < serp.getSprite().getLado(); y++) {
			int posY = y + movY;
			for (int x = 0; x < serp.getSprite().getLado(); x++) {
				int posX = x + movX;
				if (posX < 0 - serp.getSprite().getLado() || posX >= ancho || posY < 0 || posY >= largo) {
					break;
				}
				if (posX < 0) {
					posX = 0;
				}
				int colorPixsel = serp.serpienteSprite.pixeles[x + y * serp.serpienteSprite.getLado()];

				if (colorPixsel != 0xFF000000) {
					pixeles[posX + posY * ancho] = colorPixsel;
				}
			}
		}
	}

	/**
	 * Muestra en el tablero el sprite de la cola de la serpiente que tiene
	 * atualmente
	 * 
	 * @param movX posicion x de la cola de la serpiente en la que esta actualmente
	 * @param movY posicion y de la cola de la serpiente en la que esta actualmente
	 * @param col  objeto que representa la cola de la serpiente
	 */
	public void mostrarCola(int movX, int movY, Cola col) {
		movX -= difX;
		movY -= difY;

		for (int y = 0; y < col.getSprite().getLado(); y++) {
			int posY = y + movY;
			for (int x = 0; x < col.getSprite().getLado(); x++) {
				int posX = x + movX;
				if (posX < 0 - col.getSprite().getLado() || posX >= ancho || posY < 0 || posY >= largo) {
					break;
				}
				if (posX < 0) {
					posX = 0;
				}
				int colorPixsel = col.colaSprite.pixeles[x + y * col.colaSprite.getLado()];

				if (colorPixsel != 0xFF000000) {
					pixeles[posX + posY * ancho] = colorPixsel;
				}
			}
		}
	}

	/**
	 * Muestra en el tablero un Item ya sea una sorpresa o alimento
	 * 
	 * @param movX posicion x en la que se va a mostrar el sprite del item en el
	 *             tablero
	 * @param movY posicion y en la que se va a mostrar el sprite del item en el
	 *             tablero
	 * @param item objeto que representa el item
	 */
	public void mostrarItem(int movX, int movY, Item item) {
		movX -= difX;
		movY -= difY;

		for (int y = 0; y < item.getSprite().getLado(); y++) {
			int posY = y + movY;
			for (int x = 0; x < item.getSprite().getLado(); x++) {
				int posX = x + movX;
				if (posX < 0 - item.getSprite().getLado() || posX >= ancho || posY < 0 || posY >= largo) {
					break;
				}
				if (posX < 0) {
					posX = 0;
				}
				int colorPixsel = item.sprite.pixeles[x + y * item.sprite.getLado()];

				if (colorPixsel != 0xFF000000) {
					pixeles[posX + posY * ancho] = colorPixsel;
				}
			}
		}
	}

	/**
	 * Muestra un sprite ne el tablero
	 * 
	 * @param movX posicion x en la que se va a mostrar el sprite en el tablero
	 * @param movY posicion x en la que se va a mostrar el sprite en el tablero
	 * @param sp   objeto que representa el Sprite
	 */
	public void mostrarSprite(int movX, int movY, Sprite sp) {
		movX -= difX;
		movY -= difY;
		for (int y = 0; y < sp.getLado(); y++) {
			int posY = y + movY;
			for (int x = 0; x < sp.getLado(); x++) {
				int posX = x + movX;
				if (posX < 0 - sp.getLado() || posX >= ancho || posY < 0 || posY >= largo) {
					break;
				}
				if (posX < 0) {
					posX = 0;
				}
				int colorPixsel = sp.pixeles[x + y * sp.getLado()];
				pixeles[posX + posY * ancho] = colorPixsel;

			}
		}
	}

	public void setDiferencia(final int difX, final int difY) {
		this.difX = difX;
		this.difY = difY;
	}
}
