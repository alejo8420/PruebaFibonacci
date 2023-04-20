/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package prueba.prote.envioFibonacciMail.principal;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Calendar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author alejo
 */

@RestController
public class EnviarCorreo {
    
    @Autowired
    private JavaMailSender mail;
    
    
    @PostMapping("enviarCorreo")
    public ResponseEntity<?> enviar_correo(){
        
        int X = 0, Y = 0;
        int semilla1 = 0, semilla2 = 0;
        Calendar calendario = Calendar.getInstance();
        int hora, minutos, segundos;
        hora = calendario.get(Calendar.HOUR_OF_DAY);
        minutos = calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND);
        String hora_actual = hora+":"+minutos+":"+segundos;
        
        if(minutos < 10){
            Y = minutos;
            semilla2 = minutos;
        }else{
                String semilla = String.valueOf(minutos);     
                char[] digitos = semilla.toCharArray();
                X = Character.getNumericValue(digitos[0]);
                Y = Character.getNumericValue(digitos[1]);
                semilla1 = Character.getNumericValue(digitos[0]);
                semilla2 = Character.getNumericValue(digitos[1]);
          
            }
        int cant_num = Integer.valueOf(segundos +2);
        
        int arreglo[]= new int[cant_num];
        int arreglo2[] = new int[cant_num];
        arreglo[0]= X;
        
        for(int i=2; i<=cant_num; i++){
            arreglo[i-1]=Y;
            Y = X + Y;
            X = Y - X;
        }
        int cont=0;
        for (int j = (arreglo.length-1); j>=0; j--) {
            System.out.print(arreglo[j] + ",");
            arreglo2[cont] = arreglo[j];
            cont++;
        }

        String mensaje = "La hora actual es: " + hora_actual + ". Las semillas son: "+ semilla1 +" y "+ semilla2 +". La serie es: "+Arrays.toString(arreglo2);
        
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo("didier.correa@proteccion.com.co");
        email.setFrom("alejandro.salazar8420@gmail.com");
        email.setSubject("Mensaje de Prueba");
        email.setText(mensaje);
        
        mail.send(email);
        
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
    
}
