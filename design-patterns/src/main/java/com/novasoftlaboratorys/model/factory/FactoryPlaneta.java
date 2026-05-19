package com.novasoftlaboratorys.model.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import com.novasoftlaboratorys.interfaces.IPlaneta;
import com.novasoftlaboratorys.model.planetas.PlanetaMarte;
import com.novasoftlaboratorys.model.planetas.PlanetaTierra;

public class FactoryPlaneta {

    private static final Map<String, Supplier<IPlaneta>> PlanetaDelivery = new HashMap<>();
    static {
        PlanetaDelivery.put("tierra", PlanetaTierra::getInstance);
        PlanetaDelivery.put("marte", PlanetaMarte::getInstance);
    }

    public static void registrarPlaneta(String nombrePlaneta, Supplier<IPlaneta> planetaSupplier) {
        PlanetaDelivery.put(nombrePlaneta.toLowerCase(), planetaSupplier);
    }

    public static IPlaneta getPlaneta(String nombrePlaneta) {
        Supplier<IPlaneta> planetaSupplier = PlanetaDelivery.get(nombrePlaneta.toLowerCase());
        if (planetaSupplier == null) {
            throw new IllegalArgumentException("Planeta desconocido: " + nombrePlaneta);
        }
        return planetaSupplier.get();
    }
}
