package Controllers;

import Model.Pessoas.ClienteGerenciar;
import Services.ValidacaoDadosPessoas;
import static Services.ValidacaoDadosPessoas.tentarValidarDados;

/**
 * Esta classe representa o controller para o gerenciamento de clientes.
 * Ela fornece métodos para realizar cadastro, edição, exclusão e impressão de clientes.
 * Os dados dos clientes são gerenciados pela classe ClienteGerenciar.
 * 
 *  @author Erica Silva Rodrigues e Gabriel Moreira Siqueira
 
 */


public class ClienController {

    ClienteGerenciar cliente = new ClienteGerenciar();

    
    /**
     * Método para chamar o cadastro de um novo cliente.
     * Verifica se os dados são válidos antes de realizar o cadastro.
     * 
     * @param nome  
     * @param cpf  
     * @param numeroTelefone  
     * @param email  
     * @param rua  
     * @param bairro  
     * @param cep  
     * @param numeroMoradia  
     */
    public void chamarCadastroCliente(String nome, String cpf, String numeroTelefone, String email, String rua, String bairro, String cep, String numeroMoradia) {

        if ((tentarValidarDados(nome, cpf, numeroTelefone, email, rua, bairro, cep, numeroMoradia))) {
            return;
        }

        cliente.cadastroCliente(nome, cpf, numeroTelefone, email, rua, bairro, cep, numeroMoradia);
    }
 /**
     * Método para chamar a impressão de todos os clientes cadastrados.
     */
    public void chamarImprimirCliente() {
        cliente.imprimirClientes();
    }

     /**
     * Método para chamar a edição de um cliente existente.
     * Verifica se os dados são válidos antes de realizar a edição.
     * 
     * @param nome  
     * @param cpf  
     * @param numeroTelefone  
     * @param email  
     * @param rua  
     * @param bairro  
     * @param cep  
     * @param numeroMoradia  
     */
    public void chamarEditarCliente(int i, String nome, String cpf, String numeroTelefone, String email, String rua, String bairro, String cep, String numeroMoradia) {

        ClienteGerenciar.getListaClientes().get(i).setCpf(null);
        if (!ValidacaoDadosPessoas.verificarSeIdClienteEhValido(i)) {
            return;
        }

        if ((tentarValidarDados(nome, cpf, numeroTelefone, email, rua, bairro, cep, numeroMoradia))) {
            return;
        }

        cliente.editarCliente(i, nome, cpf, numeroTelefone, email, rua, bairro, cep, numeroMoradia);
    }

    /**
     * Método para chamar a exclusão de um cliente existente.
     * 
     * @param i  
     */
    public void chamarExcluirCliente(int i) {
        cliente.excluirCliente(i);
    }

}
