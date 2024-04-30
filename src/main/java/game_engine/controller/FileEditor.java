package game_engine.controller;

import java.io.*;


/**
 * Class implements an editor which can read and write files.
 *
 * @author      Christian-2003
 * @deprecated  This class was part of the demo and is no longer needed.
 */
public class FileEditor {

    /**
     * Attribute stores the path to the file which shall be edited.
     */
    private final String filepath;


    /**
     * Constructor instantiates a new FileEditor which edits the file with the specified
     * file path.
     *
     * @param filepath              Path of the file to be edited.
     * @throws NullPointerException The passed path is {@code null}.
     */
    public FileEditor(String filepath) throws NullPointerException {
        if (filepath == null) {
            throw new NullPointerException("Null is invalid file path.");
        }
        this.filepath = filepath;
    }


    /**
     * Method reads the content of the file and returns them as String.
     *
     * @return              Content of the file.
     * @throws IOException  The file could not be read.
     */
    public String read() throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            while (reader.ready()) {
                content.append(reader.readLine());
                content.append("\n");
            }
        }
        return content.toString();
    }


    /**
     * Method writes the passed content to the file. The parameter {@code append} indicates whether the
     * content shall be appended to the file, if it already exists.
     *
     * @param content               Content to be written to the file.
     * @param append                Whether the content shall be appended to the already existing file.
     * @throws NullPointerException The passed content is {@code null}.
     * @throws IOException          The file could not be written.
     */
    public void write(String content, boolean append) throws NullPointerException, IOException {
        if (content == null) {
            throw new NullPointerException("Null is invalid content");
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath, append))) {
            writer.write(content);
        }
    }


    /**
     * Method writes the passed content to the file. The previous content of the file will be overwritten when
     * this method is called.
     *
     * @param content               Content to be written to the file.
     * @throws NullPointerException The passed content is {@code null}.
     * @throws IOException          The file could not be written.
     */
    public void write(String content) throws NullPointerException, IOException {
        write(content, false);
    }

}
