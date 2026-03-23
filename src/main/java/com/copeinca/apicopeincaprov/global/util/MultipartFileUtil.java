
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/global/util/MultipartFileUtil.java.p.vm
 */
package com.copeinca.apicopeincaprov.global.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Slf4j
public class MultipartFileUtil {

    public static InputStream getInputStream(MultipartFile file) {

        try {

            return file.getInputStream();

        } catch (Exception ex) {

            log.error("Ocurrió un problema al obtener el contenido del archivo. Detalle técnico: {}", ex.getMessage());

            throw new RuntimeException("Ocurrió un problema al obtener el contenido del archivo. Detalle técnico: " + ex.getMessage());

        }

    }

    public static byte[] getBytes(MultipartFile file) {

        try {

            return file.getBytes();

        } catch (Exception ex) {

            log.error("Ocurrió un problema al obtener el binario del archivo. Detalle técnico: {}", ex.getMessage());

            throw new RuntimeException("Ocurrió un problema al obtener el binario del archivo. Detalle técnico " + ex.getMessage());

        }

    }

}
