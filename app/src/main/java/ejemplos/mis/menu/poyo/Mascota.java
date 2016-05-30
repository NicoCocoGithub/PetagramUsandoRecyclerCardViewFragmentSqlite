package ejemplos.mis.menu.poyo;

/**
 * Created by nicopro on 16/5/16.
 */
public class Mascota {


    public void setCodigoUnico(int codigoUnico) {
        this.codigoUnico = codigoUnico;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public void setColor(int color) {
        this.color = color;
    }

    private int codigoUnico;
    private String nombre;
    private int imagen;
    private int color;

    public Mascota(int codigoUnico, String nombre, int imagen, int color) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.color = color;
        this.codigoUnico = codigoUnico;
    }

    public String getNombre() {
        return nombre;
    }

    public int getImagen() {
        return imagen;
    }

    public int getColor() {
        return color;
    }

    public int getCodigoUnico() {return codigoUnico;}
}
