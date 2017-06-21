/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;
import entis.People;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Properties;
import java.util.Random;
import java.util.StringTokenizer;
 
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
/**
 *
 * @author Usuario
 */
public class Utils {
  
  
          
  public void enviarCorreo(People destinatario, int opcion) {
  // El correo gmail de envío
        String correoEnvia = "lorenaportilloaguilar@gmail.com"; 
        String claveCorreo = "lamfaabp";                       
        int reenvio = 1;                                      

        // La configuración para enviar correo
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.user", correoEnvia);
        properties.put("mail.password", claveCorreo);

        // Obtener la sesion
        Session session = Session.getInstance(properties, null);

        try {
         // Crear el cuerpo del mensaje
         MimeMessage mimeMessage = new MimeMessage(session);

         // Agregar quien envía el correo
         mimeMessage.setFrom(new InternetAddress(correoEnvia, "Skills Finder"));

         // Los destinatarios
         // InternetAddress[] toAddress = InternetAddress.parse(to, false);
         InternetAddress[] internetAddresses= null;
          if (reenvio==0){
              internetAddresses = InternetAddress.parse(destinatario.getEmail(), false);
          }
          else{
             internetAddresses = InternetAddress.parse(correoEnvia, false);
          }

         // Agregar los destinatarios al mensaje
         mimeMessage.setRecipients(Message.RecipientType.TO,
           internetAddresses);

         // Agregar el asunto al correo
         if (opcion ==1){
              mimeMessage.setSubject("Skills Finder. New User");
         }
         else    mimeMessage.setSubject("Skills Finder. Recovery Password");


         // Creo la parte del mensaje
         MimeBodyPart text = new MimeBodyPart( ) ; 
                  text.setContent("<html>" 
                          + "<h1><font color='ff0000'>Welcome Skills Finder</font></h1><br>" 
                          + "<p><h3>New User           :" + destinatario.getEmail() + "</h3></p> "
                          + "<p><h3>Generated Password :" + destinatario.getPasword() + "</h3></p> "
                          + "</html>", "text/html" ); 


         // Crear el multipart para agregar la parte del mensaje anterior
         Multipart multipart = new MimeMultipart();
         multipart.addBodyPart(text);

         // Agregar el multipart al cuerpo del mensaje
         mimeMessage.setContent(multipart);

         // Enviar el mensaje
         Transport transport = session.getTransport("smtp");
         transport.connect(correoEnvia, claveCorreo);
         transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
         transport.close();

        } catch (Exception ex) {
         ex.printStackTrace();
        }
        System.out.println("Correo enviado");
 }

  public static String  getCadenaAlfanumAleatoria (int longitud){
    String cadenaAleatoria = "";
    long milis = new java.util.GregorianCalendar().getTimeInMillis();
    Random r = new Random(milis);
    int i = 0;
    while ( i < longitud){
        char c = (char)r.nextInt(255);
        if ( (c >= '0' && c <='9') || (c >='A' && c <='Z') ){
            cadenaAleatoria += c;
            i ++;
            }
     }
    return cadenaAleatoria;
  }
  public static String capitalize(String sString){
       sString = sString.toLowerCase();
       sString = Character.toString(sString.charAt(0)).toUpperCase()+sString.substring(1);
       return sString;
  }
}