package com.transunion.automation.tasks.facades;

import com.transunion.automation.models.request.UserRequestDto;
import com.transunion.automation.tasks.user.CreateUser;
import com.transunion.automation.utils.data.UserDataFactory;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;

/**
 * Facade pattern implementation for orchestrating multi-step user onboarding and complex pre-test setups.
 */
public final class UserOnboardingFacade {

    private UserOnboardingFacade() {
        // Utility facade class
    }

    /**
     * Prepares and creates a standard random user.
     */
    public static Performable createStandardUser() {
        UserRequestDto payload = UserDataFactory.validUser();
        return Task.where("{0} onboards a standard valid user through facade",
                CreateUser.withData(payload)
        );
    }

    /**
     * Prepares and creates a custom user with specific details.
     */
    public static Performable createCustomUser(String name, String job) {
        UserRequestDto payload = UserRequestDto.builder()
                .name(name)
                .job(job)
                .email(name.toLowerCase().replace(" ", ".") + "@transunion.com")
                .build();

        return Task.where("{0} onboards a custom user through facade",
                CreateUser.withData(payload)
        );
    }
}
