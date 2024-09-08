package com.uretim.izleme.services;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.uretim.izleme.entity.MakineVerisi;
import com.uretim.izleme.repository.MakineVerisiRepository;

@Service
public class MakineVerisiService {

    private final MakineVerisiRepository repository;

    public MakineVerisiService(MakineVerisiRepository repository) {
        this.repository = repository;
    }

    public List<MakineVerisi> getVeriByMakine(String makine) {
        return repository.findByMakine(makine);
    }
    
    public Map<String, Map<String, Long>> getStatusCountsByMakine() {
        List<MakineVerisi> data = repository.findAll();

        return data.stream()
                .collect(Collectors.groupingBy(
                        MakineVerisi::getMakine,
                        Collectors.groupingBy(
                                MakineVerisi::getStatus,
                                Collectors.counting()
                        )
                ));
    }
}
