package ua.nure.kn16.ioshchenko.usermanagement.gui;
import ua.nure.kn16.ioshchenko.usermanagement.User;
import ua.nure.kn16.ioshchenko.usermanagement.util.Message;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserTableModel extends AbstractTableModel {
    private static  final String[] COLUMN_NAMES = {Message.getString("id"),
                                                    Message.getString("name"),
                                                    Message.getString("surname")};
    private static final Class[] COLUMN_CLASSES = {Long.class, String.class, String.class};
    private List usersList = null;

    UserTableModel (Collection users) {
        this.usersList = new ArrayList(users);
    }

    @Override
    public int getRowCount() {
        return usersList.size();
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length;
    }


    public Class getColumnClass(int columnIndex) {
        return COLUMN_CLASSES[columnIndex];
    }


    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User user = (User) usersList.get(rowIndex);

        if (columnIndex == 0) {
            return user.getId();
        } else if (columnIndex == 1) {
            return user.getFirstName();
        } else if (columnIndex == 2) {
            return user.getLastName();
        }

        return null;
    }
}