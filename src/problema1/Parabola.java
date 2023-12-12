package problema1;
import java.lang.Math;
public class Parabola {
    private int a,b,c;

    public Parabola(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    private double delta()
    {
        return (double)(b*b-4*a*c);
    }

    public double Xv()
    {
        return -1*this.b/(2*this.a);
    }

    public double Yv()
    {
        return -1*delta()/(4*a);
    }

    public static void mijloc(Parabola f1, Parabola f2)
    {
        double Xm, Ym;
        Xm=(f1.Xv()+f2.Xv())/2;
        Ym=(f1.Yv()+f2.Yv())/2;
        System.out.println("Coordonatele mijlocului celor 2 varfuri: Xm="+Xm+" Yv="+Ym);
    }

    public double lungime(Parabola f)
    {
        return Math.hypot(-1*this.b/(2*this.a)-f.Xv(),-1*delta()/(2*a) - f.Yv());
    }

    public static double lungime2(Parabola f1, Parabola f2)
    {
        return Math.hypot(f1.Xv()-f2.Xv(),f1.Yv() - f2.Yv());
    }

    @Override
    public String toString() {
        return "f(x)="+a+"x^2+"+b+"x+"+c;
    }


}