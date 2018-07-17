/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aplicacao.modelo;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author luis.silva
 */
public class PrecoVeiculo {

    private String modelo;
    private BigDecimal valor;

    public PrecoVeiculo(String modelo, BigDecimal valor) {
        super();
        this.modelo = modelo;
        this.valor = valor;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.modelo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PrecoVeiculo other = (PrecoVeiculo) obj;
        if (!Objects.equals(this.modelo, other.modelo)) {
            return false;
        }
        return true;
    }

}
