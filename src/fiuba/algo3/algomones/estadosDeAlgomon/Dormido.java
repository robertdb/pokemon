package fiuba.algo3.algomones.estadosDeAlgomon;

import fiuba.algo3.algomones.Algomon;
import fiuba.algo3.algomones.excepciones.AtacarDormidoNoPuedeRealizarseException;


public class Dormido implements Efecto{
  
  private int tiempo;
  private ContextoEstado contexto;
  
  public Dormido() {
    tiempo = 3;
  }
  
  public void agregar(ContextoEstado contexto){
    this.contexto = contexto;
    contexto.setearDormido(this);    
  }
  
  @Override
  public void aplicarEfecto(Algomon algomon) {
    
    if (efectoCaducado()){
    	
    		contexto.desactivarDormido();
    		
    		return;
    }
      
    
    this.tiempo -= 1;
    
    throw new AtacarDormidoNoPuedeRealizarseException();
    
  }

  private boolean efectoCaducado(){
    
      return tiempo == 0;
    
  }
}