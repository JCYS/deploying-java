INSERT INTO SV_PARAMETRO (PARAMETRO_CODE, DESCRIPTION, NAME, VALOR, IS_DELETED, VERSION)
VALUES ('OBLIGATORIOPROVDOCSSO', 'Necesita al menos 1 documento adjunto SSO a nivel proveedor para marcar apto en Estado SSO Proveedor', 'Obligatorio Prov Doc SSO', '0', FALSE, 0);

INSERT INTO SV_PARAMETRO (PARAMETRO_CODE, DESCRIPTION, NAME, VALOR, IS_DELETED, VERSION)
VALUES ('OBLIGATORIOPROVDOCCA', 'Necesita al menos 1 documento adjunto Calidad Ambiental a nivel proveedor para marcar apto', 'Obligatorio Prov Doc CA', '0', FALSE, 0);

INSERT INTO SV_PARAMETRO (PARAMETRO_CODE, DESCRIPTION, NAME, VALOR, IS_DELETED, VERSION)
VALUES ('OBLIGSERVICIOALTORIESGO', 'Necesita al menos 1 documento adjunto en "Actividad de alto riesgo" para marcar servicio como apto', 'Oblig Servicio Alto Riesgo', '0', FALSE, 0);

INSERT INTO SV_PARAMETRO (PARAMETRO_CODE, DESCRIPTION, NAME, VALOR, IS_DELETED, VERSION)
VALUES ('OBLIGPROVANDAMIOS', 'Necesita al menos 1 documento adjunto en "Necesita andamios" para marcar el servicio como apto', 'Oblig Prov Andamios', '0', FALSE, 0);

INSERT INTO SV_PARAMETRO (PARAMETRO_CODE, DESCRIPTION, NAME, VALOR, IS_DELETED, VERSION)
VALUES ('OBLIGPROVGRUA', 'Necesita al menos 1 documento adjunto en "Necesita grua" para marcar el servicio como apto', 'Oblig Prov Grua', '0', FALSE, 0);

INSERT INTO SV_PARAMETRO (PARAMETRO_CODE, DESCRIPTION, NAME, VALOR, IS_DELETED, VERSION)
VALUES ('OBLIGPROVEQUIPOSMOVILES', 'Necesita al menos 1 documento adjunto en "Necesita equipos moviles" para marcar servicio como apto', 'Oblig Prov Equipos Moviles', '0', FALSE, 0);

INSERT INTO SV_PARAMETRO (PARAMETRO_CODE, DESCRIPTION, NAME, VALOR, IS_DELETED, VERSION)
VALUES ('OBLIGPROVBUCEO', 'Necesita al menos 1 documento adjunto en "Trabajos de buceo" para marcar el servicio como apto', 'Oblig Prov Buceo', '0', FALSE, 0);

INSERT INTO SV_PARAMETRO (PARAMETRO_CODE, DESCRIPTION, NAME, VALOR, IS_DELETED, VERSION)
VALUES ('OBLIGATORIOTBJDOCSSO', 'Necesita al menos 1 documento adjunto SSO a nivel trabajador para marcar apto en Estado SSO', 'Obligatorio Tbjd Doc SSO', '0', FALSE, 0);

INSERT INTO SV_PARAMETRO (PARAMETRO_CODE, DESCRIPTION, NAME, VALOR, IS_DELETED, VERSION)
VALUES ('OBLIGPROVDOCABORDO', 'Necesita al menos 1 documento adjunto a nivel proveedor si marcó "Actividad a bordo" como "Si"', 'Oblig Prov Doc Abordo', '0', FALSE, 0);

INSERT INTO SV_PARAMETRO (PARAMETRO_CODE, DESCRIPTION, NAME, VALOR, IS_DELETED, VERSION)
VALUES ('OBLIGTBJDPREVENCIONISTA', 'Necesita al menos 1 documento adjunto a nivel trabajador si marcó "Es Prevencionista" como "Si"', 'Oblig Tbjd Prevencionista', '0', FALSE, 0);
