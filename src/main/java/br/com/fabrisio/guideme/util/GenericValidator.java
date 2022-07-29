package br.com.fabrisio.guideme.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GenericValidator {

    public static boolean validate(String characters, String regex) {
        var pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        var matcher = pattern.matcher(characters);
        return matcher.find();
    }
}

