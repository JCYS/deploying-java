package com.copeinca.apicopeincaprov.integration.CloudIdentityService.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ScimUser {

    private List<String> schemas;

    private String id;

    private ScimMeta meta;

    private String userName;

    private ScimUserName name;

    private String displayName;

    private String userType;

    private Boolean active;

    private List<ScimEmail> emails;

    private List<ScimGroupRef> groups;

    @JsonProperty("urn:ietf:params:scim:schemas:extension:sap:2.0:User")
    private SapScimUserExtension sapExtension;
}
