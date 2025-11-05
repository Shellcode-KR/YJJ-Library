package com.yjj.library.model.services;

import com.yjj.library.model.dao.UsuarioDAO;
import com.yjj.library.model.entities.Usuario;
import com.yjj.library.utils.HashUtils;
import java.util.List;



public class UsuarioService {
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    public List<Usuario> listarTodos() {
        return usuarioDAO.findAll();
    }

    public Usuario buscarPorId(Integer id) {
        return usuarioDAO.findById(id);
    }

    public Usuario buscarPorUsername(String username) {
        return usuarioDAO.buscarPorUsername(username);
    }

    public void registrar(Usuario usuario, String passwordPlano) {
        String hash = HashUtils.hashPassword(passwordPlano);
        usuario.setPasswordHash(hash);
        usuarioDAO.create(usuario);
    }

    public boolean autenticar(String username, String passwordPlano) {
        Usuario u = usuarioDAO.buscarPorUsername(username);
        if (u == null) return false;
        return HashUtils.verificar(passwordPlano, u.getPasswordHash());
    }

    public void actualizar(Usuario usuario) {
        usuarioDAO.update(usuario);
    }

    public void eliminar(Integer id) {
        usuarioDAO.delete(id);
    }

    public List<Usuario> listarPorEstado(String estado) {
        return usuarioDAO.buscarPorEstado(estado);
    }
}
