package a02a.e2;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.util.random.RandomGenerator;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<Pair<Integer, Integer>, JButton> cells = new HashMap<>();
    private int counter = 0;
    private boolean first;
    private Controller controller;
    
    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(50*size, 50*size);
        this.first = true;
        this.controller = new ControllerImpl(size);
        
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
        
        ActionListener al = e -> {
            if (first){
            	Pair<Integer, Integer> randomCoord = new Pair<Integer,Integer>(new Random().nextInt(size), new Random().nextInt(size));
                cells.get(randomCoord).setText(String.valueOf(counter++));
                controller.setCurrentLocation(randomCoord);
                first = false;
            }
            else{
                cells.get(controller.hit()).setText(String.valueOf(counter++));
                controller.isOver();
            }
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
            	var pos = new Pair<>(j,i);
                final JButton jb = new JButton(" ");
                this.cells.put(pos, jb);
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }

}
