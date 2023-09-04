package com.api.crudangular.exception;

public class RecordNotFoundException  extends RuntimeException{
    public static final long serialVersionUID = 1L;

    public RecordNotFoundException(Long id){
        super("Registro não encotrando com o id: "+ id);//TODO tentar passar no body para tratar no front
    }

}
