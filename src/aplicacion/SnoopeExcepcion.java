package aplicacion;

public class SnoopeExcepcion extends Exception {

	private static final long serialVersionUID = 1L;

	public static final String NO_SERPIENTE_JUGADOR = "No existe serpiente para el jugador";
	public static final String CANTIDAD_DE_JUGADORES = "La cantidad de jugadores incorrecta";
	public static final String NOMBRE_JUGADOR = "El nombre del Jugador es invalido";
	public static final String NO_CONTROLES = "No se encontraron controles";
	public static final String ITEM_POS = "Item generado en una posicion erronea";

	public SnoopeExcepcion(String Message) {
		super(Message);
	}
}
