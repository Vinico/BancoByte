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

/**
 * Esta versão implementa todas opções do menu com classes internas e anônimas
 * e também implementa um DAO que gerencia várias contas bancárias
 * @author mouriac
 */
public class Main
{

    public static void main(String[] args)
    {
        try
        {
            Correntista correntista1 = new Correntista( new Cpf("58759565004"),"Ivo Lima","Lajeado");
            ContaBancaria conta1 = new ContaBancariaEspecial( correntista1 );
            
            Correntista correntista2 = new Correntista( new Cpf("78297346072"),"Dalva Oliveira","Lajeado");
            ContaBancaria conta2 = new ContaBancariaEspecial( correntista2 );
            
            Correntista correntista3 = new Correntista( new Cpf("03765569003"),"Carlos Pichinini","Estrela");
            ContaBancaria conta3 = new ContaBancariaEspecial( correntista3 );
            
            ContaBancariaDao dao = new ContaBancariaDao();
            dao.create(conta1);
            dao.create(conta2);
            dao.create(conta3);
            
            
            ArrayList<ContaBancaria> vetor = dao.readAll();
            for (ContaBancaria c : vetor)
            {
                System.out.println( c.getNumero() );
            }
            
            
            TelaPrincipal tela = new TelaPrincipal();
            tela.exibir();    
        } 
        catch (InvalidEntryException ex)
        {
            System.out.println("Há uma CPF de correntista inválido. O sistema não pode ser executado");
        }
        System.exit(0);
    }
}
