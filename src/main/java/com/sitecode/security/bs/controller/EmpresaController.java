package com.sitecode.security.bs.controller;

import com.sitecode.datadto.conf.ErrorDTO;
import com.sitecode.datadto.security.EmpresaDTO;
import com.sitecode.datadto.security.EmpresaPorUsuarioDTO;
import com.sitecode.security.bs.service.EmpresaService;
import com.sitecode.security.bo.Empresa;
import com.sitecode.security.bo.RelacionEmpresaUsuario;
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
@RequestMapping(value = "/empresa", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Entidad Empresa.", description = "Utiliza el archivo: Empresa")
public class EmpresaController {

    private final EmpresaService empresaService;

    @Autowired
    public EmpresaController(EmpresaService empresaService){
        Assert.notNull(empresaService,"EmpresaService no puede ser nulo");
        this.empresaService=empresaService;
    }

    @GetMapping(value = "/consultaGeneral")
    @ApiOperation(value = "Lista todas los registro", notes = "Retorna una lista de todos los registro, del archivo: Empresa" )
    public ResponseEntity<Iterable<EmpresaDTO>> list(){
        Iterable<EmpresaDTO> response = null;
        try {
            response = empresaService.listAll();
        }catch (ServiceException e){
            throw new ResourceNotFoundException(e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/crear")
    @ApiOperation(value = "Crea registros", notes = "Crea un registro en el archivo Empresa, recibe un Json de Empresa")
    public ResponseEntity<EmpresaDTO> create(@RequestBody Empresa entidad) throws ServiceException{
        EmpresaDTO response = empresaService.guardarRegistro(entidad);
        return new ResponseEntity<>(response,(response != null) ? HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PatchMapping(value = "/editar")
    @ApiOperation(value = "Edita registros", notes = "Edita un registro del archivo Empresa, recibo un Json de Empresa")
    public ResponseEntity<EmpresaDTO> edit(@RequestBody Empresa entidad) throws  ServiceException{
        EmpresaDTO response = empresaService.editarRegistro(entidad);
        return new ResponseEntity<>(response,(response != null) ? HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "eliminar/{codigo}")
    @ApiOperation(value = "Elimina registros", notes = "Elimina un registro del archivo Empresa.")
    public ResponseEntity<EmpresaDTO> delete(@PathVariable("codigo") Integer codigo) throws  ServiceException{
        EmpresaDTO response = empresaService.eliminarRegistro(codigo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "consulta/{codigo}")
    @ApiOperation(value = "Obtiene un solo registro", notes = "Obtiene registro de archivo Empresa")
    public ResponseEntity<EmpresaDTO> get(@PathVariable("codigo") Integer codigo) throws  ServiceException{
        EmpresaDTO response = empresaService.getRegistro(codigo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/empresasPorUsuario/{idUsuario}")
    @ApiOperation(value = "Lista todas las empresas con relacion al usuario.", notes = "Retorna una lista de todos los registro por usuario, del archivo: Empresa" )
    public ResponseEntity<Iterable<EmpresaDTO>> listUsuario(@PathVariable(value = "idUsuario") Integer idUsuario){
        Iterable<EmpresaDTO> response = null;
        try {
            response = empresaService.listForUsuario(idUsuario);
        }catch (ServiceException e){
            throw new ResourceNotFoundException(e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/validaEmpresasPorUsuario/{idUsuario}")
    @ApiOperation(value = "Lista todas las empresas con relacion al usuario.", notes = "Retorna una lista de todos los registro por usuario, del archivo: Empresa" )
    public ResponseEntity<EmpresaDTO> validaEmpresa(@PathVariable(value = "idUsuario") Integer idUsuario){
        EmpresaDTO response = null;
        try {
            response = empresaService.validaEmpresa(idUsuario);
        }catch (ServiceException e){
            throw new ResourceNotFoundException(e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(value = "/consultaGeneralPorusuario/{idUsuario}")
    @ApiOperation(value = "Lista todas los registro", notes = "Retorna una lista de todos los registro marcados si las tiene asignadas el usuario., del archivo: Empresa" )
    public ResponseEntity<Iterable<EmpresaPorUsuarioDTO>> listEmpresaPorUsuario(@PathVariable(value = "idUsuario") Integer idUsuario){
        Iterable<EmpresaPorUsuarioDTO> response = null;
        try {
            response = empresaService.listAllPorUsuario(idUsuario);
        }catch (ServiceException e){
            throw new ResourceNotFoundException(e.getMessage());
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/crearRelacion")
    @ApiOperation(value = "Crea registros de relacion", notes = "Crea un registro en el archivo RelacionEmpresaUsuario, recibe id de Usuario y id de Empresa a relacionar")
    public ResponseEntity<ErrorDTO> createRelacion(@RequestBody RelacionEmpresaUsuario entidad) throws ServiceException{
        ErrorDTO response = empresaService.creaRegistroRelacion(entidad.getIdUsuario(), entidad.getIdEmpresa());
        return new ResponseEntity<>(response,(response != null) ? HttpStatus.OK:HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping(value = "eliminaRelacion/{idUsuario}/{idEmpresa}")
    @ApiOperation(value = "Elimina registros de relacion", notes = "Elimina un registro del archivo RelacionEmpresaUsuario.")
    public ResponseEntity<ErrorDTO> delete(@PathVariable("idUsuario") Integer idUsuario,
                                           @PathVariable("idEmpresa") Integer idEmpresa) throws  ServiceException{
        ErrorDTO response = empresaService.eliminaRelacion(idUsuario, idEmpresa);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
