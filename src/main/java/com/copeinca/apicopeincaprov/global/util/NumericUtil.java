
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/global/util/NumericUtil.java.p.vm
 */
package com.copeinca.apicopeincaprov.global.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Optional;

public class NumericUtil {

    public static BigDecimal coalesce(BigDecimal importe) {

        return Optional.ofNullable(importe).orElse(BigDecimal.ZERO);

    }

    public static String coalesceAndFormatMiles(BigDecimal importe) {

        BigDecimal value = Optional.ofNullable(importe).orElse(BigDecimal.ZERO);

        DecimalFormat formatter = new DecimalFormat("#,##0.00"); // separador de miles, 2 decimales (Si es un entero completa con .00)
        return formatter.format(value);

    }

    public static String formatBySiglaCurrency(BigDecimal importe, String sigla, int justRight) {
        if (justRight > 0) {
            String formatPattern = "%-" + justRight + "s%s";
            return String.format(formatPattern, sigla, importe.toPlainString());
        } else {
            return sigla + " " + importe.toPlainString();
        }
    }

    public static String coalesceAndFormatBySiglaCurrency(BigDecimal importe, String sigla, int justRight) {

        return formatBySiglaCurrency(coalesce(importe), sigla, justRight);

    }

    public static String formatBySiglaCurrency(BigDecimal importe, String sigla) {

        return sigla + " " + importe.toPlainString();

    }

    public static String formatBySiglaCurrencyAndMiles(BigDecimal importe, String sigla) {

        return sigla + " " + coalesceAndFormatMiles(importe);

    }

}
