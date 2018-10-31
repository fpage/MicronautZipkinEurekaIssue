/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package micronaut.zipkin.eureka.issue;

/**
 *
 * @author FrancisPage
 */
import io.micronaut.context.ApplicationContext;
import io.micronaut.context.env.PropertySource;
import io.micronaut.core.util.CollectionUtils;
import io.micronaut.http.HttpMethod;
import io.micronaut.web.router.Router;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestControllerRegistration {

    private ApplicationContext server;

    @Before
    public void setup() {

    }

    @Test
    public void testEurekaZipkin() {
        try {
            this.server = ApplicationContext.run(PropertySource.of(
                    CollectionUtils.mapOf(
                            "tracing.zipkin.enabled", "true",
                            "eureka.client.registration.enabled", "true"
                    )
            )).start();

            Assert.assertTrue("books endpoint not present when zipkin enabled", server.getBean(Router.class).route(HttpMethod.GET, "/books").isPresent());
        } finally {
            this.server.stop();
        }

    }

    @Test
    public void testEurekaNoZipkin() {
        try {
            this.server = ApplicationContext.run(PropertySource.of(
                    CollectionUtils.mapOf(
                            "tracing.zipkin.enabled", "false",
                            "eureka.client.registration.enabled", "true"
                    )
            )).start();
            Assert.assertTrue(server.getBean(Router.class).route(HttpMethod.GET, "/books").isPresent());
        } finally {
            this.server.stop();
        }

    }
    
    @Test
    public void testNoEurekaZipkin() {
        try {
            this.server = ApplicationContext.run(PropertySource.of(
                    CollectionUtils.mapOf(
                            "tracing.zipkin.enabled", "true",
                            "eureka.client.registration.enabled", "false"
                    )
            )).start();
            Assert.assertTrue(server.getBean(Router.class).route(HttpMethod.GET, "/books").isPresent());
        } finally {
            this.server.stop();
        }

    }

}
