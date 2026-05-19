package com.novasoftlaboratorys.model.factory;

import com.novasoftlaboratorys.interfaces.ILinajeFactory;
import com.novasoftlaboratorys.interfaces.IPlaneta;
import com.novasoftlaboratorys.interfaces.IJugador;
import com.novasoftlaboratorys.model.planetas.PlanetaTierra;
import com.novasoftlaboratorys.model.jugadores.jugadorTerricola;

/**
 * ABSTRACT FACTORY — Linaje Terrícola
 *
 * Garantiza que tanto el planeta como el jugador creados
 * pertenezcan al linaje de la Tierra.
 *
 * obtenerPlaneta() devuelve la instancia Singleton de PlanetaTierra;
 * el planeta ya existe, no se crea de nuevo.
 */
public class TierraFactory implements ILinajeFactory {

    @Override
    public IPlaneta obtenerPlaneta() {
        return PlanetaTierra.getInstance();
    }

    @Override
    public IJugador crearJugador(String nombre) {
        return new jugadorTerricola.Builder()
                .setNombre(nombre)
                .setBioma("Tierra")
                .setRaza("Terricola")
                .setClase("Guerrero")
                .setEspecialidad("Espadas")
                .build();
    }
}
