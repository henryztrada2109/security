package com.sitecode.security.bs.service.impl;

import com.sitecode.datadto.security.TipoMenuDTO;
import com.sitecode.security.bs.repository.TipoMenuRepository;
import com.sitecode.security.bs.service.TipoMenuService;
import com.sitecode.security.bo.TipoMenu;
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
public class TipoMenuServiceImpl implements TipoMenuService {

    @Autowired

    private final TipoMenuRepository tipoMenuRepository;

    @Autowired
    public TipoMenuServiceImpl(TipoMenuRepository tipoMenuRepository){
        Assert.notNull(tipoMenuRepository,"TipoMenuRepository no puede ser nulo.");
        this.tipoMenuRepository=tipoMenuRepository;
    }

    /*List*/
    @Override
    public Page<TipoMenuDTO> listAll(Pageable page){
        List<TipoMenuDTO> lista = new ArrayList<>();
        Page<TipoMenu> listaIterable = tipoMenuRepository.findAllByOrderByCodigoAsc(page);
        if(listaIterable != null){
            listaIterable.forEach(cycle-> {
                TipoMenuDTO tipoMenuDTO = new TipoMenuDTO();
                tipoMenuDTO.setCodigo(cycle.getCodigo());
                tipoMenuDTO.setDescripcion(cycle.getDescripcion());
                tipoMenuDTO.setIdTipoMenu(cycle.getIdTipoMenu());
                lista.add(tipoMenuDTO);
            });
        }
        Page<TipoMenuDTO> myObjectsPage = new PageImpl<TipoMenuDTO>(
                lista,
                new PageRequest(page.getPageNumber(), page.getPageSize()),
                listaIterable.getTotalElements());
        return myObjectsPage;
    }

    /*List*/
    @Override
    public Iterable<TipoMenuDTO> listAll(){
        List<TipoMenuDTO> lista = new ArrayList<>();
        Iterable<TipoMenu> listaIterable = tipoMenuRepository.findAllByOrderByCodigoAsc();
        if(listaIterable != null){
            listaIterable.forEach(cycle-> {
                TipoMenuDTO tipoMenuDTO = new TipoMenuDTO();
                tipoMenuDTO.setCodigo(cycle.getCodigo());
                tipoMenuDTO.setDescripcion(cycle.getDescripcion());
                tipoMenuDTO.setIdTipoMenu(cycle.getIdTipoMenu());
                lista.add(tipoMenuDTO);
            });
        }
        return lista;
    }

    /*Get*/
    @Override
    public TipoMenuDTO getRegistro(Integer codigo){
        if(codigo==null){
            return new TipoMenuDTO(1,"Codigo de registro no puede ser nulo.");
        }
        TipoMenu response = tipoMenuRepository.findOne(codigo);
        if(response == null){
            return new TipoMenuDTO(2,"No se encontro registro.");
        }else{
            TipoMenuDTO responseDto = new TipoMenuDTO();
            responseDto.setCodigo(response.getCodigo());
            responseDto.setDescripcion(response.getDescripcion());
            responseDto.setIdTipoMenu(response.getIdTipoMenu());
            return responseDto;
        }
    }

    /*Save*/
    @Override
    public TipoMenuDTO guardarRegistro(TipoMenu entidad){
        if(entidad==null){
            return new TipoMenuDTO(3,"Datos no pueden ser nulos.");
        }
        TipoMenu validacion = tipoMenuRepository.findByCodigo(entidad.getCodigo());
        if(validacion != null){
            return new TipoMenuDTO(4,"Codigo ya existe.");
        }
        entidad.setIdTipoMenu(null);
        tipoMenuRepository.save(entidad);
        return new TipoMenuDTO(10, "Registro creado con exito");
    }

    /*Edit*/
    @Override
    public TipoMenuDTO editarRegistro(TipoMenu entidad){
        if(entidad.getIdTipoMenu() == null){
            return new TipoMenuDTO(1,"Codigo de registro no puede ser nulo.");
        }
        TipoMenu validacion = tipoMenuRepository.findByCodigo(entidad.getCodigo());
        if(validacion != null && validacion.getIdTipoMenu() != entidad.getIdTipoMenu()){
            return new TipoMenuDTO(4,"Codigo ya existe.");
        }
        TipoMenu update = tipoMenuRepository.findOne(entidad.getIdTipoMenu());
        if (update == null){
            return new TipoMenuDTO(5,"No se encontro registro");
        }else{
            tipoMenuRepository.saveAndFlush(entidad);
        }
        return new TipoMenuDTO(11, "Registro modificado con exito");
    }

    /*Borrar*/
    @Override
    public TipoMenuDTO eliminarRegistro(Integer codigo){
        if (codigo == null){
            return new TipoMenuDTO(1,"Codigo de registro no puede ser nulo.");
        }
        TipoMenu delete = tipoMenuRepository.findOne(codigo);
        if(delete==null){
            return new TipoMenuDTO(5,"No se encontro registro");
        }
        try{
            tipoMenuRepository.delete(codigo);
        }catch (Exception e){
            return new TipoMenuDTO(6,"Registro se encuentra relacionado");
        }
        return new TipoMenuDTO(12, "Registro eliminado con exito");
    }

}
