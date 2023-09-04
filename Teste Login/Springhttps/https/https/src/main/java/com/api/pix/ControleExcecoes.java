package com.api.pix;

import com.api.pix.model.dto.ObjetoErroDTO;
import org.hibernate.PersistentObjectException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;
@RestControllerAdvice
@ControllerAdvice
public class ControleExcecoes extends ResponseEntityExceptionHandler {



    @ExceptionHandler(ExceptionApiPix.class)
    public ResponseEntity<Object> handleExceptionCustom (ExceptionApiPix ex) {

        ObjetoErroDTO objetoErroDTO = new ObjetoErroDTO();

        objetoErroDTO.setError(ex.getMessage());
        objetoErroDTO.setCode(HttpStatus.OK.toString());

        return new ResponseEntity<Object>(objetoErroDTO, HttpStatus.OK);
    }


    @ExceptionHandler({DataIntegrityViolationException.class,
            ConstraintViolationException.class, SQLException.class,InvalidDataAccessApiUsageException.class})
    protected ResponseEntity<Object> handleExceptionDataIntegry(Exception ex){

        ObjetoErroDTO objetoErroDTO = new ObjetoErroDTO();
System.out.println("aaa "+ex.getClass());
        String msg = "";
        if (ex instanceof InvalidDataAccessApiUsageException){
            System.out.println("aaassssssssssssssssssssssssssssssss "+ex.getClass());
            msg = "Erro de persistencia no banco: " ;
//            +  ((InvalidDataAccessApiUsageException) ex).getCause().getCause().getMessage();
        }
        else
        if (ex instanceof DataIntegrityViolationException) {
            msg = "Erro de integridade no banco: " +  ((DataIntegrityViolationException) ex).getCause().getCause().getMessage();
        }else
        if (ex instanceof ConstraintViolationException) {
            msg = "Erro de chave estrangeira: " + ((ConstraintViolationException) ex).getCause().getCause().getMessage();
        }else
        if (ex instanceof SQLException) {
            msg = "Erro de SQL do Banco: " + ((SQLException) ex).getCause().getCause().getMessage();
        }else {
            msg = "Aqui"+ ex.getMessage();
        }

        objetoErroDTO.setError(msg);
        objetoErroDTO.setCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());

        ex.printStackTrace();


//		try {
//
//			serviceSendEmail.enviarEmailHtml("Erro na loja virtual",
//					ExceptionUtils.getStackTrace(ex),
//					"alex.fernando.egidio@gmail.com");
//
//		} catch (UnsupportedEncodingException | MessagingException e) {
//			e.printStackTrace();
//		}
//
        return new ResponseEntity<Object>(objetoErroDTO, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
