package aplicacion;

import java.io.Serializable;

import controles.BotonesTeclado;
import graficos.Sprite;

/**
 * 
 * @author Juan Esteban Cortes
 *
 */
public class Juego implements Serializable {

	private static final long serialVersionUID = 1L;

	private static Serpiente serpiente1;
	private static Serpiente serpiente2;
	private Item[] alimentos;
	private Item sorpresa;
	private static Tablero tablero;
	private BotonesTeclado controles;
	private static int tSalimento1 = 0;
	private static int dSalimento1 = 100;
	private static int tSalimento2 = 0;
	private static int dSalimento2 = 100;
	private static int tSorpresa = 0;
	private static int dSorpresa = 100;
	private static boolean lupaJ1;
	private static boolean lupaJ2;
	private int players;
	private boolean fin;

	private static Jugador nJugador1;
	private static Jugador nJugador2;

	/**
	 * Constructor de la clase Juego
	 * 
	 * @param players   cantidad de jugadores
	 * @param color1    color del jugador 1
	 * @param color2    color del jugador 2
	 * @param jugador1  nombre del jugador 1
	 * @param jugador2  nombre del jugador 2
	 * @param ancho     ancho del tablero
	 * @param largo     largo del tablero
	 * @param controles objeto que contiene cada comando recibido por las telas del
	 *                  teclado
	 * @throws SnoopeExcepcion Si la cantidad de jugadores es incorrecta o si el
	 *                         nombre llega vacio
	 */
	public Juego(final int players, String color1, String color2, String jugador1, String jugador2, int ancho,
			int largo, BotonesTeclado controles) throws SnoopeExcepcion {
		if (players < 1 || players > 2)
			throw new SnoopeExcepcion(SnoopeExcepcion.CANTIDAD_DE_JUGADORES);
		if (jugador1 == null || jugador2 == null || jugador1.equals("") || jugador2.equals(""))
			throw new SnoopeExcepcion(SnoopeExcepcion.NOMBRE_JUGADOR);
		if (controles == null)
			throw new SnoopeExcepcion(SnoopeExcepcion.NO_CONTROLES);
		this.controles = controles;
		this.players = players;
		Juego.nJugador1 = new Jugador(jugador1, 1);
		Juego.nJugador2 = new Jugador(jugador2, 2);
		this.fin = false;
		this.alimentos = new Item[2];
		crearAlimentos();
		tablero = new Tablero(ancho, largo);

		if (players == 1) {
			serpiente1 = new Serpiente(127, 95, Sprite.MSERPIENTEVR, color1, "r");
		} else if (players == 2) {
			serpiente1 = new Serpiente(127, 95, Sprite.MSERPIENTEVR, color1, "r");
			serpiente2 = new Serpiente(863, 639, Sprite.MSERPIENTEVL, color2, "l");
		}

	}

	/**
	 * Se encarga de asignar el respectivo sprite a la serpiente dependiendo de su
	 * color y direccion
	 * 
	 * @param serp serpiente a modificar
	 * @param dir  direccion a analizar
	 * @throws SnoopeExcepcion
	 */
	private void direccionSerp(Serpiente serp, String dir) throws SnoopeExcepcion {
		if (serp == null)
			throw new SnoopeExcepcion(SnoopeExcepcion.NO_SERPIENTE_JUGADOR);
		if (serp.color.equals("Morado")) {
			if (serp.getDireccion().equals("r")) {
				if (serp.durAnimacion == serp.tAnimacion) {

					if (serp.serpienteSprite.getName().equals("MSerpienteV R")) {
						serp.serpienteSprite = Sprite.MSERPIENTEV2R;
					} else {
						serp.serpienteSprite = Sprite.MSERPIENTEVR;
					}
					serp.durAnimacion = 0;
				} else {
					serp.durAnimacion++;
				}
			} else if (serp.getDireccion().equals("d")) {
				if (serp.durAnimacion == serp.tAnimacion) {
					if (serp.serpienteSprite.getName().equals("MSerpienteV D")) {
						serp.serpienteSprite = Sprite.MSERPIENTEV2D;
					} else {
						serp.serpienteSprite = Sprite.MSERPIENTEVD;
					}
					serp.durAnimacion = 0;
				} else {
					serp.durAnimacion++;
				}
			} else if (serp.getDireccion().equals("u")) {
				if (serp.durAnimacion == serp.tAnimacion) {
					if (serp.serpienteSprite.getName().equals("MSerpienteV U")) {
						serp.serpienteSprite = Sprite.MSERPIENTEV2U;
					} else {
						serp.serpienteSprite = Sprite.MSERPIENTEVU;
					}
					serp.durAnimacion = 0;
				} else {
					serp.durAnimacion++;
				}
			} else if (serp.getDireccion().equals("l")) {
				if (serp.durAnimacion == serp.tAnimacion) {
					if (serp.serpienteSprite.getName().equals("MSerpienteV L")) {
						serp.serpienteSprite = Sprite.MSERPIENTEV2L;
					} else {
						serp.serpienteSprite = Sprite.MSERPIENTEVL;
					}
					serp.durAnimacion = 0;
				} else {
					serp.durAnimacion++;
				}
			}
		} else if (serp.color.equals("Verde")) {
			if (serp.getDireccion().equals("r")) {
				if (serp.durAnimacion == serp.tAnimacion) {

					if (serp.serpienteSprite.getName().equals("VSerpienteV R")) {
						serp.serpienteSprite = Sprite.VSERPIENTEV2R;
					} else {
						serp.serpienteSprite = Sprite.VSERPIENTEVR;
					}
					serp.durAnimacion = 0;
				} else {
					serp.durAnimacion++;
				}
			} else if (serp.getDireccion().equals("d")) {
				if (serp.durAnimacion == serp.tAnimacion) {
					if (serp.serpienteSprite.getName().equals("VSerpienteV D")) {
						serp.serpienteSprite = Sprite.VSERPIENTEV2D;
					} else {
						serp.serpienteSprite = Sprite.VSERPIENTEVD;
					}
					serp.durAnimacion = 0;
				} else {
					serp.durAnimacion++;
				}
			} else if (serp.getDireccion().equals("u")) {
				if (serp.durAnimacion == serp.tAnimacion) {
					if (serp.serpienteSprite.getName().equals("VSerpienteV U")) {
						serp.serpienteSprite = Sprite.VSERPIENTEV2U;
					} else {
						serp.serpienteSprite = Sprite.VSERPIENTEVU;
					}
					serp.durAnimacion = 0;
				} else {
					serp.durAnimacion++;
				}
			} else if (serp.getDireccion().equals("l")) {
				if (serp.durAnimacion == serp.tAnimacion) {
					if (serp.serpienteSprite.getName().equals("VSerpienteV L")) {
						serp.serpienteSprite = Sprite.VSERPIENTEV2L;
					} else {
						serp.serpienteSprite = Sprite.VSERPIENTEVL;
					}
					serp.durAnimacion = 0;
				} else {
					serp.durAnimacion++;
				}
			}
		} else if (serp.color.equals("Cafe")) {
			if (serp.getDireccion().equals("r")) {
				if (serp.durAnimacion == serp.tAnimacion) {

					if (serp.serpienteSprite.getName().equals("CSerpienteV R")) {
						serp.serpienteSprite = Sprite.CSERPIENTEV2R;
					} else {
						serp.serpienteSprite = Sprite.CSERPIENTEVR;
					}
					serp.durAnimacion = 0;
				} else {
					serp.durAnimacion++;
				}
			} else if (serp.getDireccion().equals("d")) {
				if (serp.durAnimacion == serp.tAnimacion) {
					if (serp.serpienteSprite.getName().equals("CSerpienteV D")) {
						serp.serpienteSprite = Sprite.CSERPIENTEV2D;
					} else {
						serp.serpienteSprite = Sprite.CSERPIENTEVD;
					}
					serp.durAnimacion = 0;
				} else {
					serp.durAnimacion++;
				}
			} else if (serp.getDireccion().equals("u")) {
				if (serp.durAnimacion == serp.tAnimacion) {
					if (serp.serpienteSprite.getName().equals("CSerpienteV U")) {
						serp.serpienteSprite = Sprite.CSERPIENTEV2U;
					} else {
						serp.serpienteSprite = Sprite.CSERPIENTEVU;
					}
					serp.durAnimacion = 0;
				} else {
					serp.durAnimacion++;
				}
			} else if (serp.getDireccion().equals("l")) {
				if (serp.durAnimacion == serp.tAnimacion) {
					if (serp.serpienteSprite.getName().equals("CSerpienteV L")) {
						serp.serpienteSprite = Sprite.CSERPIENTEV2L;
					} else {
						serp.serpienteSprite = Sprite.CSERPIENTEVL;
					}
					serp.durAnimacion = 0;
				} else {
					serp.durAnimacion++;
				}
			}
		} else if (serp.color.equals("Negra")) {
			if (serp.getDireccion().equals("r")) {
				if (serp.durAnimacion == serp.tAnimacion) {

					if (serp.serpienteSprite.getName().equals("NSerpienteV R")) {
						serp.serpienteSprite = Sprite.NSERPIENTEV2R;
					} else {
						serp.serpienteSprite = Sprite.NSERPIENTEVR;
					}
					serp.durAnimacion = 0;
				} else {
					serp.durAnimacion++;
				}
			} else if (serp.getDireccion().equals("d")) {
				if (serp.durAnimacion == serp.tAnimacion) {
					if (serp.serpienteSprite.getName().equals("NSerpienteV D")) {
						serp.serpienteSprite = Sprite.NSERPIENTEV2D;
					} else {
						serp.serpienteSprite = Sprite.NSERPIENTEVD;
					}
					serp.durAnimacion = 0;
				} else {
					serp.durAnimacion++;
				}
			} else if (serp.getDireccion().equals("u")) {
				if (serp.durAnimacion == serp.tAnimacion) {
					if (serp.serpienteSprite.getName().equals("NSerpienteV U")) {
						serp.serpienteSprite = Sprite.NSERPIENTEV2U;
					} else {
						serp.serpienteSprite = Sprite.NSERPIENTEVU;
					}
					serp.durAnimacion = 0;
				} else {
					serp.durAnimacion++;
				}
			} else if (serp.getDireccion().equals("l")) {
				if (serp.durAnimacion == serp.tAnimacion) {
					if (serp.serpienteSprite.getName().equals("NSerpienteV L")) {
						serp.serpienteSprite = Sprite.NSERPIENTEV2L;
					} else {
						serp.serpienteSprite = Sprite.NSERPIENTEVL;
					}
					serp.durAnimacion = 0;
				} else {
					serp.durAnimacion++;
				}
			}
		}

	}

	/**
	 * Se encarga de mover las serpientes actuales en el juego
	 * 
	 * @throws SnoopeExcepcion
	 */
	public void moverSerpientes() throws SnoopeExcepcion {
		if (players == 1) {
			if (serpiente1.isVIvo()) {
				if (controles.abajoS1) {
					serpiente1.actualizar("d");
					direccionSerp(serpiente1, "d");
				} else if (controles.arribaS1) {
					serpiente1.actualizar("u");
					direccionSerp(serpiente1, "u");
				} else if (controles.izquierdaS1) {
					serpiente1.actualizar("l");
					direccionSerp(serpiente1, "l");
				} else if (controles.derechaS1) {
					serpiente1.actualizar("r");
					direccionSerp(serpiente1, "r");
				} else {
					serpiente1.actualizar(serpiente1.getDireccion());
					direccionSerp(serpiente1, serpiente1.getDireccion());
				}
				serpiente1.mover();

			}
		} else if (players == 2) {
			if (serpiente1.isVIvo()) {
				if (controles.abajoS1) {
					serpiente1.actualizar("d");
					direccionSerp(serpiente1, "d");
				} else if (controles.arribaS1) {
					serpiente1.actualizar("u");
					direccionSerp(serpiente1, "u");
				} else if (controles.izquierdaS1) {
					serpiente1.actualizar("l");
					direccionSerp(serpiente1, "l");
				} else if (controles.derechaS1) {
					serpiente1.actualizar("r");
					direccionSerp(serpiente1, "r");
				} else {
					serpiente1.actualizar(serpiente1.getDireccion());
					direccionSerp(serpiente1, serpiente1.getDireccion());
				}
				serpiente1.mover();

			}
			if (serpiente2.isVIvo()) {
				if (controles.abajoS2) {
					serpiente2.actualizar("d");
					direccionSerp(serpiente2, "d");
				} else if (controles.arribaS2) {
					serpiente2.actualizar("u");
					direccionSerp(serpiente2, "u");
				} else if (controles.izquierdaS2) {
					serpiente2.actualizar("l");
					direccionSerp(serpiente2, "l");
				} else if (controles.derechaS2) {
					serpiente2.actualizar("r");
					direccionSerp(serpiente2, "r");
				} else {
					serpiente2.actualizar(serpiente2.getDireccion());
					direccionSerp(serpiente2, serpiente2.getDireccion());
				}
				serpiente2.mover();

			}
		}
	}

	/**
	 * Verifica si el juego a acabado
	 * 
	 * @return fin true si el juego acabo , false si el juego sigue
	 */
	public void finDelJuego() {
		if (players == 1 && !serpiente1.isVIvo()) {
			fin = true;
		} else if (players == 2 && (!serpiente1.isVIvo() || !serpiente2.isVIvo())) {
			if (serpiente1.isVIvo()) {
				nJugador1.setGandor(true);
				;
			} else {
				nJugador2.setGandor(true);
			}
			fin = true;
		}
	}

	/**
	 * crea o refresca el tablero dejandolo en un estado base y listo para colocar
	 * los nuevos Sprites
	 */
	public void crearTablero() {
		tablero.crear();
	}

	/**
	 * Genera los alimentos que actualmente estan en el juego
	 * 
	 * @throws SnoopeExcepcion
	 */
	public void crearAlimentos() throws SnoopeExcepcion {
		if (alimentos[0] == null && alimentos[1] == null) {

			alimentos[0] = Alimento.generarAlimentoAleatorio();
			alimentos[1] = Alimento.generarAlimentoAleatorio();

		}
		if (!alimentos[0].isVivo) {
			if (dSalimento1 == tSalimento1) {
				alimentos[0] = Alimento.generarAlimentoAleatorio();
				tSalimento1 = 0;
			} else {
				tSalimento1 += 1;
			}

		}
		if (!alimentos[1].isVivo) {
			if (dSalimento2 == tSalimento2) {
				alimentos[1] = Alimento.generarAlimentoAleatorio();
				tSalimento2 = 0;
			} else {
				tSalimento2 += 1;
			}

		}
	}

	/**
	 * Genera la sorpresa que se muestra en el tablero
	 * 
	 * @throws SnoopeExcepcion
	 */
	public void crearSorpresa() throws SnoopeExcepcion {
		if (sorpresa == null) {
			sorpresa = Sorpresa.generarSorpresaAleatoria();
		}
		if (!sorpresa.isVivo) {
			if (dSorpresa == tSorpresa) {
				sorpresa = Sorpresa.generarSorpresaAleatoria();
				tSorpresa = 0;
			} else {
				tSorpresa += 1;
			}
		}
	}

	/**
	 * Aplica los efectos de cada alimento
	 * @param serp serpiente a la que se le aplicaran los efectos
	 * @param it tipo de alimento
	 * @throws SnoopeExcepcion
	 */
	private void aplicarEfectosAlimentos(Serpiente serp,Item it) throws SnoopeExcepcion {
		if(it instanceof Fruta) {
			if(serp.color.equals(((Fruta) it).getColor())) {
				it.morir();
				serp.a�adirCola();
				serp.a�adirCola();
			}else {
				it.morir();
				serp.a�adirCola();
			}
			
			
		}else if(it instanceof FrutaArc) {
			it.morir();
			serp.a�adirCola();
			serp.a�adirCola();
			serp.a�adirCola();

		}else if(it instanceof Veneno) {
			it.morir();
			serp.morir();
		}else if(it instanceof Dulce) {
			it.morir();
			serp.removerCola();
		}
	}

	/**
	 * Aplica los efectos de las sopresas a las serpientes
	 * 
	 * @param jugador
	 */
	private void aplicarEfectosSorpresas(Jugador jugador) {
		if (jugador.getSPendiente().equals("Division")) {
			jugador.setSPendiente("Ninguna");
			if (players == 1) {
				int lim = Math.round(serpiente1.getCuerpo().size() / 2);
				for (int i = 0; i < lim; i++) {
					serpiente1.removerCola();
				}
			} else if (players == 2) {

				if (jugador.getNumero() == 1) {
					int lim = Math.round(serpiente2.getCuerpo().size() / 2);
					for (int i = 0; i < lim; i++) {
						serpiente2.removerCola();
					}
				} else if (jugador.getNumero() == 2) {
					int lim = Math.round(serpiente1.getCuerpo().size() / 2);
					for (int i = 0; i < lim; i++) {
						serpiente1.removerCola();
					}
				}

			}
		} else if (jugador.getSPendiente().equals("Lupa")) {
			jugador.setSPendiente("Ninguna");

			if (players == 1) {
				lupaJ1 = true;
			} else if (players == 2) {
				if (jugador.getNumero() == 1) {
					lupaJ2 = true;
				} else if (jugador.getNumero() == 2) {
					lupaJ1 = true;
				}
			}

		} else if (jugador.getSPendiente().equals("Estrella de Fuego")) {
			jugador.setSPendiente("Ninguna");

		} else if (jugador.getSPendiente().equals("Flecha de Velocidad +")) {
			jugador.setSPendiente("Ninguna");
			if (jugador.getNumero() == 1) {
				serpiente1.aumentarVelocidad();
			} else if (jugador.getNumero() == 2) {
				serpiente2.aumentarVelocidad();
			}

		} else if (jugador.getSPendiente().equals("Flecha de Velocidad -")) {
			jugador.setSPendiente("Ninguna");
			if (players == 1) {
				serpiente1.disminuirVelocidad();
			} else if (players == 2) {
				if (jugador.getNumero() == 1) {
					serpiente2.disminuirVelocidad();
				} else if (jugador.getNumero() == 2) {
					serpiente1.disminuirVelocidad();
				}
			}
		} else if (jugador.getSPendiente().equals("Bloque Trampa")) {

		}
	}

	/**
	 * Verifica si el jugador a presionado la tecla para activar su sorpresa
	 */
	public void activarSorpresa() {
		if (controles.actSorpresaS1) {
			aplicarEfectosSorpresas(nJugador1);
		}
		if (controles.actSorpresaS2) {
			aplicarEfectosSorpresas(nJugador2);
		}
	}

	/**
	 * Agrega en pendiente la sorpresa al jugador que la agarro
	 * 
	 * @param it sopresa agarrada
	 */
	private void ponerEnPendiente(Item it, int jug) {
		it.morir();
		String nom;
		if (it instanceof Division) {
			nom = "Division";
		} else if (it instanceof EstrellaDeFuego) {
			nom = "Estrella de Fuego";
		} else if (it instanceof Lupa) {
			nom = "Lupa";
		} else if (it instanceof FlechaMasVelocidad) {
			nom = "Flecha de Velocidad +";
		} else if (it instanceof FlechaMenosVelocidad) {
			nom = "Flecha de Velocidad -";
		} else if (it instanceof BloqueTrampa) {
			nom = "Bloque Trampa";
		} else {
			nom = "Ninguna";
		}

		if (jug == 1) {
			nJugador1.setSPendiente(nom);
		} else if (jug == 2) {
			nJugador2.setSPendiente(nom);
		}
	}

	/**
	 * Verifica si hay colisiones entre las serpientes y cambia el estado a muerto
	 * de la serpiente que colisiono con la otra
	 */
	public void colisionSerpientes() {
		for (Cola i : serpiente1.getCuerpo()) {
			int pLX = i.getPosX() + 12;
			int pUY = i.getPosY() + 12;

			int pDY = pUY + 8;
			int pRX = pLX + 8;

			int hitLX = serpiente1.getPosX() + 10;
			int hitUY = serpiente1.getPosY() + 10;

			int hitDY = hitUY + 10;
			int hitRX = hitLX + 10;

			for (int x = pLX; x <= pRX; x++) {
				for (int y = pUY; y <= pDY; y++) {
					if ((hitLX == x && hitUY == y) || (hitLX == x && hitDY == y) || (hitRX == x && hitUY == y)
							|| (hitRX == x && hitDY == y)) {
						serpiente1.morir();
						;
					}
				}
			}
		}

		if (players == 2) {
			for (Cola i : serpiente2.getCuerpo()) {
				int pLX = i.getPosX() + 12;
				int pUY = i.getPosY() + 12;

				int pDY = pUY + 8;
				int pRX = pLX + 8;

				int hitLX = serpiente1.getPosX() + 10;
				int hitUY = serpiente1.getPosY() + 10;

				int hitDY = hitUY + 10;
				int hitRX = hitLX + 10;

				for (int x = pLX; x <= pRX; x++) {
					for (int y = pUY; y <= pDY; y++) {
						if ((hitLX == x && hitUY == y) || (hitLX == x && hitDY == y) || (hitRX == x && hitUY == y)
								|| (hitRX == x && hitDY == y)) {
							serpiente1.morir();
							;
						}
					}
				}
			}

			for (Cola i : serpiente2.getCuerpo()) {
				int pLX = i.getPosX() + 12;
				int pUY = i.getPosY() + 12;

				int pDY = pUY + 8;
				int pRX = pLX + 8;

				int hitLX = serpiente2.getPosX() + 10;
				int hitUY = serpiente2.getPosY() + 10;

				int hitDY = hitUY + 10;
				int hitRX = hitLX + 10;

				for (int x = pLX; x <= pRX; x++) {
					for (int y = pUY; y <= pDY; y++) {
						if ((hitLX == x && hitUY == y) || (hitLX == x && hitDY == y) || (hitRX == x && hitUY == y)
								|| (hitRX == x && hitDY == y)) {
							serpiente2.morir();
							;
						}
					}
				}
			}

			for (Cola i : serpiente1.getCuerpo()) {
				int pLX = i.getPosX() + 12;
				int pUY = i.getPosY() + 12;

				int pDY = pUY + 8;
				int pRX = pLX + 8;

				int hitLX = serpiente2.getPosX() + 10;
				int hitUY = serpiente2.getPosY() + 10;

				int hitDY = hitUY + 10;
				int hitRX = hitLX + 10;

				for (int x = pLX; x <= pRX; x++) {
					for (int y = pUY; y <= pDY; y++) {
						if ((hitLX == x && hitUY == y) || (hitLX == x && hitDY == y) || (hitRX == x && hitUY == y)
								|| (hitRX == x && hitDY == y)) {
							serpiente2.morir();
							;
						}
					}
				}
			}
		}
	}

	/**
	 * Verifica si las serpientes han colisionado con algun alimento
	 * 
	 * @throws SnoopeExcepcion
	 */
	public void colisionAlimentos() throws SnoopeExcepcion {
		for (Item i : alimentos) {
			if (i.isVivo) {
				int pLX = i.getPosX();
				int pUY = i.getPosY();

				int pDY = pUY + 32;
				int pRX = pLX + 32;

				int hitLX = serpiente1.getPosX();
				int hitUY = serpiente1.getPosY();

				int hitDY = hitUY + 32;
				int hitRX = hitLX + 32;

				for (int x = pLX; x <= pRX; x++) {
					for (int y = pUY; y <= pDY; y++) {
						if ((hitLX == x && hitUY == y) || (hitLX == x && hitDY == y) || (hitRX == x && hitUY == y)
								|| (hitRX == x && hitDY == y)) {
							if (!lupaJ1) {
								aplicarEfectosAlimentos(serpiente1, i);
							} else {
								lupaJ1 = false;
								i.morir();
							}
						}
					}
				}

				if (players == 2) {
					int hitLX2 = serpiente2.getPosX();
					int hitUY2 = serpiente2.getPosY();

					int hitDY2 = hitUY2 + 32;
					int hitRX2 = hitLX2 + 32;

					for (int x = pLX; x <= pRX; x++) {
						for (int y = pUY; y <= pDY; y++) {
							if ((hitLX2 == x && hitUY2 == y) || (hitLX2 == x && hitDY2 == y)
									|| (hitRX2 == x && hitUY2 == y) || (hitRX2 == x && hitDY2 == y)) {
								if (!lupaJ2) {
									aplicarEfectosAlimentos(serpiente2, i);
								} else {
									lupaJ2 = false;
									i.morir();
								}

							}
						}
					}
				}

			}

		}
	}

	/**
	 * Verifica si las serpientes han colisionado con una sorpresa
	 * 
	 * @throws SnoopeExcepcion
	 */
	public void colisionSorpresa() throws SnoopeExcepcion {

		if (!(sorpresa == null)) {
			if (sorpresa.isVivo) {
				int pLX = sorpresa.getPosX();
				int pUY = sorpresa.getPosY();

				int pDY = pUY + 32;
				int pRX = pLX + 31;

				int hitLX = serpiente1.getPosX();
				int hitUY = serpiente1.getPosY();

				int hitDY = hitUY + 32;
				int hitRX = hitLX + 32;

				for (int x = pLX; x <= pRX; x++) {
					for (int y = pUY; y <= pDY; y++) {
						if ((hitLX == x && hitUY == y) || (hitLX == x && hitDY == y) || (hitRX == x && hitUY == y)
								|| (hitRX == x && hitDY == y)) {
							ponerEnPendiente(sorpresa, 1);
						}
					}
				}

				if (players == 2) {
					int hitLX2 = serpiente2.getPosX();
					int hitUY2 = serpiente2.getPosY();

					int hitDY2 = hitUY2 + 32;
					int hitRX2 = hitLX2 + 32;

					for (int x = pLX; x <= pRX; x++) {
						for (int y = pUY; y <= pDY; y++) {
							if ((hitLX2 == x && hitUY2 == y) || (hitLX2 == x && hitDY2 == y)
									|| (hitRX2 == x && hitUY2 == y) || (hitRX2 == x && hitDY2 == y)) {
								ponerEnPendiente(sorpresa, 2);
							}
						}
					}
				}

			}
		}

	}

	/**
	 * Actualiza el estado de la puntuacion de los jugadores
	 */
	public void puntosJugadores() {
		if (players == 1) {
			nJugador1.setPuntiacion(serpiente1.getCuerpo().size());
		} else if (players == 2) {
			nJugador1.setPuntiacion(serpiente1.getCuerpo().size());
			nJugador2.setPuntiacion(serpiente2.getCuerpo().size());
		}
	}

	/**
	 * Actualiza en el tablero los sprites de serpientes e items
	 */
	public void mostrar() {
		if (players == 1) {
			serpiente1.mostrar(tablero);
		} else if (players == 2) {
			serpiente1.mostrar(tablero);
			serpiente2.mostrar(tablero);
		}

		for (Item i : alimentos) {
			i.mostrar(tablero);
		}
		if (!(sorpresa == null)) {
			sorpresa.mostrar(tablero);
		}

		if (nJugador1.getSPendiente().equals("Division")) {
			tablero.mostrarSprite(60, 33, Sprite.DIVISION);
		} else if (nJugador1.getSPendiente().equals("Estrella de Fuego")) {
			tablero.mostrarSprite(60, 33, Sprite.ESTRELLA);
		} else if (nJugador1.getSPendiente().equals("Flecha de Velocidad +")) {
			tablero.mostrarSprite(60, 33, Sprite.FVELOCIDADMAS);
		} else if (nJugador1.getSPendiente().equals("Flecha de Velocidad -")) {
			tablero.mostrarSprite(60, 33, Sprite.FVELOCIDADMEN);
		} else if (nJugador1.getSPendiente().equals("Bloque Trampa")) {
			tablero.mostrarSprite(60, 33, Sprite.MTRAMPA);
		} else if (nJugador1.getSPendiente().equals("Lupa")) {
			tablero.mostrarSprite(60, 33, Sprite.LUPA);
		}
		if (players == 2) {
			if (nJugador2.getSPendiente().equals("Division")) {
				tablero.mostrarSprite(565, 33, Sprite.DIVISION);
			} else if (nJugador2.getSPendiente().equals("Estrella de Fuego")) {
				tablero.mostrarSprite(565, 33, Sprite.ESTRELLA);
			} else if (nJugador2.getSPendiente().equals("Flecha de Velocidad +")) {
				tablero.mostrarSprite(565, 33, Sprite.FVELOCIDADMAS);
			} else if (nJugador2.getSPendiente().equals("Flecha de Velocidad -")) {
				tablero.mostrarSprite(565, 33, Sprite.FVELOCIDADMEN);
			} else if (nJugador2.getSPendiente().equals("Bloque Trampa")) {
				tablero.mostrarSprite(565, 33, Sprite.MTRAMPA);
			} else if (nJugador2.getSPendiente().equals("Lupa")) {
				tablero.mostrarSprite(565, 33, Sprite.LUPA);
			}
		}

	}

	/**
	 * actualiza el estado de los objetos del juego
	 * 
	 * @throws SnoopeExcepcion
	 */
	public void actualizar() throws SnoopeExcepcion {
		controles.actualizar();
		finDelJuego();
		if (!fin) {
			crearSorpresa();
			moverSerpientes();
			activarSorpresa();
			for (Item i : alimentos) {
				i.actualizar();
			}
			sorpresa.actualizar();
			crearAlimentos();
			colisionAlimentos();
			colisionSorpresa();
			colisionSerpientes();
			puntosJugadores();
		}

	}

	public Tablero getTablero() {
		return tablero;
	}

	public Serpiente getSerpiente1() {
		return serpiente1;
	}

	public Serpiente getSerpiente2() {
		return serpiente2;
	}

	public boolean isEnded() {
		return fin;
	}

	public int getPlayers() {
		return players;
	}

	public String getJugador1() {
		return nJugador1.getNombre();
	}

	public String getJugador2() {
		return nJugador2.getNombre();
	}

	public int getPJugador1() {
		return nJugador1.getPuntos();
	}

	public int getPJugador2() {
		return nJugador2.getPuntos();
	}

	public String getSPJugador1() {
		return nJugador1.getSPendiente();
	}

	public String getSPJugador2() {
		return nJugador2.getSPendiente();
	}

	/**
	 * Obtiene el nombre del jugador ganador
	 * 
	 * @return
	 */
	public String getGanador() {
		String gan = "No hay ganador";
		if (nJugador1.isGanador()) {
			gan = nJugador1.getNombre();
		} else if (nJugador2.isGanador()) {
			gan = nJugador2.getNombre();
		}
		return gan;
	}

	/**
	 * Obtiene los puntos del jugador ganador
	 * 
	 * @return puntos son los puntos que tiene el jugdor ganador
	 */
	public int getPuntosGanador() {
		int puntos;
		if (players == 1) {
			puntos = nJugador1.getPuntos();
		} else if (players == 2) {
			if (nJugador1.isGanador()) {
				puntos = nJugador1.getPuntos();
			} else if (nJugador2.isGanador()) {
				puntos = nJugador2.getPuntos();
			} else {
				puntos = 0;
			}
		} else {
			puntos = 0;
		}
		return puntos;
	}

}
