package org.example.panel;

import org.example.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ControlPanel extends JPanel {

    final MainFrame frame;
    /*am setat campurile unde se vor introduce date de catre utilizator , avand camp pentru numele clasei( insertClassName )
    *din care face parte componenta si un camp pentru a seta denumirea componentei(insertCompName) ce urmeaza a fi adaugata ,
    *iar butonul de addComponent odata actionat plaseaza componenta pe ecran
    */
    JLabel className = new JLabel("Numele clasei");
    JLabel componentName = new JLabel("Denumirea componentei");
    JTextField  insertClassName = new JTextField(30);
    JTextField insertCompName = new JTextField(15);
    JButton addComponent = new JButton("Adauga componenta");

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    /*
    * aceasta metoda adauga campurile mentionate anterior in partea rezervata panoului de control (NORTH),
    * iar odata apasat butonul de "adaugare componenta" se apeleaza metoda addNewComponent pentru a se realiza functionalitatea
    * butonului
    */
    private void init() {
        add(className);
        add(insertClassName);

        add(componentName);
        add(insertCompName);

        add(addComponent);
        addComponent.addActionListener(this:: addNewComponent);

    }
    /*
    * un obiect de tipul JComponent primeste rezultatul apelarii metodei createComponent,care are ca si parametru clasa introdusa
    * de catre utilizator in campul rezervat (insertClassName), metoda returneaza componenta in caz de succes sau null in caz de esec
    * in metoda addNewComponent se face verificarea daca rezultatul obtinut este null se va furniza un mesaj de eroare , altfel
    * inseamna ca acea componenta a fost creata si prin apelarea metodei setComponentName se seteaza denumirea componentei , luand valoare
    * din campul special rezervat (insertCompName) , iar apoi prin apelarea metodei setPositionOnPanel se stabileste o pozitie random
    * pe fereasta si componenta este adaugata
    */
    public void addNewComponent(ActionEvent e) {
        JComponent component = createComponent(insertClassName.getText());
        if (component == null) {
            System.out.println("Clasa introdusa nu este valida!");
        }
        else {
            setComponentName(component, insertCompName.getText());
            frame.designPanel.setPositionOnPanel(component);
        }
    }
   /*
   * metoda primeste ca si parametru clasa exacta al carui tip urmeaza a se construi componenta
   * prin apelarea metodei Class.ForName() se returneaza obiectul Class asociat cu clasa introdusa ca si parametru (className),
   * iar prin apelarea metodei newInstance() se creeaza o noua instanta a clasei reprezentate de obiectul Class si se returneaza
   * componenta  creata in caz de succes , in cazul in care este gasita o exceptie (nu s-a gasit clasa oferita ca si parametru,
   * s-au au aparut erori in crearea noii instante) se returneza null */
    private JComponent createComponent(String className) {
        try {
            Class objectName = Class.forName(className);
            return (JComponent) objectName.newInstance();
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
/*
* aceasta metoda primeste ca si parametrii componenta deja creata si denumirea pe care dorim sa i-o asociem
* prin apelarea metodei getMethod se obtine un obiect de tipul Method (de aceea am creat initial un obiect de acest tip
* caruia i-am asociat rezultatul metodei) prin care se doreste sa se obtina metoda specificata "setText" care urmeaza sa primeasca
* ca si parametru un obiect de tipul String
* apoi este apelata metoda invoke cu scopul de a invoca metoda definita anterior , aceasta are ca si parametrii instanta obiect
* (componenta ce urmeaza a fi adaugata pe ecran ) pentru care se invoca metoda si parametru functiei ( setText (componentName)  ),
* in acelasi timp tratandu-se si cazurile de eroare( de exemplu metoda pe care incercam sa o utilizam in mod dinamic nu exista)
* astfel componentei ii este asociata denumirea introdusa de catre utilizator
*/
    private void setComponentName(JComponent component, String componentName) {
        Class objectName = component.getClass();
        Method a ;
        try {
            a = objectName.getMethod("setText",String.class);
            a.invoke(component, componentName);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}


