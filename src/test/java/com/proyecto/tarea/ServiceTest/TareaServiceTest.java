package com.proyecto.tarea.ServiceTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.tarea.Entity.Tarea;
import com.proyecto.tarea.Repository.TareaRepository;
import com.proyecto.tarea.Service.Imp.TareaServiceImp;
import com.proyecto.tarea.Service.TareaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;

@ExtendWith(SpringExtension.class)
@Import({
        TareaServiceImp.class
})
public class TareaServiceTest {

    @Autowired
    private TareaService service;

    @MockBean
    private TareaRepository repository;


    private ArrayList<Tarea> lista (){
        ArrayList<Tarea> list = new ArrayList<>();
        list.add(new Tarea(3L, "descriocion", null, true));
        return list;
    }
    @Test
    public void testFindAll() {
        Mockito.when(repository.findAll()).thenReturn(lista());
        ArrayList<Tarea> tareas = service.obtenerTareas();
        assertEquals(1,tareas.size());
    }


    @Test
    public void obtenerTareaTest(){
        Mockito.when(repository.findById(3L)).thenReturn(java.util.Optional.of(new Tarea(3L, "firstName4", null, true)));
        Tarea tareas = service.obtenerTarea(3L);
        assertEquals("firstName4",tareas.getDescripcion());
    }

    @Test
    public void insertarTareaTest() throws IOException {
        Mockito.when(repository.save(new Tarea(3L, "firstName6", new Date(), true))).thenReturn(new Tarea(3L, "firstName6", new Date(), true));
        Tarea tarea = service.agregarTarea(new Tarea(3L, "firstName6", new Date(), true));
        Assertions.assertEquals(null,tarea);
    }

    @Test
    public void modificarTareaTest() throws IOException {
        Mockito.when(repository.existsById(3L)).thenReturn(true);
        Mockito.when(repository.save(new Tarea(3L, "firstName6", new Date(), true))).thenReturn(new Tarea(3L, "firstName6", new Date(), true));
        Tarea tarea = service.editarTarea(new Tarea(3L, "firstName6", null, true));
        assertEquals(null,tarea);
    }

    @Test
    public void eliminarTareaTest(){
        Mockito.when(repository.existsById(3L)).thenReturn(true);
        assertEquals(true, service.eliminarTarea(3L));
    }




}
