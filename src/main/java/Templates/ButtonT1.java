package Templates;

import javax.swing.*;
import java.awt.*;

public class ButtonT1 extends JButton {
    public ButtonT1(){
        this("");
    }
    public ButtonT1(String text){
        this.setText(text);
        this.setPreferredSize(new Dimension(180, 70));
        this.setFocusPainted(false);
        this.setFont(new Font("SansSerif", Font.BOLD, 22));
    }
}

