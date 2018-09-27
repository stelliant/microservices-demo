package io.pivotal.microservices.itex.poller;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.GenericMessage;

@EnableAutoConfiguration
@EnableDiscoveryClient
public class ItexPoller {

	protected Logger logger = Logger.getLogger(ItexPoller.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(ItexPoller.class, args);
	}

	//Following source is used as test producer.
	@EnableBinding(Source.class)
	static class TestProducer {

		private AtomicBoolean semaphore = new AtomicBoolean(true);

		@Bean
		@InboundChannelAdapter(channel = Source.OUTPUT, poller = @Poller(fixedDelay = "15000"))
		public MessageSource<String> sendTestData() {
			return () ->
					new GenericMessage<>(this.semaphore.getAndSet(!this.semaphore.get()) ? "qualification" : "expertise");

		}
	}

}
