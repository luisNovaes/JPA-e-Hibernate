/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aplicacao.dao;

import br.com.aplicacao.modelo.Pessoa;
import br.com.aplicacao.modelo.PrecoVeiculo;
import br.com.aplicacao.modelo.Proprietario;
import br.com.aplicacao.modelo.Proprietario_;
import br.com.aplicacao.modelo.TipoCombustivel;
import br.com.aplicacao.modelo.TotalCarroPorAno;
import br.com.aplicacao.modelo.Veiculo;
import br.com.aplicacao.modelo.Veiculo_;
import br.com.aplicacao.utilidades.Conexao;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

/**
 *
 * @author luis.silva
 */
public class ConsultasDAO {

    public String salvar() {
        EntityManager manager = Conexao.getEntitymanager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        Proprietario proprietario = new Proprietario();
        proprietario.setNome("Luis Magno");
        proprietario.setEmail("luis.magno@gmail.com");
        proprietario.setTelefone("65 999991245");
        manager.persist(proprietario);
        //proprietario.setVeiculos(veiculo);
//        Acessorio alarme = new Acessorio();
//        alarme.setDescricao("Alarme");
//
//        Acessorio arCondicioanado = new Acessorio();
//        arCondicioanado.setDescricao("arCondicioanado");
//
//        Acessorio bancoDeCouro = new Acessorio();
//        bancoDeCouro.setDescricao("bancoDeCouro");
//
//        Acessorio direcaoHidraulica = new Acessorio();
//        direcaoHidraulica.setDescricao("direcaoHidraulica");
//
//        manager.persist(alarme);
//        manager.persist(arCondicioanado);
//        manager.persist(bancoDeCouro);
//        manager.persist(direcaoHidraulica);
//
//        Veiculo veiculo = new Veiculo();
//        veiculo.setFabricante("Fiat");
//        veiculo.setModelo("Uno");
//        veiculo.setAnoFabricacao(2018);
//        veiculo.setAnoModelo(2019);
//        veiculo.setDataCadastro(new Date());
//        veiculo.setTipoCombustivel(TipoCombustivel.ALCOOL);
//        veiculo.setValor(new BigDecimal(50000));
//        veiculo.getAcessorios().add(alarme);
//        veiculo.getAcessorios().add(arCondicioanado);
//
//        Veiculo veiculo2 = new Veiculo();
//        veiculo2.setFabricante("Fiat");
//        veiculo2.setModelo("Uno");
//        veiculo2.setAnoFabricacao(2018);
//        veiculo2.setAnoModelo(2019);
//        veiculo2.setDataCadastro(new Date());
//        veiculo2.setTipoCombustivel(TipoCombustivel.ALCOOL);
//        veiculo2.setValor(new BigDecimal(50000));
//        veiculo2.getAcessorios().add(bancoDeCouro);
//        veiculo2.getAcessorios().add(direcaoHidraulica);
//        veiculo2.getAcessorios().add(alarme);
//        veiculo2.getAcessorios().add(arCondicioanado);
//
//        manager.persist(veiculo);
//        manager.persist(veiculo2);
//
        tx.commit();
        manager.close();
        Conexao.close();

        return null;
    }

    public String consulta() {
        EntityManager manager = Conexao.getEntitymanager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        try {

            Query query = manager.createQuery(
                    " from Veiculo  where anoFabricacao = 2018");
            List veiculos = query.getResultList();

            veiculos.stream().forEach((obj) -> {
                Veiculo veiculo = (Veiculo) obj;
                System.out.println(veiculo.getModelo() + " " + veiculo.getFabricante()
                        + ": " + veiculo.getAnoFabricacao());
            });
        } catch (Exception e) {
            System.out.println(" Erro (" + e.getMessage()
                    + ") ao tentar consultar dados!");

        } finally {
            manager.close();
            Conexao.close();

        }

        return "Operação realizada com sucesso!";

    }

    public String consultaParamNomeado() {
        EntityManager manager = Conexao.getEntitymanager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        try {
            Query query = manager.createQuery(
                    "from Veiculo Where anoFabricacao >= :ano and valor <= :preco");
            query.setParameter("ano", 2009);
            query.setParameter("preco", new BigDecimal(60000));
            List veiculos = query.getResultList();

            for (Object obj : veiculos) {
                Veiculo veiculo = (Veiculo) obj;

                System.out.println(veiculo.getCodigo()
                        + " - " + veiculo.getModelo()
                        + " - " + veiculo.getFabricante()
                        + " - " + veiculo.getAnoFabricacao());
            }

        } catch (Exception e) {
            System.out.println("Erro "
                    + e.getMessage() + " ao tentar consultar dados!");

        } finally {
            manager.close();
            Conexao.close();

        }
        return "Operação realizada com sucesso!";

    }

    public String consultasTipadas() {
        EntityManager manager = Conexao.getEntitymanager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        try {
            TypedQuery<Veiculo> query
                    = manager.createQuery("from Veiculo", Veiculo.class);
            List<Veiculo> veiculos = query.getResultList();

            for (Veiculo veiculo : veiculos) {
                System.out.println(veiculo.getModelo()
                        + " - " + veiculo.getFabricante()
                        + " - " + veiculo.getAnoFabricacao());
            }

        } catch (Exception e) {
            System.out.println(" Erro " + e.getMessage()
                    + " ao tentar consultar dados!");

        } finally {
            manager.close();
            Conexao.close();
        }

        return "Operação realizada com sucesso!";

    }

    public String consultaPaginacao() {
        EntityManager manager = Conexao.getEntitymanager();
        EntityTransaction tx = manager.getTransaction();
        tx.begin();

        try {
            TypedQuery<Veiculo> query
                    = manager.createQuery("from Veiculo", Veiculo.class);
            query.setFirstResult(0);
            query.setMaxResults(4);

            List<Veiculo> veiculos = query.getResultList();

            for (Veiculo veiculo : veiculos) {

                System.out.println(veiculo.getModelo()
                        + " - " + veiculo.getFabricante()
                        + " - " + veiculo.getAnoFabricacao());
            }

        } catch (Exception e) {
            System.out.println(" Erro " + e.getMessage()
                    + " ao tentar consultar dados!");

        } finally {
            manager.close();
            Conexao.close();

        }

        return "Operação realizada com sucesso!";

    }

    public String ConsultaPaginacaoDinamica() {
        EntityManager manager = Conexao.getEntitymanager();
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Digite o numero de registros por página: ");
            int registrosPorPagina = scanner.nextInt();
            int numeroDaPagina = 0;

            TypedQuery<Veiculo> query
                    = manager.createQuery("from Veiculo", Veiculo.class);

            do {
                System.out.print("Agora digite o número da página: ");
                numeroDaPagina = scanner.nextInt();

                if (numeroDaPagina != 0) {
                    int primeiroRegistro = (numeroDaPagina - 1) * registrosPorPagina;

                    query.setFirstResult(primeiroRegistro);
                    query.setMaxResults(registrosPorPagina);
                    List<Veiculo> veiculos = query.getResultList();

                    for (Veiculo veiculo : veiculos) {
                        System.out.println(veiculo.getModelo()
                                + " - " + veiculo.getFabricante()
                                + " - " + veiculo.getAnoFabricacao());
                    }
                }
            } while (numeroDaPagina != 0);

        } catch (Exception e) {
            System.out.println("Erro "
                    + e.getMessage() + " ao tentar consultar dados!");

        } finally {
            scanner.close();
            manager.close();
            Conexao.close();
        }

        return "Operação realizada com sucesso!";

    }

    public String Conustaprojecoes() {
        EntityManager manager = Conexao.getEntitymanager();

        try {
            TypedQuery<String> query = manager.createQuery("Select modelo from Veiculo",
                    String.class);

            List<String> modelos = query.getResultList();

            for (String modelo : modelos) {
                System.out.println(modelo);
            }

        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage()
                    + " ao tentar consultar doados!");

        } finally {
            manager.close();
            Conexao.close();
        }

        return "operação realizada com sucesso!";
    }

    public String consultaComplexos() {
        EntityManager manager = Conexao.getEntitymanager();

        try {
            TypedQuery<PrecoVeiculo> query = manager.createQuery(
                    "select new  br.com.aplicacao.modelo.PrecoVeiculo"
                    + "(modelo, valor) from Veiculo",
                    PrecoVeiculo.class);

            List<PrecoVeiculo> precos = query.getResultList();

            for (PrecoVeiculo preco : precos) {
                System.out.println(preco.getModelo()
                        + " - " + preco.getValor());

            }

        } catch (Exception e) {
            System.out.println("Erro (" + e.getMessage()
                    + ") ao tentar consultar dados!");

        } finally {
            manager.close();
            Conexao.close();
        }

        return "Operação realizada com sucesso!";
    }

    public String consultaComAgregcao() {
        EntityManager manager = Conexao.getEntitymanager();

        try {
            TypedQuery<TotalCarroPorAno> query = manager.createQuery(
                    "select new br.com.aplicacao.modelo.TotalCarroPorAno"
                    + "(v.anoFabricacao, avg(v.valor), count(v))"
                    + "from Veiculo v group by v.anoFabricacao", TotalCarroPorAno.class);

            List<TotalCarroPorAno> resultado = query.getResultList();

            for (TotalCarroPorAno valores : resultado) {
                System.out.println("Ano: " + valores.getAnoFabricacao()
                        + " -  Preço médio: " + valores.getMediapreco()
                        + " - Quantidade: " + valores.getQuantidadeCarros());
            }

        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage()
                    + " ao consultar dados!");

        } finally {
            manager.close();
            Conexao.close();

        }
        return "Operação realizada com sucesso!";

    }

    public String consultaComAgregcaounico() {
        EntityManager manager = Conexao.getEntitymanager();

        try {
            TypedQuery<Long> query = manager.createQuery(
                    "select count(v) from Veiculo v", Long.class);

            Long quantidadeVeiculos = query.getSingleResult();

            System.out.println("Quantidade de veículos: " + quantidadeVeiculos);

        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage()
                    + " ao consultar dados!");

        } finally {
            manager.close();
            Conexao.close();

        }
        return "Operação realizada com sucesso!";

    }

    public String consultaComInnerJoin() {
        EntityManager manager = Conexao.getEntitymanager();

        try {
            TypedQuery<Proprietario> query = manager.createQuery(
                    "select distinct p\n"
                    + "from Veiculo v\n"
                    + "inner join v.proprietario p",
                    Proprietario.class);
            List<Proprietario> proprietarios = query.getResultList();

            for (Proprietario proprietario : proprietarios) {
                System.out.println(proprietario.getNome());
            }

        } catch (Exception e) {
            System.out.println("Erro (" + e.getMessage()
                    + ") ao tentar consultar dados!");

        } finally {
            manager.close();
            Conexao.close();
        }

        return "Operação realizada com sucesso!";
    }

    public String consultaCmLeftJoin() {
        EntityManager manager = Conexao.getEntitymanager();

        try {
            TypedQuery<Object[]> query = manager.createQuery(
                    "select p.nome, count(v) from Proprietario p "
                    + "left join p.veiculos v group by p.nome",
                    Object[].class);
            List<Object[]> resultado = query.getResultList();

            for (Object[] valores : resultado) {
                System.out.println(valores[0] + " - " + valores[1]);
            }

        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage()
                    + " ao tentar realizar a consulta!");

        } finally {
            manager.close();
            Conexao.close();
        }

        return "Operação realizada co  sucesso!";
    }

    public String cinsusltaproblemaN1() {
        EntityManager manager = Conexao.getEntitymanager();

        try {

            // ** esté query resulta de várias consultas no banco
            // TypedQuery<Veiculo> query = manager.createQuery(
            // "from Veiculo v", Veiculo.class);
            /**
             * *******************************************************************
             */
            // Esta query resolve o problema e só faz a consulta necessaria.
            TypedQuery<Veiculo> query = manager.createQuery(
                    "from Veiculo v inner join fetch v.proprietario", Veiculo.class);
            List<Veiculo> veiculos = query.getResultList();

            for (Veiculo veiculo : veiculos) {
                System.out.println(veiculo.getModelo() + " - "
                        + veiculo.getProprietario().getNome());
            }

        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage()
                    + " ao tentar consultar dados!");

        } finally {
            manager.close();
            Conexao.close();
        }

        return "Operação realizada com sucesso!";
    }

    public String consultaQuerysNomeadasLancamento() {
        EntityManager manager = Conexao.getEntitymanager();

        try {
            TypedQuery<String> query = manager.createNamedQuery(
                    "Lancamento.descricoesQueContem", String.class);
            query.setParameter("descricao", "%água%");
            List<String> descricoes = query.getResultList();

            for (String descricao : descricoes) {
                System.out.println(descricao.toUpperCase());

            }

        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage()
                    + " ao tentar consultar dados!");

        } finally {
            manager.close();
            Conexao.close();
        }

        return "Operação realizadas com sucesso!";
    }

    public String consultaQuerysNomeadasPessoa() {
        EntityManager manager = Conexao.getEntitymanager();

        try {
            TypedQuery<Pessoa> query = manager.createNamedQuery(
                    "Pessoa.todas", Pessoa.class);
            List<Pessoa> pessoas = query.getResultList();

            for (Pessoa pessoa : pessoas) {
                System.out.println(pessoa.getCodigo() + " - "
                        + pessoa.getNome());

            }

        } catch (Exception e) {
            System.out.println("Erro (" + e.getMessage()
                    + ") ao tentar consultar dados!");
        } finally {
            manager.close();
            Conexao.close();

        }

        return "Opereação realizada com sucesso!";

    }

    public String consultaQueryNativas() {
        EntityManager manager = Conexao.getEntitymanager();

        try {
            Query query = manager.createNativeQuery(
                    "select * from Veiculo", Veiculo.class);

            List<Veiculo> veiculos = query.getResultList();

            for (Veiculo veiculo : veiculos) {
                System.out.println(veiculo.getModelo() + " - "
                        + veiculo.getProprietario().getNome());
            }

        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage()
                    + " ao tentar cunsultar dados!");

        } finally {
            manager.close();
            Conexao.close();
        }

        return "Operação realizada com sucesso!";
    }

    public String consultaCriteriaAPI() {
        EntityManager manager = Conexao.getEntitymanager();
        try {

            //fábrica de vários objetos que podemos usar para definir uma consulta
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            //instanciar um CriteriaQuery, interface possui cláusulAS DE CONSULTA.
            CriteriaQuery<Veiculo> criteriaQuery = builder.createQuery(Veiculo.class);
            // Chamamos o método from de CriteriaQuery para obtermos um objeto do tipo Root.
            Root<Veiculo> veiculo = criteriaQuery.from(Veiculo.class);
            //cláusula select, informando como parâmetro o objeto do tipo Root, dizendo que queremos selecionar a entidade Veiculo.
            criteriaQuery.select(veiculo);
            //Criamos uma TypedQuery através do método EntityManager.createQuery, e depois
            //recuperamos o resultado da consulta pelo método getResultList.
            TypedQuery<Veiculo> query = manager.createQuery(criteriaQuery);
            List<Veiculo> veiculos = query.getResultList();

            for (Veiculo v : veiculos) {
                System.out.println(v.getModelo());
            }

        } catch (Exception e) {
            System.out.println("Erro (" + e.getMessage()
                    + " ao tentar consultar dados!");
        } finally {
            manager.close();
            Conexao.close();
        }

        return "Operação realizada com sucesso!";
    }

    public String cunsultaCritariaWhere() {
        EntityManager manager = Conexao.getEntitymanager();

        try {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Veiculo> criteriaQuery = builder.createQuery(Veiculo.class);

            Root<Veiculo> veiculo = criteriaQuery.from(Veiculo.class);
            Predicate predicate = builder.not(builder.equal(veiculo.get("tipoCombustivel"),
                    TipoCombustivel.BIOCOMBUSTIVEL));

            criteriaQuery.select(veiculo);
            criteriaQuery.where(predicate);

            TypedQuery<Veiculo> query = manager.createQuery(criteriaQuery);
            List<Veiculo> veiculos = query.getResultList();

            for (Veiculo v : veiculos) {
                System.out.println(v.getModelo());
            }

        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage()
                    + " ao tentar consultar daods!");

        } finally {
            manager.close();
            Conexao.close();
        }

        return "Operação realizada com sucesso!";
    }

    public static List<Veiculo> pesquisarVeiculos(
            TipoCombustivel tipoCombustivel, BigDecimal maiorValor) {
        EntityManager manager = Conexao.getEntitymanager();

        try {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Veiculo> criteriaQuery = builder.createQuery(Veiculo.class);

            Root<Veiculo> veiculo = criteriaQuery.from(Veiculo.class);
            criteriaQuery.select(veiculo);

            List<Predicate> predicates = new ArrayList<>();

            if (tipoCombustivel != null) {
                ParameterExpression<TipoCombustivel> paramTipoCombustivel
                        = builder.parameter(TipoCombustivel.class, "tipoCombustivel");
                predicates.add(builder.equal(veiculo.get("tipoCombustivel"),
                        paramTipoCombustivel));
            }

            if (maiorValor != null) {
                ParameterExpression<BigDecimal> paramValor = builder.parameter(
                        BigDecimal.class, "maiorValor");
                predicates.add(builder.lessThanOrEqualTo(
                        veiculo.<BigDecimal>get("valor"), paramValor));
            }

            criteriaQuery.where(predicates.toArray(new Predicate[0]));

            TypedQuery<Veiculo> query = manager.createQuery(criteriaQuery);

            if (tipoCombustivel != null) {
                query.setParameter("tipoCombustivel", tipoCombustivel);
            }

            if (maiorValor != null) {
                query.setParameter("maiorValor", maiorValor);
            }

            List<Veiculo> veiculos = query.getResultList();
            return veiculos;

        } catch (Exception e) {
            System.out.println("Erro (" + e.getMessage()
                    + ") ao tenter consultar dados!");

        } finally {
            manager.close();
            Conexao.close();

        }

        return null;
    }

    public String cunsultaprojecoes() {
        EntityManager manager = Conexao.getEntitymanager();

        try {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<String> criteriaQuery = builder.createQuery(String.class);

            Root<Veiculo> veiculo = criteriaQuery.from(Veiculo.class);
            criteriaQuery.select(veiculo.<String>get("modelo"));

            TypedQuery<String> query = manager.createQuery(criteriaQuery);
            List<String> modelos = query.getResultList();

            for (String modelo : modelos) {
                System.out.println(modelo);
            }

        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage()
                    + " ao tentar consultar dados!");

        } finally {
            manager.close();
            Conexao.close();
        }

        return "Operação realizada com sucesso!";
    }

    public String consultaAgregacao() {
        EntityManager manager = Conexao.getEntitymanager();

        try {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<BigDecimal> criteriaQuery = builder.createQuery(
                    BigDecimal.class);

            Root<Veiculo> veiculo = criteriaQuery.from(Veiculo.class);
            criteriaQuery.select(builder.sum(veiculo.<BigDecimal>get("valor")));

            TypedQuery<BigDecimal> query = manager.createQuery(criteriaQuery);
            BigDecimal total = query.getSingleResult();

            System.out.println("Valor total: " + total);

        } catch (Exception e) {
            System.out.println("Erro (" + e.getMessage()
                    + ") ao tentar consultar doados!");

        } finally {
            manager.close();
            Conexao.close();
        }

        return "Operação realizada com sucesso!";
    }

    public String consultaComplexosObject() {
        EntityManager manager = Conexao.getEntitymanager();

        try {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);

            Root<Veiculo> veiculo = criteriaQuery.from(Veiculo.class);
            criteriaQuery.multiselect(veiculo.<String>get("modelo"),
                    veiculo.<String>get("valor"), veiculo.<String>get("fabricante"),
                    veiculo.<Integer>get("anoFabricacao"));

            TypedQuery<Object[]> query = manager.createQuery(criteriaQuery);
            List<Object[]> resultado = query.getResultList();

            for (Object[] valores : resultado) {
                System.out.println(
                        valores[0] + " - "
                        + valores[1] + " - "
                        + valores[2] + " - "
                        + valores[3]);

            }

        } catch (Exception e) {
            System.out.println(" Erro " + e.getMessage()
                    + " ao tentar consultar dados!");

        } finally {
            manager.close();
            Conexao.close();
        }

        return "Operação realizada com sucesso!";
    }

    public String consultaComplexosTuplas() {
        EntityManager manager = Conexao.getEntitymanager();

        try {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Tuple> criteriaQuery = builder.createTupleQuery();

            Root<Veiculo> veiculo = criteriaQuery.from(Veiculo.class);
            criteriaQuery.multiselect(
                    veiculo.<String>get("modelo").alias("modeloVeiculo"),
                    veiculo.<String>get("valor").alias("valorVeiculo"));

            TypedQuery<Tuple> query = manager.createQuery(criteriaQuery);

            List<Tuple> resultado = query.getResultList();

            for (Tuple tupla : resultado) {
                System.out.println(tupla.get("modeloVeiculo")
                        + " - " + tupla.get("valorVeiculo"));
            }

        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage()
                    + " ao tentar consultar dados!");

        } finally {
            manager.close();
            Conexao.close();
        }

        return "Operaçao realizada com sucesso!";
    }

    public String consultaComplexoConstrutores() {
        EntityManager manager = Conexao.getEntitymanager();

        try {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<PrecoVeiculo> criteriaQuery = builder
                    .createQuery(PrecoVeiculo.class);

            Root<Veiculo> veiculo = criteriaQuery.from(Veiculo.class);
            criteriaQuery.select(builder.construct(PrecoVeiculo.class,
                    veiculo.<String>get("modelo"), veiculo.<String>get("valor")));

            TypedQuery<PrecoVeiculo> query = manager.createQuery(criteriaQuery);
            List<PrecoVeiculo> resultado = query.getResultList();

            for (PrecoVeiculo tupla : resultado) {
                System.out.println(tupla.getModelo() + " - " + tupla.getValor());
            }

        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage()
                    + " ao tentar consultar dados!");

        } finally {
            manager.close();
            Conexao.close();
        }

        return "Operação realizada com sucesso!";
    }

    public String consultaComplexoFuncoes() {
        EntityManager manager = Conexao.getEntitymanager();

        try {

            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Veiculo> criteriaQuery = builder.createQuery(Veiculo.class);

            Root<Veiculo> veiculo = criteriaQuery.from(Veiculo.class);
            Predicate predicate = builder.equal(builder.upper(
                    veiculo.<String>get("modelo")), "Fusca".toLowerCase());

            criteriaQuery.select(veiculo);
            criteriaQuery.where(predicate);

            TypedQuery<Veiculo> query = manager.createQuery(criteriaQuery);
            List<Veiculo> veiculos = query.getResultList();

            for (Veiculo v : veiculos) {
                System.out.println(v.getModelo());

            }

        } catch (Exception e) {
            System.out.println("Erro (" + e.getMessage()
                    + " ao tentar consultar dados!");

        } finally {
            manager.close();
            Conexao.close();
        }

        return "Operação realizada com sucesso!";
    }

    public String consultaComplexoFuncoesList() {
        EntityManager manager = Conexao.getEntitymanager();

        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<String> criteriaQuery = builder.createQuery(String.class);

        Root<Veiculo> veiculo = criteriaQuery.from(Veiculo.class);

        Expression<String> expression = builder.concat(builder.concat(
                veiculo.<String>get("fabricante"), " - "),
                veiculo.<String>get("modelo"));
        criteriaQuery.select(expression);

        TypedQuery<String> query = manager.createQuery(criteriaQuery);
        List<String> veiculos = query.getResultList();

        for (String v : veiculos) {
            System.out.println(v);
        }

        return "Operação realizada com sucesso!";
    }

    public String consultaComplexoOrdenacao() {
        EntityManager manager = Conexao.getEntitymanager();

        try {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Veiculo> criteriaQuery = builder.createQuery(Veiculo.class);

            Root<Veiculo> veiculo = criteriaQuery.from(Veiculo.class);
            Order order = builder.asc(veiculo.<String>get("anoFabricacao"));

            criteriaQuery.select(veiculo);
            criteriaQuery.orderBy(order);

            TypedQuery<Veiculo> query = manager.createQuery(criteriaQuery);
            List<Veiculo> veiculos = query.getResultList();

            for (Veiculo v : veiculos) {
                System.out.println(v.getModelo() + " - " + v.getAnoFabricacao());

            }

        } catch (Exception e) {
            System.out.println("Erro (" + e.getMessage()
                    + ") ao consultar dados!");

        } finally {
            manager.close();
            Conexao.close();
        }

        return "Opeeração realizada com sucesso!";
    }

    public String consultaComplexoJoin() {
        EntityManager manager = Conexao.getEntitymanager();

        try {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Veiculo> criteriaQuery = builder.createQuery(Veiculo.class);

            Root<Veiculo> veiculo = criteriaQuery.from(Veiculo.class);
            Join<Veiculo, Proprietario> proprietario = veiculo.join("proprietario");

            criteriaQuery.select(veiculo);
            criteriaQuery.where(builder.equal(proprietario.get("nome"), "Luis Magno"));

            TypedQuery<Veiculo> query = manager.createQuery(criteriaQuery);
            List<Veiculo> veiculos = query.getResultList();

            for (Veiculo v : veiculos) {
                System.out.println(v.getModelo() + " - "
                        + v.getProprietario().getNome());
            }

        } catch (Exception e) {
            System.out.println("Erro " + e.getMessage()
                    + " ao tentar consultar dados!");

        } finally {
            manager.close();
            Conexao.close();
        }

        return "Operação realizada com sucesso!";
    }

    public String consultsComplexoFtch() {
        EntityManager manager = Conexao.getEntitymanager();

        try {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Veiculo> criteriaQuery = builder.createQuery(Veiculo.class);

            Root<Veiculo> veiculo = criteriaQuery.from(Veiculo.class);
            Join<Veiculo, Proprietario> proprietario = (Join) veiculo.fetch(
                    "proprietario");

            criteriaQuery.select(veiculo);
            criteriaQuery.where(builder.equal(proprietario.get("nome"), "Adrinaldo"));

            TypedQuery<Veiculo> query = manager.createQuery(criteriaQuery);
            List<Veiculo> veiculos = query.getResultList();

            for (Veiculo v : veiculos) {
                System.out.println(v.getModelo() + " - "
                        + v.getProprietario().getNome());
            }

        } catch (Exception e) {
            System.out.println("Erro (" + e.getMessage()
                    + " ao tentar consultar dados!");

        } finally {
            manager.close();
            Conexao.close();
        }

        return "Operação realizada com sucesso!";
    }

    public String consultaComplexoSubqueries() {
        EntityManager manager = Conexao.getEntitymanager();

        try {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Veiculo> criteriaQuery = builder.createQuery(Veiculo.class);

            Subquery<Double> subquery = criteriaQuery.subquery(Double.class);

            Root<Veiculo> veiculoA = criteriaQuery.from(Veiculo.class);
            Root<Veiculo> veiculoB = subquery.from(Veiculo.class);

            subquery.select(builder.avg(veiculoB.<Double>get("valor")));

            criteriaQuery.select(veiculoA);
            criteriaQuery.where(builder.greaterThanOrEqualTo(veiculoA.<Double>get("valor"),
                    subquery));

            TypedQuery<Veiculo> query = manager.createQuery(criteriaQuery);
            List<Veiculo> veiculos = query.getResultList();

            for (Veiculo v : veiculos) {
                System.out.println(v.getModelo() + " - "
                        + v.getProprietario().getNome());

            }

        } catch (Exception e) {
            System.out.println("Erro (" + e.getMessage()
                    + ") ao tentar consultar dados!");

        } finally {
            manager.close();
            Conexao.close();
        }

        return "Operação realizada com sucesso!";
    }

    public String conasultaComplexoMetamodel() {
        EntityManager manager = Conexao.getEntitymanager();

        try {
            CriteriaBuilder builder = manager.getCriteriaBuilder();
            CriteriaQuery<Veiculo> criteriaQuery = builder.createQuery(Veiculo.class);

            Root<Veiculo> veiculo = criteriaQuery.from(Veiculo.class);
            Join<Veiculo, Proprietario> proprietario = veiculo.join(
                    Veiculo_.proprietario);

            criteriaQuery.select(veiculo);
            criteriaQuery.where(builder.equal(proprietario.get(Proprietario_.nome),
                    "Luis Magno"));

            TypedQuery<Veiculo> query = manager.createQuery(criteriaQuery);
            List<Veiculo> veiculos = query.getResultList();

            for (Veiculo v : veiculos) {
                System.out.println(v.getModelo() + " - "
                        + v.getProprietario().getNome());
            }

        } catch (Exception e) {
            System.out.println("Erro (" + e.getMessage()
                    + ") ao tentar consultar dados!");
        } finally {
            manager.close();
            Conexao.close();
        }

        return "Operação realizada com sucesso!";
    }
}
