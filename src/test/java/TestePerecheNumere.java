import problema1.PerecheNumere;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
public class TestePerecheNumere
{
    @Test
    void test1_CMMMC()
    {
        PerecheNumere p = new PerecheNumere(12,18);
        assertEquals(36, p.CMMMC());
    }

    @Test
    void test2_CMMMC()
    {
        PerecheNumere p =new PerecheNumere(14, 21);
        assertTrue(p.CMMMC() == 42);
    }

    @Test
    void test3_CMMMC()
    {
        PerecheNumere p = new PerecheNumere(10,20);
        assertFalse(p.CMMMC() != 20);
    }

    @Test
    void test1_NrEgalCifrePare()
    {
        PerecheNumere p = new PerecheNumere(2455, 1344);
        assertEquals(true,p.NrEgalCifrePare());
    }

    @Test
    void test2_NrEgalCifrePare()
    {
        PerecheNumere p = new PerecheNumere(2465, 2344);
        assertTrue(true==p.NrEgalCifrePare());
    }

    @Test
    void test3_NrEgalCifrePare()
    {
        PerecheNumere p = new PerecheNumere(2111, 2377);
        assertFalse(true!=p.NrEgalCifrePare());
    }

    @Test
    void test1_cifreEgale()
    {
        PerecheNumere p = new PerecheNumere(15, 33);
        assertEquals(true,p.cifreEgale());
    }

    @Test
    void test2_cifreEgale()
    {
        PerecheNumere p = new PerecheNumere(15, 33);
        assertTrue(true == p.cifreEgale());
    }

    @Test
    void test3_cifreEgale()
    {
        PerecheNumere p = new PerecheNumere(25, 33);
        assertFalse(true == p.cifreEgale());
    }
}
