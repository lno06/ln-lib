package com.lnlib.springboot.failureanalyzer;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.diagnostics.AbstractFailureAnalyzer;
import org.springframework.boot.diagnostics.FailureAnalysis;

/**
 * Offers a way to intercept exceptions that occur during the startup of an application causing an
 * application startup failure
 * <p>
 * Can be bound to PortInUseException, NoUniqueBeanDefinitionException, ...
 * <p>
 * The list of analyzers are defined in META-INF/spring.factories
 */
public class MyFailureAnalyzer extends AbstractFailureAnalyzer<NoSuchBeanDefinitionException>
{
    @Override
    protected FailureAnalysis analyze(Throwable rootFailure, NoSuchBeanDefinitionException cause)
    {
        return new FailureAnalysis(getDescription(cause), getAction(cause), cause);
    }

    private String getAction(NoSuchBeanDefinitionException cause)
    {
        return "Consider creating a bean with name " + cause.getResolvableType();
    }

    private String getDescription(NoSuchBeanDefinitionException cause)
    {
        return "Oops with field " + cause.getResolvableType()
                + " could not find autowired type";
    }
}
