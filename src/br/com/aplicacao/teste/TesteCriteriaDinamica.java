/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aplicacao.teste;

import br.com.aplicacao.dao.ConsultasDAO;

/**
 *
 * @author luis.silva
 */
public class TesteCriteriaDinamica {

    public static void main(String[] args) {

//        List<Veiculo> veiculos = pesquisarVeiculos(
//                TipoCombustivel.GASOLINA, new BigDecimal(60_000));
//
//        for (Veiculo v : veiculos) {
//
//            System.out.println(v.getModelo() + " - " + v.getValor());
//
//        }
        ConsultasDAO dao = new ConsultasDAO();

        //dao.cunsultaprojecoes();
        //dao.consultaAgregacao();
        //dao.consultaComplexosObject();
        //dao.consultaComplexosTuplas();
        //dao.consultaComplexoConstrutores();
        //dao.consultaComplexoFuncoes();
        //dao.consultaComplexoFuncoesList();
        //dao.consultaComplexoOrdenacao();
        //dao.consultaComplexoJoin();
        //dao.consultsComplexoFtch();
        //dao.consultaComplexoSubqueries();
        dao.conasultaComplexoMetamodel();
    }

}
