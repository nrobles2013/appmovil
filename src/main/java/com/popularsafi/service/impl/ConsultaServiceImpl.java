package com.popularsafi.service.impl;

import com.popularsafi.model.TipoDocumento;
import com.popularsafi.repo.ITipoDocumentoRepo;
import com.popularsafi.service.IConsultaService;
import jakarta.activation.DataHandler;
import jakarta.activation.FileDataSource;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

@Service
public class ConsultaServiceImpl implements IConsultaService {
    @Autowired
    ITipoDocumentoRepo tipoDocumentoRepo;


    @Override
    public TipoDocumento save(TipoDocumento tipoDocumento) {
        return null;
    }

    @Override
    public TipoDocumento update(TipoDocumento tipoDocumento, Integer integer) {
        return null;
    }

    @Override
    public List<TipoDocumento> findAll() {
        return null;
    }

    @Override
    public TipoDocumento findById(Integer integer) {
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }


    public void sendFromGMail(String from,String pass,String[] to, String subject, String body, String[] file1) throws IOException
    {
        Properties props = System.getProperties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", 587);
        props.put("mail.smtp.password",pass.trim());
        props.put("mail.smtp.user", from.trim());
        props.put("mail.smtp.auth", "true");
        System.out.println("por aca paso");
        Session session = Session.getDefaultInstance(props);


        try
        {
            MimeMessage message = new MimeMessage(session);
            //   message.setFrom(new InternetAddress((String) props.get("mail.smtp.mail.sender")));
            message.setSubject(subject);
            int cntarch=0;
            for (int r = 0; r < file1.length; r++)
            {
                if (file1[r]!=null){
                    cntarch=cntarch+1;
                }

            }
            System.out.println("por aca paso2");
            BodyPart[] adjunto = new BodyPart[cntarch];
            for (int j = 0; j < cntarch; j++)
            {
                String[] rutaArchivo = file1[j].split("/");
                int nombre = rutaArchivo.length - 1;
                adjunto[j] = new MimeBodyPart();
                adjunto[j].setDataHandler(new DataHandler(new FileDataSource(file1[j])));
                File carpetaDestino = new File(rutaArchivo[nombre]);
                adjunto[j].setFileName(carpetaDestino.getName());
            }
            System.out.println("por aca paso3");
            BodyPart texto = new MimeBodyPart();

            texto.setContent( body,"text/html; charset=utf-8");

            MimeMultipart multiParte = new MimeMultipart();
            multiParte.addBodyPart(texto);
            for (BodyPart aux : adjunto) {
                multiParte.addBodyPart(aux);
            }

            System.out.println("por aca paso4");
            message.setContent(multiParte);


            InternetAddress[] toAddress = new InternetAddress[to.length];

            // To get the array of addresses
            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new InternetAddress(to[i]);
                System.out.println(to[i]);
            }

            for( int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);

            }

            System.out.println("por aca paso5");

            Transport t = session.getTransport("smtp");
            t.connect((String) props.get("mail.smtp.user"), (String) props.get("mail.smtp.password"));
            System.out.println("por aca paso6");
            t.sendMessage(message, message.getAllRecipients());
            t.close();
            System.out.println("finalizo");

        }
        catch (AddressException ae) {

        }
        catch (MessagingException me) {

        }
    }


}
