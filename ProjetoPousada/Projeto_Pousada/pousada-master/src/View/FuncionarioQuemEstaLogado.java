package View;

import Controllers.FuncController;
import Controllers.ClienController;
import Controllers.PousadaPackageController;

import java.text.ParseException;
import java.util.*;

public class FuncionarioQuemEstaLogado {
    private static FuncionarioQuemEstaLogado funcionarioQuemEstaLogado = new FuncionarioQuemEstaLogado();
    protected static FuncController chamarFuncController = new FuncController();
    protected static PousadaPackageController chamarPousadaPackageController = new PousadaPackageController();
    protected static ClienController chamarClienController = new ClienController();
    protected static String nomeUser = null;
    protected static String cpfUser = null;
    protected  static boolean exit = false;

    public static void funcionarioNaSituacao(String nomeFuncLogado, String cpfFuncLogado) {

        nomeUser = nomeFuncLogado;
        cpfUser = cpfFuncLogado;
        Scanner scanner = new Scanner(System.in);

        while (!exit) {
            try {
                funcionarioQuemEstaLogado.exibirMenu();
                int opcaoDesejada = funcionarioQuemEstaLogado.solicitarOpcaoDesejada(scanner);
                if (opcaoDesejada < 0 || opcaoDesejada > 13) {
                    System.out.println("Opção inválida!");
                    continue;
                }
                switch (opcaoDesejada) {

                    case 0 -> {
                        exit();
                        return;
                    }
                    case 1, 2, 3, 4 -> {
                        switch (opcaoDesejada) {
                            case 1 -> funcionarioQuemEstaLogado.adicionarFuncionario(scanner);
                            case 2 -> funcionarioQuemEstaLogado.editarFuncionario(scanner);
                            case 3 -> funcionarioQuemEstaLogado.excluirFuncionario(scanner);
                            case 4 -> chamarFuncController.chamarImpressaoFuncionario();
                        }
                    }
                    case 5, 6, 7, 8 -> {
                        switch (opcaoDesejada) {
                            case 5 -> funcionarioQuemEstaLogado.adicionarCliente(scanner);
                            case 6 -> funcionarioQuemEstaLogado.editarCliente(scanner);
                            case 7 -> funcionarioQuemEstaLogado.excluirCliente(scanner);
                            case 8 -> chamarClienController.chamarImprimirCliente();
                        }
                    }
                    case 9, 10, 11, 12, 13 -> {
                        switch (opcaoDesejada) {
                            case 9 -> chamarPousadaPackageController.chamarImprimirQuartos();
                            case 10 -> chamarPousadaPackageController.chamarImprimirReservas();
                            case 11 -> funcionarioQuemEstaLogado.adicionarReserva(scanner);
                            case 12 -> funcionarioQuemEstaLogado.confirmarReserva(scanner);
                            case 13 -> funcionarioQuemEstaLogado.editarUsuarioLogado(nomeUser, cpfUser);
                        }
                    }

                }
                } catch(InputMismatchException e){
                    System.out.println("Entrada Inválida!");
                } catch (ParseException e) {
                throw new RuntimeException(e);
            }

        }
        }

    protected void confirmarReserva(Scanner scanner) {
        int indice = Integer.parseInt(SolicitarIndiceReserva(scanner));
        chamarPousadaPackageController.chamarConfirmarReserva(indice);
    }

    protected static String SolicitarIndiceReserva(Scanner scanner) {
        System.out.println("As reservas abaixo estão há mais de 30 dias distantes e podem ser confirmadas: ");
        chamarPousadaPackageController.chamarImprimirReservasComAte30DiasDeHoje();

        System.out.println("Escolha a reserva a ser confirmada: ");
        String numeroReserva  = scanner.nextLine();
        return numeroReserva;
    }

    protected void adicionarReserva(Scanner scanner) throws ParseException {

        System.out.println("Vamos criar uma reserva.");

        String stringDeEntrada = solicitarDataEntradaReserva(scanner);
        String stringDeSaida = solicitarDataSaidaReserva(scanner);
        int quartoEscolhido = Integer.parseInt(solicitarEscolhaDoQuarto(scanner));
        int idCliente = solicitarObterIdCliente(scanner);
        String observacoes = solicitarDescricaoObservacoesReserva(scanner);

        chamarPousadaPackageController.chamarCadastroNovaReserva(
                stringDeEntrada, stringDeSaida, quartoEscolhido, idCliente, observacoes);
    }

    protected static String solicitarDescricaoObservacoesReserva(Scanner scanner) {
        System.out.println("Forneça uma descrição adicional com observações sobre a reserva: ");
        String observacoes = scanner.nextLine();
        return observacoes;
    }

    protected static String solicitarDataSaidaReserva(Scanner scanner) {
        // Solicitar data de saida
        System.out.println("Informe a data de saída na Pousada");
        String stringDeSaida = solicitarData(scanner);
        return stringDeSaida;
    }

    protected static String solicitarDataEntradaReserva(Scanner scanner) {
        // Solicitar data de entrada
        System.out.println("Informe a data de entrada na Pousada");
        String stringDeEntrada = solicitarData(scanner);
        return stringDeEntrada;
    }

    /**
     * Exibe o menu de opções de casos de uso disponíveis ao Funcionário
     */
    protected void exibirMenu() {
        System.out.println("""
                -------------- Menu --------------------
                | (1) - Cadastrar Funcionário
                | (2) - Editar Funcionário
                | (3) - Excluir Funcionários
                | (4) - Imprimir Funcionários
                | (5) - Cadastrar Cliente
                | (6) - Editar Cliente
                | (7) - Remover Cliente
                | (8) - Imprimir Clientes
                | (9) - Imprimir Status dos Quartos
                | (10) -Imprimir Todas as Reservas
                | (11) -Cadastrar Reserva
                | (12) -Confirmar Reserva
                | (13) -Editar Usuario Logado
                | (0) - Encerrar Programa
                ----------------------------------------
                Digite a opção desejada:
                """);
    }

    /**
     *
     * @param scanner Objeto utilizado para se obter entrada do usuário
     * @return Retorna o número que reflete a opção escolhida pelo usuário
     */
    protected int solicitarOpcaoDesejada(Scanner scanner) {
        int opcaoDesejada = 0;
        opcaoDesejada = scanner.nextInt();
        scanner.nextLine();
        return opcaoDesejada;
    }

    /**
     * Solicita ao usuário que informe o nome do Funcionário
     * @param scanner Objeto utilizado para se obter entrada do usuário
     * @return Retorna o nome informado do Cliente
     */
    protected String solicitarNome(Scanner scanner) {
        System.out.println("Digite o nome do Usuário: ");
        String nome = scanner.nextLine().trim();
        return nome;
    }

    /**
     * Solicita ao usuário que informe o CPF do Funcionário
     * @param scanner Objeto utilizado para se obter entrada do usuário
     * @return Retorna o CPF informado do Cliente
     */
    protected String solicitarCpf(Scanner scanner) {
        System.out.println("Digite o CPF do Usuário. Deve conter 11 dígitos: ");
        String cpf = scanner.nextLine().trim();
        return cpf;
    }

    /**
     * Solicita ao usuário que informe a senha do Funcionário
     * @param scanner Objeto utilizado para se obter entrada do usuário
     * @return Retorna a senha informado do Cliente
     */
    protected String solicitarSenha(Scanner scanner) {
        System.out.println("Digite a senha do Usuário. Mínimo de 6 dígitos:  ");
        String senha = scanner.nextLine().trim();
        return senha;
    }

    /**
     * Imprime e solicita o ID do funcionário
     * @param scanner Objeto utilizado para se obter entrada do usuário
     * @return Retorna o ID informado
     */
    protected static int solicitarIdDoFuncionario(Scanner scanner) {
        chamarFuncController.chamarImpressaoFuncionario();
        System.out.println("Informe o ID do funcionário: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        return id;
    }

    /**
     * Imprime e solicita o ID do Cliente
     * @param scanner Objeto utilizado para se obter entrada do usuário
     * @return Retorna o ID informado
     */
    protected static int solicitarIdDoCliente(Scanner scanner) {
        chamarClienController.chamarImprimirCliente();
        System.out.println("Informe o ID do Cliente: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        return id;
    }

    /**
     * Solicita entrada de dados referentes ao Funcionário e faz requisição de adicionar funcionário ao Controller
     * @param scanner Objeto utilizado para se obter entrada do usuário
     */
    protected void adicionarFuncionario(Scanner scanner) {
        String nome = solicitarNome(scanner);
        String cpf = solicitarCpf(scanner);
        String senha = solicitarSenha(scanner);
        chamarFuncController.chamarCadastroFuncionario(nome, cpf, senha);
    }

    /**
     * Solicita entrada de dados referentes ao Funcionário e faz requisição de editar funcionário ao Controller
     * @param scanner Objeto utilizado para se obter entrada do usuário
     */
    protected void editarFuncionario(Scanner scanner){
        int editar = solicitarIdDoFuncionario(scanner);
        String novoNome = funcionarioQuemEstaLogado.solicitarNome(scanner);
        String novoCpf = funcionarioQuemEstaLogado.solicitarCpf(scanner);
        String novaSenha = funcionarioQuemEstaLogado.solicitarSenha(scanner);
        chamarFuncController.chamarEditarFuncionario(editar, novoNome, novoCpf, novaSenha);
    }

    protected void editarUsuarioLogado(String nomeUser, String cpfUser){

        String nome = solicitarNome(new Scanner(System.in));
        String cpf = solicitarCpf(new Scanner(System.in));
        String senha = solicitarSenha(new Scanner(System.in));

        chamarFuncController.chamarEditarFuncionario(nome, cpf, senha);

    }

    /**
     * Solicita entrada de dados referentes ao ID do Funcionário e faz requisição de o excluir ao Controller
     * @param scanner Objeto utilizado para se obter entrada do usuário
     */
    protected void excluirFuncionario(Scanner scanner){
        int excluir = solicitarIdDoFuncionario(scanner);
        chamarFuncController.chamarExcluirFuncionario(excluir);
    }

    /**
     * Solicita entrada de dados referentes ao Cliente e faz requisição de adicionar Cliente ao Controller
     * @param scanner Objeto utilizado para se obter entrada do usuário
     */
    protected void adicionarCliente(Scanner scanner){
        solicitarDadosDoCliente dadosCliente = getSolicitarDadosDoCliente(scanner);
        chamarClienController.chamarCadastroCliente(dadosCliente.nome(), dadosCliente.cpf(), dadosCliente.numeroTelefone(), dadosCliente.email(), dadosCliente.rua(), dadosCliente.bairro(), dadosCliente.cep(), dadosCliente.numeroMoradia());
    }

    /**
     * Solicita entrada de dados referentes ao cliente e faz requisição de editar funcionário ao Controller
     * @param scanner Objeto utilizado para se obter entrada do usuário
     */
    protected void editarCliente(Scanner scanner){
        int editar = solicitarIdDoCliente(scanner);
        solicitarDadosDoCliente dadosCliente = getSolicitarDadosDoCliente(scanner);
        chamarClienController.chamarEditarCliente(editar, dadosCliente.nome(), dadosCliente.cpf(), dadosCliente.numeroTelefone(), dadosCliente.email(), dadosCliente.rua(), dadosCliente.bairro(), dadosCliente.cep(), dadosCliente.numeroMoradia());
    }

    /**
     * Solicita entrada de dados referentes ao ID do cliente e faz requisição de o excluir ao Controller
     * @param scanner Objeto utilizado para se obter entrada do usuário
     */
    protected void excluirCliente(Scanner scanner){
        int excluir = solicitarIdDoCliente(scanner);
        chamarClienController.chamarExcluirCliente(excluir);
    }

    /**
     * Solicita os dados referentes a Cliente
     * @param scanner Objeto utilizado para se obter entrada do usuário
     * @return Retorna objeto `solicitarDadosDoCliente`, que contém os dados do Cliente
     */
    protected static solicitarDadosDoCliente getSolicitarDadosDoCliente(Scanner scanner) {

        System.out.println("Digite o nome do Cliente: ");
        String nome = scanner.nextLine().trim();

        System.out.println("Digite o cpf do Cliente: ");
        String cpf = scanner.nextLine().trim();

        System.out.println("Digite o numeroTelefone do Cliente: ");
        String numeroTelefone = scanner.nextLine().trim();

        System.out.println("Digite o email do Cliente: ");
        String email = scanner.nextLine().trim();

        System.out.println("Digite a rua do Cliente: ");
        String rua = scanner.nextLine().trim();

        System.out.println("Digite o bairro do Cliente: ");
        String bairro = scanner.nextLine().trim();

        System.out.println("Digite o cep do Cliente: ");
        String cep = scanner.nextLine().trim();

        System.out.println("Digite o número da moradia do Cliente: ");
        String numeroMoradia = scanner.nextLine().trim();

        return new solicitarDadosDoCliente(nome, cpf, numeroTelefone, email, rua, bairro, cep, numeroMoradia);
    }

    /**
     * Classe que representa os dados de um cliente.
     *
     * @param nome           O nome do cliente.
     * @param cpf            O CPF do cliente.
     * @param numeroTelefone O número de telefone do cliente.
     * @param email          O email do cliente.
     * @param rua            A rua do endereço do cliente.
     * @param bairro         O bairro do endereço do cliente.
     * @param cep            O CEP do endereço do cliente.
     * @param numeroMoradia  O número da moradia do cliente.
     */
    protected record solicitarDadosDoCliente(String nome, String cpf, String numeroTelefone, String email, String rua, String bairro, String cep, String numeroMoradia) {
    }

    /**
     * Método que finaliza a execução do programa
     * @return Retorna a variável exit como true, dando fim ao loop que exibe as opções do programa.
     */
    protected static boolean exit() {
        return exit = true;
    }

    protected static String solicitarData(Scanner scanner) {
        System.out.println("""
                Data (formato dd/MM/yyyy): 
                """);
        String data = scanner.nextLine();
        return data;
    }


    protected static String solicitarEscolhaDoQuarto(Scanner scanner) {
        System.out.println("Informe o quarto escolhido: ");
        chamarPousadaPackageController.chamarImprimirQuartos();
        String numeroQuarto = scanner.nextLine();
        return numeroQuarto;
    }

    protected static int solicitarObterIdCliente(Scanner scanner) {
        ClienController clienController = new ClienController();
        clienController.chamarImprimirCliente();
        System.out.println("Selecione o cliente: ");
        int idCliente = scanner.nextInt();
        scanner.nextLine();
        return idCliente;
    }


}
