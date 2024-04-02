package Model.Pousada.Reserva;

import Model.Pousada.Reserva.Reserva;

/**
 * Implementação do estado de reserva cancelada.
 * Uma reserva cancelada não sofre transições de estados.
 * A tentativa de cancelar uma reserva já cancelada não fará operação alguma.
 */
public class ReservaCanceladaEstado implements EstadoAbstratoReserva {

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
        System.out.println("Ops, reserva cancelada não pode mudar de estado");
        return this;
    }

    /**
     * Transição para o estado de reserva cancelada
     * @param reserva Busca abstrair o conceito de uma reserva em uma pousada
     * @return Retorna o próprio objeto EstadoAbstratoReserva, não há transição de estados
     */
    @Override
    public EstadoAbstratoReserva definirComoCancelada(Reserva reserva) {
        System.out.println("Ops, reserva cancelada não pode mudar de estado");
        return this;
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