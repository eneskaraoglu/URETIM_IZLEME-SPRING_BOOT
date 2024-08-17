package com.uretim.izleme.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class UretimTestController {
	
	@GetMapping("/makineListesi")
	public String getMakineListesi() {
		String result = "Makine listesi";
		return result;
	}
}
