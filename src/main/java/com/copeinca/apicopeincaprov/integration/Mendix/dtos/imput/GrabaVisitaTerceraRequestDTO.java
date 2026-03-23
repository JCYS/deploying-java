package com.copeinca.apicopeincaprov.integration.Mendix.dtos.imput;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

        import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
        getterVisibility = JsonAutoDetect.Visibility.NONE,
        isGetterVisibility = JsonAutoDetect.Visibility.NONE)
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class GrabaVisitaTerceraRequestDTO {

    private VisitaTerceraDTO VisitaTercera;

    // =========================
    // ROOT: VisitaTercera
    // =========================
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
            getterVisibility = JsonAutoDetect.Visibility.NONE,
            isGetterVisibility = JsonAutoDetect.Visibility.NONE)
    @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor
    @Builder
    public static class VisitaTerceraDTO {

        @JsonProperty("RUC")
        private String ruc;
        private String RazonSocial;
        private String MailContacto;
        private String TelefonoContacto;

        private String Sede;

        /** Formato: dd/MM/yyyy (según ejemplo) */
        private String FechaInicio;
        /** Formato: HH:mm */
        private String HoraInicio;

        /** Formato: dd/MM/yyyy (según ejemplo) */
        private String FechaFin;
        /** Formato: HH:mm */
        private String HoraFin;

        private String DescripcionVisita;
        private String Visitado;

        private List<NombreItemDTO> AreasVisita;
        private List<NombreItemDTO> ZonasVisita;

        private List<MaterialDTO> Materiales;
        private List<EquipoDTO> Equipos;

        private String ObservacionesEspeciales;
        private String OrdenServicio;

        private List<DocumentoProveedorDTO> DocumentosProveedorSSO;
        private List<DocumentoProveedorDTO> DocumentosProveedorCA;

        /** "Si" / "No" según ejemplo */
        private String ActividadAltoRiesgoProveedor;
        private List<AltoRiesgoProveedorDTO> AltoRiesgoProveedor;

        private List<DocumentoProveedorDTO> DocumentosSSOProveedorAltoRiesgo;

        /** "Si" / "No" */
        private String ActividadABordoProveedor;
        private List<DocumentoProveedorDTO> DocumentosABordoProveedor;

        /** "Si" / "No" */
        private String NecesitaAndamios;
        private List<DocumentoProveedorDTO> DocumentosAndamiosProveedor;

        /** "Si" / "No" */
        private String NecesitaGrua;
        private List<DocumentoProveedorDTO> DocumentosGruaProveedor;

        /** "Si" / "No" */
        private String NecesitaEquiposMoviles;
        private List<DocumentoProveedorDTO> DocumentosEquiposMoviles;

        /** "Si" / "No" */
        private String NecesitaTrabajoBuceo;
        private List<DocumentoProveedorDTO> DocumentosTrabajoBuceo;

        private List<IntegranteVisitaTerceraDTO> IntegranteVisitaTercera;
    }

    // =========================
    // Reusables
    // =========================

    /** Para { "Nombre": "FLOTA" } */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
            getterVisibility = JsonAutoDetect.Visibility.NONE,
            isGetterVisibility = JsonAutoDetect.Visibility.NONE)
    @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor
    @Builder
    public static class NombreItemDTO {

        @JsonProperty("Nombre")
        private String Nombre;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
            getterVisibility = JsonAutoDetect.Visibility.NONE,
            isGetterVisibility = JsonAutoDetect.Visibility.NONE)
    @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor
    @Builder
    public static class MaterialDTO {
        @JsonProperty("Material")
        private String Material;
        @JsonProperty("Cantidad")
        private String Cantidad;
        @JsonProperty("Unidad")
        private String Unidad;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
            getterVisibility = JsonAutoDetect.Visibility.NONE,
            isGetterVisibility = JsonAutoDetect.Visibility.NONE)
    @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor
    @Builder
    public static class EquipoDTO {
        @JsonProperty("CodigoTipoEquipo")
        private String CodigoTipoEquipo;
        @JsonProperty("Equipo")
        private String Equipo;
        @JsonProperty("Cantidad")
        private String Cantidad;
        @JsonProperty("Serie")
        private String Serie;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
            getterVisibility = JsonAutoDetect.Visibility.NONE,
            isGetterVisibility = JsonAutoDetect.Visibility.NONE)
    @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor
    @Builder
    public static class DocumentoProveedorDTO {
        private String ID;
        private String Ambito;
        private String TipoDocumento;
        private String Nombre;

        /** Formato: dd/MM/yyyy */
        private String FechaDocumento;
        /** Formato: dd/MM/yyyy */
        private String FechaVencimiento;

        private String Revision;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
            getterVisibility = JsonAutoDetect.Visibility.NONE,
            isGetterVisibility = JsonAutoDetect.Visibility.NONE)
    @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor
    @Builder
    public static class AltoRiesgoProveedorDTO {
        private String ARProveedor;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY,
            getterVisibility = JsonAutoDetect.Visibility.NONE,
            isGetterVisibility = JsonAutoDetect.Visibility.NONE)
    @Getter @Setter
    @NoArgsConstructor @AllArgsConstructor
    @Builder
    public static class IntegranteVisitaTerceraDTO {
        private String TipoDocumento;
        private String NumeroDocumento;
        private String Nombres;
        private String Observaciones;
    }
}
