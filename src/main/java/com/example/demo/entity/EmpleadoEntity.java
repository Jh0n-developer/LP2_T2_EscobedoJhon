package com.example.demo.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_empleado")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EmpleadoEntity {

	@Id
	@Column(name = "dni_empleado", columnDefinition = "CHAR(8)")
	private String dni_empleado;
	
	@Column(name = "nombre_empleado", columnDefinition = "VARCHAR(45)")
	private String nombre_empleado;
	
	@Column(name = "apellido_empleado", columnDefinition = "VARCHAR(45)")
	private String apellido_empleado;
	
	@Column(name = "fecha_nacimiento", columnDefinition = "DATE")
	private Date fecha_nacimiento;
	
	@Column(name = "direccion", columnDefinition = "VARCHAR(45)")
	private String direccion;
	
	@Column(name = "correo", columnDefinition = "VARCHAR(45)")
	private String correo;
	
	@ManyToOne
	@JoinColumn(name = "area_id", columnDefinition = "INT", nullable = false)
	private AreaEntity areaEntity;
}
