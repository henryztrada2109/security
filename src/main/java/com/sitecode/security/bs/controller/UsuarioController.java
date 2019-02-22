package com.sitecode.security.bs.controller;

import com.sitecode.datadto.security.UsuarioDTO;
import com.sitecode.security.bs.service.UsuarioService;
import com.sitecode.security.bo.Usuario;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(value = "/usuario", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Entidad Usuario.", description = "Utiliza el archivo: Usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService){
        Assert.notNull(usuarioService,"UsuarioService no puede ser nulo");
        this.usuarioService=usuarioService;
    }

    @GetMapping(value = "/consultaGeneral")
    @ApiOperation(value = "Lista todas los registro", notes = "Retorna una lista de todos los registro, del archivo: Usuario" )
    public ResponseEntity<Iterable<UsuarioDTO>> list(Pageable page){
        Iterable<UsuarioDTO> response = null;
        try {
            response = usuarioService.listAll(page);
        }catch (ServiceException e){
            throw new ResourceNotFoundException(e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/consultaFiltroUsuario/{usuario}")
    @ApiOperation(value = "Lista todas los registro", notes = "Retorna una lista de todos los registro, del archivo: Usuario" )
    public ResponseEntity<Iterable<UsuarioDTO>> list(Pageable page,
                                                     @PathVariable("usuario") String usuario){
        Iterable<UsuarioDTO> response = null;
        try {
            response = usuarioService.listAll(page, usuario);
        }catch (ServiceException e){
            throw new ResourceNotFoundException(e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/consultaFiltroRol/{rol}")
    @ApiOperation(value = "Lista todas los registro", notes = "Retorna una lista de todos los registro, del archivo: Usuario" )
    public ResponseEntity<Iterable<UsuarioDTO>> list(Pageable page,
                                                     @PathVariable("rol") Integer rol){
        Iterable<UsuarioDTO> response = null;
        try {
            response = usuarioService.listAll(page, rol);
        }catch (ServiceException e){
            throw new ResourceNotFoundException(e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/consultaFiltroUsuarioAndRol/{usuario}/{rol}")
    @ApiOperation(value = "Lista todas los registro", notes = "Retorna una lista de todos los registro, del archivo: Usuario" )
    public ResponseEntity<Iterable<UsuarioDTO>> list(Pageable page,
                                                     @PathVariable("usuario") String usuario,
                                                     @PathVariable("rol") Integer rol){
        Iterable<UsuarioDTO> response = null;
        try {
            response = usuarioService.listAll(page, usuario, rol);
        }catch (ServiceException e){
            throw new ResourceNotFoundException(e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/crear/{idEmpresa}")
    @ApiOperation(value = "Crea registros", notes = "Crea un registro en el archivo Usuario, recibe un Json de Usuario")
    public ResponseEntity<UsuarioDTO> create(@PathVariable("idEmpresa") Integer idEmpresa,
                                             @RequestBody Usuario entidad) throws ServiceException{
        UsuarioDTO response = usuarioService.guardarRegistro(entidad, idEmpresa);
        return new ResponseEntity<>(response,(response != null) ? HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PatchMapping(value = "/editar")
    @ApiOperation(value = "Edita registros", notes = "Edita un registro del archivo Usuario, recibo un Json de Usuario")
    public ResponseEntity<UsuarioDTO> edit(@RequestBody Usuario entidad) throws  ServiceException{
        UsuarioDTO response = usuarioService.editarRegistro(entidad);
        return new ResponseEntity<>(response,(response != null) ? HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "eliminar/{codigo}")
    @ApiOperation(value = "Elimina registros", notes = "Elimina un registro del archivo Empresa.")
    public ResponseEntity<UsuarioDTO> delete(@PathVariable("codigo") Integer codigo) throws  ServiceException{
        UsuarioDTO response = usuarioService.eliminarRegistro(codigo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "consulta/{codigo}")
    @ApiOperation(value = "Obtiene un solo registro", notes = "Obtiene registro de archivo Usuario")
    public ResponseEntity<UsuarioDTO> get(@PathVariable("codigo") Integer codigo) throws  ServiceException{
        UsuarioDTO response = usuarioService.getRegistro(codigo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "validar/{usuario}/{pass}")
    @ApiOperation(value = "Valida el usuario", notes = "Obtiene respues si es valido el usuario")
    public ResponseEntity<UsuarioDTO> get(@PathVariable("usuario") String usuario,
                                          @PathVariable("pass") String pass) throws  ServiceException{
        UsuarioDTO response = usuarioService.validarUsuario(usuario, pass);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
