package br.edu.up.allanhousequest.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {
    
    public static void saveFile(String nameFile, String conteudo) throws IOException {
        FileWriter writer = new FileWriter(new File(nameFile));
        writer.write(conteudo);
        writer.close();
    }

    public static String readFile(String nameFile) throws IOException {
        // Implementação para ler de arquivo
        return null;
    }
}
