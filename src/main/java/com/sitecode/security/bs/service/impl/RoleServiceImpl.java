package com.sitecode.security.bs.service.impl;

import com.sitecode.datadto.security.RoleDTO;
import com.sitecode.security.bs.repository.RoleRepository;
import com.sitecode.security.bs.service.RoleService;
import com.sitecode.security.bo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
public class RoleServiceImpl implements RoleService {

    @Autowired

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository){
        Assert.notNull(roleRepository,"RoleRepository no puede ser nulo.");
        this.roleRepository=roleRepository;
    }

    /*List*/
    @Override
    public Page<RoleDTO> listAll(Pageable page){
        List<RoleDTO> lista = new ArrayList<>();
        Page<Role> listaIterable = roleRepository.findAllByOrderByCodigoAsc(page);
        if(listaIterable != null){
            listaIterable.forEach(cycle-> {
                RoleDTO roleDTO = new RoleDTO();
                roleDTO.setCodigo(cycle.getCodigo());
                roleDTO.setIdRole(cycle.getIdRole());
                roleDTO.setNombre(cycle.getNombre());
                lista.add(roleDTO);
            });
        }
        Page<RoleDTO> myObjectsPage = new PageImpl<RoleDTO>(
                lista,
                new PageRequest(page.getPageNumber(), page.getPageSize()),
                listaIterable.getTotalElements());
        return myObjectsPage;
    }

    /*List*/
    @Override
    public Iterable<RoleDTO> listAll(){
        List<RoleDTO> lista = new ArrayList<>();
        Iterable<Role> listaIterable = roleRepository.findAllByOrderByCodigoAsc();
        if(listaIterable != null){
            listaIterable.forEach(cycle-> {
                RoleDTO roleDTO = new RoleDTO();
                roleDTO.setNombre(cycle.getNombre());
                roleDTO.setIdRole(cycle.getIdRole());
                roleDTO.setCodigo(cycle.getCodigo());
                lista.add(roleDTO);
            });
        }
        return lista;
    }

    /*Get*/
    @Override
    public RoleDTO getRegistro(Integer codigo){
        if(codigo==null){
            return new RoleDTO(1,"Codigo de registro no puede ser nulo.");
        }
        Role response = roleRepository.findOne(codigo);
        if(response == null){
            return new RoleDTO(2,"No se encontro registro.");
        }else{
            RoleDTO responseDto = new RoleDTO();
            responseDto.setCodigo(response.getCodigo());
            responseDto.setIdRole(response.getIdRole());
            responseDto.setNombre(response.getNombre());
            return responseDto;
        }
    }

    /*Save*/
    @Override
    public RoleDTO guardarRegistro(Role entidad){
        if(entidad==null){
            return new RoleDTO(3,"Datos no pueden ser nulos.");
        }
        Role validacion = roleRepository.findByCodigo(entidad.getCodigo());
        if(validacion != null){
            return new RoleDTO(4,"Codigo ya existe.");
        }
        entidad.setIdRole(null);
        Role role = roleRepository.save(entidad);
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setCodigo(role.getCodigo());
        roleDTO.setIdRole(role.getIdRole());
        roleDTO.setNombre(role.getNombre());
        roleDTO.setCode(10);
        roleDTO.setDescription("Registro creado con exito");
        return roleDTO;
    }

    /*Edit*/
    @Override
    public RoleDTO editarRegistro(Role entidad){
        if(entidad.getCodigo() == null){
            return new RoleDTO(1,"Codigo de registro no puede ser nulo.");
        }
        Role validacion = roleRepository.findByCodigo(entidad.getCodigo());
        if(validacion != null && validacion.getIdRole() != entidad.getIdRole()){
            return new RoleDTO(4,"Codigo ya existe.");
        }
        Role update = roleRepository.findOne(entidad.getIdRole());
        if (update == null){
            return new RoleDTO(5,"No se encontro registro");
        }else{
            roleRepository.saveAndFlush(entidad);
        }
        return new RoleDTO(11, "Registro modificado con exito");
    }

    /*Borrar*/
    @Override
    public RoleDTO eliminarRegistro(Integer codigo){
        if (codigo == null){
            return new RoleDTO(1,"Codigo de registro no puede ser nulo.");
        }
        Role delete = roleRepository.findOne(codigo);
        if(delete==null){
            return new RoleDTO(5,"No se encontro registro");
        }
        try{
            roleRepository.delete(codigo);
        }catch (Exception e){
            return new RoleDTO(6,"Registro se encuentra relacionado");
        }
        return new RoleDTO(12, "Registro eliminado con exito");
    }

}
