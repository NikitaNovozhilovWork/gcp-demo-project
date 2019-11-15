package com.nikitanov.demo.gcp.demoproject.gitterlistener.services;

import com.google.cloud.translate.Detection;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import org.springframework.stereotype.Service;

/**
 * Service to translate text from any language (except english) to english
 * analyzes incoming text through cloud translation detection api and if it is not in english
 * translate it through cloud translation translate api
 */
@Service
public class TranslationService {

    private static final String ENGLISH_LANGUAGE = "en";

    public String translate(String text) {
        Translate translator = TranslateOptions.getDefaultInstance().getService();

        Detection detection = translator.detect(text);
        String detectedLanguage = detection.getLanguage();

        if (!ENGLISH_LANGUAGE.equals(detectedLanguage)) {
            Translation translation = translator.translate(
                    text,
                    Translate.TranslateOption.sourceLanguage(detectedLanguage),
                    Translate.TranslateOption.targetLanguage(ENGLISH_LANGUAGE));

            return translation.getTranslatedText();
        }

        return text;
    }

}
