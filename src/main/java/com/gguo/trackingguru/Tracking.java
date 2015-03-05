/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gguo.trackingguru;

import com.gguo.util.Utilities;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author gguo
 */
public class Tracking extends javax.swing.JFrame {

    /**
     * Creates new form Tracking
     */
    public Tracking() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btn_search = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tracking_table = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        status_label = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tf_tracking_number = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        mi_exit = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        mi_api_setting = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Tracking Guru");

        jLabel1.setText("Tracking Number");

        btn_search.setText("Search");
        btn_search.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_searchActionPerformed(evt);
            }
        });

        tracking_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tracking_table.getTableHeader().setReorderingAllowed(false);
        tracking_table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tracking_tableMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tracking_table);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 637, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(status_label, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(status_label, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tf_tracking_number.setColumns(20);
        tf_tracking_number.setLineWrap(true);
        tf_tracking_number.setRows(5);
        tf_tracking_number.setText(Utilities.setJTextAreaText());
        tf_tracking_number.setToolTipText("Tracking numbers can be separated by either space or comma.");
        tf_tracking_number.setWrapStyleWord(true);
        jScrollPane1.setViewportView(tf_tracking_number);

        jMenu1.setMnemonic('F');
        jMenu1.setText("File");

        mi_exit.setMnemonic('X');
        mi_exit.setText("Exit");
        mi_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mi_exitActionPerformed(evt);
            }
        });
        jMenu1.add(mi_exit);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Settings");

        mi_api_setting.setText("API Setting");
        jMenu2.add(mi_api_setting);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(btn_search)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_search))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_searchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_searchActionPerformed

        // TODO add your handling code here:


        String url = "http://track.blueskyexpress.com.au/cgi-bin/GInfo.dll?EmmisTrack";
        String w = "blueskyexpress";
        String cmodel = "";
        //String cno = tf_tracking_number.getText();
        int ntype = 0;
        String tracking_no = tf_tracking_number.getText();
        String cleanedTracking = "";
        String errorTracking = "";
        boolean processFlag = false;
        boolean singleNoFlag = false;
        if (tracking_no.length() > 0) {
            String[] result = validateTracking(tracking_no);
            cleanedTracking = result[1];
            processFlag = Boolean.valueOf(result[0]);
            singleNoFlag = Boolean.valueOf(result[3]);
            errorTracking = result[2];
        }

        if (processFlag) {//Utilities.fileExist(APIConfigFilePath)) {
            //save tn only when valid tracking no(s)
            if (singleNoFlag) {
                Utilities.saveJTextAreaToFile(cleanedTracking.substring(0,cleanedTracking.indexOf(" ")));
            } else {
            Utilities.saveJTextAreaToFile(cleanedTracking);
            }

            CloseableHttpClient httpclient = HttpClients.createDefault();
            try {
                HttpPost httpPost = new HttpPost(url);

                List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                nvps.add(new BasicNameValuePair("w", w));
                nvps.add(new BasicNameValuePair("cmodel", cmodel));
                nvps.add(new BasicNameValuePair("cno", cleanedTracking));
                nvps.add(new BasicNameValuePair("ntype", String.valueOf(ntype)));

                httpPost.setEntity(new UrlEncodedFormEntity(nvps));

                // Create a custom response handler
                ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
                    public String handleResponse(
                            final HttpResponse response) throws ClientProtocolException, IOException {
                        int status = response.getStatusLine().getStatusCode();
                        status_label.setText(response.getStatusLine().toString());
                        if (status >= 200 && status < 300) {
                            HttpEntity entity = response.getEntity();
                            return entity != null ? EntityUtils.toString(entity) : null;
                        } else {
                            throw new ClientProtocolException("Unexpected response status: " + status);
                        }
                    }
                };
                String responseBody = httpclient.execute(httpPost, responseHandler);
                //System.out.println("----------------------------------------");
                //System.out.println(responseBody);

                //tp_result.setText(responseBody);
                Document htmlDoc = Jsoup.parse(responseBody);
//                tracking_number.setText(htmlDoc.getElementById("HeaderNum").text());
//                tracking_status.setText(htmlDoc.getElementById("HeaderState").text());
//                tracking_from.setText(htmlDoc.getElementById("HeaderFrom").text());
//                tracking_to.setText(htmlDoc.getElementById("HeaderDes").text());
//                tracking_quantity.setText(htmlDoc.getElementById("HeaderItem").text());

                Element tableElement = htmlDoc.getElementById("oMHtable");

                Vector<String> tableHeaders = new Vector<String>();
                Vector tableData = new Vector();

                for (Element header : tableElement.select("tr:eq(0)")) {
                    Elements tds = header.select("td:not([rowspan])");//tds without attr=rowspan
                    for (Element td : tds) {
                        //System.out.println("header"+td.text());
                        tableHeaders.add(td.text());
                    }
                }

                for (Element row : tableElement.select("tr:gt(0)")) {
                    Elements tds = row.select("td:not([rowspan])");//tds without attr=rowspan
                    Vector<Object> oneRow = new Vector<Object>();
                    for (Element td : tds) {
                        //System.out.println(td.text());
                        oneRow.add(td.text());
                    }
                    tableData.add(oneRow);
                }
                tracking_table.setModel(new DefaultTableModel(tableData, tableHeaders));

                //            CloseableHttpResponse response2 = httpclient.execute(httpPost);
                //            try {
                //                System.out.println(response2.getStatusLine());
                //
                //                HttpEntity entity2 = response2.getEntity();
                //                // do something useful with the response body
                //                // and ensure it is fully consumed
                //                  tp_result.setText(EntityUtils.toString(entity2));
                //                  EntityUtils.consume(entity2);
                //            } catch (IOException ex) {
                //                Logger.getLogger(Tracking.class.getName()).log(Level.SEVERE, null, ex);
                //            } finally {
                //                try {
                //                    response2.close();
                //                } catch (IOException ex) {
                //                    Logger.getLogger(Tracking.class.getName()).log(Level.SEVERE, null, ex);
                //                }
                //            }
            } catch (IOException ex) {
                Logger.getLogger(Tracking.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    httpclient.close();
                } catch (IOException ex) {
                    Logger.getLogger(Tracking.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            //new APISettingFrame().setVisible(true);
            //do sth if not true
            String str = "";
            if (errorTracking.length() > 0) {
                str = "Invalid tracking no(s): " + errorTracking;
            } else {
                str = "Tracking No can't be empty!";
            }
            JOptionPane.showMessageDialog(null, str);
        }
    }//GEN-LAST:event_btn_searchActionPerformed

    private void mi_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mi_exitActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_mi_exitActionPerformed

    private void tracking_tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tracking_tableMouseClicked
        // TODO add your handling code here:
        JTable target = (JTable) evt.getSource();
        int row = target.getSelectedRow();
        int column = target.getSelectedColumn();
        if (column == 0 || column == 7) {
            String t_no = (String) target.getValueAt(row, 0);
            String base_url = "http://198.11.173.181/cgi-bin/GInfo.dll?EmmisTrack&w=blueskyexpress&cno=ReplaceMe&cmodel=&ntype=0";
            String url = base_url.replace("ReplaceMe", t_no);
            System.out.println(url);

            Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
            if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                try {
                    desktop.browse(new URI(url));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        //System.out.println("row: "+row +" col: "+column);

    }//GEN-LAST:event_tracking_tableMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Tracking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Tracking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Tracking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Tracking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Tracking().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_search;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JMenuItem mi_api_setting;
    private javax.swing.JMenuItem mi_exit;
    private javax.swing.JLabel status_label;
    private javax.swing.JTextArea tf_tracking_number;
    private javax.swing.JTable tracking_table;
    // End of variables declaration//GEN-END:variables

    private String[] validateTracking(String tracking_no) {
        String[] result = new String[4];
        boolean process = true;
        String[] trackingNos = tracking_no.split("\\s+|,"); //regex for 1 or more spaces, or comma
        StringBuilder sb = new StringBuilder();
        StringBuilder err = new StringBuilder();
        boolean single = false;
        for (String tn : trackingNos) {
            if (tn.trim().matches("[a-zA-Z]{2}\\d{9}MEL")) {
                sb.append(tn);
                sb.append(" ");
            } else {
                if (tn.length() > 0) {
                    process = false;
                    err.append(tn);
                    err.append(",");
                }
            }
        }

        if (sb.length() > 0) {
            if (sb.indexOf(" ") == sb.length() - 1) {
                sb.append(sb.substring(0, sb.length() - 1));
                result[1] = sb.toString();
                single = true;
            } else {
                result[1] = sb.substring(0, sb.length() - 1);
            }
        } else {
            result[1] = sb.toString();
        }
        result[0] = process ? String.valueOf(process) : String.valueOf(process);
        result[3] = single ? String.valueOf(single) : String.valueOf(single);
        if (err.length() > 0 && String.valueOf(err.charAt(err.length() - 1)).equals(",")) {
            result[2] = err.substring(0, err.length() - 1);
        } else {
            result[2] = err.toString();
        }
        //System.out.println(sb.toString());
        return result;
    }
}
