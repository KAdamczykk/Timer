package mini;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Okno extends JFrame implements Runnable{
    protected JPanel northPanel;
    protected JButton button;
    protected JSlider slider;
    protected JPanel centralPanel;
    protected int counter = 0;
    protected int wlaczone = 0;



    public Okno(){
        // Preferencje Okna
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500,500);
        this.setLocation(300,200);
        this.setLayout(new BorderLayout());
        this.setTitle("L I C Z N I K");
        this.setBackground(Color.lightGray);
        // Panel z suakiem i buttonem
        northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());
        northPanel.setSize(500,100);
        button = new JButton();
        button.setText("NIE KLIKAJ");
        button.setFont(button.getFont().deriveFont(15f));
        slider = new JSlider(1,3,1);
        slider.setPreferredSize(new Dimension(200,90));
        slider.setMajorTickSpacing(1);
        slider.setPaintLabels(true);
        button.setPreferredSize(new Dimension(120,50));
        northPanel.add(button);
        northPanel.add(slider);
        this.add(northPanel, BorderLayout.NORTH);

        // Panel na labele
        centralPanel = new JPanel();
        centralPanel.setSize(500,300);
        centralPanel.setLayout(new GridLayout(5,1,5,0));
        centralPanel.setBorder(new LineBorder(Color.RED));
        addListeners();
        this.add(centralPanel, BorderLayout.CENTER);

        this.setVisible(true);


    }


    // Dodanie Listenerow na suwak i button
    private void addListeners(){
        button.addActionListener(e -> {
            if (counter <5) {
                Thread thread = new Thread(this);
                thread.start();
                counter++;
            } else {
                button.setEnabled(false);
                button.setText("MAX 5");
                //System.out.println("Za duzo przyciskow");
            }
        });
        slider.addChangeListener(e -> {
            slider.setValue(slider.getValue());
        });

    }

    // Tworzenie nowych licznikow
    @Override
    public void run() {
        JLabel label = new JLabel();
        label.setSize(500,80);
        label.setOpaque(true);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(new LineBorder(Color.RED));
        label.setFont(label.getFont().deriveFont(16f));
        wlaczone++;
        centralPanel.add(label);
        for (int i = 10; i >-1; i--){
            label.setText("" + i);
            try {
                Thread.sleep(1000L * slider.getValue());
            } catch (InterruptedException e) {
                System.out.println("Blad jakis");
            }
        }
        wlaczone--;
        if (wlaczone == 0){
            button.setText("KONIEC");
            button.setEnabled(false);
        }


    }
}