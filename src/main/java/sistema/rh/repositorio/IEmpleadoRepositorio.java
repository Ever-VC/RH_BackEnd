package sistema.rh.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import sistema.rh.models.Empleado;

public interface IEmpleadoRepositorio extends JpaRepository<Empleado, Integer> {

}
