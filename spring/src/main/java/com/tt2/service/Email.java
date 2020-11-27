package com.tt2.service;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.tt2.entity.Usuario;
import com.tt2.service.interfaz.EmailInterfaz;

@Service("emailDao")
public class Email implements EmailInterfaz{
    //Importante hacer la inyección de dependencia de JavaMailSender:
    @Autowired
    private JavaMailSender mailSender;
    
    private String urlBase = "Si deseas recuperar sigue el link de lo contrario ignora este correo http://localhost:3000/CambioPassword/";
    private String subject = "Solicitud recuperacion de contraseña";
    //Pasamos por parametro: destinatario, asunto y el mensaje
    @Override
    public boolean sendEmail(Usuario usuario) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(usuario.getCorreo());
        email.setSubject(subject);
        //Cifrar email
        String encoded = new String(Base64.getEncoder().encode(usuario.getCorreo().getBytes()));
        email.setText(urlBase+encoded);
        mailSender.send(email);
        
        return true;
    }
}
