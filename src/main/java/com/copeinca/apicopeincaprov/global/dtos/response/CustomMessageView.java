
/*
 * Autor: Anthony Ramos
 * Email: anthonyramosdev@gmail.com
 * Template pack-angular:src/main/java/global/dtos/response/CustomMessageView.java.p.vm
 */
package com.copeinca.apicopeincaprov.global.dtos.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sap.cloud.sdk.datamodel.odata.client.exception.ODataServiceError;
import com.sap.cloud.sdk.datamodel.odata.client.exception.ODataServiceErrorDetails;
import com.sap.cloud.sdk.datamodel.odata.client.exception.ODataServiceErrorException;
import lombok.*;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomMessageView<T> implements Serializable {

    @JsonProperty(value = "messages")
    private List<MessageViewModel> messages;
    @JsonProperty(value = "response")
    private T response;
    @JsonProperty(value = "finished")
    private Boolean finished;
    @JsonProperty(value = "successfully")
    private Boolean successfully;

    public static <T> CustomMessageView<T> defaultInstance() {

        return CustomMessageView.<T> builder().messages(new ArrayList<>()).finished(false).successfully(false).response(null).build();

    }

    public static <T> CustomMessageView<T> defaultInstanceSuccessful() {

        return CustomMessageView.<T> builder().messages(new ArrayList<>()).finished(true).successfully(true).response(null).build();

    }

    public static CustomMessageView<?> defaultInstanceWithErros(Exception ex) {

        List<MessageViewModel> messages = new ArrayList<>();

        String messageError = "Inténtelo nuevamente, si el problema persiste comuníquese con el administrador de Sistema.";

        if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException manve = (MethodArgumentNotValidException) ex;

            for (FieldError fieldError : manve.getBindingResult().getFieldErrors()) {
                String message = fieldError.getDefaultMessage(); // Mensaje i18n
                messages.add(MessageViewModel.builder().type(TypeMessageViewEnum.ERROR.getType()).title("Validación fallida").description(message)
                        .subtitle("Campo: " + fieldError.getField()).group("General").build());
            }
        } else {
            messages.add(MessageViewModel.builder().type(TypeMessageViewEnum.ERROR.getType()).title("Surgió un inconveniente en la ejecución del proceso.")
                    .description("Error interno: " + ex.getLocalizedMessage() + " \n" + messageError).subtitle(ex.getClass().getSimpleName()).group("General")
                    .build());
        }

        return CustomMessageView.builder().successfully(false).messages(messages).finished(false).response(null).build();
    }

    public static CustomMessageView<?> defaultInstanceWithOdataErros(ODataServiceErrorException ex, String entity) {

        CustomMessageView response = CustomMessageView.builder().successfully(false).messages(new ArrayList<>()).finished(false).response(null).build();

        ODataServiceError error = ex.getOdataError();

        MessageViewModel.messageError(error.getODataCode(), "[" + entity + "] - [MAIN] " + error.getODataMessage(), response.getMessages());

        for (ODataServiceErrorDetails errorDetail : error.getDetails()) {

            MessageViewModel.messageError(errorDetail.getODataCode(), "[" + entity + "]" + " - [DETAIL] " + errorDetail.getODataMessage(),
                    response.getMessages());

        }

        return response;

    }

    public CustomMessageView<T> finish() {

        this.finished = true;
        return this;

    }

    public CustomMessageView<T> successful() {

        this.successfully = true;
        return this;

    }

    public static <T> CustomMessageView<T> success(T response) {
        return CustomMessageView.<T> builder().messages(new ArrayList<>()) // sin mensajes de error
                .response(response) // la data de respuesta
                .finished(true) // proceso finalizado
                .successfully(true) // operación exitosa
                .build();
    }

}