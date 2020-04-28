package unicen.tallerjava;

public class Usuario {
    String nombreUsuario;
    String apellido;
    int edad;

    public Usuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    @Override
    public boolean equals(Object obj) {
        Usuario otro = (Usuario) obj;
        return otro.nombreUsuario.equals(this.nombreUsuario) && otro.apellido.equals(this.apellido);
    }

    @Override
    public int hashCode() {
        return 17 * (this.nombreUsuario.hashCode() + this.apellido.hashCode());
    }

    /*
     * 
     * hashCode de object: 31 * numeroInstancia
     * 
     * u1.equals
     * 
     * u1: ale -> 3 u2: ale -> 0 u3: ale -> 1
     * 
     * u4: ale -> 2
     * 
     * int idx = usuario.hashCode() % buckets.length
     * 
     * 0 [u2] 1 [u3] 2 -> [u4, u10, u17] 3 [u1]
     */

    public static void main(String[] args) {
        Usuario u1 = new Usuario("ale");
        Usuario u2 = new Usuario("ale");

        Usuario u3 = new Usuario("ale");

        System.out.println(u1.equals(u2));

        System.out.println(u1.hashCode());
        System.out.println(u2.hashCode());
        System.out.println(u3.hashCode());
    }
}