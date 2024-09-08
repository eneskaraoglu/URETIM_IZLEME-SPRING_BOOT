package com.uretim.izleme.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.uretim.izleme.entity.MakineVerisi;

public interface MakineVerisiRepository extends MongoRepository<MakineVerisi, String> {
    List<MakineVerisi> findByMakine(String makine); // Makine adına göre filtreleme
}
