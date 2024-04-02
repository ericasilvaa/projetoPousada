import java.util.Scanner;
import Services.AutenticarUsuario;

/**
 * Esse programa visa implementar um sistema orientado a objetos que busca abstrair
 * e implementar a lógica básica de negócios de uma Pousada, segundo os critérios avaliativos
 * e como exigência para aprovação da disciplina de Programação Orientada a Objetos
 *
 * @author  Erica Silva Rodrigues, Gabriel Moreira Siqueira
 */

public class Main {
    public static void main(String[] args)  {
        Scanner receba = new Scanner(System.in);

            System.out.println("Por favor, informe o nome de usuário: ");
            String nomeUsuario = receba.nextLine();

            System.out.println("Por favor, informe o CPF de usuário: ");
            String cpfUsuario = receba.nextLine();

            System.out.println("Por favor, informe a senha de usuário: ");
            String senhaUsuario = receba.nextLine();

            AutenticarUsuario logar = new AutenticarUsuario();
            logar.verificarLogin(nomeUsuario, cpfUsuario, senhaUsuario);

        receba.close();

    }
}