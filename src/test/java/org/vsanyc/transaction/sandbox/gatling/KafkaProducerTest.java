package org.vsanyc.transaction.sandbox.gatling;

import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.apache.kafka.common.header.internals.RecordHeaders;
import ru.tinkoff.gatling.kafka.javaapi.protocol.KafkaProtocolBuilder;

import java.time.Duration;
import java.util.Map;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;
import static ru.tinkoff.gatling.kafka.javaapi.KafkaDsl.kafka;

public class KafkaProducerTest extends Simulation {

    private final KafkaProtocolBuilder kafkaProtocol = kafka()
            .topic("speakerLikeTopic")
            .properties(Map.of(ProducerConfig.ACKS_CONFIG, "1",
                    ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:29092",
                    ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer",
                    ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG , "org.apache.kafka.common.serialization.StringSerializer"));
    private final Headers headers = new RecordHeaders(new Header[]{new RecordHeader("test-header", "value".getBytes())});
    private final ScenarioBuilder kafkaProducer = scenario("Kafka Producer")
            .exec(kafka("Simple Message")
                    .send("key","{\"speakerName\": \"speaker_name2\",\"threadName\": \"kafka-thread-1\"}", headers)

            );
    {

        setUp(
                kafkaProducer.injectOpen(incrementUsersPerSec(1000)
                        .times(1)
                        .eachLevelLasting(2)
                        //.separatedByRampsLasting(10)
                        .startingFrom(1))
        ).protocols(kafkaProtocol);
    }

}
