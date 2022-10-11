package presentacion; 

import java.awt.Dimension;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;

import aplicacion.*;
import persistencia.SnoopeAG;

/**
 * Interfaz Principal de el Juego SnOOPe
 * @author Juan Esteban Cortes, Andres Felipe Martinez
 *
 */
public class SnoopeGUI extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	//Ventana del juego
	private Snoope juego;
	private static final int ANCHO = 1100;
	private static final int LARGO = 860;
	private static final Dimension DIMENSIONES = new Dimension(ANCHO,LARGO);
	private static final ImageIcon logo = new ImageIcon("res/logo.png");
	private JDialog configuracion, unJugador, dosJugadores, jugadorMaquina;
	private JButton nuevoJuego, cargarJuego, jugador, jugadores, maquina, salir,atras,continuar,continuar2;
	private JComboBox<String> coloresMUJ,coloresMDJ1,coloresMDJ2;
	private JLabel coloresText,nombreText, coloresText1,coloresText2,nombreText1,nombreText2;
	private JTextField nombreMUJ,nombreMDJ1,nombreMDJ2;
	
	
	private static String nJugador1 = "Desconocido";
	private static String nJugador2 = "Desconocido";
	private static String cJugador2 = "Cafe";
	
	/**
	 * Constructor de la interfaz
	 */
	public SnoopeGUI() {
		prepareElementos();
		prepareAcciones();
	}
	
	/**
	 * Prepara todos los elementos de la interfaz grafica y llama a los otros metodos encargados de crear y organizar cada componente en cada ventana
	 */
	private void prepareElementos() {
		this.setTitle("SnOOPe");
		this.setLayout(new BorderLayout());
		this.setSize(DIMENSIONES);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setIconImage(logo.getImage());
		
		//BotonesMenuPrincipal
		nuevoJuego = new JButton("Nuevo Juego");
		nuevoJuego.setBackground(new Color(150,196,2));
		nuevoJuego.setForeground(Color.BLACK);
		nuevoJuego.setFont(new Font("Forte", Font.ITALIC,30));
		cargarJuego = new JButton("Cargar Juego");
		cargarJuego.setBackground(new Color(150,196,2));
		cargarJuego.setForeground(Color.BLACK);
		cargarJuego.setFont(new Font("Forte", Font.ITALIC,30));
		salir = new JButton("Salir");
		salir.setBackground(new Color(150,196,2));
		salir.setForeground(Color.BLACK);
		salir.setFont(new Font("Forte", Font.ITALIC,30));
		
		//BotonesMenuModos
		jugador = new JButton("Un Jugador");
		jugador.setBackground(new Color(150,196,2));
		jugador.setForeground(Color.BLACK);
		jugador.setFont(new Font("Forte", Font.ITALIC,30));
		jugadores = new JButton("Dos Jugadores");
		jugadores.setBackground(new Color(150,196,2));
		jugadores.setForeground(Color.BLACK);
		jugadores.setFont(new Font("Forte", Font.ITALIC,30));
		maquina = new JButton("Jugador vs Maquina");
		maquina.setBackground(new Color(150,196,2));
		maquina.setForeground(Color.BLACK);
		maquina.setFont(new Font("Forte", Font.ITALIC,30));
		atras = new JButton("Atras");
		atras.setBackground(new Color(150,196,2));
		atras.setForeground(Color.BLACK);
		atras.setFont(new Font("Forte", Font.ITALIC,30));
		
		//Botones menu un jugador
		continuar = new JButton("Continuar");
		continuar.setBackground(new Color(150,196,2));
		continuar.setForeground(Color.BLACK);
		continuar.setFont(new Font("Forte", Font.ITALIC,20));
		coloresMUJ = new JComboBox<String>();
		coloresText = new JLabel("Color de la Serpiente:");
		nombreText = new JLabel("Nombre del Jugador:");
		nombreMUJ = new JTextField("Desconocido");
		
		//Botones menu dos jugadores
		
		continuar2 = new JButton("Continuar");
		continuar2.setBackground(new Color(150,196,2));
		continuar2.setForeground(Color.BLACK);
		continuar2.setFont(new Font("Forte", Font.ITALIC,20));
		coloresMDJ1 = new JComboBox<String>();
		coloresMDJ2 = new JComboBox<String>();
		coloresText1 = new JLabel("Color de la Serpiente:");
		nombreText1 = new JLabel("Nombre del Jugador 1:");
		coloresText2 = new JLabel("Color de la Serpiente:");
		nombreText2 = new JLabel("Nombre del Jugador 2:");
		nombreMDJ1 = new JTextField("Desconocido");
		nombreMDJ2 = new JTextField("Desconocido");
		
		
		prepareElementosMenuPrincipal();
		prepareElementosMenuModos();
		prepareElementosMenuUnJugador();
		prepareElementosMenuDosJugadores();
	}
	
	/**
	 * prrpara los elementos del menu principal y organiza  los elementos en la ventana
	 */
	private void prepareElementosMenuPrincipal() {
		
		//Imagen de serpiente en el menu pricipal
		JPanel imagenprincipal = new JPanel();
		imagenprincipal.setLayout(new BorderLayout());
		ImageIcon serpi = new ImageIcon("res/PRINCIPAL.png") ;
		imagenprincipal.add(new JLabel(serpi));
		this.add(imagenprincipal, BorderLayout.CENTER);
		
		//Botones del menu principal
		JPanel botonesMenuP = new JPanel();
		botonesMenuP.setLayout(new FlowLayout());
		botonesMenuP.setBackground(new Color(150,196,2));
		botonesMenuP.add(nuevoJuego);
		botonesMenuP.add(cargarJuego);
		botonesMenuP.add(salir);
		imagenprincipal.add(botonesMenuP, BorderLayout.SOUTH);
	}
	
	/**
	 * prrpara los elementos del menu de modos de juego y organiza  los elementos en la ventana
	 */
	private void prepareElementosMenuModos() {
		
		configuracion = new JDialog(this, "Modos de Juego", false);
		configuracion.setModal(true);
		configuracion.setSize(DIMENSIONES);
		configuracion.setLocationRelativeTo(null);
		configuracion.setResizable(false);
		
		JPanel imagenprincipalM = new JPanel();
		imagenprincipalM.setLayout(new BorderLayout());
		ImageIcon serpi2 = new ImageIcon("res/PRINCIPAL.png") ;
		imagenprincipalM.add(new JLabel(serpi2));
		configuracion.add(imagenprincipalM, BorderLayout.CENTER);
		
		//Botones
		JPanel botonesMenuM = new JPanel();
		botonesMenuM.setLayout(new FlowLayout());
		botonesMenuM.setBackground(new Color(150,196,2));
		botonesMenuM.add(jugador);
		botonesMenuM.add(jugadores);
		botonesMenuM.add(maquina);
		botonesMenuM.add(atras);
		imagenprincipalM.add(botonesMenuM, BorderLayout.SOUTH);
		
	}
	
	/**
	 * prrpara los elementos del menu un jugador y organiza  los elementos en la ventana
	 */
	private void prepareElementosMenuUnJugador(){
		unJugador = new JDialog(this, "Un Jugador", false);
		unJugador.setModal(true);
		unJugador.setSize(300,200);
		unJugador.setLocationRelativeTo(null);
		unJugador.setResizable(false);
		
		JPanel imagenprincipalUJ = new JPanel();
		imagenprincipalUJ.setLayout(new BorderLayout());
		unJugador.add(imagenprincipalUJ);
		
		
		//Botones
		JPanel botonesMenuUJ = new JPanel();
		botonesMenuUJ.setLayout(new FlowLayout());
		botonesMenuUJ.setBackground(new Color(150,196,2));
		botonesMenuUJ.add(continuar);
		imagenprincipalUJ.add(botonesMenuUJ, BorderLayout.SOUTH);
		
		//Elegir colores y nombre
		JPanel  elec = new JPanel();
		elec.setLayout(new GridLayout(2,1));
		elec.setBackground(new Color(150,196,2));
		JPanel  elecColor = new JPanel();
		elecColor.setLayout(new FlowLayout());
		elecColor.setBackground(new Color(150,196,2));
		JPanel  elecNombre = new JPanel();
		elecNombre.setLayout(new FlowLayout());
		elecNombre.setBackground(new Color(150,196,2));
		coloresMUJ.addItem("Morado");
		coloresMUJ.addItem("Negra");
		coloresMUJ.addItem("Verde");
		coloresMUJ.addItem("Cafe");
		coloresMUJ.setPreferredSize(new Dimension(75,18));
		elecColor.add(coloresText);
		elecColor.add(coloresMUJ);
		elecNombre.add(nombreText);
		elecNombre.add(nombreMUJ);
		elec.add(elecNombre);
		elec.add(elecColor);
		imagenprincipalUJ.add(elec,BorderLayout.CENTER);
	}
	
	/**
	 * prrpara los elementos del menu dos jugadores y organiza  los elementos en la ventana
	 */
	private void prepareElementosMenuDosJugadores(){
		dosJugadores = new JDialog(this, "Dos Jugadores", false);
		dosJugadores.setModal(true);
		dosJugadores.setSize(300,200);
		dosJugadores.setLocationRelativeTo(null);
		dosJugadores.setResizable(false);
		
		JPanel imagenprincipalDJ = new JPanel();
		imagenprincipalDJ.setLayout(new BorderLayout());
		dosJugadores.add(imagenprincipalDJ);
		
		
		//Botones
		JPanel botonesMenuDJ = new JPanel();
		botonesMenuDJ.setLayout(new FlowLayout());
		botonesMenuDJ.setBackground(new Color(150,196,2));
		botonesMenuDJ.add(continuar2);
		imagenprincipalDJ.add(botonesMenuDJ, BorderLayout.SOUTH);
		
		//Elegir colores y nombre
		JPanel  elec2 = new JPanel();
		elec2.setLayout(new GridLayout(4,1));
		elec2.setBackground(new Color(150,196,2));
		JPanel  elec2ColorJ1 = new JPanel();
		elec2ColorJ1.setLayout(new FlowLayout());
		elec2ColorJ1.setBackground(new Color(150,196,2));
		JPanel  elec2NombreJ1 = new JPanel();
		elec2NombreJ1.setLayout(new FlowLayout());
		elec2NombreJ1.setBackground(new Color(150,196,2));
		JPanel  elec2ColorJ2 = new JPanel();
		elec2ColorJ2.setLayout(new FlowLayout());
		elec2ColorJ2.setBackground(new Color(150,196,2));
		JPanel  elec2NombreJ2 = new JPanel();
		elec2NombreJ2.setLayout(new FlowLayout());
		elec2NombreJ2.setBackground(new Color(150,196,2));
		coloresMDJ1.addItem("Morado");
		coloresMDJ1.addItem("Negra");
		coloresMDJ1.addItem("Verde");
		coloresMDJ1.addItem("Cafe");
		coloresMDJ1.setPreferredSize(new Dimension(75,18));
		elec2ColorJ1.add(coloresText1);
		elec2ColorJ1.add(coloresMDJ1);
		elec2NombreJ1.add(nombreText1);
		elec2NombreJ1.add(nombreMDJ1);
		coloresMDJ2.addItem("Morado");
		coloresMDJ2.addItem("Negra");
		coloresMDJ2.addItem("Verde");
		coloresMDJ2.addItem("Cafe");
		coloresMDJ2.setPreferredSize(new Dimension(75,18));
		elec2ColorJ2.add(coloresText2);
		elec2ColorJ2.add(coloresMDJ2);
		elec2NombreJ2.add(nombreText2);
		elec2NombreJ2.add(nombreMDJ2);
		elec2.add(elec2NombreJ1);
		elec2.add(elec2ColorJ1);
		elec2.add(elec2NombreJ2);
		elec2.add(elec2ColorJ2);
		imagenprincipalDJ.add(elec2,BorderLayout.CENTER);
	}
	
	/**
	 * Prepara las acciones que tendran los componentes interactuables en cada ventana y les asignara un metodo de accion para comunicarse con la capa de aplicacion
	 */
	private void prepareAcciones() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter () {
			public void windowClosing(WindowEvent event) {
				cerrarAccion();
			}
		} );
		
		nuevoJuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent mouse) {
				nuevoJuegoAccion();
			}
		} );
		
		cargarJuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent mouse) {
				enConstruccionAccion("Cargar Juego");
			}
		} );
		
		salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent even) {
				cerrarAccion();
			}
		});
		
		prepareAccionesMenuModos();
		prepareAccionesMenuUnJugador();
		prepareAccionesMenuDosJugadores();
	}
	
	/**
	 * Prepara las acciones en el menu un jugador
	 */
	private void prepareAccionesMenuUnJugador() {
		unJugador.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		continuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent even) {
				continuarUJAccion();
			}
		});
		
	}
	
	/**
	 * Prepara las acciones en el menu un dos jugadores
	 */
	private void prepareAccionesMenuDosJugadores() {
		dosJugadores.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		continuar2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent even) {
				continuarDJAccion();
			}
		});
	}
	
	/**
	 * Prepara las acciones en el menu de modos de juego
	 */
	private void prepareAccionesMenuModos() {
		
		jugador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent even) {
				unJugadorAccion();
			}
		});
		
		jugadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent even) {
				dosJugadoresAccion();
			}
		});
		
		maquina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent even) {
				enConstruccionAccion("Jugador vs Maquina");
			}
		});
		
		atras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent even) {
				atrasModosAccion();
			}
		});
	}
	
	/**
	 * Metodo de accion el cual emite en mensaje temporal si se ejecuta, se usa para informar al usuario que la acciona querer realizar esta en construccion
	 * @param message mensaje a mostrar al usuario
	 */
	private void enConstruccionAccion(String message) {
		JOptionPane.showMessageDialog(null, "La accion "+ message +" esta en construccion ", "Advertencia",JOptionPane.WARNING_MESSAGE);
		
	}
	
	/**
	 * Metodo de accion encargado de cerrar completamente el Juego
	 */
	private void cerrarAccion() {
		int seleccion = JOptionPane.showConfirmDialog(this, "¿Estas seguro de que quieres salir del mejor juego del mundo?", "Salida", JOptionPane.YES_NO_OPTION);
		if (seleccion == JOptionPane.YES_OPTION) {
			setVisible(false);
			System.exit(0);
			
		}
	
	}
	
	
	/**
	 * Metodo de accion encargado de abrir un juego guardado
	 */
	private void cargarAccion() {
    	JFileChooser choose = new JFileChooser();
		int seleccion = choose.showOpenDialog(null);
		if (seleccion == JFileChooser.APPROVE_OPTION) {
				juego = new Snoope(SnoopeAG.abra(choose.getSelectedFile()),true);
				juego.iniciar();
				
		}
    	
    }
	
	/**
	 * Metodo de accion el cual permite iniciar un juego desde cero en el modo de un jugador
	 */
	private void continuarUJAccion() {
		unJugador.dispose();
		try {
			juego = new Snoope(1,coloresMUJ.getSelectedItem().toString(),cJugador2,nombreMUJ.getText(),nJugador2,true);
			juego.iniciar();
		} catch (SnoopeExcepcion e) {
			JOptionPane.showMessageDialog(null,"Lo sentimos mucho, ocurrio un error. " + e.getMessage());
		}
		
	}
	
	/**
	 * Metodo de accion el cual permite iniciar un juego desde cero en el modo de dos jugadores
	 */
	private void continuarDJAccion() {
		dosJugadores.dispose();
		try {
			juego = new Snoope(2,coloresMDJ1.getSelectedItem().toString(),coloresMDJ2.getSelectedItem().toString(),nombreMDJ1.getText(),nombreMDJ2.getText(),true);
			juego.iniciar();
		} catch (SnoopeExcepcion e) {
			JOptionPane.showMessageDialog(null,"Lo sentimos mucho, ocurrio un error. " + e.getMessage());
		}
		
	}
	
	/**
	 * Metodo de accion el cual abre una nuava ventana y muestra el menu de un jugador
	 */
	private void unJugadorAccion() {
		unJugador.setVisible(true);
		atrasModosAccion();
	}
	
	/**
	 * Metodo de accion el cual abre una nuava ventana y muestra el menu de dos jugadores
	 */
	private void dosJugadoresAccion() {
		dosJugadores.setVisible(true);
		atrasModosAccion();
	}
	
	/**
	 * Metodo de accion el cual abre una nuava ventana y muestra el menu de modos de juego
	 */
	private void nuevoJuegoAccion() {
		configuracion.setVisible(true);
	}	
	
	/**
	 * Metodo de accion el cual cierra la ventana del menu de modos de juego y permite volver al menu principal
	 */
	private void atrasModosAccion(){
		configuracion.dispose();
	}
	
	
	public static void main(String args[]) {
		SnoopeGUI snoopG = new SnoopeGUI();
		snoopG.setVisible(true);
	}
	
}
