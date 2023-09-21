package enums;

public enum TypeJob {
    DIRECTIVO("D"), ASISTENCIAL("A"), OPERATIVO("O");
    private final String codigo;

    TypeJob(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }
}
