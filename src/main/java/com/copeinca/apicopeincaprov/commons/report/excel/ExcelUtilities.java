
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/commons/report/excel/ExcelUtilities.java.p.vm
 */
package com.copeinca.apicopeincaprov.commons.report.excel;

import com.copeinca.apicopeincaprov.global.util.DateTimeUtil;
import com.copeinca.apicopeincaprov.global.util.UtilString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ExcelUtilities {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat( "#.##########" );

    /**
     * Convierte un MultipartFile a Workbook soportando tanto XLS como XLSX
     */
    public static Workbook getWorkbook(MultipartFile multipartFile) {

        if (multipartFile == null || multipartFile.isEmpty())
            throw new RuntimeException("El archivo no puede estar vacío");

        try (InputStream inputStream = new ByteArrayInputStream(multipartFile.getBytes())) {

            return getWorkbook(inputStream);

        } catch (IOException ex) {

            throw new RuntimeException("Error al procesar el archivo Excel: " + ex.getMessage(), ex);

        }

    }

    /**
     * Convierte un InputStream a Workbook intentando automáticamente detectar el formato
     *
     * @param inputStream InputStream del archivo Excel
     * @return Workbook
     */
    public static Workbook getWorkbook(InputStream inputStream) {

        if (inputStream == null)
            throw new RuntimeException("El InputStream no puede ser null");

        try {
            // Por defecto intentar XLSX, si falla intentar XLS
            try {

                return new XSSFWorkbook(inputStream);

            } catch (Exception ex) {

                // Si falla XLSX, intentar XLS
                inputStream.reset();
                return new HSSFWorkbook(inputStream);

            }

        } catch (IOException ex) {

            throw new RuntimeException("Error al procesar el InputStream: " + ex.getMessage(), ex);

        }

    }

    /**
     * Convierte cualquier tipo de celda a String de manera segura
     */
    public static String getCellValueAsString(Cell cell) {

        if (cell == null)
            return "";

        try {

            switch (cell.getCellType()) {
            case STRING:
                return UtilString.coalesceTrim(cell.getStringCellValue());

            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return DateTimeUtil.asString(cell.getDateCellValue());
                } else {
                    double numericValue = cell.getNumericCellValue();
                    // Si es número entero, no mostrar decimales
                    if (numericValue % 1.0 == 0) {
                        return String.valueOf((long) numericValue);
                    } else {
                        return DECIMAL_FORMAT.format(numericValue);
                    }
                }

            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());

            case FORMULA:
                throw new RuntimeException("El campo de tipo formula no está soportado para lectura");

            case BLANK:
                return "";

            case ERROR:
                return "";

            default:
                return "";
            }

        } catch (Exception ex) {
            // En caso de cualquier error, retornar cadena vacía
            return "";
        }

    }

    /**
     * Convierte una celda a Boolean de manera segura
     */
    public static Boolean getCellValueAsBoolean(Cell cell) {
        if (cell == null)
            return null;

        try {
            switch (cell.getCellType()) {
            case BOOLEAN:
                return cell.getBooleanCellValue();
            case STRING:
                String value = UtilString.coalesceTrim(cell.getStringCellValue());
                if (value.isEmpty())
                    return null;
                if ("SÍ".equals(value) || "SI".equals(value) || "✓ Sí".equals(value) || "✓ Si".equals(value)) {
                    return true;
                }

                if ("NO".equals(value) || "✗ No".equals(value)) {
                    return false;
                }
                return Boolean.parseBoolean(value);
            case NUMERIC:
                return cell.getNumericCellValue() != 0;
            default:
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Convierte una celda a BigDecimal de manera segura
     */
    public static BigDecimal getCellValueAsBigDecimal(Cell cell) {
        if (cell == null)
            return null;

        try {
            switch (cell.getCellType()) {
            case NUMERIC:
                return new BigDecimal(cell.getNumericCellValue());
            case STRING:
                String value = UtilString.coalesceTrim(cell.getStringCellValue());
                if (value.isEmpty())
                    return null;
                return new BigDecimal(value);
            default:
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Convierte una celda a Integer de manera segura
     */
    public static Integer getCellValueAsInteger(Cell cell) {
        if (cell == null)
            return null;

        try {
            switch (cell.getCellType()) {
            case NUMERIC:
                return (int) cell.getNumericCellValue();
            case STRING:
                String value = UtilString.coalesceTrim(cell.getStringCellValue());
                if (value.isEmpty())
                    return null;
                return Integer.parseInt(value);
            default:
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Convierte una celda a Long de manera segura
     */
    public static Long getCellValueAsLong(Cell cell) {
        if (cell == null)
            return null;

        try {
            switch (cell.getCellType()) {
            case NUMERIC:
                return (long) cell.getNumericCellValue();
            case STRING:
                String value = UtilString.coalesceTrim(cell.getStringCellValue());
                if (value.isEmpty())
                    return null;
                return Long.parseLong(value);
            default:
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Convierte una celda a LocalDate de manera segura
     */
    public static LocalDate getCellValueAsLocalDate(Cell cell) {
        if (cell == null)
            return null;

        try {
            switch (cell.getCellType()) {
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getLocalDateTimeCellValue().toLocalDate();
                }
                return null;
            case STRING:
                String value = UtilString.coalesceTrim(cell.getStringCellValue());
                if (value.isEmpty())
                    return null;
                return DateTimeUtil.asLocalDate(value, "yyyy-MM-dd");
            default:
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * Convierte una celda a LocalDateTime de manera segura
     */
    public static LocalDateTime getCellValueAsLocalDateTime(Cell cell) {
        if (cell == null)
            return null;

        try {
            switch (cell.getCellType()) {
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getLocalDateTimeCellValue();
                }
                return null;
            case STRING:
                String value = UtilString.coalesceTrim(cell.getStringCellValue());
                if (value.isEmpty())
                    return null;
                return DateTimeUtil.asLocalDateTime(value);
            default:
                return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }
    public static LocalDateTime getCellValueAsLocalDateTimeFromString(Cell cell) {
        if (cell == null)
            return null;

        try {
            switch (cell.getCellType()) {
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) {
                        return cell.getLocalDateTimeCellValue();
                    }
                    return null;
                case STRING:
                    String value = UtilString.coalesceTrim(cell.getStringCellValue());
                    if (value.isEmpty())
                        return null;
                    return DateTimeUtil.asLocalDateTimeFromXLSX(value);
                default:
                    return null;
            }
        } catch (Exception ex) {
            return null;
        }
    }

}
