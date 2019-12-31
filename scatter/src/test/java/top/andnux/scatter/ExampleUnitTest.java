package top.andnux.scatter;

import org.bouncycastle.util.encoders.Hex;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        byte[] decode = Hex.decode("1a691ade3960010ec3963bb105d0c48d156571480012bcd3fbcdc93d0cfe9d78993d592dbd08e8e53");
        String s = new String(decode);
        System.out.println(s);
    }
}