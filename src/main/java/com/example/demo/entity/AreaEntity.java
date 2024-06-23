package com.example.demo.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_area")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AreaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "area_id", columnDefinition = "INT")
	private Integer area_id;
	
	@Column(name = "nombre_area", columnDefinition = "VARCHAR(45)")
	private String nombre_area;
	
	@OneToMany(mappedBy = "areaEntity", cascade = CascadeType.ALL)
	private List<EmpleadoEntity> empleadoEntity;
}
