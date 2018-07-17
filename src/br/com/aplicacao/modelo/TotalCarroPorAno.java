/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aplicacao.modelo;

import java.util.Objects;

/**
 *
 * @author luis.silva
 */
public class TotalCarroPorAno {

    private Integer anoFabricacao;
    private Double mediapreco;
    private Long quantidadeCarros;

    public TotalCarroPorAno(Integer anoFabricacao, Double mediapreco,
            Long quantidadeCarros) {
        super();
        this.anoFabricacao = anoFabricacao;
        this.mediapreco = mediapreco;
        this.quantidadeCarros = quantidadeCarros;
    }

    public Integer getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(Integer anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public Double getMediapreco() {
        return mediapreco;
    }

    public void setMediapreco(Double mediapreco) {
        this.mediapreco = mediapreco;
    }

    public Long getQuantidadeCarros() {
        return quantidadeCarros;
    }

    public void setQuantidadeCarros(Long quantidadeCarros) {
        this.quantidadeCarros = quantidadeCarros;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.anoFabricacao);
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
        final TotalCarroPorAno other = (TotalCarroPorAno) obj;
        if (!Objects.equals(this.anoFabricacao, other.anoFabricacao)) {
            return false;
        }
        return true;
    }

}
