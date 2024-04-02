package Model.Pousada.Reserva;

import Model.Pessoas.Cliente;
import Model.Pousada.Quarto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * 
 * Classe que busca abstrair o conceito de uma reserva em uma pousada
 */
public class Reserva {
    /**
     * Responsável por armazenar a data de início de uma Reserva
     */
    private Date dataEntrada;
    /**
     * Responsável por armazenar a data do vencimento de uma Reserva
     */
    private Date dataSaida;
    /**
     * Responsável por armazenar o estado atual da Reserva
     */
    private EstadoAbstratoReserva estado;
    /**
     * Responsável por indicar qual o quarto correspondente da Reserva
     */
    private Quarto quarto;
    /**
     * Responsável por armazenar uma descrição ou observação da reserva
     */
    private String descricao;
    private Double preco;
    private Cliente cliente;

    /**
     * Construtor com todos os argumentos de uma Reserva
     *
     * @param dataEntrada Responsável por armazenar a data do vencimento de uma Reserva
     * @param dataSaida   Responsável por armazenar o estado atual da Reserva
     * @param estado      Responsável por indicar qual o quarto correspondente da Reserva
     * @param quarto      Responsável por indicar qual o quarto correspondente da Reserva
     * @param descricao   Responsável por armazenar uma descrição ou observação acercada reserva
     * @param preco       Responsável por armazenar o preco da reserva
     */
    public Reserva(Date dataEntrada, Date dataSaida, EstadoAbstratoReserva estado, Quarto quarto, String descricao, Double preco, Cliente cliente) {
        this.dataEntrada = dataEntrada;
        this.dataSaida = dataSaida;
        this.estado = new ReservaPreliminarEstado();
        this.quarto = quarto;
        this.descricao = descricao;
        this.preco = null;
        this.cliente = new Cliente();
    }

    /**
     * Construtor sem argumentos (padrão) de uma Reserva
     */
    public Reserva() {
        estado = new ReservaPreliminarEstado();
        this.cliente = new Cliente();
    }

    /**
     * Consula a data de entrada da Reserva
     *
     * @return
     */
    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public Date getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(Date dataSaida) {
        this.dataSaida = dataEntrada;
    }

    public EstadoAbstratoReserva getEstado() {
        return estado;
    }

    public void setEstado(EstadoAbstratoReserva estado) {
        this.estado = estado;
    }

    public Quarto getQuarto() {
        return quarto;
    }

    public void setQuarto(Quarto quarto) {
        this.quarto = quarto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public EstadoAbstratoReserva definirReservaComoDefinitiva() {
        estado.definirComoDefinitiva(this);
        return null;
    }

    public void definirReservaComoPreliminar() {
        estado.definirComoPreliminar(this);
    }

    public void definirReservaComoCancelada() {
        estado.definirComoCancelada(this);
    }

    public static double definirPreco(ArrayList<String> datasDoPeriodoDaReserva, Quarto quartoInstanciado) {

        int numeroDeDiasReservados = datasDoPeriodoDaReserva.size();
        Double precoDoQuartoReservado = quartoInstanciado.getPreco();

        return numeroDeDiasReservados * precoDoQuartoReservado;

    }

    @Override
    public String toString() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormatadaEntrada = formato.format(dataEntrada);
        String dataFormatadaSaida = formato.format(dataSaida);


        return
                "Início da Reserva = " + dataFormatadaEntrada +
                " | Fim da Reserva =  " + dataFormatadaSaida +
                " | " + estado +
                " \nQuarto: " + quarto +
                ", descricao = '" + descricao + '\'' +
                " \nPreço = " + preco +
                ", Cliente = " + cliente.toString() + "\n\n" +
                '}';
    }


}