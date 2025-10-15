package br.univates.banco;

import br.univates.alexandria.Ambiente;
import br.univates.alexandria.Cpf;
import br.univates.alexandria.InvalidEntryException;
import br.univates.banco.apresentacao.TelaPrincipal;
import br.univates.banco.negocio.ContaBancaria;
import br.univates.banco.negocio.ContaBancariaEspecial;
import br.univates.banco.negocio.Correntista;
import br.univates.banco.persistencia.ContaBancariaDao;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Esta versão implementa todas opções do menu com classes internas e anônimas
 * e também implementa um DAO que gerencia várias contas bancárias
 * @author mouriac
 */
public class Main
{

    public static void main(String[] args)
    {
        System.out.println( Ambiente.NOME_USER );        
        
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));


            
            TelaPrincipal tela = new TelaPrincipal();
            tela.exibir();    




        System.exit(0);
    }
}
