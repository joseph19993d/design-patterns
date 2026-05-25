package com.novasoftlaboratorys.model.factory;

import java.util.HashMap;
import java.util.Map;

import com.novasoftlaboratorys.interfaces.ILinajeFactory;
import com.novasoftlaboratorys.interfaces.IPlaneta;
import com.novasoftlaboratorys.interfaces.IJugador;
import com.novasoftlaboratorys.model.planetas.PlanetaTierra;
import com.novasoftlaboratorys.model.jugadores.jugadorTerricola;
import java.util.function.Supplier;

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
                .setBioma("Prados y desiertos")
                .setRaza("Terricola")
                .setClase("Guerrero")
                .setEspecialidad("Guerrero")
                .build();
    }
}
