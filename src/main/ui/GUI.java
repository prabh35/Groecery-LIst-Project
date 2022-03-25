package ui;

import model.Product;
import model.ProductCatalogue;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Graphic-user interface
public class GUI implements ActionListener {

    private ProductCatalogue productCatalogue;
    private JTextField productName;
    private JTextField toBeSold;
    private JTextField quantity;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/workroom.json";
    private JFrame frame;
    private JPanel panel;

    //EFFECTS: creates the frame and panel
    public GUI() {

        productCatalogue = new ProductCatalogue();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        frame = new JFrame("Product-Catalogue Interface");
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JPanel();
        jbutton();
        frame.add(panel);
        frame.setVisible(true);
    }

    //EFFECTS: creates buttons on panel
    private void jbutton() {
        JButton add = new JButton("Add");
        JButton view = new JButton("View");
        JButton save = new JButton("Save");
        JButton load = new JButton("Load");

        add.setActionCommand("add product");
        add.addActionListener(this);

        view.setActionCommand("view product");
        view.addActionListener(this);

        save.setActionCommand("save product");
        save.addActionListener(this);

        load.setActionCommand("load product");
        load.addActionListener(this);

        panel.add(add);
        panel.add(view);
        panel.add(save);
        panel.add(load);

    }

    //EFFECTS: creates the add frame and panel
    private void addFrame() {

        frame = new JFrame();
        frame.setSize(400, 400);

        panel = new JPanel();

        productName = new JTextField();
        toBeSold = new JTextField();
        quantity = new JTextField();
        productName.setBounds(70,20,30,35);
        toBeSold.setBounds(70,60,30,35);
        quantity.setBounds(70,100,30,35);

        toBeContinued();


    }

    //EFFECTS: set labels and add button
    private void toBeContinued() {

        JLabel label1 = new JLabel("Product Name");
        JLabel label2 = new JLabel("To Be Sold");
        JLabel label3 = new JLabel("Quantity");
        label1.setBounds(100,20,100,35);
        label2.setBounds(100,60,100,35);
        label3.setBounds(100,100,100,35);
        panel.add(label1);
        panel.add(label2);
        panel.add(label3);

        panel.add(productName);
        panel.add(toBeSold);
        panel.add(quantity);

        JButton add = new JButton("Add");
        add.setActionCommand("add");
        add.addActionListener(this);
        add.setBounds(200,300,30,30);
        toBeContinued();

        panel.add(add);
        panel.setLayout(null);

        frame.add(panel);
        frame.setVisible(true);
    }

    //REQUIRES: add product everytime
    //EFFECTS: adds product with product name, quantity and toBeSold
    private void add() {
        int i1 = Integer.parseInt(toBeSold.getText());
        int i2 = Integer.parseInt(quantity.getText());
        Product product = new Product(productName.getText(), i1, i2);
        productCatalogue.addProduct(product);
    }


    //EFFECTS: creates the view frame and panel
    public void viewFrame() {
        frame = new JFrame();
        panel = new JPanel();
        String str;
        List<String> formatted = new ArrayList<>();
        if (productCatalogue.viewCatalogue() != null) {

            for (Product p : productCatalogue.viewCatalogue()) {
                str = "Product Name : " + p.getProductName() + " " + " Quantity :" + p.getQuantity()
                        + "To Be Sold : " + p.productLeft();

                formatted.add(str);
            }
        }

        JList<Object> j = new JList<>(formatted.toArray());
        panel.add(j);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }


    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadProduct() {
        try {
            productCatalogue = jsonReader.read();
            System.out.println("Loaded " + productCatalogue + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: saves the workroom to file
    private void saveProduct() {
        try {
            jsonWriter.open();
            jsonWriter.write(productCatalogue);
            jsonWriter.close();
            System.out.println("Saved " + productCatalogue + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    @Override
    //EFFECTS: action listener
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("add product")) {
            addFrame();
        } else if (e.getActionCommand().equals("add")) {
            add();
        } else if (e.getActionCommand().equals("view product")) {
            viewFrame();
        } else if (e.getActionCommand().equals("load product")) {
            loadProduct();
        } else if (e.getActionCommand().equals("save product")) {
            saveProduct();
        } else if (e.getActionCommand().equals("back")) {
            frame.dispose();
        }

    }

    //EFFECTS: runs the GUI
    public static void main(String[] args) {
        new GUI();
    }
}
