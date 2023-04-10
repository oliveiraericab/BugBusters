package apiTest;

import com.google.gson.Gson;
import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

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
            //.body("bookingid", is(id))
            ;
        }

        @Test
        public void testarGetBooking() {

            given()
                    .contentType(ct)
                    .log().all()
                    .when()
                    .get(uriUser + "booking/" + id)
                    .then()
                    .contentType(ct)
                    .log().all()
                    .body("firstname", is("Malagueta"))
                    .body("lastname", is("Oliveira"))
                    .body("bookingdates.checkin", is("2018-01-01"))
                    .body("bookingdates.checkout", is("2019-01-01"))
            ;
        }

        @ParameterizedTest
        @CsvFileSource(resources = "csv/massaCreateBooking.csv", numLinesToSkip = 1, delimiter = ',')
        public void testarCreateBookingCSV(
                String firstname,
                String lastname,
                String totalprice,
                String depositpaid,
                String checkin,
                String checkout){

            User user = new User();
            user.firstname = firstname;
            user.lastname = lastname;
            user.totalprice = totalprice;
            user.depositpaid = depositpaid;
            user.checkin = checkin;
            user.checkout = checkout;

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
/*
        @Test/
        public void testarDeleteBooking(){

            given()
                    .auth().oauth2(token)
                    .contentType(ct)
                    .log().all()
                    .when()
                    .delete(uriUser + "booking/" + id)
                    .then()
            // .statusCode(201)
            // .body("HTTP/1.1", is("Created"))
            ;
        }
*/
    }


