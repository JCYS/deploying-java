
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/global/util/UtilString.java.p.vm
 */
package com.copeinca.apicopeincaprov.global.util;

import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.text.Normalizer;
import java.util.regex.Pattern;

public class UtilString {

    public static String cast(BigDecimal importe) {

        return (importe == null) ? "" : importe.toString();

    }

    public static String coalesceTrim(String str) {
        return (str == null) ? "" : str.trim();
    }

    public static Boolean nonEmpty(String text) {

        return !isEmpty(text);

    }

    public static Boolean isEmpty(String text) {

        return coalesceTrim(text).isEmpty();

    }

    public static void validaCampoObligatorio(Object campo, String nombreCampo) {

        String mensaje = "Es necesario enviar información en el campo: ";

        if (campo == null) {

            throw new RuntimeException(mensaje + nombreCampo);

        } else if (campo instanceof String) {

            if (coalesceTrim((String) campo).isEmpty()) {

                throw new RuntimeException(mensaje + nombreCampo);

            }

        }

    }

    public static String truncate(String value, int length) {
        if (value == null) {
            return "";
        }
        return value.length() > length ? value.substring(0, length) : value;
    }

    public static String truncateAndRemoveSpecialChars(String value, int length) {

        return removeSpecialChars(truncate(value, length));

    }

    public static String validateLength(String campo, String value, int length) {

        if (value == null)
            return "";

        if (value.length() > length)
            throw new RuntimeException("La longitud del campo " + campo + " es mayor a la esperada.");

        return value;

    }

    private static String removeSpecialChars(String input) {

        String normalized = Normalizer.normalize(input, Normalizer.Form.NFD);

        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");

        String result = pattern.matcher(normalized).replaceAll("");

        result = result.replace('ñ', 'n').replace('Ñ', 'N').replace('ü', 'u').replace('Ü', 'U').replace("¿", "").replace("¡", "");

        return result;

    }

    public static String obtieneMensajeErrorException(Exception e) {
        String retorno = null;
        if (StringUtils.isNotBlank(e.getMessage()))
            return e.getMessage();
        if (StringUtils.isNotBlank(e.getLocalizedMessage()))
            return e.getLocalizedMessage();
        retorno = e.toString();
        return retorno;
    }

    public static String obtieneMensajeErrorExceptionCustom(Exception e) {
        String retorno = null;
        String nombreException = e.getClass().getName();
        if (StringUtils.isNotBlank(e.getMessage())) {
            return nombreException + " -- " + e.getMessage();
        }
        if (StringUtils.isNotBlank(e.getLocalizedMessage())) {
            return nombreException + " -- " + e.getLocalizedMessage();
        }
        retorno = e.toString();
        return retorno;
    }

}
