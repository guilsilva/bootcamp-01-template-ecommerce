package br.com.bootcamp.ecommerce.api.validator;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

    private String domainAttribute;
    private Class<?> klass;
    @PersistenceContext
    private EntityManager manager;

    @Override
    public void initialize(UniqueValue constraintAnnotation) {
        domainAttribute = constraintAnnotation.fieldName();
        klass = constraintAnnotation.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if(value == null) return Boolean.TRUE;
        Query query = manager.createQuery("select 1 from "+klass.getName()+
                " where "+domainAttribute+"=:value");
        query.setParameter("value", value);
        List<?> resultado = query.getResultList();
        Assert.state(resultado.isEmpty(), "Já existe um "+klass.getSimpleName()+
                " com o atributo "+domainAttribute+" = "+value.toString());
        return Boolean.TRUE;
    }
}
