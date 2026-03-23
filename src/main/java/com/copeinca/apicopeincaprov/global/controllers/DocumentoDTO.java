package com.copeinca.apicopeincaprov.global.controllers;

import java.time.LocalDate;

public class DocumentoDTO {
    private String tab; // SSO | CALIDAD
    private String tipoDocumento;
    private LocalDate fechaDocumento;
    private LocalDate fechaVencimiento;
    private Integer orden;
    private String fileKey;
}
