package com.sitecode.security.bs.service.impl;

import com.sitecode.datadto.security.EstructuraMenuDTO;
import com.sitecode.datadto.security.MenuPrincipalDTO;
import com.sitecode.datadto.security.SubEstructuraMenuDTO;
import com.sitecode.security.bo.*;
import com.sitecode.security.bs.repository.*;
import com.sitecode.security.bs.service.MenuPrincipalService;
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
 * created on 22/06/18
 */

@Service
public class MenuPrincipalServiceImpl implements MenuPrincipalService {

    @Autowired

    private final MenuPrincipalRepository menuPrincipalRepository;
    private final TipoMenuRepository tipoMenuRepository;
    private final OpcionesRepository opcionesRepository;
    private final IconosRepository iconosRepository;
    private final MenuSecundarioRepository menuSecundarioRepository;

    @Autowired
    public MenuPrincipalServiceImpl(MenuPrincipalRepository menuPrincipalRepository,
                                    TipoMenuRepository tipoMenuRepository,
                                    OpcionesRepository opcionesRepository,
                                    IconosRepository iconosRepository,
                                    MenuSecundarioRepository menuSecundarioRepository){
        Assert.notNull(menuPrincipalRepository,"MenuPrincipalRepository no puede ser nulo.");
        Assert.notNull(tipoMenuRepository,"TipoMenuRepository no puede ser nulo.");
        Assert.notNull(opcionesRepository,"OpcionesRepository no puede ser nulo.");
        Assert.notNull(iconosRepository,"IconosRepository no puede ser nulo.");
        Assert.notNull(menuSecundarioRepository,"MenuSecundarioRepository no puede ser nulo.");
        this.menuPrincipalRepository=menuPrincipalRepository;
        this.tipoMenuRepository = tipoMenuRepository;
        this.opcionesRepository = opcionesRepository;
        this.iconosRepository = iconosRepository;
        this.menuSecundarioRepository = menuSecundarioRepository;
    }

    /*List*/
    @Override
    public Page<MenuPrincipalDTO> listAll(Pageable page, Integer tipoMenu){
        List<MenuPrincipalDTO> lista = new ArrayList<>();
        Page<MenuPrincipal> listaIterable = menuPrincipalRepository.findByIdTipoMenuOrderByOrden(page, tipoMenu);
        if(listaIterable != null){
            listaIterable.forEach(cycle-> {
                MenuPrincipalDTO menuDto = new MenuPrincipalDTO();
                menuDto.setIdMenuPrincipal(cycle.getIdMenuPrincipal());
                menuDto.setOrden(cycle.getOrden());
                menuDto.setDescripcion(cycle.getDescripcion());
                menuDto.setIdOpciones(cycle.getIdOpciones());
                menuDto.setIdIcono(cycle.getIdIcono());
                menuDto.setIdTipoMenu(cycle.getIdTipoMenu());
                menuDto.setEjecuta(cycle.getEjecuta());
                lista.add(menuDto);
            });
        }
        Page<MenuPrincipalDTO> myObjectsPage = new PageImpl<MenuPrincipalDTO>(
                lista,
                new PageRequest(page.getPageNumber(), page.getPageSize()),
                listaIterable.getTotalElements());
        return myObjectsPage;
    }

    /*List*/
    @Override
    public Iterable<MenuPrincipalDTO> listAll(Integer tipoMenu){
        List<MenuPrincipalDTO> lista = new ArrayList<>();
        Iterable<MenuPrincipal> listaIterable = menuPrincipalRepository.findByIdTipoMenuOrderByOrden(tipoMenu);
        if(listaIterable != null){
            listaIterable.forEach(cycle-> {
                MenuPrincipalDTO menuDto = new MenuPrincipalDTO();
                menuDto.setIdMenuPrincipal(cycle.getIdMenuPrincipal());
                menuDto.setOrden(cycle.getOrden());
                menuDto.setDescripcion(cycle.getDescripcion());
                menuDto.setIdOpciones(cycle.getIdOpciones());
                menuDto.setIdIcono(cycle.getIdIcono());
                menuDto.setIdTipoMenu(cycle.getIdTipoMenu());
                menuDto.setEjecuta(cycle.getEjecuta());
                lista.add(menuDto);
            });
        }
        return lista;
    }

    /*Get*/
    @Override
    public MenuPrincipalDTO getRegistro(Integer codigo){
        if(codigo==null){
            return new MenuPrincipalDTO(1,"Codigo de registro no puede ser nulo.");
        }
        MenuPrincipal response = menuPrincipalRepository.findOne(codigo);
        if(response == null){
            return new MenuPrincipalDTO(2,"No se encontro registro.");
        }else{
            MenuPrincipalDTO menuDto = new MenuPrincipalDTO();
            menuDto.setIdMenuPrincipal(response.getIdMenuPrincipal());
            menuDto.setOrden(response.getOrden());
            menuDto.setDescripcion(response.getDescripcion());
            menuDto.setIdOpciones(response.getIdOpciones());
            menuDto.setIdIcono(response.getIdIcono());
            menuDto.setIdTipoMenu(response.getIdTipoMenu());
            menuDto.setEjecuta(response.getEjecuta());
            return menuDto;
        }
    }

    /*Save*/
    @Override
    public MenuPrincipalDTO guardarRegistro(MenuPrincipal entidad){
        if(entidad==null){
            return new MenuPrincipalDTO(3,"Datos no pueden ser nulos.");
        }
        TipoMenu tipoMenu = tipoMenuRepository.findOne(entidad.getIdTipoMenu());
        if (tipoMenu == null) {
            return new MenuPrincipalDTO(7, "Tipo de menu no existe.");
        }
        if (entidad.getIdOpciones() != 0) {
            Opciones opciones = opcionesRepository.findOne(entidad.getIdOpciones());
            if (opciones == null) {
                return new MenuPrincipalDTO(8, "Opcion de menu no existe.");
            }
        }
        Iconos iconos = iconosRepository.findOne(entidad.getIdIcono());
        if (iconos == null) {
            return new MenuPrincipalDTO(9, "Icono no existe.");
        }
        entidad.setIdMenuPrincipal(null);
        menuPrincipalRepository.save(entidad);
        return new MenuPrincipalDTO(10, "Registro creado con exito");
    }

    /*Edit*/
    @Override
    public MenuPrincipalDTO editarRegistro(MenuPrincipal entidad){
        if(entidad == null){
            return new MenuPrincipalDTO(3,"Datos no pueden ser nulos.");
        }
        TipoMenu validacion = tipoMenuRepository.findOne(entidad.getIdTipoMenu());
        if(validacion == null){
            return new MenuPrincipalDTO(7,"Tipo de menu no existe.");
        }
        if (entidad.getIdOpciones() != 0) {
            Opciones opciones = opcionesRepository.findOne(entidad.getIdOpciones());
            if (opciones == null) {
                return new MenuPrincipalDTO(8, "Opcion de menu no existe.");
            }
        }
        Iconos iconos = iconosRepository.findOne(entidad.getIdIcono());
        if (iconos == null) {
            return new MenuPrincipalDTO(9, "Icono no existe.");
        }
        MenuPrincipal update = menuPrincipalRepository.findOne(entidad.getIdMenuPrincipal());
        if (update == null){
            return new MenuPrincipalDTO(5,"No se encontro registro");
        }else{
            menuPrincipalRepository.saveAndFlush(entidad);
        }
        return new MenuPrincipalDTO(11, "Registro modificado con exito");
    }

    /*Borrar*/
    @Override
    public MenuPrincipalDTO eliminarRegistro(Integer codigo){
        if (codigo == null){
            return new MenuPrincipalDTO(1,"Codigo de registro no puede ser nulo.");
        }
        MenuPrincipal delete = menuPrincipalRepository.findOne(codigo);
        if(delete==null){
            return new MenuPrincipalDTO(5,"No se encontro registro");
        }
        try{
            System.out.print("Hola");
            Iterable<MenuSecundario> menuSecundarios = menuSecundarioRepository.findByIdMenuPrincipalOrderByOrden(codigo);
            menuSecundarios.forEach(cycle -> {
                 menuSecundarioRepository.delete(cycle.getIdMenuSecundario());
            });
            menuPrincipalRepository.delete(codigo);
        }catch (Exception e){
            return new MenuPrincipalDTO(6,"Registro se encuentra relacionado");
        }
        return new MenuPrincipalDTO(12, "Registro eliminado con exito");
    }

    /*MenuArmado*/
    @Override
    public Iterable<EstructuraMenuDTO> listEstructura(Integer tipoMenu){
        List<EstructuraMenuDTO> lista = new ArrayList<>();
        Iterable<MenuPrincipal> listaIterable = menuPrincipalRepository.findByIdTipoMenuOrderByOrden(tipoMenu);
        if(listaIterable != null){
            listaIterable.forEach(cycle-> {
                EstructuraMenuDTO estructuraMenuDTO = new EstructuraMenuDTO();
                estructuraMenuDTO.setIdMenuPrincipal(cycle.getIdMenuPrincipal());
                estructuraMenuDTO.setDescripcion(cycle.getDescripcion());
                if(cycle.getEjecuta() == 1) {
                    estructuraMenuDTO.setPath(opcionesRepository.findOne(cycle.getIdOpciones()).getPath());
                } else {
                    estructuraMenuDTO.setPath("");
                }
                estructuraMenuDTO.setEjecuta(cycle.getEjecuta());
                estructuraMenuDTO.setClase(iconosRepository.findOne(cycle.getIdIcono()).getClase());
                estructuraMenuDTO.setControl(true);
                Iterable<MenuSecundario> listaSubIterable = menuSecundarioRepository.findByIdMenuPrincipalOrderByOrden(cycle.getIdMenuPrincipal());
                List<SubEstructuraMenuDTO> subLista = new ArrayList<>();
                listaSubIterable.forEach(sub -> {
                    SubEstructuraMenuDTO subEstructuraMenuDTO = new SubEstructuraMenuDTO();
                    subEstructuraMenuDTO.setIdMenuSecundario(sub.getIdMenuSecundario());
                    subEstructuraMenuDTO.setDescripcion(sub.getDescripcion());
                    subEstructuraMenuDTO.setPath(opcionesRepository.findOne(sub.getIdOpciones()).getPath());
                    subLista.add(subEstructuraMenuDTO);
                });
                estructuraMenuDTO.setListadoSubEstructura(subLista);
                lista.add(estructuraMenuDTO);
            });
        }
        return lista;
    }

}
