package com.sitecode.security.bs.service.impl;

import com.sitecode.datadto.security.MenuSecundarioDTO;
import com.sitecode.security.bs.repository.MenuPrincipalRepository;
import com.sitecode.security.bs.repository.MenuSecundarioRepository;
import com.sitecode.security.bs.repository.OpcionesRepository;
import com.sitecode.security.bs.service.MenuSecundarioService;
import com.sitecode.security.bo.MenuPrincipal;
import com.sitecode.security.bo.MenuSecundario;
import com.sitecode.security.bo.Opciones;
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
 * created on 24/06/18
 */

@Service
public class MenuSecundarioServiceImpl implements MenuSecundarioService {

    @Autowired

    private final MenuPrincipalRepository menuPrincipalRepository;
    private final MenuSecundarioRepository menuSecundarioRepository;
    private final OpcionesRepository opcionesRepository;

    @Autowired
    public MenuSecundarioServiceImpl(MenuPrincipalRepository menuPrincipalRepository,
                                     MenuSecundarioRepository menuSecundarioRepository,
                                     OpcionesRepository opcionesRepository){
        Assert.notNull(menuPrincipalRepository,"MenuPrincipalRepository no puede ser nulo.");
        Assert.notNull(menuSecundarioRepository,"MenuSecundarioRepository no puede ser nulo.");
        Assert.notNull(opcionesRepository,"OpcionesRepository no puede ser nulo.");
        this.menuPrincipalRepository=menuPrincipalRepository;
        this.menuSecundarioRepository = menuSecundarioRepository;
        this.opcionesRepository = opcionesRepository;
    }

    /*List*/
    @Override
    public Page<MenuSecundarioDTO> listAll(Pageable page, Integer tipoMenu){
        List<MenuSecundarioDTO> lista = new ArrayList<>();
        Page<MenuSecundario> listaIterable = menuSecundarioRepository.findByIdMenuPrincipalOrderByOrden(page, tipoMenu);
        if(listaIterable != null){
            listaIterable.forEach(cycle-> {
                MenuSecundarioDTO dto = new MenuSecundarioDTO();
                dto.setDescripcion(cycle.getDescripcion());
                dto.setIdMenuPrincipal(cycle.getIdMenuPrincipal());
                dto.setIdMenuSecundario(cycle.getIdMenuSecundario());
                dto.setIdOpciones(cycle.getIdOpciones());
                dto.setOrden(cycle.getOrden());
                lista.add(dto);
            });
        }
        Page<MenuSecundarioDTO> myObjectsPage = new PageImpl<MenuSecundarioDTO>(
                lista,
                new PageRequest(page.getPageNumber(), page.getPageSize()),
                listaIterable.getTotalElements());
        return myObjectsPage;
    }

    /*List*/
    @Override
    public Iterable<MenuSecundarioDTO> listAll(Integer tipoMenu){
        List<MenuSecundarioDTO> lista = new ArrayList<>();
        Iterable<MenuSecundario> listaIterable = menuSecundarioRepository.findByIdMenuPrincipalOrderByOrden(tipoMenu);
        if(listaIterable != null){
            listaIterable.forEach(cycle-> {
                MenuSecundarioDTO dto = new MenuSecundarioDTO();
                dto.setDescripcion(cycle.getDescripcion());
                dto.setIdMenuPrincipal(cycle.getIdMenuPrincipal());
                dto.setIdMenuSecundario(cycle.getIdMenuSecundario());
                dto.setIdOpciones(cycle.getIdOpciones());
                dto.setOrden(cycle.getOrden());
                lista.add(dto);
            });
        }
        return lista;
    }

    /*Get*/
    @Override
    public MenuSecundarioDTO getRegistro(Integer codigo){
        if(codigo==null){
            return new MenuSecundarioDTO(1,"Codigo de registro no puede ser nulo.");
        }
        MenuSecundario response = menuSecundarioRepository.findOne(codigo);
        if(response == null){
            return new MenuSecundarioDTO(2,"No se encontro registro.");
        }else{
            MenuSecundarioDTO responseDto = new MenuSecundarioDTO();
            responseDto.setDescripcion(response.getDescripcion());
            responseDto.setIdMenuPrincipal(response.getIdMenuPrincipal());
            responseDto.setIdMenuSecundario(response.getIdMenuSecundario());
            responseDto.setIdOpciones(response.getIdOpciones());
            responseDto.setOrden(response.getOrden());
            return responseDto;
        }
    }

    /*Save*/
    @Override
    public MenuSecundarioDTO guardarRegistro(MenuSecundario entidad){
        if(entidad==null){
            return new MenuSecundarioDTO(3,"Datos no pueden ser nulos.");
        }
        MenuPrincipal menuPrincipal = menuPrincipalRepository.findOne(entidad.getIdMenuPrincipal());
        if (menuPrincipal == null) {
            return new MenuSecundarioDTO(7, "Menu principal no existe.");
        }
        Opciones opciones = opcionesRepository.findOne(entidad.getIdOpciones());
        if (opciones == null) {
            return new MenuSecundarioDTO(8, "Opcion de menu no existe.");
        }
        entidad.setIdMenuSecundario(null);
        menuSecundarioRepository.save(entidad);
        return new MenuSecundarioDTO(10, "Registro creado con exito");
    }

    /*Edit*/
    @Override
    public MenuSecundarioDTO editarRegistro(MenuSecundario entidad){
        if(entidad == null){
            return new MenuSecundarioDTO(3,"Datos no pueden ser nulos.");
        }
        MenuPrincipal validacion = menuPrincipalRepository.findOne(entidad.getIdMenuPrincipal());
        if(validacion == null){
            return new MenuSecundarioDTO(7,"Menu principal no existe.");
        }
        Opciones opciones = opcionesRepository.findOne(entidad.getIdOpciones());
        if (opciones == null) {
            return new MenuSecundarioDTO(8, "Opcion de menu no existe.");
        }
        MenuSecundario update = menuSecundarioRepository.findOne(entidad.getIdMenuSecundario());
        if (update == null){
            return new MenuSecundarioDTO(5,"No se encontro registro");
        }else{
            menuSecundarioRepository.saveAndFlush(entidad);
        }
        return new MenuSecundarioDTO(11, "Registro modificado con exito");
    }

    /*Borrar*/
    @Override
    public MenuSecundarioDTO eliminarRegistro(Integer codigo){
        if (codigo == null){
            return new MenuSecundarioDTO(1,"Codigo de registro no puede ser nulo.");
        }
        MenuSecundario delete = menuSecundarioRepository.findOne(codigo);
        if(delete==null){
            return new MenuSecundarioDTO(5,"No se encontro registro");
        }
        try{
            menuSecundarioRepository.delete(codigo);
        }catch (Exception e){
            return new MenuSecundarioDTO(6,"Registro se encuentra relacionado");
        }
        return new MenuSecundarioDTO(12, "Registro eliminado con exito");
    }

}
