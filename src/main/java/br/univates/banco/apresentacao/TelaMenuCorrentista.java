/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.univates.banco.apresentacao;

import br.univates.alexandria.Entrada;
import br.univates.alexandria.menu2.Menu;
import br.univates.alexandria.menu2.MenuItem;
import br.univates.banco.negocio.ContaBancaria;
import br.univates.banco.persistencia.ContaBancariaDaoPostgres;

/**
 *
 * @author mouriac
 */
public class TelaMenuCorrentista
{

    public TelaMenuCorrentista()
    {
    }
    
    public void exibir()
    {
        Menu m = new Menu();
    
        m.addItem( new MenuItem( 'c',"Cadastrar novo correntista")
        {
            @Override
            public void executar()
            {
                TelaCorrentistaCadastrar tela = new TelaCorrentistaCadastrar();
                tela.exibir();
            }
        });
        
        m.addItem( new MenuItem( 'e',"Editar correntista")
        {
            @Override
            public void executar()
            {
                TelaCorrentistaEditar tela = new TelaCorrentistaEditar();
                tela.exibir();
            }
        });
        
        m.addItem( new MenuItem( 'd',"Deletar correntista")
        {
            @Override
            public void executar()
            {
                TelaCorrentistaDeletar tela = new TelaCorrentistaDeletar();
                tela.exibir();
            }
        });
        
        m.addItem( new MenuItem( 'l',"Listar correntistas")
        {
            @Override
            public void executar()
            {
                TelaCorrentistaListar tela = new TelaCorrentistaListar();
                tela.exibir();
            }
        });
        
        m.gerarMenu();
        
    }
    
    /*
    public void exibir()
    {
        ArrayList<ContaBancaria> listaContas = dao.retornarContas();
        
        Menu m = new Menu();
        
        for (ContaBancaria conta: listaContas)
        {
            m.addOpcao( new MenuItem( Character.forDigit(conta.getNumero(),10),
                                      conta.getCorrentista().getNome() )
            {
                @Override
                public void executar()
                {
                    ContaBancariaDao dao = new ContaBancariaDao();
                    TelaContaBancaria tela = new TelaContaBancaria( conta );
                    tela.exibir();
                }
            });
        }
        
        m.gerarMenu();
    }
    */
}
