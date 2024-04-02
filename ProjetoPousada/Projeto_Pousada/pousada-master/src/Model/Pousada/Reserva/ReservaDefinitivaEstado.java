package Model.Pousada.Reserva;

import Model.Pousada.Reserva.Reserva;
import java.sql.SQLOutput;

/**
 * Implementação do estado de reserva definitiva.
 * Uma reserva definitiva só pode ser cancelada.
 * A tentativa de setar uma reserva já definitiva como definitiva novamente não fará operação alguma.
 */
public class ReservaDefinitivaEstado implements EstadoAbstratoReserva {

    /**
     * Transição para o estado de reserva preliminar
     * @param reserva Busca abstrair o conceito de uma reserva em uma pousada
     * @return Retorna o próprio objeto EstadoAbstratoReserva, não há transição de estados
     */
    @Override
    public EstadoAbstratoReserva definirComoPreliminar(Reserva reserva) {
        System.out.println("Ops, reserva cancelada não pode mudar de estado");
        return this;
    }

    /**
     * Transição para o estado de reserva definitiva
     * @param reserva Busca abstrair o conceito de uma reserva em uma pousada
     * @return Retorna o próprio objeto EstadoAbstratoReserva, não há transição de estados
     */
    @Override
    public EstadoAbstratoReserva definirComoDefinitiva(Reserva reserva) {
        System.out.println("Ops. Reserva já é definitiva");
        return this;
    }

    /**
     * Transição para o estado de reserva cancelada
     * @param reserva Busca abstrair o conceito de uma reserva em uma pousada
     * @return Faz-se a transição de estado definitivo para cancelado
     */
    @Override
    public EstadoAbstratoReserva definirComoCancelada(Reserva reserva) {
        System.out.println("Reserva anteriormente definitiva definida como Cancelada");
        reserva.setEstado(new ReservaCanceladaEstado());
        return reserva.getEstado();
    }


    /**
     * Retorna representação em string do estado da Reserva
     * @return Representação em string do estado da Reserva
     */
    @Override
    public String toString() {
        return "Reserva Definitiva";
    }
}
