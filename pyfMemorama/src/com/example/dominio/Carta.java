/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.dominio;

import java.util.Random;

/**
 *
 * @author Emanuel-Not
 */
public class Carta {

    private String imgCarta, imgContraCara;
    private int valorCarta;

    public Carta() {
        imgCarta = "";
        imgContraCara = "";
        valorCarta = 0;
    }

    public Carta(String imgCarta, String imgContraCara) {
        this.imgCarta = imgCarta;
        this.imgContraCara = imgContraCara;
        valorCarta = calcularValor();
    }

    public String getImgCarta() {
        return imgCarta;
    }

    public void setImgCarta(String imgCarta) {
        this.imgCarta = imgCarta;
    }

    public String getImgContraCara() {
        return imgContraCara;
    }

    public void setImgContraCara(String imgContraCara) {
        this.imgContraCara = imgContraCara;
    }

    public int getValorCarta() {
        return valorCarta;
    }

    public int calcularValor() {
        /*Como no comprendo del enunciado que cartas deberían tener un valor
        de carta facil o dificil, hago que aleatoriamente elija entre una
        CartaFacil o Dificil.
         */
        int valC, valor;
        Random r = new Random();
        valC = r.nextInt(2) + 1;
        if (valC == 1) {
            valor = r.nextInt(10) + 1; // Carta facil
        } else {
            valor = (r.nextInt(100)) * (r.nextInt(5) + 1); // Carta dificil
        }
        return valor;
    }
}
