package presentacion;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.Serializable;

import javax.swing.*;

import aplicacion.Juego;
import aplicacion.Serpiente;
import aplicacion.SnoopeExcepcion;
import controles.BotonesTeclado;
import persistencia.SnoopeAG;

/**
 * Clase que muestra en panatalla a travez de un Canvas los graficos del juego (Sprites) a demas de tener el Thread responsable de actualizarlos y mostra los Sprites constantemente 60 pos segundo
 * @author Juan Esteban Cortes
 *
 */
public class Snoope extends Canvas implements Runnable, Serializable{

	private static final long serialVersionUID = 1L;
	
	private static int fps;
	private static int aps;
	
	private static final String NOMBRE = "SnOOPe";
	
	private static final int ANCHO = 1024;
	private static final int LARGO = 768;
	private static final Dimension DIMENSIONES = new Dimension(ANCHO,LARGO);
	private boolean isVisible;
	
	private static volatile boolean enEjecucion = false;
	
	private static JFrame vista;
	private static JButton guardar,pausar,cont;
	private static boolean isPausado;
	
	private static Thread thread;
	private static BotonesTeclado controles;
	
	private static BufferedImage Images = new BufferedImage(ANCHO, LARGO, BufferedImage.TYPE_INT_RGB);
	private static int[] pixeles = ((DataBufferInt)Images.getRaster().getDataBuffer()).getData();

	
	private static Juego game;
	
	/**
	 * Constructor de la clase Snoope
	 * 
	 * @param players cantidad de jugadores
	 * @param color1 color del jugador 1
	 * @param color2 color del jugador 2
	 * @param jugador1 nombre del jugador 1
	 * @param jugador2 nombre del jugador 2
 	 * @param isVisible booleano encargado de llevar la visivilidad del canvas
	 */
	public Snoope(final int players,String color1, String color2,String jugador1,String jugador2,boolean isVisible) throws SnoopeExcepcion {
		this.isVisible = isVisible;
		isPausado = false;
		controles = new BotonesTeclado();
		addKeyListener(controles);

		game = new Juego(players,color1,color2, jugador1,jugador2,ANCHO,LARGO,controles);
		
		prepareElementos();
		prepareAcciones();
	}
	
	public Snoope(Juego nGame,boolean isVisible) {
		this.isVisible = isVisible;
		isPausado = false;
		controles = new BotonesTeclado();
		addKeyListener(controles);

		game = nGame;
		
		prepareElementos();
		prepareAcciones();
	}
	/**
	 * prepara los elementos del canvas
	 */
	public void prepareElementos(){
		vista = new JFrame(NOMBRE);
		vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		vista.setLayout(new BorderLayout());
		vista.add(this,BorderLayout.CENTER);
		vista.setSize(DIMENSIONES);
		vista.setResizable(false);
		vista.setLocationRelativeTo(null);
		vista.setVisible(isVisible);
		guardar = new JButton("Guardar");
		guardar.setBackground(new Color(109,181,86));
		guardar.setForeground(Color.BLACK);
		guardar.setFont(new Font("Forte", Font.ITALIC,20));
		cont = new JButton("Continuar");
		cont.setBackground(new Color(109,181,86));
		cont.setForeground(Color.BLACK);
		cont.setFont(new Font("Forte", Font.ITALIC,20));
		pausar = new JButton("Pausar");
		pausar.setBackground(new Color(109,181,86));
		pausar.setForeground(Color.BLACK);
		pausar.setFont(new Font("Forte", Font.ITALIC,20));
		JPanel botones = new JPanel();
		botones.setLayout(new FlowLayout());
		botones.setBackground(new Color(109,181,86));
		botones.add(guardar);
		botones.add(pausar);
		botones.add(cont);
		vista.add(botones, BorderLayout.SOUTH);
	}
	/**
	 * prepara las acciones que se tiene en el canvas 
	 */
	private void prepareAcciones() {
		vista.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		vista.addWindowListener(new WindowAdapter () {
			public void windowClosing(WindowEvent event) {
				cerrarAccion();
			}
		} );
		
		pausar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent even) {
				pausarAccion();
			}
		});
		
		cont.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent even) {
				continuarAccion();
			}
		});
		
		guardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent even) {
				accionGuardar();
			}
		});
	}
	
	/**
	 * Guarda el estado actual del juego en un archivo
	 */
	public void accionGuardar() {
		pausarAccion();
		JFileChooser choose = new JFileChooser();
		int seleccion = choose.showSaveDialog(null);
		if (seleccion == JFileChooser.APPROVE_OPTION) {
			SnoopeAG.guardar(choose.getSelectedFile(),game);
		}
	}
	
	/**
	 * Metodo de accion que inicia el thread y permite despausar el juego
	 */
	private void continuarAccion() {
		if(isPausado) {
			iniciar();
			isPausado = false;
		}
		requestFocus();
		
	}
	
	/**
	 * Metodo de accion que detiene el thread y permite pausar el juego
	 */
	private void pausarAccion(){
		if(!isPausado) {
			detener();
			isPausado= true;
		}
		requestFocus();
		
	}
	
	/**
	 * cierra el canvas y detiene el Thread si se confirma la accion
	 */
	private void cerrarAccion() {
		detener();
		int seleccion = JOptionPane.showConfirmDialog(this, "¿Estas seguro de que quieres salir de la partida?", "Salida", JOptionPane.YES_NO_OPTION);
		if (seleccion == JOptionPane.YES_OPTION) {
			mostrarGanadores();
			setVisible(false);
			vista.dispose();
			
		}else {
			iniciar();
		}
		
	}
	
	/**
	 * Muestar un mensaje con los gandores y su puntuacion luego de cerrar el juego, solo se muestran si el juego acabo correctamente y no fue interrumpido
	 */
	private void mostrarGanadores(){
		if (game.isEnded() && game.getPlayers() == 1) {
			JOptionPane.showMessageDialog(null,"Fin del Juego. "+game.getJugador1()+" obtuviste una puntuacion de: "+game.getPJugador1() );
		}else if(game.isEnded() && game.getPlayers() == 2) {
			JOptionPane.showMessageDialog(null,"Fin del Juego. Ganador: "+game.getGanador()+" Puntuacion: "+game.getPuntosGanador() );
		}
	}
	
	/**
	 * actualiza el juego
	 * @throws SnoopeExcepcion
	 */
	public void actualizar() throws SnoopeExcepcion {
		aps++;
		game.actualizar();
	}
	
	/**
	 * Muestra en pantalla todos los graficos del juego
	 */
	public void mostrar() {
		fps++;
		game.crearTablero();
		
		BufferStrategy estrategia = getBufferStrategy();
		
		if (estrategia == null) {
			createBufferStrategy(3);
			return;
		}
		
		game.mostrar();
		
		System.arraycopy(game.getTablero().pixeles, 0, pixeles, 0, pixeles.length);
		
		Graphics g = estrategia.getDrawGraphics();
		g.drawImage(Images, 0, 0, getWidth(), getHeight(), null);
		if(game.getPlayers() == 1) {
			g.drawString("Puntos: "+game.getPJugador1(), 95, 27);
			g.drawString("Nombre: "+game.getJugador1(), 95, 15);
			g.drawString("Sorpresa Pendiente: "+game.getSPJugador1(), 95, 40);
		}else if(game.getPlayers() == 2) {
			g.drawString("Puntos: "+game.getPJugador1(), 95, 27);
			g.drawString("Nombre: "+game.getJugador1(), 95, 15);
			g.drawString("Sorpresa Pendiente: "+game.getSPJugador1(), 95, 40);
			g.drawString("Puntos: "+game.getPJugador2(), 600, 27);
			g.drawString("Nombre: "+game.getJugador2(), 600, 15);
			g.drawString("Sorpresa Pendiente: "+game.getSPJugador2(), 600, 40);
		}
		g.dispose();
		estrategia.show();
	}
	
	/**
	 * hace isible el canvas
	 */
	public void makeVisible() {
		isVisible = true;
		vista.setVisible(isVisible);
	}
	
	/**Hace invisible el canvas
	 * 
	 */
	public void makeInvisible() {
		isVisible = false;
		vista.setVisible(isVisible);
	}
	
	public Serpiente getSerpiente1() {
		return game.getSerpiente1();
	}
	
	public Serpiente getSerpiente2() {
		return game.getSerpiente1();
	}
	
	public Juego getGame() {
		return game;
	}
	
	
	/**
	 * Inicia el Thread 
	 */
	public synchronized void iniciar() {
		enEjecucion = true;
		
		thread = new Thread(this, "Graficos");
		thread.start();
	}
	
	/**
	 * Detiene el Thread
	 */
	public synchronized void detener() {
		enEjecucion = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo que se ejecuta el iniciar el Thread
	 */
	public void run() {
		final byte APS_OBJETIVO = 60;
		final double NS_POR_ACTUALIZACION = 1000000000 / APS_OBJETIVO;
		
		long actualizacion = System.nanoTime();
		long refCont = System.nanoTime();
		
		double  tTranscurrido;
		double delta = 0;
		
		requestFocus();
		
		//Bucle responsable de la ejecucion constante del juego
		while (enEjecucion) {
			
			final long inicioBucle = System.nanoTime();
			tTranscurrido = inicioBucle - actualizacion;
			actualizacion = inicioBucle;
			
			delta += tTranscurrido / NS_POR_ACTUALIZACION;
			while (delta >= 1) {
				try {
					actualizar();
				} catch (SnoopeExcepcion e) {
					JOptionPane.showMessageDialog(null,"Lo sentimos mucho, ocurrio un error. " + e.getMessage());
				}
				delta--;
			}
			
			mostrar();
			
			if(System.nanoTime() - refCont > 1000000000) {
				vista.setTitle(NOMBRE + " || aps: "+ aps + "|| fps: " + fps);
				aps = 0;
				fps = 0;
				refCont = System.nanoTime();
			}
			
			
			//Implementacion realizada gracias el Canal de Youtube Jav Dev One  
				
		}
	}

}
