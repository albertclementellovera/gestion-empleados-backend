package com.gestion.empleados.controlador;

import com.gestion.empleados.excepciones.ResourceNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gestion.empleados.modelo.Empleado;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.gestion.empleados.repositorY.EmpleadoRepository;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200/")
public class EmpleadoControlador {

	@Autowired
	private EmpleadoRepository repositorio;
        
        //este metodo sirve para listar todos los empleados
	@GetMapping("/empleados")
	public List<Empleado> listarTodosLosEmpleados() {
		return repositorio.findAll();
	};

        @PostMapping("/empleados")
	public Empleado GuardarEmpleado(@RequestBody Empleado empleado) {
		return repositorio.save(empleado);
	};
        
        //Metodo para modificar Empleado por el id
@GetMapping("/empleados/{id}")
public ResponseEntity<Empleado> obtenerEmpleadoPorId(@PathVariable Long id){
   Empleado empleado = repositorio.findById(id)
         .orElseThrow(() -> new ResourceNotFoundException(
               "No existe el empleado con el Id : "+id));
   return ResponseEntity.ok(empleado);
}

@PutMapping("/empleados/{id}")
public ResponseEntity<Empleado> actualizarEmpleadoPorId(@PathVariable Long id,@RequestBody Empleado detallesEmpleado){
   Empleado empleado = repositorio.findById(id)
         .orElseThrow(() -> new ResourceNotFoundException(
               "No existe el empleado con el Id : "+id));
   empleado.setNombre(detallesEmpleado.getNombre());
   empleado.setApellido(detallesEmpleado.getApellido());
   empleado.setEmail(detallesEmpleado.getEmail());
   Empleado empleadoActualizado = repositorio.save(empleado);
   return ResponseEntity.ok(empleadoActualizado);
}
//este metodo sirve para eliminar un empleado
@DeleteMapping("/empleados/{id}")
public ResponseEntity<Map<String,Boolean>> eliminarEmpleado(@PathVariable Long id){
   Empleado empleado = repositorio.findById(id)
         .orElseThrow(() -> new ResourceNotFoundException("No existe el empleado con el ID : " + id));

   repositorio.delete(empleado);
   Map<String, Boolean> respuesta = new HashMap<>();
   respuesta.put("eliminar",Boolean.TRUE);
   return ResponseEntity.ok(respuesta);
}

}
