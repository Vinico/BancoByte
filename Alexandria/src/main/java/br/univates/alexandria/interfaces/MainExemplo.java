package br.univates.alexandria.interfaces;

import java.util.ArrayList;

public class MainExemplo
{
    public static void main(String[] args)
    {
        ArrayList<FormaGeometrica> lista = new ArrayList();
        lista.add( new Retangulo(30, 40)     );
        lista.add( new Triangulo(45, 29, 60) );
        lista.add( new Retangulo(5, 14)      );
        lista.add( new Retangulo(7, 6)       );
        lista.add( new Triangulo(4, 5,6)     );
        
        for (FormaGeometrica forma: lista)
        {
            System.out.println(forma.getClass().getSimpleName() );
            System.out.println(forma.getArea());
        }
    }
}
