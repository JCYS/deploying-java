
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/global/dtos/AttachmentData.java.p.vm
 */
package com.copeinca.apicopeincaprov.global.dtos;

import jakarta.servlet.http.HttpServletResponse;
import lombok.*;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentData implements Serializable {

    private String name;
    private byte[] content;
    private String extension;
    private String contentType;

    public void setDataDownload(HttpServletResponse response) {

        response.setContentType(contentType);
        response.setContentLength(content.length);
        response.setHeader("Expires", "0");
        response.setHeader("Content-Disposition", "attachment; filename=" + name);

    }

    public void writeToResponse(HttpServletResponse response) throws IOException {

        response.setContentType(contentType);
        response.setContentLength(content.length);
        response.setHeader("Expires", "0");
        response.setHeader("Content-Disposition", "attachment; filename=" + name);

        OutputStream outputStream = response.getOutputStream();
        outputStream.write(content);
        outputStream.flush();

    }

}
