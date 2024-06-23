package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.AreaEntity;
import com.example.demo.entity.EmpleadoEntity;
import com.example.demo.repository.AreaRepository;
import com.example.demo.repository.EmpleadoRepository;

@Controller
public class EmpleadoController {

	@Autowired
    private EmpleadoRepository empleadoRepository;
	
	@Autowired
    private AreaRepository areaRepository;
	
	@GetMapping("/")
	public String index(Model model) {
		List<EmpleadoEntity> listaEmpleado = empleadoRepository.findAll();
		model.addAttribute("listaEmpleado",listaEmpleado);
		return "index";
	}
	
	@GetMapping("/registrar_empleado")
	public String showRegistrarEmpleado(Model model) {
		model.addAttribute("emple", new EmpleadoEntity());
	    List<AreaEntity> areas = areaRepository.findAll();
	    model.addAttribute("areas", areas);
	    return "registrar_empleado";
	}
	
	@PostMapping("/registrar_empleado")
	public String registrarEmpleado(@ModelAttribute("emple") @Validated EmpleadoEntity empleado, BindingResult result, Model model) {
	    if (result.hasErrors()) {
	        model.addAttribute("errorMessage", "Error al registrar el empleado");
	        List<AreaEntity> areas = areaRepository.findAll();
	        model.addAttribute("areas", areas);
	        return "registrar_empleado";
	    }
	    
	    try {
	        Optional<AreaEntity> areaOptional = areaRepository.findById(empleado.getAreaEntity().getArea_id());
	        if (areaOptional.isPresent()) {
	            empleado.setAreaEntity(areaOptional.get());
	            empleadoRepository.save(empleado);
	            return "redirect:/";
	        } else {
	            model.addAttribute("errorMessage", "Área seleccionada no válida");
	            List<AreaEntity> areas = areaRepository.findAll();
	            model.addAttribute("areas", areas);
	            return "registrar_empleado";
	        }
	    } catch (Exception e) {
	        model.addAttribute("errorMessage", "Error al registrar el empleado: " + e.getMessage());
	        List<AreaEntity> areas = areaRepository.findAll();
	        model.addAttribute("areas", areas);
	        return "registrar_empleado";
	    }
	}
	
	@GetMapping("/editar_empleado/{id}")
	public String showEditarEmpleado(@PathVariable("id") String dni_empleado, Model model) {
	    Optional<EmpleadoEntity> empleadoOptional = empleadoRepository.findById(dni_empleado);
	    
	    if (empleadoOptional.isPresent()) {
	        EmpleadoEntity empleado = empleadoOptional.get();
	        model.addAttribute("emple", empleado);
	        List<AreaEntity> areas = areaRepository.findAll();
	        model.addAttribute("areas", areas);
	        return "editar_empleado";
	    } else {
	        model.addAttribute("errorMessage", "Empleado no encontrado");
	        List<EmpleadoEntity> listaEmpleado = empleadoRepository.findAll();
	        model.addAttribute("listaEmpleado", listaEmpleado);
	        return "index";
	    }
	}
	
	@PostMapping("/editar_empleado")
	public String editarEmpleado(@ModelAttribute("emple") @Validated EmpleadoEntity empleado, BindingResult result, Model model) {
	    if (result.hasErrors()) {
	        model.addAttribute("errorMessage", "Error al editar el empleado");
	        List<AreaEntity> areas = areaRepository.findAll();
	        model.addAttribute("areas", areas);
	        return "editar_empleado";
	    }
	    
	    try {
	        Optional<AreaEntity> areaOptional = areaRepository.findById(empleado.getAreaEntity().getArea_id());
	        if (areaOptional.isPresent()) {
	            empleado.setAreaEntity(areaOptional.get());
	            empleadoRepository.save(empleado);
	            return "redirect:/";
	        } else {
	            model.addAttribute("errorMessage", "Área seleccionada no válida");
	            List<AreaEntity> areas = areaRepository.findAll();
	            model.addAttribute("areas", areas);
	            return "editar_empleado";
	        }
	    } catch (Exception e) {
	        model.addAttribute("errorMessage", "Error al editar el empleado: " + e.getMessage());
	        List<AreaEntity> areas = areaRepository.findAll();
	        model.addAttribute("areas", areas);
	        return "editar_empleado";
	    }
	}
}
