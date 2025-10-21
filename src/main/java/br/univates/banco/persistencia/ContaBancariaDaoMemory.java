package br.univates.banco.persistencia;

import br.univates.banco.negocio.ContaBancaria;
import br.univates.banco.negocio.Correntista;
import java.util.ArrayList;
import java.util.Collections;

public class ContaBancariaDaoMemory
{
    private static ArrayList<ContaBancaria> listaContas;

    public ContaBancariaDaoMemory()
    {
        if (listaContas == null)
        {
            listaContas = new ArrayList();
        }
    }
    
    /*
    public ContaBancaria read( int numero )
    {
        ContaBancaria contaRetorno = null;
        for (ContaBancaria conta: listaContas)
        {
            if (conta.getNumero() == numero)
            {
                contaRetorno = conta;
            }
        }
        return contaRetorno;
    }
    */
    
    public ContaBancaria read( int numero )
    {
        ContaBancaria contaProcurada = new ContaBancaria(numero,null,0,null);
        ContaBancaria contaRetorno = null;
        int index = listaContas.indexOf(contaProcurada);
        if (index >= 0)
        {
            contaRetorno = listaContas.get(index);
        }
        return contaRetorno;
    }
    
    
    public void create( ContaBancaria conta )
    {
        // verificação de integridade ??
        // número não deve repetir, em RAM o número da conta
        // é sequencial e começa em 01 sempre, mas quando for em arquivo
        // precisamos cuidar para seguir com a numeração
        listaContas.add(conta);
    }
    
    public ArrayList<ContaBancaria> readAll()
    {
        Collections.sort(listaContas);
        return listaContas;
    }
    
    public ArrayList<ContaBancaria> readAll( Correntista correntista )
    {
        ArrayList<ContaBancaria> listaContasAux = new ArrayList();
        
        for (ContaBancaria conta: listaContas)
        {
            if (conta.getCorrentista().equals(correntista))
            {
                listaContasAux.add(conta);
            }
        }
        Collections.sort(listaContasAux);
        return listaContasAux;
    }
}
