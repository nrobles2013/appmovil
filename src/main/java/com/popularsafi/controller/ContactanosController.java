package com.popularsafi.controller;
import com.popularsafi.security.JwtRequestContactanos;
import com.popularsafi.security.JwtResponseRuta;
import com.popularsafi.service.IConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin("*")
@RequestMapping("/contactanos")
public class ContactanosController {

    @Value("${usuariocorreo}")
    private String usuario;

    @Value("${passwordcorreo}")
    private String password;

    @Value("${correooculto}")
    private String oculto;

    @Autowired
    private IConsultaService iConsultaService;


    @PostMapping("/enviocorreo")
    public ResponseEntity<JwtResponseRuta> enviarCorreo(@RequestBody JwtRequestContactanos jwtcontacta) throws Exception {
        String mensaje="" ;
        System.out.println(jwtcontacta.getAsunto());
        if (jwtcontacta.getCorreo()!=null){

        String body=  "<html><body>  " + jwtcontacta.getMensaje() +
                "<p><p><p><p><img src='https://ci3.googleusercontent.com/mail-sig/AIorK4zp0xnuCxJiBiWM8IhxrixsCRYNscTIa2gACv65i5giCUA-Kq0YJFc9hyfFktNgdyFY22gifu0'  width='450' height='160'/>  </body></html>";
        String subject="Participe " + jwtcontacta.getUsuario() + ": " +    jwtcontacta.getAsunto() ;
        String from="";
        String pass="";
        String[] file =new String[0];
        String correo=getOculto();
        String[] to= correo.trim().split(",");
        iConsultaService.sendFromGMail(getUsuario(), getPassword(), to, subject, body, file);
        mensaje="se envio correo";
        }else{
            mensaje="no existe el correo del participe";
        }
         return new  ResponseEntity<JwtResponseRuta>(new JwtResponseRuta(mensaje), HttpStatus.OK) ;
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
}
