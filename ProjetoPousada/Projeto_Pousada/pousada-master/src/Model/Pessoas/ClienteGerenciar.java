package Model.Pessoas;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import static Services.ValidacaoDadosPessoas.tentarValidarDados;

/**
 * Classe responsável por gerenciar os objetos do tipo Cliente.
 * Realiza operações como cadastro, edição, exclusão, impressão e persistência dos dados dos clientes.
 * Também permite carregar os dados a partir de um arquivo JSON.

 * @author  Erica Silva Rodrigues e   Gabriel Moreira Siqueira

 */



public class ClienteGerenciar {
    private static ArrayList<Cliente> listaClientes = new ArrayList<>();
    private static final String ARQUIVOS_CLIENTES_JSON = "src/arquivos/clientes.json";

    public ClienteGerenciar() {
        carregarDados();
    }

    /**
     * Variável estática usada como artificio de implementação em prol da identificação do Cliente via ID.
     * Compartilhado por todas as instâncias.
     */
    private static int contadorCli = listaClientes.size();

    public static ArrayList<Cliente> getListaClientes() {
        ClienteGerenciar clienteGerenciar = new ClienteGerenciar();
        clienteGerenciar.carregarDados();
        return listaClientes;
    }

    public static void setListaClientes(ArrayList<Cliente> listaClientes) {
        ClienteGerenciar.listaClientes = listaClientes;
    }

    public static void setContadorCli(int contadorCli) {
        ClienteGerenciar.contadorCli = contadorCli;
    }

    public static int getContadorCli() {
        return contadorCli;
    }

    public void cadastroCliente(String nome, String cpf, String numeroTelefone, String email, String rua, String bairro, String cep, String numeroMoradia){

        Endereco novoEndereco = new Endereco(rua, bairro, cep,numeroMoradia);
        Cliente novoCliente = new Cliente(nome, cpf, numeroTelefone, email, novoEndereco);

        boolean cadastroOcorreu = listaClientes.add(novoCliente);
        System.out.println("O cadastro de " + novoCliente.getNome() + " ocorreu? " + cadastroOcorreu);

        if(cadastroOcorreu){
            contadorCli++;
            salvarDados();
        }

    }

    public void imprimirClientes(){

        carregarDados();
        if (listaClientes.size()<1){
            System.out.println("\nNão há clientes cadastrados");
            return;
        }
        for (int i = 0; i < listaClientes.size(); i++) {
            System.out.println("ID: " + i + " - " + listaClientes.get(i) );
        }
    }

    public void editarCliente(int i, String nome, String cpf, String numeroTelefone, String email, String rua, String bairro, String cep, String numeroMoradia){
        carregarDados();

        listaClientes.get(i).setNome(nome);
        listaClientes.get(i).setCpf(nome);
        listaClientes.get(i).setNumeroTelefone(nome);
        listaClientes.get(i).setEmail(nome);

        Endereco editando = new Endereco(rua, bairro, cep, numeroMoradia);
        listaClientes.get(i).setDadosResidenciais(editando);

        System.out.println(listaClientes.get(i).toString() + " é o dado atualmente salvo.");

        salvarDados();

    }

    public void excluirCliente(int i){

        carregarDados();

        System.out.println("O Func " + listaClientes.get(i) + " foi excluído. ");
        listaClientes.remove(i);

        contadorCli++;

        salvarDados();
    }

 /*   public void ordenar(){
        List<Cliente> listaClienteGenerica = new ArrayList<>();

        for (Cliente listaCliente : listaClientes) {
            listaClienteGenerica.add(listaCliente);
        }
        Collections.sort(listaClienteGenerica);
    }*/

    public void salvarDados() {
        try (Writer writer = new FileWriter(ARQUIVOS_CLIENTES_JSON)) {
            Gson gson = new Gson();
            gson.toJson(listaClientes, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void carregarDados() {
        try {
            String json = new String(Files.readAllBytes(Paths.get(ARQUIVOS_CLIENTES_JSON)));
            Gson gson = new Gson();
            listaClientes = gson.fromJson(json, new TypeToken<ArrayList<Administrador>>(){}.getType());
        } catch (IOException e) {
            e.printStackTrace();
        }
        contadorCli = listaClientes.size();
    }

}
