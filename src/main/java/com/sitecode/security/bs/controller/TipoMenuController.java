package com.sitecode.security.bs.controller;

import com.sitecode.datadto.security.TipoMenuDTO;
import com.sitecode.security.bs.service.TipoMenuService;
import com.sitecode.security.bo.TipoMenu;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
@RequestMapping(value = "/tipoMenu", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Entidad TipoMenu.", description = "Utiliza el archivo: TipoMenu")
public class TipoMenuController {

    private final TipoMenuService tipoMenuService;

    @Autowired
    public TipoMenuController(TipoMenuService tipoMenuService){
        Assert.notNull(tipoMenuService,"TipoMenuService no puede ser nulo");
        this.tipoMenuService=tipoMenuService;
    }

    @GetMapping(value = "/consultaGeneral")
    @ApiOperation(value = "Lista todas los registro", notes = "Retorna una lista de todos los registro, del archivo: TipoMenu" )
    public ResponseEntity<Page<TipoMenuDTO>> list(Pageable page){
        Page<TipoMenuDTO> response = null;
        try {
            response = tipoMenuService.listAll(page);
        }catch (ServiceException e){
            throw new ResourceNotFoundException(e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/consultaSelect")
    @ApiOperation(value = "Lista todas los registro", notes = "Retorna una lista de todos los registro, del archivo: TipoMenu" )
    public ResponseEntity<Iterable<TipoMenuDTO>> list(){
        Iterable<TipoMenuDTO> response = null;
        try {
            response = tipoMenuService.listAll();
        }catch (ServiceException e){
            throw new ResourceNotFoundException(e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/crear")
    @ApiOperation(value = "Crea registros", notes = "Crea un registro en el archivo TipoUsuario, recibe un Json de TipoUsuario")
    public ResponseEntity<TipoMenuDTO> create(@RequestBody TipoMenu entidad) throws ServiceException{
        TipoMenuDTO response = tipoMenuService.guardarRegistro(entidad);
        return new ResponseEntity<>(response,(response != null) ? HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PatchMapping(value = "/editar")
    @ApiOperation(value = "Edita registros", notes = "Edita un registro del archivo TipoUsuario, recibo un Json de TipoUsuario")
    public ResponseEntity<TipoMenuDTO> edit(@RequestBody TipoMenu entidad) throws  ServiceException{
        TipoMenuDTO response = tipoMenuService.editarRegistro(entidad);
        return new ResponseEntity<>(response,(response != null) ? HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "eliminar/{codigo}")
    @ApiOperation(value = "Elimina registros", notes = "Elimina un registro del archivo Empresa.")
    public ResponseEntity<TipoMenuDTO> delete(@PathVariable("codigo") Integer codigo) throws  ServiceException{
        TipoMenuDTO response = tipoMenuService.eliminarRegistro(codigo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "consulta/{codigo}")
    @ApiOperation(value = "Obtiene un solo registro", notes = "Obtiene registro de archivo Usuario")
    public ResponseEntity<TipoMenuDTO> get(@PathVariable("codigo") Integer codigo) throws  ServiceException{
        TipoMenuDTO response = tipoMenuService.getRegistro(codigo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
