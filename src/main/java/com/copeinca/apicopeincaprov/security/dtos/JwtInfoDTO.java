package com.copeinca.apicopeincaprov.security.dtos;

import com.copeinca.apicopeincaprov.modules.core.models.dto.SvProveedorDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvSedeDTO;
import com.copeinca.apicopeincaprov.modules.core.models.dto.SvUsuarioRolSedeDTO;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtInfoDTO {
    private String sub;
    private String userName;
    private String email;
    private String familyName;
    private List<String> roleCollections;
    private List<String> samlGroups;
    private SvProveedorDTO proveedor;

    private List<SvUsuarioRolSedeDTO> listSvUsuarioRolSedeDto;

    private List<SvSedeDTO> listSedes;
}
