package Principal;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Main {
   public static void main(String[] args) {
      try {
         // Carga del fichero que tiene los certificados de los servidores en
         // los que confiamos.
         InputStream fileCertificadosConfianza = new FileInputStream(new File(
               "./almacen1"));
         KeyStore ksCertificadosConfianza = KeyStore.getInstance(KeyStore
               .getDefaultType());
         ksCertificadosConfianza.load(fileCertificadosConfianza,
               "123456".toCharArray());
         fileCertificadosConfianza.close();

         // Ponemos el contenido en nuestro manager de certificados de
         // confianza.
         TrustManagerFactory tmf = TrustManagerFactory
               .getInstance(TrustManagerFactory.getDefaultAlgorithm());
         tmf.init(ksCertificadosConfianza);

         // Creamos un contexto SSL con nuestro manager de certificados en los
         // que confiamos.
         SSLContext context = SSLContext.getInstance("TLS");
         context.init(null, tmf.getTrustManagers(), null);
         SSLSocketFactory sslSocketFactory = context.getSocketFactory();

         // Abrimos la conexión y le pasamos nuestro contexto SSL
         URL url = new URL("https://servayto.madrid.es/MTPAR_RSINFO/restInfoParking/listParking?language=ES");
         URLConnection conexion = url.openConnection();
         ((HttpsURLConnection) conexion).setSSLSocketFactory(sslSocketFactory);

         // Ya podemos conectar y leer
         conexion.connect();
         InputStream is = conexion.getInputStream();
         BufferedReader br = new BufferedReader(new InputStreamReader(is));
         String cadena = new String();;
  		 String subcadena;
		 char[] buffer = new char[1000];
	     int leido;
	     while ((leido = br.read(buffer)) > 0) 
	     {
	        cadena=cadena+new String(buffer, 0, leido);
	     }
	     
	     System.out.println(cadena);
	     cadena = "{\"aparcamientos\":"+cadena+"}";
	     
	     JsonObject jsonObject = JsonParser.parseString(cadena).getAsJsonObject();
	     JsonArray vector = jsonObject.get("aparcamientos").getAsJsonArray();
	     for (JsonElement elemento : vector)
	     {
	    	String id=elemento.getAsJsonObject().get("adress").getAsString();
	        //String nombre=elemento.getAsJsonObject().get("strSport").getAsString();
	        //String formato=elemento.getAsJsonObject().get("strFormat").getAsString();

	        //System.out.println("ID="+id+" Nombre="+nombre+" Formato="+formato);
	    	System.out.println("ID="+id);
	     }
               
      } catch (MalformedURLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (KeyStoreException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (NoSuchAlgorithmException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (CertificateException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      } catch (KeyManagementException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      }

   }
}



