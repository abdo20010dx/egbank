package com.banking.egbank.shared.common.apiResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import com.banking.egbank.shared.common.translations.KeysMessages;
import com.banking.egbank.shared.common.translations.Langs;
import com.banking.egbank.shared.common.translations.TrMethods;

import java.util.HashMap;
import java.util.Map;

public class ResStructure {

    public static ResponseEntity<Object> successResponse(Langs lang, Object data, KeysMessages messageKey,
            HttpStatus status) {
        if (lang == null) {
            lang = Langs.EN;
        }

        Map<String, Object> response = new HashMap<>();
        response.put("data", data);
        response.put("status", Map.of("code", status.value(), "message", TrMethods.getMessage(lang, messageKey)));
        return new ResponseEntity<>(response, status);
    }

    public static ResponseEntity<Object> successResponse(Langs lang, Object data, KeysMessages messageKey,
            HttpStatus status,
            Map<String, Object> meta) {
        if (lang == null) {
            lang = Langs.EN;
        }

        Map<String, Object> response = new HashMap<>();
        response.put("data", data);
        response.put("status", Map.of("code", status.value(), "message", TrMethods.getMessage(lang, messageKey)));
        response.put("meta", meta);
        return new ResponseEntity<>(response, status);
    }

    public static ResponseEntity<Object> errorResponse(Langs lang, KeysMessages messageKey, HttpStatus status) {

        if (lang == null) {
            lang = Langs.EN;
        }
        Map<String, Object> response = new HashMap<>();
        response.put("errors", Map.of("message", TrMethods.getMessage(lang, messageKey)));
        response.put("status", Map.of("code", status.value(), "message", status.getReasonPhrase()));
        return new ResponseEntity<>(response, status);
    }

    public static ResponseEntity<Object> errorResponse(Langs lang, KeysMessages messageKey, HttpStatus status,
            String field,
            String errorCode) {
        Map<String, Object> response = new HashMap<>();
        if (lang == null) {
            lang = Langs.EN;
        }
        response.put("errors", Map.of(
                "message", TrMethods.getMessage(lang, messageKey),
                "field", field,
                "code", errorCode));
        response.put("status", Map.of("code", status.value(), "message", status.getReasonPhrase()));
        return new ResponseEntity<>(response, status);
    }

    public static void notFoundRes(Langs lang, KeysMessages messageKey) {
        if (lang == null) {
            lang = Langs.EN;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, TrMethods.getMessage(lang, messageKey));

    }
}
