package com.tt2.service.interfaz;

import com.tt2.entity.Usuario;

public interface UsuarioBeanInterfaz {
	Usuario iniciarSesion(String correo,String password);
	boolean borrarUsuario(String correo, String password);
	boolean recuperarPassword(String correo);
	Usuario cambioPassword(Usuario usuario);
}
