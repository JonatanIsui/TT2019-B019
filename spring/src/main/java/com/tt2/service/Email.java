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

    //Pasamos por parametro: destinatario, asunto y el mensaje
    @Override
    public boolean sendEmail(Usuario usuario) {
    	boolean res = false;
    	String subject = "Solicitud recuperacion de contraseña";
    	String urlBase = "Si deseas recuperar sigue el link de lo contrario ignora este correo http://localhost:3000/CambioPassword/";
    	try {
    		SimpleMailMessage email = new SimpleMailMessage();
    		email.setTo(usuario.getCorreo());
    		email.setSubject(subject);
    		//Cifrar email
    		String encoded = new String(Base64.getEncoder().encode(usuario.getCorreo().getBytes()));
    		email.setText(urlBase+encoded);
    		mailSender.send(email);
    		res = true;
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
        return res;
    }
    
    public boolean aceptarProveedor(Usuario usuario) {
    	boolean res = false;
    	String subject = "Bienvenido";
    	String urlBase = "A traves de este correo se le informa que su solicitud ha sido aprovada, por favor ingrese sesion en http://localhost:3000";
    	try {
    		SimpleMailMessage email = new SimpleMailMessage();
    		email.setTo(usuario.getCorreo());
    		email.setSubject(subject);
    		//Cifrar email
    		String encoded = new String(Base64.getEncoder().encode(usuario.getCorreo().getBytes()));
    		email.setText(urlBase+encoded);
    		mailSender.send(email);
    		res = true;
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
        return res;
    }
}
