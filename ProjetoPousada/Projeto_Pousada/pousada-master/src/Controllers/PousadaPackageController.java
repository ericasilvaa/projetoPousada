package Controllers;

import Model.Pousada.Balanco;
import Model.Pousada.BalancoGerenciar;
import Model.Pousada.Quarto;
import Model.Pousada.QuartoGerenciar;
import Model.Pousada.Reserva.*;
import Services.ValidarDadosPousada;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import static Model.Pousada.Reserva.ReservaGerenciar.getListaReservas;
import static Services.ValidarDadosPousada.gerarPeriodoDatasReservado;


/**
 * Esta classe representa o controller para o gerenciamento de reservas e quartos em uma pousada.
 * Ela fornece métodos para realizar cadastro de novas reservas, edição, exclusão e impressão de quartos e reservas.
 * Os dados das reservas e quartos são gerenciados pelas classes ReservaGerenciar e QuartoGerenciar, respectivamente.
 * Além disso, também realiza o gerenciamento do balanço financeiro da pousada através da classe BalancoGerenciar.
 @author  Erica Silva Rodrigues, Gabriel Moreira Siqueira
 ***/

public class PousadaPackageController {

    ReservaGerenciar reservaGerenciar = new ReservaGerenciar();
    private static final int DIVIDIR_PARA_OBTER_PRECO_PRELIMINAR = 2;


    public void chamarCadastroNovaReserva(String stringDeEntrada, String stringDeSaida,
                                          int quartoEscolhido, int idCliente, String observacoes)
            throws ParseException {

        //data ser válida/existir; no formato dd/MM/yyyy e não ter passado
        chamarValidacaoData(stringDeEntrada);
        chamarValidacaoData(stringDeSaida);

        if(stringDeEntrada == null || stringDeSaida == null){
            return;
        }

        // quarto
        chamarVerificarValidadeNumeroDoQuartoInformado(String.valueOf(quartoEscolhido));

        // data saída não ser anterior a de entrada
        Date dataInicial = new Date(stringDeEntrada);
        Date dataFinal = new Date((stringDeSaida));
        chamarVerificarConflitoDatas(dataInicial, dataFinal);

        // gerar periodo em que a reserva irá durar
        ArrayList<String> arrayListPeriodoDaReserva;
        arrayListPeriodoDaReserva = chamarGerarPeriodoDatasReservado(dataInicial, dataFinal);

        Quarto quartoInstanciado = chamarSelecionarQuartoParaUmaReserva(quartoEscolhido, arrayListPeriodoDaReserva);

        if(quartoInstanciado == null){
            System.out.println("Quarto nessa data indisponível");
            return;
        }

        Double precoReserva = chamarCalculoPrecoPreliminar(arrayListPeriodoDaReserva, quartoInstanciado);
        chamarAdicionarAoBalanco(precoReserva/DIVIDIR_PARA_OBTER_PRECO_PRELIMINAR, quartoInstanciado);

        chamarAdicaoPersistenciaReserva(dataInicial, dataFinal, quartoInstanciado,
                precoReserva, observacoes, idCliente);

    }

    public void chamarImprimirQuartos(){
        Quarto.imprimirQuartos();
    }

    public void chamarImprimirReservas(){
        reservaGerenciar.imprimirReservas();
    }

    public void chamarImprimirReservasComAte30DiasDeHoje(){
        reservaGerenciar.imprimirReservasComAte30DiasDeHoje();
    }

    private void chamarAdicionarAoBalanco(Double preco, Quarto quartoInstanciado){
        System.out.println(quartoInstanciado.toString());
        BalancoGerenciar balanco1 = new BalancoGerenciar();
        balanco1.entradaDeDinheiro(quartoInstanciado.getPreco());
        balanco1.saidaDeDinheiro(quartoInstanciado.getDespesaQuarto());
    }

    public void chamarConfirmarReserva(int indice){

        int tamanho = getListaReservas().size();
        if( indice > tamanho || indice < 0 ){
            return;
        }

        Date hoje = new Date();
        Date dataComparar = getListaReservas().get(indice).getDataEntrada();

        /*if(ReservaGerenciar.calcularUmMesAntesInicioReserva(dataComparar)){
            //EstadoAbstratoReserva estadoAbstratoReserva = getListaReservas().get(indice).definirReservaComoDefinitiva();
            getListaReservas().get(indice).setEstado(new ReservaDefinitivaEstado());
            System.out.println(getListaReservas().get(indice).getEstado());
        }else{
            System.out.println("Não pode ser confirmada por causa da data.");
        }*/

        getListaReservas().get(indice).setEstado(new ReservaDefinitivaEstado());
        Balanco.setReceita(getListaReservas().get(indice).getPreco()/2);
    }

    public void chamarCancelarReserva(int indice){



    }


    private void chamarAdicaoPersistenciaReserva(Date dataDeEntrada, Date dataDeSaida, Quarto quartoInstanciado,
                                                Double precoReserva, String observacoes, int idCliente){
        ReservaGerenciar reservaGerenciar = new ReservaGerenciar();
        reservaGerenciar.cadastroReserva(dataDeEntrada, dataDeSaida, quartoInstanciado,
                precoReserva, observacoes, idCliente);
    }

    private Double chamarCalculoPrecoPreliminar(ArrayList arrayListPeriodoDaReserva, Quarto quartoInstanciado){
        Double precoReserva = Reserva.definirPreco(arrayListPeriodoDaReserva, quartoInstanciado);
        return precoReserva;
    }

    private void chamarVerificarValidadeNumeroDoQuartoInformado(String numeroQuarto){
        ValidarDadosPousada.verificarValidadeNumeroDoQuartoInformado(numeroQuarto);
    }

    private Date chamarValidacaoData(String dataFornecida){

        return ValidarDadosPousada.validarData(dataFornecida);
    }

    private void chamarVerificarConflitoDatas(Date dataEntrada, Date dataSaida){
        ValidarDadosPousada.verificarConflitoDatas(dataEntrada, dataSaida);
    }

    private ArrayList chamarGerarPeriodoDatasReservado(Date dataInicial, Date dataFinal){
        return gerarPeriodoDatasReservado(dataInicial, dataFinal);
    }

    private Quarto chamarSelecionarQuartoParaUmaReserva(int quartoEscolhido, ArrayList arrayListPeriodoDaReserva) throws ParseException {
        // instanciar quarto
        Quarto quartoInstanciado = QuartoGerenciar.selecionarQuarto(quartoEscolhido, arrayListPeriodoDaReserva);

        // descobrir se quarto está disponível
        if(QuartoGerenciar.descobrirSeQuartoEstaDisponivel(arrayListPeriodoDaReserva, quartoEscolhido)){
            return quartoInstanciado;
        }
        return null;
    }

}
