package com.copeinca.apicopeincaprov.integration.LaserFiche.pojos.deleteFiles.body;

import jakarta.xml.bind.annotation.*;
import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
public class IdArchivosECMEnc {

    @XmlElement(
            name = "string",
            namespace = "http://tempuri.org/"
    )
    private List<String> string;

    public List<String> getString() {
        return string;
    }

    public void setString(List<String> string) {
        this.string = string;
    }
}
