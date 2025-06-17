package cr.ac.ucr.paraiso.progra2.quiz32025.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;

public class PaisXmlDao {
    private String rutaDocumento;
    private Element raiz;
    private Document documento;

    private PaisXmlDao(String rutaDocumento) throws IOException, JDOMException {
        File file = new File(rutaDocumento);
        if (!file.exists()) {
            //se encarga de crear tanto el DOM y como Documento XML
            this.rutaDocumento = rutaDocumento;
            this.raiz = new Element("equipos");
            this.documento = new Document(raiz);
            guardar();
        } else {
            //se encarga de ABRIR y parsear el Documento XML a un DOM
            SAXBuilder saBuilder = new SAXBuilder();
            saBuilder.setIgnoringElementContentWhitespace(true);
            //parseo
            this.documento = saBuilder.build(new File(rutaDocumento));
            this.raiz = documento.getRootElement();

            this.rutaDocumento = rutaDocumento;
        }
    }

    public void guardar() throws FileNotFoundException, IOException {
        XMLOutputter xmlOutputter = new XMLOutputter();
        xmlOutputter.output(this.documento,
                new PrintWriter(this.rutaDocumento));
        // imprimir en la consola el DOM
        xmlOutputter.output(this.documento, System.out);
    }

    public static PaisXmlDao abrirDocumento(String rutaDocumento)
            throws JDOMException, IOException {
        return new PaisXmlDao(rutaDocumento);
    }
}
