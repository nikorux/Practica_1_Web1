import java.io.IOException;

class CantLineas {
    private String doc;
    public CantLineas(String url2) throws IOException {
        // Imprimir valor

        System.out.println("Cantidad de l\u00edneas: "+ParaseadorPrac(url2));
    }

    public static int ParaseadorPrac(String url2) {
        int i=0;
        int contar=0;
        String[] array;
        array = url2.split("\n");
        contar = array.length;

        return contar;
    }

}
