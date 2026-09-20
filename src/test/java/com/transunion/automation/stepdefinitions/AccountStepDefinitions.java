package com.transunion.automation.stepdefinitions;

import com.transunion.automation.models.account.AccountData;
import com.transunion.automation.models.account.AccountDataFactory;
import com.transunion.automation.tasks.account.CreateAccount;
import com.transunion.automation.tasks.account.DeleteAccount;
import com.transunion.automation.tasks.account.GetUserDetail;
import com.transunion.automation.tasks.account.UpdateAccount;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import net.serenitybdd.screenplay.actors.OnStage;

/**
 * Step definitions for Automation Exercise account lifecycle operations.
 */
public class AccountStepDefinitions {

    private static final String DYNAMIC_USER_SESSION_KEY = "DYNAMIC_USER";

    @Dado("el actor crea una cuenta de usuario dinámica para eliminación")
    public void elActorCreaUnaCuentaDeUsuarioDinamicaParaEliminacion() {
        AccountData dynamicUser = AccountDataFactory.dynamicUser();
        OnStage.theActorInTheSpotlight().remember(DYNAMIC_USER_SESSION_KEY, dynamicUser);
        OnStage.theActorInTheSpotlight().attemptsTo(CreateAccount.withData(dynamicUser));
    }

    @Cuando("el actor envía una solicitud para crear una nueva cuenta con datos válidos")
    public void elActorEnviaUnaSolicitudParaCrearUnaNuevaCuentaConDatosValidos() {
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
        AccountData accountData = AccountDataFactory.withEmailAndPassword(email, AccountDataFactory.DEFAULT_PASSWORD);
        accountData.setName(name);
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
}
