package graficos;

import java.io.Serializable;

public final class Sprite implements Serializable{

	private static final long serialVersionUID = 1L;
	private final int lado;
	private int posX;
	private int posY;
	private String name;
	public int[] pixeles;
	private HojaDeSprites hojaS;
	
	//Serpiente morada
	public static Sprite MSERPIENTEVD = new Sprite(32, 2, 1, HojaDeSprites.fuente, "MSerpienteV D");
	public static Sprite MSERPIENTEV2D = new Sprite(32, 3, 1, HojaDeSprites.fuente, "MSerpienteV2 D");
	public static Sprite MSERPIENTEVR = new Sprite(32, 8, 1, HojaDeSprites.fuente, "MSerpienteV R");
	public static Sprite MSERPIENTEV2R = new Sprite(32, 9, 1, HojaDeSprites.fuente, "MSerpienteV2 R");
	public static Sprite MSERPIENTEVL = new Sprite(32, 6, 1, HojaDeSprites.fuente, "MSerpienteV L");
	public static Sprite MSERPIENTEV2L = new Sprite(32, 7, 1, HojaDeSprites.fuente, "MSerpienteV2 L");
	public static Sprite MSERPIENTEVU = new Sprite(32, 4, 1, HojaDeSprites.fuente, "MSerpienteV U");
	public static Sprite MSERPIENTEV2U = new Sprite(32, 5, 1, HojaDeSprites.fuente, "MSerpienteV2 U");
	public static Sprite MSERPIENTEM = new Sprite(32,2,5,HojaDeSprites.fuente,"MSerpienteM");
	
	//Serpiente Verde
	public static Sprite VSERPIENTEVD = new Sprite(32, 4, 0, HojaDeSprites.fuente, "VSerpienteV D");
	public static Sprite VSERPIENTEV2D = new Sprite(32, 5, 0, HojaDeSprites.fuente, "VSerpienteV2 D");
	public static Sprite VSERPIENTEVR = new Sprite(32, 0, 1, HojaDeSprites.fuente, "VSerpienteV R");
	public static Sprite VSERPIENTEV2R = new Sprite(32, 1, 1, HojaDeSprites.fuente, "VSerpienteV2 R");
	public static Sprite VSERPIENTEVL = new Sprite(32, 8, 0, HojaDeSprites.fuente, "VSerpienteV L");
	public static Sprite VSERPIENTEV2L = new Sprite(32, 9, 0, HojaDeSprites.fuente, "VSerpienteV2 L");
	public static Sprite VSERPIENTEVU = new Sprite(32, 6, 0, HojaDeSprites.fuente, "VSerpienteV U");
	public static Sprite VSERPIENTEV2U = new Sprite(32, 7, 0, HojaDeSprites.fuente, "VSerpienteV2 U");
	public static Sprite VSERPIENTEM = new Sprite(32,3,5,HojaDeSprites.fuente,"VSerpienteM");
	
	//Serpiente Cafe
	public static Sprite CSERPIENTEVD = new Sprite(32, 0, 2, HojaDeSprites.fuente, "CSerpienteV D");
	public static Sprite CSERPIENTEV2D = new Sprite(32, 1, 2, HojaDeSprites.fuente, "CSerpienteV2 D");
	public static Sprite CSERPIENTEVR = new Sprite(32, 6, 2, HojaDeSprites.fuente, "CSerpienteV R");
	public static Sprite CSERPIENTEV2R = new Sprite(32, 7, 2, HojaDeSprites.fuente, "CSerpienteV2 R");
	public static Sprite CSERPIENTEVL = new Sprite(32, 4, 2, HojaDeSprites.fuente, "CSerpienteV L");
	public static Sprite CSERPIENTEV2L = new Sprite(32, 5, 2, HojaDeSprites.fuente, "CSerpienteV2 L");
	public static Sprite CSERPIENTEVU = new Sprite(32, 2, 2, HojaDeSprites.fuente, "CSerpienteV U");
	public static Sprite CSERPIENTEV2U = new Sprite(32, 3, 2, HojaDeSprites.fuente, "CSerpienteV2 U");
	public static Sprite CSERPIENTEM = new Sprite(32,1,5,HojaDeSprites.fuente,"CSerpienteM");
	
	//Serpiente Negra
	public static Sprite NSERPIENTEVD = new Sprite(32, 8, 2, HojaDeSprites.fuente, "NSerpienteV D");
	public static Sprite NSERPIENTEV2D = new Sprite(32, 9, 2, HojaDeSprites.fuente, "NSerpienteV2 D");
	public static Sprite NSERPIENTEVR = new Sprite(32, 4, 3, HojaDeSprites.fuente, "NSerpienteV R");
	public static Sprite NSERPIENTEV2R = new Sprite(32, 5, 3, HojaDeSprites.fuente, "NSerpienteV2 R");
	public static Sprite NSERPIENTEVL = new Sprite(32, 2, 3, HojaDeSprites.fuente, "NSerpienteV L");
	public static Sprite NSERPIENTEV2L = new Sprite(32, 3, 3, HojaDeSprites.fuente, "NSerpienteV2 L");
	public static Sprite NSERPIENTEVU = new Sprite(32, 0, 3, HojaDeSprites.fuente, "NSerpienteV U");
	public static Sprite NSERPIENTEV2U = new Sprite(32, 1, 3, HojaDeSprites.fuente, "NSerpienteV2 U");
	public static Sprite NSERPIENTEM = new Sprite(32,0,5,HojaDeSprites.fuente,"NSerpienteM");
	
	//Colas
	public static Sprite NCOLA = new Sprite(32, 6, 3, HojaDeSprites.fuente, "COLA N");
	public static Sprite MCOLA = new Sprite(32, 8, 3, HojaDeSprites.fuente, "COLA M");
	public static Sprite VCOLA = new Sprite(32, 9, 3, HojaDeSprites.fuente, "COLA V");
	public static Sprite CCOLA = new Sprite(32, 7, 3, HojaDeSprites.fuente, "COLA C");
	
	//Mapa
	public static Sprite MURO = new Sprite(32,1,0,HojaDeSprites.fuente,"Muro");
	public static Sprite HIERBA1 = new Sprite(32,0,0,HojaDeSprites.fuente,"Hierba1");
	public static Sprite HIERBA2 = new Sprite(32,2,0,HojaDeSprites.fuente,"Hierba2");
	public static Sprite HIERBA3 = new Sprite(32,3,0,HojaDeSprites.fuente,"Hierba3");
	
	//Alimentos
	public static Sprite FRUTAV = new Sprite(32,0,4,HojaDeSprites.fuente,"Fruta V");
	public static Sprite FRUTAM = new Sprite(32,4,5,HojaDeSprites.fuente,"Fruta V");
	public static Sprite FRUTAC = new Sprite(32,5,5,HojaDeSprites.fuente,"Fruta V");
	public static Sprite FRUTAN = new Sprite(32,6,5,HojaDeSprites.fuente,"Fruta V");
	public static Sprite FRUTAARC = new Sprite(32,1,4,HojaDeSprites.fuente,"Fruta Arc");
	public static Sprite VENENO = new Sprite(32,3,4,HojaDeSprites.fuente,"Veneno");
	public static Sprite DUlCE = new Sprite(32,4,4,HojaDeSprites.fuente,"Dulce");
	
	//Sorpresas
	public static Sprite CSORPRESA = new Sprite(32,2,4,HojaDeSprites.fuente,"Caja Sorpresa");
	public static Sprite FVELOCIDADMAS = new Sprite(32,5,4,HojaDeSprites.fuente,"Flecha +");
	public static Sprite FVELOCIDADMEN = new Sprite(32,6,4,HojaDeSprites.fuente,"Flecha -");
	public static Sprite ESTRELLA = new Sprite(32,7,4,HojaDeSprites.fuente,"Estrella");
	public static Sprite DIVISION = new Sprite(32,8,4,HojaDeSprites.fuente,"Division");
	public static Sprite MTRAMPA = new Sprite(32,9,4,HojaDeSprites.fuente,"Trampa");
	public static Sprite LUPA = new Sprite(32,7,5,HojaDeSprites.fuente,"Lupa");
	
	/**
	 *Crea un sprite tomandolo de una imagen en el directorio que llega por parametro
	 * 
	 * @param lado tamaño del sprite nxn
	 * @param columna columna donde se encuentra el Sprite en la imagen
	 * @param fila fila donde se encuentra el Sprite en la imagen
	 * @param hoja clase que contiene la hoja de sprites leida de la imagen
	 * @param name Nombre del sprite
	 */
	public Sprite(final int lado, final int columna, final int fila, final HojaDeSprites hoja, String name){
		this.lado = lado;
		pixeles = new int[lado * lado];
		this.posX= columna * lado;
		this.posY = fila * lado;
		this.hojaS = hoja;
		this.name = name;
		
		for (int y=0; y<lado; y++) {
			for (int x=0; x<lado; x++) {
				pixeles[x + y * lado] = hojaS.pixeles[(x+this.posX) + (y+this.posY) * hojaS.getAncho()];
			}
		}
	}
	
	public int getLado() {
		return lado;
	}
	public String getName() {
		return name;
	}
}
