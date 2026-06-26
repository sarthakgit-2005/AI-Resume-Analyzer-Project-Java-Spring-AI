package org.airesume.aiservice.controller;

import org.airesume.aiservice.dto.aiRequestDTO;
import org.airesume.aiservice.dto.aiResponseDTO;
import org.airesume.aiservice.service.aiService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/ai")
class aiController {
    private final aiService service;
    public aiController(aiService service) {
        this.service = service;
    }

    @PostMapping("/analyze")
    public ResponseEntity<aiResponseDTO> analyzeResume(@RequestBody aiRequestDTO requestDTO)
    {
        aiResponseDTO responseDTO=service.analyzeResume(requestDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

}
