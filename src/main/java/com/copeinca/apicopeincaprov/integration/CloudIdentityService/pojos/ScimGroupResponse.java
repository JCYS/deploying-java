package com.copeinca.apicopeincaprov.integration.CloudIdentityService.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScimGroupResponse {

    private List<String> schemas;

    private String displayName;

    private String id;

    private Meta meta;

    private List<Member> members;

    /**
     * Esta propiedad en el JSON es una clave "rara" (con : y .), por eso se mapea con @JsonProperty exacto.
     */
    @JsonProperty("urn:sap:cloud:scim:schemas:extension:custom:2.0:Group")
    private SapCustomGroupExtension sapCustomGroupExtension;

    // ----------------- inner classes -----------------

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Meta {
        private String created;
        private String lastModified;
        private String location;
        private String resourceType;
        private String version;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Member {
        private String value;
        private String type;

        /**
         * En JSON viene como "$ref"
         */
        @JsonProperty("$ref")
        private String ref;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SapCustomGroupExtension {
        private String additionalId;
        private String name;
        private String description;
    }
}

