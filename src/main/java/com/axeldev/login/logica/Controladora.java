
package com.axeldev.login.logica;

import com.axeldev.login.persistencia.ControladoraPersistencia;
import java.util.List;

public class Controladora {

    ControladoraPersistencia controlPersis = null;

    public Controladora() {
        controlPersis = new ControladoraPersistencia();
    }
    
    public Usuario validarUsuario(String usuario, String password) {
        Usuario target = null;
        List<Usuario> usuarios = controlPersis.traerUsuarios();
        for (Usuario user : usuarios) {
            if (user.getUsuario().equals(usuario) && user.getPassword().equals(password)) {
                target = user;
                return target;
            } else {
                target = null;
            }
        }
        return target;
    }

    public String validarRol(String usuario) {
        return "";
    }

    public List<Usuario> traerUsuarios() {
        return controlPersis.traerUsuarios();
    }

    public List<Rol> traerRoles() {
        return controlPersis.traerRoles();
    }

    public void crearUsuario(String usuario, String password, String rol) {
        Usuario user = new Usuario();
        user.setUsuario(usuario);
        user.setPassword(password);
        
        Rol role = new Rol();
        role = this.traerRol(rol);
        
        if (role != null) {
            user.setRol(role);
        }
        
        int id = this.buscarUltimoIdUsuarios();
        
        user.setId(id + 1);
        
        controlPersis.crearUsuario(user);
    }

    private Rol traerRol(String rol) {
        List<Rol> roles = controlPersis.traerRoles();
        
        if(roles != null){
            for (Rol role : roles) {
                if(role.getNombre().equalsIgnoreCase(rol)){
                    return role;
                }
            }
        }
        
        return null;
    }

    private int buscarUltimoIdUsuarios() {
        List<Usuario> usuarios = this.traerUsuarios();
        Usuario user = new Usuario();
        user = usuarios.get(usuarios.size() - 1);
        return user.getId();
    }

    public void borrarUsuario(int id_user) {
        controlPersis.borrarUsuario(id_user);
    }

    public Usuario traerUsuario(int id_user) {
        return controlPersis.traerUsuario(id_user);
    }

    public void editarUsuario(Usuario user, String usuario, String password, String rol) {
        user.setUsuario(usuario);
        user.setPassword(password);

        Rol role = new Rol();
        role = this.traerRol(rol);

        if (role != null) {
            user.setRol(role);
        }
        controlPersis.editarUsuario(user);
    }
    
}
