package com.copeinca.apicopeincaprov.modules.notificacion.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EmailTemplate {

    /**
     * Evento: Proveedor / Creador / SSO / CA presiona 'Grabar y enviar'<br>
     * Arguments order:<br>
     * Recipient:<br>
     * 1 Email Proveedor<br>
     * Body:<br>
     * 1 estadoSSOEmpresa<br>
     * 2 estadoCalidadAmbientalEmpresa<br>
     * 3 detalleTrabajadores<br>
     * 4 urlBtp<br>
     * 5 codigoProveedor<br>
     */
    MAIL_01(
            "MAIL-01",
            "%s",
            "Estado de habilitación de su empresa y trabajadores – COPEINCA",
            """
                    Estimado proveedor,
                    
                    Le informamos que la información registrada ha sido revisada.
                    
                    Estado SSO Empresa: %s
                    Estado Calidad Ambiental Empresa: %s
                    
                    Detalle de trabajadores:
                    %s
                    
                    Puede ingresar a la plataforma para mayor detalle:
                    %s
                    
                    Código de proveedor: %s
                    
                    Atentamente,
                    Equipo COPEINCA
                    """
    ),

    /**
     * Evento: Acción manual 'Reenviar mail'<br>
     * Arguments order:<br>
     * Recipient:<br>
     * 1 Email Proveedor<br>
     * Body:<br>
     * 1 urlBtp<br>
     * 2 codigoProveedor<br>
     */
    MAIL_02(
            "MAIL-02",
            "%s",
            "Acceso a plataforma de proveedores – COPEINCA",
            """
                    Estimado proveedor,
                    
                    Le reenviamos la información de acceso a la plataforma de proveedores COPEINCA.
                    
                    Ingrese desde el siguiente enlace:
                    %s
                    
                    Código de proveedor: %s
                    
                    Atentamente,
                    Equipo COPEINCA
                    """
    ),

    /**
     * Evento: Proveedor presiona 'Grabar y enviar' con sedes nuevas<br>
     * Arguments order:<br>
     * Recipient:<br>
     * 1 Email SSOs y CAs por Sede<br>
     * Subject:<br>
     * 1 razonSocial<br>
     * Body:<br>
     * 1 razonSocial<br>
     * 2 sedes<br>
     */
    MAIL_03(
            "MAIL-03",
            "%s",
            "Proveedor pendiente de evaluación – %s",
            """
                    Estimado usuario,
                    
                    El proveedor %s ha enviado información para su evaluación.
                    
                    Sede(s) solicitadas:
                    %s
                    
                    Ingrese a la plataforma para realizar la evaluación correspondiente.
                    
                    Atentamente,
                    Sistema COPEINCA
                    """
    ),

    /**
     * Evento: Proveedor presiona 'Grabar y enviar' con sedes nuevas<br>
     * Arguments order:<br>
     * Recipient:<br>
     * 1 Email SSOs<br>
     * Subject:<br>
     * 1 razonSocial<br>
     * Body:<br>
     * 1 motivoObservacion<br>
     * 2 urlBtp<br>
     * 3 codigoProveedor<br>
     */
    MAIL_04(
            "MAIL-04",
            "%s",
            "Proveedor observado – %s",
            """
                    Estimado proveedor,
                    
                    La información enviada ha sido observada.
                    
                    Motivo de la observación:
                    %s
                    
                    Puede ingresar a la plataforma para realizar las correcciones necesarias:
                    %s
                    
                    Código de proveedor: %s
                    
                    Atentamente,
                    Equipo COPEINCA
                    """
    ),

    /**
     * Evento: Proveedor modifica documentos Calidad Ambiental y envía<br>
     * Arguments order:<br>
     * Recipient:<br>
     * 1 Email CAs<br>
     * Subject:<br>
     * 1 razonSocial<br>
     * Body:<br>
     * 1 urlBtp<br>
     * 2 codigoProveedor<br>
     */
    MAIL_05(
            "MAIL-05",
            "%s",
            "Proveedor aprobado – %s",
            """
                    Estimado proveedor,
                    
                    Nos complace informarle que su empresa ha sido aprobada satisfactoriamente.
                    
                    Puede operar conforme a los lineamientos establecidos por COPEINCA.
                    
                    Ingrese a la plataforma para más información:
                    %s
                    
                    Código de proveedor: %s
                    
                    Atentamente,
                    Equipo COPEINCA
                    """
    ),

    /**
     * Evento: Proveedor modifica trabajadores o documentos SSO de trabajador<br>
     * Arguments order:<br>
     * Recipient:<br>
     * 1 Email SSOs<br>
     * Subject:<br>
     * 1 razonSocial<br>
     * Body:<br>
     * 1 motivoInhabilitacion<br>
     * 2 urlBtp<br>
     * 3 codigoProveedor<br>
     */
    MAIL_06(
            "MAIL-06",
            "%s",
            "Proveedor inhabilitado – %s",
            """
                    Estimado proveedor,
                    
                    Le informamos que su empresa ha sido inhabilitada temporalmente.
                    
                    Motivo:
                    %s
                    
                    Para mayor detalle puede ingresar a la plataforma:
                    %s
                    
                    Código de proveedor: %s
                    
                    Atentamente,
                    Equipo COPEINCA
                    """
    ),

    /**
     * Evento: Creación de Proveedor<br>
     * Arguments order:<br>
     * Recipient:<br>
     * 1 Email proveedor<br>
     * Subject:<br>
     * 1 razonSocial<br>
     * Body:<br>
     * 1 razonSocial<br>
     * 2 usuarioBtp<br>
     * 3 urlBtp<br>
     */
    CREAR_PROVEEDOR(
            "CPT_2",
            "%s",
            "Creación de Proveedor – %s",
            """
                    Estimado %s,
                    
                    Se le comunica que se ha creado como proveedor en nuestra app de Control de Proveedores. Deberá acceder con su usuario %s y su clave, en la url %s
                    
                    Atte.
                    Administración de Procesos
                    """
    ),

    /**
     * Evento: Evaluación de Proveedor<br>
     * Arguments order:<br>
     * Recipient:<br>
     * 1 Email proveedor<br>
     * Subject:<br>
     * 1 razonSocial<br>
     * Body:<br>
     * 1 razonSocial<br>
     * 2 usuarioBtp<br>
     * 3 urlBtp<br>
     * 4 urlBtp<br>
     * 5 statusSSO<br>
     * 6 motivoSSO<br>
     * 7 statusCalAmbiental<br>
     * 8 motivoCalAmbiental<br>
     * 9 tablaTrabajadores<br>
     */
    EVALUAR_PROVEEDOR(
            "CPT_3-4",
            "%s",
            "Evaluación de Proveedor – %s",
            """
                    <html>
                    <body>
                        <p>Estimado %s</p>
                    
                        <p>
                        Se le comunica que ha habido modificaciones en su evaluación, en nuestra app de Control de Proveedores. Deberá revisar los datos accediendo con su usuario <b>%s</b> y su clave, en la url <a href="%s">%s</a>
                        </p>
                    
                        <p>
                            <b>Status SSO:</b> %s<br>
                            <b>Motivo SSO:</b> %s<br>
                            <b>Status Calidad Ambiental:</b> %s<br>
                            <b>Motivo Calidad Ambiental:</b> %s
                        </p>
                    
                        <h3>Lista Trabajadores</h3>
                    
                        <table border="1" cellpadding="5" cellspacing="0" style="border-collapse:collapse;">
                            <thead>
                                <tr>
                                    <th>DNI</th>
                                    <th>Nombre Completo</th>
                                    <th>Status SSO</th>
                                    <th>Motivo SSO</th>
                                    <th>N° Docs</th>
                                    <th>Alto Riesgo</th>
                                    <th>N° Docs AR</th>
                                    <th>Prevenc</th>
                                    <th>N° Docs Prevenc</th>
                                </tr>
                            </thead>
                            <tbody>
                                %s
                            </tbody>
                        </table>
                    
                        <br>
                        <p>
                            Atte.<br>
                            Administración de Procesos
                        </p>
                    </body>
                    </html>
                    """
    ),

    /**
     * Evento: Modificación de Proveedor<br>
     * Arguments order:<br>
     * Recipient:<br>
     * 1 Email SSO y Calidad de SEDE<br>
     * Subject:<br>
     * 1 razonSocial<br>
     * Body:<br>
     * 1 listaSedes<br>
     * 2 statusSSO<br>
     * 3 nroDocSSO<br>
     * 4 altoRiesgo<br>
     * 5 nroDocAltoRiesgo<br>
     * 6 aBordo<br>
     * 7 nroDocABordo<br>
     * 8 statusCalAmbiental<br>
     * 9 nroDocCalAmbiental<br>
     * 10 tablaTrabajadores<br>
     * 11 urlBtp<br>
     * 12 urlBtp<br>
     */
    MODIFICACION_PROVEEDOR(
            "CPT_8",
            "%s",
            "Modificación de datos por parte del proveedor – %s",
            """
                    <html>
                    <body>
                        <p>Estimado Usuario</p>
                    
                        <p>
                        Se le comunica que ha habido modificaciones cargadas por parte del proveedor, en nuestra app de Control de Proveedores. 
                        </p>
                    
                        <div style="display: flex; justify-content: space-between; width: 100%%; margin-bottom: 10px;">
                            <div><strong>Sedes permitidas:</strong> <span>%s</span></div>
                        </div>
                        
                        <div style="display: flex; justify-content: space-between; width: 100%%; margin-bottom: 10px;">
                            <div><strong>Status SSO:</strong> <span>%s</span></div>
                            <div><strong>Nº Docs adjuntos SSO:</strong> <span>%s</span></div>
                        </div>
                        
                        <div style="display: flex; justify-content: space-between; width: 100%%; margin-bottom: 10px;">
                            <div><strong>Alto riesgo?:</strong> <span>%s</span></div>
                            <div><strong>Nº Docs adjuntos Alto riesgo:</strong> <span>%s</span></div>
                        </div>
                        
                        <div style="display: flex; justify-content: space-between; width: 100%%; margin-bottom: 10px;">
                            <div><strong>A bordo?:</strong> <span>%s</span></div>
                            <div><strong>Nº Docs adjuntos a bordo:</strong> <span>%s</span></div>
                        </div>
                        
                        <div style="display: flex; justify-content: space-between; width: 100%%; margin-bottom: 10px;">
                            <div><strong>Status Calidad Ambiental:</strong> <span>%s</span></div>
                            <div><strong>Nº Docs adjuntos C.Amb:</strong> <span>%s</span></div>
                        </div>
                    
                        <h3>Lista Trabajadores</h3>
                    
                        <table border="1" cellpadding="5" cellspacing="0" style="border-collapse:collapse;">
                            <thead>
                                <tr>
                                    <th>DNI</th>
                                    <th>Nombre Completo</th>
                                    <th>Status SSO</th>
                                    <th>Motivo SSO</th>
                                    <th>N° Docs</th>
                                    <th>Alto Riesgo</th>
                                    <th>N° Docs AR</th>
                                    <th>Prevenc</th>
                                    <th>N° Docs Prevenc</th>
                                </tr>
                            </thead>
                            <tbody>
                                %s
                            </tbody>
                        </table>
                        
                        <p>
                        Deberá revisar los datos accediendo en la url <a href="%s">%s</a>
                        </p>
                    
                        <br>
                        <p>
                            Atte.<br>
                            Administración de Procesos
                        </p>
                    </body>
                    </html>
                    """
    ),

    /**
     * Evento: Solicitud de visita programada o reenviar solicitud a aprobación tras rechazo<br>
     * Arguments order:<br>
     * Recipient:<br>
     * 1 Email SSO y Calidad de SEDE<br>
     * Subject:<br>
     * 1 razonSocial<br>
     * Body:<br>
     * 1 razonSocial<br>
     * 2 sede<br>
     * 3 usuarioVisitado<br>
     * 4 fechaHoraInicio<br>
     * 5 fechaHoraFin<br>
     * 6 ordenServicio<br>
     * 7 descVisita<br>
     * 8 obsEspeciales<br>
     * 9 areasAVisitar<br>
     * 10 zonasAVisitar<br>
     * 11 altoRiesgo<br>
     * 12 actABordo<br>
     * 13 andamios<br>
     * 14 grua<br>
     * 15 equiposMoviles<br>
     * 16 trabajoBuceo<br>
     * 17 tablaTrabajadores<br>
     * 18 urlBtp<br>
     * 19 urlBtp<br>
     */
    VISITA_PROGRAMADA_PROVEEDOR(
            "SVT1-4",
            "%s",
            "Solicitud de visita programada por parte del proveedor – %s",
            """
                    <html>
                    <body>
                        <p>Estimado Usuario</p>
                    
                        <p>
                        Se le comunica que el proveedor %s, ha registrado una solicitud de visita programada, en nuestra app de Control de Proveedores.
                        </p>
                    
                        <div style="display: flex; justify-content: space-between; width: 100%%; margin-bottom: 10px;">
                            <div><strong>Sede:</strong> <span>%s</span></div>
                            <div><strong>A quien se visita:</strong> <span>%s</span></div>
                        </div>
                        
                        <p>
                            <b>Fecha-hora inicio:</b> %s<br>
                            <b>Fecha-hora fin:</b> %s<br>
                            <b>Orden de servicio:</b> %s<br>
                            <b>Descripción de visita:</b> %s<br>
                            <b>Observaciones especiales:</b> %s<br>
                            <b>Areas a visitar:</b> %s<br>
                            <b>Zonas a visitar:</b> %s<br>
                        </p>
                        
                        <div style="display: flex; justify-content: space-between; width: 100%%; margin-bottom: 10px;">
                            <div><strong>Alto riesgo?:</strong> <span>%s</span></div>
                            <div><strong>Act. A bordo?:</strong> <span>%s</span></div>
                            <div><strong>Andamios?:</strong> <span>%s</span></div>
                        </div>
                        
                        <div style="display: flex; justify-content: space-between; width: 100%%; margin-bottom: 10px;">
                            <div><strong>Grua?:</strong> <span>%s</span></div>
                            <div><strong>Equipos móviles?:</strong> <span>%s</span></div>
                            <div><strong>Trabajo de Buceo?:</strong> <span>%s</span></div>
                        </div>
                    
                        <h3>Lista Trabajadores</h3>
                    
                        <table border="1" cellpadding="5" cellspacing="0" style="border-collapse:collapse;">
                            <thead>
                                <tr>
                                    <th>DNI</th>
                                    <th>Nombre Completo</th>
                                    <th>Status SSO</th>
                                    <th>Motivo SSO</th>
                                    <th>N° Docs</th>
                                    <th>Alto Riesgo</th>
                                    <th>Prevenc</th>
                                </tr>
                            </thead>
                            <tbody>
                                %s
                            </tbody>
                        </table>
                        
                        <p>
                        Deberá revisar los datos accediendo en la url <a href="%s">%s</a>
                        Favor
                        </p>
                    
                        <br>
                        <p>
                            Atte.<br>
                            Administración de Procesos
                        </p>
                    </body>
                    </html>
                    """
    ),

    /**
     * Evento: Aprobación de visita programada por SSO o Calidad Ambiental<br>
     * Arguments order:<br>
     * Recipient:<br>
     * 1 Email proveedor<br>
     * Subject:<br>
     * 1 razonSocial<br>
     * 2 estadoSSO<br>
     * 1 estadoCalidadAmbiental<br>
     * Body:<br>
     * 1 razonSocial<br>
     * 2 sede<br>
     * 3 usuarioVisitado<br>
     * 4 fechaHoraInicio<br>
     * 5 fechaHoraFin<br>
     * 6 ordenServicio<br>
     * 7 descVisita<br>
     * 8 obsEspeciales<br>
     * 9 areasAVisitar<br>
     * 10 zonasAVisitar<br>
     * 11 altoRiesgo<br>
     * 12 actABordo<br>
     * 13 andamios<br>
     * 14 grua<br>
     * 15 equiposMoviles<br>
     * 16 trabajoBuceo<br>
     * 17 tablaTrabajadores<br>
     * 18 statusSSO<br>
     * 19 motivoSSO<br>
     * 20 statusCalAmbiental<br>
     * 21 motivoCalAmbiental<br>
     * 22 urlBtp<br>
     * 23 urlBtp<br>
     */
    APROB_VISITA_PROGRAMADA_PROVEEDOR(
            "SVT2-3",
            "%s",
            "Solicitud de visita programada por parte del proveedor - %s - %s - %s",
            """
                    <html>
                    <body>
                        <p>Estimado %s</p>
                    
                        <p>
                        Se le comunica que se ha modificado el status de su solicitud de visita programada, en nuestra app de Control de Proveedores.
                        </p>
                    
                        <div style="display: flex; justify-content: space-between; width: 100%%; margin-bottom: 10px;">
                            <div><strong>Sede:</strong> <span>%s</span></div>
                            <div><strong>A quien se visita:</strong> <span>%s</span></div>
                        </div>
                        
                        <p>
                            <b>Fecha-hora inicio:</b> %s<br>
                            <b>Fecha-hora fin:</b> %s<br>
                            <b>Orden de servicio:</b> %s<br>
                            <b>Descripción de visita:</b> %s<br>
                            <b>Observaciones especiales:</b> %s<br>
                            <b>Areas a visitar:</b> %s<br>
                            <b>Zonas a visitar:</b> %s<br>
                        </p>
                        
                        <div style="display: flex; justify-content: space-between; width: 100%%; margin-bottom: 10px;">
                            <div><strong>Alto riesgo?:</strong> <span>%s</span></div>
                            <div><strong>Act. A bordo?:</strong> <span>%s</span></div>
                            <div><strong>Andamios?:</strong> <span>%s</span></div>
                        </div>
                        
                        <div style="display: flex; justify-content: space-between; width: 100%%; margin-bottom: 10px;">
                            <div><strong>Grua?:</strong> <span>%s</span></div>
                            <div><strong>Equipos móviles?:</strong> <span>%s</span></div>
                            <div><strong>Trabajo de Buceo?:</strong> <span>%s</span></div>
                        </div>
                    
                        <h3>Lista Trabajadores</h3>
                    
                        <table border="1" cellpadding="5" cellspacing="0" style="border-collapse:collapse;">
                            <thead>
                                <tr>
                                    <th>DNI</th>
                                    <th>Nombre Completo</th>
                                    <th>Status SSO</th>
                                    <th>Motivo SSO</th>
                                    <th>N° Docs</th>
                                    <th>Alto Riesgo</th>
                                    <th>Prevenc</th>
                                </tr>
                            </thead>
                            <tbody>
                                %s
                            </tbody>
                        </table>
                        
                        <p>
                            <b>Status SSO:</b> %s<br>
                            <b>Motivo SSO:</b> %s<br>
                            <b>Status Calidad Ambiental:</b> %s<br>
                            <b>Motivo Calidad Ambiental:</b> %s<br>
                        </p>
                        <p>
                        Deberá revisar los datos accediendo en la url <a href="%s">%s</a>
                        Favor
                        </p>
                    
                        <br>
                        <p>
                            Atte.<br>
                            Administración de Procesos
                        </p>
                    </body>
                    </html>
                    """
    ),

    /**
     * Evento: Si la solicitud pasó a POR APROBAR VISITADO<br>
     * Arguments order:<br>
     * Recipient:<br>
     * 1 Email usuario visitado<br>
     * Subject:<br>
     * 1 razonSocial<br>
     * Body:<br>
     * 1 nombreUsuarioVisitado<br>
     * 2 razonSocial<br>
     * 3 sede<br>
     * 4 usuarioVisitado<br>
     * 5 fechaHoraInicio<br>
     * 6 fechaHoraFin<br>
     * 7 ordenServicio<br>
     * 8 descVisita<br>
     * 9 obsEspeciales<br>
     * 10 areasAVisitar<br>
     * 11 zonasAVisitar<br>
     * 12 altoRiesgo<br>
     * 13 actABordo<br>
     * 14 andamios<br>
     * 15 grua<br>
     * 16 equiposMoviles<br>
     * 17 trabajoBuceo<br>
     * 18 tablaTrabajadores<br>
     * 19 statusSSO<br>
     * 20 motivoSSO<br>
     * 21 statusCalAmbiental<br>
     * 22 motivoCalAmbiental<br>
     * 23 urlBtp<br>
     * 24 urlBtp<br>
     */
    APROB_VISITA_PROGRAMADA_VISITADO(
            "SVT2-3",
            "%s",
            "Solicitud de visita programada por parte del proveedor - %s - APROBADA SSO | APROBADA CALIDAD AMBIENTAL – POR APROBAR VISITADO",
            """
                    <html>
                    <body>
                        <p>Estimado %s</p>
                    
                        <p>
                        Se solicita su aprobación de la solicitud de visita programada del proveedor %s, en nuestra app de Control de Proveedores.
                        </p>
                    
                        <div style="display: flex; justify-content: space-between; width: 100%%; margin-bottom: 10px;">
                            <div><strong>Sede:</strong> <span>%s</span></div>
                            <div><strong>A quien se visita:</strong> <span>%s</span></div>
                        </div>
                        
                        <p>
                            <b>Fecha-hora inicio:</b> %s<br>
                            <b>Fecha-hora fin:</b> %s<br>
                            <b>Orden de servicio:</b> %s<br>
                            <b>Descripción de visita:</b> %s<br>
                            <b>Observaciones especiales:</b> %s<br>
                            <b>Areas a visitar:</b> %s<br>
                            <b>Zonas a visitar:</b> %s<br>
                        </p>
                        
                        <div style="display: flex; justify-content: space-between; width: 100%%; margin-bottom: 10px;">
                            <div><strong>Alto riesgo?:</strong> <span>%s</span></div>
                            <div><strong>Act. A bordo?:</strong> <span>%s</span></div>
                            <div><strong>Andamios?:</strong> <span>%s</span></div>
                        </div>
                        
                        <div style="display: flex; justify-content: space-between; width: 100%%; margin-bottom: 10px;">
                            <div><strong>Grua?:</strong> <span>%s</span></div>
                            <div><strong>Equipos móviles?:</strong> <span>%s</span></div>
                            <div><strong>Trabajo de Buceo?:</strong> <span>%s</span></div>
                        </div>
                    
                        <h3>Lista Trabajadores</h3>
                    
                        <table border="1" cellpadding="5" cellspacing="0" style="border-collapse:collapse;">
                            <thead>
                                <tr>
                                    <th>DNI</th>
                                    <th>Nombre Completo</th>
                                    <th>Status SSO</th>
                                    <th>Motivo SSO</th>
                                    <th>N° Docs</th>
                                    <th>Alto Riesgo</th>
                                    <th>Prevenc</th>
                                </tr>
                            </thead>
                            <tbody>
                                %s
                            </tbody>
                        </table>
                        
                        <p>
                            <b>Status SSO:</b> %s<br>
                            <b>Motivo SSO:</b> %s<br>
                            <b>Status Calidad Ambiental:</b> %s<br>
                            <b>Motivo Calidad Ambiental:</b> %s<br>
                        </p>
                        <p>
                        Deberá revisar los datos accediendo en la url <a href="%s">%s</a>
                        </p>
                        
                        <div style="display: flex; justify-content: space-between; width: 100%%; margin-bottom: 10px;">
                            <div><strong>APROBAR</strong></div>
                            <div><strong>RECHAZAR</strong></div>
                        </div>
                    
                        <br>
                        <p>
                            Atte.<br>
                            Administración de Procesos
                        </p>
                    </body>
                    </html>
                    """
    ),

    /**
     * Evento: Como proveedor cancelo solicitud de visita<br>
     * Arguments order:<br>
     * Recipient:<br>
     * 1 Email usuario visitado<br>
     * Subject:<br>
     * 1 razonSocial<br>
     * Body:<br>
     * 1 nombreUsuarioVisitado<br>
     * 2 razonSocial<br>
     * 3 sede<br>
     * 4 usuarioVisitado<br>
     * 5 fechaHoraInicio<br>
     * 6 fechaHoraFin<br>
     * 7 ordenServicio<br>
     * 8 descVisita<br>
     * 9 obsEspeciales<br>
     * 10 areasAVisitar<br>
     * 11 zonasAVisitar<br>
     * 12 altoRiesgo<br>
     * 13 actABordo<br>
     * 14 andamios<br>
     * 15 grua<br>
     * 16 equiposMoviles<br>
     * 17 trabajoBuceo<br>
     * 18 tablaTrabajadores<br>
     * 19 statusSSO<br>
     * 20 motivoSSO<br>
     * 21 statusCalAmbiental<br>
     * 22 motivoCalAmbiental<br>
     * 23 urlBtp<br>
     * 24 urlBtp<br>
     */
    CANCELAR_VISITA_PROGRAMADA_PROVEEDOR(
            "SVT8",
            "%s",
            "Solicitud de visita programada por parte del proveedor - %s - CANCELADA",
            """
                    <html>
                    <body>
                        <p>Estimado %s</p>
                    
                        <p>
                        Se comunica que la solicitud de visita programada del proveedor %s, ha sido CANCELADA en nuestra app de Control de Proveedores. A continuación los datos:
                        </p>
                    
                        <div style="display: flex; justify-content: space-between; width: 100%%; margin-bottom: 10px;">
                            <div><strong>Sede:</strong> <span>%s</span></div>
                            <div><strong>A quien se visita:</strong> <span>%s</span></div>
                        </div>
                        
                        <p>
                            <b>Fecha-hora inicio:</b> %s<br>
                            <b>Fecha-hora fin:</b> %s<br>
                            <b>Orden de servicio:</b> %s<br>
                            <b>Descripción de visita:</b> %s<br>
                            <b>Observaciones especiales:</b> %s<br>
                            <b>Areas a visitar:</b> %s<br>
                            <b>Zonas a visitar:</b> %s<br>
                        </p>
                        
                        <div style="display: flex; justify-content: space-between; width: 100%%; margin-bottom: 10px;">
                            <div><strong>Alto riesgo?:</strong> <span>%s</span></div>
                            <div><strong>Act. A bordo?:</strong> <span>%s</span></div>
                            <div><strong>Andamios?:</strong> <span>%s</span></div>
                        </div>
                        
                        <div style="display: flex; justify-content: space-between; width: 100%%; margin-bottom: 10px;">
                            <div><strong>Grua?:</strong> <span>%s</span></div>
                            <div><strong>Equipos móviles?:</strong> <span>%s</span></div>
                            <div><strong>Trabajo de Buceo?:</strong> <span>%s</span></div>
                        </div>
                    
                        <h3>Lista Trabajadores</h3>
                    
                        <table border="1" cellpadding="5" cellspacing="0" style="border-collapse:collapse;">
                            <thead>
                                <tr>
                                    <th>DNI</th>
                                    <th>Nombre Completo</th>
                                    <th>Status SSO</th>
                                    <th>Motivo SSO</th>
                                    <th>N° Docs</th>
                                    <th>Alto Riesgo</th>
                                    <th>Prevenc</th>
                                </tr>
                            </thead>
                            <tbody>
                                %s
                            </tbody>
                        </table>
                        
                        <p>
                            <b>Status SSO:</b> %s<br>
                            <b>Motivo SSO:</b> %s<br>
                            <b>Status Calidad Ambiental:</b> %s<br>
                            <b>Motivo Calidad Ambiental:</b> %s<br>
                        </p>
                        <p>
                        Puede revisar los datos del servicio accediendo en la url <a href="%s">%s</a>
                        </p>
                    
                        <br>
                        <p>
                            Atte.<br>
                            Administración de Procesos
                        </p>
                    </body>
                    </html>
                    """
    ),
    ;

    public final String code;
    public final String recipient;
    public final String subjectTemplate;
    public final String bodyTemplate;
}
