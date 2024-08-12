package com.banking.egbank.shared.common.translations;

public class TrMethods {

    public static String getMessage(Langs lang, KeysMessages key) {
        String message;
        if (lang == Langs.EN) {
            message = EnMessages.CLIENT_EN_MESS.get(key);
        } else {
            message = "Unknown language";
        }

        return message;
    }
}
