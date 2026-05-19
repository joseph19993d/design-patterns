package com.novasoftlaboratorys.interfaces;

/**
 * PATRON CREACIONAL: ABSTRACT FACTORY
 *
 * Contrato que toda fábrica de Linaje debe cumplir.
 * Garantiza que el planeta y el jugador creados pertenezcan
 * al mismo linaje (civilización de origen).
 *
 * Principio SOLID aplicado:
 *   - Open/Closed: nuevos linajes se registran en FactoryLinaje
 *     sin modificar esta interfaz ni las fábricas existentes.
 *   - Dependency Inversion: App.java depende de esta abstracción,
 *     nunca de implementaciones concretas.
 */
public interface ILinajeFactory {

    /**
     * Obtiene el planeta que representa al linaje.
     * El planeta NO se crea aquí; ya existe como Singleton.
     * Este método simplemente devuelve la referencia a esa instancia única.
     *
     * @return la instancia Singleton del planeta del linaje
     */
    IPlaneta obtenerPlaneta();

    /**
     * Crea un nuevo jugador perteneciente a este linaje.
     *
     * @param nombre el nombre del jugador a crear
     * @return un IJugador con raza, clase y bioma propios del linaje
     */
    IJugador crearJugador(String nombre);
}
