package br.univates.alexandria;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ArquivoTeste
{
    public static void main(String[] args)
    {
        /*
        Arquivo a = new Arquivo("correntista.dat");
        a.abrirEscrita();
        a.escreverLinha("58759565004;Juca Bastista;Lajeado");
        a.fecharArquivo();
        */
        
        try
        {
            Correntista c = new Correntista( new Cpf("58759565004"),"Juca Bastista", "Lajeado");
            String cor = "";
            
            Arquivo a = new Arquivo("correntista.dat");
            a.abrirEscrita();
            a.escreverLinha( c.toString() );
            a.fecharArquivo();

            if (a.abrirLeitura())
            {
                String s = a.lerLinha();
                while (s != null)
                {
                    cor = s;
                    System.out.println(s);
                    s = a.lerLinha();
                }
            }
            a.fecharArquivo();
        
            
            String[] partes = cor.split(";");
            
            Correntista c2 = new Correntista( new Cpf(partes[0]),partes[1],partes[2] );
            
            System.out.println("c2 ==> "+c2);
            
        } 
        catch (InvalidEntryException ex)
        {
        
        }
        
        
    }
            
}
