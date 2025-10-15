package br.univates.banco.persistencia;

import br.univates.alexandria.Arquivo;
import br.univates.alexandria.Cpf;
import br.univates.alexandria.InvalidEntryException;
import br.univates.banco.negocio.ContaBancaria;
import br.univates.banco.negocio.Correntista;

import java.util.ArrayList;
import java.util.Collections;

public class ContaBancariaDao // fazer uma funcao pra update e adicionar pra cada função da conta em si
{

    private final String path = "db/contabancaria.dat";

    public ContaBancariaDao()
    {

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
        ArrayList<ContaBancaria> listaContas = readAll();
        ContaBancaria contaProcurada = new ContaBancaria(numero,null,0);
        ContaBancaria contaRetorno = null;
        int index = listaContas.indexOf(contaProcurada);
        if (index >= 0)
        {
            contaRetorno = listaContas.get(index);
        }
        return contaRetorno;
    }

    public void update(ContaBancaria conta){
        ArrayList<ContaBancaria> listaContas = readAll();

        Arquivo a = new Arquivo(path);
        if (a.abrirEscrita())
        {
            for (ContaBancaria c: listaContas)
            {
                if (c.getNumero() == conta.getNumero())
                a.escreverLinha( conta.toString() );
            }
        }
        a.fecharArquivo();
    }
    
    
    public void create( ContaBancaria conta )
    {
        ArrayList<ContaBancaria> listaContas = readAll();
        listaContas.add(conta);


        Arquivo a = new Arquivo(path);
        if (a.abrirEscrita())
        {
            for (ContaBancaria c: listaContas)
            {
                a.escreverLinha( c.toString() );
            }
        }
        a.fecharArquivo();
    }
    
    public ArrayList<ContaBancaria> readAll()
    {
        ArrayList<ContaBancaria> listaContas = new ArrayList<ContaBancaria>();
        Arquivo a = new Arquivo(path);
        CorrentistaDao dao = new CorrentistaDao();
        TransacaoDao transacaoDao = new TransacaoDao();
        a.abrirLeitura();


        String linha = a.lerLinha();
        while (linha != null) {
            if (!linha.isEmpty()) {
                String[] splis = linha.split(";");
                Correntista corre;
                try {
                    corre = dao.read(new Cpf(splis[1], true));
                } catch (InvalidEntryException e) {
                    throw new RuntimeException(e);
                }
                int numero =  Integer.parseInt(splis[0]);

                listaContas.add(new ContaBancaria(numero, corre, Double.parseDouble(splis[2])));
            }
            linha = a.lerLinha();
        }

        a.fecharArquivo();
        return listaContas;
    }


}
