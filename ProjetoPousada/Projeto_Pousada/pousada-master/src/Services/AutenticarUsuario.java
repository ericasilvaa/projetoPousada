package Services;

import Model.Pessoas.Administrador;
import Model.Pessoas.Funcionario;
import View.AdministradorQuemEstaLogado;
import View.FuncionarioQuemEstaLogado;
 import java.util.Scanner;

/**
 * Classe responsável pela autenticação e login de usuários.
 */

public class AutenticarUsuario {

    /**
     * Método que promove a autenticação e login do usuário com base em nome, cpf e senha.
     * Se o login ocorre, invoca o método que mostra as opções disponíveis no sistema para o usuário
     * @param nome O nome do usuário
     * @param cpf O cpf do usuário
     * @param senha O senha do usuário
     */
    public void verificarLogin(String nome, String cpf, String senha){
        Administrador novoAdm = new Administrador();
        Funcionario novoFunc = new Funcionario();

        if(novoAdm.autenticarAdministrador(nome, cpf, senha)){
            logandoComoAdministrador(nome, cpf);
        } else if (novoFunc.autenticarFuncionario(nome, cpf, senha)) {
            logandoComoFuncionario(nome, cpf);
        } else{
            tenteNovamente();
        }
    }

    /**
     * Executado quando o login na figura de um funcionário tem êxito.
     * Imprime mensagem de sucesso e invoca método na view de funcionário que mostra as opções do funcionário
     * @param nome Nome do usuário funcionário
     */
    private static void logandoComoFuncionario(String nome, String cpf) {
        System.out.printf("\nLogando como Funcionário '", nome);
        FuncionarioQuemEstaLogado.funcionarioNaSituacao(nome, cpf);
    }

    /**
     * Executado quando o login na figura de um administrador tem êxito.
     * Imprime mensagem de sucesso e invoca método na view de administrador que mostra as opções do Administrador
     * @param nome Nome do usuário administrador
     */
    private static void logandoComoAdministrador(String nome, String cpf) {
        System.out.println("\nLogando como administrador '" + nome + "'\n");
        AdministradorQuemEstaLogado.admNaSituacao(nome, cpf);
    }

    /**
     * Invoca o método que solicita entrada de dados por parte de usuários
     * e invoca método para verificar se os dados de login estão corretos
     */
    private static void tenteNovamente() {
        dadosTentativaLogin novaEntradaDeDadosDeUsuario = solicitarDados();

        AutenticarUsuario tentarNovamente = new AutenticarUsuario();
        tentarNovamente.verificarLogin(novaEntradaDeDadosDeUsuario.nomeUsuario(), novaEntradaDeDadosDeUsuario.cpfUsuario(), novaEntradaDeDadosDeUsuario.senhaUsuario());
    }

    private static dadosTentativaLogin solicitarDados() {

        System.out.println("\nTente novamente.\n");
        Scanner receba = new Scanner(System.in);

        System.out.println("Por favor, informe o nome de usuário: ");
        String nomeUsuario = receba.nextLine();

        System.out.println("Por favor, informe o CPF de usuário: ");
        String cpfUsuario = receba.nextLine();

        System.out.println("Por favor, informe a senha de usuário: ");
        String senhaUsuario = receba.nextLine();

        return new dadosTentativaLogin(nomeUsuario, cpfUsuario, senhaUsuario);
    }

    /**
     * Representa os dados relativos à autenticação de usuário
     *
     * @param nomeUsuario  O nome do usuário.
     * @param cpfUsuario   O CPF do usuário.
     * @param senhaUsuario A senha do usuário.
     */
    private record dadosTentativaLogin(String nomeUsuario, String cpfUsuario, String senhaUsuario) {
    }
}
