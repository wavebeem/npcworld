import java.awt.*;
import javax.swing.JComponent;

/*
 * @author tgriswol
 */
public interface Individual {
    public Dna getDna();
    public JComponent getWidget();
    public int getAge();
}
