package aplicacion;

import java.util.ArrayList;

import graficos.Sprite;

public class Serpiente implements Elemento {

	private String direccion;
	private int x;
	private int y;
	private boolean viva;
	public int tAnimacion = 10;
	public int durAnimacion = 0;
	private int tA�adirC=50;
	private int dA�adirC=0;
	private int tDoble = 150;
	private int dDoble = 0;
	private int tMitad = 150;
	private int dMitad = 0;
	private int colasPendientes;
	private int vel;
	private boolean doble;
	private boolean mitad;
	public String color;
	private ArrayList<Cola> cuerpo;

	private ArrayList<String> ultDir;
	private ArrayList<int[]> ultPos;

	public Sprite serpienteSprite;

	/**
	 * Constructor de la clase Serpiente
	 * 
	 * @param x      posicion x de la serpiente
	 * @param y      posicion y de la serpiente
	 * @param sprite Sprite inicial de la serpiente
	 * @param color  color de la serpiente
	 * @param dir    direccion inicial de la serpiente
	 */
	public Serpiente(final int x, final int y, Sprite sprite, String color, String dir) {
		this.x = x;
		this.y = y;
		this.serpienteSprite = sprite;
		this.color = color;
		this.vel = 2;
		this.doble = false;
		this.mitad = false;
		ultDir = new ArrayList<String>();
		ultPos = new ArrayList<int[]>();
		cuerpo = new ArrayList<Cola>();
		viva = true;
		direccion = dir;
		colasPendientes = 0;
	}

	/**
	 * Actuliza la direccion de la serpiente a demas de el estado en que se encuentra viva o muerta
	 * @param mov nueva direccion de la serpiente
	 */
	public void actualizar(String mov) {
		if(viva) {
			if ((direccion.equals("r") || direccion.equals("l")) && !mov.equals("l") && !mov.equals("r")) {
				direccion = mov;
				
			}else if ((direccion.equals("u") || direccion.equals("d")) && !mov.equals("u") && !mov.equals("d")){
				direccion = mov;
			}
			
			if (cuerpo.size() > 0) {
				cuerpo.get(0).actualizar(ultDir.get(0));
				for(int i = 1; i < cuerpo.size(); i++) {
					cuerpo.get(i).actualizar(cuerpo.get(i-1).getUltDir());
				}
			}
			
		}
		
		if ( x < 127 || x > 863 || y < 95  || y > 639) {
			morir();
		}
		
		if(colasPendientes > 0 && dA�adirC == tA�adirC) {
			crearCola();
			dA�adirC = 0;
			colasPendientes -=1;
		}else if (colasPendientes > 0){
			dA�adirC +=1;
			
		}
		
		if(doble && tDoble == dDoble) {
			doble = false;
			dDoble = 0;
		}else if(doble) {
			dDoble +=1;
		}if(!doble && mitad && tMitad == dMitad) {
			mitad = false;
			dMitad = 0;
		}else if(!doble && mitad) {
			dMitad += 1;
		}
			
		
	}

	/**
	 * Cambia el estado de vida de la serpiente
	 */
	public void morir() {
		viva = false;
		if (color.equals("Morado")) {
			serpienteSprite = Sprite.MSERPIENTEM;
		} else if (color.equals("Cafe")) {
			serpienteSprite = Sprite.CSERPIENTEM;
		} else if (color.equals("Negra")) {
			serpienteSprite = Sprite.NSERPIENTEM;
		} else if (color.equals("Verde")) {
			serpienteSprite = Sprite.VSERPIENTEM;
		}
	}

	/**
	 * Muestra en el tablero la serpiente
	 * 
	 * @param tablero tablero donde se va a mostrar la serpiente
	 */
	public void mostrar(Tablero tablero) {
		tablero.mostrarSerpiente(x, y, this);
		for (Cola i : cuerpo) {
			i.mostrar(tablero);
		}
	}

	/**
	 * mueve la serpiete dependiendo de la direccion en la que se encuentra
	 */
	private void realizarMovimiento() {
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

			for (Cola i : cuerpo) {
				i.mover();
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
	 * Raliza el movimiento de la serpiente dependiendo de la velocidad
	 */
	public void mover() {
		if (!doble && !mitad) {
			realizarMovimiento();
		} else if (doble) {
			int stop = 0;
			while (stop <= 1) {
				realizarMovimiento();
				stop += 1;
			}
		} else if (mitad) {
			if (dMitad % 2 == 0) {
				realizarMovimiento();
			}
		}
	}

	/**
	 * Remueve la ultima cola de la sepiente
	 */
	public void removerCola() {
		if (cuerpo.size() > 0) {
			cuerpo.remove(cuerpo.size() - 1);
		}

	}

	/**
	 * A�ade una cola a la lista de colas por adicionar
	 */
	public void a�adirCola() {
		colasPendientes +=1;
	}

	/**
	 * Crea una nueva cola y la a�ade al final de la serpiente
	 */
	private void crearCola() {
		Sprite cCola;
		if (color.equals("Morado")) {
			cCola = Sprite.MCOLA;
		} else if (color.equals("Verde")) {
			cCola = Sprite.VCOLA;
		} else if (color.equals("Negra")) {
			cCola = Sprite.NCOLA;
		} else if (color.equals("Cafe")) {
			cCola = Sprite.CCOLA;
		} else {
			cCola = Sprite.MCOLA;
		}

		if (cuerpo.size() > 0) {
			int posX = cuerpo.get(cuerpo.size() - 1).getUltPosX();
			int posY = cuerpo.get(cuerpo.size() - 1).getUltPosY();

			Cola co = new Cola(posX, posY, cCola, color, cuerpo.get(cuerpo.size() - 1).getUltDir());
			cuerpo.add(co);

		} else {
			int posX = ultPos.get(0)[0];
			int posY = ultPos.get(0)[1];
			Cola co = new Cola(posX, posY, cCola, color, direccion);
			cuerpo.add(co);
		}

	}

	/**
	 * cambia la valocidad haciendo que la serpiente vaya mas rapido
	 */
	public void aumentarVelocidad() {
		doble = true;
	}

	/**
	 * cambia la velocidad haciendo que la serpiente vaya mas lento
	 */
	public void disminuirVelocidad() {
		mitad = true;
	}

	/**
	 * Indica si la serpiente esta viva o muerta
	 */
	public boolean isVIvo() {
		return viva;
	}

	public ArrayList<Cola> getCuerpo() {
		return cuerpo;
	}

	public int getPosX() {
		return x;
	}

	public int getPosY() {
		return y;
	}

	public Sprite getSprite() {
		return serpienteSprite;
	}

	public String getDireccion() {
		return direccion;
	}
}
