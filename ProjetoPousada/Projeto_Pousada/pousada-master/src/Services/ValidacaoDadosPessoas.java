package Services;

import Model.Pessoas.AdministradorGerenciar;
import Model.Pessoas.ClienteGerenciar;
import Model.Pessoas.FuncionarioGerenciar;

import static Model.Pessoas.ClienteGerenciar.getContadorCli;



/**
 * /**
 * Classe responsável por realizar a validação dos dados de pessoas (Administrador, Funcionário e Cliente).
 */
 

public class ValidacaoDadosPessoas {

    public static boolean tentarValidarDados(String nome, String cpf, String senha) { // para Adm

        // se validacaoInvalida == true, retorne tentarValidarDados == false
        if (!validacaoInvalida(nome, cpf, senha)) return false;

        // se nao, validação ok
        return true;
    }

    public static boolean tentarValidarDados(FuncionarioGerenciar.DadosFuncionario dadosFuncionario) { // para Fun
        String nome = dadosFuncionario.nome();
        String cpf = dadosFuncionario.cpf();
        String senha = dadosFuncionario.senha();

        if (validacaoInvalida(nome, cpf, senha)) return false;

        return true;
    }

    private static boolean validacaoInvalida(String nome, String cpf, String senha) {
        if (verificarCamposVaziosDoUsuario(nome, cpf, senha)) {
            System.out.println("Error: verificarCamposVaziosDoUsuario");
            return true;
        }
        if (verificarTamanhoDoCpf(cpf)) {
            System.out.println("Error: verificarTamanhoDoCpf");
            return true;
        }
        if (verificarTamanhoSenha(senha)) {
            System.out.println("Error: verificarTamanhoSenha");
            return true;
        }
        if (verificarSeDadosSaoNumericos(cpf)) {
            System.out.println("Error: verificarSeDadosSaoNumericos");
            return true;
        }

        return false;
    }

    public static boolean seCpfEhRepetido(String cpf) {
        if (verificarSeCpfEhRepitidoAdministrador(cpf)) {
            System.out.println("Error: verificarSeCpfEhRepitidoAdministrador");
            return true;
        }
        return false;
    }

    public static boolean tentarValidarDados(String nome, String cpf, String numeroTelefone, String email,
                                             String rua, String bairro, String cep, String numeroMoradia) {

        if (verificarCamposVaziosDoUsuario(nome, cpf, numeroTelefone, email, rua, bairro, cep, numeroMoradia))
            return false;
        if (verificarSeTamanhosSaoAdequados(cpf, cep, numeroTelefone)) return false;
        if (verificarSeDadosSaoNumericos(cpf, cep, numeroTelefone, numeroMoradia)) return false;
        if (verificarSeCpfEhRepitidoCliente(cpf)) return false;

        return true;
    }

    public static boolean verificarSeIdAdmEhValido(int i){
        if (i >= 0 && i < AdministradorGerenciar.getListaAdministrador().size()) {
            return true;
        } else {
            System.out.println("\nPor favor, note que o ID informado não é válido. \n");
            return false;
        }
    }


    public static boolean verificarSeIdFuncEhValido(int i){
        if((i > FuncionarioGerenciar.getContadorFunc()) || i < 0){
            System.out.println("\nPor favor, note que o ID informado não é válido. \n");
            return false;
        }
        return true;
    }

    public static boolean verificarSeIdClienteEhValido(int i){
        if((i > getContadorCli()) || i < 0){
            System.out.println("\nPor favor, note que o ID informado não é válido. \n");
            return false;
        }
        return true;
    }

    private static boolean verificarCamposVaziosDoUsuario(String nome, String cpf, String numeroTelefone,
                                                          String email, String rua, String bairro, String cep,
                                                          String numeroMoradia) {

        if (        nome.isEmpty() || cpf.isEmpty() || numeroTelefone.isEmpty() || email.isEmpty()
                ||  rua.isEmpty() || bairro.isEmpty() || cep.isEmpty() || numeroMoradia.isEmpty()) {
            System.out.println("Erro ao cadastrar: os campos não devem estar vazios");
            return false;
        }
        return true;
    }

    /**
     * Verifica se parâmetros não estão vazios (sem caracteres) ou consistem em espaços em branco.
     *
     * @param nome  O nome da entidade Cliente
     * @param cpf   O CPF da entidade Cliente
     * @param senha A senha da entidade Cliente
     * @return {@code true} se houver campos vazio, {@code false} caso contrário.
     */
    private static boolean verificarCamposVaziosDoUsuario(String nome, String cpf, String senha) {
        if (nome.isEmpty()) {
            System.out.println("Erro ao cadastrar: o nome de usuário não deve estar vazio");
            return true;
        }
        if (cpf.isEmpty()) {
            System.out.println("Erro ao cadastrar: o CPF de usuário não deve estar vazio");
            return true;
        }
        if (senha.isEmpty()) {
            System.out.println("Erro ao cadastrar: a senha de usuário não deve estar vazio");
            return true;
        }
        return false;
    }

    /**
     * Verifica se o CPF já existe na lista de Administradores
     *
     * @param cpf O CPF do Administrador
     * @return {@code true} se o CPF já existir, {@code false} caso contrário.
     */
    private static boolean verificarSeCpfEhRepitidoAdministrador(String cpf) {
        for (int i = 0; i < AdministradorGerenciar.getListaAdministrador().size(); i++) {
            if (AdministradorGerenciar.getListaAdministrador().get(i).getCpf().equals(cpf)) {
                System.out.println("O CPF informado já está cadastrado. ");
                return true;
            }
        }
        return false;
    }

    private static boolean verificarSeCpfEhRepitidoCliente(String cpf) {
        for (int i = 0; i < ClienteGerenciar.getListaClientes().size(); i++) {
            if (ClienteGerenciar.getListaClientes().get(i).getCpf().equals(cpf)) {
                System.out.println("O CPF do Cliente informado já está cadastrado. ");
                return false;
            }
        }
        return true;
    }


    /**
     * Verifica se a senha informada tem ao menos 6 dígitos
     *
     * @param senha A senha do Administrador
     * @return {@code true} a senha for menor que 6 dígitos, {@code false} caso contrário.
     */
    private static boolean verificarTamanhoSenha(String senha) {
        if (senha.length() < 6) {
            System.out.println("Erro ao cadastrar: A senha deve possuir pelo menos 6 caracteres");
            return true;
        }
        return false;
    }

    /**
     * Verifica se o CPF informado tem ao menos 11 dígitos
     *
     * @param cpf O CPF do cliente
     * @return {@code true} o CPF for menor que 11 dígitos, {@code false} caso contrário.
     */
    private static boolean verificarTamanhoDoCpf(String cpf) {
        if (cpf.length() != 11) {
            System.out.println("Erro ao cadastrar: Um CPF contém 11 dígitos");
            return true;
        }
        return false;
    }

    private static boolean verificarSeTamanhosSaoAdequados(String cpf, String cep, String numeroTelefone) {

        verificarTamanhoDoCpf(cpf);

        if (cep.length() != 8) {
            System.out.println("O CEP possui 8 dígitos");
            return false;
        }

        if (numeroTelefone.length() > 9 || numeroTelefone.length() < 8) {
            System.out.println("Um telefone possui 8 ou 9 dígitos");
            return false;
        }
        return true;
    }

    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean verificarSeDadosSaoNumericos(String cpf, String cep, String numeroTelefone,
                                                        String numeroMoradia) {

        if (!cpf.matches("[0-9]+")) {
            System.out.println("Cpf não numérico");
            return false;
        }

        if (!isNumeric(cep)) {
            System.out.println("O CEP deve conter apenas valores numéricos");
            return false;
        }

        if (!isNumeric(numeroTelefone)) {
            System.out.println("O número de telefone deve conter apenas valores numéricos");
            return false;
        }

        if (!isNumeric(numeroMoradia)) {
            System.out.println("O número da moradia deve conter apenas valores numéricos");
            return false;
        }
        return true;
    }

    private static boolean verificarSeDadosSaoNumericos(String cpf) {
        if (!cpf.matches("[0-9]+")) {
            System.out.println("O CPF deve conter apenas dígitos");
            return true;
        }
        return false;
    }

}

