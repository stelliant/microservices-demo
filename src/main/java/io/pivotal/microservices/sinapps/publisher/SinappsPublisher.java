package io.pivotal.microservices.sinapps.publisher;

import java.util.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableAutoConfiguration
@EnableDiscoveryClient
public class SinappsPublisher {

	protected Logger logger = Logger.getLogger(SinappsPublisher.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(SinappsPublisher.class, args);
	}

	@EnableBinding(Sink.class)
	static class TestConsumer {

		private final Logger logger = Logger.getLogger(TestConsumer.class.getName());

		@StreamListener(Sink.INPUT)
		public void receive(String data) {
			logger.info("Data received..." + data);
		}
	}
}
