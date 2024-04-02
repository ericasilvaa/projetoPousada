import Model.Pessoas.AdministradorGerenciar;

import java.util.ArrayList;
import java.util.Iterator;

import static Model.Pessoas.AdministradorGerenciar.getListaAdministrador;

public class Questoes {
    public static void main(String[] args) {

        AdministradorGerenciar administradorGerenciar = new AdministradorGerenciar();
        administradorGerenciar.carregarDados();
        imprimirListaComIterator(getListaAdministrador());

    }

    public static <T> void imprimirListaComIterator(ArrayList<T> lista) {
        Iterator<T> iterator = lista.iterator();
        while (iterator.hasNext()) {
            T elemento = iterator.next();
            System.out.println(elemento);
        }

    }
}
