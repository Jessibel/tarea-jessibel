package com.proyecto.tarea.ControllerTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.tarea.Controller.TareaController;
import com.proyecto.tarea.Entity.Tarea;
import com.proyecto.tarea.Service.Imp.TareaServiceImp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringRunner.class)
@WebMvcTest(TareaController.class)
public class TareaControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TareaServiceImp service;


    @Test
    public void crearTest() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders
                .post("/api/crear")
                .content(asJsonString(new Tarea(1L,"firstName4", null, true)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

        @Test
        public void obtenerTareasTest() throws Exception
        {
            mvc.perform( MockMvcRequestBuilders
                    .get("/api/obtener")
                    .accept(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk());
        }

    @Test
    public void obtenerTareaTest() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders
                .get("/api/obtenerTarea/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void eliminarTarea() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders.delete("/api/eliminar/{id}", 1) )
                .andExpect(status().isOk());
    }

    @Test
    public void editarTarea() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders
                .put("/api/editar")
                .content(asJsonString(new Tarea(2L, "firstName2", null, true)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
