/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.univates.banco.apresentacao;

import br.univates.alexandria.Cpf;
import br.univates.alexandria.Entrada;
import br.univates.alexandria.InvalidEntryException;
import br.univates.alexandria.menu2.Menu;
import br.univates.alexandria.menu2.MenuItem;
import br.univates.banco.negocio.ContaBancaria;
import br.univates.banco.negocio.Correntista;
import br.univates.banco.persistencia.ContaBancariaDao;
import br.univates.banco.persistencia.CorrentistaDao;
import java.util.ArrayList;

/**
 *
 * @author mouriac
 */
public class TelaPrincipal
{

    public TelaPrincipal()
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
                    TelaContaBancaria tela = new TelaContaBancaria( conta );
                    tela.exibir();
                }
                else
                {
                    System.out.println("Conta corrente não localizada");
                }
            }
        });
        
        m.addItem( new MenuItem( 'c',"Cadastrar correntista")
        {
            @Override
            public void executar()
            {
                TelaCadastroCorrentista tela = new TelaCadastroCorrentista();
                tela.exibir();
            }
        });
        
        m.addItem( new MenuItem( 'l',"Listar correntistas")
        {
            @Override
            public void executar()
            {
                CorrentistaDao dao = new CorrentistaDao();
                ArrayList<Correntista> lista = dao.readAll();
                for (Correntista c: lista)
                {
                    System.out.println( c.getNome() );
                }
                System.out.println();
            }
        });

        m.addItem(new MenuItem('p', "Cadastrar Conta") {
            @Override
            public void executar() {
                ContaBancariaDao dao = new ContaBancariaDao();
                CorrentistaDao correDao = new CorrentistaDao();
                String cpfToCad = Entrada.leiaString("Digite o cpf do correntista a ser cadastrado: ");
                try {
                    ContaBancaria conta = new ContaBancaria(correDao.read(new Cpf(cpfToCad, true)));
                    dao.create(conta);
                } catch (InvalidEntryException e) {
                    throw new RuntimeException(e);
                }
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
