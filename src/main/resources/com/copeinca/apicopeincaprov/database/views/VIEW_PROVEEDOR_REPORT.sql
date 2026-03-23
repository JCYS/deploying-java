CREATE OR REPLACE VIEW VIEW_PROVEEDOR_REPORT AS
SELECT
    -- Información básica del proveedor (campos directos sin alias)
    p.PROVEEDOR_ID,
    p.NRO_DOCUMENTO_IDENTIDAD,
    p.NOMBRE_FISCAL,
    p.EMAIL,
    p.DIRECCION,
    p.TELEFONO,
    p.NACIONAL,
    p.CONTACTO_PRINCIPAL,
    p.CONTACTO_PRINCIPAL_TELEFONO,
    p.ORIGEN_REGISTRO,

    -- IDs de sedes permitidas (lista concatenada para filtrado) - prefijo SEDES_
    (
        SELECT STRING_AGG(ps.SEDE_ID, ',')
        FROM SV_PROVEEDOR_SEDE ps
        WHERE ps.PROVEEDOR_ID = p.PROVEEDOR_ID
          AND ps.IS_ACTIVE = true
    ) AS SEDES_IDS,

    -- Sedes permitidas (lista concatenada de nombres de sedes) - prefijo SEDES_
    (
        SELECT STRING_AGG(s.NAME, ', ')
        FROM SV_PROVEEDOR_SEDE ps
                 INNER JOIN SV_SEDE s ON ps.SEDE_ID = s.SEDE_ID
        WHERE ps.PROVEEDOR_ID = p.PROVEEDOR_ID
          AND ps.IS_ACTIVE = true
    ) AS SEDES_NOMBRES,

    -- Información SSO del proveedor - campos directos sin alias
    p.ESTADO_SSO_CODE,
    p.SSO_TIENE_VIGENCIA,
    p.SSO_FECHA_INICIO_VIGENCIA,
    p.SSO_FECHA_FIN_VIGENCIA,
    p.SSO_MOTIVO,
    p.SSO_USUARIO_EVALUADOR,
    p.SSO_FECHA_EVALUACION,

    -- Información de tabla SV_ESTADO_SSO - prefijo SSO_ESTADO_
    esso.NAME AS SSO_ESTADO_NOMBRE,
    esso.DESCRIPTION AS SSO_ESTADO_DESCRIPCION,

    -- Estado SSO calculado con validación de vigencia
    CASE
        WHEN p.ESTADO_SSO_CODE = 'ST_SSO_0002' THEN
            CASE
                WHEN p.SSO_FECHA_FIN_VIGENCIA IS NULL THEN 'APTO'
                WHEN CURRENT_DATE <= p.SSO_FECHA_FIN_VIGENCIA THEN 'APTO'
                ELSE 'NO APTO'
            END
        ELSE esso.NAME
    END AS SSO_ESTADO_VIGENTE,

    -- Información Calidad Ambiental del proveedor - campos directos sin alias
    p.ESTADO_CALIDAD_AMBIENTAL_CODE,
    p.CA_TIENE_VIGENCIA,
    p.CA_FECHA_INICIO_VIGENCIA,
    p.CA_FECHA_FIN_VIGENCIA,
    p.CA_MOTIVO,
    p.CA_USUARIO_EVALUADOR,
    p.CA_FECHA_EVALUACION,

    -- Información de tabla SV_ESTADO_CALIDAD_AMBIENTAL - prefijo CA_ESTADO_
    eca.NAME AS CA_ESTADO_NOMBRE,
    eca.DESCRIPTION AS CA_ESTADO_DESCRIPCION,

    -- Estado Calidad Ambiental calculado con validación de vigencia
    CASE
        WHEN p.ESTADO_CALIDAD_AMBIENTAL_CODE = 'ST_CA_0002' THEN
            CASE
                WHEN p.CA_FECHA_FIN_VIGENCIA IS NULL THEN 'APTO'
                WHEN CURRENT_DATE <= p.CA_FECHA_FIN_VIGENCIA THEN 'APTO'
                ELSE 'NO APTO'
            END
        ELSE eca.NAME
    END AS CA_ESTADO_VIGENTE,

    -- Conteo de trabajadores aptos (estado SSO = 'APTO')
    (
        SELECT COUNT(*)
        FROM SV_TRABAJADOR t
                 INNER JOIN SV_ESTADO_SSO es ON t.ESTADO_SSO_CODE = es.ESTADO_SSO_CODE
        WHERE t.PROVEEDOR_ID = p.PROVEEDOR_ID
          AND UPPER(es.NAME) = 'ST_SSO_0002'
    ) AS N_TRABAJADORES_APTOS,

    -- Conteo total de trabajadores
    (
        SELECT COUNT(*)
        FROM SV_TRABAJADOR t
        WHERE t.PROVEEDOR_ID = p.PROVEEDOR_ID
    ) AS N_TRABAJADORES_TOTALES,

    -- Conteo de documentos adjuntos de proveedor para SSO
    (
        SELECT COUNT(*)
        FROM SV_ADJUNTO a
        WHERE a.PROVEEDOR_ID = p.PROVEEDOR_ID
          AND a.TRABAJADOR_ID IS NULL
          AND UPPER(a.CLASIFICATION) LIKE 'SSO'
          AND a.IS_DELETED = FALSE
    ) AS N_DOCS_PROV_SSO,

    -- Conteo de documentos adjuntos de proveedor para Calidad Ambiental
    (
        SELECT COUNT(*)
        FROM SV_ADJUNTO a
        WHERE a.PROVEEDOR_ID = p.PROVEEDOR_ID
          AND a.TRABAJADOR_ID IS NULL
          AND UPPER(a.CLASIFICATION) LIKE 'QA'
          AND a.IS_DELETED = FALSE
    ) AS N_DOCS_CA,
    -- Conteo de documentos adjuntos de proveedor para AAR
    (
        SELECT COUNT(*)
        FROM SV_ADJUNTO a
        WHERE a.PROVEEDOR_ID = p.PROVEEDOR_ID
          AND a.TRABAJADOR_ID IS NULL
          AND UPPER(a.CLASIFICATION) LIKE 'AAR'
          AND a.IS_DELETED = FALSE
    ) AS N_DOCS_AAR,

    -- Conteo de documentos adjuntos de proveedor para AB
    (
        SELECT COUNT(*)
        FROM SV_ADJUNTO a
        WHERE a.PROVEEDOR_ID = p.PROVEEDOR_ID
          AND a.TRABAJADOR_ID IS NULL
          AND UPPER(a.CLASIFICATION) LIKE 'AB'
          AND a.IS_DELETED = FALSE
    ) AS N_DOCS_AB,

    -- Conteo de documentos adjuntos de trabajadores para SSO
    (
        SELECT COUNT(*)
        FROM SV_ADJUNTO a
        WHERE a.PROVEEDOR_ID = p.PROVEEDOR_ID
          AND a.TRABAJADOR_ID IS NOT NULL
          AND UPPER(a.CLASIFICATION) LIKE 'Trabajador SSO'
          AND a.IS_DELETED = FALSE
    ) AS N_DOCS_TRABAJ_SSO,

    -- Indicadores de actividad (campos directos sin alias)
    p.IND_REALIZA_ACTIVIDAD_ALTO_RIESGO,
    p.IND_REALIZA_ACTIVIDAD_A_BORDO,

    -- IDs de tipos de actividad de alto riesgo (para filtrado) - prefijo TAR_
    (
        SELECT STRING_AGG(ptar.TIPO_ACTIVIDAD_ALTO_RIESGO_CODE, ',')
        FROM SV_PROVEEDOR_TIPO_ACTIVIDAD_ALTO_RIESGO ptar
        WHERE ptar.PROVEEDOR_ID = p.PROVEEDOR_ID
          AND ptar.IS_ACTIVE = true
    ) AS TAR_CODIGOS,

    -- Tipos de trabajo de alto riesgo (nombres para visualización) - prefijo TAR_
    (
        SELECT STRING_AGG(tar.DESCRIPTION, ', ')
        FROM SV_PROVEEDOR_TIPO_ACTIVIDAD_ALTO_RIESGO ptar
                 INNER JOIN SV_TIPO_ACTIVIDAD_ALTO_RIESGO tar ON ptar.TIPO_ACTIVIDAD_ALTO_RIESGO_CODE = tar.TIPO_ACTIVIDAD_ALTO_RIESGO_CODE
        WHERE ptar.PROVEEDOR_ID = p.PROVEEDOR_ID
          AND ptar.IS_ACTIVE = true
          AND tar.IS_ACTIVE = true
    ) AS TAR_DESCRIPCIONES

FROM SV_PROVEEDOR p
         LEFT JOIN SV_ESTADO_SSO esso ON p.ESTADO_SSO_CODE = esso.ESTADO_SSO_CODE
         LEFT JOIN SV_ESTADO_CALIDAD_AMBIENTAL eca ON p.ESTADO_CALIDAD_AMBIENTAL_CODE = eca.ESTADO_CALIDAD_AMBIENTAL_CODE;



-- SELECT * FROM VIEW_PROVEEDOR_REPORT