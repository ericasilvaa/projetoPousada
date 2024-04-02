package Services;

import Model.Pousada.Quarto;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


/**
 * Classe responsável por validar os dados relacionados à Pousada.
 */
public class ValidarDadosPousada {

    public static void verificarValidadeNumeroDoQuartoInformado(String numeroQuarto) {

        boolean contemApenasNumeros = numeroQuarto.matches("[0-9]+");
        if (!contemApenasNumeros) {
            System.out.println("A String deve ter apenas dígitos de 0 a 9.");
            return;
        }
    }

    public static Date validarData(String dataFornecida)  {

        DateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        Date data = new Date();

        // garantir data válida
        formatador.setLenient(false);

        // garantir data no formato dd/MM/yyyy

        try {
            data = formatador.parse(dataFornecida);
        } catch (ParseException e) {
            System.out.println("ParseException em validarData: " + e);
            return null;
        }

        // garantir que dataFornecida já não passou
        Date hoje = new Date();
        if(data.before(hoje)){
            System.out.println("Data Fornecida já passou");
            return null;
        }

        // nao se faz reservas se elas demorarao mais de dois anos
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.YEAR, 2);
        Date novaData = calendar.getTime();

        if(data.after(novaData)){
            System.out.println("Data Fornecida está muito distante");
            return null;
        }

        return data;
    }

    public static void verificarConflitoDatas(Date dataEntrada, Date dataSaida){

        if (dataEntrada.after(dataSaida)) {
            System.out.println("Data de entrada da pousada não pode acontecer depois da data de saída ");
        } else if(dataEntrada.before(dataSaida) || dataEntrada.equals(dataSaida)) {
            System.out.println("Datas fornecidas são válidas.");
        }else{
            System.out.println("Erro desconhecido. Tente novamente");
        }

    }

    public static ArrayList gerarPeriodoDatasReservado(Date dataEntrada, Date dataSaida) {
        DateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
        Calendar inicio = Calendar.getInstance();
        Calendar fim = Calendar.getInstance();

        inicio.setTime(dataEntrada);
        fim.setTime(dataSaida);

        inicio.setTime(dataEntrada);
        ArrayList<String> periodoDeDatasReservados = new ArrayList<>();
        int contador = 0;

        while (!inicio.getTime().after(dataSaida)) {
            String dataFormatada = formatador.format(inicio.getTime());
            periodoDeDatasReservados.add(dataFormatada);
            inicio.add(Calendar.DATE, 1);
            contador = contador + 1;
        }

        System.out.println(periodoDeDatasReservados);
        return periodoDeDatasReservados;
    }


    // não é daqui
    public static void verificarDisponibilidadeQuartosDatas(ArrayList arrayList, int contador, Quarto quartoEscolhido){
        for (contador = 0; contador < arrayList.size(); contador++) {
            if(arrayList.get(contador).equals(quartoEscolhido)){
                System.out.println("Quarto indisponível");
            }
        }
    }


    public static void main(String[] args) {
        Date date = new Date();
        ValidarDadosPousada.gerarPeriodoDatasReservado(date, date);
    }
}
