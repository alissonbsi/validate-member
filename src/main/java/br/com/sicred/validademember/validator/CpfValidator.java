package br.com.sicred.validademember.validator;

import br.com.sicred.validademember.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import br.com.caelum.stella.validation.CPFValidator;

import static org.apache.commons.lang3.BooleanUtils.isFalse;
import static org.apache.logging.log4j.util.Strings.isNotEmpty;

@Slf4j
public class CpfValidator {

    public static void acceptDecoded(final String cpf) {
        if (isFalse(test(removeCaracters(cpf)))) {
            log.warn("Invalid CPF!");
            throw new NotFoundException("Invalid CPF!");
        }
    }

    private static boolean test(final String cpf) {
        return isNotEmpty(cpf)
            && new CPFValidator().invalidMessagesFor(cpf).isEmpty();
    }

    private static String removeCaracters(final String cpf) {
        return cpf.replaceAll("[^0-9]", "");
    }
}