/*
 * FBPwn
 * 
 * http://code.google.com/p/fbpwn
 * 
 * Copyright (C) 2011 - FBPwn
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package fbpwn.plugins.ui;

import fbpwn.core.FacebookAccount;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class AddVictimsFriendsDialog extends javax.swing.JDialog {

    ArrayList<FacebookAccount> friendsList;

    /** Creates new form AddVictimsFriendsDialog */
    public AddVictimsFriendsDialog(java.awt.Frame parent, boolean modal, final ArrayList<FacebookAccount> friendsList) {
        super(parent, modal);
        initComponents();

        jTable1.setCellSelectionEnabled(false);
        jTable1.setColumnSelectionAllowed(false);
        jTable1.setRowSelectionAllowed(true);
        jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation((int) (dim.getWidth() / 2 - getWidth() / 2), (int) (dim.getHeight() / 2 - getHeight() / 2));

        JCheckBox jcheckbox = new JCheckBox();
        jcheckbox.setHorizontalAlignment(SwingConstants.CENTER);
        jTable1.getColumn(jTable1.getColumnName(0)).setCellEditor(new DefaultCellEditor(jcheckbox));
        jTable1.getColumn(jTable1.getColumnName(0)).setMaxWidth(30);
        jTable1.getColumn(jTable1.getColumnName(1)).setCellRenderer(new LableTableCellRenderer(this));
        jTable1.getColumn(jTable1.getColumnName(1)).setMaxWidth(90);
        jTable1.setRowHeight(90);
        this.friendsList = friendsList;

        Thread iconsResolver = new Thread(new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < friendsList.size(); i++) {
                    try {

                        ImageIcon ico = new ImageIcon(new URL(friendsList.get(i).getProfilePhotoURL()));
                        Image newResizedIcon = ico.getImage().getScaledInstance(90, 90, java.awt.Image.SCALE_SMOOTH);
                        friendsList.get(i).setProfilePicture(new ImageIcon(newResizedIcon));
                        ((DefaultTableModel) jTable1.getModel()).fireTableCellUpdated(i, 1);
                    } catch (MalformedURLException ex) {
                    }
                }
            }
        });
        iconsResolver.start();


        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        for (FacebookAccount fbAccount : friendsList) {
            model.addRow(new Object[]{new Boolean(false),
                        fbAccount.getProfilePhotoURL(),
                        fbAccount.getName()});
        }
    }

    public ImageIcon getIcon(int index) {
        synchronized (friendsList) {
            return friendsList.get(index).getProfilePicture();
        }
    }

    public ArrayList<FacebookAccount> showSelectionDialog() {
        setModal(true);
        setVisible(true);

        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        ArrayList<FacebookAccount> selectedAccounts = new ArrayList<FacebookAccount>();

        for (int i = 0; i < model.getRowCount(); i++) {
            if (((Boolean) model.getValueAt(i, 0)) == true) {
                selectedAccounts.add(friendsList.get(i));
            }
        }
        return selectedAccounts;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Add Victim's Friends");
        setResizable(false);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Image", "Name"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setColumnSelectionAllowed(true);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jButton1.setText("Submit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Select All");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Select None");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Open In Browser");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 605, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, 141, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton4)
                        .addGap(214, 214, 214)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    dispose();
}//GEN-LAST:event_jButton1ActionPerformed

private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

    for (int i = 0; i < model.getRowCount(); i++) {
        model.setValueAt(true, i, 0);
    }
}//GEN-LAST:event_jButton2ActionPerformed

private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();

    for (int i = 0; i < model.getRowCount(); i++) {
        model.setValueAt(false, i, 0);
    }
}//GEN-LAST:event_jButton3ActionPerformed

private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
    if (jTable1.getSelectedRowCount() == 0) {
        JOptionPane.showMessageDialog(this,
                "You need to select a profile first",
                "Error Occurred",
                JOptionPane.ERROR_MESSAGE);
        return;
    }
    try {
        Desktop.getDesktop().browse(new URI(friendsList.get(jTable1.getSelectedRow()).getProfilePageURL()));
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this,
                "Failed to open browser",
                "Error Occurred",
                JOptionPane.ERROR_MESSAGE);
    }
}//GEN-LAST:event_jButton4ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}

class LableTableCellRenderer extends DefaultTableCellRenderer {

    AddVictimsFriendsDialog parentDiag = null;

    public LableTableCellRenderer(AddVictimsFriendsDialog parentDiag) {
        this.parentDiag = parentDiag;
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

        JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setText("");
        label.setIcon(parentDiag.getIcon(row));
        return label;
    }
}
