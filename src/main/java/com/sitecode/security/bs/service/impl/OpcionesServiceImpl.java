package com.sitecode.security.bs.service.impl;

import com.sitecode.datadto.security.OpcionesDTO;
import com.sitecode.security.bs.repository.OpcionesRepository;
import com.sitecode.security.bs.repository.TipoMenuRepository;
import com.sitecode.security.bs.service.OpcionesService;
import com.sitecode.security.bo.Opciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Henry Hernandez
 * @version 1.0.0
 * @since 1.0.0
 * created on 22/06/18
 */

@Service
public class OpcionesServiceImpl implements OpcionesService {

    @Autowired

    private final OpcionesRepository opcionesRepository;

    @Autowired
    public OpcionesServiceImpl(OpcionesRepository opcionesRepository,
                               TipoMenuRepository tipoMenuRepository){
        Assert.notNull(opcionesRepository,"OpcionesRepository no puede ser nulo.");
        this.opcionesRepository=opcionesRepository;
    }

    /*List*/
    @Override
    public Iterable<OpcionesDTO> listAll(){
        List<OpcionesDTO> lista = new ArrayList<>();
        Iterable<Opciones> listaIterable = opcionesRepository.findAllByOrderByDescripcionAsc();
        if(listaIterable != null){
            listaIterable.forEach(cycle-> {
                OpcionesDTO opcionesDTO = new OpcionesDTO();
                opcionesDTO.setDescripcion(cycle.getDescripcion());
                opcionesDTO.setIdOpciones(cycle.getIdOpciones());
                opcionesDTO.setPath(cycle.getPath());
                lista.add(opcionesDTO);
            });
        }
        return lista;
    }

}
