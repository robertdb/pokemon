package fiuba.algo3.test;

import static org.junit.Assert.*;

import java.util.EnumMap;

import org.junit.Test;

import fiuba.algo3.algomones.Algomon;
import fiuba.algo3.algomones.EspecieAlgomon;
import fiuba.algo3.algomones.NombreDelAtaque;
import fiuba.algo3.algomones.Salud;
import fiuba.algo3.algomones.ataques.Ataque;
import fiuba.algo3.algomones.ataques.AtaqueCanto;
import fiuba.algo3.algomones.ataques.AtaqueChupavidas;
import fiuba.algo3.algomones.ataques.AtaqueFogonazo;
import fiuba.algo3.algomones.ataques.AtaqueSimple;
import fiuba.algo3.algomones.excepciones.AtacarDormidoNoPuedeRealizarseException;
import fiuba.algo3.algomones.tiposDeAlgomon.Tipo;
import fiuba.algo3.algomones.tiposDeAlgomon.TipoAgua;
import fiuba.algo3.algomones.tiposDeAlgomon.TipoFuego;
import fiuba.algo3.algomones.tiposDeAlgomon.TipoNormal;
import fiuba.algo3.algomones.tiposDeAlgomon.TipoPlanta;

public class AtaqueFogonazoTest {

	@Test
	public void testAtacarConFogonazoAunAlgomonNormalQuita2ptsDeVidaYCambiaEstado() {
		
		//Se crea un algomon personalizado con fogonazo.
		Ataque fogonazo = new AtaqueFogonazo(new AtaqueSimple(new TipoFuego(), 2, 4 ));
		EnumMap<NombreDelAtaque, Ataque> ataques = new EnumMap<NombreDelAtaque, Ataque >(NombreDelAtaque.class);
		ataques.put(NombreDelAtaque.FOGONAZO,fogonazo);
				
		Salud saludCharizard= new Salud(400);
		Algomon charizard = new Algomon("Charizard", new TipoFuego(), ataques , saludCharizard);
		
		// Se crea un algomon personalizado.
		Ataque ataqueRapido = new AtaqueSimple(new TipoNormal(), 25 , 16 );
		EnumMap<NombreDelAtaque, Ataque> ataquesNormal = new EnumMap<NombreDelAtaque, Ataque >(NombreDelAtaque.class);
		ataquesNormal.put(NombreDelAtaque.ATAQUE_RAPIDO, ataqueRapido);
		
		Salud saludRaticate = new Salud(302);
		Algomon raticate = new Algomon("Raticate", new TipoNormal(), ataquesNormal, saludRaticate);
		
		charizard.atacar(raticate, NombreDelAtaque.FOGONAZO);
		assertEquals(300,raticate.salud(),0.001D);
		
		//afecta el efecto Quemado
		raticate.atacar(charizard, NombreDelAtaque.ATAQUE_RAPIDO);
		assertEquals(270,raticate.salud(),0.001D);
				
	}
	
	@Test
	public void testAtacarConFogonazoAunAlgomonTipoAguaQuita4ptsDeVidaYCambiaEstado() {
		
		//Se crea un algomon personalizado con fogonazo.
		Ataque fogonazo = new AtaqueFogonazo(new AtaqueSimple(new TipoFuego(), 2, 4 ));
		EnumMap<NombreDelAtaque, Ataque> ataques = new EnumMap<NombreDelAtaque, Ataque >(NombreDelAtaque.class);
		ataques.put(NombreDelAtaque.FOGONAZO,fogonazo);
				
		Salud saludCharizard= new Salud(400);
		Algomon charizard = new Algomon("Charizard", new TipoFuego(), ataques , saludCharizard);
		
		// Se crea un algomon personalizado.
		Ataque ataqueRapido = new AtaqueSimple(new TipoNormal(), 25 , 16 );
		EnumMap<NombreDelAtaque, Ataque> ataquesNormal = new EnumMap<NombreDelAtaque, Ataque >(NombreDelAtaque.class);
		ataquesNormal.put(NombreDelAtaque.ATAQUE_RAPIDO, ataqueRapido);
		
		
		Algomon blastoise = new Algomon("Blastoise", new TipoAgua(), ataquesNormal, new Salud(400));
		
		charizard.atacar(blastoise, NombreDelAtaque.FOGONAZO);
		assertEquals(399,blastoise.salud(),0.001D);
		
		//afecta el efecto Quemado
		blastoise.atacar(charizard, NombreDelAtaque.ATAQUE_RAPIDO);
		assertEquals(359,blastoise.salud(),0.001D);
				
	}


}