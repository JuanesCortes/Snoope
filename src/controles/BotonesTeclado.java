package controles;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.Serializable;

public final class BotonesTeclado implements KeyListener,Serializable{
	

	private static final long serialVersionUID = 1L;
	private final static int numeroTeclas = 120;
	private final boolean[] teclas = new boolean[numeroTeclas];
	
	public boolean arribaS1;
	public boolean abajoS1;
	public boolean izquierdaS1;
	public boolean derechaS1;
	public boolean arribaS2;
	public boolean abajoS2;
	public boolean izquierdaS2;
	public boolean derechaS2;
	public boolean actSorpresaS1;
	public boolean actSorpresaS2;
	public boolean pausa;
	public boolean continuar;
	
	/**
	 * 
	 */
	public void actualizar() {
		
		arribaS1 = teclas[KeyEvent.VK_W];
		abajoS1 = teclas[KeyEvent.VK_S];
		izquierdaS1 = teclas[KeyEvent.VK_A];
		derechaS1 = teclas[KeyEvent.VK_D];
		
		arribaS2 = teclas[KeyEvent.VK_UP];
		abajoS2 = teclas[KeyEvent.VK_DOWN];
		izquierdaS2 = teclas[KeyEvent.VK_LEFT];
		derechaS2 = teclas[KeyEvent.VK_RIGHT];

		actSorpresaS1 = teclas[KeyEvent.VK_SPACE];
		actSorpresaS2 = teclas[KeyEvent.VK_CONTROL];
		
		pausa = teclas[KeyEvent.VK_ESCAPE];
		continuar = teclas[KeyEvent.VK_ENTER];
	}
	
	/**
	 * 
	 */
	public void keyPressed(KeyEvent e) {
		teclas[e.getKeyCode()] = true;
		
	}
	
	/**
	 * 
	 */
	public void keyReleased(KeyEvent e) {
		teclas[e.getKeyCode()] = false;
	}


	public void keyTyped(KeyEvent e) {
	}

}
