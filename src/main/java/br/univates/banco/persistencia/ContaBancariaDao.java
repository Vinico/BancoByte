package br.univates.banco.persistencia;

import br.univates.alexandria.Cpf;
import br.univates.banco.negocio.ContaBancaria;
import br.univates.banco.negocio.Correntista;

import java.util.ArrayList;
import java.util.Collections;

public class ContaBancariaDao {
    private static ArrayList<ContaBancaria> listaContas;

    public ContaBancariaDao() {
        if (listaContas == null) {
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

    public ContaBancaria read(int numero) {
        ContaBancaria contaProcurada = new ContaBancaria(numero, null, 0);
        ContaBancaria contaRetorno = null;
        int index = listaContas.indexOf(contaProcurada);
        if (index >= 0) {
            contaRetorno = listaContas.get(index);
        }
        return contaRetorno;
    }

    public boolean checkCorrentista(Cpf cpf) {
        for (ContaBancaria conta : listaContas) {
            if (conta.getCorrentista().getCpf() == cpf) {
                 return true;
            }
        }

        return false;
    }


    public void create(ContaBancaria conta) {
        listaContas.add(conta);
    }

    public ArrayList<ContaBancaria> readAll() {
        Collections.sort(listaContas);
        return listaContas;
    }
}
