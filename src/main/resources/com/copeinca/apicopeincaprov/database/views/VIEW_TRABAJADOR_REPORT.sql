CREATE OR REPLACE VIEW VIEW_TRABAJADOR_REPORT AS
SELECT
    -- ID principal (campo directo sin alias)
    t.TRABAJADOR_ID,

    -- Información del Proveedor - prefijo PROV_
    t.PROVEEDOR_ID,
    p.NRO_DOCUMENTO_IDENTIDAD AS PROV_NRO_DOCUMENTO_IDENTIDAD,
    p.NOMBRE_FISCAL AS PROV_NOMBRE_FISCAL,

    -- Documento de Identidad del trabajador - campos directos sin alias
    t.TIPO_DOCUMENTO_IDENTIDAD_CODE,
    t.NRO_DOCUMENTO_IDENTIDAD,

    -- Información de tabla SV_TIPO_DOCUMENTO_IDENTIDAD - prefijo TDI_
    tdi.NAME AS TDI_NOMBRE,

    -- Información Personal del trabajador - campos directos sin alias
    t.NOMBRE,
    t.TELEFONO,
    t.EMAIL,

    -- Estado SSO del trabajador - campos directos sin alias
    t.ESTADO_SSO_CODE,
    t.SSO_MOTIVO,
    t.SO_TIENE_VIGENCIA,
    t.SSO_FECHA_INICIO_VIGENCIA,
    t.SSO_FECHA_FIN_VIGENCIA,
    t.IS_DELETED,
    t.IS_ACTIVE,

    -- Información de tabla SV_ESTADO_SSO - prefijo SSO_ESTADO_
    esso.NAME AS SSO_ESTADO_NOMBRE,

    -- Estado SSO Vigente (validación automática de fechas)
    CASE
        WHEN esso.NAME = 'APTO' AND t.SSO_FECHA_FIN_VIGENCIA IS NULL THEN 'APTO'
        WHEN esso.NAME = 'APTO' AND CURRENT_DATE <= t.SSO_FECHA_FIN_VIGENCIA THEN 'APTO'
        WHEN esso.NAME = 'APTO' AND CURRENT_DATE > t.SSO_FECHA_FIN_VIGENCIA THEN 'NO APTO'
        ELSE esso.NAME
    END AS SSO_ESTADO_VIGENTE,

    -- Indicadores del trabajador - campos directos sin alias
    t.IND_TRABAJO_ALTO_RIESGO,
    t.IND_PREVENCIONISTA,

    -- IDs de tipos de actividad de alto riesgo (para filtrado) - prefijo TAR_
    (
        SELECT STRING_AGG(ttar.TIPO_ACTIVIDAD_ALTO_RIESGO_CODE, ',')
        FROM SV_TRABAJADOR_TIPO_ACTIVIDAD_ALTO_RIESGO ttar
        WHERE ttar.TRABAJADOR_ID = t.TRABAJADOR_ID
          AND ttar.IS_ACTIVE = true
    ) AS TAR_CODIGOS,

    -- Tipos de trabajo de alto riesgo (nombres para visualización) - prefijo TAR_
    (
        SELECT STRING_AGG(tar.DESCRIPTION, ', ')
        FROM SV_TRABAJADOR_TIPO_ACTIVIDAD_ALTO_RIESGO ttar
                 INNER JOIN SV_TIPO_ACTIVIDAD_ALTO_RIESGO tar ON ttar.TIPO_ACTIVIDAD_ALTO_RIESGO_CODE = tar.TIPO_ACTIVIDAD_ALTO_RIESGO_CODE
        WHERE ttar.TRABAJADOR_ID = t.TRABAJADOR_ID
          AND ttar.IS_ACTIVE = true
          AND tar.IS_ACTIVE = true
    ) AS TAR_DESCRIPCIONES,

    -- Conteo de documentos SSO adjuntos del trabajador
    (SELECT COUNT(*)
     FROM SV_ADJUNTO a
     WHERE a.TRABAJADOR_ID = t.TRABAJADOR_ID
       AND a.IS_DELETED = FALSE
       AND UPPER(a.CLASIFICATION) LIKE '%SSO%') AS N_DOCS_SSO_ADJUNTOS,

    -- Conteo de documentos Calidad / QA
    (SELECT COUNT(*)
     FROM SV_ADJUNTO a
     WHERE a.TRABAJADOR_ID = t.TRABAJADOR_ID
       AND a.IS_DELETED = FALSE
       AND (UPPER(a.CLASIFICATION) LIKE '%CALIDAD%'
        OR UPPER(a.CLASIFICATION) LIKE '%QA%')) AS N_DOCS_CALIDAD_ADJUNTOS,

    -- Conteo de documentos Actividad Alto Riesgo / AAR
        (SELECT COUNT(*)
         FROM SV_ADJUNTO a
         WHERE a.TRABAJADOR_ID = t.TRABAJADOR_ID
           AND a.IS_DELETED = FALSE
           AND (UPPER(a.CLASIFICATION) LIKE '%ACTIVIDAD ALTO RIESGO%'
            OR UPPER(a.CLASIFICATION) LIKE '%AAR%')) AS N_DOCS_AAR_ADJUNTOS,

    -- Conteo de documentos Actividad a Bordo / AB
        (SELECT COUNT(*)
         FROM SV_ADJUNTO a
         WHERE a.TRABAJADOR_ID = t.TRABAJADOR_ID
           AND a.IS_DELETED = FALSE
           AND (UPPER(a.CLASIFICATION) LIKE '%ACTIVIDAD A BORDO%'
            OR UPPER(a.CLASIFICATION) LIKE '%AB%')) AS N_DOCS_AB_ADJUNTOS,

    -- Conteo de documentos Trabajador P
        (SELECT COUNT(*)
         FROM SV_ADJUNTO a
         WHERE a.TRABAJADOR_ID = t.TRABAJADOR_ID
           AND a.IS_DELETED = FALSE
           AND (UPPER(a.CLASIFICATION) LIKE '%TRABAJADOR P%'
            OR UPPER(a.CLASIFICATION) LIKE '%PREV%')) AS N_DOCS_PREV_ADJUNTOS


FROM SV_TRABAJADOR t
INNER JOIN SV_PROVEEDOR p ON t.PROVEEDOR_ID = p.PROVEEDOR_ID
LEFT JOIN SV_TIPO_DOCUMENTO_IDENTIDAD tdi ON t.TIPO_DOCUMENTO_IDENTIDAD_CODE = tdi.TIPO_DOCUMENTO_IDENTIDAD_CODE
LEFT JOIN SV_ESTADO_SSO esso ON t.ESTADO_SSO_CODE = esso.ESTADO_SSO_CODE

WHERE t.IS_DELETED = FALSE;



-- SELECT * FROM VIEW_TRABAJADOR_REPORT