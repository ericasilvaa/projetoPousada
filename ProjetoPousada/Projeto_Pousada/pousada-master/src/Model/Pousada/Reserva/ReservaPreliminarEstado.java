package Model.Pousada.Reserva;

import Model.Pousada.Reserva.Reserva;

/**
 * Implementação do estado de reserva preliminar.
 * Uma reserva preliminar só pode ser definida como definitiva ou cancelada.
 * A tentativa de definir uma reserva já preliminar como preliminar não fará operação alguma.
 */
public class ReservaPreliminarEstado implements EstadoAbstratoReserva {
    /**
     * Transição para o estado de reserva preliminar
     * @param reserva Busca abstrair o conceito de uma reserva em uma pousada
     * @return Retorna o próprio objeto EstadoAbstratoReserva, já que reserva já é preliminar;
     * não há transição de estado
     */
    @Override
    public EstadoAbstratoReserva definirComoPreliminar(Reserva reserva) {
        System.out.println("Ops, reserva já está preliminar; não pode ser novamente definida como preliminar");
        return this;
    }

    /**
     * Transição para o estado de reserva definitiva
     * @param reserva Busca abstrair o conceito de uma reserva em uma pousada
     * @return Faz-se a transição de estado preliminar para definitiva
     */
    @Override
    public EstadoAbstratoReserva definirComoDefinitiva(Reserva reserva) {
        System.out.println("Reserva anteriormente preliminar definida como Definitiva");
        reserva.setEstado(new ReservaDefinitivaEstado());
        return reserva.getEstado();
    }

    /**
     * Transição para o estado de reserva cancelada
     * @param reserva Busca abstrair o conceito de uma reserva em uma pousada
     * @return Faz-se a transição de estado preliminar para cancelada
     */
    @Override
    public EstadoAbstratoReserva definirComoCancelada(Reserva reserva) {
        System.out.println("Reserva anteriormente preliminar definida como Cancelada");
        reserva.setEstado(new ReservaCanceladaEstado());
        return reserva.getEstado();
    }

    /**
     * Retorna representação em string do estado da Reserva
     * @return Representação em string do estado da Reserva
     */
    @Override
    public String toString() {
        return "Reserva Preliminar";
    }

    public static void main(String[] args) {
        Reserva reserva = new Reserva();
        System.out.println(reserva.getEstado());
        System.out.println(reserva.getEstado().definirComoDefinitiva(reserva));

    }
}