/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.novasoftlaboratorys.model.reportes;
import com.novasoftlaboratorys.interfaces.Igenerador;

/**
 *
 * @author Joseph Rodelo S
 */
public class ReporteGenerador {
    private final Igenerador generador;
    
    public ReporteGenerador (Igenerador generador) {
        this.generador = generador;
    }

    public void generar(){
        System.out.println(generador.exportar());
    }
}
