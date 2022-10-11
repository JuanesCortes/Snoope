package persistencia;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JOptionPane;

import aplicacion.Juego;

public abstract class SnoopeAG {
	
	/**
     * Guarda en un archivo 
     * @param file el archivo donde se guardara
     */
    public static void guardar(File file, Juego juego){
		ObjectOutputStream out;
		try {
			out = new ObjectOutputStream(new FileOutputStream(file));
			out.writeObject(juego);
			out.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null,"Error al guardar","Adevertencia",JOptionPane.WARNING_MESSAGE);
		}
    }
	
	/**
     * Abre de un archivo
     * @param file 
     */
    public static Juego abra(File file){
    	Juego newSnoope;
		ObjectInputStream in;
		try {
			in = new ObjectInputStream(new FileInputStream(file));
			newSnoope = (Juego)in.readObject();
			in.close();
		}catch (ClassNotFoundException e) {
			newSnoope = null;
			JOptionPane.showMessageDialog(null,"Clase no encontrada","Adevertencia",JOptionPane.WARNING_MESSAGE);
		} catch (IOException e) {
			newSnoope = null;
			JOptionPane.showMessageDialog(null,"Error al abrir","Adevertencia",JOptionPane.WARNING_MESSAGE);
		}
		return newSnoope;
    }
}
