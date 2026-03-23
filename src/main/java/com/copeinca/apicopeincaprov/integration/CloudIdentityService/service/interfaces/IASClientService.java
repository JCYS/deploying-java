package com.copeinca.apicopeincaprov.integration.CloudIdentityService.service.interfaces;

import com.copeinca.apicopeincaprov.integration.CloudIdentityService.pojos.ScimGroupResponse;
import com.copeinca.apicopeincaprov.integration.CloudIdentityService.pojos.ScimListResponse;
import com.copeinca.apicopeincaprov.integration.CloudIdentityService.pojos.ScimUser;
import com.copeinca.apicopeincaprov.integration.CloudIdentityService.pojos.ScimUserDTO;
import com.copeinca.apicopeincaprov.security.dtos.UserSedeDto;

import java.io.IOException;
import java.net.URISyntaxException;

public interface IASClientService {
    ScimListResponse<ScimUser> usersByGroupDisplay() throws IOException, URISyntaxException;
    ScimGroupResponse updateUserWithGroup(UserSedeDto userSede) throws IOException, URISyntaxException;
    ScimUserDTO searchUSer(String userId) throws IOException, URISyntaxException;
}
