package jtextvalidar;

import java.awt.Component;
import java.beans.PropertyEditorSupport;

/**
 *
 * @author davibern
 * @version 1.0
 */
public class ValidarTextoEditorPropertySupport extends PropertyEditorSupport {
    
    /**
     * Objeto que nos servirá obtener el valor del tipo a validar desde el editor de propiedades
     */
    private ValidarTextoPanel panel = new ValidarTextoPanel();
    
    /**
     * Este método nos indica si tenemos une editor propio personalizado, como es sí lo ponemos a verdadero
     * @return True (para especificar que sí que tenemos editor personalizado)
     */
    @Override
    public boolean supportsCustomEditor() {
        return true;
    }
    
    /**
     * Método que devuelve el editor de propiedades
     * @return Se devuelve el panel de propiedades
     */
    @Override
    public Component getCustomEditor() {
        return panel;
    }
    
    /**
     * Método que devuelve los tipos a evaluar
     * @return Array con los tipos a validar
     */
    @Override
    public String[] getTags() {
        String[] tags = {"Entero", "Real", "Texto"};
        return tags;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        super.setAsText(text);
    }

    @Override
    public String getAsText() {
        return super.getAsText();
    }
    
    /**
     * Método que inicializará el componente
     * @return Inicializará el componente
     */
    @Override
    public String getJavaInitializationString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\"");
        sb.append(this.panel.getSelectedTypeTextValue());
        sb.append("\"");
        String resultado = new String(sb);
        return resultado;
    }
    
    /**
     * Se obtiene el texto a validar
     * @return Text a validar
     */
    @Override
    public Object getValue() {
        return this.panel.getSelectedTypeTextValue();
    }

    @Override
    public void setValue(Object value) {
        super.setValue(value);
    }
    
}
