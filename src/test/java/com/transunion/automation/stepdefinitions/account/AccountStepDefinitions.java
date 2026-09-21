package com.transunion.automation.stepdefinitions.account;

import com.transunion.automation.domain.account.models.AccountData;
import com.transunion.automation.domain.account.models.AccountDataFactory;
import com.transunion.automation.domain.account.models.UserDetailResponseDto;
import com.transunion.automation.domain.account.questions.UserDetailResponse;
import com.transunion.automation.domain.account.tasks.CreateAccount;
import com.transunion.automation.domain.account.tasks.DeleteAccount;
import com.transunion.automation.domain.account.tasks.GetUserDetail;
import com.transunion.automation.domain.account.tasks.UpdateAccount;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Y;
import net.serenitybdd.screenplay.actors.OnStage;
import org.assertj.core.api.Assertions;

/**
 * Step definitions for Automation Exercise account lifecycle operations.
 */
public class AccountStepDefinitions {

    private static final String DYNAMIC_USER_SESSION_KEY = "DYNAMIC_USER";

    @Dado("el actor crea una cuenta de usuario dinámica para eliminación")
    @Cuando("el actor envía una solicitud para crear una nueva cuenta con datos válidos")
    public void elActorCreaUnaCuentaDeUsuarioDinamica() {
        AccountData dynamicUser = AccountDataFactory.dynamicUser();
        OnStage.theActorInTheSpotlight().remember(DYNAMIC_USER_SESSION_KEY, dynamicUser);
        OnStage.theActorInTheSpotlight().attemptsTo(CreateAccount.withData(dynamicUser));
    }

    @Cuando("el actor consulta los detalles del usuario con email {string}")
    public void elActorConsultaLosDetallesDelUsuarioConEmail(String email) {
        OnStage.theActorInTheSpotlight().attemptsTo(GetUserDetail.forEmail(email));
    }

    @Cuando("el actor actualiza los datos del perfil para el usuario {string} con nombre {string}")
    public void elActorActualizaLosDatosDelPerfilParaElUsuarioConNombre(String email, String name) {
        AccountData accountData = AccountDataFactory.withEmailPasswordAndName(
                email, AccountDataFactory.DEFAULT_PASSWORD, name);
        OnStage.theActorInTheSpotlight().attemptsTo(UpdateAccount.withData(accountData));
    }

    @Cuando("el actor elimina la cuenta de usuario creada")
    public void elActorEliminaLaCuentaDeUsuarioCreada() {
        AccountData dynamicUser = OnStage.theActorInTheSpotlight().recall(DYNAMIC_USER_SESSION_KEY);
        if (dynamicUser != null) {
            OnStage.theActorInTheSpotlight().attemptsTo(
                    DeleteAccount.withCredentials(dynamicUser.getEmail(), dynamicUser.getPassword())
            );
        }
    }

    @Y("los detalles del usuario consultado deben ser válidos para el email {string}")
    public void losDetallesDelUsuarioConsultadoDebenSerValidosParaElEmail(String email) {
        UserDetailResponseDto response = OnStage.theActorInTheSpotlight().asksFor(UserDetailResponse.received());
        Assertions.assertThat(response.getUser()).isNotNull();
        Assertions.assertThat(response.getUser().getEmail()).isEqualToIgnoringCase(email);
        Assertions.assertThat(response.getUser().getName()).isNotBlank();
    }

    // ── Negative scenario steps ────────────────────────────────────────────────

    @Cuando("el actor intenta crear la misma cuenta de usuario nuevamente")
    public void elActorIntentaCrearLaMismaCuentaDeUsuarioNuevamente() {
        AccountData dynamicUser = OnStage.theActorInTheSpotlight().recall(DYNAMIC_USER_SESSION_KEY);
        OnStage.theActorInTheSpotlight().attemptsTo(CreateAccount.withData(dynamicUser));
    }

    @Cuando("el actor consulta los detalles de un usuario con email inexistente {string}")
    public void elActorConsultaLosDetallesDeUnUsuarioConEmailInexistente(String email) {
        OnStage.theActorInTheSpotlight().attemptsTo(GetUserDetail.forEmail(email));
    }

    @Cuando("el actor intenta eliminar una cuenta con credenciales inválidas")
    public void elActorIntentaEliminarUnaCuentaConCredencialesInvalidas() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                DeleteAccount.withCredentials("invalid@notexist.com", "WrongPass123!")
        );
    }

    @Cuando("el actor intenta crear una cuenta sin proporcionar los campos obligatorios")
    public void elActorIntentaCrearUnaCuentaSinProporcionarLosCamposObligatorios() {
        OnStage.theActorInTheSpotlight().attemptsTo(
                CreateAccount.withData(AccountDataFactory.withoutRequiredFields())
        );
    }

    @Cuando("el actor intenta actualizar los datos de la cuenta para un usuario inexistente {string}")
    public void elActorIntentaActualizarLosDatosDeLaCuentaParaUnUsuarioInexistente(String email) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                UpdateAccount.withData(AccountDataFactory.withEmailAndPassword(email, "WrongPass123!"))
        );
    }

    @Cuando("el actor intenta eliminar una cuenta sin proporcionar la contraseña para el email {string}")
    public void elActorIntentaEliminarUnaCuentaSinProporcionarLaContrasenaParaElEmail(String email) {
        OnStage.theActorInTheSpotlight().attemptsTo(
                DeleteAccount.withoutPassword(email)
        );
    }
}
