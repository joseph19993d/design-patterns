package com.novasoftlaboratorys.model.factory;

import com.novasoftlaboratorys.interfaces.ILinajeFactory;
import com.novasoftlaboratorys.interfaces.IPlaneta;
import com.novasoftlaboratorys.interfaces.IJugador;
import com.novasoftlaboratorys.model.planetas.PlanetaViejoAdapter;
import com.novasoftlaboratorys.model.planetas.PlanetaViejo;
import com.novasoftlaboratorys.model.jugadores.JugadorPlanetaViejo;

/**
 * ABSTRACT FACTORY — Linaje Antiguo
 *
 * Garantiza que tanto el planeta como el jugador creados
 * pertenezcan al linaje ancestral (PlanetaViejo).
 *
 * PATRON ADAPTER en accion:
 *   PlanetaViejo es una clase legada que no implementa IPlaneta.
 *   PlanetaViejoAdapter la envuelve para hacerla compatible con la
 *   interfaz moderna, sin modificar la clase original (Open/Closed).
 *
 * PATRON SINGLETON:
 *   PlanetaViejo.getInstance() garantiza que solo existe una instancia
 *   del planeta legado, y el Adapter simplemente la referencia.
 */
public class FactoryPlanetaViejo implements ILinajeFactory {

    @Override
    public IPlaneta obtenerPlaneta() {
        // El Singleton ya existe; el Adapter lo adapta a IPlaneta
        return new PlanetaViejoAdapter(PlanetaViejo.getInstance());
    }

    @Override
    public IJugador crearJugador(String nombre) {
        return new JugadorPlanetaViejo(nombre);
    }
}
