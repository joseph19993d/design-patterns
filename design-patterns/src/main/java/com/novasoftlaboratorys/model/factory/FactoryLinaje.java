package com.novasoftlaboratorys.model.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import com.novasoftlaboratorys.interfaces.ILinajeFactory;

/**
 * PATRON CREACIONAL: ABSTRACT FACTORY + SOLID Open/Closed
 *
 * Registro central de fábricas de Linaje.
 * Utiliza un Map<String, Supplier<ILinajeFactory>> para almacenar
 * los constructores de cada fábrica concreta de forma lazy.
 *
 * Ventaja del Supplier: cada llamada a getLinajeFactory() crea una
 * instancia fresca de la fábrica concreta, sin acoplar al cliente
 * a ninguna clase específica (Dependency Inversion).
 *
 * Extensión sin modificación (Open/Closed):
 *   Para agregar un nuevo linaje, basta con llamar a registrarLinaje()
 *   desde cualquier punto del código, sin tocar esta clase.
 */
public class FactoryLinaje {

    // Mapa de nombre de linaje → proveedor lazy de su fábrica concreta
    private static final Map<String, Supplier<ILinajeFactory>> registro = new HashMap<>();

    // Registro de linajes conocidos en tiempo de compilación
    static {
        registro.put("tierra", TierraFactory::new);
        registro.put("marte",  MarteFactory::new);
    }

    /**
     * Registra un nuevo linaje en tiempo de ejecución.
     * Implementa el principio Open/Closed: el sistema está abierto a extensión
     * (nuevos linajes) pero cerrado a modificación.
     *
     * @param nombreLinaje      identificador del linaje (case-insensitive)
     * @param proveedorFabrica  Supplier que construye la fábrica concreta
     */
    public static void registrarLinaje(String nombreLinaje, Supplier<ILinajeFactory> proveedorFabrica) {
        registro.put(nombreLinaje.toLowerCase(), proveedorFabrica);
    }

    /**
     * Obtiene la fábrica concreta asociada al linaje solicitado.
     *
     * @param nombreLinaje identificador del linaje (case-insensitive)
     * @return una instancia de ILinajeFactory lista para crear objetos del linaje
     * @throws IllegalArgumentException si el linaje no está registrado
     */
    public static ILinajeFactory getLinajeFactory(String nombreLinaje) {
        Supplier<ILinajeFactory> proveedor = registro.get(nombreLinaje.toLowerCase());
        if (proveedor == null) {
            throw new IllegalArgumentException(
                "Linaje desconocido: \"" + nombreLinaje + "\". " +
                "Registralo primero con FactoryLinaje.registrarLinaje()."
            );
        }
        return proveedor.get();
    }
}
