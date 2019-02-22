package com.sitecode.security.bs.service.impl;

import com.sitecode.datadto.conf.ErrorDTO;
import com.sitecode.datadto.security.EmpresaDTO;
import com.sitecode.datadto.security.EmpresaPorUsuarioDTO;
import com.sitecode.security.bs.repository.EmpresaRepository;
import com.sitecode.security.bs.repository.RelacionEmpresaUsuarioRepository;
import com.sitecode.security.bs.repository.UsuarioRepository;
import com.sitecode.security.bs.service.EmpresaService;
import com.sitecode.security.bo.Empresa;
import com.sitecode.security.bo.RelacionEmpresaUsuario;
import com.sitecode.security.bo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Henry Hernandez
 * @version 1.0.0
 * @since 1.0.0
 * created on 1/06/18
 */

@Service
public class EmpresaServiceImpl implements EmpresaService {

    @Autowired

    private final EmpresaRepository empresaRepository;
    private final RelacionEmpresaUsuarioRepository relacionEmpresaUsuarioRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public EmpresaServiceImpl(EmpresaRepository empresaRepository,
                              RelacionEmpresaUsuarioRepository relacionEmpresaUsuarioRepository,
                              UsuarioRepository usuarioRepository){
        Assert.notNull(empresaRepository,"MonedaRepository no puede ser nulo.");
        Assert.notNull(relacionEmpresaUsuarioRepository,"RelacionEmpresaUsuarioRepository no puede ser nulo.");
        Assert.notNull(usuarioRepository,"UsuarioRepository no puede ser nulo.");
        this.empresaRepository=empresaRepository;
        this.relacionEmpresaUsuarioRepository=relacionEmpresaUsuarioRepository;
        this.usuarioRepository=usuarioRepository;
    }

    /*List*/
    @Override
    public Iterable<EmpresaDTO> listAll(){
        Iterable<EmpresaDTO> responseDto = null;
        List<EmpresaDTO> response = new ArrayList<>();
        empresaRepository.findAll().forEach(cycle->{
            EmpresaDTO empresaDTO = new EmpresaDTO();
            empresaDTO.setIdEmpresa(cycle.getIdEmpresa());
            empresaDTO.setCodigo(cycle.getCodigo());
            empresaDTO.setNombre(cycle.getNombre());
            response.add(empresaDTO);
        });
        responseDto = response;
        return responseDto;
    }

    /*Get*/
    @Override
    public EmpresaDTO getRegistro(Integer codigo){
        if(codigo==null){
            return new EmpresaDTO(1,"Codigo de registro no puede ser nulo.");
        }
        Empresa response = empresaRepository.findOne(codigo);
        if(response == null){
            return new EmpresaDTO(2,"No se encontro registro.");
        }else{
            EmpresaDTO empresaDTO = new EmpresaDTO();
            empresaDTO.setIdEmpresa(response.getIdEmpresa());
            empresaDTO.setCodigo(response.getCodigo());
            empresaDTO.setNombre(response.getNombre());
            return empresaDTO;
        }
    }

    /*Save*/
    @Override
    public EmpresaDTO guardarRegistro(Empresa entidad){
        if(entidad==null){
            return new EmpresaDTO(3,"Datos no pueden ser nulos.");
        }
        if(entidad.getCodigo() == null){
            return new EmpresaDTO(1,"Codigo de registro no puede ser nulo.");
        }
        Empresa validacion = empresaRepository.findByCodigo(entidad.getCodigo());
        if(validacion != null){
            return new EmpresaDTO(4, "Codigo de empresa ya existe.");
        }
        entidad.setIdEmpresa(null);
        empresaRepository.save(entidad);
        return new EmpresaDTO(10, "Registro creado con exito");
    }

    /*Edit*/
    @Override
    public EmpresaDTO editarRegistro(Empresa entidad){
        if(entidad.getCodigo() == null){
            return new EmpresaDTO(1,"Codigo de registro no puede ser nulo.");
        }
        Empresa update = empresaRepository.findOne(entidad.getIdEmpresa());
        if (update == null){
            return new EmpresaDTO(5,"No se encontro registro");
        }else{
            empresaRepository.saveAndFlush(entidad);
        }
        return new EmpresaDTO(11, "Registro modificado con exito");
    }

    /*Borrar*/
    @Override
    public EmpresaDTO eliminarRegistro(Integer codigo){
        if (codigo == null){
            return new EmpresaDTO(1,"Codigo de registro no puede ser nulo.");
        }
        Empresa delete = empresaRepository.findOne(codigo);
        if(delete==null){
            return new EmpresaDTO(5,"No se encontro registro");
        }
        try{
            empresaRepository.delete(codigo);
        }catch (Exception e){
            return new EmpresaDTO(6,"Registro se encuentra relacionado");
        }
        return new EmpresaDTO(12, "Registro eliminado con exito");
    }

    /*List*/
    @Override
    public Iterable<EmpresaDTO> listForUsuario(Integer idUsuario){
        List<EmpresaDTO> response = new ArrayList<>();
        relacionEmpresaUsuarioRepository.findByIdUsuario(idUsuario).forEach(cycle -> {
            EmpresaDTO empresaDTO = new EmpresaDTO();
            empresaDTO.setIdEmpresa(cycle.getIdEmpresa());
            Empresa empresa = empresaRepository.findOne(cycle.getIdEmpresa());
            empresaDTO.setCodigo(empresa.getCodigo());
            empresaDTO.setNombre(empresa.getNombre());
            response.add(empresaDTO);
        });
        return response;
    }

    /*Get*/
    @Override
    public EmpresaDTO validaEmpresa(Integer idUsuario){
        List<RelacionEmpresaUsuario> listRelacion = new ArrayList<>();
        Iterable<RelacionEmpresaUsuario> empresaIterable = relacionEmpresaUsuarioRepository.findByIdUsuario(idUsuario);
        empresaIterable.forEach(cycle -> {
            if (cycle != null) {
                listRelacion.add(cycle);
            }
        });
        if(listRelacion.size() > 1) {
            return new EmpresaDTO(20,"Usuario posee mas de una empresa relacionada.");
        } else {
            if (listRelacion.size() > 0) {
                EmpresaDTO empresaDTO = new EmpresaDTO(21, "Usuario posee solo una empresa.");
                empresaDTO.setIdEmpresa(listRelacion.get(0).getIdEmpresa());
                return empresaDTO;
            } else {
                return new EmpresaDTO(22, "No se encontro registro.");
            }
        }
    }

    /*List Por Usuario*/
    @Override
    public Iterable<EmpresaPorUsuarioDTO> listAllPorUsuario(Integer idUsuario){
        List<EmpresaPorUsuarioDTO> response = new ArrayList<>();
        empresaRepository.findAll().forEach(cycle->{
            EmpresaPorUsuarioDTO empresaPorUsuarioDTO = new EmpresaPorUsuarioDTO();
            empresaPorUsuarioDTO.setIdEmpresa(cycle.getIdEmpresa());
            empresaPorUsuarioDTO.setCodigo(cycle.getCodigo());
            empresaPorUsuarioDTO.setNombre(cycle.getNombre());
            RelacionEmpresaUsuario validacion = relacionEmpresaUsuarioRepository.findByIdUsuarioAndIdEmpresa(idUsuario, cycle.getIdEmpresa());
            if(validacion == null) {
                empresaPorUsuarioDTO.setRelacion(false);
            } else {
                empresaPorUsuarioDTO.setRelacion(true);
            }
            empresaPorUsuarioDTO.setIdUsuario(idUsuario);
            response.add(empresaPorUsuarioDTO);
        });
        return response;
    }

    /*Save*/
    @Override
    public ErrorDTO creaRegistroRelacion(Integer idUsuario, Integer idEmpresa){
        if(idUsuario==null || idEmpresa == null){
            return new ErrorDTO(3,"Datos no pueden ser nulos.");
        }
        Empresa empresa = empresaRepository.findOne(idEmpresa);
        if(empresa == null){
            return new ErrorDTO(8,"Empresa no existe.");
        }
        Usuario usuario = usuarioRepository.findOne(idUsuario);
        if (usuario == null) {
            return new ErrorDTO(9, "Usuario no existe.");
        }
        RelacionEmpresaUsuario relacionEmpresaUsuario = new RelacionEmpresaUsuario();
        relacionEmpresaUsuario.setIdRelacion(null);
        relacionEmpresaUsuario.setIdEmpresa(idEmpresa);
        relacionEmpresaUsuario.setIdUsuario(idUsuario);
        relacionEmpresaUsuarioRepository.save(relacionEmpresaUsuario);
        return new ErrorDTO(10, "Registro creado con exito");
    }

    /*Borrar*/
    @Override
    public ErrorDTO eliminaRelacion(Integer idUsuario, Integer idEmpresa){
        if (idUsuario == null || idEmpresa == null){
            return new ErrorDTO(1,"Codigo de registro no puede ser nulo.");
        }
        Empresa delete = empresaRepository.findOne(idEmpresa);
        if(delete==null){
            return new ErrorDTO(5,"Empresa no existe.");
        }
        Usuario usuario = usuarioRepository.findOne(idUsuario);
        if (usuario == null) {
            return new ErrorDTO(9, "Usuario no existe.");
        }
        RelacionEmpresaUsuario validacion = relacionEmpresaUsuarioRepository.findByIdUsuarioAndIdEmpresa(idUsuario, idEmpresa);
        if (validacion == null) {
            return new ErrorDTO(10, "Relacion no encontrada.");
        }
        try{
            relacionEmpresaUsuarioRepository.delete(validacion);
        }catch (Exception e){
            return new ErrorDTO(6,"Registro se encuentra relacionado");
        }
        return new ErrorDTO(12, "Registro eliminado con exito");
    }

}
