package br.com.zupacademy.erivelton.mercadolivre.validacao.validador;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.zupacademy.erivelton.mercadolivre.validacao.anotacao.UniqueValue;

public class ValorUnicoValidacao implements ConstraintValidator<UniqueValue, Object>{

	@PersistenceContext
	private EntityManager em;
	
	private String atributo;
	private Class<?> classe;
	
	@Override
	public void initialize(UniqueValue constraintAnnotation) {
		atributo = constraintAnnotation.atributo();
		classe = constraintAnnotation.classe();
	}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		Query query = em.createQuery("select 1 from " + classe.getName() + " where " + atributo + " = :valor");
		query.setParameter("valor", value);
		return query.getResultList().isEmpty();
	}
	
	
}
