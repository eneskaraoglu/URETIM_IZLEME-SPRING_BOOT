package com.uretim.izleme.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.uretim.izleme.entity.MakineVerisi;
import com.uretim.izleme.services.MakineVerisiService;

import java.util.List;
import java.util.Map;

@Controller
public class PageController {

    private final MakineVerisiService service;

    public PageController(MakineVerisiService service) {
        this.service = service;
    }

    @GetMapping("/makine-verisi")
    public List<MakineVerisi> getMakineVerisi(@RequestParam String makine) {
        return service.getVeriByMakine(makine);
    }
    
	@GetMapping("/status")
	public String getStatus(Model model) {
		model.addAttribute("message", "Canlı makine verileri burada görüntülenecek.");
		return "status";
	}
	
    @GetMapping("/canliveriler")
    public String getCanliVeriler(Model model) {
    	System.out.println("-----------");
        model.addAttribute("canliveriler", "Canlı makine verileri burada görüntülenecek.");

        // canli-veriler.html Thymeleaf şablonunu döner
        return "canliveriler";
    }
    
    @GetMapping("/canli-veriler")
    public String getCanliVeriler() {
        return "canliVeriler"; // Thymeleaf şablonu
    }

    @GetMapping("/makine-tanimlamalari")
    public String getMakineTanimlamalari() {
        return "makineTanimlamalari"; // Thymeleaf şablonu
    }

    @GetMapping("/kullanici-tanimlamalari")
    public String getKullaniciTanimlamalari() {
        return "kullaniciTanimlamalari"; // Thymeleaf şablonu
    }

    @GetMapping("/makine-ozet")
    public String getMakineOzetBilgileri() {
        return "makineOzetBilgileri"; // Thymeleaf şablonu
    }
    
    @GetMapping("/plain-page")
    public String plainPage() {
        return "plain-page";
    }

    @GetMapping("/pricing-tables")
    public String pricingTables() {
        return "pricing-tables";
    }
    
    @GetMapping("/index.html")
    public RedirectView redirectToIndexHtm() {
        return new RedirectView("/plain-page");
    }
    
}
