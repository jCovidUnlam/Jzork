package zorkEnum;

public enum EnumDireccion {
    NORTE("norte"),
    SUR("sur"),
    ESTE("este"),
    OESTE("oeste"),
    ARRIBA("arriba"),
    ABAJO("abajo");

    private final String value;

    EnumDireccion(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
