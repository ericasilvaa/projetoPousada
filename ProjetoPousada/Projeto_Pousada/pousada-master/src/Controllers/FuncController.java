package Controllers;
import Model.Pessoas.FuncionarioGerenciar;
import Services.ValidacaoDadosPessoas;

import static Model.Pessoas.FuncionarioGerenciar.getListaFuncionario;
import static Services.ValidacaoDadosPessoas.tentarValidarDados;
import static Services.ValidacaoDadosPessoas.verificarSeIdFuncEhValido;

/**
 * Esta classe representa o controller para o gerenciamento de funcionários.
 * Ela fornece métodos para realizar cadastro, edição, exclusão e impressão de funcionários.
 * Os dados dos funcionários são gerenciados pela classe FuncionarioGerenciar.
 * 
 *  @author Erica Silva Rodrigues e Gabriel Moreira Siqueira

 */




public class FuncController {
    FuncionarioGerenciar func = new FuncionarioGerenciar();
    
    /**
     * Método para chamar o cadastro de um novo funcionário.
     * Verifica se os dados são válidos antes de realizar o cadastro.
     * 
     * @param nome  
     * @param cpf  
     * @param senha 
     */
    public void chamarCadastroFuncionario(String nome, String cpf, String senha){
        if(ValidacaoDadosPessoas.tentarValidarDados(nome, cpf, senha)){
            System.out.println("Erro validação");
            return;
        }

        if(ValidacaoDadosPessoas.seCpfEhRepetido(cpf)){
            return;
        }
        func.cadastroFuncionario(nome, cpf, senha);
    }
    /**
     * Método para chamar a impressão de todos os funcionários cadastrados.
     */

    public void chamarImpressaoFuncionario(){
        func.imprimirFuncionario();
    }

     /**
     * Método para chamar a edição de um funcionário existente.Verifica se os dados são válidos antes de realizar a edição.
     *
     * @param i
     * @param novoNome
     * @param novoCpf
     * @param novaSenha **/
    public void chamarEditarFuncionario(int i, String novoNome, String novoCpf, String novaSenha){
        getListaFuncionario().get(i).setCpf(null);
        if(!verificarSeIdFuncEhValido(i)) {
            System.out.println("Erro ID");
            return;
        }
        if(ValidacaoDadosPessoas.tentarValidarDados(novoNome, novoCpf, novaSenha)){
            System.out.println("Erro validação");
            return;
        }
        func.editarFuncionario(i, novoNome, novoCpf, novaSenha);
        System.out.println("Sucesso");
    }

    /**
     * Método para chamar a exclusão de um funcionário existente.
     * 
     * @param i  
     */
    public void chamarExcluirFuncionario(int i){
        if(!verificarSeIdFuncEhValido(i)) return;
        func.excluirFuncionario(i);
    }

    
     /**
     * Método para chamar a edição de um funcionário pelo nome, CPF e senha.
     * 
     * @param nome  
     * @param cpf  
     * @param senha  
     */
    public void chamarEditarFuncionario(String nome, String cpf, String senha){
        FuncionarioGerenciar funcionarioGerenciar = new FuncionarioGerenciar();
        funcionarioGerenciar.editarFuncionario(nome, cpf, senha);
    }

}
