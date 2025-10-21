/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.univates.banco.apresentacao;

import br.univates.alexandria.Entrada;
import br.univates.alexandria.menu2.Menu;
import br.univates.alexandria.menu2.MenuItem;
import br.univates.banco.negocio.ContaBancaria;
import br.univates.banco.persistencia.ContaBancariaDao;

/**
 *
 * @author mouriac
 */
public class TelaMenuPrincipal
{

    public TelaMenuPrincipal()
    {
    }
    
    public void exibir()
    {
        Menu m = new Menu();
        
        m.addItem( new MenuItem( 'a',"Autoatendimento")
        {
            @Override
            public void executar()
            {
                int numero = Entrada.leiaInt("Digite o número da conta corrente:");
                
                ContaBancariaDao dao = new ContaBancariaDao();
                
                ContaBancaria conta = dao.read(numero);
                if (conta != null)
                {
                    TelaContaBancariaMovimentar tela = new TelaContaBancariaMovimentar( conta );
                    tela.exibir();
                }
                else
                {
                    System.out.println("Conta corrente não localizada");
                }
            }
        });
        
        m.addItem( new MenuItem( 'c',"Cadastro de correntistas")
        {
            @Override
            public void executar()
            {
                TelaMenuCorrentista tela = new TelaMenuCorrentista();
                tela.exibir();
            }
        });
        
        m.addItem( new MenuItem( 'n',"Criar nova conta bancária")
        {
            @Override
            public void executar()
            {
                TelaContaBancariaCriar tela = new TelaContaBancariaCriar();
                tela.exibir();
            }
        });
        
        m.addItem( new MenuItem( 'l',"Listar contas bancárias")
        {
            @Override
            public void executar()
            {
                TelaContaBancariaListar tela = new TelaContaBancariaListar();
                tela.exibir();
            }
        });

        m.gerarMenu();
        
    }
    

}
