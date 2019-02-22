package com.sitecode.security.bs.controller;

import com.sitecode.datadto.security.EstructuraMenuDTO;
import com.sitecode.datadto.security.MenuPrincipalDTO;
import com.sitecode.security.bs.service.MenuPrincipalService;
import com.sitecode.security.bo.MenuPrincipal;
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
@RequestMapping(value = "/menuPrincipal", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Entidad MenuPrincipal.", description = "Utiliza el archivo: MenuPrincipal")
public class MenuPrincipalController {

    private final MenuPrincipalService menuPrincipalService;

    @Autowired
    public MenuPrincipalController(MenuPrincipalService menuPrincipalService){
        Assert.notNull(menuPrincipalService,"MenuPrincipalService no puede ser nulo");
        this.menuPrincipalService=menuPrincipalService;
    }

    @GetMapping(value = "/consultaGeneral/{tipoMenu}")
    @ApiOperation(value = "Lista todas los registro", notes = "Retorna una lista de todos los registro, del archivo: MenuPrincipal" )
    public ResponseEntity<Page<MenuPrincipalDTO>> list(@PathVariable(value = "tipoMenu") Integer tipoMenu,
                                                       Pageable page){
        Page<MenuPrincipalDTO> response = null;
        try {
            response = menuPrincipalService.listAll(page, tipoMenu);
        }catch (ServiceException e){
            throw new ResourceNotFoundException(e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/consultaSelect/{tipoMenu}")
    @ApiOperation(value = "Lista todas los registro", notes = "Retorna una lista de todos los registro, del archivo: MenuPrincipal" )
    public ResponseEntity<Iterable<MenuPrincipalDTO>> list(@PathVariable(value = "tipoMenu") Integer tipoMenu){
        Iterable<MenuPrincipalDTO> response = null;
        try {
            response = menuPrincipalService.listAll(tipoMenu);
        }catch (ServiceException e){
            throw new ResourceNotFoundException(e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/crear")
    @ApiOperation(value = "Crea registros", notes = "Crea un registro en el archivo MenuPrincipal, recibe un Json de MenuPrincipal")
    public ResponseEntity<MenuPrincipalDTO> create(@RequestBody MenuPrincipal entidad) throws ServiceException{
        MenuPrincipalDTO response = menuPrincipalService.guardarRegistro(entidad);
        return new ResponseEntity<>(response,(response != null) ? HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PatchMapping(value = "/editar")
    @ApiOperation(value = "Edita registros", notes = "Edita un registro del archivo MenuPrincipal, recibo un Json de MenuPrincipal")
    public ResponseEntity<MenuPrincipalDTO> edit(@RequestBody MenuPrincipal entidad) throws  ServiceException{
        MenuPrincipalDTO response = menuPrincipalService.editarRegistro(entidad);
        return new ResponseEntity<>(response,(response != null) ? HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "eliminar/{codigo}")
    @ApiOperation(value = "Elimina registros", notes = "Elimina un registro del archivo MenuPrincipal.")
    public ResponseEntity<MenuPrincipalDTO> delete(@PathVariable("codigo") Integer codigo) throws  ServiceException{
        MenuPrincipalDTO response = menuPrincipalService.eliminarRegistro(codigo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "consulta/{codigo}")
    @ApiOperation(value = "Obtiene un solo registro", notes = "Obtiene registro del archivo MenuPrincipal")
    public ResponseEntity<MenuPrincipalDTO> get(@PathVariable("codigo") Integer codigo) throws  ServiceException{
        MenuPrincipalDTO response = menuPrincipalService.getRegistro(codigo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "estructuraMenu/{tipoMenu}")
    @ApiOperation(value = "Obtiene la estructura para los menus", notes = "Obtiene los registros de los archivos MenuPrincipal y MenuSecuncario")
    public ResponseEntity<Iterable<EstructuraMenuDTO>> getEstructura(@PathVariable("tipoMenu") Integer tipoMenu) throws  ServiceException{
        Iterable<EstructuraMenuDTO> response = menuPrincipalService.listEstructura(tipoMenu);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
