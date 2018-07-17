/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aplicacao.utilidades;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author luis.silva
 */
public class Conexao {

    private static final EntityManagerFactory factory;

    static {
        factory = Persistence.createEntityManagerFactory("AplicacaoJPQLPU");
    }

    public static EntityManager getEntitymanager() {
        return factory.createEntityManager();
    }

    public static void close() {
        factory.close();
    }

}
