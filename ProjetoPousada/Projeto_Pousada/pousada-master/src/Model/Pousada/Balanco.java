package Model.Pousada;


/**
 * Classe responsável por gerenciar o balanço financeiro da pousada.
 */
public class Balanco {
    private static Double receita = 0.0;
    private static Double despesa = 0.0;
    private static Double saldo = 0.0;

    public Balanco() {

    }

    public static Double getReceita() {
        return receita;
    }

    public static void setReceita(Double receita) {
        Balanco.receita = receita;
    }

    public static Double getDespesa() {
        return despesa;
    }

    public static void setDespesa(Double despesa) {
        Balanco.despesa = despesa;
    }

    public static Double getSaldo() {
        return saldo;
    }

    public static void setSaldo(Double saldo) {
        Balanco.saldo = saldo;
    }
}