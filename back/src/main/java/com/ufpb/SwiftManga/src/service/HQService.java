package com.ufpb.swiftmanga.src.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.ufpb.swiftmanga.src.dto.HQDto;
import com.ufpb.swiftmanga.src.model.HQ;
import com.ufpb.swiftmanga.src.repository.HQRepository;

@Service
public class HQService {

    @Autowired
    private HQRepository hqRepository;

    public HQDto createHQ(HQDto hqDto) {
        HQ hq = new HQ();
        hq.setTitle(hqDto.getTitle());
        hq.setArtist(hqDto.getArtist());
        hq.setPublisher(hqDto.getPublisher());
        hq.setIssue(hqDto.getIssue());
        hq.setClassification(hqDto.getClassification());
        hqRepository.save(hq);
        return hqDto; // Conversion to DTO should be implemented
    }

    public List<HQDto> getAllHQs() {
        return hqRepository.findAll().stream().map(hq -> {
            HQDto dto = new HQDto();
            dto.setId(hq.getId());
            dto.setTitle(hq.getTitle());
            dto.setArtist(hq.getArtist());
            dto.setPublisher(hq.getPublisher());
            dto.setIssue(hq.getIssue());
            dto.setClassification(hq.getClassification());
            return dto;
        }).collect(Collectors.toList());
    }

    public HQDto getHQById(Long hqId) {
        HQ hq = hqRepository.findById(hqId)
            .orElseThrow(() -> new ResourceNotFoundException("HQ not found with id: " + hqId));
        HQDto dto = new HQDto();
        dto.setId(hq.getId());
        dto.setTitle(hq.getTitle());
        dto.setArtist(hq.getArtist());
        dto.setPublisher(hq.getPublisher());
        dto.setIssue(hq.getIssue());
        dto.setClassification(hq.getClassification());
        return dto;
    }

    public HQDto updateHQ(Long hqId, HQDto hqDto) {
        HQ hq = hqRepository.findById(hqId)
            .orElseThrow(() -> new ResourceNotFoundException("HQ not found with id: " + hqId));
        hq.setTitle(hqDto.getTitle());
        hq.setArtist(hqDto.getArtist());
        hq.setPublisher(hqDto.getPublisher());
        hq.setIssue(hqDto.getIssue());
        hq.setClassification(hqDto.getClassification());
        hqRepository.save(hq);
        return hqDto;
    }

    public void deleteHQ(Long hqId) {
        hqRepository.deleteById(hqId);
    }
}
