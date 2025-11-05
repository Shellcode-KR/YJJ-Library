package com.yjj.library.model.services;


import com.yjj.library.model.dao.NotificacionDAO;
import com.yjj.library.model.entities.Notificacion;
import java.util.List;

public class NotificacionService {
    private final NotificacionDAO notificacionDAO = new NotificacionDAO();

    public List<Notificacion> listarPorUsuario(Integer idUsuario) {
        return notificacionDAO.buscarPorUsuario(idUsuario);
    }

    public void marcarComoLeida(Notificacion notificacion) {
        notificacion.setLeido(true);
        notificacionDAO.update(notificacion);
    }

    public void enviarNotificacion(Notificacion notificacion) {
        notificacionDAO.create(notificacion);
    }
}
