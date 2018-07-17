package br.com.aplicacao.modelo;

import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Veiculo.class)
public abstract class Veiculo_ {

	public static volatile SingularAttribute<Veiculo, Long> codigo;
	public static volatile SingularAttribute<Veiculo, TipoCombustivel> tipoCombustivel;
	public static volatile SingularAttribute<Veiculo, Proprietario> proprietario;
	public static volatile SingularAttribute<Veiculo, BigDecimal> valor;
	public static volatile SingularAttribute<Veiculo, Integer> anoFabricacao;
	public static volatile SetAttribute<Veiculo, Acessorio> acessorios;
	public static volatile SingularAttribute<Veiculo, String> fabricante;
	public static volatile SingularAttribute<Veiculo, Integer> anoModelo;
	public static volatile SingularAttribute<Veiculo, String> modelo;
	public static volatile SingularAttribute<Veiculo, Date> dataCadastro;

}

