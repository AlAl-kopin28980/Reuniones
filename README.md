# Reuniones

# nombres:
    Antonella Francisca Pincheira Poblete
    Pablo Benjamin Vargas Pino

# cambios:
     A el modelo inicial agregamos getters y setters, ademas de metodos para que los objetos interactuen
    por ejemplo un empleado puede crear una reunion, y desde la reunion se pueden enviar invitaciones,
    los empleados pueden aceptar la invitacion y asi se añade su asistencia o retraso a los ArrayList
    que se guardan en Reunion

     Para las caracteristicas nuevas, añadimos la clase Persona, de la cual ahora hereda Empleado,
    entonces es posible invitar a quienes no son empleados
     Para generar el informe desde Reunion usamos los getter en conjunto con metodos toString

     Ademas añadimos Exceptions para evitar que se llamen ciertos metodos antes de la Reunion, como finalizar,
    durante la Reunion, como calcularTiempoReal o emitirInforme, o luego de finalizar la Reunion, como Unirse
     Tambien para evitir que una Persona llame al metodo Unirse cuando no ha sido invitada a una Reunion