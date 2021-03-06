package fiuba.algo3.test;

import static org.junit.Assert.*;

import java.util.EnumMap;

import org.junit.Test;

import fiuba.algo3.modelo.Algomon;
import fiuba.algo3.modelo.Salud;
import fiuba.algo3.modelo.ataques.Ataque;
import fiuba.algo3.modelo.ataques.AtaqueCanto;
import fiuba.algo3.modelo.ataques.AtaqueFogonazo;
import fiuba.algo3.modelo.ataques.AtaqueSimple;
import fiuba.algo3.modelo.ataques.NombreDelAtaque;
import fiuba.algo3.modelo.elementos.Elemento;
import fiuba.algo3.modelo.elementos.Restaurador;
import fiuba.algo3.modelo.elementos.Vitamina;
import fiuba.algo3.modelo.excepciones.AtacarDormidoNoPuedeRealizarseException;
import fiuba.algo3.modelo.excepciones.RestauradorAgotadoException;
import fiuba.algo3.modelo.tiposDeAlgomon.TipoAgua;
import fiuba.algo3.modelo.tiposDeAlgomon.TipoFuego;
import fiuba.algo3.modelo.tiposDeAlgomon.TipoNormal;

public class RestauradorTest {

	@Test
	public void testUsarRestauradorEnAlgomonBajoEfectoQuemadoPuedeAtacar() {
		
		//Se crea un algomon personalizado con fogonazo.
		Ataque fogonazo = new AtaqueFogonazo(new AtaqueSimple(new TipoFuego(), 2, 4) );
		EnumMap<NombreDelAtaque, Ataque> ataques = new EnumMap<NombreDelAtaque, Ataque >(NombreDelAtaque.class);
		ataques.put(NombreDelAtaque.FOGONAZO,fogonazo);
				
		Salud saludCharizard= new Salud(400);
		Algomon charizard = new Algomon("Charizard", new TipoFuego(), ataques , saludCharizard);
		
		// Se crea un algomon personalizado.
		Ataque ataqueRapido = new AtaqueSimple(new TipoNormal(), 10, 16 );
		EnumMap<NombreDelAtaque, Ataque> ataquesNormal = new EnumMap<NombreDelAtaque, Ataque >(NombreDelAtaque.class);
		ataquesNormal.put(NombreDelAtaque.ATAQUE_RAPIDO, ataqueRapido);
		
		
		Algomon blastoise = new Algomon("Blastoise", new TipoAgua(), ataquesNormal, new Salud(400));
		
		charizard.atacar(blastoise, NombreDelAtaque.FOGONAZO);
		assertEquals(399,blastoise.salud(),0.001D);
		
		// blastoise se bajo el efecto quemado.
		blastoise.atacar(charizard, NombreDelAtaque.ATAQUE_RAPIDO);
		assertEquals(359,blastoise.salud(),0.001D);
		
		Elemento restaurador = new Restaurador();
		
		restaurador.aplicarElemento(blastoise);
		assertEquals(319,blastoise.salud(),0.001D);
		
		// blastoise se quema por ultima vez al aplicarse el restaurador.
		blastoise.atacar(charizard, NombreDelAtaque.ATAQUE_RAPIDO);
		assertEquals(319,blastoise.salud(),0.001D);
		
		// blastoise que no esta bajo el efecto quemado.
		blastoise.atacar(charizard, NombreDelAtaque.ATAQUE_RAPIDO);
		assertEquals(319,blastoise.salud(),0.001D);
			
	}
	
	@Test
	public void testUsarRestauradorEnAlgomonBajoEfectoDomidoPuedeAtacar() {
		
		//Se crea un algomon personalizado con fogonazo.
		Ataque canto = new AtaqueCanto(new AtaqueSimple(new TipoNormal(), 0, 4));
		EnumMap<NombreDelAtaque, Ataque> ataques = new EnumMap<NombreDelAtaque, Ataque >(NombreDelAtaque.class);
		ataques.put(NombreDelAtaque.CANTO,canto);
		Algomon chansey = new Algomon("Chansey", new TipoFuego(), ataques , new Salud(400));
		
		// Se crea un algomon personalizado.
		Ataque ataqueRapido = new AtaqueSimple(new TipoNormal(), 10 , 16 );
		EnumMap<NombreDelAtaque, Ataque> ataquesNormal = new EnumMap<NombreDelAtaque, Ataque >(NombreDelAtaque.class);
		ataquesNormal.put(NombreDelAtaque.ATAQUE_RAPIDO, ataqueRapido);
		Algomon blastoise = new Algomon("Blastoise", new TipoAgua(), ataquesNormal, new Salud(400));
		
		chansey.atacar(blastoise, NombreDelAtaque.CANTO);
		
		try{
			
			blastoise.atacar(chansey, NombreDelAtaque.ATAQUE_RAPIDO);
		
		}catch(AtacarDormidoNoPuedeRealizarseException exception){}
		
		try{
			
			blastoise.atacar(chansey, NombreDelAtaque.ATAQUE_RAPIDO);
		
		}catch(AtacarDormidoNoPuedeRealizarseException exception){}
		
		try{
			
			blastoise.atacar(chansey, NombreDelAtaque.ATAQUE_RAPIDO);
		
		}catch(AtacarDormidoNoPuedeRealizarseException exception){}
		assertEquals(400,chansey.salud(),0.001D);
		
		Elemento restaurador = new Restaurador();
		
		restaurador.aplicarElemento(blastoise);
		
		blastoise.atacar(chansey, NombreDelAtaque.ATAQUE_RAPIDO);
		
		assertEquals(390,chansey.salud(),0.001D);
			
	}
	
	@Test(expected = RestauradorAgotadoException.class)
	public void testAlgomonAgotoLasPosionesYnoPuedeUtilizarMasPosiones(){
		
		// Se crea un algomon personalizado.
	    int potenciaAtaqueRapido = 25;
		int cantidadMaximaDeAtaquesAtaqueRapido = 16;
		Ataque ataqueRapido = new AtaqueSimple(new TipoNormal(), potenciaAtaqueRapido , cantidadMaximaDeAtaquesAtaqueRapido );
		EnumMap<NombreDelAtaque, Ataque> ataquesNormal = new EnumMap<NombreDelAtaque, Ataque >(NombreDelAtaque.class);
		ataquesNormal.put(NombreDelAtaque.ATAQUE_RAPIDO, ataqueRapido);
		Salud saludRaticate = new Salud(300);
		Algomon raticate = new Algomon("Raticate", new TipoNormal(), ataquesNormal, saludRaticate);
				
		Elemento restaurador = new Restaurador();
			
		restaurador.aplicarElemento(raticate);
		restaurador.aplicarElemento(raticate);
		restaurador.aplicarElemento(raticate);
		assertTrue(true);
		restaurador.aplicarElemento(raticate);
		
	}
	
}
