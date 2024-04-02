package Model.Pessoas;

/**
 * Classe concreta que representa o objeto Endereço.
 * Um endereço é composto por rua, bairro, CEP e o número da residência.
  * @author  Erica Silva Rodrigues, Gabriel Moreira Siqueira
 */
public class Endereco {

    /**
     * Variável de instância do tipo String que armazena o nome da rua em que o Cliente reside.
     */
    private String rua;

    /**
     * Variável de instância do tipo String que armazena o nome do bairro em que o Cliente reside.
     */
    private String bairro;

    /**
     * Variável de instância do tipo String que armazena o CEP da localidade em que o Cliente reside.
     */
    private String cep;

    /**
     * Variável de instância do tipo String que armazenará o número de identificação em que o Cliente reside.
     */
    private String numerologiaResidencia;

    public Endereco() {
    }

    public Endereco(String rua, String bairro, String cep, String numerologiaResidencia) {
        this.rua = rua;
        this.bairro = bairro;
        this.cep = cep;
        this.numerologiaResidencia = numerologiaResidencia;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getNumerologiaResidencia() {
        return numerologiaResidencia;
    }

    public void setNumerologiaResidencia(String numerologiaResidencia) {
        this.numerologiaResidencia = numerologiaResidencia;
    }

    @Override
    public String toString() {
        return "{\"rua\": \"" + rua + "\", " +
                "\"bairro\": \"" + bairro + "\", " +
                "\"cep\": \"" + cep + "\", " +
                "\"numerologiaResidencia\": \"" + numerologiaResidencia + "\"" +
                "}";
    }

}

