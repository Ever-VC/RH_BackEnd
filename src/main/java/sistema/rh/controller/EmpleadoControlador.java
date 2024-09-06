package sistema.rh.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sistema.rh.excepcion.RecursoNoEncontradoExcepcion;
import sistema.rh.models.Empleado;
import sistema.rh.services.IEmpleadoServicio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api")
@CrossOrigin(value = "http://localhost:3000")
public class EmpleadoControlador {

    private static final Logger logger = LoggerFactory.getLogger(EmpleadoControlador.class);

    @Autowired
    private IEmpleadoServicio empleadoServicio;

    @GetMapping(value = "/empleados")
    public List<Empleado> obtenerEmpleados() {
        return empleadoServicio.listarEmpleados();
    }

    @PostMapping("/empleados")
    public Empleado guardarEmpleado(@RequestBody Empleado empleado) {
        return empleadoServicio.guardarEmpleado(empleado);
    }

    @GetMapping("/empleados/{id}")
    public ResponseEntity<Empleado> obtenerEmpleadoPorId(@PathVariable Integer id) {
        Empleado empleado = empleadoServicio.buscarEmpleadoPorId(id);
        if (empleado == null) {
            throw new RecursoNoEncontradoExcepcion("No se encontró el empleado con id " + id);
        }
        return ResponseEntity.ok(empleado);
    }

    @PutMapping("/empleados/{id}")
    public ResponseEntity<Empleado> actualizarEmpleado(@PathVariable Integer id, @RequestBody Empleado empleado) {
        Empleado empleadoActualizado = empleadoServicio.buscarEmpleadoPorId(id);
        if (empleadoActualizado == null) {
            throw new RecursoNoEncontradoExcepcion("El empleado con id " + id + " no existe.");
        }
        empleadoActualizado.setNombre(empleado.getNombre());
        empleadoActualizado.setDepartamento(empleado.getDepartamento());
        empleadoActualizado.setSalario(empleado.getSalario());
        empleadoServicio.guardarEmpleado(empleadoActualizado);
        return ResponseEntity.ok(empleadoActualizado);
    }

    @DeleteMapping("/empleados/{id}")
    public ResponseEntity<Map<String, Boolean>> eliminarEmpleado(@PathVariable Integer id) {
        Empleado empleado = empleadoServicio.buscarEmpleadoPorId(id);
        if (empleado == null) {
            throw new RecursoNoEncontradoExcepcion("El empleado con id " + id + " no existe.");
        }
        empleadoServicio.eliminarEmpleado(empleado);
        //Respuesta tipo JSON {"eliminado": "true"}
        Map<String, Boolean> respuesta = new HashMap<>();
        respuesta.put("eliminado", Boolean.TRUE);
        return ResponseEntity.ok(respuesta);
    }
}
