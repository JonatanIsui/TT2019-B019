package com.tt2.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;
import com.tt2.service.interfaz.ArchivoInterfaz;
@Service("archivoBean")
public class Archivo implements ArchivoInterfaz{
	private String url = System.getProperty("user.dir")+"\\Archivos";
	@Override
	public String toFile(String material,String empresa) {
		String tipo = "xlsx";
		String archivo = material.split(",")[1];
		File file = new File(url,empresa+"."+tipo);
		byte [] data = Base64.decodeBase64(archivo);
		try {
			OutputStream os = new FileOutputStream(file);
			os.write(data);
			os.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return file.getAbsolutePath();
	}
}
