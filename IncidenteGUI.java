import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class IncidenteGUI {
    private JPanel panelMenuPrincipal;
    private JPanel panelRegistro;
    private JPanel panelVisualizacion;
    private JPanel panelEstados;
    private JTextField txtFuente;
    private JTextArea txtDescripcion;
    private JTextField txtEvidencia;
    private JComboBox cmbTipoAmenaza;
    private JButton btnRegistrar;
    private JButton btnVolver;
    private JTable tablaIncidentes;
    private JComboBox cmbFiltroTipo;
    private JButton btnFiltrar;
    private JButton btnActualizar;
    private JButton btnEliminar;
    private JButton btnVolver2;
    private JTable tablaEstados;
    private JTextField txtResponsable;
    private JComboBox cmbEstado;
    private JTextArea txtNotas;
    private JButton btnGuardarCambios;
    private JButton btnVolver3;


    private GestorIncidentes gestor = new GestorIncidentes();

    public IncidenteGUI() {
        // Inicializar interfaz mostrando solo el panel de registro
        mostrarPanel(panelRegistro);

        // Botón registrar incidente
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String fuente = txtFuente.getText();
                String descripcion = txtDescripcion.getText();
                String evidencia = txtEvidencia.getText();
                String tipoAmenaza = (String) cmbTipoAmenaza.getSelectedItem();

                if (fuente.isEmpty() || descripcion.isEmpty() || evidencia.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, completa todos los campos.");
                } else {
                    gestor.registrarIncidente(fuente, descripcion, evidencia, tipoAmenaza);
                    JOptionPane.showMessageDialog(null, "✅ Incidente registrado exitosamente.");
                    txtFuente.setText("");
                    txtDescripcion.setText("");
                    txtEvidencia.setText("");
                    cmbTipoAmenaza.setSelectedIndex(0);
                }
            }
        });

        // Botones para volver al menú principal
        btnVolver.addActionListener(e -> mostrarPanel(panelRegistro));
        btnVolver2.addActionListener(e -> mostrarPanel(panelRegistro));
        btnVolver3.addActionListener(e -> mostrarPanel(panelRegistro));

        // Botones de panel de visualización
        btnActualizar.addActionListener(e -> cargarTablaIncidentes(null));
        btnFiltrar.addActionListener(e -> cargarTablaIncidentes((String) cmbFiltroTipo.getSelectedItem()));
        btnEliminar.addActionListener(e -> eliminarSeleccionado());

        // Botón para actualizar estado en panel de estados
        btnGuardarCambios.addActionListener(e -> actualizarEstado());
    }

    private void mostrarPanel(JPanel panelVisible) {
        panelRegistro.setVisible(false);
        panelVisualizacion.setVisible(false);
        panelEstados.setVisible(false);
        panelVisible.setVisible(true);
    }

    private void cargarTablaIncidentes(String filtro) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("Fuente");
        modelo.addColumn("Descripción");
        modelo.addColumn("Evidencia");
        modelo.addColumn("Tipo Amenaza");
        modelo.addColumn("Fecha");
        modelo.addColumn("Estado");

        for (Incidente i : gestor.getCola().obtenerTodos()) {
            if (filtro == null || filtro.equals("Todos") || i.getTipoAmenaza().equals(filtro)) {
                modelo.addRow(new Object[]{i.getFuente(), i.getDescripcion(), i.getEvidencia(), i.getTipoAmenaza(), i.getFechaReporte(), i.getEstado()});
            }
        }
        tablaIncidentes.setModel(modelo);
        tablaEstados.setModel(modelo);
    }

    private void eliminarSeleccionado() {
        int fila = tablaIncidentes.getSelectedRow();
        if (fila >= 0) {
            Vector<Incidente> lista = new Vector<>(gestor.getCola().obtenerTodos());
            Incidente i = lista.get(fila);
            gestor.getCola().cola.remove(i);
            cargarTablaIncidentes(null);
            JOptionPane.showMessageDialog(null, "🗑️ Incidente eliminado.");
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona un incidente para eliminar.");
        }
    }

    private void actualizarEstado() {
        int fila = tablaEstados.getSelectedRow();
        if (fila >= 0) {
            Vector<Incidente> lista = new Vector<>(gestor.getCola().obtenerTodos());
            Incidente i = lista.get(fila);
            String nuevoEstado = (String) cmbEstado.getSelectedItem();
            i.setEstado(nuevoEstado);
            cargarTablaIncidentes(null);
            JOptionPane.showMessageDialog(null, "📌 Estado actualizado a: " + nuevoEstado);
        } else {
            JOptionPane.showMessageDialog(null, "Selecciona un incidente para actualizar.");
        }
    }

    public JPanel getPanelMenuPrincipal() {
        return panelMenuPrincipal;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Gestión de Incidentes");
        frame.setContentPane(new IncidenteGUI().getPanelMenuPrincipal());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
