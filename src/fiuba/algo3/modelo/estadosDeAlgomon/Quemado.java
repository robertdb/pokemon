package fiuba.algo3.modelo.estadosDeAlgomon;

import fiuba.algo3.modelo.Algomon;

public class Quemado implements Efecto{
	  
	  private final double porcetajeDeQuemadura  = 0.10;
	  
	  public void agregar(ContextoEstado contexto){
	    
	      contexto.setearQuemado(this);    
	  }
	  
	  @Override
	  public void aplicarEfecto(Algomon algomon) {
	    
	    double vida = algomon.saludOriginal();
	    
	    double danio = vida * porcetajeDeQuemadura;
	    
	    algomon.reducirSalud( danio);
	    
	  }
}
