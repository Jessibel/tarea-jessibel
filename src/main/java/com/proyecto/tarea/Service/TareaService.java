package com.proyecto.tarea.Service;

import com.proyecto.tarea.Entity.Tarea;

import java.io.IOException;
import java.util.ArrayList;

public interface TareaService {

    ArrayList<Tarea> obtenerTareas();
    Tarea obtenerTarea(Long id);
    Tarea agregarTarea(Tarea task) throws IOException;
    Tarea editarTarea(Tarea task) throws IOException;
    boolean eliminarTarea(Long id);
}
