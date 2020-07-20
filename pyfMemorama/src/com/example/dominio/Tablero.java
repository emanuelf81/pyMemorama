/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.dominio;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Emanuel-Not
 */
public class Tablero {

    private List<Carta> cartas;
    private int tematica, cantCeldas, puntaje;
    private LocalTime tiempoIni;
    private String tiempoFin, nomJugador;

    public Tablero() {
        this.cartas = new ArrayList<>(cantCeldas);
        tematica = 0;
        tiempoIni = LocalTime.now();
        tiempoFin = "";
        puntaje = 0;
        nomJugador = "";
    }

    public Tablero(int tematica, int cantCeldas, String nomJugador) {
        this.cartas = new ArrayList<>(cantCeldas);
        this.tematica = tematica;
        this.cantCeldas = cantCeldas;
        this.tiempoIni = LocalTime.now();
        this.tiempoFin = "";
        this.puntaje = 0;
        this.nomJugador = nomJugador;
    }

    public void setTiempoIni(LocalTime tiempo) {
        this.tiempoIni = tiempo;
    }

    public LocalTime getTiempoIni() {
        return tiempoIni;
    }

    public String getTiempoFin() {
        return tiempoFin;
    }

    public void setTiempoFin(String tiempoFin) {
        this.tiempoFin = tiempoFin;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public String getNomJugador() {
        return nomJugador;
    }

    public void setNomJugador(String nomJugador) {
        this.nomJugador = nomJugador;
    }

    private int[] getPosCartasAzar() {
        // Genera posiciones al azar para los pares de cartas.
        int[] posiciones = new int[cantCeldas];
        int count = 0, p, pr; // p = posicion aleatoria y pr = posision repetida
        while (count < posiciones.length) {
            pr = 0;
            Random r = new Random();
            p = r.nextInt(cantCeldas / 2) + 1;
            for (int i = 0; i < posiciones.length; i++) {
                if (posiciones[i] == p) {
                    pr++;
                }
            }
            if (pr < 2) {
                posiciones[count] = p;
                count++;
            }
        }
        return posiciones;
    }

    private String[][] getDirImgCartas() {
        /* Guarda las direcciones de los archivos de las imagenes como String
        para luego poder compararlas y ver si las cartas son iguales o
        diferentes. Utiliza getPosCartasAzar() para que sea al azar.*/
        String[][] dirImg = new String[2][cantCeldas];
        int[] posiciones = getPosCartasAzar();
        // Opción para diferentes tipos de imagenes
        switch (tematica) {
            case 0:
                for (int i = 0; i < cantCeldas; i++) {
                    // Cara
                    dirImg[0][i] = "../imagenes/cMath" + posiciones[i] + ".png";
                    // Contracara
                    dirImg[1][i] = "../imagenes/cMath.png";
                }
                break;
            case 1:
                for (int i = 0; i < cantCeldas; i++) {
                    dirImg[0][i] = "../imagenes/cBand" + posiciones[i] + ".png";
                    dirImg[1][i] = "../imagenes/cBand.png";
                }
                break;
            case 2:
                for (int i = 0; i < cantCeldas; i++) {
                    dirImg[0][i] = "../imagenes/cShrek" + posiciones[i] + ".png";
                    dirImg[1][i] = "../imagenes/cShrek.png";
                }
                break;
        }
        return dirImg;
    }

    public void setCartas() {
        String[][] dirImg = getDirImgCartas();
        for (int i = 0; i < cantCeldas; i++) {
            cartas.add(i, new Carta(dirImg[0][i], dirImg[1][i]));
        }
    }

    public List<Carta> getCartas() {
        return cartas;
    }

    public void setTiempoTranscurrido() {
        int min, seg;
        seg = LocalTime.now().getSecond() - tiempoIni.getSecond();
        if (seg < 0) {
            min = LocalTime.now().getMinute() - tiempoIni.getMinute();
            min = min - 1;
            seg = seg + 60;
        } else {
            min = LocalTime.now().getMinute() - tiempoIni.getMinute();
        }
        tiempoFin = min + " mín., " + seg + " seg.";
    }

    public boolean comparaCartas(int posCeldaA, int posCeldaB) {
        return cartas.get(posCeldaA - 1).getImgCarta().equals(cartas.get(posCeldaB - 1).getImgCarta());
    }

    public void puntuacion(int posCeldaA, int posCeldaB) {
        puntaje += cartas.get(posCeldaA - 1).getValorCarta() + cartas.get(posCeldaB - 1).getValorCarta();
    }

    @Override
    public String toString() {
        return "\n\nJugador = " + nomJugador + "\nPuntaje = " + puntaje + "\nTiempo = " + tiempoFin;
    }

}
