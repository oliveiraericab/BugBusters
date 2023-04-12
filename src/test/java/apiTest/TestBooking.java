package apiTest;

import com.google.gson.Gson;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasLength;
import static org.hamcrest.Matchers.is;

    public class TestBooking {   //Início da classe
        //Atributos
        static String ct = "application/json";  //content type
        static String uriUser = "https://restful-booker.herokuapp.com/";

        static int id;

        static String token = "";

        //Funções e métodos
        //Funções de apoio
        public static String lerArquivoJson(String arquivoJson) throws IOException {
            return new String(Files.readAllBytes(Paths.get(arquivoJson)));
        }
        //Funções  de Testes

        @Test
        public void testarCreateBooking() throws IOException {
            // carregar os dados do nosso Json
            String jsonBody = lerArquivoJson("src/test/resources/json/user1.json");
            String username = "Malagueta";

            Response resposta = (Response) given()
                    .contentType(ct)
                    .log().all()
                    .body(jsonBody)
                    .when()
                    .post(uriUser + "booking")
                    .then()
                    .contentType(ct)
                    .log().all()
                    .body("booking.firstname", is("Malagueta"))
                    .body("booking.lastname", is("Oliveira"))
                    .body("booking.bookingdates.checkin", is("2018-01-01"))
                    .body("booking.bookingdates.checkout", is("2019-01-01"))
                    //como extrair o bookingid
                    .extract();
            id = resposta.jsonPath().getInt("bookingid");
        }

        @Test
        public void testarCreateToken() throws IOException {
            String jsonBody = lerArquivoJson("src/test/resources/json/user2.json");

            Response resposta = (Response) given()
                    .contentType(ct)                             // tipo de conteúdo
                    .log().all()                                 // mostre tudo
                    .body(jsonBody)                              // corpo da requisição
                    .when()
                    .post(uriUser + "auth")
                    .then()
                    .contentType(ct)
                    .log().all()
                    .body("token", hasLength(15))
                    .extract();
            token = resposta.jsonPath().getString("token");
        }

        @Test
        public void testarGetBookingIds() {

            given()
                    .contentType(ct)
                    .log().all()
                    .when()
                    .get(uriUser + "booking")
                    .then()
                    .contentType(ct)
                    .log().all()
            //
            ;
        }

        @Test
        public void testarGetBooking() {
            int id = 39;  //testando se a funcao delete esta funcionando.nao esta.

            given()
                    .contentType(ct)
                    .log().all()
                    .when()
                    .get(uriUser + "booking/" + id)
                    .then()
                    .contentType(ct)
                    .log().all()
                   /* .body("firstname", is("Malagueta"))
                    .body("lastname", is("Oliveira"))
                    .body("bookingdates.checkin", is("2018-01-01"))
                    .body("bookingdates.checkout", is("2019-01-01")) */
            ;
        }

        @ParameterizedTest
        @CsvFileSource(resources = "massaCreateBooking.csv", numLinesToSkip = 1, delimiter = ',')
        public void testarCreateBookingCSV(
                String firstname,
                String lastname,
                String totalprice,
                String depositpaid,
                String checkin,
                String checkout) {

            User user = new User();
            user.firstname = firstname;
            user.lastname = lastname;
            user.totalprice = totalprice;
            user.depositpaid = depositpaid;
            user.bookingdates.checkin = checkin;
            user.bookingdates.checkout = checkout;

            Gson gson = new Gson(); //instancia a classe user
            String jsonBody = gson.toJson(user);

            given()
                    .contentType(ct)
                    .log().all()
                    .body(jsonBody)
                    .when()
                    .post(uriUser + "booking")
                    .then()
                    .contentType(ct)
                    .log().all()
                    .body("booking.firstname", is(firstname))
                    .body("booking.lastname", is(lastname))
                    .body("booking.bookingdates.checkin", is(checkin))
                    .body("booking.bookingdates.checkout", is(checkout))
            ;
        }

    }
/*
        @Test
        public void testarUpdateBooking() throws IOException {
            String jsonBody = lerArquivoJson("src/test/resources/json/putUser1.json");

            given()
                    .contentType(ct)
                    .log().all()
                    .header("Cookie","token=" + token) //é opcional
                    .body(jsonBody)
                    .when()
                    .put(uriUser + "booking/" + id)
                    .then()
                    .contentType(ct)
                    .log().all()
                    .statusCode(200)
                    .body("firstname", is("Girassol"))
                    .body("lastname", is("Oliveira"))
            //.body("bookingdates.checkin", is("2018-01-01"))
            //.body("bookingdates.checkout", is("2019-01-01"))
            ;
        }


        @Test
        public void testarPartialUpdateBooking() throws IOException {
            String jsonBody = lerArquivoJson("src/test/resources/json/putPartialUser1.json");

            given()
                    .contentType(ct)
                    .log().all()
                    .header("Cookie","token=" + token)
                    .body(jsonBody)
                    .when()
                    .put(uriUser + "booking/" + id)
                    .then()
                    .contentType(ct)
                    .log().all()
                    .statusCode(200)
                    .body("firstname", is("Girassol"))
                    .body("lastname", is("Oliveira"))
                    .body("bookingdates.checkin", is("2018-01-01"))
                    .body("bookingdates.checkout", is("2019-01-01"))
            ;
        }

         @Test
        public void testarDeleteBooking() {
            int id = 39;

            given()
                    //.auth().oauth2(token)
                    //.cookie("token=<token_value")
                    //.cookie("token=4e74165646ee39d")
                    .contentType(ct)
                    .header("Cookie", "token=" + token) //é opcional
                    .log().all()
                    .when()
                    .delete(uriUser + "booking/" + id)
                    .then()
                    .contentType(ct)
                    .log().all()
                    //A MENSAGEM EXIBIDA QUANDO DELETA É : "HTTP/1.1 201 Created". COMO COMPARAR?
                    //.statusCode(200)
                    //.body("Response", is("HTTP/1.1 201 Created"))
                    //.body("OK", is("HTTP/1.1 201")) >>>>> OK é o nome do campo indicado na api
            ;
        }
 */
