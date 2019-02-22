package com.sitecode.security.bs.controller;

import com.sitecode.datadto.security.MenuSecundarioDTO;
import com.sitecode.security.bs.service.MenuSecundarioService;
import com.sitecode.security.bo.MenuSecundario;
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
 * created on 24/06/18
 */

@RestController
@CrossOrigin
@RequestMapping(value = "/menuSecundario", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Entidad MenuSecundario.", description = "Utiliza el archivo: MenuSecundario")
public class MenuSecundarioController {

    private final MenuSecundarioService menuSecundarioService;

    @Autowired
    public MenuSecundarioController(MenuSecundarioService menuSecundarioService){
        Assert.notNull(menuSecundarioService,"MenuSecundarioService no puede ser nulo");
        this.menuSecundarioService=menuSecundarioService;
    }

    @GetMapping(value = "/consultaGeneral/{menuPrincipal}")
    @ApiOperation(value = "Lista todas los registro", notes = "Retorna una lista de todos los registro, del archivo: MenuSecundario" )
    public ResponseEntity<Page<MenuSecundarioDTO>> list(@PathVariable(value = "menuPrincipal") Integer menuPrincipal,
                                                        Pageable page){
        Page<MenuSecundarioDTO> response = null;
        try {
            response = menuSecundarioService.listAll(page, menuPrincipal);
        }catch (ServiceException e){
            throw new ResourceNotFoundException(e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/consultaSelect/{menuPrincipal}")
    @ApiOperation(value = "Lista todas los registro", notes = "Retorna una lista de todos los registro, del archivo: MenuSecundario" )
    public ResponseEntity<Iterable<MenuSecundarioDTO>> list(@PathVariable(value = "menuPrincipal") Integer menuPrincipal){
        Iterable<MenuSecundarioDTO> response = null;
        try {
            response = menuSecundarioService.listAll(menuPrincipal);
        }catch (ServiceException e){
            throw new ResourceNotFoundException(e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/crear")
    @ApiOperation(value = "Crea registros", notes = "Crea un registro en el archivo MenuSecundario, recibe un Json de MenuSecundario")
    public ResponseEntity<MenuSecundarioDTO> create(@RequestBody MenuSecundario entidad) throws ServiceException{
        MenuSecundarioDTO response = menuSecundarioService.guardarRegistro(entidad);
        return new ResponseEntity<>(response,(response != null) ? HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PatchMapping(value = "/editar")
    @ApiOperation(value = "Edita registros", notes = "Edita un registro del archivo MenuSecundario, recibo un Json de MenuSecundario")
    public ResponseEntity<MenuSecundarioDTO> edit(@RequestBody MenuSecundario entidad) throws  ServiceException{
        MenuSecundarioDTO response = menuSecundarioService.editarRegistro(entidad);
        return new ResponseEntity<>(response,(response != null) ? HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "eliminar/{codigo}")
    @ApiOperation(value = "Elimina registros", notes = "Elimina un registro del archivo MenuSecundario.")
    public ResponseEntity<MenuSecundarioDTO> delete(@PathVariable("codigo") Integer codigo) throws  ServiceException{
        MenuSecundarioDTO response = menuSecundarioService.eliminarRegistro(codigo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "consulta/{codigo}")
    @ApiOperation(value = "Obtiene un solo registro", notes = "Obtiene registro del archivo MenuSecundario")
    public ResponseEntity<MenuSecundarioDTO> get(@PathVariable("codigo") Integer codigo) throws  ServiceException{
        MenuSecundarioDTO response = menuSecundarioService.getRegistro(codigo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
