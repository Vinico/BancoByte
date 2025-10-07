package br.univates.banco;

import br.univates.alexandria.Cpf;
import br.univates.alexandria.InvalidEntryException;
import br.univates.banco.apresentacao.TelaContaBancaria;
import br.univates.banco.apresentacao.TelaPrincipal;
import br.univates.banco.negocio.ContaBancaria;
import br.univates.banco.negocio.ContaBancariaEspecial;
import br.univates.banco.negocio.Correntista;
import br.univates.banco.persistencia.ContaBancariaDao;
import java.util.ArrayList;


public class Main
{

    public static void main(String[] args)
    {
            
            ContaBancariaDao dao = new ContaBancariaDao();
            
            ArrayList<ContaBancaria> vetor = dao.readAll();
            for (ContaBancaria c : vetor)
            {
                System.out.println( c.getNumero() );
            }
            
            
            TelaPrincipal tela = new TelaPrincipal();
            tela.exibir();    
            System.exit(0);


    }
}
