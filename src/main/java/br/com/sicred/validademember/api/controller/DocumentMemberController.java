package br.com.sicred.validademember.api.controller;

import br.com.sicred.validademember.api.controller.dto.response.DocumentStatusResponse;
import br.com.sicred.validademember.service.ValidateDocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/users/{cpf}")
public class DocumentMemberController {

    private final ValidateDocumentService service;

    @GetMapping
    @ResponseStatus(OK)
    public DocumentStatusResponse getStatus(@PathVariable String cpf) {
        return service.getStatus(cpf);
    }
}
