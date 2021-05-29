package dominio;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Veterinaria {

    private String nombre;
    private ArrayList<Mascota> pacientes = new ArrayList<>();

    public boolean esAptoParaOperacion(String nombre, String sexo, int edad, boolean esterilizado) {
        boolean esapto = false;
        if(esterilizado==false) {
            if(sexo == "macho") {
                if(edad >= 12) {
                    esapto = true;
                }
            } else {
                if(edad >= 18) {
                    esapto = true;
                }
            }
        }
        return esapto;
    }

    public Mascota revisarMascota(String nombre, String sexo, int edad, boolean esterilizado) {
        Mascota mascota = null;
        if(esAptoParaOperacion(nombre, sexo, edad, esterilizado)) {
            mascota = new MascotaApta(nombre, sexo, edad, esterilizado);
        } else {
            mascota = new MascotaEsterilizada(nombre, sexo, edad, esterilizado);
        }
        return mascota;
    }

    public List<MascotaApta> getPacientesAOperar(){
        List<MascotaApta> mascotasAptas = new ArrayList<>();
        for (Mascota mascota : pacientes) {
            if(esAptoParaOperacion(mascota.getNombre(), mascota.getSexo(), mascota.getEdad(), mascota.isEsterilizado())) {
                mascotasAptas.add((MascotaApta) mascota);
            }
        }
        return mascotasAptas;
    }

    public void operar() {
        List<MascotaApta> mascotasAOperar = this.getPacientesAOperar();
        for (MascotaApta mascotaApta : mascotasAOperar) {
            mascotaApta.operar();
        }
    }
}
