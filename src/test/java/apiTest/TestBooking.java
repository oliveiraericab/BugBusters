package apiTest;

import com.google.gson.Gson;
import io.restassured.response.Response;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasLength;
import static org.hamcrest.Matchers.is;

    @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
    public class TestBooking {   //Início da classe
        //Atributos
        static String ct = "application/json";  //content type
        static String uriUser = "https://restful-booker.herokuapp.com/";

        private static int id;

        private static String token = "";

        //Funções e métodos
        //Funções de apoio
        public static String lerArquivoJson(String arquivoJson) throws IOException {
            return new String(Files.readAllBytes(Paths.get(arquivoJson)));
        }
        //Funções  de Testes

        @Test  @Order(1)
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
                    .statusCode(200)
                    .body("booking.firstname", is("Malagueta"))
                    .body("booking.lastname", is("Oliveira"))
                    .body("booking.bookingdates.checkin", is("2018-01-01"))
                    .body("booking.bookingdates.checkout", is("2019-01-01"))
                    //como extrair o bookingid
                    .extract();
            id = resposta.jsonPath().getInt("bookingid");
        }

        @Test @Order(2)
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
                    .statusCode(200)
                    .body("token", hasLength(15))
                    .extract();
            token = resposta.jsonPath().getString("token");
        }

        @Test @Order(3)
        public void testarGetBookingIds() {

            given()
                    .contentType(ct)
                    .log().all()
                    .when()
                    .get(uriUser + "booking")
                    .then()
                    .contentType(ct)
                    .log().all()
                    .statusCode(200)
            ;
        }

        @Test @Order(4)
        public void testarGetBooking() {

            given()
                    .contentType(ct)
                    .log().all()
                    .when()
                    .get(uriUser + "booking/" + id)
                    .then()
                    .contentType(ct)
                    .log().all()
                    .statusCode(200)
                    .body("firstname", is("Malagueta"))
                    .body("lastname", is("Oliveira"))
                    .body("totalprice", is(1110))
                    .body("bookingdates.checkin", is("2018-01-01"))
                    .body("bookingdates.checkout", is("2019-01-01"))
            ;
        }

        @ParameterizedTest @Order(8)
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


        @Test @Order(5)
        public void testarUpdateBooking() throws IOException {
            String jsonBody = lerArquivoJson("src/test/resources/json/putUser1.json");

            given()
                    .contentType(ct)
                    .log().all()
                    .header("Cookie", "token=" + token) //é opcional
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

        @Test @Order(6)
        public void testarPartialUpdateBooking() throws IOException {
            String jsonBody = lerArquivoJson("src/test/resources/json/putPartialUser1.json");
            testarCreateBooking();
            testarCreateToken();

            given()
                    .contentType(ct)
                    .log().all()
                    .header("Cookie","token=" + token)
                    .body(jsonBody)
                    .when()
                    .patch(uriUser + "booking/" + id)
                    .then()
                    .contentType(ct)
                    .log().all()
                    .statusCode(200)
                    .body("firstname", is("Maga"))
                    .body("lastname", is("Oliveira"))
                    .body("bookingdates.checkin", is("2018-01-01"))
                    .body("bookingdates.checkout", is("2019-01-01"))
            ;
        }

        @Test @Order(7)
        public void testarDeleteBooking() throws IOException {
            testarCreateBooking();
            testarCreateToken();

            given()
                    .contentType(ct)
                    .header("Cookie", "token=" + token) //é opcional
                    .log().all()
                    .when()
                    .delete(uriUser + "booking/" + id)
                    .then()
                    .log().all()
                    .statusCode(201)
                    .body(is("Created"))
            ;
        }
    }
    /*
    Pontos de Atenção:
    1. Extração das informações/ salvar em variáveis externas dentro da classe. Estudar como extrair.
    Ainda assim, quando a variável é usada em um método, os métodos seguintes não conseguem utilizar.
    Devo salvar num arquivo externo exemplo "token.txt"/"id.txt" para que seja consultada quantas vezes seja necessário.
    2. No método com massa de teste, é necessário criar uma classe em arquivo separado para mapear os itens dos jsons.
    Só após fazer isso consegui rodar o teste com massa.

    Tirar dúvidas:
    1. Preciso chamar as funções nos endpoints patch e delete.
    2. O que é o contentType e pq não utiliza na resposta da requisição, só no envio?
    Quando tirei da resposta do delete o teste passou.
     */