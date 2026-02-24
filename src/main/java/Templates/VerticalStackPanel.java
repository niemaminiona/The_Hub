package Templates;

import javax.swing.*;

public class VerticalStackPanel extends JPanel {
    public VerticalStackPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
}
