
package com.axeldev.login.persistencia;

import com.axeldev.login.logica.Rol;
import com.axeldev.login.logica.Usuario;
import com.axeldev.login.persistencia.exceptions.NonexistentEntityException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladoraPersistencia {

    UsuarioJpaController userJpa = new UsuarioJpaController();
    RolJpaController rolJpa = new RolJpaController();
    
    public List<Usuario> traerUsuarios() {
        return userJpa.findUsuarioEntities();
    }

    public List<Rol> traerRoles() {
        return rolJpa.findRolEntities();
    }

    public void crearUsuario(Usuario user) {
        userJpa.create(user);
    }

    public void borrarUsuario(int id_user) {
        try {
            userJpa.destroy(id_user);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Usuario traerUsuario(int id_user) {
        return userJpa.findUsuario(id_user);
    }

    public void editarUsuario(Usuario usuario) {
        try {
            userJpa.edit(usuario);
        } catch (Exception ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
