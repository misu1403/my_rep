package problema1;

public class PerecheNumere
{
    private int x;
    private int y;

    public PerecheNumere()
    {

    }

    public PerecheNumere(int x,int y)
    {
        this.x=x;
        this.y=y;
    }

    @Override
    public String toString() {
        return "\nx="+x+"\ny="+y;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public void setX(int newX)
    {
        this.x=newX;
    }

    public void setY(int newY)
    {
        this.y=newY;
    }

    /**
     *fwwfwffwf
     * @return gveeve
     */
    public int CMMMC() {
        int maxNum ;
        if(this.x > this.y)
            maxNum = this.x;
        else
            maxNum = this.y;
        while (true)
        {
            if (maxNum % this.x == 0 && maxNum % this.y == 0) {
                return maxNum;
            }
            maxNum++;
        }
    }

    public boolean areConsecutiveInFibonacci()
    {
        if (this.x > this.y)
        {
            int temp = this.x;
            this.x = this.y;
            this.y = temp;
        }
        int fib1 = 0;
        int fib2 = 1;
        while (fib2 < this.y)
        {
            int temp = fib1 + fib2;
            fib1 = fib2;
            fib2 = temp;

            if (fib1 == this.x && fib2 == this.y) {
                return true;
            }
        }
        return false;
    }

    public boolean cifreEgale()
    {
        int suma1=0,suma2=0;
        int temp;
        temp = this.x;
        while(temp>0)
        {
            suma1+=temp%10;
            temp/=10;
        }
        temp=this.y;
        while(temp>0)
        {
            suma2+=temp%10;
            temp/=10;
        }
        if(suma1 == suma2)
            return true;
        else
            return false;
    }

    public boolean NrEgalCifrePare()
    {
        int nr1=0,nr2=0;
        int temp,cifra;
        temp = this.x;
        while(temp>0)
        {
            cifra=temp%10;
            if(cifra % 2==0)
                nr1++;
            temp/=10;
        }
        temp=this.y;
        while(temp>0)
        {
            cifra=temp%10;
            if(cifra % 2==0)
                nr2++;
            temp/=10;
        }
        if(nr1 == nr2)
            return true;
        else
            return false;
    }

}
