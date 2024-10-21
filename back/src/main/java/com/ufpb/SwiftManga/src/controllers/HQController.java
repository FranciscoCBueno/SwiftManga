package com.ufpb.SwiftManga.src.controllers;

import com.ufpb.SwiftManga.src.dto.HQDTO;
import com.ufpb.SwiftManga.src.service.HQService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hqs")
public class HQController {

    @Autowired
    private HQService hqService;


    @GetMapping("/user/{userId}")
    public List<HQDTO> getHQsByUserId(@PathVariable Long userId) {
        return hqService.findAllByUserId(userId);
    }

    @GetMapping("/{hqId}")
    public Optional<HQDTO> getHQById(@PathVariable Long hqId) {
        return hqService.findById(hqId);
    }

    @PostMapping
    public ResponseEntity<HQDTO> createHQ(@RequestBody HQDTO hqDTO) {
        HQDTO createdHQ = hqService.saveHQ(hqDTO);
        return ResponseEntity.status(201).body(createdHQ); // Retorna 201 Created
    }

    @PutMapping("/{hqId}")
    public ResponseEntity<HQDTO> updateHQ(@PathVariable Long hqId, @RequestBody HQDTO hqDTO) {
        hqDTO.setId(hqId);
        HQDTO updatedHQ = hqService.saveHQ(hqDTO);
        return ResponseEntity.ok(updatedHQ);
    }

    @DeleteMapping("/{hqId}")
    public String deleteHQ(@PathVariable Long hqId) {
        hqService.deleteHQ(hqId);
        return "HQ removida com sucesso.";
    }
}
