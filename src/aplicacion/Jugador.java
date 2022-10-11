package aplicacion;

public class Jugador {

	private String nombre;
	private String sPendiente;
	private int numero;
	private int puntuacion;
	private boolean ganador;

	/**
	 * Constructor de la clase Jugador
	 * 
	 * @param nombre nombre del jugador
	 * @param numero numero del jugador
	 */
	public Jugador(String nombre, int numero) {
		this.nombre = nombre;
		this.numero = numero;
		puntuacion = 0;
		sPendiente = "Ninguna";
		ganador = false;
	}

	/**
	 * Cambia la puntuacion del jugador
	 * 
	 * @param nPunt puntos nuevos
	 */
	public void setPuntiacion(int nPunt) {
		puntuacion = nPunt;
	}

	/**
	 * Cambia la sopresa pendiente que tiene el jugador
	 * 
	 * @param nSP Nueva sorpresa pendiente
	 */
	public void setSPendiente(String nSP) {
		sPendiente = nSP;
	}

	/**
	 * quita en una unidad los puntos del jugador
	 */
	public void quitarPunt() {
		if (puntuacion > 0) {
			puntuacion -= 1;
		}
	}

	/**
	 * A�ade en una unidad los puntos del jugador
	 */
	public void subirPunt() {
		puntuacion += 1;
	}

	public int getNumero() {
		return numero;
	}

	public String getSPendiente() {
		return sPendiente;
	}

	public String getNombre() {
		return nombre;
	}

	public int getPuntos() {
		return puntuacion;
	}

	public void setGandor(boolean g) {
		ganador = g;
	}

	public boolean isGanador() {
		return ganador;
	}

}
