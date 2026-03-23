
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/global/dtos/response/MessageViewModel.java.p.vm
 */
package com.copeinca.apicopeincaprov.global.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageViewModel implements Serializable {

    @JsonProperty(value = "type")
    String type;
    @JsonProperty(value = "title")
    String title;
    @JsonProperty(value = "description")
    String description;
    @JsonProperty(value = "subtitle")
    String subtitle;
    @JsonProperty(value = "group")
    String group;

    public static void messageSuccess(String message, List<MessageViewModel> list) {

        list.add(buildMessage(TypeMessageViewEnum.SUCCESS, message));

    }

    public static void messageSuccess(String message, String description, List<MessageViewModel> list) {

        list.add(buildMessage(TypeMessageViewEnum.SUCCESS, message, description));

    }

    public static void messageError(String message, List<MessageViewModel> list) {

        list.add(buildMessage(TypeMessageViewEnum.ERROR, message));

    }

    public static void messageError(String message, String description, List<MessageViewModel> list) {

        list.add(buildMessage(TypeMessageViewEnum.ERROR, message, description));

    }

    public static void messageWarning(String message, String description, List<MessageViewModel> list) {

        list.add(buildMessage(TypeMessageViewEnum.WARNING, message, description));

    }

    public static void messageWarning(String message, List<MessageViewModel> list) {

        list.add(buildMessage(TypeMessageViewEnum.WARNING, message));

    }

    public static void messageInformation(String message, List<MessageViewModel> list) {

        list.add(buildMessage(TypeMessageViewEnum.INFORMATION, message));

    }

    public static void messageInformation(String message, String description, List<MessageViewModel> list) {

        list.add(buildMessage(TypeMessageViewEnum.INFORMATION, message, description));

    }

    public static MessageViewModel buildMessage(TypeMessageViewEnum typeMessageViewEnum, String message) {

        return MessageViewModel.builder().type(typeMessageViewEnum.getType()).group(typeMessageViewEnum.getType()).title(message).subtitle("Ampliar mensaje.")
                .description(message).build();

    }

    public static MessageViewModel buildMessage(TypeMessageViewEnum typeMessageViewEnum, String message, String description) {

        return MessageViewModel.builder().type(typeMessageViewEnum.getType()).group(typeMessageViewEnum.getType()).title(message).subtitle(description)
                .description(description).build();

    }

    public static MessageViewModel buildMessageAttachment(TypeMessageViewEnum typeMessageViewEnum, String group, String title, String description,
            String subtitle) {

        return MessageViewModel.builder().type(typeMessageViewEnum.getType()).group(group).title(title).subtitle(subtitle).description(description).build();

    }

}
