package com.proyecto.tarea.Controller;

import com.proyecto.tarea.Entity.Tarea;
import com.proyecto.tarea.Service.Imp.TareaServiceImp;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class TareaController {

    @Autowired
    private TareaServiceImp tareaService;

    @GetMapping("/obtener")
    @ApiOperation(value = "Obtener Tareas", notes = "Obtener todas las tareas")
    public ArrayList<Tarea> obtenerTareas(){
        return tareaService.obtenerTareas();
    }

    @GetMapping("/obtenerTarea/{id}")
    @ApiOperation(value = "Obtener Tarea", notes = "Obtener una tarea por su identificador")
    public Tarea obtenerTarea(@PathVariable Long id){
        return tareaService.obtenerTarea(id);
    }


    @PostMapping("/crear")
    @ApiOperation(value = "Crear Tarea", notes = "Crear una nueva tarea")
    public Tarea crearTarea(@RequestBody Tarea tarea) throws IOException {
        return tareaService.agregarTarea(tarea);
    }

    @PutMapping("/editar")
    @ApiOperation(value = "Editar Tarea", notes = "Editar una tarea")
    public Tarea editarTarea(@RequestBody Tarea tarea) throws IOException{
        return tareaService.editarTarea(tarea);
    }

    @DeleteMapping("/eliminar/{id}")
    @ApiOperation(value = "Eliminar Tarea", notes = "Eliminar una tarea")
    public void eliminarTarea(@PathVariable("id") Long id) {
         tareaService.eliminarTarea(id);
    }


}
