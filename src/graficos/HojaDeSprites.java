package graficos;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

public class HojaDeSprites implements Serializable{

	private static final long serialVersionUID = 1L;
	private final int ancho;
	private final int largo;
	public final int[] pixeles;
	
	//HoasSprites
	public static HojaDeSprites fuente = new HojaDeSprites("res/sprites.png",320,320);
	
	
	/**
	 * 
	 * @param ruta
	 * @param anchos
	 * @param alto
	 */
	public HojaDeSprites(final String ruta, final int anchos, final int alto){
		this.ancho = anchos;
		this.largo = alto;
		 
		pixeles = new int[ancho * largo];
		 
		BufferedImage imagen;
		try {
			imagen = ImageIO.read(new File(ruta));
			 
			imagen.getRGB(0, 0, ancho, largo, pixeles, 0, ancho);
		}catch (IOException e) {
			e.printStackTrace();
		}  
	}
	
	public int getAncho() {
		return ancho;
	}

}