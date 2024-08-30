package com.uretim.izleme.services;

import org.springframework.stereotype.Service;

@Service
public class MqttMessageProcessorService {

    public void processMessage(String message) {
        // Gelen MQTT mesajını işleme
        System.out.println("Processing MQTT message: " + message);
        // Burada işleme mantığını ekleyin (Veritabanına kaydetme, işleme vb.)
    }
}