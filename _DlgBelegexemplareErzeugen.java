/*
 *
 * Das JAVA-Programm Miles-Verlag Verlagsverwaltung stellt alle notwendigen
 * Funktionen f�r die Verwaltung des Carola Hartman Miles-Verlags bereit.
 *
 * Copyright (C) 2017 EDV-Beratung und Betrieb, Entwicklung von SOftware
 *                    Dipl.Inform Thomas Zimmermann
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
package milesVerlagMain;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.swing.*;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;

/**
 *
 * @author Thomas Zimmermann
 */
public class _DlgBelegexemplareErzeugen extends javax.swing.JDialog {

    /**
     * Creates new form _DlgRezensionErzeugen
     *
     * @param parent
     * @param modal
     */
    public _DlgBelegexemplareErzeugen(java.awt.Frame parent, boolean modal) {
        super(parent, modal);

        conn = null;

        // Datenbank-Treiber laden
        try {
            Class.forName(Modulhelferlein.dbDriver);
        } catch (ClassNotFoundException exept) {
            Modulhelferlein.Fehlermeldung("ClassNotFoundException: Treiber nicht gefunden. " + exept.getMessage());
        }

        // Verbindung zur Datenbank �ber die JDBC-Br�cke
        try {
            conn = DriverManager.getConnection(Modulhelferlein.dbUrl, Modulhelferlein.dbUser, Modulhelferlein.dbPassword);
        } catch (SQLException exept) {
            Modulhelferlein.Fehlermeldung("SQL-Exception: Verbindung zur Datenbank nicht moeglich. " + exept.getMessage());
        }

        try {
            SQLAnfrage = null;    // Anfrage erzeugen
            SQLAnfrage = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            result = SQLAnfrage.executeQuery("SELECT * FROM tbl_adresse WHERE ADRESSEN_Typ = 'Autor' ORDER BY ADRESSEN_NAME");

            while (result.next()) {
                eintrag = Integer.toString(result.getInt("ADRESSEN_ID")) + ", " + result.getString("ADRESSEN_Name")
                        + ", " + result.getString("ADRESSEN_Vorname");
                listModel.addElement(eintrag);

            }    // while

        } catch (SQLException exept) {
            Modulhelferlein.Fehlermeldung("SQL-Exception: Verbindung zur Datenbank nicht moeglich. " + exept.getMessage());
        }        // try

        initComponents();

        Format.add(rbPDF);
        Format.add(rbDOC);

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        panel1 = new JPanel();
        jLabel1 = new JLabel();
        Erstellen = new JButton();
        Schliessen = new JButton();
        jLabel2 = new JLabel();
        scrollPane = new JScrollPane();
        Rezensenten = new JList<>();
        jLabel3 = new JLabel();
        rbPDF = new JRadioButton();
        rbDOC = new JRadioButton();

        //======== this ========
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Carola Hartmann Miles Verlag");
        setResizable(false);
        Container contentPane = getContentPane();

        //======== panel1 ========
        {

            //---- jLabel1 ----
            jLabel1.setFont(new Font("Tahoma", Font.BOLD, 12));
            jLabel1.setText("Belegexemplare erzeugen");

            //---- Erstellen ----
            Erstellen.setText("Erstellen");
            Erstellen.addActionListener(e -> ErstellenActionPerformed(e));

            //---- Schliessen ----
            Schliessen.setText("Schlie\u00dfen");
            Schliessen.addActionListener(e -> SchliessenActionPerformed(e));

            //---- jLabel2 ----
            jLabel2.setText("Liste der Autoren bzw. Mitarbeiter");

            //======== scrollPane ========
            {

                //---- Rezensenten ----
                Rezensenten.setModel(listModel);
                scrollPane.setViewportView(Rezensenten);
            }

            //---- jLabel3 ----
            jLabel3.setText("Ausgabeformat");

            //---- rbPDF ----
            rbPDF.setSelected(true);
            rbPDF.setText("PDF");

            //---- rbDOC ----
            rbDOC.setText("DOC");

            GroupLayout panel1Layout = new GroupLayout(panel1);
            panel1.setLayout(panel1Layout);
            panel1Layout.setHorizontalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                        .addContainerGap(17, Short.MAX_VALUE)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addComponent(jLabel1)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(183, 183, 183)
                                .addComponent(jLabel3))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 303, GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addGroup(panel1Layout.createParallelGroup()
                                    .addComponent(rbPDF)
                                    .addComponent(rbDOC)))
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(Erstellen)
                                .addGap(18, 18, 18)
                                .addComponent(Schliessen)))
                        .addContainerGap())
            );
            panel1Layout.setVerticalGroup(
                panel1Layout.createParallelGroup()
                    .addGroup(panel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(6, 6, 6)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
                            .addGroup(panel1Layout.createSequentialGroup()
                                .addComponent(rbPDF)
                                .addGap(3, 3, 3)
                                .addComponent(rbDOC)))
                        .addGap(6, 6, 6)
                        .addGroup(panel1Layout.createParallelGroup()
                            .addComponent(Erstellen)
                            .addComponent(Schliessen))
                        .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            );
        }

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(panel1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addComponent(panel1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 278, Short.MAX_VALUE)
        );
        pack();
        setLocationRelativeTo(getOwner());
    }// </editor-fold>//GEN-END:initComponents

    private void ErstellenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ErstellenActionPerformed
        // TODO add your handling code here:
        Integer Auswahl = 0;

        if (JOptionPane.showConfirmDialog(null, "Sollen die Briefe wirklich erstellt werden?", "Best�tigung",
                JOptionPane.YES_NO_OPTION) == 0) {

            // liste der markierten Eintr�ge holen
            // int auswahlliste[] = Rezensenten.getSelectedIndices();
            List<String> auswahlliste = Rezensenten.getSelectedValuesList();

            // geht die Liste durch
            // geht die Liste durch
            for (int i = 0, n = auswahlliste.size(); i < n; i++) {

                // for (int i = 0, n = auswahlliste.length; i < n; i++) {
                String strAuswahl = auswahlliste.get(i);
//helferlein.Infomeldung(strAuswahl);
                // String strAuswahl = (String)Rezensenten.getModel().getElementAt(auswahlliste[i]);
                String[] splitRezensent = strAuswahl.split(",");
//helferlein.Infomeldung(splitRezensent[0]);

                // ruft jeweils die Adressen des Rezensenten ab
                try {
                    result = SQLAnfrage.executeQuery("SELECT * FROM tbl_adresse WHERE ADRESSEN_ID = "
                            + splitRezensent[0]);
                    result.next();

                    // und ruft briefRezension auf
                    try {
//helferlein.Infomeldung(Autor); 
                        if (rbPDF.isSelected()) {
                            Modulhelferlein.Infomeldung("Schreibe Brief f�r " + splitRezensent[1]);
                            briefBelegexemplar.briefPDF(Anrede, Autor, Titel, Beschreibung, ISBN,
                                    splitRezensent[1], result.getString("ADRESSEN_ZEITSCHRIFT"),
                                    result.getString("ADRESSEN_ZUSATZ_1"),
                                    result.getString("ADRESSEN_ZUSATZ_2"),
                                    result.getString("ADRESSEN_NAMENSZUSATZ"),
                                    result.getString("ADRESSEN_VORNAME"), result.getString("ADRESSEN_NAME"),
                                    result.getString("ADRESSEN_STRASSE"),
                                    result.getString("ADRESSEN_PLZ") + " " + result.getString("ADRESSEN_ORT"),
                                    result.getString("ADRESSEN_ANREDE"), Preis, Seiten);
                            Modulhelferlein.Infomeldung("Schreibe Pseude-Rechnung f�r " + splitRezensent[1]);
                            briefRechnungMahnung.briefPDF(Modulhelferlein.makeBestellung(result.getString("ADRESSEN_ZEITSCHRIFT"),
                                    result.getString("ADRESSEN_ZUSATZ_1"),
                                    result.getString("ADRESSEN_ZUSATZ_2"),
                                    result.getString("ADRESSEN_NAMENSZUSATZ")
                                    + result.getString("ADRESSEN_VORNAME") + " "
                                    + result.getString("ADRESSEN_NAME"),
                                    result.getString("ADRESSEN_STRASSE"),
                                    result.getString("ADRESSEN_PLZ") + " " + result.getString("ADRESSEN_ORT"),
                                    GBuch,
                                    GTyp, GAnzahl),
                                    3,
                                    "",
                                    0);
                        } else {
                            briefBelegexemplar.briefDOC(Anrede, Autor, Titel, Beschreibung, ISBN,
                                    splitRezensent[1], result.getString("ADRESSEN_ZEITSCHRIFT"),
                                    result.getString("ADRESSEN_ZUSATZ_1"),
                                    result.getString("ADRESSEN_ZUSATZ_2"),
                                    result.getString("ADRESSEN_NAMENSZUSATZ"),
                                    result.getString("ADRESSEN_VORNAME"), result.getString("ADRESSEN_NAME"),
                                    result.getString("ADRESSEN_STRASSE"),
                                    result.getString("ADRESSEN_PLZ") + " " + result.getString("ADRESSEN_ORT"),
                                    result.getString("ADRESSEN_ANREDE"), Preis, Seiten);

                            briefRechnungMahnung.briefDOC(Modulhelferlein.makeBestellung(result.getString("ADRESSEN_ZEITSCHRIFT"),
                                    result.getString("ADRESSEN_ZUSATZ_1"),
                                    result.getString("ADRESSEN_ZUSATZ_2"),
                                    result.getString("ADRESSEN_NAMENSZUSATZ")
                                    + result.getString("ADRESSEN_VORNAME") + " "
                                    + result.getString("ADRESSEN_NAME"),
                                    result.getString("ADRESSEN_STRASSE"),
                                    result.getString("ADRESSEN_PLZ") + " " + result.getString("ADRESSEN_ORT"),
                                    GBuch,
                                    GTyp, GAnzahl),
                                    3,
                                    "");
                        }
                    } catch (Exception e) {
                        Modulhelferlein.Fehlermeldung("Exception: " + e.getMessage());
                    }    // try
                } catch (SQLException e1) {
                    Modulhelferlein.Fehlermeldung("SQL-Exception: " + e1.getMessage());
                }        // schickt SQL an DB und erzeugt ergebnis -> wird in result gespeichert
            } // for
        } // if
        this.dispose();
    }//GEN-LAST:event_ErstellenActionPerformed

    private void SchliessenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SchliessenActionPerformed
        // TODO add your handling code here:
        try {
            SQLAnfrage.close();
            conn.close();
        } catch (SQLException exept) {
            Modulhelferlein.Fehlermeldung("SQL-Exception: " + exept.getMessage());
        }
        this.dispose();
    }//GEN-LAST:event_SchliessenActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */

        Anrede = args[0];
        Autor = args[1];
        Titel = args[2];
        Beschreibung = args[3];
        ISBN = args[4];
        Preis = args[5];
        Seiten = args[6];
        GBuch = Integer.parseInt(args[7]);
        GTyp = Integer.parseInt(args[8]);
        GAnzahl = Integer.parseInt(args[9]);

        /**
         * try { for (javax.swing.UIManager.LookAndFeelInfo info :
         * javax.swing.UIManager.getInstalledLookAndFeels()) { if
         * ("Nimbus".equals(info.getName())) {
         * javax.swing.UIManager.setLookAndFeel(info.getClassName()); break; } }
         * } catch (ClassNotFoundException | InstantiationException |
         * IllegalAccessException | javax.swing.UnsupportedLookAndFeelException
         * ex) {
         * java.util.logging.Logger.getLogger(CarolaHartmannMilesVerlag.class.getName()).log(java.util.logging.Level.SEVERE,
         * null, ex); }
         */
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            _DlgBelegexemplareErzeugen dialog = new _DlgBelegexemplareErzeugen(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    dialog.setVisible(false);
                }
            });
            dialog.setVisible(true);
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JPanel panel1;
    private JLabel jLabel1;
    private JButton Erstellen;
    private JButton Schliessen;
    private JLabel jLabel2;
    private JScrollPane scrollPane;
    private JList<String> Rezensenten;
    private JLabel jLabel3;
    private JRadioButton rbPDF;
    private JRadioButton rbDOC;
    // End of variables declaration//GEN-END:variables

    Connection conn;
    Statement SQLAnfrage;
    ResultSet result;
    String SQL;
    String eintrag;
    DefaultListModel<String> listModel = new DefaultListModel<>();

    private static String Autor;
    private static String Anrede;
    private static String Titel;
    private static String Beschreibung;
    private static String ISBN;
    private static String Preis;
    private static String Seiten;
    private static Integer GBuch;
    private static Integer GTyp;
    private static Integer GAnzahl;

    private ButtonGroup Format = new ButtonGroup();
}
