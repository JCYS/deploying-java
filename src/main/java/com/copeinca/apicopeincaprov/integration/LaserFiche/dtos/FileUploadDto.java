package com.copeinca.apicopeincaprov.integration.LaserFiche.dtos;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Builder
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FileUploadDto {
    private MultipartFile pArchivoOrigen;
    private String pRutaArchivoDestino;
    private String pCadenaMD;
    private Boolean pHaciaECM;
    private String pNombreGabinete;
    private String pNombreTipoDocumento;
    private String pNombresDestino;
}
