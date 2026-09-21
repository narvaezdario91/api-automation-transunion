package com.transunion.automation.stepdefinitions.catalog;

import com.transunion.automation.domain.catalog.models.BrandDto;
import com.transunion.automation.domain.catalog.models.BrandsListResponseDto;
import com.transunion.automation.domain.catalog.models.ProductDto;
import com.transunion.automation.domain.catalog.models.ProductsListResponseDto;
import com.transunion.automation.domain.catalog.questions.BrandsListResponse;
import com.transunion.automation.domain.catalog.questions.ProductsListResponse;
import com.transunion.automation.domain.catalog.tasks.GetBrandsList;
import com.transunion.automation.domain.catalog.tasks.GetProductsList;
import com.transunion.automation.domain.catalog.tasks.SearchProduct;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Y;
import net.serenitybdd.screenplay.actors.OnStage;
import org.assertj.core.api.Assertions;

/**
 * Step Definitions for Automation Exercise catalog domain (products, brands, and search).
 */
public class CatalogStepDefinitions {

    @Cuando("el actor consulta la lista completa de productos")
    public void elActorConsultaLaListaCompletaDeProductos() {
        OnStage.theActorInTheSpotlight().attemptsTo(GetProductsList.fromCatalog());
    }

    @Cuando("el actor consulta la lista completa de marcas")
    public void elActorConsultaLaListaCompletaDeMarcas() {
        OnStage.theActorInTheSpotlight().attemptsTo(GetBrandsList.fromCatalog());
    }

    @Cuando("el actor busca productos con la palabra clave {string}")
    public void elActorBuscaProductosConLaPalabraClave(String keyword) {
        OnStage.theActorInTheSpotlight().attemptsTo(SearchProduct.withTerm(keyword));
    }

    @Cuando("el actor busca productos sin proporcionar el parámetro de búsqueda")
    public void elActorBuscaProductosSinProporcionarElParametroDeBusqueda() {
        OnStage.theActorInTheSpotlight().attemptsTo(SearchProduct.withoutParameters());
    }

    @Y("el catálogo debe contener productos con detalles válidos")
    public void elCatalogoDebeContenerProductosConDetallesValidos() {
        ProductsListResponseDto response = OnStage.theActorInTheSpotlight().asksFor(ProductsListResponse.received());
        Assertions.assertThat(response.getProducts())
                .as("La lista de productos no debe estar vacía")
                .isNotEmpty();

        ProductDto firstProduct = response.getProducts().get(0);
        Assertions.assertThat(firstProduct.getId()).isNotNull();
        Assertions.assertThat(firstProduct.getName()).isNotBlank();
        Assertions.assertThat(firstProduct.getPrice()).isNotBlank();
    }

    @Y("el catálogo debe contener marcas con identificadores válidos")
    public void elCatalogoDebeContenerMarcasConIdentificadoresValidos() {
        BrandsListResponseDto response = OnStage.theActorInTheSpotlight().asksFor(BrandsListResponse.received());
        Assertions.assertThat(response.getBrands())
                .as("La lista de marcas no debe estar vacía")
                .isNotEmpty();

        BrandDto firstBrand = response.getBrands().get(0);
        Assertions.assertThat(firstBrand.getId()).isNotNull();
        Assertions.assertThat(firstBrand.getBrand()).isNotBlank();
    }

    @Y("todos los productos devueltos deben coincidir con el criterio de búsqueda para {string}")
    public void todosLosProductosDebenCoincidirConElCriterioDeBusquedaPara(String keyword) {
        ProductsListResponseDto response = OnStage.theActorInTheSpotlight().asksFor(ProductsListResponse.received());
        Assertions.assertThat(response.getProducts())
                .as("El resultado de búsqueda no debe estar vacío")
                .isNotEmpty();

        boolean anyMatchesKeyword = response.getProducts().stream()
                .anyMatch(product -> product.getName().toLowerCase().contains(keyword.toLowerCase())
                        || (product.getCategory() != null && product.getCategory().getCategory() != null
                        && product.getCategory().getCategory().toLowerCase().contains(keyword.toLowerCase())));

        Assertions.assertThat(anyMatchesKeyword)
                .as("Al menos un producto devuelto debe coincidir con la palabra clave: " + keyword)
                .isTrue();
    }
}
