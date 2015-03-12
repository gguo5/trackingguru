/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gguo.trackingguru;

import com.gguo.trackingguru.Tracking.Logistics;
import com.gguo.util.HttpUtil;
import com.gguo.util.Utilities;
import java.awt.Color;
import java.awt.Component;
import java.io.File;
import java.util.Properties;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author Grant
 */
public class ComponentControls {

    final static Logger logger = Logger.getLogger(ComponentControls.class.getName());
    final static String PROGRAMROOT = "C:/TrackingGuru/";

    public static boolean setSearchBtnIntialStatus(int count) {
        boolean status = count > 0 ? true : false;
        return status;
    }

    public static DefaultListModel setJListModel(String tabName) {
        DefaultListModel listModel = new DefaultListModel();

        String rootDir = "C:/TrackingGuru/";
        String filePathString = rootDir + tabName + ".properties";
        if (Utilities.fileExist(filePathString)) {
            Properties prop = Utilities.ReadPropFile(filePathString);
            for (String name : prop.stringPropertyNames()) {
                listModel.addElement(name);
            }
        }
        return listModel;
    }

    public static DefaultListModel setJListModel(File propertyFile) {
        DefaultListModel listModel = new DefaultListModel();


        if (propertyFile.exists() && !propertyFile.isDirectory()) {
            Properties prop = Utilities.ReadPropFile(propertyFile);
            for (String name : prop.stringPropertyNames()) {
                listModel.addElement(name);
            }
        }
        return listModel;
    }

    public static void AddNewProperty(String key, String value, String tabName) {

        String rootDir = "C:/TrackingGuru/";
        String filePathString = rootDir + tabName + ".properties";
        Properties prop;
        if (Utilities.fileExist(filePathString)) {
            prop = Utilities.ReadPropFile(filePathString);
        } else {
            prop = new Properties();
        }
        if (!prop.containsKey(key)) {
            prop.setProperty(key, value);
            Utilities.WriteToPropFile(prop, filePathString);
        }

    }

    public static void UpdateProperty(String key, String value, String tabName) {

        String rootDir = "C:/TrackingGuru/";
        String filePathString = rootDir + tabName + ".properties";
        Properties prop;
        if (Utilities.fileExist(filePathString)) {
            prop = Utilities.ReadPropFile(filePathString);
        } else {
            prop = new Properties();
        }
        if (prop.containsKey(key)) {
            prop.setProperty(key, value);
            Utilities.WriteToPropFile(prop, filePathString);
        }

    }

    static boolean removeProperty(String removedKey, String currentTabName) {
        boolean flag = true;
        String rootDir = "C:/TrackingGuru/";
        String filePathString = rootDir + currentTabName + ".properties";
        Properties prop;
        if (Utilities.fileExist(filePathString)) {
            prop = Utilities.ReadPropFile(filePathString);
            if (prop.containsKey(removedKey)) {
                prop.remove(removedKey);
                Utilities.WriteToPropFile(prop, filePathString);
            } else {
                logger.info("key doesn't exist!");
                flag = false;
            }
        } else {
            logger.info(filePathString + " not found!");
            flag = false;
        }
        return flag;
    }

    static boolean validateTrackingNumber(String tn, String currentTabName) {
        Logistics logi = Tracking.Logistics.valueOf(currentTabName);
        String regex = "";
        switch (logi) {
            case BlueSky:
                regex = "BS\\d{9}MEL";
                break;

            case EFS:
                regex = "EF\\d{9}AU";
                break;
            default:
                regex = "[a-zA-Z]{2}\\d{9}[a-zA-Z]{2,3}+";
                break;
        }
        return tn.trim().matches(regex);
    }

    static String getCleanedTrackingString(DefaultListModel tracking_listModel) {
        StringBuilder sb = new StringBuilder();
        if (tracking_listModel.getSize() > 1) {
            for (int i = 0; i < tracking_listModel.getSize(); i++) {
                sb.append(tracking_listModel.get(i).toString());
                sb.append(" ");
            }
        } else {
            sb.append(tracking_listModel.get(0).toString());
            sb.append(" ");
            sb.append(tracking_listModel.get(0).toString());

        }
        return sb.toString().trim();
    }

    static TableModel setJTableModel(String response) {

        Document htmlDoc = Jsoup.parse(response);

        Element tableElement = htmlDoc.getElementById("oMHtable");//if single tn, need to get 2 copy

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
        //create a table model and set cell non-edible
        DefaultTableModel tableModel = new DefaultTableModel(tableData, tableHeaders) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        return tableModel;
    }

    static void setJTableProperties(JTable tracking_table) {
        //set column width
        int[] width = getTableColumWidth(tracking_table.getName());
        for (int i = 0; i < tracking_table.getColumnCount(); i++) {
            tracking_table.getColumnModel().getColumn(i).setPreferredWidth(width[i]);
        }
        //set table header centre horizontally
        TableCellRenderer rendererFromHeader = tracking_table.getTableHeader().getDefaultRenderer();
        JLabel headerLabel = (JLabel) rendererFromHeader;
        headerLabel.setHorizontalAlignment(JLabel.CENTER);

        //set tn column style
        tracking_table.getColumn(tracking_table.getColumnName(0)).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                setText(value.toString());
                setForeground(Color.BLUE);
                setToolTipText(Utilities.getPropValueByKey(table.getName(), value.toString()));
                return this;
            }
        });

        tracking_table.getColumn(tracking_table.getColumnName(tracking_table.getColumnCount() - 1)).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                setText(value.toString());
                setForeground(Color.BLUE);
                return this;
            }
        });
    }

    private static int[] getTableColumWidth(String name) {
        Logistics logi = Tracking.Logistics.valueOf(name);

        int[] width;
        switch (logi) {
            case BlueSky:
                width = new int[]{100, 65, 35, 25, 35, 100, 110, 50};
                break;

            case EFS:
                width = new int[]{100, 65, 35, 25, 35, 50};
                break;
            default:
                width = null;
                break;
        }
        return width;
    }

    static void displayTrackingDetails(DefaultListModel tracking_listModel, String currentTabName, JTable tracking_table, Tracking mainTracking) {
        String cno = getCleanedTrackingString(mainTracking.getTracking_listModel());
        String[] params = HttpUtil.getParams(currentTabName);
        String response = HttpUtil.POSTRequest(params, cno, mainTracking);
        tracking_table.setModel(ComponentControls.setJTableModel(response));
        setJTableProperties(tracking_table);
    }

    //ManageTracking frame utilities
    public static TableModel setTrackingNumberJTableModel(Properties props) {
        Vector<String> tableHeaders = new Vector<String>();
        Vector tableData = new Vector();

        String[] headerTextArray = new String[]{"Tracking Number", "Recipient"};


        for (String header : headerTextArray) {
            tableHeaders.add(header);
        }


        for (Object key : props.keySet()) {
            String keyStr = (String) key;
            String value = props.getProperty(keyStr);

            Vector<Object> oneRow = new Vector<Object>();

            oneRow.add(keyStr);
            oneRow.add(value);

            tableData.add(oneRow);
        }

        DefaultTableModel tableModel = new DefaultTableModel(tableData, tableHeaders);

        return tableModel;
    }

    static void DisplayTrackingManagementTable(JTable propertyTable, final String tabName) {
        String rootDir = "C:/TrackingGuru/"; //make this a variable in main frame
        String propertyFilePath = rootDir + tabName + ".properties";
        Properties prop = Utilities.ReadPropFile(propertyFilePath);
        propertyTable.setModel(ComponentControls.setTrackingNumberJTableModel(prop));
        propertyTable.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent evt) {
                int row = evt.getFirstRow();
                int column = evt.getColumn();
                TableModel model = (TableModel) evt.getSource();

                String columnName = model.getColumnName(column);
                String data = (String) model.getValueAt(row, column);
                if (columnName.equalsIgnoreCase("Recipient")) {
                    String key = (String) model.getValueAt(row, column - 1);
                    UpdateProperty(key, data, tabName);
                }


            }
        });
    }

    static File getRootFile() {
        Utilities.FolderExist(PROGRAMROOT, true);
        return new File(PROGRAMROOT);
    }

    static void removeTrackingNumber(final Tracking mainTracking, JList tracking_list, DefaultListModel tracking_listModel, String tabName) {
        int dialogResult = JOptionPane.showConfirmDialog(null, "Do you wish to remove this tracking number? Note: changes can not be reverted!", "Remove Tracking Number", JOptionPane.YES_NO_OPTION);
        if (dialogResult == JOptionPane.YES_OPTION) {
            boolean success = removeTracking(mainTracking, tracking_list, tracking_listModel,tabName);
            logger.info("remove tn? " + success);
        }
    }

    private static boolean removeTracking(Tracking mainTrack, JList tracking_list, DefaultListModel tracking_listModel, String tabName) {
        boolean flag = true;
        //This method can be called only if
        //there's a valid selection
        //so go ahead and remove whatever's selected.
        int index = tracking_list.getSelectedIndex();

        String removedKey = (String) tracking_listModel.get(index);
        if (removeProperty(removedKey, tabName)) {
            tracking_listModel.remove(index);

            int size = tracking_listModel.getSize();

            if (size == 0) { //Nobody's left, disable firing.
                mainTrack.DisableRemoveBtn(tabName);

            } else { //Select an index.
                if (index == tracking_listModel.getSize()) {
                    //removed item in last position
                    index--;
                }

                tracking_list.setSelectedIndex(index);
                tracking_list.ensureIndexIsVisible(index);
            }

        } else {
            flag = false;
        }

        return flag;
    }
}
