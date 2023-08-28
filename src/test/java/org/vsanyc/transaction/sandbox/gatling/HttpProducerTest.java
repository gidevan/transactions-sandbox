package org.vsanyc.transaction.sandbox.gatling;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;




import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

public class HttpProducerTest extends Simulation {

    HttpProtocolBuilder httpProtocol = http.baseUrl("http://localhost:8080")
            .header("Content-Type", "application/json")
            .header("Accept-Encoding", "gzip")
            .check(status().is(200));

    ScenarioBuilder scn = scenario("Like speaker")
            .exec(http("like speaker 2 _1")
                    .post("/speaker_name2"))
            .exec(http("like speaker2 _2").post("/speaker_name2"));

    {
        setUp(scn.injectOpen(constantUsersPerSec(1000).during(Duration.ofMillis(1000))))
                .protocols(httpProtocol);
    }

}
