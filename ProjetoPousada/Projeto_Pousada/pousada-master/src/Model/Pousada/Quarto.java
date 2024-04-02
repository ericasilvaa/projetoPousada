package Model.Pousada;

import java.util.ArrayList;
import java.util.Arrays;

import static Model.Pousada.QuartoGerenciar.getListaQuartos;


/**
 * Classe que representa um quarto da pousada.
 */

public class Quarto {
    private int numeroQuarto;
    private String apelido;
    private String tipo;
    private Double preco;
    private int capacidade;
    private boolean disponibilidade;
    private Double despesaQuarto;
    /**
     * Array que armazena todas as datas que uma reserva contempla
     */
    private ArrayList<String> arrayDatas = new ArrayList<>();

    public Quarto() {

    }

    public Quarto(int numeroQuarto, String apelido, String tipo, Double preco, int capacidade, boolean disponibilidade, ArrayList<String> arrayDatas, Double despesaQuarto) {
        this.numeroQuarto = numeroQuarto;
        this.apelido = apelido;
        this.tipo = tipo;
        this.preco = preco;
        this.capacidade = capacidade;
        this.disponibilidade = disponibilidade;
        this.arrayDatas = new ArrayList<>();
        this.despesaQuarto = despesaQuarto;
    }

    public Quarto(int numeroQuarto) {
        this.numeroQuarto = numeroQuarto;
    }


    public int getNumeroQuarto() {
        return numeroQuarto;
    }

    public void setNumeroQuarto(int numeroQuarto) {
        this.numeroQuarto = numeroQuarto;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public boolean isDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public ArrayList<String> getArrayDatas() {
        return arrayDatas;
    }

    public void setArrayDatas(ArrayList<String> arrayDatas) {
        this.arrayDatas = arrayDatas;
    }

    public static Quarto acessarQuarto(int i){
        return getListaQuartos()[i];
    }

    public Double getDespesaQuarto() {
        return despesaQuarto;
    }

    public void setDespesaQuarto(Double despesaQuarto) {
        this.despesaQuarto = despesaQuarto;
    }

    public static void imprimirQuartos(){
        for (Quarto listaQuarto : QuartoGerenciar.getListaQuartos()) {
            System.out.println(listaQuarto.toString());
        }
    }

    @Override
    public String toString() {
        String resultado = null;
        QuartoGerenciar quartoGerenciar = new QuartoGerenciar();
        quartoGerenciar.carregarDados();
        return
                "Número do Quarto = " + numeroQuarto +
                "\nApelido:'" + apelido + '\'' +
                ", tipo = '" + tipo + '\'' +
                ", disponibilidade = " + (disponibilidade ? "disponível" : "não disponível") +
                ", \nDatas Reservadas = " + arrayDatas.toString() +
                ", Preço = " + preco +
                ", Capacidade = " + capacidade +
                ", Despesa do Quarto = " + despesaQuarto +
                '}';
    }

    public static void main(String[] args) {


        QuartoGerenciar quartoGerenciar = new QuartoGerenciar();
        quartoGerenciar.carregarDados();

        Quarto quarto = new Quarto();
        System.out.println(quarto.getDespesaQuarto());
    }

}
