package View;

import Controllers.AdmController;
import static View.FuncionarioQuemEstaLogado.chamarFuncController;
import static View.FuncionarioQuemEstaLogado.chamarPousadaPackageController;
import static View.FuncionarioQuemEstaLogado.exit;

import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class AdministradorQuemEstaLogado extends FuncionarioQuemEstaLogado {
    private static final AdministradorQuemEstaLogado administradorQuemEstaLogado = new AdministradorQuemEstaLogado();
    private static final AdmController chamarAdmController = new AdmController();

    public static void admNaSituacao(String nomeAdmLogado, String cpfAdmLogado) {

        Scanner scanner = new Scanner(System.in);

        while (!exit) {
            try {

                administradorQuemEstaLogado.exibirMenu();
                int opcaoDesejada = administradorQuemEstaLogado.solicitarOpcaoDesejada(scanner);

                switch (opcaoDesejada) {
                    case 0 -> {
                        exit();
                        return;
                    }
                        case 1 -> {
                            administradorQuemEstaLogado.adicionarAdministrador(scanner);
                        }
                        case 2 -> {
                            chamarAdmController.chamarImpressaoAdministradores();
                        }

                        case 3 -> {
                            administradorQuemEstaLogado.editarAdministrador(scanner);
                        }

                        case 4 -> {
                            administradorQuemEstaLogado.excluirAdministrador(scanner);
                        }

                        case 5 -> {
                            administradorQuemEstaLogado.adicionarFuncionario(scanner);
                        }

                        case 6 -> {
                            administradorQuemEstaLogado.editarFuncionario(scanner);
                        }

                        case 7 -> {
                            administradorQuemEstaLogado.excluirFuncionario(scanner);
                        }

                        case 8 -> {
                            chamarFuncController.chamarImpressaoFuncionario();
                        }

                        case 9 -> {
                            administradorQuemEstaLogado.adicionarCliente(scanner);
                        }

                        case 10 -> {
                            administradorQuemEstaLogado.editarCliente(scanner);
                        }

                        case 11 -> {
                            administradorQuemEstaLogado.excluirCliente(scanner);
                        }

                        case 12 -> {
                            chamarPousadaPackageController.chamarImprimirQuartos();
                        }

                        case 13 -> {
                            chamarPousadaPackageController.chamarImprimirReservas();
                        }
                        case 14 -> {
                            administradorQuemEstaLogado.adicionarReserva(scanner);
                        }
                        case 15 -> {
                            administradorQuemEstaLogado.confirmarReserva(scanner);
                    }

                        case 16 -> {
                            chamarPousadaPackageController.chamarImprimirReservasComAte30DiasDeHoje();
                        }
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada Inválida!");
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void exibirMenu() {
        System.out.println("""
                \n\n-------------- Menu --------------------
                | (0) - Encerrar Programa
                | (1) - Cadastrar Novo Administrador
                | (2) - Imprimir Administradores
                | (3) - Editar Administrador
                | (4) - Excluir Administrador
                | (5) - Cadastrar Funcionário
                | (6) - Editar Funcionário
                | (7) - Excluir Funcionários
                | (8) - Imprimir Funcionários
                | (9) - Cadastrar Cliente
                | (10) - Editar Cliente
                | (11) - Remover Cliente
                | (12) - Imprimir Status dos Quartos
                | (13) -Imprimir Todas as Reservas
                | (14) -Cadastrar Reserva
                | (15) -Confirmar Reserva
                | (16) -Imprimir Reservas que podem ser confirmadas
                ----------------------------------------
                Digite a opção desejada:
                """);
    }

    /**
     * Solicita entrada de dados referentes ao Administrador e faz requisição de adicionar ao Administrador Controller
     * @param scanner Objeto utilizado para se obter entrada do usuário
     */
    private void adicionarAdministrador(Scanner scanner) {
        String nome = solicitarNome(scanner);
        String cpf = solicitarCpf(scanner);
        String senha = solicitarSenha(scanner);
        chamarAdmController.chamarCadastroAdministrador(nome, cpf, senha);
    }

    /**
     * Solicita entrada de dados referentes ao Funcionário e faz requisição de editar funcionário ao Controller
     * @param scanner Objeto utilizado para se obter entrada do usuário
     */
    private void editarAdministrador(Scanner scanner){
        int idDoAdministrador = solicitarIdDoAdministrador(scanner);
        String novoNome = administradorQuemEstaLogado.solicitarNome(scanner);
        String novoCpf = administradorQuemEstaLogado.solicitarCpf(scanner);
        String novaSenha = administradorQuemEstaLogado.solicitarSenha(scanner);

        chamarAdmController.chamarEditarAdministrador(idDoAdministrador, novoNome, novoCpf, novaSenha);
    }

    /**
     * Imprime e solicita o ID do Administrador
     * @param scanner Objeto utilizado para se obter entrada do usuário
     * @return Retorna o ID informado
     */
    private static int solicitarIdDoAdministrador(Scanner scanner) {
        chamarAdmController.chamarImpressaoAdministradores();
        System.out.println("Informe o ID do Administrador: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        return id;
    }

    /**
     * Solicita entrada de dados referentes ao ID do Administrador e faz requisição de o excluir ao Controller
     * @param scanner Objeto utilizado para se obter entrada do usuário
     */
    private void excluirAdministrador(Scanner scanner){
        int excluir = solicitarIdDoAdministrador(scanner);
        chamarAdmController.chamarExcluirAdministrador(excluir);
    }

}
