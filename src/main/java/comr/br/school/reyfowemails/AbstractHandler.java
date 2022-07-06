package comr.br.school.reyfowemails;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.ParameterizedType;

public abstract class AbstractHandler<T> {

    /**
     * Spring IOC application context
     */
    private ApplicationContext applicationContext;

    /**
     * Every handler can override this String variable to provide an example event JSON for local running
     *
     * @link https://github.com/cagataygurturk/aws-lambda-local-runner
     */
    protected String exampleEvent = "{}";

    public AbstractHandler() {
        /**
         * Gets config class to create an Application context
         */
        Class typeParameterClass = ((Class) ((ParameterizedType) getClass()
                .getGenericSuperclass())
                .getActualTypeArguments()[0]);

        /**
         * Check if T has @Configuration annotation,
         * if no throws an exception
         */
        if (!typeParameterClass.isAnnotationPresent(Configuration.class)) {
            throw new RuntimeException(typeParameterClass + " is not a @Configuration class");
        }

        /**
         * Create Spring application context
         */
        applicationContext = new AnnotationConfigApplicationContext(typeParameterClass);
    }

    /**
     * Use this getter to access to Spring Application Context
     *
     * @return ApplicationContext
     */
    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * This method is only used by local lambda runner.
     *
     * @return String
     */
    public String getExampleEvent() {
        return exampleEvent;
    }
}