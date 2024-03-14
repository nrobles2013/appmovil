package com.popularsafi.controller;

import com.popularsafi.dto.UtilDocumento;
import com.popularsafi.model.FileDocumentoRuta;
import com.popularsafi.model.TipoDocumento;
import com.popularsafi.security.JwtRequestEmail;
import com.popularsafi.security.JwtRequestRuta;
import com.popularsafi.security.JwtResponseRuta;
import com.popularsafi.service.IConsultaService;
import com.popularsafi.service.IFileDocumentoService;
import com.popularsafi.service.ITipoDocumentoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
@CrossOrigin("*")
@RequestMapping("/consulta")
public class ConsultaController {

    @Value("${usuariocorreo}")
    private String usuario;

    @Value("${passwordcorreo}")
    private String password;

    @Value("${correooculto}")
    private String oculto;


    @Autowired
    private ITipoDocumentoService iTipoDocumentoService;

    @Autowired
    private IConsultaService iConsultaService;


    @Autowired
    private IFileDocumentoService iFileDocumentoService;

    @PostMapping("/enviocorreo")
    public ResponseEntity<JwtResponseRuta> enviarCorreo(@RequestBody JwtRequestRuta jwtRequestRuta) throws Exception {
        String mensaje="" ;
        System.out.println("tipo"+jwtRequestRuta.getTparticipe());
        System.out.println("ssss"+jwtRequestRuta.getAnio());
        System.out.println("rrr"+jwtRequestRuta.getPeriodo());
        if (jwtRequestRuta.getCorreo()!=null){
        TipoDocumento tdoc=new TipoDocumento();
       tdoc =iTipoDocumentoService.findTipoOneDocum(jwtRequestRuta.getTipodocumento());
        System.out.println(jwtRequestRuta.getRuta()+"  "+ jwtRequestRuta.getTipodocumento());

         String subject="Portal Web Participe (Consulta) - Solicitud de documento " ;
         String lsperiodo="";
         if (jwtRequestRuta.getPeriodo()!=null){
             if (jwtRequestRuta.getPeriodo().equals("01")){
                 lsperiodo = " 1er Periodo ";
             }
             if (jwtRequestRuta.getPeriodo().equals("02")){
                 lsperiodo = " 2do Periodo";
             }
             if (jwtRequestRuta.getPeriodo().equals("03")){
                 lsperiodo = " 3er Periodo";
             }
             if (jwtRequestRuta.getPeriodo().equals("04")){
                 lsperiodo = " 4to Periodo";
             }

         }
            String body=  GeneraPlantilla(subject, "","se envia el documento de " +tdoc.getDescripcion() + " del año " + jwtRequestRuta.getAnio() + " " + lsperiodo);

            String from="";
            String pass="";
            String[] file =new String[6];
            String correo=getOculto()+","+jwtRequestRuta.getCorreo();
            String[] to= correo.trim().split(",");;
            String urlRuta=jwtRequestRuta.getRuta();
            File fDirectorio = new File(urlRuta);
            Boolean lnexiste=false;

            if (fDirectorio.exists()) {
                File[] ficheros = fDirectorio.listFiles();
                String archivo="";
                for (int x=0;x<ficheros.length;x++)
                {
                    archivo=  ficheros[x].toString();
                    if (archivo.contains(jwtRequestRuta.getTparticipe().trim()+"_"+jwtRequestRuta.getCparticipe().trim())){
                        mensaje="se envio el correo, Correctamente";
                        lnexiste=true;
                        file[0]=archivo;
                        System.out.println("paso por acas");
                        iConsultaService.sendFromGMail(getUsuario(), getPassword(), to, subject, body, file);
                    }
                }
                if (lnexiste==false){
                    mensaje="no cuentas con dicho documento";
                }
            }else{
                mensaje="no existe";
            }
        }else{
            mensaje="no existe el correo del participe";
        }
         return new  ResponseEntity<JwtResponseRuta>(new JwtResponseRuta(mensaje), HttpStatus.OK) ;
    }

    @GetMapping("/traedocumentos/{iddoc}/{cpart}")
    public ResponseEntity<List<UtilDocumento>> traedocumentos(@PathVariable("iddoc") String iddoc,@PathVariable("cpart") String cpart) throws Exception {
        FileDocumentoRuta tdoc=new FileDocumentoRuta();
        System.out.println(iddoc);
        tdoc =iFileDocumentoService.findByDocumentoAll(iddoc);
        File fDirectorio=null;
        if (iddoc.equals("0002")) {
             fDirectorio = new File(tdoc.getRuta().trim() + "\\" + cpart);
        }else{
             fDirectorio = new File(tdoc.getRuta().trim() );
        }

        List<UtilDocumento> outilDocumento= new ArrayList<>();
        if (fDirectorio.exists()) {
            File[] ficheros = fDirectorio.listFiles();
            String archivo = "";
            String ruta="";
            for (int x = 0; x < ficheros.length; x++) {
                archivo = ficheros[x].getName();
                UtilDocumento onew=new UtilDocumento();
                onew.setId(x+1);
                if (iddoc.equals("0002")) {
                    onew.setDocumento(archivo.substring(0, archivo.lastIndexOf('.')));
                    ruta = ficheros[x].getCanonicalPath();
                    onew.setRuta(ruta.substring(0,ruta.length()-archivo.length())+archivo);
                    //onew.setRutaenviar(ruta.substring(0,ruta.length()-archivo.length())+cpart+"\\"+archivo);
                    long modi = ficheros[x].lastModified();
                    Date d = new Date(modi);
                    onew.setFecha(d);
                }else{
                    onew.setDocumento(archivo.substring(0, archivo.lastIndexOf('.')));
                    onew.setRuta(ficheros[x].toString());
                    long modi = ficheros[x].lastModified();
                    Date d = new Date(modi);
                    onew.setFecha(d);
                }
                outilDocumento.add(onew);
            }
        }
        return new  ResponseEntity<List<UtilDocumento>>(outilDocumento, HttpStatus.OK) ;
    }




    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOculto() {
        return oculto;
    }

    public void setOculto(String oculto) {
        this.oculto = oculto;
    }

    String GeneraPlantilla(String Asunto, String tituloMensaje, String mensaje) {
        String plantilla = "<table width=\"100%\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" bgcolor=\"#ffffff\"> <tbody> <tr> <td valign=\"top\" align=\"center\"> <font size=\"12px\" face=\"Arial, Helvetica, sans-serif\"></font> <table style=\"border:1px solid #dddddd;background-color:#01355c\" width=\"632\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"\"> <tbody> <tr style=\"height:80px\" valign=\"top\"> <td width=\"25\" bgcolor=\"\"> </td> <td style=\"font-family:Arial,Helvetica,sans-serif;color:#f89223;font-size:28px;text-align:left\" valign=\"middle\"> <br> <b>" + Asunto + "</b> </td> </tr> </tbody> </table> <table style=\"border:1px solid #ddd;border-top:none\" width=\"620\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\"> <tbody> <tr> <td style=\"height:30px;float:left;width:630px\"> <table cellspacing=\"0\" cellpadding=\"0\"> <tbody> <tr>  <td style=\"height:33px\" width=\"25px\"> <a shape=\"rect\" style=\"text-decoration:none\"> <img style=\"text-decoration:none;border:0\" src=\"https://ci5.googleusercontent.com/proxy/gS-2MSVGtlIb7Npx9tEd7fgJ9oIUOwaz7nqoo_x5EZdKbfbvxBnudHgnWjcNEuNgyDiwTR3oRhZVvLcSjp7aprVnhE0XRT4IsOyVMZrgD__IW-OdYuBLrok=s0-d-e1-ft#http://f.i.wix.com/i/11/2076041122/Social_Icon_2013_New_Tweeter.png\" alt=\"\" class=\"CToWUd\"> </a> </td> <td style=\"height:33px\" width=\"25px\"> <a shape=\"rect\"> <img style=\"text-decoration:none;border:0\" src=\"https://ci3.googleusercontent.com/proxy/B2YuRGO92TteUjI4vnLP4OxdW2fT1dikBNud-m_YhP2PCHpCiApk8k7xDAjAzIVn9KBkKBEanRzNop2x7xbUTkmTRnLQBmSxnC9c7tGY8GBtX4iIeJvSQQ=s0-d-e1-ft#http://f.i.wix.com/i/11/2076041122/Social_Icon_2013_New_Google.png\" alt=\"\" class=\"CToWUd\"> </a> </td> <td width=\"25px\"> <a shape=\"rect\"> <img style=\"text-decoration:none;border:0\" src=\"https://ci5.googleusercontent.com/proxy/V3DRjT02YWL7IA_ta9rBMyP_Vdm8NE_DBoQhrbo8-FXwqaQJJY8isgYHm0fmzGlCWxi08WWBztS73ve0ic-2UUbXd_XgCFkLoPJBTiVK1VEdbH1vAM8c2PxcKA=s0-d-e1-ft#http://f.i.wix.com/i/11/2076041122/Social_Icon_2013_New_Pinterest.png\" alt=\"\" class=\"CToWUd\"> </a> </td> </tr> </tbody> </table> </td> </tr> <tr> <td style=\"height:20px;width:630px\"> <table width=\"620\" cellspacing=\"0\" cellpadding=\"5\" border=\"0\"> <tbody> <tr> <td width=\"15\"> </td> <td><p style=\"margin-bottom:13px!important;font-family:Arial,Helvetica,sans-serif;font-size:13px;color:#4c4c4c;text-align:justify\" align=\"justify\">Estimado usuario,</p><p style=\"margin-bottom:13px!important;font-family:Arial,Helvetica,sans-serif;font-size:18px;color:#003366;text-align:justify\" align=\"justify\">" + tituloMensaje + "</p><p style=\"margin-bottom:13px!important;font-family:Arial,Helvetica,sans-serif;font-size:13px;color:#4c4c4c;text-align:justify\" align=\"justify\">" + mensaje + "</p> </td> <td style=\"width:6px\"> </td> </tr> </tbody> </table> <table style=\"margin-top:0px!important;padding:0\" width=\"620\" cellspacing=\"0\" cellpadding=\"5\" border=\"0\" bgcolor=\"#ffffff\"> <tbody> <tr> <td width=\"45\"> </td> </tr> </tbody> </table> <table style=\"border-top:1px solid #ddd;border-bottom:1px solid #ddd;height:60px\" width=\"630\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\" bgcolor=\"#f1f1f1\"> <tbody> <tr> <td> <table style=\"margin-top:0;padding-top:0\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\"> <tbody> <tr> <td width=\"20\" bgcolor=\"#f1f1f1\"> </td> <td style=\"height:50px;width:63px;padding-bottom:10px\" bgcolor=\"#f1f1f1\"> <p style=\"margin:0;font-family:Arial,Helvetica,sans-serif;color:#4c4c4c;text-align:center;font-weight:bold;font-size:11px;width:63px;margin-bottom:0\" align=\"justify\"> <font size=\"1\" face=\"Arial, Helvetica, sans-serif\"> <td width=\"15\" bgcolor=\"#f1f1f1\"> </td> </tr> </tbody> </table> </td> " +
                " <td> <table style=\"margin-left:0px;margin-top:0px\" cellspacing=\"0\" cellpadding=\"0\" border=\"0\"> <tbody> <tr>  <td style=\"height:50px;padding-bottom:10px\" width=\"134\" align=\"left\"> <img src='https://ci3.googleusercontent.com/mail-sig/AIorK4zp0xnuCxJiBiWM8IhxrixsCRYNscTIa2gACv65i5giCUA-Kq0YJFc9hyfFktNgdyFY22gifu0'  width='450' height='160'/>   </td> </tr> </tbody> </table>" +
                " </td> </tr> </tbody> </table> <table width=\"630\" cellspacing=\"0\" cellpadding=\"0\" bgcolor=\"#ffffff\" align=\"center\"> <tbody>  " +
                "<tr> <td style=\"font-family:Arial,Helvetica,sans-serif;font-size:11px;color:#4c4c4c;text-align:center\"> <p style=\"margin-bottom:0;margin:0\"> <font size=\"1\" face=\"Arial, Helvetica, sans-serif\">Si no esta interesado en recibir mas...</font></p></td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table>";
        return plantilla;
    }
}
