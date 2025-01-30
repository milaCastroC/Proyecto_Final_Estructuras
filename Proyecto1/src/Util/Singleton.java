package Util;

import Modelo.Curso;
import Modelo.Laboratorio;
import Modelo.Persona;
import Modelo.Programa;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Singleton {

    private static final Singleton INSTANCIA = new Singleton();
    private IList<Programa> programas;
    private IList<Persona> usuarios;
    private IList<Curso> cursos;
    private Laboratorio[][] laboratorios;

    private Singleton() {
        programas = leerProgramas();
        usuarios = leerUsuarios();
        cursos = leerCursos();
        laboratorios = leerLaboratorios();
    }

    public static Singleton getINSTANCIA() {
        return INSTANCIA;
    }

    public IList<Programa> getProgramas() {
        return this.programas;
    }

    public IList<Persona> getUsuarios() {
        return usuarios;
    }

    public IList<Curso> getCursos() {
        return cursos;
    }

    public Laboratorio[][] getLaboratorios() {
        return laboratorios;
    }

    public void escribirProgramas(IList<Programa> programas) {
        try {
            FileOutputStream archivo = new FileOutputStream("programas.dat");
            ObjectOutputStream escritor = new ObjectOutputStream(archivo);
            escritor.writeObject(programas);
        } catch (IOException ex) {
//            ex.printStackTrace();
        }
    }

    public Listas<Programa> leerProgramas() {
        try {
            FileInputStream archivo = new FileInputStream("programas.dat");
            ObjectInputStream lector = new ObjectInputStream(archivo);
            return (Listas<Programa>) lector.readObject();
        } catch (IOException | ClassNotFoundException ex) {
//            ex.printStackTrace();
            return new Listas<>();
        }
    }

    public void escribirUsuarios() {
        try {
            FileOutputStream archivo = new FileOutputStream("usuarios.dat");
            ObjectOutputStream escritor = new ObjectOutputStream(archivo);
            escritor.writeObject(usuarios);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    

    public Listas<Persona> leerUsuarios() {
        try {
            FileInputStream archivo = new FileInputStream("usuarios.dat");
            ObjectInputStream lector = new ObjectInputStream(archivo);
            return (Listas<Persona>) lector.readObject();
        } catch (IOException | ClassNotFoundException ex) {
//            ex.printStackTrace();
            return new Listas<>();
        }
    }

    public void escribirCursos() {
        try {
            FileOutputStream archivo = new FileOutputStream("cursos.dat");
            ObjectOutputStream escritor = new ObjectOutputStream(archivo);
            escritor.writeObject(cursos);
        } catch (IOException ex) {
//            ex.printStackTrace();
        }
    }

    public Listas<Curso> leerCursos() {
        try {
            FileInputStream archivo = new FileInputStream("cursos.dat");
            ObjectInputStream lector = new ObjectInputStream(archivo);
            return (Listas<Curso>) lector.readObject();
        } catch (IOException | ClassNotFoundException ex) {
//            ex.printStackTrace();
            return new Listas<>();
        }
    }

    public void escribirLaboratorios() {
        try {
            FileOutputStream archivo = new FileOutputStream("laboratorios.dat");
            ObjectOutputStream escritor = new ObjectOutputStream(archivo);
            escritor.writeObject(laboratorios);

        } catch (IOException ex) {
//            ex.printStackTrace();
        }
    }

    public Laboratorio[][] leerLaboratorios() {
        try {
            FileInputStream archivo = new FileInputStream("laboratorios.dat");
            ObjectInputStream lector = new ObjectInputStream(archivo);
            return (Laboratorio[][]) lector.readObject();
        } catch (IOException | ClassNotFoundException ex) {
//            ex.printStackTrace();
            Laboratorio[][] laboratorios = new Laboratorio[3][];
            laboratorios[0] = new Laboratorio[5];
            laboratorios[1] = new Laboratorio[1];
            laboratorios[2] = new Laboratorio[5];
            return laboratorios;
        }
    }
    
}
