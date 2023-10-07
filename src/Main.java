package API_CLIMA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import org.json.JSONException;
import org.json.JSONObject;



public class Main {


    private static final String API_KEY = "f2987de8c9365587d25f69bc27c952e8";
    private static final String API_ENDPOINT = "https://api.openweathermap.org/data/2.5/weather";

    public static void main(String[] args) throws JSONException, InterruptedException, IOException {

        Scanner leia = new Scanner (System.in);


        int seg = 2;

        String op1;

        do {
            limpa();
            System.out.println("\n===========================\n"
                    + "BEM VINDO AO CLIMA ON LIVE\n"
                    + "===========================\n");



            System.out.println(
                    "A) - DIGITAR CIDADE ESPECIFICA PARA CONSULTAR O CLIMA\n"
                            + "0) - SAIR");
            op1 = leia.next();




            if(!op1.equals("0") && !op1.equalsIgnoreCase("A") && !op1.equalsIgnoreCase("B")) {

                System.out.println("OPÇÃO INVÁLIDA, DIGITE NOVAMENTE!");

            }






            if(op1.equalsIgnoreCase("A")) {

                String cidadepesquisa;





                try {

                    BufferedReader reader2 = new BufferedReader(new InputStreamReader(System.in));


                    System.out.println("DIGITE A CIDADE QUE DESEJA SABER AS INFORMAÇÕES DE TEMPO"
                    );
                    cidadepesquisa = reader2.readLine();


                    String url = API_ENDPOINT + "?q=" + cidadepesquisa + "&appid=" + API_KEY;

                    URL apiUrl = new URL(url);
                    HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
                    connection.setRequestMethod("GET");

                    int responseCode = connection.getResponseCode();

                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        String inputLine;
                        StringBuilder response = new StringBuilder();

                        while ((inputLine = reader.readLine()) != null) {
                            response.append(inputLine);
                        }

                        reader.close();

                        limpa();
                        System.out.println(response.toString());

                        System.out.println("\n======================\n"
                                + "      RELATÓRIO\n"
                                + "======================\n");
                        System.out.println("____________________________________\n");
                        System.out.println("     || INFORMAÇÕES GERAIS ||");
                        System.out.println("____________________________________");
                        JSONObject jsonResponse = new JSONObject(response.toString());

                        String main2 = jsonResponse.getString("name");
                        System.out.println("\nCIDADE -> " + main2);

                        JSONObject pais = jsonResponse.getJSONObject("sys");
                        String country = pais.getString("country");

                        System.out.println("\nPAIS -> " + country );




                        JSONObject main = jsonResponse.getJSONObject("main");
                        int temperaturaKelvin = main.getInt("temp");
                        int temperaturaCelsius = temperaturaKelvin - (int)273.15;
                        System.out.println("\nTEMPERATURA -> " + temperaturaCelsius + "°C");






                        JSONObject weather = jsonResponse.getJSONArray("weather").getJSONObject(0);
                        String descricaoClima = weather.getString("description");
                        System.out.println("\nDESCRIÇÃO -> " + descricaoClima);

                        System.out.println("____________________________________\n");
                        System.out.println("   || INFORMAÇÕES ESPECIFICAS ||");
                        System.out.println("____________________________________");

                        int pressao = main.getInt("pressure");
                        System.out.println("\nPRESSÃO -> " + pressao + " hPa");


                        int umidade = main.getInt("humidity");
                        System.out.println("\nUMIDADE -> " + umidade +"%");

                        JSONObject coord = jsonResponse.getJSONObject("coord");
                        double lon = coord.getDouble("lon");
                        System.out.println("\nLONGITUDE -> " + lon);

                        double lat = coord.getDouble("lat");
                        System.out.println("\nLATITUDE -> " + lat);


                        int temperaturaKelvin1 = main.getInt("temp_max");
                        int temperaturaCelsius1 = temperaturaKelvin1 - (int)273.15;
                        System.out.println("\nTEMPERATURA MAXIMA -> " + temperaturaCelsius1 + "°C");

                        int temperaturaKelvin2 = main.getInt("temp_min");
                        int temperaturaCelsius2 = temperaturaKelvin2 - (int)273.15;
                        System.out.println("\nTEMPERATURA MINIMA -> " + temperaturaCelsius2 + "°C");


                        int feels = main.getInt("feels_like");
                        int temperaturaCelsius3 = feels - (int)273.15;
                        System.out.println("\nSENSAÇÃO TÉRMICA -> " + temperaturaCelsius3 + "°C");


                        System.out.println("\n____________________________________");



                        System.out.println("APERTE QUALQUER TECLA PARA CONTINUAR");
                        leia.next();

                    } else {


                        limpa();
                        System.out.println("\nCIDADE INVÁLIDA, DIGITE CORRETAMENTE O NOME DA CIDADE\n");
                        Thread.sleep(seg * 1000);

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }








        }while(!op1.equalsIgnoreCase("0"));



























//                System.out.println(response.toString());

        // Aqui você pode fazer o parse dos dados e retornar as informações desejadas

    }

    public static void limpa() throws InterruptedException, IOException {
        new ProcessBuilder ("cmd", "/c",  "cls" ).inheritIO().start().waitFor();

    }







}