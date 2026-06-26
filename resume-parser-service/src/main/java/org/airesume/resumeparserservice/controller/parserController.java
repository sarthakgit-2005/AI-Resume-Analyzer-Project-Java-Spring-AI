package org.airesume.resumeparserservice.controller;

import org.airesume.resumeparserservice.dto.parserRequestDTO;
import org.airesume.resumeparserservice.dto.parserResponseDTO;
import org.airesume.resumeparserservice.service.parserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/parser")
class parserController {
    private final parserService parserService;

    public parserController(parserService parserService) {
        this.parserService = parserService;
    }

    @PostMapping("/parse")
    public parserResponseDTO parseResume(@RequestBody parserRequestDTO request) throws Exception{
        return parserService.parseResume(request.getResumeText());
    }
}
