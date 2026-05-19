package com.novasoftlaboratorys.interfaces;

import java.util.UUID;

public interface IJugador {
    UUID getId();

    String getNombre();

    String getRaza();

    String getClase();

    void mostrarDescripcion();

    void mostrarEstadisticas();
}
