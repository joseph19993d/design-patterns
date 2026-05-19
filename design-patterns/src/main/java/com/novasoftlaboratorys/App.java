
package com.novasoftlaboratorys;

import com.novasoftlaboratorys.interfaces.IJugador;
import com.novasoftlaboratorys.interfaces.IPlaneta;
import com.novasoftlaboratorys.interfaces.ILinajeFactory;
import com.novasoftlaboratorys.model.factory.FactoryPlaneta;
import com.novasoftlaboratorys.model.factory.FactoryLinaje;
import com.novasoftlaboratorys.model.factory.FactoryPlanetaViejo;
import com.novasoftlaboratorys.model.planetas.PlanetaTierra;
import com.novasoftlaboratorys.model.planetas.PlanetaViejo;
import com.novasoftlaboratorys.model.planetas.PlanetaViejoAdapter;

/**
 * Demostracion de Patrones de Diseno:
 *   Creacionales: Singleton, Factory Method, Abstract Factory
 *   Estructurales: Adapter
 *   Principios:   SOLID Open/Closed via Map + Supplier
 */
public class App {

    // ── Codigos de color ANSI ──────────────────────────────────────────────────
    static final String RESET   = "\u001B[0m";
    static final String BOLD    = "\u001B[1m";
    static final String RED     = "\u001B[31m";
    static final String GREEN   = "\u001B[32m";
    static final String YELLOW  = "\u001B[33m";
    static final String BLUE    = "\u001B[34m";
    static final String MAGENTA = "\u001B[35m";
    static final String CYAN    = "\u001B[36m";
    static final String WHITE   = "\u001B[37m";

    // ── Helpers de UI (ASCII puro, compatible con Windows) ────────────────────
    static void titulo(String color, String texto) {
        System.out.println();
        System.out.println(color + BOLD + "+--------------------------------------------------+" + RESET);
        System.out.println(color + BOLD + "  " + texto                                           + RESET);
        System.out.println(color + BOLD + "+--------------------------------------------------+" + RESET);
    }

    static void separador() {
        System.out.println(WHITE + "\n  --------------------------------------------------\n" + RESET);
    }

    static void info(String label, String valor) {
        System.out.println("  " + CYAN + label + RESET + " " + valor);
    }

    static void ok(String mensaje) {
        System.out.println("  " + GREEN + "[OK] " + RESET + mensaje);
    }

    static void sub(String color, String texto) {
        System.out.println("\n  " + color + BOLD + ">> " + texto + RESET);
    }

    // ── Main ──────────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        demoSingleton();
        demoFactory();
        demoAdapter();
        demoAbstractFactory();
    }

    // ── 1. PATRON CREACIONAL: SINGLETON ───────────────────────────────────────
    static void demoSingleton() {
        titulo(BLUE, "PATRON CREACIONAL  |  SINGLETON");
        System.out.println(BLUE + "  Planeta Tierra - instancia unica garantizada" + RESET);
        separador();

        PlanetaTierra instancia1 = PlanetaTierra.getInstance();
        PlanetaTierra instancia2 = PlanetaTierra.getInstance();

        sub(CYAN, "Instancia 1");
        System.out.println("  " + instancia1);
        info("Hash:", String.valueOf(instancia1.hashCode()));

        sub(CYAN, "Instancia 2");
        System.out.println("  " + instancia2);
        info("Hash:", String.valueOf(instancia2.hashCode()));

        System.out.println();
        if (instancia1.hashCode() == instancia2.hashCode()) {
            ok("Ambas variables apuntan a la MISMA instancia en memoria.");
        }
        System.out.flush();
    }

    // ── 2. PATRON CREACIONAL: FACTORY METHOD ──────────────────────────────────
    static void demoFactory() {
        titulo(MAGENTA, "PATRON CREACIONAL  |  FACTORY METHOD  (Map + Supplier)");
        System.out.println(MAGENTA + "  Principio SOLID - Open/Close" + RESET);

        sub(RED, "Planeta Marte");
        IPlaneta marte = FactoryPlaneta.getPlaneta("marte");
        marte.entrar();
        marte.explorar();
        marte.salir();

        sub(GREEN, "Planeta Tierra");
        IPlaneta tierra = FactoryPlaneta.getPlaneta("tierra");
        tierra.entrar();
        tierra.explorar();
        tierra.salir();
        System.out.flush();
    }

    // ── 3. PATRON ESTRUCTURAL: ADAPTER ────────────────────────────────────────
    static void demoAdapter() {
        titulo(YELLOW, "PATRON ESTRUCTURAL  |  ADAPTER + FACTORY + SINGLETON");
        System.out.println(YELLOW + "  Adapta PlanetaViejo (legado) a la interfaz IPlaneta moderna" + RESET);
        separador();

        // Open/Closed: registramos el adaptador sin modificar FactoryPlaneta
        FactoryPlaneta.registrarPlaneta("viejo", () -> new PlanetaViejoAdapter(PlanetaViejo.getInstance()));
        IPlaneta viejo = FactoryPlaneta.getPlaneta("viejo");

        info("Objeto adaptado:", viejo.toString());
        sub(YELLOW, "Operaciones via interfaz moderna:");
        viejo.entrar();
        System.out.flush();
    }

    // ── 4. PATRON CREACIONAL: ABSTRACT FACTORY ────────────────────────────────
    static void demoAbstractFactory() {
        titulo(CYAN, "PATRON CREACIONAL  |  ABSTRACT FACTORY  (ILinajeFactory)");
        System.out.println(CYAN + "  Crea familias de objetos (Planeta + Jugador) del mismo Linaje" + RESET);

        // Open/Closed en accion: registramos el Linaje Antiguo sin modificar FactoryLinaje
        FactoryLinaje.registrarLinaje("antiguo", FactoryPlanetaViejo::new);

        String[] linajes = { "tierra",  "marte",  "antiguo" };
        String[] colores = { GREEN,     RED,      YELLOW    };

        for (int i = 0; i < linajes.length; i++) {
            String linaje = linajes[i];
            String color  = colores[i];

            sub(color, "Linaje: " + linaje.toUpperCase());
            ILinajeFactory fabrica = FactoryLinaje.getLinajeFactory(linaje);

            // El planeta ya existe (Singleton); obtenerPlaneta() solo referencia esa instancia
            IPlaneta planeta = fabrica.obtenerPlaneta();
            info("Planeta del linaje:", planeta.toString());
            planeta.entrar();

            IJugador jugador = fabrica.crearJugador("Explorador-" + linaje.substring(0, 1).toUpperCase());
            info("Jugador del linaje:", jugador.getNombre() + " [" + jugador.getRaza() + " / " + jugador.getClase() + "]");
            jugador.mostrarDescripcion();
            System.out.flush();
        }

        System.out.println();
        ok("Cada ILinajeFactory garantiza coherencia: Planeta y Jugador del mismo linaje.");
        System.out.flush();
    }
}