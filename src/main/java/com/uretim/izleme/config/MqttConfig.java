package com.uretim.izleme.config;

import java.io.IOException;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uretim.izleme.entity.MakineVerisi;
import com.uretim.izleme.repository.MakineVerisiRepository;

@Configuration
public class MqttConfig {
	
    @Autowired
    private MyWebSocketHandler webSocketHandler;  // WebSocket handler'ı enjekte ediyoruz


    private static final String MQTT_BROKER_URL = "tcp://localhost:1883";
    private static final String MQTT_CLIENT_ID = "spring-boot-client";
    private static final String MQTT_TOPIC = "dummy/data";

    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();
        options.setServerURIs(new String[]{MQTT_BROKER_URL});
        factory.setConnectionOptions(options);
        return factory;
    }

    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    public MqttPahoMessageDrivenChannelAdapter inbound() {
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(MQTT_CLIENT_ID, mqttClientFactory(), MQTT_TOPIC);
        adapter.setCompletionTimeout(5000);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }



    @Autowired
    private MakineVerisiRepository makineDataRepository;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
        return message -> {
            String payload = (String) message.getPayload();
            System.out.println("Received message: " + payload);

            // JSON'u MakineData nesnesine çevir
            try {
            	MakineVerisi makineData = objectMapper.readValue(payload, MakineVerisi.class);
                // MongoDB'ye kaydet
                makineDataRepository.save(makineData);

                // WebSocket ile frontend'e gönder
                webSocketHandler.sendMessageToAll(payload);
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }

}
