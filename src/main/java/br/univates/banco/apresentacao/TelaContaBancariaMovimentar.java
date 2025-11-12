package br.univates.banco.apresentacao;

import br.univates.alexandria.Entrada;
import br.univates.alexandria.menu3.Menu;
import br.univates.alexandria.menu3.MenuItem;
import br.univates.alexandria.menu3.MenuProcedimento;
import br.univates.alexandria.persistence.IDao;
import br.univates.alexandria.persistence.RecordNotFoundException;
import br.univates.banco.negocio.*;
import br.univates.banco.persistencia.ContaBancariaDaoPostgres;
import br.univates.banco.persistencia.DaoFactory;

import java.util.ArrayList;

public class TelaContaBancariaMovimentar {
    private ContaBancaria conta;

    public TelaContaBancariaMovimentar(ContaBancaria conta) {
        this.conta = conta;
    }

    public void exibir() {
        Menu m = new Menu();
        m.setTitulo("Autoatendimento do Banco Byte\n\nConta de: " + conta.getCorrentista().getNome());
        m.addItem(new MenuItem('d', "Depositar", new MenuProcedimento() {
            @Override
            public void executarProcedimento() {
                try {
                    double valor = Entrada.leiaDouble("Valor do depósito: ");
                    conta.depositar(valor);
                    IDao<ContaBancaria, Integer> daoConta = DaoFactory.createContaBancariaDao();
                    daoConta.update(conta);

                    System.out.println("Operação realizada");
                } catch (ValorNegativoException ex) {
                    System.out.println("O valor precisa ser positivo");
                } catch (RecordNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }));


        m.addItem(new MenuItem('s', "Sacar", new MenuProcedimento() {
            @Override
            public void executarProcedimento() {
                try {
                    double valor = Entrada.leiaDouble("Valor do saque: ");
                    conta.sacar(valor);

                    ContaBancariaDaoPostgres dao = new ContaBancariaDaoPostgres();
                    dao.update(conta);

                    System.out.println("Operação realizada");
                } catch (ValorNegativoException ex) {
                    System.out.println("O valor precisa ser positivo");
                } catch (SaldoInsuficienteException ex) {
                    System.out.println("Não há saldo suficiente");
                } catch (RecordNotFoundException e) {
                    System.out.println("Conta não achada");
                }
            }
        }));

        m.addItem(new MenuItem('p', "Fazer pix", new MenuProcedimento() {
            @Override
            public void executarProcedimento() {
                try {
                    double valor = Entrada.leiaDouble("Valor do pix: ");
                    conta.fazerPix(valor);

                    IDao<ContaBancaria, Integer> daoConta = DaoFactory.createContaBancariaDao();
                    daoConta.update(conta);

                    System.out.println("Operação realizada");
                } catch (ValorNegativoException ex) {
                    System.out.println("O valor precisa ser positivo");
                } catch (SaldoInsuficienteException ex) {
                    System.out.println("Não há saldo suficiente");
                } catch (RecordNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }));

        m.addItem(new MenuItem('c', "Consultar saldo", new MenuProcedimento() {
            @Override
            public void executarProcedimento() {
                System.out.println("O saldo atual é: " + conta.getSaldo());
            }
        }));

        m.addItem(new MenuItem('e', "Consular extrato", new MenuProcedimento() {
            @Override
            public void executarProcedimento() {
                System.out.println("** Extrato bancário **");
                System.out.println("DATA       DESCRIÇÃO                  VALOR           SALDO");
                ArrayList<Transacao> listaTransacoes = conta.getListaTransacoes();
                for (Transacao t : listaTransacoes) {
                    System.out.println(t.getTransacaoFormatada());
                }
                System.out.println("--------------------------------------");
            }
        }));

        m.gerarMenu();
    }

}
