package com.tt2.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;
import com.tt2.service.interfaz.ImagenesInterfaz;


@Service("imagenesBean") 
public class Imagenes implements ImagenesInterfaz{
	private String url = System.getProperty("user.dir")+"\\Imagenes";
	@Override
	public String toFile(String imagen,String empresa){
		String tipo = imagen.split(";")[0].split("/")[1];
		imagen = imagen.split (",") [1];
		File file = new File(url,empresa+"."+tipo);
		byte [] data = Base64.decodeBase64 (imagen);
		try { 
            OutputStream os = new FileOutputStream(file); 
            os.write(data); 
            os.close(); 
        } 
        catch (Exception e) { 
            System.out.println("Exception: " + e); 
        } 
		return  file.getAbsolutePath();
	}
	
	@Override
	public String toString(String imagen) {
		String encodedfile = null;
		FileInputStream fileInputStreamReader;
		try {
			File file = new File(imagen);
			fileInputStreamReader = new FileInputStream(file);
            byte[] bytes = new byte[(int)file.length()];
            fileInputStreamReader.read(bytes);
            encodedfile = new String(Base64.encodeBase64(bytes), "UTF-8");
		}catch(Exception e) {
			System.out.println("Exception: "+e);
		}
		return encodedfile;
	}
}
