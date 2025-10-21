package br.univates.banco.persistencia;

import br.univates.alexandria.Arquivo;
import br.univates.alexandria.Cpf;
import br.univates.alexandria.InvalidEntryException;
import br.univates.banco.negocio.ContaBancaria;
import br.univates.banco.negocio.ContaBancariaEspecial;
import br.univates.banco.negocio.Correntista;
import br.univates.banco.negocio.Transacao;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

public class ContaBancariaDao
{
    
    public ContaBancariaDao()
    {
        if (ContaBancaria.getSequencial() == 0) // atualizar sequencial
        {
            try
            {
                ContaBancaria.setSequencial( this.readAll().getLast().getNumero() );
            }
            catch (NoSuchElementException e)
            {
                // Não tem nada para fazer. A primeira conta será a conta 1.
            }
        }
    }
    
    public ContaBancaria read( int numero )
    {
        ArrayList<ContaBancaria> listaContas = readAll();
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
        ArrayList<ContaBancaria> listaContas = readAll();
        listaContas.add(conta);
        salvarArquivo(listaContas);
    }
    
    private void salvarArquivo( ArrayList<ContaBancaria> listaContas )
    {
        Arquivo a = new Arquivo("conta.dat");
        if (a.abrirEscrita())
        {
            for (ContaBancaria i: listaContas)
            {
                a.escreverLinha( i.toString() );
            }
            a.fecharArquivo();
        }
        
        a = new Arquivo("transacao.dat");
        if (a.abrirEscrita())
        {
            for (ContaBancaria i: listaContas)
            {
                ArrayList<Transacao> listaTransacoes = i.getListaTransacoes();
                for (Transacao t: listaTransacoes)
                {
                    a.escreverLinha( i.getNumero()+";"+t.toString() );
                }
            }
            a.fecharArquivo();
        }
    }
    
    public void update(ContaBancaria conta)
    {
        // verificação de integridade contralada diretamente pelo sistema
        ArrayList<ContaBancaria> listaContas = readAll();
        int index = listaContas.indexOf(conta);
        if (index >= 0)
        {
            listaContas.set(index, conta);
            salvarArquivo(listaContas);
        }
    }
    
    public ArrayList<ContaBancaria> readAll()
    {
        ArrayList<ContaBancaria> listaContas = new ArrayList();
        
        Arquivo a = new Arquivo("conta.dat");
        if (a.abrirLeitura())
        {
            String linha = a.lerLinha();
            while (linha != null)
            {
                String[] campo = linha.split(";");
                
                int numeroConta = Integer.parseInt(campo[1]);
                ArrayList<Transacao> listaTransacoes = readTransacoes( numeroConta );
                ContaBancaria conta;
                
                if (campo[0].charAt(0) == ContaBancaria.TIPO_SIMPLES )
                {
                    conta = new ContaBancaria( numeroConta, 
                                               new CorrentistaDao().read( campo[2] ),
                                               Double.parseDouble( campo[3] ),
                                               listaTransacoes );
                    listaContas.add(conta);
                }
                else //ContaBancaria.TIPO_ESPECIAL 
                {
                    conta = new ContaBancariaEspecial( numeroConta, 
                                               new CorrentistaDao().read( campo[2] ),
                                               Double.parseDouble( campo[3] ),
                                               listaTransacoes,
                                               Double.parseDouble( campo[4] ));
                    listaContas.add(conta);
                }
                linha = a.lerLinha();
            }
            a.fecharArquivo();
        }
        Collections.sort(listaContas);
        
        return listaContas;
    }
    
    public ArrayList<ContaBancaria> readAll( Correntista correntista )
    {
        ArrayList<ContaBancaria> listaContas = readAll();
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
    
    
    private ArrayList<Transacao> readTransacoes( int numeroConta )
    {
        ArrayList<Transacao> listaTransacoes = new ArrayList();
        Arquivo a = new Arquivo("transacao.dat");
        if (a.abrirLeitura())
        {
            String linha = a.lerLinha();
            while (linha != null)
            {
                String[] campo = linha.split(";");
                
                int numero = Integer.parseInt(campo[0]);
                
                if (numero == numeroConta) // se é uma transação desta conta (select where numeroConta = ??)
                {
                    String[] parteData = campo[1].split("/");
                    
                    LocalDate dt = LocalDate.of( Integer.parseInt(parteData[2]),
                                                 Integer.parseInt(parteData[1]),
                                                 Integer.parseInt(parteData[0]));
                    
                    Transacao t = new Transacao( dt,                             //data
                                                 campo[2],                       //descricao
                                                 Double.parseDouble(campo[3]),   //valor
                                                 campo[4].charAt(0),             // tipo operação
                                                 Double.parseDouble(campo[5]) ); // saldo
                    
                    listaTransacoes.add(t);
                }

                linha = a.lerLinha();
            }
            a.fecharArquivo();
        }
      
        return listaTransacoes;
    }
    
    
}
