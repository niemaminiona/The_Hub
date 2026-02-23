package Templates;

import javax.swing.*;

public class HorizontalStackPanel extends JPanel {
    public HorizontalStackPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
}
