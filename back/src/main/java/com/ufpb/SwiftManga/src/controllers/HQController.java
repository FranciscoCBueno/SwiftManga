package com.ufpb.SwiftManga.src.controllers;

import com.ufpb.SwiftManga.src.dto.HQDto;
import com.ufpb.SwiftManga.src.service.HQService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api")
public class HQController {
    private final HQService hqService;

    public HQController(HQService hqService) {
        this.hqService = hqService;
    }

    @GetMapping(path="/hq")
    public List<HQDto> listHQs() {
        return hqService.getAllHQs();
    }

    @GetMapping(path="/hq/{hqId}")
    public HQDto getHQById(@PathVariable Long hqId) {
        return hqService.getHQById(hqId);
    }

    @PostMapping(path="/createHQ")
    public HQDto createHQ(@RequestBody @Valid HQDto hqDto) {
        return hqService.createHQ(hqDto);
    }

    @PutMapping(path="/updateHQ/{hqId}")
    public HQDto updateHQ(@PathVariable Long hqId, @RequestBody @Valid HQDto hq) {
        return hqService.updateHQ(hqId, hq);
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(path="deleteHQ/{hqId}")
    public void deleteHQ(@PathVariable Long hqId) {
        hqService.deleteHQ(hqId);
    }
}
