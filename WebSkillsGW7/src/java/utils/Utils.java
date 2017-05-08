/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;
import java.util.Properties;
 
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
/**
 *
 * @author Usuario
 */
public class Utils {
    
  public void enviarCorreo(String destinatrio) {
  // El correo gmail de envío
  String correoEnvia = "lorenaportilloaguilar@gmail.com";
  String claveCorreo = "lamfaabp";
 
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
   mimeMessage.setFrom(new InternetAddress(correoEnvia, "Dato Java"));
    
   // Los destinatarios
   InternetAddress[] internetAddresses = {
     new InternetAddress(destinatrio) }; //Aqui el destinatrio
 
   // Agregar los destinatarios al mensaje
   mimeMessage.setRecipients(Message.RecipientType.TO,
     internetAddresses);
 
   // Agregar el asunto al correo
   mimeMessage.setSubject("Dato Java Enviando Correo.");
 
   // Creo la parte del mensaje
   MimeBodyPart text = new MimeBodyPart( ) ; 
            text.setContent("<html>n" 
                    + "<h2><font color='ff0000'>Bienvenido a Garonz</font></h2>n" 
                    + "<p>Usuario Nuevo:</p>n" + destinatrio + " "
                    + "<p>Password temporal : MD5</p>n"
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
 /*
 public static void main(String[] args) {
  Util correoTexto = new Util();
  correoTexto.enviarCorreo();
   
 }*/
}

