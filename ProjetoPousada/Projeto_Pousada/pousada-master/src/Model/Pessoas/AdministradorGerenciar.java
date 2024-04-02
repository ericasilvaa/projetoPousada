package Model.Pessoas;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;



/**
 * Esta classe é responsável por gerenciar os dados dos administradores em uma pousada.
 * Ela permite cadastrar, editar, excluir e imprimir administradores, além de carregar e salvar os dados em arquivos JSON.
 * Os dados são armazenados em uma lista de administradores, que é convertida em JSON para persistência.
 * A contagem de administradores também é mantida para controle.
 * 
 * A classe utiliza a biblioteca Gson para serialização e desserialização dos dados em JSON.
 * 
 * Os dados dos administradores são salvos no arquivo "administradores.json" na pasta "src/arquivos".
 * 
*
 * @author  Erica Silva Rodrigues, Gabriel Moreira Siqueira
 */


public class AdministradorGerenciar {
    private static ArrayList<Administrador> listaAdministrador = new ArrayList<>();
    private static int contadorAdm;
    private static final String ARQUIVOS_ADMINISTRADORES_JSON = "src/arquivos/administradores.json";

    public AdministradorGerenciar() {
        carregarDados();
    }

    public static int getContadorAdm() {
        return contadorAdm;
    }

    public static void setContadorAdm(int contadorAdm) {
        AdministradorGerenciar.contadorAdm = contadorAdm;
    }

    public static ArrayList<Administrador> getListaAdministrador() {
        AdministradorGerenciar administradorGerenciar = new AdministradorGerenciar();
        administradorGerenciar.carregarDados();
        return listaAdministrador;
    }

    public void cadastroAdministrador(String nome, String cpf, String senha) {
        Administrador novoAdm = new Administrador(nome, cpf, senha, true);
        boolean cadastroOcorreu = listaAdministrador.add(novoAdm);
        System.out.println("O cadastro de " + novoAdm.getNome() + " ocorreu? " + cadastroOcorreu);

        if (cadastroOcorreu) {
            contadorAdm = contadorAdm + 1;
            salvarDados();
        }
    }

    public void imprimirAdministradores() {
        for (int i = 0; i < listaAdministrador.size(); i++) {
            System.out.println("ID: " + i + " - " + listaAdministrador.get(i));
        }
    }

    public void editarAdministrador(int i, String novoNome, String novoCpf, String novaSenha) {
        carregarDados();
        System.out.println("Selecionado para editar: " + listaAdministrador.get(i).toString());
        listaAdministrador.get(i).setNome(novoNome);
        listaAdministrador.get(i).setCpf(novoCpf);
        listaAdministrador.get(i).setSenha(novaSenha);
        System.out.println("Dado atual: " + listaAdministrador.get(i).toString());
        salvarDados();
    }

    public void excluirAdministrador(int i) {
        System.out.println("O Adm " + listaAdministrador.get(i) + " foi excluído. ");
        listaAdministrador.remove(i);
        salvarDados();
    }

    public void salvarDados() {
        try (Writer writer = new FileWriter(ARQUIVOS_ADMINISTRADORES_JSON)) {
            Gson gson = new Gson();
            gson.toJson(listaAdministrador, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void carregarDados() {
        try {
            String json = new String(Files.readAllBytes(Paths.get(ARQUIVOS_ADMINISTRADORES_JSON)));
            Gson gson = new Gson();
            listaAdministrador = gson.fromJson(json, new TypeToken<ArrayList<Administrador>>(){}.getType());
            contadorAdm = listaAdministrador.size();
        } catch (IOException e) {
            e.printStackTrace();
        }
        contadorAdm = listaAdministrador.size();
    }

}
