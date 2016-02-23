import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * Created by ������� �����-������ on 08.05.2015.
 */
public class TextRedactor {
    private File lastfile;
    public void build() {

        JFrame frame = new JFrame("Text");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 400, 300);

        JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(textArea,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JMenuBar menu = new JMenuBar();
        JMenu menubar = new JMenu("Menu");
        JMenuItem open = new JMenuItem("Open");
        JMenuItem newfile = new JMenuItem("New");
        JMenuItem save = new JMenuItem("Save");
        JMenuItem saveAs = new JMenuItem("Save as");
        menubar.add(newfile);
        menubar.add(open);
        menubar.add(save);
        menubar.add(saveAs);
        menu.add(menubar);

        frame.add(BorderLayout.CENTER, scrollPane);

        frame.setJMenuBar(menu);
        frame.setVisible(true);

        newfile.addActionListener(e -> {


            JFrame quest = new JFrame("Please, choose");
            quest.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            quest.setBounds(100, 100, 400, 300);

            JLabel issaving = new JLabel("Save file before closing?");
JPanel panel = new JPanel();

            JButton ok = new JButton("OK");
            JButton no = new JButton("No");
            panel.add(ok);
            panel.add(no);
            ok.addActionListener(e1 -> {
                JFileChooser chooser = new JFileChooser();
                int returnValue = chooser.showOpenDialog(frame);
                try {
                    if (returnValue == JFileChooser.APPROVE_OPTION) {
                        File file = chooser.getSelectedFile();

                        PrintWriter writer = new PrintWriter(file);
                        writer.println(textArea.getText());
                        writer.close();
                    }
                } catch (IOException g) {
                    g.printStackTrace();
                }


            });
            no.addActionListener(e1 -> {
                quest.dispose();
                lastfile = null;
                textArea.setText("");

            });
            quest.add(BorderLayout.CENTER, issaving);
            quest.add(BorderLayout.SOUTH,panel);
            quest.setVisible(true);
            lastfile = null;
            textArea.setText("");

        });


                open.addActionListener(e -> {
                    JFileChooser chooser = new JFileChooser();
                    int returnValue = chooser.showOpenDialog(frame);
                    if (returnValue == JFileChooser.APPROVE_OPTION) {
                        try {
                            File file = chooser.getSelectedFile();
                            String path = file.getAbsolutePath();
                            BufferedReader reader = new BufferedReader(
                                    new InputStreamReader(new FileInputStream(path))
                            );
                            String text;
                            while ((text = reader.readLine()) != null) {
                                textArea.setText(textArea.getText() + text + "\n");
                            }
                            lastfile = file;
                            reader.close();
                        } catch (IOException d) {
                            d.printStackTrace();
                        }

                    }

                });


                saveAs.addActionListener(e -> {
                    JFileChooser chooser = new JFileChooser();
                    int returnValue = chooser.showOpenDialog(frame);
                    try {
                        if (returnValue == JFileChooser.APPROVE_OPTION) {
                            File file = chooser.getSelectedFile();

                            PrintWriter writer = new PrintWriter(file);
                            writer.println(textArea.getText());
                            writer.close();
                        }
                    } catch (IOException g) {
                        g.printStackTrace();
                    }
                });

                save.addActionListener(e -> {

                    try {
                        if (lastfile != null) {
                            PrintWriter writer = new PrintWriter(lastfile);
                            writer.println(textArea.getText());
                            writer.close();
                        } else {
                            JFileChooser chooser = new JFileChooser();
                            int returnValue = chooser.showOpenDialog(frame);
                            try {
                                if (returnValue == JFileChooser.APPROVE_OPTION) {
                                    File file = chooser.getSelectedFile();

                                    PrintWriter writer = new PrintWriter(file);
                                    writer.println(textArea.getText());
                                    writer.close();
                                }
                            } catch (IOException g) {
                                g.printStackTrace();
                            }

                        }
                    } catch (IOException g) {
                        g.printStackTrace();
                    }
                });
            }
        }



