package com.uretim.izleme.services;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.uretim.izleme.entity.MakineVerisi;
import com.uretim.izleme.repository.MakineVerisiRepository;

import javax.annotation.PostConstruct;
import java.util.Date;

@Service
public class MqttListenerService implements MqttCallback {

    @Value("${mqtt.broker-url}")
    private String brokerUrl;

    @Value("${mqtt.client-id}")
    private String clientId;

    @Value("${mqtt.topic}")
    private String topic;

    private final MakineVerisiRepository repository;

    public MqttListenerService(MakineVerisiRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void start() {
        try {
            MqttClient client = new MqttClient(brokerUrl, clientId);
            client.setCallback(this);
            client.connect();
            client.subscribe(topic);
            System.out.println("MQTT'ye bağlanıldı ve " + topic + " konusuna abone olundu.");
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void connectionLost(Throwable cause) {
        System.out.println("Bağlantı kaybedildi: " + cause.getMessage());
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        String payload = new String(message.getPayload());
        System.out.println("Gelen mesaj: " + payload);

        // Gelen mesajı Java nesnesine dönüştür
       //MakineVerisi verisi = new MakineVerisi();
        //verisi.setTime(new Date()); // Zamanı ayarla
        //verisi.setMakine("C001"); // Örnek makine verisi
        //verisi.setStatus("ÇALIŞIYOR"); // Örnek status verisi
        //verisi.setYuk(5); // Örnek yük verisi

        // Mesajdan JSON parse edebilir ve nesneye set edebilirsiniz:
         ObjectMapper mapper = new ObjectMapper();
         MakineVerisi verisi = mapper.readValue(payload, MakineVerisi.class);

        // Veriyi MongoDB'ye kaydet
        repository.save(verisi);
        System.out.println("Veri kaydedildi: " + verisi);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        // Mesaj teslimi tamamlandığında yapılacak işlemler
    }
}
