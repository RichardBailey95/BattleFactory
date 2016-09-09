import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

import javax.imageio.ImageIO;
import javax.swing.*;

public class BattleFactory {
    private static BattleFactory season1;

    private List<String> national;
    private List<String> OU;
    private List<String> UU;
    private List<String> RU;
    private List<String> NU;
    private List<String> PU;
    private List<String> NFE;
    private Random generator;

    private static JPanel display;

    private static JRadioButton nfeButton;
    private static JRadioButton nuButton;
    private static JRadioButton puButton;
    private static JRadioButton ouButton;
    private static JRadioButton ruButton;
    private static JRadioButton uuButton;
    private static JRadioButton nationalButton;

    private BattleFactory(){
        this.generator = new Random();
    }

    private static void displayGui() throws MalformedURLException{
        JFrame window = new JFrame("Battle Factory");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        display = new JPanel(new FlowLayout());

        JButton generate = new JButton("Generate");
        generate.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                try {
                    displayTeam(season1.generateTeam(generateList()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }});

        JButton swaps = new JButton("Swaps");
        swaps.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                try {
                    displayTeam(season1.generateSwaps());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }});

        display.add(generate);
        display.add(swaps);

        nfeButton = new JRadioButton("NFE");
        nuButton = new JRadioButton("NU");
        puButton = new JRadioButton("PU");
        ouButton = new JRadioButton("OU");
        uuButton = new JRadioButton("UU");
        ruButton = new JRadioButton("RU");
        nationalButton = new JRadioButton("All");

        display.add(nfeButton);
        display.add(nuButton);
        display.add(puButton);
        display.add(ruButton);
        display.add(uuButton);
        display.add(ouButton);
        display.add(nationalButton);

        window.getContentPane().add(display, BorderLayout.CENTER);

        window.pack();
        window.setVisible(true);
    }

    private static List<String> generateList(){
        List<String> temp = new ArrayList<String>();
        if(nfeButton.isSelected()){
            temp.addAll(season1.NFE);
        }
        if(puButton.isSelected()){
            temp.addAll(season1.PU);
        }
        if(nuButton.isSelected()){
            temp.addAll(season1.NU);
        }
        if(ruButton.isSelected()){
            temp.addAll(season1.RU);
        }
        if(uuButton.isSelected()){
            temp.addAll(season1.UU);
        }
        if(ouButton.isSelected()){
            temp.addAll(season1.OU);
        }
        if(nationalButton.isSelected()){
            return(season1.national);
        }
        return(temp);
    }

    private static void displayTeam(List<JLabel> list){
        JFrame window = new JFrame("Battle Factory Generated Team");
        JPanel displayTeam = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        for(int i=0; i<list.size(); i=i+2){
            c.gridx=i;
            displayTeam.add(list.get(i), c);
        }
        c.gridy=1;
        for(int i=1; i<list.size(); i=i+2){
            c.gridx=(i-1);
            displayTeam.add(list.get(i), c);
        }

        window.getContentPane().add(displayTeam, BorderLayout.CENTER);

        window.pack();
        window.setVisible(true);
    }

    private void nationalDex(){
        Scanner s = null;
        try {
            s = new Scanner(new File("resources/lists/national.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        national = new ArrayList<String>();
        while (s.hasNextLine()){
            national.add(s.nextLine());
        }
        s.close();
    }

    private void nfeTier(){
        Scanner s = null;
        try {
            s = new Scanner(new File("resources/lists/nfe.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        NFE = new ArrayList<String>();
        while (s.hasNextLine()){
            NFE.add(s.nextLine());
        }
        s.close();
    }

    private void puTier(){
        Scanner s = null;
        try {
            s = new Scanner(new File("resources/lists/pu.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PU = new ArrayList<String>();
        while (s.hasNextLine()){
            PU.add(s.nextLine());
        }
        s.close();
    }

    private void nuTier(){
        Scanner s = null;
        try {
            s = new Scanner(new File("resources/lists/nu.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        NU = new ArrayList<String>();
        while (s.hasNextLine()){
            NU.add(s.nextLine());
        }
        s.close();
    }

    private void ouTier(){
        Scanner s = null;
        try {
            s = new Scanner(new File("resources/lists/ou.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        OU = new ArrayList<String>();
        while (s.hasNextLine()){
            OU.add(s.nextLine());
        }
        s.close();
    }

    private void uuTier(){
        Scanner s = null;
        try {
            s = new Scanner(new File("resources/lists/uu.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        UU = new ArrayList<String>();
        while (s.hasNextLine()){
            UU.add(s.nextLine());
        }
        s.close();
    }

    private void ruTier(){
        Scanner s = null;
        try {
            s = new Scanner(new File("resources/lists/ru.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        RU = new ArrayList<String>();
        while (s.hasNextLine()){
            RU.add(s.nextLine());
        }
        s.close();
    }

    private void populateTiers() {
        nfeTier();
        puTier();
        nuTier();
        ruTier();
        uuTier();
        ouTier();
        nationalDex();
    }

    private List<JLabel> generateTeam(List<String> list) throws IOException {
        List<JLabel> temp = new ArrayList<JLabel>();
        for(int i=0; i<6; i++){
            int pokemon = generator.nextInt(list.size());
            URL imageURL =  new URL("file", "localhost", "resources/sprites/"+list.get(pokemon)+".gif");
            ImageIcon icon = new ImageIcon(imageURL);
            JLabel label = new JLabel(icon);
            temp.add(label);
            label = new JLabel(list.get(pokemon));
            temp.add(label);
            list.remove(pokemon);
        }
        nationalDex();
        return(temp);
    }

    private List<JLabel> generateSwaps() throws MalformedURLException {
        List<JLabel> temp = new ArrayList<JLabel>();
        if(nfeButton.isSelected()){
            int pokemon = generator.nextInt(NFE.size());
            System.out.println(NFE.get(pokemon));
            URL imageURL = new URL("file", "localhost", "resources/sprites/"+NFE.get(pokemon)+".gif");
            ImageIcon icon = new ImageIcon(imageURL);
            JLabel label = new JLabel(icon);
            temp.add(label);
            label = new JLabel(NFE.get(pokemon));
            temp.add(label);
        }
        if(nuButton.isSelected()){
            int pokemon = generator.nextInt(NFE.size());
            URL imageURL =  new URL("file", "localhost", "resources/sprites/"+NU.get(pokemon)+".gif");
            ImageIcon icon = new ImageIcon(imageURL);
            JLabel label = new JLabel(icon);
            temp.add(label);
            label = new JLabel(NU.get(pokemon));
            temp.add(label);
        }
        if(puButton.isSelected()){
            int pokemon = generator.nextInt(PU.size());
            URL imageURL =  new URL("file", "localhost", "resources/sprites/"+PU.get(pokemon)+".gif");
            ImageIcon icon = new ImageIcon(imageURL);
            JLabel label = new JLabel(icon);
            temp.add(label);
            label = new JLabel(PU.get(pokemon));
            temp.add(label);
        }
        if(ruButton.isSelected()){
            int pokemon = generator.nextInt(RU.size());
            URL imageURL =  new URL("file", "localhost", "resources/sprites/"+RU.get(pokemon)+".gif");
            ImageIcon icon = new ImageIcon(imageURL);
            JLabel label = new JLabel(icon);
            temp.add(label);
            label = new JLabel(RU.get(pokemon));
            temp.add(label);
        }
        if(uuButton.isSelected()){
            int pokemon = generator.nextInt(UU.size());
            URL imageURL =  new URL("file", "localhost", "resources/sprites/"+UU.get(pokemon)+".gif");
            ImageIcon icon = new ImageIcon(imageURL);
            JLabel label = new JLabel(icon);
            temp.add(label);
            label = new JLabel(UU.get(pokemon));
            temp.add(label);
        }
        if(ouButton.isSelected()){
            int pokemon = generator.nextInt(OU.size());
            URL imageURL = new URL("file", "localhost", "resources/sprites/"+OU.get(pokemon)+".gif");
            ImageIcon icon = new ImageIcon(imageURL);
            JLabel label = new JLabel(icon);
            temp.add(label);
            label = new JLabel(OU.get(pokemon));
            temp.add(label);
        }
        return(temp);
    }

    public static void main(String[] args) {
        season1 = new BattleFactory();
        season1.populateTiers();
        try {
            displayGui();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }
}
