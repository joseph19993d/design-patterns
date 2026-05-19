package com.novasoftlaboratorys.model.planetas;
import com.novasoftlaboratorys.interfaces.IPlaneta;

public class PlanetaViejoAdapter implements IPlaneta {
    
    private PlanetaViejo viejo;

    public PlanetaViejoAdapter(PlanetaViejo viejo) {
        this.viejo = viejo;
    }

    @Override
    public void entrar() {
        viejo.entrarViejoDiferenteAlDeLaInterfaz();
    }

    @Override
    public void explorar() {
        viejo.explorarViejoDiferenteAlDeLaInterfaz();
    }

    @Override
    public void salir() {
        viejo.salirViejoDiferenteAlDeLaInterfaz();
    }
}

