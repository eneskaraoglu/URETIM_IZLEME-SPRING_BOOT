package com.uretim.izleme.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uretim.izleme.services.MakineVerisiService;

@RestController
@RequestMapping("/api")
public class StatusController {

	private final MakineVerisiService service;
	
    public StatusController(MakineVerisiService service) {
        this.service = service;
    }
	
    @GetMapping("/status-counts")
    public Map<String, Map<String, Long>> getStatusCounts() {
    	System.out.println("-------------------------------------------------------------");
    	printStatusCounts(service.getStatusCountsByMakine());
        return service.getStatusCountsByMakine();
    }
    
    public void printStatusCounts(Map<String, Map<String, Long>> statusCounts) {
        // Ana map üzerinde iterasyon yap
        for (Map.Entry<String, Map<String, Long>> entry : statusCounts.entrySet()) {
            String makine = entry.getKey(); // Anahtar, yani makine ismi
            Map<String, Long> statuses = entry.getValue(); // İç map, yani status ve count

            System.out.println("-----------------Makine: " + makine);

            // İç map üzerinde iterasyon yap
            for (Map.Entry<String, Long> statusEntry : statuses.entrySet()) {
                String status = statusEntry.getKey(); // Status ismi
                Long count = statusEntry.getValue(); // Count değeri

                System.out.println("\tStatus: " + status + ", Count: " + count);
            }
        }
    }
}
