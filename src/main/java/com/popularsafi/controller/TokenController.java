package com.popularsafi.controller;

import com.google.gson.Gson;
import com.popularsafi.model.Role;
import com.popularsafi.model.Usuario;
import com.popularsafi.security.*;
import com.popularsafi.service.IAuditoriaService;
import com.popularsafi.service.IConsultaService;
import com.popularsafi.service.ISeguridadService;
import com.popularsafi.service.impl.UserServiceImpl;
import com.popularsafi.service.impl.KeyCloakServiceImpl;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping("/tokens")
@RequiredArgsConstructor
public class TokenController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsService userDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    private final KeyCloakServiceImpl keycloakService;

    @Autowired
    private IAuditoriaService iAuditoriaService;
    @Value("${usuariocorreo}")
    private String usuario;

    @Value("${passwordcorreo}")
    private String password;

    @Value("${correooculto}")
    private String oculto;

    @Autowired
    private IConsultaService iConsultaService;

    @Autowired
    private ISeguridadService iSeguridadService;

    @Autowired
    UserServiceImpl usuarioService;

    @PostMapping(value="/user/add")
    public ResponseEntity<Boolean> createUser(@RequestBody Usuario user) throws  Exception{
        System.out.println("por aca paso"+user.getUsername()+user.getPassword());
        boolean rpta =  keycloakService.addUser(user);
        if (rpta) {
            Usuario usuario =
                    new Usuario(user.getNombre(), user.getApellido(), user.getUsername(), user.getEmail(),
                            passwordEncoder.encode(user.getPassword()), user.getTelefono(), user.getCelular(),user.getTpparticipe(), true,0,user.getCparticipe());
            Set<Role> roles = new HashSet<>();
            roles.add(new Role(2, "User"));

            usuario.setRoles(roles);

            System.out.println("paso paso2");

            usuarioService.save(usuario);
        }
        return new ResponseEntity<>(rpta, HttpStatus.OK);
    }


    @PutMapping(value="/user/update")
    public ResponseEntity<Boolean> updateUser(@RequestBody Usuario user) throws  Exception{
        System.out.println("por aca paso"+user.getUsuario_id()+user.getPassword());
        boolean rpta =  keycloakService.updateUser(user);

        if (rpta) {
            Usuario usuario =
                    new Usuario( user.getUsuario_id(),  passwordEncoder.encode(user.getPassword()));
            Set<Role> roles = new HashSet<>();
            roles.add(new Role(2, "User"));

            usuario.setRoles(roles);

            System.out.println("paso paso2");

            usuarioService.saveusuario(usuario.getUsuario_id(),usuario.getPassword());
        }
        return new ResponseEntity<>(rpta, HttpStatus.OK);
    }




    @PostMapping("/user/reseteo")
    public ResponseEntity<JwtResponsePass> ResteoContraseña(@RequestBody JwtRequestEmail jwtRequestEmail) throws Exception {
        String mensaje="" ;
          Usuario usu= usuarioService.existeEmailLike(jwtRequestEmail.getCorreo().trim());
            String Codigoverificacion=getRandomString();
            if (usu.getEmail() != null){
                String body=  "<html><body> se envia codigo de verificación: " + Codigoverificacion +
                        "<p><p><p><p><p><img src='https://ci3.googleusercontent.com/mail-sig/AIorK4zp0xnuCxJiBiWM8IhxrixsCRYNscTIa2gACv65i5giCUA-Kq0YJFc9hyfFktNgdyFY22gifu0'  width='450' height='160'/"+
                        " </body></html>";
                String subject="Portal Web Participe - Solicitud de Reseteo de contraseña" ;
                String from="";
                String pass="";
                String[] file =new String[6];
                String correo=jwtRequestEmail.getCorreo();
                String[] to= correo.trim().split(",");;
                iConsultaService.sendFromGMail(this.usuario, this.password, to, subject, body, file);
                Calendar calendario = Calendar.getInstance();
                Calendar fecha = Calendar.getInstance();
                fecha.add(Calendar.SECOND, 60);
                int hora, minutos, segundos,horaf, minutosf, segundosf;
                hora = calendario.get(Calendar.HOUR_OF_DAY) ;
                minutos=calendario.get(Calendar.MINUTE);
                segundos=calendario.get(Calendar.SECOND);
                horaf = fecha.get(Calendar.HOUR_OF_DAY) ;
                minutosf=fecha.get(Calendar.MINUTE);
                segundosf=fecha.get(Calendar.SECOND);
                String shora = String.format("%02d", hora);
                String sminutos = String.format("%02d", minutos);
                String ssegundos = String.format("%02d", segundos);
                String shoraf = String.format("%02d", horaf);
                String sminutosf = String.format("%02d", minutosf);
                String ssegundosf = String.format("%02d", segundosf);
                Timestamp stamp = new Timestamp(calendario.getInstance().getTimeInMillis());
                iSeguridadService.savecodigo(usu.getUsuario_id(),usu.getUsername(), Codigoverificacion,shora + ":" + sminutos + ":" + ssegundos,shoraf + ":" + sminutosf + ":" + ssegundosf );
                iAuditoriaService.insertarAuditoria("Petición de contraseña ",jwtRequestEmail.getIpaddress(),usu.getTpparticipe(),usu.getCparticipe(),usu.getUsername(),stamp,jwtRequestEmail.getTipo());
                mensaje="ok";

            }else{
                mensaje="no existe el correo del participe";
            }
        return new  ResponseEntity<JwtResponsePass>(new JwtResponsePass(usu.getUsuario_id(),usu.getUsername(),jwtRequestEmail.getCorreo(),mensaje), HttpStatus.OK) ;
    }


    private String getRandomString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 16) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }


    @PostMapping("/user/actualizausu")
    public ResponseEntity<JwtResponseRuta> actualizaInfoUsu(@RequestBody JwtRequestPass jwtRequestpass) throws Exception {
        String mensaje="" ;
        int resp;
        resp=iSeguridadService.existeVerificacion(jwtRequestpass.getUsuario_id(),jwtRequestpass.getUsername().trim(),jwtRequestpass.getCodigover().trim());
        if (resp>0) {
            Usuario user = new Usuario();
            user.setUsername(jwtRequestpass.getUsername().trim());
            user.setUsuario_id(jwtRequestpass.getUsuario_id());
            user.setPassword(jwtRequestpass.getPassword().trim());
            boolean rpta = keycloakService.updateUser(user);

            if (rpta) {
                Usuario usuario =
                        new Usuario(user.getUsuario_id(), passwordEncoder.encode(user.getPassword().trim()));
                Set<Role> roles = new HashSet<>();
                roles.add(new Role(2, "User"));

                usuario.setRoles(roles);

                System.out.println("paso paso2");

                usuarioService.saveusuario(usuario.getUsuario_id(), usuario.getPassword().trim());
                mensaje="ok";
            }else{
                mensaje="no actualizo contraseña";

            }
        }else{
            mensaje="codigo de verificacion ya expiro";
        }
        return new  ResponseEntity<JwtResponseRuta>(new JwtResponseRuta(mensaje), HttpStatus.OK) ;
    }



    @PostMapping("/user/actualizapermiso")
    public ResponseEntity<JwtResponseRuta> actualizapermiso(@RequestBody Usuario user) throws Exception {
        String mensaje="" ;
        int resp;
        resp=iSeguridadService.permisoacceso(user.getTpparticipe(),user.getUsername().trim());
        if (resp>0){
            mensaje="ok";
        }else{
            mensaje="mal";
        }
        return new  ResponseEntity<JwtResponseRuta>(new JwtResponseRuta(mensaje), HttpStatus.OK) ;
    }



    @PostMapping("/user/numeropermiso")
    public ResponseEntity<Integer> listaacceso(@RequestBody Usuario user) throws Exception {
        int resp;
        System.out.println(user.getTpparticipe()+user.getUsername().trim());
        resp=iSeguridadService.listaacceso(user.getTpparticipe(),user.getUsername().trim());

        return new  ResponseEntity<Integer>(resp, HttpStatus.OK) ;
    }

    @GetMapping("/user/cualesmiip")
    public ResponseEntity<String> miip() throws Exception {

        URL url = new URL("https://api.ipify.org/?format=json");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("accept-encoding","gzip, deflate, br");
        con.setRequestProperty("cache-control", "max-age=0");
        con.setRequestProperty("upgrade-insecure-requests", "1");

        con.setRequestProperty("Accept","*/*");
        con.setRequestProperty("AUTHTOKEN","jYFDtk2MsV-wx29ns-i8tQ");
        con.setDoOutput(true);
        //con.getOutputStream().write(data.getBytes("UTF-8"));
        //con.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"));

        String resultInfo = "";
        String line;
        while ((line = reader.readLine()) != null) {
            resultInfo += line;
        }
        //writer.close();
        reader.close();
        JSONObject jsonObject = new JSONObject(resultInfo);

        return new  ResponseEntity<String>(resultInfo, HttpStatus.OK) ;
    }



    @PostMapping("/user/notificacionbloqueo")
    public ResponseEntity<JwtResponsePass> notificacionBloqueo(@RequestBody JwtRequest jwtRequest) throws Exception {
        String mensaje="" ;
        Usuario usu= usuarioService.findByUsernameLike(jwtRequest.getTpparticipe(),jwtRequest.getUsername());
        System.out.println("email: "+ usu.getEmail());
        System.out.println("username: "+usu.getUsername());
        if (usu.getEmail() != null){
            String body=  "<html><body> Bloqueo de accesos: "  + usu.getUsername()+"-"+usu.getNombre() + " " + usu.getApellido() +
                    "<p><p><p><p><p><img src='https://ci3.googleusercontent.com/mail-sig/AIorK4zp0xnuCxJiBiWM8IhxrixsCRYNscTIa2gACv65i5giCUA-Kq0YJFc9hyfFktNgdyFY22gifu0'  width='450' height='160'/"+
                    " </body></html>";
            String subject="Portal Web Participe - Solicitud de desbloqueo de contraseña" ;
            String from="";
            String pass="";
            String[] file =new String[6];
            String correo=this.oculto; //this.usuario
            String[] to= correo.trim().split(",");;
            iConsultaService.sendFromGMail(this.usuario, this.password, to, subject, body, file);
            mensaje="ok";

        }else{
            mensaje="no existe el correo del participe";
        }
        return new  ResponseEntity<JwtResponsePass>(new JwtResponsePass(0,"","",mensaje), HttpStatus.OK) ;
    }

    @PutMapping(value="/user/dapermiso")
    public ResponseEntity<Integer> dapermiso(@RequestBody JwtRequest jwtRequest) throws  Exception{
        Integer rpta =  iSeguridadService.savepermiso(jwtRequest.getTpparticipe(),jwtRequest.getUsername().trim());

        return new ResponseEntity<>(rpta, HttpStatus.OK);
    }

}
