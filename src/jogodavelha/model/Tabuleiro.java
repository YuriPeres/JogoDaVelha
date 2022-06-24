package jogodavelha.model;

import java.util.ArrayList;
import java.util.Arrays;

public class Tabuleiro {

    private ArrayList<String> campos = new ArrayList<String>(Arrays.asList("1","2","3","4","5","6","7","8","9"));

    @Override
    public String toString() {
        return  " "+ campos.get(0)+" | "+ campos.get(1)+" | "+ campos.get(2)+"\n" +
                "---+---+---\n" +
                " "+ campos.get(3)+" | "+ campos.get(4)+" | "+ campos.get(5)+"\n" +
                "---+---+---\n" +
                " "+ campos.get(6)+" | "+ campos.get(7)+" | "+ campos.get(8)+"\n";
    }

    public ArrayList<String> getCampos() {
        return campos;
    }

    public void setCampos(ArrayList<String> campos) {
        this.campos = campos;
    }
}
