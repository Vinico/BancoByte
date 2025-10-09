package br.univates.alexandria.interfaces;

import br.univates.alexandria.Util;

public class Triangulo implements FormaGeometrica
{
    private double a;
    private double b;
    private double c;
    
    public Triangulo(double a_, double b_, double c_)
    {
        if (!Util.verificarTriangulo(a_,b_,c_))
        {
            throw new IllegalArgumentException("Os lados não são adequados para um triângulo");
        }
        a = a_;
        b = b_;
        c = c_;
    }
    
    @Override
    public double getArea()
    {
        double sp = (a+b+c)/2;
        double area = Math.sqrt( sp * (sp-a) * (sp-b) * (sp-c) );
        return area;
    }
    
    @Override
    public double getPerimetro()
    {
        return a+b+c;
    }

    public double getLadoA()
    {
        return a;
    }

    public double getLadoB()
    {
        return b;
    }

    public double getLadoC()
    {
        return c;
    }

    public void setLadoA(double a_)
    {
        if (Util.verificarTriangulo(a_,b,c))
        {
            a = a_; 
        }
    }

    public void setLadoB(double b_)
    {
        if (Util.verificarTriangulo(a,b_,c))
        {
            b = b_;
        }
    }

    public void setLadoC(double c_)
    {
        if (Util.verificarTriangulo(a,b,c_))
        {
            c = c_;
        }
    }
    
    public void setTriangulo( double a_, double b_, double c_ )
    {
        if (Util.verificarTriangulo(a_,b_,c_))
        {
            a = a_;
            b = b_;
            c = c_;
        }
    }
    
    public void aumentarLados( double percentual )
    {
        if (percentual > 0)
        {
            double fator = 1 + (percentual/100);
            a *= fator;
            b *= fator;
            c *= fator;
        }
    }
    
}
