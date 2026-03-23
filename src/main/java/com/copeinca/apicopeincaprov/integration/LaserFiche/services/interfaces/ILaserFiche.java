package com.copeinca.apicopeincaprov.integration.LaserFiche.services.interfaces;

import com.copeinca.apicopeincaprov.integration.LaserFiche.dtos.FileUploadDto;
import com.copeinca.apicopeincaprov.integration.LaserFiche.pojos.deleteFiles.body.EliminarArchivoECMMasivoEnc;
import com.copeinca.apicopeincaprov.integration.LaserFiche.pojos.deleteFiles.body.IdArchivosECMEnc;
import com.copeinca.apicopeincaprov.integration.LaserFiche.pojos.deleteFiles.body.SoapEnvelopeR;
import com.copeinca.apicopeincaprov.integration.LaserFiche.pojos.deleteFiles.response.EliminarArchivoECMMasivoEncResult;
import com.copeinca.apicopeincaprov.integration.LaserFiche.pojos.deleteFiles.response.SoapEliminarArchivoResponse;
import com.copeinca.apicopeincaprov.integration.LaserFiche.pojos.uploadFiles.response.SubirArchivoECMSoapResponse;

import java.util.List;

public interface ILaserFiche {
    String generateXml() throws Exception;
    SoapEliminarArchivoResponse deleteFilesLaserFiche(IdArchivosECMEnc aIds) throws Exception;
    SubirArchivoECMSoapResponse uploadFilesMasive(List<FileUploadDto> aFiles) throws Exception;
}
