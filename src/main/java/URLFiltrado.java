import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.jsoup.select.Elements;
import static spark.Spark.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class URLFiltrado {
    public static void main(String[] args) throws IOException {

        int parrafo;
        int i=1;

        System.out.println("Por favor, digite el URL:");
        Scanner scanner = new Scanner(System.in);
        String url = scanner.nextLine();
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String url2= Jsoup.connect(url).execute().body();

        CantLineas linea= new CantLineas(url2);


        Elements parrafos = doc.select("p");
        parrafo = parrafos.size();
        System.out.println("\nCantidad de p\u00e1rrafos: "+parrafo);

        Elements imagenes = doc.select("img");
        int imagen = imagenes.size();
        System.out.println("\nCantidad de im\u00e1genes: "+imagen);

        Elements FormsPost = doc.select("form[method=POST]");
        int FormPost = FormsPost.size();
        System.out.println("\nCantidad de forms Post: "+ FormPost);

        Elements FormsGet = doc.select("form[method=GET]");
        int FormGet = FormsGet.size();
        System.out.println("\nCantidad de Forms Get: "+FormGet );

            System.out.println("\nInputs");
            for (Element FormBuscar: doc.getElementsByTag("form"))
            {
                for (Element inputsBuscar : FormBuscar.getElementsByTag("input")
                ) {
                    System.out.println("Formulario #: "+ i + " Tipo: "+inputsBuscar.attr("type").toLowerCase());

                }
                i++;
            }

        Document Peticion;
        for (Element htmlparseado: doc.getElementsByTag("form")) {
            if ( htmlparseado.attr("method").equalsIgnoreCase("post"))
            {
                String Url = htmlparseado.absUrl("action");
                Peticion= Jsoup.connect(Url).data("Asignatura: ", "practica1").header("Matricula", "2015-0559").post();
                System.out.println("\n \u00daltimo Punto: "+Peticion.body().toString());
            }

        }

    }
}


