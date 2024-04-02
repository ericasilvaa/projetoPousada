package Model.Pessoas;

import Services.ValidacaoDadosPessoas;
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
 * Classe responsável por gerenciar os objetos do tipo Funcionário.
 * * @author  Erica Silva Rodrigues, Gabriel Moreira Siqueira

 */

public class FuncionarioGerenciar {

    private static ArrayList<Funcionario> listaFuncionario = new ArrayList<>();
    private static int contadorFunc;
    private static final String ARQUIVOS_FUNCIONARIOS_JSON = "src/arquivos/funcionarios.json";

    public FuncionarioGerenciar() {
        carregarDados();
    }

    public record DadosFuncionario(String nome, String cpf, String senha) {}

    public static ArrayList<Funcionario> getListaFuncionario() {
        return listaFuncionario;
    }

    public static void setListaFuncionario(ArrayList<Funcionario> listaFuncionario) {
        FuncionarioGerenciar.listaFuncionario = listaFuncionario;
    }

    public static int getContadorFunc() {
        return contadorFunc;
    }

    public void cadastroFuncionario(String nome, String cpf, String senha){

        Funcionario novoFunc = new Funcionario(nome, cpf, senha, false);
        boolean cadastroOcorreu = listaFuncionario.add(novoFunc);

        System.out.println("O cadastro de " + novoFunc.getNome() + " ocorreu? " + cadastroOcorreu);

        if(cadastroOcorreu){
            salvarDados();
            contadorFunc = contadorFunc + 1;
        }
    }

    public void editarFuncionario(int i, String novoNome, String novoCpf, String novaSenha){

        DadosFuncionario dadosFuncionario = new DadosFuncionario(novoNome, novoCpf, novaSenha);
        //ValidacaoDados validar = new ValidacaoDados();
        if (!ValidacaoDadosPessoas.tentarValidarDados(dadosFuncionario)) {
            return;
        }

        System.out.println("Selecionado para editar: " + listaFuncionario.get(i).toString()); // cuidado: isso antes das verificações pode quebrar o programa, já que uso i sem ter verificado se tá de acordo com os índices do arrayl
        listaFuncionario.get(i).setNome(novoNome);
        listaFuncionario.get(i).setCpf(novoCpf);
        listaFuncionario.get(i).setSenha(novaSenha);

        System.out.println("Dado atual: " + listaFuncionario.get(i).toString());
        salvarDados();
    }

    public void editarFuncionario(String nome, String cpf, String senha){


        for (Funcionario funcionario : listaFuncionario) {
            if(funcionario.getCpf().equals(cpf)){
                funcionario.setNome(nome);
                funcionario.setCpf(cpf);
                funcionario.setSenha(senha);
            }
        }

    }

    public boolean excluirFuncionario(int i){

        System.out.println("O Func " + listaFuncionario.get(i) + " foi excluído. ");
        listaFuncionario.remove(i);

        contadorFunc = contadorFunc - 1;
        salvarDados();
        return true;
    }

    public void imprimirFuncionario(){
        for (int i = 0; i < listaFuncionario.size(); i++) {
            System.out.println("ID: " + i + " - " + listaFuncionario.get(i) );
        }
    }

    public void salvarDados() {
        try (Writer writer = new FileWriter(ARQUIVOS_FUNCIONARIOS_JSON)) {
            Gson gson = new Gson();
            gson.toJson(listaFuncionario, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void carregarDados() {
        try {
            String json = new String(Files.readAllBytes(Paths.get(ARQUIVOS_FUNCIONARIOS_JSON)));
            Gson gson = new Gson();
            listaFuncionario = gson.fromJson(json, new TypeToken<ArrayList<Funcionario>>(){}.getType());
            contadorFunc = listaFuncionario.size();
        } catch (IOException e) {
            e.printStackTrace();
        }
        contadorFunc = listaFuncionario.size();
    }
    }




