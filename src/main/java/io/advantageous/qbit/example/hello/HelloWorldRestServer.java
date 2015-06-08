package io.advantageous.qbit.example.hello;


import io.advantageous.boon.core.IO;
import io.advantageous.boon.core.Str;
import io.advantageous.qbit.http.server.HttpServer;
import io.advantageous.qbit.server.ServiceEndpointServer;
import io.advantageous.qbit.system.QBitSystemManager;

import static io.advantageous.qbit.http.server.HttpServerBuilder.httpServerBuilder;
import static io.advantageous.qbit.server.EndpointServerBuilder.endpointServerBuilder;


/**
 * Created by rhightower on 2/9/15.
 */
public class HelloWorldRestServer {


    public static final String HTML_HELLO_PAGE = "/ui/helloWorld.html";


    public static void main(String... args) {

        /* Create the system manager to manage the shutdown. */
        QBitSystemManager systemManager = new QBitSystemManager();

        HttpServer httpServer = httpServerBuilder()
                .setPort(9999).build();

        /* Register the Predicate using a Java 8 lambda expression. */
        httpServer.setShouldContinueHttpRequest(httpRequest -> {
            /* If not the page uri we want to then just continue by returning true. */
            if (!httpRequest.getUri().equals(HTML_HELLO_PAGE)) {
                return true;
            }
            /* read the page from the file system or classpath. */
            final String helloWorldWebPage = resource(HTML_HELLO_PAGE);
            /* Send the HTML file out to the browser. */
            httpRequest.getReceiver().response(200, "text/html", helloWorldWebPage);
            return false;
        });


        /* Start the service. */
        final ServiceEndpointServer serviceServer = endpointServerBuilder().setSystemManager(systemManager)
                .setHttpServer(httpServer).build().initServices(new HelloService()).startServer();

        /* Wait for the service to shutdown. */
        systemManager.waitForShutdown();


    }

    private static String resource(String path) {
        if (!IO.exists(IO.path(path))) {
            path = add(new String[]{"classpath:/", path});
        }
        String str = IO.read(path);
        return str;
    }
    public static String add(String... args) {
        return Str.add(args);
    }
}



