package test;

import static org.junit.Assert.*;
import org.junit.Test;

import aplicacion.Division;
import aplicacion.FlechaMasVelocidad;
import aplicacion.Fruta;
import aplicacion.FrutaArc;
import aplicacion.Item;
import aplicacion.Juego;
import aplicacion.Jugador;
import aplicacion.SnoopeExcepcion;
import controles.BotonesTeclado;

public class SnoopeTest {
	public SnoopeTest() {
		
	}
	
	@Test
	public void deberiaCrearSerpienteVivaUnJugador() throws SnoopeExcepcion {
		Juego juego = new Juego(2, "Morado", "Negra","desc", "desc",1024,768,new BotonesTeclado());
		assertTrue(juego.getSerpiente1().isVIvo());
	}
	
	@Test
	public void deberiaCrearSerpientesVivasDosJugadores() throws SnoopeExcepcion {
		Juego juego = new Juego(2, "Morado", "Negra","desc", "desc",1024,768,new BotonesTeclado());
		assertTrue(juego.getSerpiente1().isVIvo());
		assertTrue(juego.getSerpiente2().isVIvo());
	}
	@Test
	public void deberianMorirLasSerpientes() throws SnoopeExcepcion {
		Juego juego = new Juego(2, "Morado", "Negra","desc", "desc",1024,768,new BotonesTeclado());
		assertTrue(juego.getSerpiente1().isVIvo());
		assertTrue(juego.getSerpiente2().isVIvo());
		
		juego.getSerpiente1().actualizar("u");
		juego.getSerpiente2().actualizar("d");
		
		juego.moverSerpientes();
		juego.moverSerpientes();
		juego.moverSerpientes();
		
		assertFalse(juego.getSerpiente1().isVIvo());
		assertFalse(juego.getSerpiente2().isVIvo());
	}
	@Test
	public void deberianMoverseLasSerpientes() throws SnoopeExcepcion {
		Juego juego = new Juego(2, "Morado", "Negra","desc", "desc",1024,768,new BotonesTeclado());
		assertEquals(juego.getSerpiente1().getPosX(),127);
		assertEquals(juego.getSerpiente1().getPosY(),95);
		assertEquals(juego.getSerpiente2().getPosX(),863);
		assertEquals(juego.getSerpiente2().getPosY(),639);
		
		
		juego.moverSerpientes();
		juego.moverSerpientes();
		juego.moverSerpientes();
		
		assertEquals(juego.getSerpiente1().getPosX(),133);
		assertEquals(juego.getSerpiente1().getPosY(),95);
		assertEquals(juego.getSerpiente2().getPosX(),857);
		assertEquals(juego.getSerpiente2().getPosY(),639);
	}
	@Test
	public void lasSerpientesDeberianTenerSuSpriteAdecuado() throws SnoopeExcepcion {
		Juego juego = new Juego(2, "Morado", "Negra","desc", "desc",1024,768,new BotonesTeclado());
		assertEquals(juego.getSerpiente1().getSprite().getName(),"MSerpienteV R");
		assertEquals(juego.getSerpiente2().getSprite().getName(),"MSerpienteV L");
		
		juego.getSerpiente1().actualizar("d");
		juego.getSerpiente2().actualizar("u");
		
		
		juego.moverSerpientes();
		juego.moverSerpientes();
		juego.moverSerpientes();
		juego.moverSerpientes();
		juego.moverSerpientes();
		juego.moverSerpientes();
		juego.moverSerpientes();
		juego.moverSerpientes();
		juego.moverSerpientes();
		juego.moverSerpientes();
		juego.moverSerpientes();
		
		assertEquals(juego.getSerpiente1().getSprite().getName(),"MSerpienteV D");
		assertEquals(juego.getSerpiente2().getSprite().getName(),"NSerpienteV U");
		
	}
	
	@Test
	public void deberiaGenerarUnAlimentoEnLaPosicionIndicada() throws SnoopeExcepcion {
		Item frut = new Fruta(400,400,"Morado");
		assertEquals(frut.getPosX(),400);
		assertEquals(frut.getPosY(),400);
		Item frut2 = new FrutaArc(600,200);
		assertEquals(frut2.getPosX(),600);
		assertEquals(frut2.getPosY(),200);
	}
	
	@Test
	public void NoDeberiaGenerarAlimentosEnPosicionesIncorrectas() {
		Item frut;
		try {
			frut = new Fruta(100,900,"Morado");
		} catch (SnoopeExcepcion e) {
			assertEquals(e.getMessage(),SnoopeExcepcion.ITEM_POS);
		}
		
	}
	@Test
	public void deberiaGenerarUnaSorpresEnlaPosicionIndicada() throws SnoopeExcepcion {
		Item div = new Division(400,400);
		assertEquals(div.getPosX(),400);
		assertEquals(div.getPosY(),400);
		Item flec = new FlechaMasVelocidad(600,200);
		assertEquals(flec.getPosX(),600);
		assertEquals(flec.getPosY(),200);
	}
	
	@Test
	public void NoDeberiaGenerarUnaSorpresaEnPosicionesIncorrectas() {
		Item flec;
		try {
			flec = new FlechaMasVelocidad(600,200);
		} catch (SnoopeExcepcion e) {
			assertEquals(e.getMessage(),SnoopeExcepcion.ITEM_POS);
		}
		
	}
	
	@Test 
	public void deberiaCrearJugadoresYConsultarSusDatosCorrectamente() {
		Jugador jug = new Jugador("Juan",1);
		assertEquals(jug.getNombre(),"Juan");
		assertEquals(jug.getPuntos(),0);
		assertFalse(jug.isGanador());
		jug.setPuntiacion(10);
		assertEquals(jug.getPuntos(),10);
		jug.subirPunt();
		jug.subirPunt();
		jug.quitarPunt();
		jug.setGandor(true);
		assertEquals(jug.getPuntos(),11);
		assertTrue(jug.isGanador());
	}
}
