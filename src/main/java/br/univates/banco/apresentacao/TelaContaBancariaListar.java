package br.univates.banco.apresentacao;

import br.univates.banco.negocio.ContaBancaria;
import br.univates.banco.negocio.Correntista;
import br.univates.banco.persistencia.ContaBancariaDaoPostgres;
import br.univates.banco.persistencia.CorrentistaDaoPostgres;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class TelaContaBancariaListar
{
    
    public void exibir()
    {
        DecimalFormat doubleFormatador = new DecimalFormat("###,##0.00");
        ContaBancariaDaoPostgres dao = new ContaBancariaDaoPostgres();
        ArrayList<ContaBancaria> lista = dao.readAll();
        for (ContaBancaria c: lista)
        {
            System.out.println( c.getNumero()+"\t"+c.getTipo()+"\t"+
                                String.format("%-20s", c.getCorrentista().getNome())+"\t"+
                                String.format("%10s",doubleFormatador.format(c.getSaldo())) );
        }
        System.out.println();
    }
}
