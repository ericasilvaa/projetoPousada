package Model.Pessoas;

import Model.Pessoas.AdministradorGerenciar;
import Model.Pessoas.Endereco;
import Model.Pessoas.Funcionario;

/**
 * Esta classe representa um Administrador em uma pousada.
 * Ela é uma subclasse de Funcionario, pois herda seus atributos e comportamentos.
 * Um Administrador é responsável por funções administrativas e possui privilégios especiais.
 * Ele pode ser autenticado para acessar determinadas operações do sistema.
 * Além disso, o Administrador também possui um toString personalizado para facilitar a exibição dos seus atributos.
 * 
@author  Erica Silva Rodrigues, Gabriel Moreira Siqueira
**/

public class Administrador extends Funcionario {

    public Administrador() {
        super();
    }

    public Administrador(String nome, String cpf, String senha, boolean privilegios) {
        super(nome, cpf, senha, privilegios);
    }

    public Administrador(String nome, String cpf, String numeroTelefone, String email, Endereco dadosResidenciais, String senha, boolean privilegios) {
        super(nome, cpf, numeroTelefone, email, dadosResidenciais, senha, privilegios);
    }
    public boolean autenticarAdministrador(String nomeLogin, String cpfLogin, String senhaLogin) {

        AdministradorGerenciar administradorGerenciar = new AdministradorGerenciar();
        administradorGerenciar.carregarDados();
        for (Administrador value : AdministradorGerenciar.getListaAdministrador()) {

            if (
                    (value.getNome().equals(nomeLogin) && value.getCpf().equals(cpfLogin) && value.getSenha().equals(senhaLogin))
            ) {
                System.out.println("Você está logado como Administrador\n");
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "{\"Nome\": \"" + getNome() + "\", " +
                "\"CPF\": \"" + getCpf() + "\", " +
                "\"É administrador?\": \"" + isPrivilegios() + "\"" +
                "}";
    }
}
