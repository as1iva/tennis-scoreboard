package org.as1iva.util;

import lombok.experimental.UtilityClass;
import org.as1iva.exception.DataNotFoundException;
import org.as1iva.exception.InvalidDataException;

import java.util.regex.Pattern;

@UtilityClass
public class ParameterValidator {

    public static void checkNameEquality(String firstPlayerName, String secondPlayerName) {
        if (firstPlayerName.equals(secondPlayerName)) {
            throw new InvalidDataException("Players must be different");
        }
    }

    public static int checkPageParameter(String pageParam) {
        Pattern pattern = Pattern.compile("^[1-9]\\d*$");
        int page;

        if (pageParam == null || pageParam.isEmpty()) {
            page = 1;
        } else if (!pattern.matcher(pageParam).matches()) {
            throw new PageNotFoundException("Oops... this page does not exist");
        } else {
            page = Integer.parseInt(pageParam);
        }

        return page;
    }
}
