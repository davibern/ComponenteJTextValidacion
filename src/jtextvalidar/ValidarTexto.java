package jtextvalidar;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.Serializable;
import javax.swing.JTextField;

/**
 * Componente personalizado de caja de texto que tendrá una opción para establecer el tipo de validación
 * 
 * - Podrá ser de tipo texto
 * - Podrá ser de tipo entero
 * - Podrá ser de tipo doble
 * 
 * Para poder ser un componenente personalizado tiene que permitir poder escribir sus valores, para ello se
 * implementa Serializable, tanto para la clase como para sus atributos, si estos es de una clase distinta.
 * 
 * @author davibern
 * @version 1.0
 */
public class ValidarTexto extends JTextField implements Serializable {
    
    /**
     * Tipo de texto a validar
     */
    private String tipo;
    
    /**
     * Constructor sin argumentos
     */
    public ValidarTexto() {
        this.validarEntrada();
    }
    
    /**
     * Obtener el tipo de texto a validar
     * @return Tipo de texto a validar
     */
    public String getTipo() {
        return this.tipo;
    }

    /**
     * Configurar el tipo de texto a validar
     * @param tipo Tipo de texto a validar
     */
    public void setTipo(String tipo) {
        if (this.tipo.equals("Entero") || this.tipo.equals("Real") || this.tipo.equals("Texto")) {
            this.tipo = tipo;
        }
    }
    
    /**
     * Convierte el tipo de dato recibido sobreescribiendo el método setText()
     * @param texto el texto que se parseará
     */
    @Override
    public void setText(String texto) {
        switch (this.tipo) {
            case "Entero":
                try {
                    Integer.parseInt(texto);
                    super.setText(texto);
                } catch (NumberFormatException e){
                    super.setText(texto);
                }
                break;
                
            case "Real":
                try {
                    Double.parseDouble(texto);
                    super.setText(texto);
                } catch (NumberFormatException e) {
                    super.setText(texto);
                }
                break;
                
            default:
                super.setText(texto);
                break;
        }
    }
    
    /**
     * Método que validará la entrada por cada pulsación que se haga en el caja de texto.
     * La validación se hará en funciónd el tipo.
     */
    public void validarEntrada() {
        this.addKeyListener(new KeyAdapter() {
            
            @Override
            public void keyTyped(KeyEvent e) {
                char character = e.getKeyChar();
                
                switch (tipo) {
                    case "Entero":
                        // Si no es entero se valida (se permite pulsar borrar)
                        if (!(Character.isDigit(character)) && (character != KeyEvent.VK_BACK_SPACE)) {
                            e.consume();
                        }
                        break;
                        
                    case "Real":
                        // Si es entero y no se escribe punto se valida (se permite borrar)
                        if ((!Character.isDigit(character)) && ((character < '.') || (character > '.')) && (character != KeyEvent.VK_BACK_SPACE)) {
                            e.consume();
                        } else {
                            // Si ya existe un punto se evita el escribir otro
                            if (getText().contains(".")) {
                                if ((!Character.isDigit(character)) && (character != KeyEvent.VK_BACK_SPACE)) {
                                    e.consume();
                                }
                            }
                        }
                        break;
                }
            }
            
        });
    }
}
