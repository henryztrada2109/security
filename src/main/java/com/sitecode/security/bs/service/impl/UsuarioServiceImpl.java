package com.sitecode.security.bs.service.impl;

import com.sitecode.datadto.security.UsuarioDTO;
import com.sitecode.security.bs.repository.EmpresaRepository;
import com.sitecode.security.bs.repository.RelacionEmpresaUsuarioRepository;
import com.sitecode.security.bs.repository.RoleRepository;
import com.sitecode.security.bs.repository.UsuarioRepository;
import com.sitecode.security.bs.service.UsuarioService;
import com.sitecode.security.bo.Empresa;
import com.sitecode.security.bo.RelacionEmpresaUsuario;
import com.sitecode.security.bo.Role;
import com.sitecode.security.bo.Usuario;
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
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired

    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;
    private final RelacionEmpresaUsuarioRepository relacionEmpresaUsuarioRepository;
    private final EmpresaRepository empresaRepository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository,
                              RoleRepository roleRepository,
                              EmpresaRepository empresaRepository,
                              RelacionEmpresaUsuarioRepository relacionEmpresaUsuarioRepository){
        Assert.notNull(usuarioRepository,"UsuarioRepository no puede ser nulo.");
        Assert.notNull(roleRepository,"RoleRepository no puede ser nulo.");
        Assert.notNull(empresaRepository,"EmpresaRepository no puede ser nulo.");
        Assert.notNull(relacionEmpresaUsuarioRepository,"RelacionEmpresaUsuarioRepository no puede ser nulo.");
        this.usuarioRepository=usuarioRepository;
        this.roleRepository=roleRepository;
        this.empresaRepository=empresaRepository;
        this.relacionEmpresaUsuarioRepository=relacionEmpresaUsuarioRepository;
    }

    /*List*/
    @Override
    public Page<UsuarioDTO> listAll(Pageable page){
        List<UsuarioDTO> lista = new ArrayList<>();
        Page<Usuario> listaIterable = usuarioRepository.findAllByEstadoOrderByUsuarioAppAsc(0, page);
        if(listaIterable != null){
            listaIterable.forEach(cycle-> {
                Role role = roleRepository.findOne(cycle.getIdRole());
                UsuarioDTO responseDto = new UsuarioDTO();
                responseDto.setDescripcionRol(role.getNombre());
                responseDto.setEstado(cycle.getEstado());
                responseDto.setIdRole(cycle.getIdRole());
                responseDto.setIdUsuario(cycle.getIdUsuario());
                responseDto.setPass(cycle.getPass());
                responseDto.setTipoMenu(cycle.getIdTipoMenu());
                responseDto.setUsuarioApp(cycle.getUsuarioApp());
                lista.add(responseDto);
            });
        }
        Page<UsuarioDTO> myObjectsPage = new PageImpl<UsuarioDTO>(
                lista,
                new PageRequest(page.getPageNumber(), page.getPageSize()),
                listaIterable.getTotalElements());
        return myObjectsPage;
    }

    /*List*/
    @Override
    public Page<UsuarioDTO> listAll(Pageable page, String usuario){
        List<UsuarioDTO> lista = new ArrayList<>();
        Page<Usuario> listaIterable = usuarioRepository.filtroPorUsuario(usuario, 0, page);
        if(listaIterable != null){
            listaIterable.forEach(cycle-> {
                Role role = roleRepository.findOne(cycle.getIdRole());
                UsuarioDTO responseDto = new UsuarioDTO();
                responseDto.setDescripcionRol(role.getNombre());
                responseDto.setEstado(cycle.getEstado());
                responseDto.setIdRole(cycle.getIdRole());
                responseDto.setIdUsuario(cycle.getIdUsuario());
                responseDto.setPass(cycle.getPass());
                responseDto.setTipoMenu(cycle.getIdTipoMenu());
                responseDto.setUsuarioApp(cycle.getUsuarioApp());
                lista.add(responseDto);
            });
        }
        Page<UsuarioDTO> myObjectsPage = new PageImpl<UsuarioDTO>(
                lista,
                new PageRequest(page.getPageNumber(), page.getPageSize()),
                listaIterable.getTotalElements());
        return myObjectsPage;
    }

    /*List*/
    @Override
    public Page<UsuarioDTO> listAll(Pageable page, Integer codigo){
        List<UsuarioDTO> lista = new ArrayList<>();
        Page<Usuario> listaIterable = null;
        listaIterable = usuarioRepository.filtroPorRol(codigo, 0, page);
        if(listaIterable != null){
            listaIterable.forEach(cycle-> {
                Role role = roleRepository.findOne(cycle.getIdRole());
                UsuarioDTO responseDto = new UsuarioDTO();
                responseDto.setDescripcionRol(role.getNombre());
                responseDto.setEstado(cycle.getEstado());
                responseDto.setIdRole(cycle.getIdRole());
                responseDto.setIdUsuario(cycle.getIdUsuario());
                responseDto.setPass(cycle.getPass());
                responseDto.setTipoMenu(cycle.getIdTipoMenu());
                responseDto.setUsuarioApp(cycle.getUsuarioApp());
                lista.add(responseDto);
            });
        }
        Page<UsuarioDTO> myObjectsPage = new PageImpl<UsuarioDTO>(
                lista,
                new PageRequest(page.getPageNumber(), page.getPageSize()),
                listaIterable.getTotalElements());
        return myObjectsPage;
    }

    /*List*/
    @Override
    public Page<UsuarioDTO> listAll(Pageable page, String usuario, Integer codigo){
        List<UsuarioDTO> lista = new ArrayList<>();
        Page<Usuario> listaIterable = usuarioRepository.filtroPorUsuarioAndRol(usuario, codigo, 0, page);
        if(listaIterable != null){
            listaIterable.forEach(cycle-> {
                Role role = roleRepository.findOne(cycle.getIdRole());
                UsuarioDTO responseDto = new UsuarioDTO();
                responseDto.setDescripcionRol(role.getNombre());
                responseDto.setEstado(cycle.getEstado());
                responseDto.setIdRole(cycle.getIdRole());
                responseDto.setIdUsuario(cycle.getIdUsuario());
                responseDto.setPass(cycle.getPass());
                responseDto.setTipoMenu(cycle.getIdTipoMenu());
                responseDto.setUsuarioApp(cycle.getUsuarioApp());
                lista.add(responseDto);
            });
        }
        Page<UsuarioDTO> myObjectsPage = new PageImpl<UsuarioDTO>(
                lista,
                new PageRequest(page.getPageNumber(), page.getPageSize()),
                listaIterable.getTotalElements());
        return myObjectsPage;
    }

    /*Get*/
    @Override
    public UsuarioDTO getRegistro(Integer codigo){
        if(codigo==null){
            return new UsuarioDTO(1,"Codigo de registro no puede ser nulo.");
        }
        Usuario response = usuarioRepository.findOne(codigo);
        if(response == null){
            return new UsuarioDTO(2,"No se encontro registro.");
        }else{
            Role role = roleRepository.findOne(response.getIdRole());
            UsuarioDTO responseDto = new UsuarioDTO();
            responseDto.setDescripcionRol(role.getNombre());
            responseDto.setEstado(response.getEstado());
            responseDto.setIdRole(response.getIdRole());
            responseDto.setIdUsuario(response.getIdUsuario());
            responseDto.setPass(response.getPass());
            responseDto.setTipoMenu(response.getIdTipoMenu());
            responseDto.setUsuarioApp(response.getUsuarioApp());
            return responseDto;
        }
    }

    /*Save*/
    @Override
    public UsuarioDTO guardarRegistro(Usuario entidad, Integer idEmpresa){
        if(entidad==null){
            return new UsuarioDTO(3,"Datos no pueden ser nulos.");
        }
        Usuario validacion = usuarioRepository.findByUsuarioAppAndEstado(entidad.getUsuarioApp(), 0);
        if(validacion != null) {
            return new UsuarioDTO(15, "Usuario ya existe.");
        }
        if(entidad.getIdRole() == null){
            return new UsuarioDTO(1,"Codigo de rol no puede ser nulo.");
        }
        Empresa empresa = empresaRepository.findOne(idEmpresa);
        if(empresa==null) {
            return new UsuarioDTO(20, "Empresa no existe");
        }
        Role role = roleRepository.findOne(entidad.getIdRole());
        if(role == null){
            return new UsuarioDTO(7, "Codigo de rol no existe.");
        }
        entidad.setIdUsuario(null);
        entidad.setEstado(0);
        Usuario usuario = usuarioRepository.save(entidad);
        RelacionEmpresaUsuario relacionEmpresaUsuario = new RelacionEmpresaUsuario();
        relacionEmpresaUsuario.setIdUsuario(usuario.getIdUsuario());
        relacionEmpresaUsuario.setIdEmpresa(idEmpresa);
        relacionEmpresaUsuario.setIdRelacion(null);
        relacionEmpresaUsuarioRepository.save(relacionEmpresaUsuario);
        return new UsuarioDTO(10, "Registro creado con exito");
    }

    /*Edit*/
    @Override
    public UsuarioDTO editarRegistro(Usuario entidad){
        if(entidad.getIdUsuario() == null){
            return new UsuarioDTO(1,"Codigo de registro no puede ser nulo.");
        }
        Role role = roleRepository.findOne(entidad.getIdRole());
        if(role == null){
            return new UsuarioDTO(7, "Codigo de rol no existe.");
        }
        Usuario validacion = usuarioRepository.findByUsuarioAppAndEstado(entidad.getUsuarioApp(), 0);
        if(validacion != null && validacion.getIdUsuario() != entidad.getIdUsuario()) {
            return new UsuarioDTO(15, "Usuario ya existe.");
        }
        Usuario update = usuarioRepository.findOne(entidad.getIdUsuario());
        if (update == null){
            return new UsuarioDTO(5,"No se encontro registro");
        }else{
            usuarioRepository.saveAndFlush(entidad);
        }
        return new UsuarioDTO(11, "Registro modificado con exito");
    }

    /*Borrar*/
    @Override
    public UsuarioDTO eliminarRegistro(Integer codigo){
        if (codigo == null){
            return new UsuarioDTO(1,"Codigo de registro no puede ser nulo.");
        }
        Usuario delete = usuarioRepository.findOne(codigo);
        if(delete==null){
            return new UsuarioDTO(5,"No se encontro registro");
        }
        delete.setEstado(1);
        usuarioRepository.saveAndFlush(delete);
        return new UsuarioDTO(12, "Registro eliminado con exito");
    }

    /*Valida usuario*/
    public UsuarioDTO validarUsuario(String usuarioApp, String pass){
        if (usuarioApp == null){
            return new UsuarioDTO(8,"Usuario no puede ser nulo");
        }
        if (usuarioApp == null){
            return new UsuarioDTO(9,"Contraseña no puede ser nulo");
        }
        Usuario validar = usuarioRepository.findByUsuarioAppAndPassAndEstado(usuarioApp, pass, 0);
        if(validar == null){
            return new UsuarioDTO(13, "Usuario no existe.");
        }else {
            UsuarioDTO usuarioDTO = new UsuarioDTO(14, "Usuario correcto.");
            usuarioDTO.setTipoMenu(validar.getIdTipoMenu());
            usuarioDTO.setIdUsuario(validar.getIdUsuario());
            return usuarioDTO;
        }
    }
}
