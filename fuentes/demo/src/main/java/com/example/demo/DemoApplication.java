package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class DemoApplication {

	@Autowired
	private MailSenderUtility mailSenderUtility;
	
	private static Logger log = LoggerFactory.getLogger(MailSenderUtility.class);
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	
	@Bean
    public void sendMail() {
    	System.out.println("\n\n\n\n***************************"+mailSenderUtility+"\n\n\n");
    	
    	try {
    		String subject = "Solicitud recuperar contraseña";
    		String message = "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\"><head><!--[if !mso]><!-- --><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><!--<![endif]--><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"><link href=\"https://fonts.googleapis.com/css2?family=Red+Hat+Display:ital,wght@0,700;1,400&display=swap\" rel=\"stylesheet\"><link href=\"https://fonts.googleapis.com/css2?family=Red+Hat+Display:ital,wght@0,400;0,500;0,700;1,400&display=swap\" rel=\"stylesheet\"><style type=\"text/css\">#outlook a {padding: 0;}.ReadMsgBody {width: 100%;}.ExternalClass {width: 100%;}.ExternalClass * {line-height: 100%;}.columna {border-top: solid 1px #E3E3E3;padding-top: 10px;padding-bottom: 10px;width: 50%;float: left;text-align: left;align-content: center;}.columnac {border-top: solid 1px #E3E3E3;padding-top: 10px;padding-bottom: 10px;width: 50%;float: left;text-align: left;align-content: center;}.titulotexto {color: #FF4713;}body {margin: 0;padding: 0;-webkit-text-size-adjust: 100%;-ms-text-size-adjust: 100%;}table,td {border-collapse: collapse;mso-table-lspace: 0pt;mso-table-rspace: 0pt;}img {border: 0;height: auto;line-height: 100%;outline: none;text-decoration: none;-ms-interpolation-mode: bicubic;}p {display: block;margin: 13px 0;}h1 {font-size: 20px;color: #c49259;}</style><!--[if !mso]><!--><style type=\"text/css\">@media only screen and (max-width:480px) {@-ms-viewport {width: 320px;}@viewport {width: 320px;}}</style><!--<![endif]--><style type=\"text/css\">@media (max-width: 500px) {.columna {width: auto;float: none;text-align: center;}.columnac {width: auto;float: none;text-align: center;}</style><style type=\"text/css\">@media only screen and (min-width:480px) {.mj-column-px-600 {width: 600px !important;max-width: 600px;}.mj-column-per-100 {width: 100% !important;max-width: 100%;}.mj-column-per-20 {width: 20% !important;max-width: 20%;}.mj-column-per-70 {width: 70% !important;max-width: 70%;}}</style><style type=\"text/css\">@media only screen and (max-width:480px) {table.full-width-mobile {width: 100% !important;}td.full-width-mobile {width: auto !important;}}</style></head><body style=\"background-color:#f1f2f3;\"><div style=\"background-color:#f1f2f3;\" ><div  style=\"Margin:0px auto;max-width:600px;\"><table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"width:100%;\"><tbody><tr><td style=\"direction:ltr;font-size:0px;padding:40px 0px 0px 0px;text-align:center;vertical-align:top;\"><div class=\"mj-column-px-600 outlook-group-fix\" style=\"font-size:13px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\"><tbody><tr><td  style=\"background-color:#ffffff;vertical-align:top;padding:0px;\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"\" width=\"100%\" ><tr><td  align=\"center\" style=\"font-size:0px;padding:0px;word-break:break-word;\" ></td></tr></table></td></tr></tbody></table></div><div class=\"mj-column-per-100 outlook-group-fix\" style=\"font-size:13px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"background-color:#ffffff;vertical-align:top;\" width=\"100%\"><tr><td align=\"center\" style=\"font-size:0px;padding:10px 25px;padding-top:60px;word-break:break-word;\" ><div style=\"font-family: 'Red Hat Display', sans-serif;font-size:30px;font-weight:900;line-height:1;text-align:center;color:#FF4713;\">" +
    	            "Cambio de contraseña" +
    	            "</div></td></tr><tr><td align=\"center\" style=\"font-size:0px;padding:10px 10px 40px 10px;\"><div style=\"font-family: 'Red Hat Display', sans-serif;font-size:24px;font-weight:400;line-height:32px;text-align:center;color:#4A4A4A;\">" +
    	            "Se ha realizado una solicitud de cambio de contraseña, si ha sido usted, por favor dar click en el siguiente enlace: " +
    	            "</div></td></tr></table></div><div class=\"mj-column-per-100 outlook-group-fix\" style=\"font-size:13px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"background-color:#ffffff;vertical-align:top;\" width=\"100%\"><tr><td align=\"center\" style=\"font-size:0px;padding-bottom:10px;word-break:break-word;\"><div style=\"font-family: 'Red Hat Display', sans-serif;font-size:13px;line-height:1;text-align:center;color:#000000;\"><a href=\"" +
    	            "https://www.vortexbird.com" +
    	            "\" style=\"text-decoration: none;padding: 10px 60px 10px 60px;/*  */font-weight: 500;font-size: 20px;color: #ffffff;background-color: #FF4713;font-family: 'Red Hat Display', sans-serif;border-radius: 20px;\">" +
    	            "Recuperar clave" +
    	            "</a></div></td></tr><tr><td align=\"center\" style=\"font-size:0px;padding:10px 10px 40px 10px;\"><div style=\"font-family: 'Red Hat Display', sans-serif;font-size:24px;font-weight:400;line-height:32px;text-align:center;color:#4A4A4A;\">Se enviará una clave de acceso al correo</div></td></tr> </table></div></td></tr></tbody></table></div><div  style=\"background:#ffffff;background-color:#ffffff;Margin:0px auto;max-width:600px;\"><table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"background:#ffffff;background-color:#ffffff;width:100%;\"><tbody><tr><td style=\"border-bottom:3px solid #f1f2f3;border-top:3px solid #f1f2f3;direction:ltr;font-size:0px;text-align:center;vertical-align:top;\"><div class=\"mj-column-per-20 outlook-group-fix\" style=\"font-size:13px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\"><tableborder=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\"><tbody><tr><td  style=\"vertical-align:top;padding:0px 0px 0px 0px;\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"\" width=\"100%\"><tr><td align=\"center\" style=\"font-size:0px;word-break:break-word;\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"border-collapse:collapse;border-spacing:0px;\"><tbody><tr><td  style=\"width:600px;\"><tr><td align=\"center\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\"><div style=\"font-family:Montserrat, Helvetica, Arial, sans-serif;font-size:12px;line-height:1;text-align:center;color:grey;\">" +
    	            "Este mensaje es solo informativo no responder a este correo." +
    	            "</div></td></tr></td></tr></tbody></table></td></tr></table></td></tr></tbody></table></div><div class=\"mj-column-per-70 outlook-group-fix\" style=\"font-size:13px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" width=\"100%\"><tbody><tr><td  style=\"vertical-align:top;\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"\" width=\"100%\"></table></td></tr></tbody></table></div></td></tr></tbody></table></div><div class=\"mj-column-per-100 outlook-group-fix\" style=\"font-size:13px;text-align:left;direction:ltr;display:inline-block;vertical-align:top;width:100%;\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"vertical-align:top;\" width=\"100%\"><tr><td align=\"center\" style=\"font-size:0px;padding:10px 25px;word-break:break-word;\" ><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"border-collapse:collapse;border-spacing:0px;\"></table></td></tr></table></div></div><div  style=\"background:#FFFFFF;background-color:#FFFFFF;Margin:0px auto;max-width:600px;\"><table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" role=\"presentation\" style=\"background:#FFFFFF;background-color:#FFFFFF;width:100%;\"></table></div></body></html>";
    		String[] emails = {"jbejarano@vortexbird.com"};
    		mailSenderUtility.sendMail(emails, subject, message, null, true);
		} catch (Exception e) {
			log.error("Error", e);
		}
    	
    }

}
