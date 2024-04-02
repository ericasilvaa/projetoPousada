package Model.Pessoas;

/**
 * Classe concreta que representa um Funcionario; um dos atores do sistema.
 * Ao contrário dos administradores, seus privilégios são limitados.
 * Um Funcionário possui como atributos senha, ID de usuário, contador de usuário estático e privilégios.
 * Herda de Cliente os atributos  nome, CPF, número de telefone, e-mail, dados residenciais
 *  * @author  Erica Silva Rodrigues, Gabriel Moreira Siqueira
 */
public class Funcionario extends Cliente {

    /**
     * Responsável por armazenar o valor da senha de um usuário do tipo Funcionário.
     */
    private String senha;

    private boolean privilegios = false;

    public Funcionario() {
        super();
    }


    public Funcionario(String nome, String cpf, String senha, boolean privilegios) {
        super(nome, cpf);
        this.senha = senha;
        this.privilegios = privilegios;
    }

    public Funcionario(String nome, String cpf, String numeroTelefone, String email, Endereco dadosResidenciais, String senha, boolean privilegios) {
        super(nome, cpf, numeroTelefone, email, dadosResidenciais);
        this.senha = senha;
        this.privilegios = privilegios;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isPrivilegios() {
        return privilegios;
    }

    public void setPrivilegios(boolean privilegios) {
        this.privilegios = privilegios;
    }

    public boolean autenticarFuncionario(String nomeLogin, String cpfLogin, String senhaLogin) {

        FuncionarioGerenciar funcionarioGerenciar = new FuncionarioGerenciar();
        funcionarioGerenciar.carregarDados();
        for (Funcionario funcionario : FuncionarioGerenciar.getListaFuncionario()) {
            if ((funcionario.getNome().equals(nomeLogin) && funcionario.getCpf().equals(cpfLogin) && funcionario.getSenha().equals(senhaLogin))) {
                System.out.println("Você está logado como Funcionário\n");
                return true;
            }
        }
        System.out.println("O usuário informado não está cadastrado. Sugestão: Revise o CPF informado e tente novamente.");
        return false;
    }

    @Override
    public String toString() {
        return "{\"Nome\": \"" + getNome() + "\", " +
                "\"CPF\": \"" + getCpf() + "\", " +
                "\"É administrador?\": \"" + privilegios + "\"" +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Funcionario that = (Funcionario) o;

        if (privilegios != that.privilegios) return false;
        return senha.equals(that.senha);
    }

    @Override
    public int hashCode() {
        int result = senha.hashCode();
        result = 31 * result + (privilegios ? 1 : 0);
        return result;
    }


}