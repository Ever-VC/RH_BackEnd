package sistema.rh.services;

import sistema.rh.models.Empleado;

import java.util.List;

public interface IEmpleadoServicio {
    public List<Empleado> listarEmpleados();

    public Empleado buscarEmpleadoPorId(Integer id);

    /*Si el id es vacío crea el empleado, si tiene un valor, actualiza el empleado*/
    public Empleado guardarEmpleado(Empleado empleado);

    public void eliminarEmpleado(Empleado empleado);
}
