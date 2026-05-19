package com.novasoftlaboratorys.model.factory;

import com.novasoftlaboratorys.interfaces.ILinajeFactory;
import com.novasoftlaboratorys.interfaces.IPlaneta;
import com.novasoftlaboratorys.interfaces.IJugador;
import com.novasoftlaboratorys.model.planetas.PlanetaMarte;
import com.novasoftlaboratorys.model.jugadores.jugadorMarciano;

/**
 * ABSTRACT FACTORY — Linaje Marciano
 *
 * Garantiza que tanto el planeta como el jugador creados
 * pertenezcan al linaje de Marte.
 *
 * obtenerPlaneta() devuelve la instancia Singleton de PlanetaMarte;
 * el planeta ya existe, no se crea de nuevo.
 */
public class MarteFactory implements ILinajeFactory {

    @Override
    public IPlaneta obtenerPlaneta() {
        return PlanetaMarte.getInstance();
    }

    @Override
    public IJugador crearJugador(String nombre) {
        return new jugadorMarciano(nombre);
    }
}
