package br.univates.banco.apresentacao;

import br.univates.alexandria.Entrada;
import br.univates.alexandria.menu3.Menu;
import br.univates.alexandria.menu3.MenuItem;
import br.univates.alexandria.menu3.MenuProcedimento;
import br.univates.banco.negocio.ContaBancaria;
import br.univates.banco.negocio.SaldoInsuficienteException;
import br.univates.banco.negocio.ValorNegativoException;

public class TelaContaBancaria
{
    private ContaBancaria conta;

    public TelaContaBancaria(ContaBancaria conta)
    {
        this.conta = conta;
    }
    
    public void exibir()
    {
        Menu m = new Menu();
        m.setTitulo("Autoatendimento do Banco Byte\n\nConta de: "+conta.getCorrentista().getNome());
        m.addItem( new MenuItem('d', "Depositar", new MenuProcedimento()
        {
            @Override
            public void executarProcedimento()
            {
                try
                {
                    double valor = Entrada.leiaDouble("Valor do depósito: ");            
                    conta.depositar(valor);
                    System.out.println("Operação realizada");
                } 
                catch (ValorNegativoException ex)
                {
                    System.out.println("O valor precisa ser positivo");
                }
            }
        } ));
        
        
        m.addItem( new MenuItem('s',"Sacar", new MenuProcedimento()
        {
            @Override
            public void executarProcedimento()
            {
            try
                {
                    double valor = Entrada.leiaDouble("Valor do saque: ");
                    conta.sacar(valor);
                    System.out.println("Operação realizada");
                } 
                catch (ValorNegativoException ex)
                {
                    System.out.println("O valor precisa ser positivo");
                } 
                catch (SaldoInsuficienteException ex)
                {
                    System.out.println("Não há saldo suficiente");
                }
            }
        }));
        
        /**
         * Terceira maneira de fazer (a mais adequada)
         */
        m.addItem( new MenuItem( 'c', "Consultar", new MenuProcedimento()
        {
            @Override
            public void executarProcedimento()
            {
                System.out.println("O saldo atual é: "+conta.getSaldo() );
            }
        }));
        
        m.gerarMenu();
    }
    
}
