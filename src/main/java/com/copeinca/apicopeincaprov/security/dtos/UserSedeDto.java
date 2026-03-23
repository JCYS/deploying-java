package com.copeinca.apicopeincaprov.security.dtos;

import com.copeinca.apicopeincaprov.modules.core.models.dto.SvRolDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSedeDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvUsuarioDTO;
import lombok.Data;

import java.util.List;

@Data
public class UserSedeDto {
    private String email;
    private SvUsuarioDTO usuarioDTO;
    private List<SvSedeDTO> sedes;
    private List<SvRolDTO> roles;
}
