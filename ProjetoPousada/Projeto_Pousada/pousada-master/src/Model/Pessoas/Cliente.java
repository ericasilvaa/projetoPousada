package Model.Pessoas;


import java.util.Collections;

/**
 * Classe concreta que representa a entidade Cliente.
 * Um Cliente possui nome, CPF, número de telefone, e-mail e dados residenciais.
 * *
 * @author  Erica Silva Rodrigues, Gabriel Moreira Siqueira
 */
 
public class Cliente {

    /**
     * Atributo que corresponde ao nome (alcunha) do Cliente.
     */
    private String nome;

    /**
     * Número do Cadastro de Pessoa Física do Cliente.
     */
    private String cpf;

    /**
     * Número de telefone celular do Cliente.
     */
    private String numeroTelefone;

    /**
     * Endereço de e-mail do Cliente.
     */
    private String email;

    /**
     * Objeto que representa o endereço do Cliente.
     */
    private Endereco dadosResidenciais;

    /**
     * Construtor padrão da classe Cliente
     */
    public Cliente() {
    }

    /**
     * Construtor de Cliente com os parâmetros mais utilizados pelas suas subclasses.
     * @param nome O nome do Cliente
     * @param cpf O CPF do Cliente
     */
    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.dadosResidenciais = new Endereco();
    }

    /**
     * Construtor com todos os argumentos da classe Cliente
     * @param nome O nome do Cliente
     * @param cpf O CPF do Cliente
     * @param email O email do Cliente
     * @param dadosResidenciais Dados residenciais do Cliente
     */
    public Cliente(String nome, String cpf, String numeroTelefone, String email, Endereco dadosResidenciais) {
        this.nome = nome;
        this.cpf = cpf;
        this.numeroTelefone = numeroTelefone;
        this.email = email;
        this.dadosResidenciais = new Endereco();
    }

    /**
     * Método que obtém/consulta o nome do Cliente
     * @return Retonar a consulta ao nome atual do referido Cliente
     */
    public String getNome() {
        return nome;
    }

    /**
     * Método que define o nome do Cliente
     * @param nome O nome do Cliente
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Método que obtém/consulta o CPF do Cliente
     * @return Retonar a consulta ao CPF atual do referido Cliente
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Método que define o CPF do Cliente
     * @param cpf O CPF do Cliente
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Método que obtém/consulta o número de telefone do Cliente
     * @return Retonar a consulta ao número de telefone do Cliente
     */
    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    /**
     * Método que define o CPF do Cliente
     * @param numeroTelefone O número de telefone do Cliente
     */
    public void setNumeroTelefone(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    /**
     * Método que obtém/consulta o e-mail do Cliente
     * @return Retonar a consulta ao e-mail do Cliente
     */
    public String getEmail() {
        return email;
    }

    /**
     * Método que define o email do Cliente
     * @param email O email do Cliente
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Método que obtém/consulta os Dados Residenciais do Cliente
     * @return Retonar a consulta as Dados Residenciais do Cliente
     */
    public Endereco getDadosResidenciais() {
        return dadosResidenciais;
    }

    /**
     * Método que define o email do Cliente
     * @param dadosResidenciais Os dados residenciais do Cliente
     */
    public void setDadosResidenciais(Endereco dadosResidenciais) {
        this.dadosResidenciais = dadosResidenciais;
    }

    /**
     * Provê uma representação em String de um Objeto do tipo Cliente
     * @return Representação em String do Cliente
     */

    @Override
    public String toString() {
        ClienteGerenciar clienteGerenciar = new ClienteGerenciar();
        clienteGerenciar.carregarDados();
        String dadosResidenciaisString = (dadosResidenciais != null) ? dadosResidenciais.toString() : "Nenhum endereço";
        return "{\"Nome\": \"" + nome + "\", " +
                "\"CPF\": \"" + cpf + "\", " +
                "\"Número de Telefone\": \"" + numeroTelefone + "\", " +
                "\"E-mail\": \"" + email + "\", " +
                "\"Dados Residenciais\": " + dadosResidenciaisString +
                "}";
    }


}