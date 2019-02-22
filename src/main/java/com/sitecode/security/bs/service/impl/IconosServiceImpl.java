package com.sitecode.security.bs.service.impl;

import com.sitecode.datadto.security.IconosDTO;
import com.sitecode.security.bs.repository.IconosRepository;
import com.sitecode.security.bs.service.IconosService;
import com.sitecode.security.bo.Iconos;
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
public class IconosServiceImpl implements IconosService {

    @Autowired

    private final IconosRepository iconosRepository;

    @Autowired
    public IconosServiceImpl(IconosRepository iconosRepository){
        Assert.notNull(iconosRepository,"IconosRepository no puede ser nulo.");
        this.iconosRepository=iconosRepository;
    }

    /*List*/
    @Override
    public Iterable<IconosDTO> listAll(){
        List<IconosDTO> lista = new ArrayList<>();
        Iterable<Iconos> listaIterable = iconosRepository.findAllByOrderByNombreAsc();
        if(listaIterable != null){
            listaIterable.forEach(cycle-> {
                IconosDTO iconosDTO = new IconosDTO();
                iconosDTO.setIdIcono(cycle.getIdIcono());
                iconosDTO.setNombre(cycle.getNombre());
                iconosDTO.setClase(cycle.getClase());
                lista.add(iconosDTO);
            });
        }
        return lista;
    }

    @Override
    public IconosDTO getIcono(Integer id) {
        Iconos iconos = iconosRepository.findOne(id);
        IconosDTO iconosDTO = new IconosDTO();
        iconosDTO.setClase(iconos.getClase());
        iconosDTO.setNombre(iconos.getNombre());
        iconosDTO.setIdIcono(iconos.getIdIcono());
        return iconosDTO;
    }

}
