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
public class TelaPrincipal {

    public TelaPrincipal() {
    }

    public void exibir() {
        Menu m = new Menu();

        m.addItem(new MenuItem('1', "Conta de Ivo Lima") {
            @Override
            public void executar() {
                ContaBancariaDao dao = new ContaBancariaDao();
                TelaContaBancaria tela = new TelaContaBancaria(dao.read(1));
                tela.exibir();
            }
        });

        m.addItem(new MenuItem('2', "Conta de Dalva Oliveira") {
            @Override
            public void executar() {
                ContaBancariaDao dao = new ContaBancariaDao();
                TelaContaBancaria tela = new TelaContaBancaria(dao.read(2));
                tela.exibir();
            }
        });

        m.addItem(new MenuItem('3', "Conta de Carlos Pichinini") {
            @Override
            public void executar() {
                ContaBancariaDao dao = new ContaBancariaDao();
                TelaContaBancaria tela = new TelaContaBancaria(dao.read(3));
                tela.exibir();
            }
        });


        m.addItem(new MenuItem('c', "Cadastrar correntista") {
            @Override
            public void executar() {
                TelaCadastroCorrentista tela = new TelaCadastroCorrentista();
                tela.exibir();
            }
        });

        m.addItem(new MenuItem('l', "Listar correntistas") {
            @Override
            public void executar() {
                CorrentistaDao dao = new CorrentistaDao();
                ArrayList<Correntista> lista = dao.readAll();
                for (Correntista c : lista) {
                    System.out.println(c.getNome() + " " + c.getCpfFormatado());
                }
                System.out.println();
            }
        });

        m.addItem(new MenuItem('e', "Editar correntistas") {
            @Override
            public void executar() {
                CorrentistaDao dao = new CorrentistaDao();
                Cpf cpf;
                String cpfChoice = Entrada.leiaString("Digite o cpf do correntista a ser editado");
                try {
                    cpf = new Cpf(cpfChoice, false);
                } catch (InvalidEntryException e) {
                    throw new RuntimeException(e);
                }
                Correntista temp = dao.read(cpf);
                if (temp != null) {

                    String nomeAtt = Entrada.leiaString("Digite o novo nome:");
                    String municipioAtt = Entrada.leiaString("Digite o novo municipio: ");
                    Correntista correntAtt = new Correntista(temp.getCpf(), nomeAtt, municipioAtt);
                    dao.update(correntAtt);

                }

            }
        });

        m.addItem(new MenuItem('d', "Deletar correntista") {
            @Override
            public void executar() {
                CorrentistaDao correntistaDao = new CorrentistaDao();
                ContaBancariaDao contaDao = new ContaBancariaDao();
                String cpfToDelete = Entrada.leiaString("Digite o cpf do correntista a ser deletadoo: ");
                Cpf cpf;

                try {
                    cpf = new Cpf(cpfToDelete);
                } catch (InvalidEntryException e) {
                    throw new RuntimeException(e);
                }

                if (!contaDao.checkCorrentista(cpf )) {

                    correntistaDao.delete(cpf);
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
