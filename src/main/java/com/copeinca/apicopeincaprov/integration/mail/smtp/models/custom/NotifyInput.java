package com.copeinca.apicopeincaprov.integration.mail.smtp.models.custom;

import com.copeinca.apicopeincaprov.global.dtos.AttachmentData;
import com.copeinca.apicopeincaprov.global.util.UtilString;
import com.copeinca.apicopeincaprov.integration.mail.smtp.models.enums.EventNotifyEnum;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotifyInput implements Serializable {

    private String to;
    private String cc;
    private Map<String, Object> data;
    private EventNotifyEnum eventNotifyEnum;
    private List<AttachmentData> attachments;

}