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

        //Funções e métodos
        //Funções de apoio
        public static String lerArquivoJson(String arquivoJson) throws IOException {
            return new String(Files.readAllBytes(Paths.get(arquivoJson)));
        }
        //Funções  de Testes

        @Test
        public void testarCreateToken() throws IOException {
            String jsonBody = lerArquivoJson("src/test/resources/json/user2.json");

            given()
                    .contentType(ct)                             // tipo de conteúdo
                    .log().all()                                 // mostre tudo
                    .body(jsonBody)                              // corpo da requisição
                    .when()
                    .post(uriUser + "auth")
                    .then()
                    .contentType(ct)
                    .log().all()
                    .body("token", hasLength(15))
            //como extrair o token
            ;
        }

        @Test
        public void testarCreateBooking() throws IOException {
            // carregar os dados do nosso Json
            String jsonBody = lerArquivoJson("src/test/resources/json/user1.json");
            String username = "Malagueta";

            given()
                    .contentType(ct)
                    .log().all()
                    .body(jsonBody)
            .when()
                    .post(uriUser + "booking")
            .then()
                    //.accept(ac)
                    .contentType(ct)
                    .log().all()
                    .body("booking.firstname", is("Malagueta"))
                    .body("booking.lastname", is("Oliveira"))
                    .body("booking.bookingdates.checkin", is("2018-01-01"))
                    .body("booking.bookingdates.checkout", is("2019-01-01"))
                  //como extrair o bookingid
            ;
        }

        @Test
        public void testarGetBooking(){
            String id = "5973";

                given()
                    .contentType(ct)
                    .log().all()
                .when()
                    .get( uriUser + "booking/" + id)

                .then()
                    .contentType(ct)
                    .log().all()
                    .body("firstname", is("Malagueta"))
                    .body("lastname", is("Oliveira"))
                    .body("bookingdates.checkin", is("2018-01-01"))
                    .body("bookingdates.checkout", is("2019-01-01"))
                //como comparar depositpaid boolean?
            ;

        }
    /*

        @Test
        public void testarAlterarUser() throws IOException {
            String jsonBody = lerArquivoJson("src/test/resources/json/user2.json");
            String userId = "15215912";
            String username = "malagueta";

            given()
                    .contentType(ct)
                    .log().all()
                    .body(jsonBody)
                    .when()
                    .put(uriUser + username)
                    .then()
                    .log().all()
                    .statusCode(200)
                    .body("code", is(200))
                    .body("type", is("unknown"))
                    .body("message", is(userId))
            ;
        }

        @Test @Order(4)
        public void testarDeletarUser(){
            String username = "malagueta";

            given()
                    .contentType(ct)
                    .log().all()
                    .when()
                    .delete(uriUser + username)
                    .then()
                    .log().all()
                    .statusCode(200)
                    .body("code", is(200))
                    .body("type", is("unknown"))
                    .body("message", is(username))
            ;
        }
 */
        @ParameterizedTest
        @CsvFileSource(resources = "csv/massaCreateBooking", numLinesToSkip = 1, delimiter = ',')
        public void testarCreateBookingCSV(
                String firstname,
                String lastname,
                String totalprice,
                String depositpaid,
                String bookingdates.checkin,
                String bookingdates.checkout){

            User user = new User();
            user.firstname = firstname;
            user.lastname = lastname;
            user.totalprice = totalprice;
            user.depositpaid = depositpaid;
            user.bookingdates.checkin = bookingdates.checkin;
            user.bookingdates.checkout = bookingdates.checkout;

            Gson gson = new Gson();
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
                    .body("bookingid", is(________)) //como extrair o bookingid
                    .body("booking.firstname", is(firstname))
                    .body("booking.lastname", is(lastname))
                    .body("booking.bookingdates.checkin", is(bookingdates.checkin))
                    .body("booking.bookingdates.checkout", is(bookingdates.checkout))
            ;
        }
    }


