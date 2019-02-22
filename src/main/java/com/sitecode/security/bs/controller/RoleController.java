package com.sitecode.security.bs.controller;

import com.sitecode.datadto.security.RoleDTO;
import com.sitecode.security.bs.service.RoleService;
import com.sitecode.security.bo.Role;
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
@RequestMapping(value = "/role", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Entidad Role.", description = "Utiliza el archivo: Role")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService){
        Assert.notNull(roleService,"RoleService no puede ser nulo");
        this.roleService=roleService;
    }

    @GetMapping(value = "/consultaGeneral")
    @ApiOperation(value = "Lista todas los registro", notes = "Retorna una lista de todos los registro, del archivo: Role" )
    public ResponseEntity<Page<RoleDTO>> list(Pageable page){
        Page<RoleDTO> response = null;
        try {
            response = roleService.listAll(page);
        }catch (ServiceException e){
            throw new ResourceNotFoundException(e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/consultaSelect")
    @ApiOperation(value = "Lista todas los registro", notes = "Retorna una lista de todos los registro, del archivo: Role" )
    public ResponseEntity<Iterable<RoleDTO>> list(){
        Iterable<RoleDTO> response = null;
        try {
            response = roleService.listAll();
        }catch (ServiceException e){
            throw new ResourceNotFoundException(e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/crear")
    @ApiOperation(value = "Crea registros", notes = "Crea un registro en el archivo Role, recibe un Json de Role")
    public ResponseEntity<RoleDTO> create(@RequestBody Role entidad) throws ServiceException{
        RoleDTO response = roleService.guardarRegistro(entidad);
        return new ResponseEntity<>(response,(response != null) ? HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PatchMapping(value = "/editar")
    @ApiOperation(value = "Edita registros", notes = "Edita un registro del archivo Role, recibo un Json de Role")
    public ResponseEntity<RoleDTO> edit(@RequestBody Role entidad) throws  ServiceException{
        RoleDTO response = roleService.editarRegistro(entidad);
        return new ResponseEntity<>(response,(response != null) ? HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "eliminar/{codigo}")
    @ApiOperation(value = "Elimina registros", notes = "Elimina un registro del archivo Role.")
    public ResponseEntity<RoleDTO> delete(@PathVariable("codigo") Integer codigo) throws  ServiceException{
        RoleDTO response = roleService.eliminarRegistro(codigo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "consulta/{codigo}")
    @ApiOperation(value = "Obtiene un solo registro", notes = "Obtiene registro de archivo Role")
    public ResponseEntity<RoleDTO> get(@PathVariable("codigo") Integer codigo) throws  ServiceException{
        RoleDTO response = roleService.getRegistro(codigo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
