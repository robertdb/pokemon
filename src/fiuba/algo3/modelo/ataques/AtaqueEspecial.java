package fiuba.algo3.modelo.ataques;

import fiuba.algo3.modelo.Algomon;

public abstract class AtaqueEspecial implements Ataque{

	protected Ataque ataque;
	
	public AtaqueEspecial(Ataque ataque){
		
		this.ataque = ataque;
		
	}
		
	public double atacar(Algomon atacante, Algomon atacado){
		
		return ataque.atacar(atacante, atacado);
		
	}

	public void aumentarAtaques() {
		
		this.ataque.aumentarAtaques();
		
	}
	
}
