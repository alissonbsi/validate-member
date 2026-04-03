package br.com.sicred.validademember.service;


import br.com.sicred.validademember.api.controller.dto.response.DocumentStatusResponse;
import br.com.sicred.validademember.domain.StatusMember;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Random;

import static br.com.sicred.validademember.validator.CpfValidator.acceptDecoded;

@Slf4j
@Service
@RequiredArgsConstructor
public class ValidateDocumentService {

    public DocumentStatusResponse getStatus(final String cpf) {
        acceptDecoded(cpf);

        StatusMember status = isAble(cpf)
            ? StatusMember.ABLE_TO_VOTE
            : StatusMember.UNABLE_TO_VOTE;

        return new DocumentStatusResponse(status);
    }

    private boolean isAble(String cpf) {
        Random random = new Random();
        int numero = random.nextInt(10);
        return cpf.endsWith(String.valueOf(numero)) || cpf.startsWith(String.valueOf(numero));
    }
}
