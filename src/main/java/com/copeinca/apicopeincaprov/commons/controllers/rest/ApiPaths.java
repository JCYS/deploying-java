
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/commons/controllers/rest/ApiPaths.java.p.vm
 */
package com.copeinca.apicopeincaprov.commons.controllers.rest;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public final class ApiPaths {

    private static final String API_V1 = "/api/v1";

    public static final class Security {

        private static final String BASE = API_V1 + "/security";

        public static final String AUTH = BASE + "/auth";
        public static final String AUTH_USER = BASE + "/auth/user";

    }

    public static final class MasterData {

        private static final String BASE = API_V1 + "/master-data";

        public static final String SEQUENCE = BASE + "/sequence";
        public static final String ANNOUNCEMENT = BASE + "/announcement";

    }

    public static final class Generator {
        private static final String BASE = API_V1 + "/generator";

        public static final String HTML_PDF = BASE + "/html-to-pdf";
    }

    public static final class Core {

        private static final String BASE = API_V1 + "/core";

        public static final String SVADJUNTO = BASE + "/svadjunto";
        public static final String SVAREA = BASE + "/svarea";
        public static final String SVESTADOCALIDADAMBIENTAL = BASE + "/svestadocalidadambiental";
        public static final String SVESTADOSOLICITUDVISITA = BASE + "/svestadosolicitudvisita";
        public static final String SVESTADOSSO = BASE + "/svestadosso";
        public static final String SVPARAMETRO = BASE + "/svparametro";
        public static final String SVPERSONAL = BASE + "/svpersonal";
        public static final String SVPERSONALSEDE = BASE + "/svpersonalsede";
        public static final String SVPERSONARESTRINGIDA = BASE + "/svpersonarestringida";
        public static final String SVPROVEEDOR = BASE + "/svproveedor";
        public static final String SVPROVEEDORSEDE = BASE + "/svproveedorsede";
        public static final String SVPROVEEDORTIPOACTIVIDADALTORIESGO = BASE + "/svproveedortipoactividadaltoriesgo";
        public static final String SVROL = BASE + "/svrol";
        public static final String SVSEDE = BASE + "/svsede";
        public static final String SVSOLICITUDVISISTA = BASE + "/svsolicitudvisista";
        public static final String SVSOLICITUDVISITAAREAAUTORIZADORA = BASE + "/svsolicitudvisitaareaautorizadora";
        public static final String SVSOLICITUDVISITADETALLE = BASE + "/svsolicitudvisitadetalle";
        public static final String SVSOLICITUDVISITAEQUIPO = BASE + "/svsolicitudvisitaequipo";
        public static final String SVSOLICITUDVISITAEVENTO = BASE + "/svsolicitudvisitaevento";
        public static final String SVSOLICITUDVISITAHISTORIAL = BASE + "/svsolicitudvisitahistorial";
        public static final String SVSOLICITUDVISITAMATERIAL = BASE + "/svsolicitudvisitamaterial";
        public static final String SVSOLICITUDVISITATIPOACTIVIDADALTORIESGO = BASE + "/svsolicitudvisitatipoactividadaltoriesgo";
        public static final String SVSOLICITUDVISITAZONA = BASE + "/svsolicitudvisitazona";
        public static final String SVTIPOACTIVIDADALTORIESGO = BASE + "/svtipoactividadaltoriesgo";
        public static final String SVTIPODOCUMENTOIDENTIDAD = BASE + "/svtipodocumentoidentidad";
        public static final String SVTIPODOCUMENTOPLANILLA = BASE + "/svtipodocumentoplanilla";
        public static final String SVTRABAJADOR = BASE + "/svtrabajador";
        public static final String SVTRABAJADORTIPOACTIVIDADALTORIESGO = BASE + "/svtrabajadortipoactividadaltoriesgo";
        public static final String SVUSUARIO = BASE + "/svusuario";
        public static final String SVUSUARIOROLSEDE = BASE + "/svusuariorolsede";
        public static final String SVZONA = BASE + "/svzona";
        public static final String VIEWADJUNTOREPORT = BASE + "/viewadjuntoreport";
        public static final String VIEWPERSONALREPORT = BASE + "/viewpersonalreport";
        public static final String VIEWPERSONARESTRINGIDAREPORT = BASE + "/viewpersonarestringidareport";
        public static final String VIEWPROVEEDORREPORT = BASE + "/viewproveedorreport";
        public static final String VIEWSOLICITUDVISITAHISTORIALREPORT = BASE + "/viewsolicitudvisitahistorialreport";
        public static final String VIEWSOLICITUDVISITAREPORT = BASE + "/viewsolicitudvisitareport";
        public static final String VIEWTRABAJADORREPORT = BASE + "/viewtrabajadorreport";

    }

    public static final class Notification {
        private static final String BASE = API_V1 + "/notification";

        public static final String MAIL = BASE + "/mail";
    }

}
