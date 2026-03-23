/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/modules/core/service/business/imp/EntityService.java.e.vm
 */
package com.copeinca.apicopeincaprov.modules.core.service.business.imp;

import com.copeinca.apicopeincaprov.commons.models.FilterOperation;
import com.copeinca.apicopeincaprov.commons.models.OperatorEnum;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedRequest;
import com.copeinca.apicopeincaprov.global.dtos.response.PagedResult;
import com.copeinca.apicopeincaprov.integration.LaserFiche.dtos.FileUploadDto;
import com.copeinca.apicopeincaprov.integration.LaserFiche.pojos.deleteFiles.body.IdArchivosECMEnc;
import com.copeinca.apicopeincaprov.integration.LaserFiche.pojos.uploadFiles.response.SubirArchivoECMSoapResponse;
import com.copeinca.apicopeincaprov.integration.LaserFiche.services.interfaces.ILaserFiche;
import com.copeinca.apicopeincaprov.modules.core.mappers.SvAdjuntoMapper;
import com.copeinca.apicopeincaprov.modules.core.mappers.SvTrabajadorMapper;
import com.copeinca.apicopeincaprov.modules.core.mappers.SvTrabajadorTipoActividadAltoRiesgoMapper;
import com.copeinca.apicopeincaprov.modules.core.models.dto.*;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvProveedorFilter;
import com.copeinca.apicopeincaprov.modules.core.models.dto.filters.SvTrabajadorFilter;
import com.copeinca.apicopeincaprov.modules.core.models.entities.*;
import com.copeinca.apicopeincaprov.modules.core.repositories.jpa.*;
import com.copeinca.apicopeincaprov.modules.core.service.business.ISvAdjuntoService;
import com.copeinca.apicopeincaprov.modules.core.service.business.ISvTrabajadorService;
import com.copeinca.apicopeincaprov.modules.core.service.crud.ISvAdjuntoCrudService;
import com.copeinca.apicopeincaprov.modules.core.service.crud.ISvTrabajadorCrudService;
import com.copeinca.apicopeincaprov.modules.core.service.crud.ISvTrabajadorTipoActividadAltoRiesgoCrudService;
import com.copeinca.apicopeincaprov.modules.core.service.crud.imp.SvProveedorCrudServiceImpl;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvAdjuntoQueryService;
import com.copeinca.apicopeincaprov.modules.core.service.query.ISvTrabajadorQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
* Implementación del service para SvTrabajadorEntity
* Extiende de las implementaciones especializadas para reutilizar funcionalidad
*/
@Slf4j
@Service
@RequiredArgsConstructor
public class SvTrabajadorService implements ISvTrabajadorService {

    private final ILaserFiche laserFiche;

    //MAPPER
    private final SvTrabajadorMapper svTrabajadorMapper;
    private final SvAdjuntoMapper adjuntoMapper;
    private final SvTrabajadorTipoActividadAltoRiesgoMapper svTrabajadorTipoActividadAltoRiesgoMapper;

    //SERVICES
    private final ISvAdjuntoCrudService iSvAdjuntoCrudService;
    private final ISvAdjuntoQueryService iSvAdjuntoQueryService;
    private final ISvAdjuntoService iSvAdjuntoService;
    private final ISvTrabajadorCrudService iSvTrabajadorCrudService;
    private final ISvTrabajadorTipoActividadAltoRiesgoCrudService iSvTrabajadorTipoActividadAltoRiesgoCrudService;
    private final ISvTrabajadorQueryService iSvTrabajadorQueryService;

    //Repositories
    private final ISvTipoDocumentoPlanillaRepository iSvTipoDocumentoPlanillaRepository;
    private final ISvTrabajadorRepository iSvTrabajadorRepository;
    private final ISvAdjuntoRepository iSvAdjuntoRepository;
    private final ISvTrabajadorTipoActividadAltoRiesgoRepository iSvTrabajadorTipoActividadAltoRiesgoRepository;
    private final ISvPersonaRestringidaRepository iSvPersonaRestringidaRepository;
    private final ISvTipoDocumentoIdentidadRepository iSvTipoDocumentoIdentidadRepository;

    private final ISvEstadoCalidadAmbientalRepository iSvEstadoCalidadAmbientalRepository;
    private final ISvEstadoSsoRepository iSvEstadoSsoRepository;
//    private final SvTrabajadorTipoActividadAltoRiesgoMapper svTrabajadorTipoActividadAltoRiesgoMapper;

    @Value( "${path.files}" )
    private String sPath;

    @Value( "${path.typeDoc}" )
    private String sTipo;

    private static final DateTimeFormatter DD_MM_YYYY =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private static final int MAX_LEN_CADENA_MD = 10000;

    @Override
    public SvTrabajadorRegistroDTO registrarTrabajador(SvTrabajadorRegistroDTO trabajador, List<MultipartFile> aFilesSSO, List<MultipartFile> aFilesAAR, List<MultipartFile> aFilesP) throws Exception {
        int anio = LocalDate.now().getYear();
        IdArchivosECMEnc deleteFiles = new IdArchivosECMEnc();
        ArrayList<String> ids = new ArrayList<>();
        Boolean bExist = false;
        if(trabajador.getTrabajador().getId() == null){
            bExist = iSvTrabajadorRepository.existsByNroDocumentoIdentidadAndTipoDocumentoIdentidadCode(trabajador.getTrabajador().getNroDocumentoIdentidad(), trabajador.getTrabajador().getTipoDocumentoIdentidadCode());
            if(bExist)
                throw new RuntimeException("Trabajador "+trabajador.getTrabajador().getTipoDocumentoIdentidadCode()+" "+trabajador.getTrabajador().getNroDocumentoIdentidad()+", ya existe.");

            bExist = iSvPersonaRestringidaRepository.existsByTipoDocumentoIdentidadCodeAndNumeroDocumento(trabajador.getTrabajador().getTipoDocumentoIdentidadCode(),trabajador.getTrabajador().getNroDocumentoIdentidad());
            if(bExist)
                throw new RuntimeException("Trabajador "+trabajador.getTrabajador().getTipoDocumentoIdentidadCode()+" "+trabajador.getTrabajador().getNroDocumentoIdentidad()+", se encuentra restringido.");
            trabajador.getTrabajador().setIsActive(true);
        }

        int o = 0;
        if(trabajador.getDeleteListTAAR() != null && trabajador.getDeleteListTAAR().size() > 0){
            for (SvTrabajadorTipoActividadAltoRiesgoDTO item: trabajador.getDeleteListTAAR()){
                item.setIsDeleted(true);
                item.setIsActive(false);
                //System.o
                //SvTrabajadorTipoActividadAltoRiesgoEntity tmp = iSvTrabajadorTipoActividadAltoRiesgoCrudService.save(item);
                trabajador.getDeleteListTAAR().set(o,svTrabajadorTipoActividadAltoRiesgoMapper.entityToDto(iSvTrabajadorTipoActividadAltoRiesgoCrudService.save(item)));
                o++;
            }
        }
        List<SvTipoDocumentoPlanillaEntity> aListTipoPlanilla = iSvTipoDocumentoPlanillaRepository.findAll();
        if(trabajador.getDeletesFiles() != null && trabajador.getDeletesFiles().size() > 0){
            for (SvAdjuntoDTO item :trabajador.getDeletesFiles()){
                ids.add(item.getIdRepositorio());
//                SvAdjuntoDTO oAdj = iSvAdjuntoQueryService.findById(item.getId());
                SvAdjuntoDTO oAdj = adjuntoMapper.entityToDto(iSvAdjuntoRepository.findById(item.getId()).get());
                oAdj.setIsDeleted(true);
                iSvAdjuntoCrudService.save(oAdj);
                //iSvAdjuntoCrudService.save(adjuntoMapper.dtoToEntity(oAdj));
            }
            deleteFiles.setString(ids);
            try{
                laserFiche.deleteFilesLaserFiche(deleteFiles);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        trabajador.setTrabajador(svTrabajadorMapper.entityToDto(iSvTrabajadorCrudService.save(trabajador.getTrabajador())));


        o = 0;
        if(trabajador.getListTAAR() != null && trabajador.getListTAAR().size() > 0){
            for (SvTrabajadorTipoActividadAltoRiesgoDTO item: trabajador.getListTAAR()){
                item.setTrabajadorId(trabajador.getTrabajador().getId());
                item.setId(null);
                trabajador.getListTAAR().set(o,svTrabajadorTipoActividadAltoRiesgoMapper.entityToDto(iSvTrabajadorTipoActividadAltoRiesgoCrudService.save(item)));
                o++;
            }
        }

        //SSO
        if(trabajador.getHsFilesSSO() != null && trabajador.getHsFilesSSO().size() > 0){
            try{
                List<FileUploadDto> afiles = new ArrayList<>();
                for (int i = 0; i < trabajador.getHsFilesSSO().size(); i++) {
                    SvAdjuntosIndexDto p = trabajador.getHsFilesSSO().get(i);
                    afiles.add(FileUploadDto.builder()
                            .pArchivoOrigen(aFilesSSO.get(i))
                            .pNombresDestino(aFilesSSO.get(i).getOriginalFilename())
//                            .pRutaArchivoDestino("COPEINCA\\COPETESTING\\")
                            .pRutaArchivoDestino(sPath+anio+"\\")

//                            .pCadenaMD("Matricula;RTA00983")
                            .pCadenaMD(this.generateCadenaSSO(p,trabajador,aListTipoPlanilla.stream()
                                    .filter(t -> p.getInfoAdjunto().getTipoDocumentoPlanillaCode().equals(t.getId()))
                                    .findFirst().get()))
//                            .pNombreTipoDocumento(aListTipoPlanilla.stream()
//                                    .filter(t -> p.getInfoAdjunto().getTipoDocumentoPlanillaCode().equals(t.getId()))
//                                    .findFirst().get().getDescription())
                            .pNombreTipoDocumento(sTipo)
                            .build());
                }
                SubirArchivoECMSoapResponse ex = laserFiche.uploadFilesMasive(afiles);
                for (int i = 0; i < ex.getBody().getResponse().getResult().getString().size(); i++) {
                    String[] Id = ex.getBody().getResponse().getResult().getString().get(i).split("#");
                    trabajador.getHsFilesSSO().get(i).setInfoAdjunto(adjuntoMapper.entityToDto(iSvAdjuntoCrudService.save(SvAdjuntoDTO.builder()
                            .nombreArchivo(aFilesSSO.get(i).getOriginalFilename())
                            .idRepositorio(Id[1])
                            .clasification("Trabajador SSO")
                            .fechaDocumento(trabajador.getHsFilesSSO().get(i).getInfoAdjunto().getFechaDocumento())
                            .fechaVencimiento(trabajador.getHsFilesSSO().get(i).getInfoAdjunto().getFechaVencimiento())
                            .ssoComentarioRevision(trabajador.getHsFilesSSO().get(i).getInfoAdjunto().getSsoComentarioRevision())
                            .proveedorId(trabajador.getTrabajador().getProveedorId())
                            .trabajadorId(trabajador.getTrabajador().getId())
                            .tipoDocumentoPlanillaCode(trabajador.getHsFilesSSO().get(i).getInfoAdjunto().getTipoDocumentoPlanillaCode())
                            .isDeleted(false)
                            .motivo(trabajador.getHsFilesSSO().get(i).getInfoAdjunto().getMotivo())
                            .rutaRelativa(sPath+anio+"\\")
                            .version(1L)
                            .build())));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("SAVING SSO");


        //AAR
        System.out.println("SAVING AAR");
        if(trabajador.getHsFilesAAR() != null && trabajador.getHsFilesAAR().size() > 0)
            try{
                List<FileUploadDto> afiles = new ArrayList<>();
                for (int i = 0; i < trabajador.getHsFilesAAR().size(); i++) {
                    SvAdjuntosIndexDto p = trabajador.getHsFilesAAR().get(i);
                    afiles.add(FileUploadDto.builder()
                            .pArchivoOrigen(aFilesAAR.get(i))
                            .pNombresDestino(aFilesAAR.get(i).getOriginalFilename())
//                            .pRutaArchivoDestino("COPEINCA\\COPETESTING\\")
                            .pRutaArchivoDestino(sPath+anio+"\\")
                            .pCadenaMD(this.generateCadenaAAR(p,trabajador,aListTipoPlanilla.stream()
                                    .filter(t -> p.getInfoAdjunto().getTipoDocumentoPlanillaCode().equals(t.getId()))
                                    .findFirst().get()))
//                            .pCadenaMD("Matricula;RTA00983")
                            .pNombreTipoDocumento(sTipo)
//                            .pNombreTipoDocumento(p.getInfoAdjunto())
                            .build());
                }
                SubirArchivoECMSoapResponse ex = laserFiche.uploadFilesMasive(afiles);
                for (int i = 0; i < ex.getBody().getResponse().getResult().getString().size(); i++) {
                    String[] Id = ex.getBody().getResponse().getResult().getString().get(i).split("#");
                    trabajador.getHsFilesAAR().get(i).setInfoAdjunto(adjuntoMapper.entityToDto(iSvAdjuntoCrudService.save(SvAdjuntoDTO.builder()
                            .nombreArchivo(aFilesAAR.get(i).getOriginalFilename())
                            .idRepositorio(Id[1])
                            .clasification("Trabajador AAR")
                            .fechaDocumento(trabajador.getHsFilesAAR().get(i).getInfoAdjunto().getFechaDocumento())
                            .fechaVencimiento(trabajador.getHsFilesAAR().get(i).getInfoAdjunto().getFechaVencimiento())
                            .ssoComentarioRevision(trabajador.getHsFilesAAR().get(i).getInfoAdjunto().getSsoComentarioRevision())
                            .proveedorId(trabajador.getTrabajador().getProveedorId())
                            .trabajadorId(trabajador.getTrabajador().getId())
                            .motivo(trabajador.getHsFilesAAR().get(i).getInfoAdjunto().getMotivo())
                            .tipoDocumentoPlanillaCode(trabajador.getHsFilesAAR().get(i).getInfoAdjunto().getTipoDocumentoPlanillaCode())
                            .isDeleted(false)
                            .rutaRelativa(sPath+anio+"\\")
                            .version(1L)
                            .build())));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        if(trabajador.getHsFilesP() != null && trabajador.getHsFilesP().size() > 0)
            try{
                List<FileUploadDto> afiles = new ArrayList<>();
                for (int i = 0; i < trabajador.getHsFilesP().size(); i++) {
                    SvAdjuntosIndexDto p = trabajador.getHsFilesP().get(i);
                    afiles.add(FileUploadDto.builder()
                            .pArchivoOrigen(aFilesP.get(i))
                            .pNombresDestino(aFilesP.get(i).getOriginalFilename())
//                            .pRutaArchivoDestino("COPEINCA\\COPETESTING\\")
                            .pRutaArchivoDestino(sPath+anio+"\\")
//                            .pCadenaMD("Matricula;RTA00983")
                            .pCadenaMD(this.generateCadenaP(p,trabajador,aListTipoPlanilla.stream()
                                    .filter(t -> p.getInfoAdjunto().getTipoDocumentoPlanillaCode().equals(t.getId()))
                                    .findFirst().get()))
                            .pNombreTipoDocumento(sTipo)
                            .build());
                }
                SubirArchivoECMSoapResponse ex = laserFiche.uploadFilesMasive(afiles);
                for (int i = 0; i < ex.getBody().getResponse().getResult().getString().size(); i++) {
                    String[] Id = ex.getBody().getResponse().getResult().getString().get(i).split("#");
                    trabajador.getHsFilesP().get(i).setInfoAdjunto(adjuntoMapper.entityToDto(iSvAdjuntoCrudService.save(SvAdjuntoDTO.builder()
                            .nombreArchivo(aFilesP.get(i).getOriginalFilename())
                            .idRepositorio(Id[1])
                            .clasification("Trabajador P")
                            .fechaDocumento(trabajador.getHsFilesP().get(i).getInfoAdjunto().getFechaDocumento())
                            .fechaVencimiento(trabajador.getHsFilesP().get(i).getInfoAdjunto().getFechaVencimiento())
                            .ssoComentarioRevision(trabajador.getHsFilesP().get(i).getInfoAdjunto().getSsoComentarioRevision())
                            .proveedorId(trabajador.getTrabajador().getProveedorId())
                            .trabajadorId(trabajador.getTrabajador().getId())
                            .tipoDocumentoPlanillaCode(trabajador.getHsFilesP().get(i).getInfoAdjunto().getTipoDocumentoPlanillaCode())
                            .isDeleted(false)
                            .rutaRelativa(sPath+anio+"\\")
                            .motivo(trabajador.getHsFilesP().get(i).getInfoAdjunto().getMotivo())
                            .version(1L)
                            .build())));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        return trabajador;
    }

    private String generateCadenaP(SvAdjuntosIndexDto indexDto, SvTrabajadorRegistroDTO infoTrabajador, SvTipoDocumentoPlanillaEntity template) {
        Map<String, Object> md = new java.util.LinkedHashMap<>(); // mantiene el orden
        md.put("RUC", infoTrabajador.getProveedor().getNroDocumentoIdentidad());
        md.put("Razon Social Proveedor", infoTrabajador.getProveedor().getNombreFiscal());
        md.put("Numero de Documento ", infoTrabajador.getTrabajador().getNroDocumentoIdentidad());
        md.put("Fecha de documento", indexDto.getInfoAdjunto().getFechaDocumento().toString());
        md.put("Fecha caduca1", indexDto.getInfoAdjunto().getFechaVencimiento() != null
                ? indexDto.getInfoAdjunto().getFechaVencimiento().toString()
                : ""
        );
        md.put("Observaciones", indexDto.getInfoAdjunto().getMotivo());
        md.put("Ambito Accesos Sedes", "SSO" );
        md.put("Sede LF", "LIMA" );
        md.put("Sede Accesos Sedes", "LIMA" );
        md.put("Es Prevencionista ","SI");
//        md.put("Tipo Actividad de alto riesgo",template.getDependencia());
        md.put("Sustento Prevencionista",template.getDescription());
        return MetadataCadenaMdBuilder.buildBounded(md, MAX_LEN_CADENA_MD);
    }

    private String generateCadenaAAR(SvAdjuntosIndexDto indexDto, SvTrabajadorRegistroDTO infoTrabajador, SvTipoDocumentoPlanillaEntity template) {
        Map<String, Object> md = new java.util.LinkedHashMap<>(); // mantiene el orden
        md.put("RUC", infoTrabajador.getProveedor().getNroDocumentoIdentidad());
        md.put("Razon Social Proveedor", infoTrabajador.getProveedor().getNombreFiscal());
        md.put("Numero de Documento ", infoTrabajador.getTrabajador().getNroDocumentoIdentidad());
        md.put("Fecha de documento", indexDto.getInfoAdjunto().getFechaDocumento().toString());
        md.put("Fecha caduca1", indexDto.getInfoAdjunto().getFechaVencimiento() != null
                ? indexDto.getInfoAdjunto().getFechaVencimiento().toString()
                : ""
        );
        md.put("Observaciones", indexDto.getInfoAdjunto().getMotivo());
        md.put("Ambito Accesos Sedes", "SSO" );
        md.put("Sede LF", "LIMA" );
        md.put("Sede Accesos Sedes", "LIMA" );
        md.put("Es Actividad de alto riesgo","SI");
        md.put("Tipo Actividad de alto riesgo",template.getDependencia());
//        md.put("Sustento Actividad de alto riesgo Empresa",template.getDescription());
        md.put("Sustento Actividad de alto riesgo Tbjd",template.getDescription());
        return MetadataCadenaMdBuilder.buildBounded(md, MAX_LEN_CADENA_MD);
    }

    public String generateCadenaSSO(SvAdjuntosIndexDto indexDto,SvTrabajadorRegistroDTO infoTrabajador,SvTipoDocumentoPlanillaEntity template){
        Map<String, Object> md = new java.util.LinkedHashMap<>(); // mantiene el orden
        md.put("RUC", infoTrabajador.getProveedor().getNroDocumentoIdentidad());
        md.put("Razon Social Proveedor", infoTrabajador.getProveedor().getNombreFiscal());
        md.put("Numero de Documento ", infoTrabajador.getTrabajador().getNroDocumentoIdentidad());
        md.put("Fecha de documento", indexDto.getInfoAdjunto().getFechaDocumento().toString());
//        md.put("Fecha caduca1", indexDto.getInfoAdjunto().getFechaVencimiento().toString() );
        md.put("Fecha caduca1", indexDto.getInfoAdjunto().getFechaVencimiento() != null
                ? indexDto.getInfoAdjunto().getFechaVencimiento().toString()
                : ""
        );
        md.put("Observaciones", indexDto.getInfoAdjunto().getMotivo());
        md.put("Ambito Accesos Sedes", "SSO" );
        md.put("Sede LF", "LIMA" );
        md.put("Sede Accesos Sedes", "LIMA" );
        md.put("Tipo de documento Trabajador SSO", template.getDescription());
        return MetadataCadenaMdBuilder.buildBounded(md, MAX_LEN_CADENA_MD);
    }
    private static String truncate(String s, int maxLen) {
        if (s == null) return "";
        if (maxLen < 0) return "";
        return (s.length() <= maxLen) ? s : s.substring(0, maxLen);
    }
    public static String formatDdMmYyyy(LocalDate date) {
        if (date == null) return "";
        return date.format(DD_MM_YYYY);
    }
    public final class MetadataCadenaMdBuilder {

        private static final char DATA_SEP = ';';
        private static final char FIELD_SEP = '|';

        private MetadataCadenaMdBuilder() {}

        /**
         * Construye pCadenaMD como: Campo;Valor|Campo2;Valor2  (SIN '|' final)
         * - Respeta maxLen
         * - Nunca deja un segmento incompleto
         * - Si no entra completo, recorta SOLO el valor del último segmento
         */
        public static String buildBounded(Map<String, ?> md, int maxLen) {
            if (md == null || md.isEmpty() || maxLen <= 0) return "";

            StringBuilder sb = new StringBuilder(Math.min(256, maxLen));
            boolean first = true;

            for (var entry : md.entrySet()) {
                String campo = requireValidCampo(entry.getKey());
                String valor = requireValidValor(entry.getValue());

                // Segmento base (sin separador de campo al final)
                // Campo;Valor
                String segmentFull = campo + DATA_SEP + valor;

                // Si no es el primero, hay que anteponer '|'
                String prefix = first ? "" : String.valueOf(FIELD_SEP);
                int remaining = maxLen - sb.length();
                if (remaining <= 0) break;

                // Caso 1: entra completo (con su prefijo si aplica)
                if ((prefix.length() + segmentFull.length()) <= remaining) {
                    sb.append(prefix).append(segmentFull);
                    first = false;
                    continue;
                }

                // Caso 2: no entra completo -> intentamos recortar SOLO el valor
                // Necesitamos como mínimo: prefix + campo + ';'   (valor puede quedar vacío)
                int minLen = prefix.length() + campo.length() + 1; // +1 por ';'
                if (minLen > remaining) {
                    break; // no se puede agregar ni siquiera "Campo;"
                }

                int spaceForValor = remaining - minLen; // lo que queda para el valor
                String valorRecortado = (spaceForValor >= valor.length())
                        ? valor
                        : valor.substring(0, spaceForValor);

                sb.append(prefix).append(campo).append(DATA_SEP).append(valorRecortado);
                // Llegamos al límite, no seguimos agregando más campos
                break;
            }

            validateStructureNoTrailingPipe(sb.toString());
            return sb.toString();
        }

        private static String requireValidCampo(String campo) {
            if (campo == null || campo.isBlank())
                throw new IllegalArgumentException("Campo (Template) es obligatorio.");

            String c = campo.trim();
            if (c.indexOf(DATA_SEP) >= 0 || c.indexOf(FIELD_SEP) >= 0)
                throw new IllegalArgumentException("Campo inválido (contiene ';' o '|'): " + c);

            return c;
        }

        private static String requireValidValor(Object valor) {
            String v = (valor == null) ? "" : String.valueOf(valor).trim();

            // Recomendado: rechazar separadores para no romper el parseo del backend
            if (v.indexOf(DATA_SEP) >= 0 || v.indexOf(FIELD_SEP) >= 0)
                throw new IllegalArgumentException("Valor inválido (contiene ';' o '|'): " + v);

            return v;
        }

        private static void validateStructureNoTrailingPipe(String cadena) {
            if (cadena == null || cadena.isEmpty()) return;

            // NO debe terminar con '|'
            if (cadena.charAt(cadena.length() - 1) == FIELD_SEP) {
                throw new IllegalStateException("Cadena inválida: termina con '|'");
            }

            // Cada segmento debe contener ';' y tener campo no vacío
            String[] segments = cadena.split("\\|", -1);
            for (String seg : segments) {
                if (seg.isEmpty())
                    throw new IllegalStateException("Cadena inválida: segmento vacío entre '|'");
                int idx = seg.indexOf(DATA_SEP);
                if (idx <= 0)
                    throw new IllegalStateException("Cadena inválida: falta ';' en segmento: " + seg);
                // campo = seg.substring(0, idx) (no vacío), valor puede ser vacío
            }
        }
    }
    @Override
    public SvTrabajadorRegistroDTO getTrabajador(String id) {
        SvTrabajadorRegistroDTO svTrabajadorRegistroDTO = new SvTrabajadorRegistroDTO();

        svTrabajadorRegistroDTO.setTrabajador(iSvTrabajadorQueryService.findById(id));
        List<SvAdjuntoEntity> aListAdj = iSvAdjuntoRepository.findByProveedorIdAndIsDeletedAndTrabajadorId(svTrabajadorRegistroDTO.getTrabajador().getProveedorId(),false,svTrabajadorRegistroDTO.getTrabajador().getId());
        svTrabajadorRegistroDTO.setHsFilesSSO(aListAdj.stream()
                .map(adjuntoMapper::entityToDto)
                .filter(dto ->
                        "Trabajador SSO".equals(dto.getClasification())
                                && Boolean.FALSE.equals(dto.getIsDeleted())
                )
                .map(dto -> SvAdjuntosIndexDto.builder()
                        .index(0)
                        .infoAdjunto(dto)
                        .build())
                .toList());

//        svTrabajadorRegistroDTO.setHsFilesSSO(aListAdj.stream()
//                .map(adjuntoMapper::entityToDto)
//                .filter(dto ->
//                        "Trabajador SSO".equals(dto.getClasification())
//                                && Boolean.FALSE.equals(dto.getIsDeleted())
//                )
//                .map(dto -> new SvAdjuntosIndexDto())
//                .toList());

        svTrabajadorRegistroDTO.setHsFilesP(aListAdj.stream()
                .map(adjuntoMapper::entityToDto)
                .filter(dto ->
                        "Trabajador P".equals(dto.getClasification())
                                && Boolean.FALSE.equals(dto.getIsDeleted())
                )
                .map(dto -> SvAdjuntosIndexDto.builder()
                        .index(0)
                        .infoAdjunto(dto)
                        .build())
                .toList());

        svTrabajadorRegistroDTO.setHsFilesAAR(aListAdj.stream()
                .map(adjuntoMapper::entityToDto)
                .filter(dto ->
                        "Trabajador AAR".equals(dto.getClasification())
                                && Boolean.FALSE.equals(dto.getIsDeleted())
                )
                .map(dto -> SvAdjuntosIndexDto.builder()
                        .index(0)
                        .infoAdjunto(dto)
                        .build())
                .toList());



        svTrabajadorRegistroDTO.setListTAAR(iSvTrabajadorTipoActividadAltoRiesgoRepository.findByTrabajadorIdAndIsActiveAndIsDeleted(svTrabajadorRegistroDTO.getTrabajador().getId(), true,false).stream().map(svTrabajadorTipoActividadAltoRiesgoMapper::entityToDto).toList());


        return svTrabajadorRegistroDTO;
    }

    private SvTrabajadorTipoActividadAltoRiesgoDTO buildTAAR(SvTrabajadorTipoActividadAltoRiesgoEntity e) {
        return SvTrabajadorTipoActividadAltoRiesgoDTO.builder()
                .id(e.getId())
                .trabajadorId(e.getTrabajadorId())
                .tipoActividadAltoRiesgoCode(e.getTipoActividadAltoRiesgoCode())
                .description(e.getTipoActividadAltoRiesgoCodeEntity().getDescription())
                .isActive(e.getIsActive())
                .isDeleted(e.getIsDeleted())
                .version(e.getVersion())
                .build();
    }

    @Override
    public List<SvTrabajadorDTO> isExist(List<SvTrabajadorDTO> result) {
//        PagedRequest<SvTrabajadorFilter> filter = new PagedRequest<>();
//        filter.setPage(0);
//        filter.setOffset(0);
//        filter.setLimit(10000);
//        filter.setSize(10000);

//        Map<String, SvEstadoCalidadAmbientalEntity> mapSetCalidad = iSvEstadoCalidadAmbientalRepository.findAll().stream().collect(
//                Collectors.toMap(
//                        SvEstadoCalidadAmbientalEntity::getName,
//                        e-> e
//                )
//        );

        Map<String, SvEstadoSsoEntity> mapSetSSO = iSvEstadoSsoRepository.findAll().stream().collect(
                Collectors.toMap(
                        SvEstadoSsoEntity::getName,
                        e-> e
                )
        );

        Map<String, SvTipoDocumentoIdentidadEntity> tdiMap =
                iSvTipoDocumentoIdentidadRepository.findAll()
                        .stream()
                        .collect(Collectors.toMap(
                                SvTipoDocumentoIdentidadEntity::getName,
                                e -> e
                        ));
        result.forEach(dto -> {
            SvEstadoSsoEntity svEstadoSsoEntity = mapSetSSO.get("PRELIMINAR");
//            SvEstadoSsoEntity svEstadoSsoEntity = mapSetSSO.get(dto.getEstadoSsoCode());
            dto.setEstadoSsoCode(svEstadoSsoEntity.getId());
            dto.setEstadoSsoDescription(svEstadoSsoEntity.getName());
            dto.setTipoDocumentoIdentidadDescription(tdiMap.get(dto.getTipoDocumentoIdentidadCode()).getName());
            if(dto.getId() == null)
                dto.setTipoDocumentoIdentidadCode(tdiMap.get(dto.getTipoDocumentoIdentidadCode()).getId());
        });

//        FilterOperation sFilter = new FilterOperation<>();
//        sFilter.setOperator(OperatorEnum.IN);
//        sFilter.setValues(result.stream().map(SvTrabajadorDTO::getNroDocumentoIdentidad).toList());
//        SvTrabajadorFilter sFilterTrabajador = new SvTrabajadorFilter();
//        sFilterTrabajador.setNroDocumentoIdentidad(List.of(sFilter));
//
//        filter.setFilter(SvTrabajadorFilter.builder()
//                        .nroDocumentoIdentidad(List.of(sFilter))
//                        .tipoDocumentoIdentidadCodeIds(result.stream().map(SvTrabajadorDTO::getTipoDocumentoIdentidadCode).toList())
//                .build());

        PagedResult<SvTrabajadorDTO> resultTrabajadores = iSvTrabajadorQueryService.search(PagedRequest.<SvTrabajadorFilter>builder()
                .filter(SvTrabajadorFilter.builder()
                        .nroDocumentoIdentidad(List.of(FilterOperation.<String>builder()
                                .operator(OperatorEnum.IN)
                                .values(result.stream().map(SvTrabajadorDTO::getNroDocumentoIdentidad).toList())
                                .build()))
                        .tipoDocumentoIdentidadCodeIds(result.stream().map(SvTrabajadorDTO::getTipoDocumentoIdentidadCode).toList())
                        .build())
                        .limit(100)
                .build());
//        PagedResult<SvTrabajadorDTO> resultTrabajadores = iSvTrabajadorQueryService.search(filter);


        Map<String, SvTrabajadorDTO> docsEnBD = resultTrabajadores.getResult().stream()
                .filter(t -> t.getTipoDocumentoIdentidadCode() != null
                        && t.getNroDocumentoIdentidad() != null)
                .collect(Collectors.toMap(
                        t -> t.getTipoDocumentoIdentidadCode() + "|" + t.getNroDocumentoIdentidad(),
                        t -> t,
                        (a, b) -> a
                ));
        result.forEach(dto -> {
            String tipo = dto.getTipoDocumentoIdentidadCode();
            String doc = dto.getNroDocumentoIdentidad();

            String key = (tipo != null && doc != null)
                    ? tipo + "|" + doc
                    : null;

            dto.setIsExists(key != null && docsEnBD.containsKey(key));
        });

        return result;
    }

    @Override
    public boolean findFiles(String id) {
//        List<SvAdjuntoEntity> aList =  iSvAdjuntoRepository.findAllByTrabajadorId(id);
        List<SvAdjuntoEntity> aList =  iSvAdjuntoRepository.findAllByTrabajadorIdAndIsDeleted(id,false);

        return aList.isEmpty();
    }
}
