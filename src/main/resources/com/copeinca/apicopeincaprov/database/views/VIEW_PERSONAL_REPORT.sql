CREATE OR REPLACE VIEW VIEW_PERSONAL_REPORT AS
SELECT
    -- Información básica de SV_PERSONAL_SEDE (campos directos sin alias)
    sps.PERSONAL_SEDE_ID,
    sps.IS_ACTIVE,

    -- Foreign Keys (campos directos sin alias)
    sps.PERSONAL_ID,
    sps.SEDE_ID,

    -- Información de SV_PERSONAL - prefijo PERSONAL_
    sp.USUARIO AS PERSONAL_USUARIO,
    sp.NOMBRE_USUARIO AS PERSONAL_NOMBRE_USUARIO,
    sp.CODIGO AS PERSONAL_CODIGO,
    sp.DESCRIPCION AS PERSONAL_DESCRIPCION,

    -- Información de SV_SEDE - prefijo SEDE_
    ss.NAME AS SEDE_NAME,
    ss.AMBITO AS SEDE_AMBITO

FROM SV_PERSONAL_SEDE sps

-- Relaciones LEFT JOIN
         INNER JOIN SV_PERSONAL sp ON sps.PERSONAL_ID = sp.PERSONAL_ID
         INNER JOIN SV_SEDE ss ON sps.SEDE_ID = ss.SEDE_ID
WHERE sps.IS_DELETED = FALSE;