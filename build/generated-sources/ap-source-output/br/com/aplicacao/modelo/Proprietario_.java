package br.com.aplicacao.modelo;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Proprietario.class)
public abstract class Proprietario_ {

	public static volatile SingularAttribute<Proprietario, Long> codigo;
	public static volatile SingularAttribute<Proprietario, String> telefone;
	public static volatile ListAttribute<Proprietario, Veiculo> veiculos;
	public static volatile SingularAttribute<Proprietario, String> nome;
	public static volatile SingularAttribute<Proprietario, String> email;

}

