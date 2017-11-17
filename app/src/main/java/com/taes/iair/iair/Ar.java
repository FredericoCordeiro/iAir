package com.taes.iair.iair;

/**
 * Created by Pedro on 17/11/2017.
 */

public class Ar {
    private int humidade;
    private int temperatura;
    private int pressAtmosferica;

    public Ar(int humidade, int temperatura, int pressAtmosferica) {
        this.humidade = humidade;
        this.temperatura = temperatura;
        this.pressAtmosferica = pressAtmosferica;
    }

    public int getHumidade() {
        return humidade;
    }

    public void setHumidade(int humidade) {
        this.humidade = humidade;
    }

    public int getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    public int getPressAtmosferica() {
        return pressAtmosferica;
    }

    public void setPressAtmosferica(int pressAtmosferica) {
        this.pressAtmosferica = pressAtmosferica;
    }
}
