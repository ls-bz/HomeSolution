import entidades.*;
import gui.PanelManager;

import java.util.LinkedList;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        HomeSolution homeSolution=new HomeSolution();
        String titulos[]={"Pintar","Instacion electrica","Trabajos jardineria","Instalar AA"};
        String descripciones[]={"","","",""};
        double duracion[]={4,2,1,.5};
        String cliente[]={"Pedro Gomez","",""};
        homeSolution.registrarProyecto(titulos,descripciones,duracion,"San Martin 1000",cliente,"2025-11-01","2025-11-05");
        homeSolution.registrarEmpleado("Juan",15000);
        homeSolution.registrarEmpleado("Luis",80000, "EXPERTO");
        homeSolution.registrarEmpleado("Julieta",15000);
        PanelManager panelManager=new PanelManager(homeSolution);

//        for (Proyecto p : homeSolution.proyectos.values()) {
//            Cliente c = p.getCliente();
//
//            System.out.println(c.getNombre());
//        }


    }
}
