package com.sitecode.security.bs.controller;

import com.sitecode.datadto.security.OpcionesDTO;
import com.sitecode.security.bs.service.OpcionesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Henry Hernandez
 * @version 1.0.0
 * @since 1.0.0
 * created on 1/06/18
 */

@RestController
@CrossOrigin
@RequestMapping(value = "/opciones", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Entidad Opciones.", description = "Utiliza el archivo: Opciones")
public class OpcionesController {

    private final OpcionesService opcionesService;

    @Autowired
    public OpcionesController(OpcionesService opcionesService){
        Assert.notNull(opcionesService,"OpcionesService no puede ser nulo");
        this.opcionesService=opcionesService;
    }

    @GetMapping(value = "/consultaSelect")
    @ApiOperation(value = "Lista todas los registro", notes = "Retorna una lista de todos los registro, del archivo: Opciones" )
    public ResponseEntity<Iterable<OpcionesDTO>> list(){
        Iterable<OpcionesDTO> response = null;
        try {
            response = opcionesService.listAll();
        }catch (ServiceException e){
            throw new ResourceNotFoundException(e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
