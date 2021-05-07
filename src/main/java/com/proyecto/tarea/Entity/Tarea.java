package com.proyecto.tarea.Entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name="tarea")
public class Tarea {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long identificador;

    @ApiModelProperty(value = "descripción de la tarea", required = true)
    private String descripcion;

    @ApiModelProperty(value = "fecha de creación de la tarea", required = true)
    private Date fechaCreacion;

    @ApiModelProperty(value = "vigencia de la tarea", required = true)
    private Boolean vigente;
}
