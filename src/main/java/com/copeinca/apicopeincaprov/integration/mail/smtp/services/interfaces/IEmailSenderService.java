package com.copeinca.apicopeincaprov.integration.mail.smtp.services.interfaces;

import com.copeinca.apicopeincaprov.integration.mail.smtp.models.custom.NotifyInput;
import com.copeinca.apicopeincaprov.integration.mail.smtp.models.custom.SendEmailResponse;

public interface IEmailSenderService {

    SendEmailResponse send( NotifyInput input );

}