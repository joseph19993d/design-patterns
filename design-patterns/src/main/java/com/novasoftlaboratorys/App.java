package com.novasoftlaboratorys;

import com.novasoftlaboratorys.model.clases.PlanetaTierra;
import com.novasoftlaboratorys.model.factory.FactoryPlaneta;
import com.novasoftlaboratorys.model.clases.PlanetaViejo;
import com.novasoftlaboratorys.model.clases.PlanetaViejoAdapter;
import com.novasoftlaboratorys.interfaces.IPlaneta;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        System.out.println("PATRON CREACIONAL SINGLENTON, PLANETA TIERA");
        PlanetaTierra planetaTierra1 = PlanetaTierra.getInstance();
        System.out.println(planetaTierra1);
        System.out.println("Direccion de memoria 1 :" + planetaTierra1.hashCode());

        PlanetaTierra planetaTierra2 = PlanetaTierra.getInstance();
        System.out.println(planetaTierra2);
        System.out.println("Direccion de memoria 2 :" + planetaTierra2.hashCode());

        if (planetaTierra1.hashCode() == planetaTierra1.hashCode()) {
            System.out.println("Las instancias son las mismas");
        }

        System.out.println("\n =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=\n");

        System.out.println("PATRON CREACIONAL FACTORY, PLANETA MARTE, TIERA : \n");
        IPlaneta planetaMarte = FactoryPlaneta.getPlaneta("marte");
        planetaMarte.entrar();
        planetaMarte.explorar();
        planetaMarte.salir();
        System.out.println("+++++++++");
        IPlaneta planetaTierra = FactoryPlaneta.getPlaneta("tierra");
        planetaTierra.entrar();
        planetaTierra.explorar();
        planetaTierra.salir();

        System.out.println("\n =*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=*=\n");

        System.out.println(
                "PATRON ESTRUCTURAL ADAPTER, CREACIONAL FACTORY " +
                        "(CON MAP Y PUPLIER PARA PRINCIPIO SOLID O (OPEN CLOSE) )," +
                        " CREACIONAL SINGLENTON, (ADAPTAR PLANETA VIEJO A NUEVO, " +
                        "INCLUIR PLANETA VIEJO EN FACTORY) \n");
        FactoryPlaneta.registrarPlaneta("viejo", () -> new PlanetaViejoAdapter(PlanetaViejo.getInstance()));
        IPlaneta planetaViejo = FactoryPlaneta.getPlaneta("viejo");
        System.out.println(planetaViejo.toString());
        planetaViejo.entrar();

    }
}
