package com.lorian.lorianBank.conta;

public enum TipoConta {
	CORRENTE("CORRENTE"),
    POUPANCA("POUPANCA");
    
    private final String valor;
    
    TipoConta(String valor) {
        this.valor = valor;
    }
    
    public String getValor() {
        return valor;
    }
}
