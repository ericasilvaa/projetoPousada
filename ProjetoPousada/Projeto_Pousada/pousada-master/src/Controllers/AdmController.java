package Controllers;
import Model.Pessoas.AdministradorGerenciar;
import Services.ValidacaoDadosPessoas;

import static Model.Pessoas.AdministradorGerenciar.getListaAdministrador;
import static Services.ValidacaoDadosPessoas.verificarSeIdAdmEhValido;

/**
 * Esta classe representa o controller para o gerenciamento de administradores.
 * Ela fornece métodos para realizar cadastro, edição, exclusão e impressão de administradores.
 * Os dados dos administradores são gerenciados pela classe AdministradorGerenciar.
 * @author  Erica Silva Rodrigues, Gabriel Moreira Siqueira
 **/



public class AdmController {
    AdministradorGerenciar administradorGerenciar = new AdministradorGerenciar();
    
    /**
     * Método para chamar o cadastro de um novo administrador.
     * Verifica se os dados são válidos antes de realizar o cadastro.
     * 
     * @param nome 
     * @param cpf  
     * @param senha  
     */
    public void chamarCadastroAdministrador(String nome, String cpf, String senha){
        if(ValidacaoDadosPessoas.tentarValidarDados(nome, cpf, senha)){
            return;
        }

        if(ValidacaoDadosPessoas.seCpfEhRepetido(cpf)){
            return;
        }
        administradorGerenciar.cadastroAdministrador(nome, cpf, senha);
    }

    
     /**
     * Método para chamar a impressão de todos os administradores cadastrados.
     */
    public void chamarImpressaoAdministradores(){
        administradorGerenciar.imprimirAdministradores();
    }

    
    /**
     * Método para chamar a edição de um administrador existente.
     * Verifica se os dados são válidos antes de realizar a edição.
     * 
     * @param i 
     * @param novoNome  
     * @param novoCpf  
     * @param novaSenha  
     */
    public void chamarEditarAdministrador(int i, String novoNome, String novoCpf, String novaSenha){

        getListaAdministrador().get(i).setCpf(null);
        if(ValidacaoDadosPessoas.tentarValidarDados(novoNome, novoCpf, novaSenha)){
            System.out.println("Dados informados inválidos");
            return;
        }

        if((!verificarSeIdAdmEhValido(i))){
            System.out.println("Erro: ID ADM inválido");
            return;
        }

        administradorGerenciar.editarAdministrador(i, novoNome, novoCpf, novaSenha);
    }
    /**
     * Método para chamar a exclusão de um administrador existente.
     * 
     * @param i  
     */

    public void chamarExcluirAdministrador(int i){
        if((!verificarSeIdAdmEhValido(i))){
            return;
        }
        administradorGerenciar.excluirAdministrador(i);
    }
}
