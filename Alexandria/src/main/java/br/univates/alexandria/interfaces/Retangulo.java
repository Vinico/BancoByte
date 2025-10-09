package br.univates.alexandria.interfaces;

public class Retangulo implements FormaGeometrica
{
    private double base;
    private double altura;

    public Retangulo(double base, double altura)
    {
        if (base > 0 && altura > 0)
        {
            this.base = base;
            this.altura = altura;
        }
        else
        {
            throw new IllegalArgumentException("Os lados não são adequados para um retângulo");
        }
    }

    public double getBase()
    {
        return base;
    }

    public double getAltura()
    {
        return altura;
    }

    public void setBase(double base)
    {
        if (base > 0)
        {
            this.base = base;
        }
    }

    public void setAltura(double altura)
    {
        if (altura > 0)
        {
            this.altura = altura;
        }
    }

    @Override
    public double getPerimetro()
    {
        return 2*base+2*altura;
    }

    @Override
    public double getArea()
    {
        return base*altura;
    }
    
    
}
