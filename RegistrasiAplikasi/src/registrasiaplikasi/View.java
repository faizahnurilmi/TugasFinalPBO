package registrasiaplikasi;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class View {
    private JFrame frame;
    private JTextField txtNamaTim;
    private JTextField txtGenre;
    private JTextField txtEmail;
    private JTextField txtJumlahMember;
    private JTable table;

    private Controller controller;

    public View() {
        controller = new Controller();
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNamaTim = new JLabel("Nama Tim");
        lblNamaTim.setBounds(10, 10, 80, 20);
        frame.getContentPane().add(lblNamaTim);

        txtNamaTim = new JTextField();
        txtNamaTim.setBounds(100, 10, 150, 20);
        frame.getContentPane().add(txtNamaTim);
        txtNamaTim.setColumns(10);

        JLabel lblGenre = new JLabel("Genre");
        lblGenre.setBounds(10, 40, 80, 20);
        frame.getContentPane().add(lblGenre);

        txtGenre = new JTextField();
        txtGenre.setBounds(100, 40, 150, 20);
        frame.getContentPane().add(txtGenre);
        txtGenre.setColumns(10);

        JLabel lblEmail = new JLabel("Email");
        lblEmail.setBounds(10, 70, 80, 20);
        frame.getContentPane().add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(100, 70, 150, 20);
        frame.getContentPane().add(txtEmail);
        txtEmail.setColumns(10);

        JLabel lblJumlahMember = new JLabel("Jumlah Member");
        lblJumlahMember.setBounds(10, 100, 100, 20);
        frame.getContentPane().add(lblJumlahMember);

        txtJumlahMember = new JTextField();
        txtJumlahMember.setBounds(120, 100, 50, 20);
        frame.getContentPane().add(txtJumlahMember);
        txtJumlahMember.setColumns(10);

        JButton btnTambah = new JButton("Tambah");
        btnTambah.setBounds(10, 130, 90, 30);
        frame.getContentPane().add(btnTambah);
        btnTambah.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String namaTim = txtNamaTim.getText();
                String genre = txtGenre.getText();
                String email = txtEmail.getText();
                int jumlahMember = Integer.parseInt(txtJumlahMember.getText());

                TeamModel teamModel = new TeamModel();
                teamModel.setNamaTim(namaTim);
                teamModel.setGenre(genre);
                teamModel.setEmail(email);
                teamModel.setJumlahMember(jumlahMember);

                controller.tambahTim(teamModel);

                // Reset input fields
                txtNamaTim.setText("");
                txtGenre.setText("");
                txtEmail.setText("");
                txtJumlahMember.setText("");

                // Refresh table
                refreshTable();
            }
        });

        JButton btnHapus = new JButton("Hapus");
        btnHapus.setBounds(110, 130, 90, 30);
        frame.getContentPane().add(btnHapus);
        btnHapus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String namaTim = (String) table.getValueAt(selectedRow, 0);
                    controller.hapusTim(namaTim);

                    // Refresh table
                    refreshTable();
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 170, 400, 80);
        frame.getContentPane().add(scrollPane);

        table = new JTable();
        scrollPane.setViewportView(table);

        // Set column names
        String[] columnNames = {"Nama Tim", "Genre", "Email", "Jumlah Member"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        table.setModel(model);

        // Refresh table
        refreshTable();

        frame.setVisible(true);
    }

    private void refreshTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        List<TeamModel> teams = controller.getAllTeams();
        for (TeamModel team : teams) {
            Object[] rowData = {team.getNamaTim(), team.getGenre(), team.getEmail(), team.getJumlahMember()};
            model.addRow(rowData);
        }
    }
}
