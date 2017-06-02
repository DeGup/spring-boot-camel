package io.fabric8.quickstarts.camel;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import static org.apache.camel.LoggingLevel.INFO;

@Component
public class ApplicationRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("{{route1.from.timer}}")
                .id("route1")
                .setBody().constant("Hello World")
                .log(" ${body} >>> to queue")
                .to("{{amq1.endpoint}}");

        from("{{amq1.endpoint}}")
                .id("amq1")
                .log(INFO, "Incoming: ${body}");
    }
}
