package com.sitecode.security.bs.controller;

import com.sitecode.datadto.security.IconosDTO;
import com.sitecode.security.bs.service.IconosService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

/**
 * @author Henry Hernandez
 * @version 1.0.0
 * @since 1.0.0
 * created on 1/06/18
 */

@RestController
@CrossOrigin
@RequestMapping(value = "/iconos", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Entidad Iconos.", description = "Utiliza el archivo: Iconos")
public class IconosController {

    private final IconosService iconosService;

    @Autowired
    public IconosController(IconosService iconosService){
        Assert.notNull(iconosService,"IconosService no puede ser nulo");
        this.iconosService=iconosService;
    }

    @GetMapping(value = "/consultaSelect")
    @ApiOperation(value = "Lista todas los registro", notes = "Retorna una lista de todos los registro, del archivo: Iconos" )
    public ResponseEntity<Iterable<IconosDTO>> list(){
        Iterable<IconosDTO> response = null;
        try {
            response = iconosService.listAll();
        }catch (ServiceException e){
            throw new ResourceNotFoundException(e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/consultaDetalle/{id}")
    @ApiOperation(value = "Obtiene un registro.", notes = "Retorna una objeto, del archivo: Iconos" )
    public ResponseEntity<IconosDTO> getIcono(@PathVariable(value = "id") Integer id){
        IconosDTO response = null;
        try {
            response = iconosService.getIcono(id);
        }catch (ServiceException e){
            throw new ResourceNotFoundException(e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
