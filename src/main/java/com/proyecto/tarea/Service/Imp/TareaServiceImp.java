package com.proyecto.tarea.Service.Imp;

import com.proyecto.tarea.Entity.Tarea;
import com.proyecto.tarea.Repository.TareaRepository;
import com.proyecto.tarea.Service.TareaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@Service
public class TareaServiceImp implements TareaService {

    @Autowired
    private TareaRepository tareaRepository;

    ////------Obtener Tareas-----////
    @Override
    public ArrayList<Tarea> obtenerTareas(){
         Iterable<Tarea> users = tareaRepository.findAll();
         ArrayList<Tarea> list = new ArrayList<>();
         users.forEach(list::add);
         return list;
    }

    ////------Obtener Tarea dado su identificador-----////
    @Override
    public Tarea obtenerTarea(Long id){
        return  tareaRepository.findById(id).get();
    }

    ////------Agregar Tarea-----////
    @Override
    public Tarea agregarTarea(Tarea tarea) throws IOException {
        if(validarCampos(tarea)){
            throw  new IOException("Campos de tarea vacios");
        }
        tarea.setFechaCreacion(new Date());
        return tareaRepository.save(tarea);
    }

    ////------Editar Tarea-----////
    @Override
    public Tarea editarTarea(Tarea tarea) throws IOException {
        if(!tareaRepository.existsById(tarea.getIdentificador())){
            throw  new IOException("La tarea no existe");
        }
        if(validarCampos(tarea)){
            throw  new IOException("La tarea contiene un campo vacio");
        }
        if(validarIgualTarea(tarea)){
            throw  new IOException("Existen dos tareas con campos iguales");
        }
        tarea.setFechaCreacion(new Date());
        return tareaRepository.save(tarea);
    }

    ////------Eliminar Tarea-----////
    @Override
    public boolean eliminarTarea(Long id){
       if (tareaRepository.existsById(id)){
           tareaRepository.deleteById(id);
           return true;
       }
           return false;
    }



    Boolean validarIgualTarea(Tarea tarea){
        ArrayList<Tarea> tareas = (ArrayList<Tarea>) tareaRepository.findAll();
        for(int i=0; i< tareas.size(); i++){
            if(tareas.get(i).getDescripcion().equals(tarea.getDescripcion()) &&
                    tareas.get(i).getVigente().compareTo(tarea.getVigente()) == 0){
                return true;
            } }
        return false;
    }

    Boolean validarCampos(Tarea tarea) {
        if (tarea.getDescripcion() == null || tarea.getVigente() == null || tarea.getDescripcion().isEmpty() ) {
            return true;
        }
        return false;
    }


}
