package br.com.aplicacao.modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Acessorio.class)
public abstract class Acessorio_ {

	public static volatile SingularAttribute<Acessorio, Long> codigo;
	public static volatile SetAttribute<Acessorio, Veiculo> veiculos;
	public static volatile SingularAttribute<Acessorio, String> descricao;

}

