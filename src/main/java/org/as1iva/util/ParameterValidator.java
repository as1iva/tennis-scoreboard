package org.as1iva.util;

import lombok.experimental.UtilityClass;
import org.as1iva.exception.InvalidDataException;

@UtilityClass
public class ParameterValidator {

    public static void checkNameEquality(String firstPlayerName, String secondPlayerName) {
        if (firstPlayerName.equals(secondPlayerName)) {
            throw new InvalidDataException("Players must be different");
        }
    }
}
