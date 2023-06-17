class Doctor {
    private String identificador;
    private String nombre;
    private String especialidad;

    public Doctor(String identificador, String nombre) {
        this.identificador = identificador;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    public String getIdentificador() {
        return identificador;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }
}