
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/commons/report/excel/SimpleExcelExportUtilities.java.p.vm
 */
package com.copeinca.apicopeincaprov.commons.report.excel;

import com.copeinca.apicopeincaprov.global.dtos.AttachmentData;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.awt.*;
import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

/**
 * Utilidad simplificada para exportación de datos a archivos Excel
 * Trabaja directamente con valores ya extraídos y retorna AttachmentData listo para usar
 */
public class SimpleExcelExportUtilities {

    // Configuraciones de archivo
    private static final String DEFAULT_SHEET_NAME = "Datos";
    private static final String EXCEL_CONTENT_TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    private static final String EXCEL_EXTENSION = ".xlsx";

    // Paleta de colores - Solo los que se usan
    private static final class ColorPalette {
        public static final String HEADER_DARK = "#2D3748"; // Gris oscuro para encabezados
        public static final String ROW_WHITE = "#FFFFFF"; // Filas blancas (pares)
        public static final String ROW_LIGHT_GRAY = "#F7FAFC"; // Filas gris muy claro (impares)

        /**
         * Convierte un color hexadecimal a XSSFColor
         */
        public static XSSFColor hex(String hexColor) {
            Color color = Color.decode(hexColor);
            return new XSSFColor(color, null);
        }
    }

    // Configuraciones de fuente
    private static final class FontConfig {
        public static final String PRIMARY_FONT = "Segoe UI";
        public static final short HEADER_FONT_SIZE = 13;
        public static final short DATA_FONT_SIZE = 11;
    }

    // Configuraciones de dimensiones
    private static final class DimensionConfig {
        public static final short ROW_HEIGHT_HEADER = 600; // 30 puntos
        public static final short ROW_HEIGHT_DATA = 450; // 22.5 puntos
        public static final int MIN_COLUMN_WIDTH = 5000;
        public static final int MAX_COLUMN_WIDTH = 20000;
        public static final int AUTO_SIZE_PADDING = 1024;
        public static final int DEFAULT_COLUMN_WIDTH = 6500;
    }

    /**
     * Genera un archivo Excel usando lista de filas con columnas ya configuradas
     * @param rows Lista de filas, donde cada fila es una lista de columnas con valores
     * @param entityName Nombre de la entidad para el archivo y la hoja
     * @return AttachmentData listo para descargar
     * @throws IOException Si ocurre un error al generar el archivo
     */
    public static AttachmentData generateExcelFile(List<List<SimpleExcelColumn>> rows, String entityName) throws IOException {

        if (rows == null || rows.isEmpty()) {
            throw new IllegalArgumentException("La lista de filas no puede estar vacía");
        }

        try (XSSFWorkbook workbook = new XSSFWorkbook()) {

            Sheet sheet = workbook.createSheet(entityName != null ? entityName : DEFAULT_SHEET_NAME);

            // Crear estilos
            CellStyle headerStyle = createHeaderStyle(workbook);
            CellStyle dataStyle = createDataStyle(workbook);
            CellStyle alternateDataStyle = createAlternateDataStyle(workbook);
            CellStyle dateStyle = createDateStyle(workbook);
            CellStyle numberStyle = createNumberStyle(workbook);
            CellStyle booleanStyle = createBooleanStyle(workbook);

            // Crear fila de cabeceras
            List<SimpleExcelColumn> firstRow = rows.get(0);
            Row headerRow = sheet.createRow(0);
            headerRow.setHeight(DimensionConfig.ROW_HEIGHT_HEADER);

            for (int i = 0; i < firstRow.size(); i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(firstRow.get(i).getExcelColumnProperties().getColumnName());
                cell.setCellStyle(headerStyle);
            }

            // Crear filas de datos con estilos alternados
            for (int rowIndex = 0; rowIndex < rows.size(); rowIndex++) {
                Row dataRow = sheet.createRow(rowIndex + 1);
                dataRow.setHeight(DimensionConfig.ROW_HEIGHT_DATA);
                List<SimpleExcelColumn> rowData = rows.get(rowIndex);

                boolean isEvenRow = rowIndex % 2 == 0;
                CellStyle currentDataStyle = isEvenRow ? dataStyle : alternateDataStyle;

                for (int colIndex = 0; colIndex < rowData.size(); colIndex++) {
                    Cell cell = dataRow.createCell(colIndex);
                    SimpleExcelColumn column = rowData.get(colIndex);

                    setCellValueWithStyle(cell, column.getValue(), currentDataStyle, dateStyle, numberStyle, booleanStyle);
                }
            }

            // Configurar anchos de columnas
            configureColumnWidths(sheet, firstRow);

            // Aplicar mejoras visuales
            applyVisualEnhancements(sheet, firstRow, rows.size());

            // Convertir a array de bytes
            byte[] excelContent;
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                workbook.write(outputStream);
                excelContent = outputStream.toByteArray();
            }

            // Generar nombre del archivo con timestamp
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
            String fileName = (entityName != null ? entityName : "export") + "_" + timestamp + EXCEL_EXTENSION;

            return AttachmentData.builder().name(fileName).content(excelContent).extension(EXCEL_EXTENSION).contentType(EXCEL_CONTENT_TYPE).build();

        }

    }

    /**
     * Crea el estilo para las cabeceras
     */
    private static CellStyle createHeaderStyle(XSSFWorkbook workbook) {

        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();

        font.setFontName(FontConfig.PRIMARY_FONT);
        font.setBold(true);
        font.setFontHeightInPoints(FontConfig.HEADER_FONT_SIZE);
        font.setColor(IndexedColors.WHITE.getIndex());

        style.setFont(font);
        style.setFillForegroundColor(ColorPalette.hex(ColorPalette.HEADER_DARK));
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // Solo bordes superiores e inferiores
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());
        style.setBottomBorderColor(IndexedColors.GREY_40_PERCENT.getIndex());

        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setWrapText(true);

        return style;

    }

    /**
     * Crea el estilo para datos (filas blancas)
     */
    private static CellStyle createDataStyle(XSSFWorkbook workbook) {

        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();

        font.setFontName(FontConfig.PRIMARY_FONT);
        font.setFontHeightInPoints(FontConfig.DATA_FONT_SIZE);
        font.setColor(IndexedColors.BLACK.getIndex());

        style.setFont(font);
        style.setFillForegroundColor(ColorPalette.hex(ColorPalette.ROW_WHITE));
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setBottomBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());

        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setWrapText(false);

        return style;

    }

    /**
     * Crea el estilo alternado para filas (gris claro)
     */
    private static CellStyle createAlternateDataStyle(XSSFWorkbook workbook) {

        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();

        font.setFontName(FontConfig.PRIMARY_FONT);
        font.setFontHeightInPoints(FontConfig.DATA_FONT_SIZE);
        font.setColor(IndexedColors.BLACK.getIndex());

        style.setFont(font);
        style.setFillForegroundColor(ColorPalette.hex(ColorPalette.ROW_LIGHT_GRAY));
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setBottomBorderColor(IndexedColors.GREY_25_PERCENT.getIndex());

        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setWrapText(false);

        return style;

    }

    /**
     * Crea el estilo para fechas
     */
    private static CellStyle createDateStyle(XSSFWorkbook workbook) {

        CellStyle style = createDataStyle(workbook);
        DataFormat format = workbook.createDataFormat();
        style.setDataFormat(format.getFormat("dd/mm/yyyy"));
        style.setAlignment(HorizontalAlignment.CENTER);
        return style;

    }

    /**
     * Crea el estilo para números
     */
    private static CellStyle createNumberStyle(XSSFWorkbook workbook) {

        CellStyle style = createDataStyle(workbook);
        DataFormat format = workbook.createDataFormat();
        style.setAlignment(HorizontalAlignment.RIGHT);
        return style;

    }

    /**
     * Crea el estilo para valores booleanos
     */
    private static CellStyle createBooleanStyle(XSSFWorkbook workbook) {

        CellStyle style = createDataStyle(workbook);
        Font font = workbook.createFont();
        font.setFontName(FontConfig.PRIMARY_FONT);
        font.setFontHeightInPoints(FontConfig.DATA_FONT_SIZE);
        font.setBold(true);
        font.setColor(IndexedColors.BLACK.getIndex());
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        return style;

    }

    /**
     * Configura los anchos de columnas
     */
    private static void configureColumnWidths(Sheet sheet, List<SimpleExcelColumn> firstRow) {

        for (int i = 0; i < firstRow.size(); i++) {

            SimpleExcelColumn column = firstRow.get(i);

            if (column.getExcelColumnProperties().getWidth() > 0) {
                int specifiedWidth = Math.max(column.getExcelColumnProperties().getWidth(), DimensionConfig.MIN_COLUMN_WIDTH);
                sheet.setColumnWidth(i, specifiedWidth);
            } else {
                sheet.setColumnWidth(i, DimensionConfig.DEFAULT_COLUMN_WIDTH);
                sheet.autoSizeColumn(i);
                int autoWidth = sheet.getColumnWidth(i);
                int finalWidth = autoWidth + DimensionConfig.AUTO_SIZE_PADDING;
                finalWidth = Math.max(finalWidth, DimensionConfig.MIN_COLUMN_WIDTH);
                finalWidth = Math.min(finalWidth, DimensionConfig.MAX_COLUMN_WIDTH);
                sheet.setColumnWidth(i, finalWidth);
            }

        }

    }

    /**
     * Aplica mejoras visuales
     */
    private static void applyVisualEnhancements(Sheet sheet, List<SimpleExcelColumn> firstRow, int dataRowCount) {

        // Congelar panel de cabeceras
        sheet.createFreezePane(0, 1);

        // Aplicar filtros automáticos
        if (dataRowCount > 0) {
            CellRangeAddress range = new CellRangeAddress(0, dataRowCount, 0, firstRow.size() - 1);
            sheet.setAutoFilter(range);
        }

        // Configurar zoom
        sheet.setZoom(95);

        // Configurar orientación horizontal si hay muchas columnas
        if (firstRow.size() > 8) {
            sheet.getPrintSetup().setLandscape(true);
        }

        sheet.setFitToPage(true);
        sheet.getPrintSetup().setFitWidth((short) 1);
        sheet.getPrintSetup().setFitHeight((short) 0);

    }

    /**
     * Establece el valor de una celda con el estilo apropiado según el tipo de dato
     */
    private static void setCellValueWithStyle(Cell cell, Object value, CellStyle dataStyle, CellStyle dateStyle, CellStyle numberStyle,
            CellStyle booleanStyle) {

        if (value == null) {
            cell.setCellValue("");
            cell.setCellStyle(dataStyle);
        } else if (value instanceof String) {
            cell.setCellValue((String) value);
            cell.setCellStyle(dataStyle);
        } else if (value instanceof Number) {
            cell.setCellValue(((Number) value).doubleValue());
            cell.setCellStyle(numberStyle);
        } else if (value instanceof Boolean) {
            boolean booleanValue = (Boolean) value;
            cell.setCellValue(booleanValue ? "✓ Sí" : "✗ No");
            cell.setCellStyle(booleanStyle);
        } else if (value instanceof Date) {
            cell.setCellValue((Date) value);
            cell.setCellStyle(dateStyle);
        } else {
            cell.setCellValue(value.toString());
            cell.setCellStyle(dataStyle);
        }
    }

}
